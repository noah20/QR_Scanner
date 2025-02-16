package com.dev.qrscanner.main.ui.home;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dev.qrscanner.R;
import com.dev.qrscanner.databinding.FragmentHomeBinding;
import com.dev.qrscanner.main.app.BaseFragment;
import com.dev.qrscanner.main.app.MainActivity;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanIntentResult;
import com.journeyapps.barcodescanner.ScanOptions;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HomeFragment extends BaseFragment {

    private HomeViewModel mViewModel;

    private FragmentHomeBinding mBinding;

    private final ActivityResultLauncher<ScanOptions> barcodeLauncher = registerForActivityResult(new ScanContract(),
            this::handleResult);


    private void handleResult(ScanIntentResult result) {
        if (result.getContents() == null) {
            Log.d("TAG", "noah here canceled: ");
        } else {
            saveCode(result.getContents());
            Log.d("TAG", "noah here : Scanned: " + result.getContents());
        }
    }

    private void saveCode(String content) {
        mViewModel.insertCode(content);
    }

    public void onScanClick(View view) {
        barcodeLauncher.launch(new ScanOptions());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        if (mBinding == null)
            mBinding = FragmentHomeBinding.inflate(inflater, container, false);

        return mBinding.getRoot();
    }

    private void initViews() {
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_history, R.id.navigation_favorite)
                .build();
        NavController navController =  NavHostFragment.findNavController(this);
        NavigationUI.setupActionBarWithNavController((MainActivity) requireActivity(), navController, appBarConfiguration);
        NavigationUI.setupWithNavController(mBinding.navView, navController);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
        initActions();
    }

    private void initActions() {
        mBinding.btnScan.setOnClickListener(this::onScanClick);
    }


}