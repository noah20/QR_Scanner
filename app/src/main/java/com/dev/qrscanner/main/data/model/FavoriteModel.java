package com.dev.qrscanner.main.data.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "favorites_table")
public class FavoriteModel implements QrCodeModel{
    private String qrCode;
    @PrimaryKey(autoGenerate = true)
    private int id;

    public FavoriteModel(String qrCode) {
        this.qrCode = qrCode;
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

    @Override
    public String getCode() {
        return  this.qrCode;
    }
}
