package com.dev.qrscanner.main.data.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import com.dev.qrscanner.main.data.model.FavoriteModel;
import com.dev.qrscanner.main.data.model.QrCodeHistoryModel;
import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Maybe;

@Dao
public interface DataBaseDao {

    @Insert( entity = FavoriteModel.class ,onConflict = OnConflictStrategy.REPLACE)
    Completable insertFavorites(FavoriteModel qr);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    Completable updateFavorites(FavoriteModel qr);

    @Delete
    Completable deleteFavorites(FavoriteModel qr);

    @Query("DELETE FROM favorites_table")
    Completable deleteAllFavorites();

    @Query("SELECT * FROM favorites_table")
    LiveData<List<FavoriteModel>> getAllFavorites();

    @Query("SELECT * FROM history_table")
    LiveData<List<QrCodeHistoryModel>> getAllHistory();

    @Query("DELETE FROM history_table")
    Completable deleteAllHistory();

    @Insert( entity = QrCodeHistoryModel.class, onConflict = OnConflictStrategy.REPLACE)
    Completable insertHistoryItem(QrCodeHistoryModel qr);

}
