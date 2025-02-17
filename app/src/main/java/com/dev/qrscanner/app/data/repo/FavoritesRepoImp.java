package com.dev.qrscanner.app.data.repo;

import androidx.lifecycle.LiveData;

import com.dev.qrscanner.app.data.db.DataBaseDao;
import com.dev.qrscanner.app.domain.model.QrCodeModel;
import com.dev.qrscanner.app.domain.repo.FavoritesRepo;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Completable;

public class FavoritesRepoImp  implements FavoritesRepo {
    private final DataBaseDao db;

    @Inject
    public FavoritesRepoImp(DataBaseDao db) {
        this.db = db;
    }

    @Override
    public @NonNull LiveData<List<QrCodeModel>> getAlFavorites() {
        return db.getAllFavorites();

    }

    @Override
    public Completable updateItem(QrCodeModel code) {
        return db.insertHistoryItem(code);
    }


}
