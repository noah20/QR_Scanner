package com.dev.qrscanner.main.domain.repo;

import androidx.lifecycle.LiveData;

import com.dev.qrscanner.main.data.model.FavoriteModel;

import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Single;

public interface FavoritesRepo {

    public LiveData<List<FavoriteModel>> getAlFavorites();

}
