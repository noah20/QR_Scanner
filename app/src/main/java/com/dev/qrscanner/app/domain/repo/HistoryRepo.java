package com.dev.qrscanner.app.domain.repo;

import androidx.lifecycle.LiveData;

import com.dev.qrscanner.app.domain.model.QrCodeModel;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;

public interface HistoryRepo {

    public LiveData<List<QrCodeModel>> getAllHistory();

    public Completable insertItem(QrCodeModel code);
}
