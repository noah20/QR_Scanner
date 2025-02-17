package com.dev.qrscanner.main.app;

import android.os.Bundle;
import android.view.View;
import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import com.dev.qrscanner.R;
import com.dev.qrscanner.databinding.ActivityMainBinding;
import com.dev.qrscanner.main.data.model.ScannerDataDialogUiConfig;
import com.dev.qrscanner.main.ui.home.HomeViewModel;
import com.dev.qrscanner.utils.views.ScannerDataDialog;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanIntentResult;
import com.journeyapps.barcodescanner.ScanOptions;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private HomeViewModel mViewModel;

    private final ActivityResultLauncher<ScanOptions> barcodeLauncher = registerForActivityResult(new ScanContract(),
            this::handleResult);


    private void handleResult(ScanIntentResult result) {
        if (result.getContents() == null) {
            ScannerDataDialogUiConfig config = new  ScannerDataDialogUiConfig("Canceled", "Scan canceled" , "got it");
            ScannerDataDialog d = ScannerDataDialog.newInstance(config, null);
            d.show(getSupportFragmentManager() , "canceled");
        } else {
            saveCode(result.getContents());
        }
    }

    private void saveCode(String content) {
        mViewModel.insertCode(content);
    }

    public void onScanClick(View view) {
        barcodeLauncher.launch(new ScanOptions());
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
        binding.btnScan.setOnClickListener(this::onScanClick);
    }

}