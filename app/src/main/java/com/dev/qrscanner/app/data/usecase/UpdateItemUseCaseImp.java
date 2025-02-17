package com.dev.qrscanner.app.data.usecase;

import com.dev.qrscanner.app.domain.model.QrCodeModel;
import com.dev.qrscanner.app.domain.repo.FavoritesRepo;
import com.dev.qrscanner.app.domain.usecase.UpdateItemUseCase;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Completable;

public class UpdateItemUseCaseImp implements UpdateItemUseCase {

    private final FavoritesRepo favoritesRepo;

    @Inject
    public UpdateItemUseCaseImp(FavoritesRepo favoritesRepo) {
        this.favoritesRepo = favoritesRepo;
    }


    @Override
    public Completable update(QrCodeModel code) {
        return favoritesRepo.updateItem(code);
    }
}
