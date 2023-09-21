package com.example.ejercicio22;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class bdAlumnos extends SQLiteOpenHelper {
    public bdAlumnos(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table alumnos(idAlu int primary key, nomAlu text, nota1 double, nota2 double, nota3 double, nota4 double)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("create table alumnos(idAlu int primary key, nomAlu text, nota1 double, nota2 double, nota3 double, nota4 double)");
    }
}
