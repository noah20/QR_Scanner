package com.dev.qrscanner.main.domain.usecase;

import androidx.lifecycle.LiveData;

import com.dev.qrscanner.main.domain.model.QrCodeModel;
import java.util.List;

public interface GetFavoritesUseCase {


    public LiveData<List<QrCodeModel>> getAllFavorites() ;
}
