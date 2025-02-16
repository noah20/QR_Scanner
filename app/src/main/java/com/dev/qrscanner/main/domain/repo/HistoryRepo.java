package com.dev.qrscanner.main.domain.repo;

import androidx.lifecycle.LiveData;

import com.dev.qrscanner.main.data.model.QrCodeHistoryModel;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

public interface HistoryRepo {

    public LiveData<List<QrCodeHistoryModel>> getAllHistory();

    public Completable insertItem(String code);
}
