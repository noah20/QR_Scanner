package com.dev.qrscanner.main.ui.history;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dev.qrscanner.main.app.BaseViewModel;
import com.dev.qrscanner.main.data.model.QrCodeHistoryModel;
import com.dev.qrscanner.main.domain.usecase.GetHistoryUseCase;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;


@HiltViewModel
public class HistoryViewModel extends BaseViewModel {

    private final GetHistoryUseCase historyUseCase;

    @Inject
    public HistoryViewModel(GetHistoryUseCase historyUseCase) {
        this.historyUseCase = historyUseCase;
    }

    public LiveData<List<QrCodeHistoryModel>> getCodes() {
        return historyUseCase.getAllHistory();
    }

}