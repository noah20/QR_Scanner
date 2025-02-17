package com.dev.qrscanner.main.ui.home;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.dev.qrscanner.main.app.BaseViewModel;
import com.dev.qrscanner.main.data.model.QrCodeModel;
import com.dev.qrscanner.main.domain.usecase.GetHistoryUseCase;
import com.dev.qrscanner.main.domain.usecase.InsertHistoryItemUseCase;
import com.dev.qrscanner.utils.AppExecutor;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

@HiltViewModel
public class HomeViewModel extends BaseViewModel {

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    public HomeViewModel() {

    }

    @Inject
    InsertHistoryItemUseCase insertHistoryItemUseCase;
    @Inject
    GetHistoryUseCase getHistoryUseCase;

    public void insertCode(String code) {
        Disposable d = insertHistoryItemUseCase.insert(new QrCodeModel(code,false))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() ->{});
        addDisposable(d);

    }


}