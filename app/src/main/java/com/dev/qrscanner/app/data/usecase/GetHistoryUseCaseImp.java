package com.dev.qrscanner.app.data.usecase;

import androidx.lifecycle.LiveData;

import com.dev.qrscanner.app.domain.model.QrCodeModel;
import com.dev.qrscanner.app.domain.repo.HistoryRepo;
import com.dev.qrscanner.app.domain.usecase.GetHistoryUseCase;

import java.util.List;

import javax.inject.Inject;

public class GetHistoryUseCaseImp implements GetHistoryUseCase {

    private final HistoryRepo historyRepo;

    @Inject
    public GetHistoryUseCaseImp(HistoryRepo historyRepo) {
        this.historyRepo = historyRepo;
    }

    @Override
    public LiveData<List<QrCodeModel>> getAllHistory() {
        return historyRepo.getAllHistory();
    }

}
