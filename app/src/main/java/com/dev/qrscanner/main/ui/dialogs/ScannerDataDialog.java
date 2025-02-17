package com.dev.qrscanner.main.ui.dialogs;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dev.qrscanner.R;
import com.dev.qrscanner.databinding.DialogScannerDataBinding;
import com.dev.qrscanner.main.data.model.ScannerDataDialogUiConfig;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import dagger.hilt.android.AndroidEntryPoint;


@AndroidEntryPoint
public class ScannerDataDialog extends BottomSheetDialogFragment {

    public interface OnActionClicked{
        void invoke();
    }

    private OnActionClicked mListener;
    private ScannerDataDialogUiConfig uiConfig;
    private DialogScannerDataBinding mBinding;

    @Override
    public int getTheme() {
        return R.style.RoundBottomSheetDialog;
    }

    public static ScannerDataDialog newInstance(ScannerDataDialogUiConfig uiConfig,OnActionClicked listener) {
        ScannerDataDialog fragment = new ScannerDataDialog();
        fragment.uiConfig  = uiConfig;
        fragment.mListener  = listener;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DialogScannerDataBinding.inflate(inflater,container,false);

        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(uiConfig != null){
            mBinding.tvMessage.setText(uiConfig.getMessage());
            mBinding.button.setText(uiConfig.getActionName());
            mBinding.tvTitle.setText(uiConfig.getTitle());
        }
        mBinding.button.setOnClickListener(v -> {
            dismiss();
            if (mListener != null){
                mListener.invoke();
            }
        });
    }
}