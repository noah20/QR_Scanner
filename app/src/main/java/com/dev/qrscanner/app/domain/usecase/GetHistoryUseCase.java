package com.dev.qrscanner.app.domain.usecase;

import androidx.lifecycle.LiveData;

import com.dev.qrscanner.app.domain.model.QrCodeModel;

import java.util.List;

public interface GetHistoryUseCase {

    public LiveData<List<QrCodeModel>> getAllHistory();

}
