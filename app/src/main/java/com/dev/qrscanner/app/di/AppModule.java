package com.dev.qrscanner.app.di;

import android.content.Context;

import com.dev.qrscanner.app.data.db.DataBaseDao;
import com.dev.qrscanner.app.data.db.UserDatabase;
import com.dev.qrscanner.app.data.repo.FavoritesRepoImp;
import com.dev.qrscanner.app.data.repo.HistoryRepoImp;
import com.dev.qrscanner.app.data.usecase.GetFavoritesUseCaseImp;
import com.dev.qrscanner.app.data.usecase.GetHistoryUseCaseImp;
import com.dev.qrscanner.app.data.usecase.InsertHistoryItemUseCaseImp;
import com.dev.qrscanner.app.data.usecase.UpdateItemUseCaseImp;
import com.dev.qrscanner.app.domain.repo.FavoritesRepo;
import com.dev.qrscanner.app.domain.repo.HistoryRepo;
import com.dev.qrscanner.app.domain.usecase.GetFavoritesUseCase;
import com.dev.qrscanner.app.domain.usecase.GetHistoryUseCase;
import com.dev.qrscanner.app.domain.usecase.InsertHistoryItemUseCase;
import com.dev.qrscanner.app.domain.usecase.UpdateItemUseCase;

import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {

    @Provides
    @Singleton
    DataBaseDao provideUserDao(@ApplicationContext Context context){
        return UserDatabase.getDatabase(context).userDao();
    }

    @Provides
    @Singleton
    HistoryRepo provideHistoryRepo(DataBaseDao db){
        return new HistoryRepoImp(db);
    }

    @Provides
    @Singleton
    FavoritesRepo provideFavoritesRepo(DataBaseDao db){
        return new FavoritesRepoImp(db);
    }

    @Provides
    @Singleton
    GetFavoritesUseCase provideFavoriteUseCase(FavoritesRepo favoritesRepo){
        return new GetFavoritesUseCaseImp(favoritesRepo);
    }

    @Provides
    @Singleton
    GetHistoryUseCase provideGetHistoryUseCase(HistoryRepo historyRepo){
        return new GetHistoryUseCaseImp(historyRepo);
    }

    @Provides
    @Singleton
    InsertHistoryItemUseCase provideInsertHistoryItemUseCase(HistoryRepo repo){
        return new InsertHistoryItemUseCaseImp(repo);
    }

    @Provides
    @Singleton
    UpdateItemUseCase provideUpdateItemUseCase(FavoritesRepo repo){
        return new UpdateItemUseCaseImp(repo);
    }

}
