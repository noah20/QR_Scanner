package com.dev.qrscanner.main.domain.usecase;

import io.reactivex.rxjava3.core.Completable;

public interface InsertHistoryItemUseCase {

    public Completable insert(String code);
}
