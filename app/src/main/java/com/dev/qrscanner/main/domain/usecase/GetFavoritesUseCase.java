package com.dev.qrscanner.main.domain.usecase;

import androidx.lifecycle.LiveData;

import com.dev.qrscanner.main.data.model.FavoriteModel;

import java.util.List;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Single;

public interface GetFavoritesUseCase {


    public LiveData<List<FavoriteModel>> getAlFavorites() ;
}
