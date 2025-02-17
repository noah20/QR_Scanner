package com.dev.qrscanner.app.domain.usecase;

import com.dev.qrscanner.app.domain.model.QrCodeModel;

import io.reactivex.rxjava3.core.Completable;

public interface InsertHistoryItemUseCase {

    public Completable insert(QrCodeModel code);
}
