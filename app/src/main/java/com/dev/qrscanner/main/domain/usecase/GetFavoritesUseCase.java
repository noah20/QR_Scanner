package com.dev.qrscanner.main.domain.usecase;

import androidx.lifecycle.LiveData;

import com.dev.qrscanner.main.data.model.QrCodeModel;
import java.util.List;

public interface GetFavoritesUseCase {


    public LiveData<List<QrCodeModel>> getAlFavorites() ;
}
