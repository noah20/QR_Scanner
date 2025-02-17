package com.dev.qrscanner.app.domain.repo;

import androidx.lifecycle.LiveData;

import com.dev.qrscanner.app.domain.model.QrCodeModel;
import java.util.List;

import io.reactivex.rxjava3.core.Completable;

public interface FavoritesRepo {

    public LiveData<List<QrCodeModel>> getAlFavorites();

    public Completable updateItem(QrCodeModel code);

}
