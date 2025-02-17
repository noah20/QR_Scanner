package com.dev.qrscanner.main.data.usecase;

import com.dev.qrscanner.main.data.model.QrCodeModel;
import com.dev.qrscanner.main.domain.repo.FavoritesRepo;
import com.dev.qrscanner.main.domain.repo.HistoryRepo;
import com.dev.qrscanner.main.domain.usecase.InsertHistoryItemUseCase;
import com.dev.qrscanner.main.domain.usecase.UpdateItemUseCase;

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
