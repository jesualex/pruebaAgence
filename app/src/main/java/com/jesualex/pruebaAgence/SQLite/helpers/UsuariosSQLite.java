package com.jesualex.pruebaAgence.SQLite.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.jesualex.pruebaAgence.SQLite.contracts.UsuariosContract.UsuarioDB;

/**
 * Created by Jesualex.
 */
public class UsuariosSQLite extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Usuarios.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + UsuarioDB.TABLE_NAME + " (" +
                    UsuarioDB._ID + " INTEGER PRIMARY KEY," +
                    UsuarioDB.COLUMN_SEUDONIMO + " TEXT NOT NULL UNIQUE," +
                    UsuarioDB.COLUMN_EMAIL + " TEXT NOT NULL UNIQUE," +
                    UsuarioDB.COLUMN_PASS + " TEXT NOT NULL," +
                    UsuarioDB.COLUMN_NOMBRES + " TEXT," +
                    UsuarioDB.COLUMN_APELLIDOS + " TEXT" +
            ")";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + UsuarioDB.TABLE_NAME;

    public UsuariosSQLite(Context context, int version) {
        super(context, DATABASE_NAME, null, version);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
