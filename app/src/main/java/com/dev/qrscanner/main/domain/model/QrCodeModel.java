package com.dev.qrscanner.main.domain.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "history_table")
public class QrCodeModel {
    private String qrCode;
    private boolean isFavorite;

    @PrimaryKey(autoGenerate = true)
    private int id;

    public QrCodeModel(String qrCode, boolean isFavorite) {
        this.qrCode = qrCode;
        this.isFavorite = isFavorite;
    }

    public String getQrCode() {
        return qrCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public boolean isFavorite() {
        return isFavorite;
    }
    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}
