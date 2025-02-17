package com.dev.qrscanner.main.domain.usecase;

import com.dev.qrscanner.main.data.model.QrCodeModel;

import io.reactivex.rxjava3.core.Completable;

public interface InsertHistoryItemUseCase {

    public Completable insert(QrCodeModel code);
}
