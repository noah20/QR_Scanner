package com.dev.qrscanner.main.domain.repo;

import androidx.lifecycle.LiveData;

import com.dev.qrscanner.main.domain.model.QrCodeModel;
import java.util.List;

import io.reactivex.rxjava3.core.Completable;

public interface FavoritesRepo {

    public LiveData<List<QrCodeModel>> getAlFavorites();

    public Completable updateItem(QrCodeModel code);

}
