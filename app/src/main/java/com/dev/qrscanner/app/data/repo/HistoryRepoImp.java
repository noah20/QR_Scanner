package com.dev.qrscanner.app.data.repo;

import androidx.lifecycle.LiveData;

import com.dev.qrscanner.app.data.db.DataBaseDao;
import com.dev.qrscanner.app.domain.model.QrCodeModel;
import com.dev.qrscanner.app.domain.repo.HistoryRepo;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Completable;

public class HistoryRepoImp implements HistoryRepo {

    private final DataBaseDao db;

    @Inject
    public HistoryRepoImp(DataBaseDao db) {
        this.db = db;
    }

    @Override
    public LiveData<List<QrCodeModel>> getAllHistory() {
        return db.getAllHistory();
    }

    @Override
    public Completable insertItem(QrCodeModel code) {
       return db.insertHistoryItem(code);
    }

}
