package com.dev.qrscanner.app.base;

import androidx.lifecycle.ViewModel;

import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;

public class BaseViewModel extends ViewModel {

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();


    public void addDisposable(Disposable d) {
        if (d == null)
            return;
        compositeDisposable.add(d);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }
}
