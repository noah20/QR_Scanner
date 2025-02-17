package com.dev.qrscanner.main.ui.favorit;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dev.qrscanner.databinding.FragmentFavoritesBinding;
import com.dev.qrscanner.main.app.BaseFragment;
import com.dev.qrscanner.main.ui.history.QrCodesAdapter;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class FavoritesFragment extends BaseFragment {

    private FavoritesViewModel mViewModel;
    private FragmentFavoritesBinding mBinding;
    private QrCodesAdapter mAdapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(FavoritesViewModel.class);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        if(mBinding == null)
            mBinding = FragmentFavoritesBinding.inflate(inflater,container,false);
        return mBinding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpRecyclerView();
        initObservers();
    }

    private void initObservers() {
        mViewModel.getFavorites().observe(getViewLifecycleOwner(), qrCodeHistoryModels -> {
            mAdapter.setCodes(qrCodeHistoryModels);
        });
    }

    private void setUpRecyclerView() {
        mAdapter = new QrCodesAdapter();
        mBinding.rvFavorites.setAdapter(mAdapter);
        mAdapter.setListener(qrCodeModel -> {
            mViewModel.updateItem(qrCodeModel);
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mBinding = null;
    }

}