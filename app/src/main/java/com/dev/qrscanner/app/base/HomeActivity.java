package com.dev.qrscanner.app.base;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.dev.qrscanner.R;
import com.dev.qrscanner.databinding.ActivityMainBinding;
import com.dev.qrscanner.app.data.model.ScannerDataDialogUiConfig;
import com.dev.qrscanner.app.ui.home.HomeViewModel;
import com.dev.qrscanner.app.ui.dialogs.ScannerDataDialog;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanIntentResult;
import com.journeyapps.barcodescanner.ScanOptions;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HomeActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private HomeViewModel mViewModel;

    private final ActivityResultLauncher<ScanOptions> barcodeLauncher =
            registerForActivityResult(new ScanContract(), this::handleResult);

    private final ActivityResultLauncher<String> requestCameraPermission =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), this::handleCameraPermission);


    private void handleResult(ScanIntentResult result) {
        if (result.getContents() == null) {
            ScannerDataDialogUiConfig config = new ScannerDataDialogUiConfig(getString(R.string.canceled), getString(R.string.scan_canceled), getString(R.string.got_it));
            ScannerDataDialog d = ScannerDataDialog.newInstance(config, null);
            d.show(getSupportFragmentManager(), "canceled");
        } else {
            validateAndSave(result.getContents());
        }
    }
    private void validateAndSave(String contents) {
        if(contents == null || contents.isEmpty()){
            ScannerDataDialogUiConfig config = new ScannerDataDialogUiConfig(getString(R.string.invalid), getString(R.string.code_is_empty_or_invalid), getString(R.string.rescan));
            ScannerDataDialog d = ScannerDataDialog.newInstance(config, this::onScanClick);
            d.show(getSupportFragmentManager(), "canceled");
        }else {
            mViewModel.insertCode(contents);
        }
    }

    public void onScanClick() {

        if (permissionGranted()) {
            barcodeLauncher.launch(new ScanOptions());
        } else {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
                ScannerDataDialogUiConfig config = new ScannerDataDialogUiConfig(getString(R.string.permission_required), getString(R.string.camera_permission_denied), getString(R.string.grant));
                ScannerDataDialog d = ScannerDataDialog.newInstance(config, () -> {
                    requestCameraPermission.launch(Manifest.permission.CAMERA);
                });
                d.show(getSupportFragmentManager(), "canceled");

            } else {
                requestCameraPermission.launch(Manifest.permission.CAMERA);
            }
        }
    }

    private boolean permissionGranted() {
        return ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
    }

    private void handleCameraPermission(Boolean o) {
        if (o) {
            barcodeLauncher.launch(new ScanOptions());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        NavController navController = Navigation.findNavController(this, R.id.nav_host);
        NavigationUI.setupWithNavController(binding.navView, navController);

        setActions();

    }

    private void setActions() {
        binding.btnScan.setOnClickListener(view -> onScanClick());
    }

}