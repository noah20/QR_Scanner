package com.dev.qrscanner.main.di;

import android.content.Context;

import com.dev.qrscanner.main.data.db.DataBaseDao;
import com.dev.qrscanner.main.data.db.UserDatabase;
import com.dev.qrscanner.main.data.repo.FavoritesRepoImp;
import com.dev.qrscanner.main.data.repo.HistoryRepoImp;
import com.dev.qrscanner.main.data.usecase.GetFavoritesUseCaseImp;
import com.dev.qrscanner.main.data.usecase.GetHistoryUseCaseImp;
import com.dev.qrscanner.main.data.usecase.InsertHistoryItemUseCaseImp;
import com.dev.qrscanner.main.data.usecase.UpdateItemUseCaseImp;
import com.dev.qrscanner.main.domain.repo.FavoritesRepo;
import com.dev.qrscanner.main.domain.repo.HistoryRepo;
import com.dev.qrscanner.main.domain.usecase.GetFavoritesUseCase;
import com.dev.qrscanner.main.domain.usecase.GetHistoryUseCase;
import com.dev.qrscanner.main.domain.usecase.InsertHistoryItemUseCase;
import com.dev.qrscanner.main.domain.usecase.UpdateItemUseCase;

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
