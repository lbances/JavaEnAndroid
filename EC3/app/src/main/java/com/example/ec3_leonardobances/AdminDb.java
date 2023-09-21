package com.example.ec3_leonardobances;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminDb extends SQLiteOpenHelper {

    public AdminDb(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table cuidades(id_cuidad int primary key, cuidad text, latitud double, longitud double)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table cuidades");
        db.execSQL("create table cuidades(id_cuidad int primary key, cuidad text, latitud double, longitud double)");
    }

}
