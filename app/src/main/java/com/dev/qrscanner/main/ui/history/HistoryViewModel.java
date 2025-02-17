package com.dev.qrscanner.main.ui.history;

import androidx.lifecycle.LiveData;

import com.dev.qrscanner.main.app.BaseViewModel;
import com.dev.qrscanner.main.data.model.QrCodeModel;
import com.dev.qrscanner.main.domain.usecase.GetHistoryUseCase;
import com.dev.qrscanner.main.domain.usecase.UpdateItemUseCase;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;


@HiltViewModel
public class HistoryViewModel extends BaseViewModel {

    private final GetHistoryUseCase historyUseCase;
    private final UpdateItemUseCase updateItemUseCase;

    @Inject
    public HistoryViewModel(GetHistoryUseCase historyUseCase,UpdateItemUseCase updateItemUseCase) {
        this.historyUseCase = historyUseCase;
        this.updateItemUseCase = updateItemUseCase;
    }

    public LiveData<List<QrCodeModel>> getHistory() {
        return historyUseCase.getAllHistory();
    }

    public void updateItem(QrCodeModel qrCodeModel) {
        if(qrCodeModel == null)
            return;
        qrCodeModel.setFavorite(!qrCodeModel.isFavorite());
        Disposable d =  updateItemUseCase.update(qrCodeModel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() ->{});
        addDisposable(d);
    }
}