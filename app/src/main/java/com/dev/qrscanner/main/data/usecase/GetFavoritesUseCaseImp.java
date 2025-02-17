package com.dev.qrscanner.main.data.usecase;

import androidx.lifecycle.LiveData;

import com.dev.qrscanner.main.data.model.QrCodeModel;
import com.dev.qrscanner.main.domain.repo.FavoritesRepo;
import com.dev.qrscanner.main.domain.usecase.GetFavoritesUseCase;
import java.util.List;
import javax.inject.Inject;


public class GetFavoritesUseCaseImp implements GetFavoritesUseCase {

    private final FavoritesRepo favoritesRepo;

    @Inject
    public GetFavoritesUseCaseImp(FavoritesRepo favoritesRepo) {
        this.favoritesRepo = favoritesRepo;
    }

    @Override
    public LiveData<List<QrCodeModel>> getAlFavorites() {
        return favoritesRepo.getAlFavorites();
    }
}
