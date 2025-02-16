package com.dev.qrscanner.main.data.usecase;

import androidx.lifecycle.LiveData;

import com.dev.qrscanner.main.data.model.QrCodeHistoryModel;
import com.dev.qrscanner.main.domain.repo.HistoryRepo;
import com.dev.qrscanner.main.domain.usecase.GetHistoryUseCase;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;

public class GetHistoryUseCaseImp implements GetHistoryUseCase {

    private final HistoryRepo historyRepo;

    @Inject
    public GetHistoryUseCaseImp(HistoryRepo historyRepo) {
        this.historyRepo = historyRepo;
    }

    @Override
    public LiveData<List<QrCodeHistoryModel>> getAllHistory() {
        return historyRepo.getAllHistory();
    }

}
