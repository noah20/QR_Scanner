package com.dev.qrscanner.app.data.usecase;

import androidx.lifecycle.LiveData;

import com.dev.qrscanner.app.domain.model.QrCodeModel;
import com.dev.qrscanner.app.domain.repo.FavoritesRepo;
import com.dev.qrscanner.app.domain.usecase.GetFavoritesUseCase;
import java.util.List;
import javax.inject.Inject;


public class GetFavoritesUseCaseImp implements GetFavoritesUseCase {

    private final FavoritesRepo favoritesRepo;

    @Inject
    public GetFavoritesUseCaseImp(FavoritesRepo favoritesRepo) {
        this.favoritesRepo = favoritesRepo;
    }

    @Override
    public LiveData<List<QrCodeModel>> getAllFavorites() {
        return favoritesRepo.getAlFavorites();
    }
}
