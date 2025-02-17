package com.dev.qrscanner.main.domain.usecase;

import com.dev.qrscanner.main.data.model.QrCodeModel;

import io.reactivex.rxjava3.core.Completable;

public interface UpdateItemUseCase {

    public Completable update(QrCodeModel code);

}
