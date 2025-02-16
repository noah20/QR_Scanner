package com.dev.qrscanner.main.domain.usecase;

import androidx.lifecycle.LiveData;

import com.dev.qrscanner.main.data.model.QrCodeHistoryModel;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;

public interface GetHistoryUseCase {

    public LiveData<List<QrCodeHistoryModel>> getAllHistory();

}
