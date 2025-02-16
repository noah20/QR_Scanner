package com.dev.qrscanner.main.data.repo;

import androidx.lifecycle.LiveData;

import com.dev.qrscanner.main.data.db.DataBaseDao;
import com.dev.qrscanner.main.data.model.QrCodeHistoryModel;
import com.dev.qrscanner.main.domain.repo.HistoryRepo;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

public class HistoryRepoImp implements HistoryRepo {

    private final DataBaseDao db;

    @Inject
    public HistoryRepoImp(DataBaseDao db) {
        this.db = db;
    }

    @Override
    public LiveData<List<QrCodeHistoryModel>> getAllHistory() {
        return db.getAllHistory();
    }

    @Override
    public Completable insertItem(String code) {
       return db.insertHistoryItem(new QrCodeHistoryModel(code));
    }

}
