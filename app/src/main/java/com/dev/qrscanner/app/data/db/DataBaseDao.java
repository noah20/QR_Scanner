package com.dev.qrscanner.app.data.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import com.dev.qrscanner.app.domain.model.QrCodeModel;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;

@Dao
public interface DataBaseDao {



    @Update(onConflict = OnConflictStrategy.REPLACE)
    Completable updateCode(QrCodeModel qr);


    @Query("SELECT * FROM history_table where isFavorite = 1")
    LiveData<List<QrCodeModel>> getAllFavorites();

    @Query("SELECT * FROM history_table")
    LiveData<List<QrCodeModel>> getAllHistory();

    @Query("DELETE FROM history_table")
    Completable deleteAllHistory();

    @Insert( entity = QrCodeModel.class, onConflict = OnConflictStrategy.REPLACE)
    Completable insertHistoryItem(QrCodeModel qr);

}
