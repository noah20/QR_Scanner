package com.dev.qrscanner.app.data.usecase;

import com.dev.qrscanner.app.domain.model.QrCodeModel;
import com.dev.qrscanner.app.domain.repo.HistoryRepo;
import com.dev.qrscanner.app.domain.usecase.InsertHistoryItemUseCase;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Completable;

public class InsertHistoryItemUseCaseImp implements InsertHistoryItemUseCase {

    private final HistoryRepo historyRepo;

    @Inject
    public InsertHistoryItemUseCaseImp(HistoryRepo historyRepo) {
        this.historyRepo = historyRepo;
    }

    @Override
    public Completable insert(QrCodeModel code) {
       return historyRepo.insertItem(code);
    }

}
