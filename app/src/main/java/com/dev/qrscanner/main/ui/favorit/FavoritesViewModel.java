package com.dev.qrscanner.main.ui.favorit;

import androidx.lifecycle.LiveData;

import com.dev.qrscanner.main.app.BaseViewModel;
import com.dev.qrscanner.main.data.model.QrCodeModel;
import com.dev.qrscanner.main.domain.usecase.GetFavoritesUseCase;
import com.dev.qrscanner.main.domain.usecase.UpdateItemUseCase;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

@HiltViewModel
public class FavoritesViewModel extends BaseViewModel {


    private final UpdateItemUseCase updateItemUseCase;
    private final GetFavoritesUseCase getFavoritesUseCase;

    @Inject
    public FavoritesViewModel(UpdateItemUseCase updateItemUseCase,GetFavoritesUseCase getFavoritesUseCase) {
        this.updateItemUseCase = updateItemUseCase;
        this.getFavoritesUseCase = getFavoritesUseCase;

    }

    public LiveData<List<QrCodeModel>> getFavorites() {
        return getFavoritesUseCase.getAllFavorites();
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