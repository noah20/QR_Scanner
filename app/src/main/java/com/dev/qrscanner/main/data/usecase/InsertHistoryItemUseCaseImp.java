package com.dev.qrscanner.main.data.usecase;

import com.dev.qrscanner.main.domain.repo.HistoryRepo;
import com.dev.qrscanner.main.domain.usecase.InsertHistoryItemUseCase;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Completable;

public class InsertHistoryItemUseCaseImp implements InsertHistoryItemUseCase {

    private final HistoryRepo historyRepo;

    @Inject
    public InsertHistoryItemUseCaseImp(HistoryRepo historyRepo) {
        this.historyRepo = historyRepo;
    }

    @Override
    public Completable insert(String code) {
       return historyRepo.insertItem(code);
    }

}
