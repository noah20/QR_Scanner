package com.dev.qrscanner.main.data.db;


import android.content.Context;
import android.util.Base64;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.dev.qrscanner.main.domain.model.QrCodeModel;
import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SupportFactory;

import java.nio.charset.StandardCharsets;


@Database(entities = { QrCodeModel.class}, version = 1, exportSchema = false)
public abstract class UserDatabase extends RoomDatabase {

    public abstract DataBaseDao userDao();

    private static final String DB_NAME = "user_database";
    private static final String encrypted_key = "REIjZmVXMiUkRERG";
    private static UserDatabase mInstance ;

    public static synchronized UserDatabase getDatabase(Context context){
        if(mInstance == null){
            mInstance = Room.databaseBuilder(context,UserDatabase.class, DB_NAME)
                    .fallbackToDestructiveMigration()
                 //   .openHelperFactory(getSupportFactory())
                    .build();
        }
        return mInstance;
    }

    private static SupportSQLiteOpenHelper.Factory getSupportFactory() {
        return new SupportFactory(SQLiteDatabase.getBytes(getEncryptionKey()));
    }

    private static char[] getEncryptionKey() {
        return decryptString().toCharArray();
    }

    @NonNull
    private static String decryptString() {
        try {
            byte[] decrypt= Base64.decode(UserDatabase.encrypted_key, Base64.DEFAULT);
            return new String(decrypt, StandardCharsets.UTF_8);
        } catch (Exception e) {
            return "";
        }
    }

}
