package com.dev.qrscanner.app.ui.home;

import com.dev.qrscanner.app.base.BaseViewModel;
import com.dev.qrscanner.app.domain.model.QrCodeModel;
import com.dev.qrscanner.app.domain.usecase.GetHistoryUseCase;
import com.dev.qrscanner.app.domain.usecase.InsertHistoryItemUseCase;

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