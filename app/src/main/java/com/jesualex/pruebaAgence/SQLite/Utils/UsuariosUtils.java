package com.jesualex.pruebaAgence.SQLite.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.jesualex.pruebaAgence.SQLite.contracts.UsuariosContract.UsuarioDB;
import com.jesualex.pruebaAgence.SQLite.helpers.UsuariosSQLite;
import com.jesualex.pruebaAgence.Utils.Usuario;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jesualex.
 */
public class UsuariosUtils {
    UsuariosSQLite usuariosHelper;

    public UsuariosUtils(Context context, int dbVersion){
        usuariosHelper = new UsuariosSQLite(context,dbVersion);
    }

    public boolean isEmpty(){
        SQLiteDatabase db = usuariosHelper.getReadableDatabase();
        Cursor cursor = db.query(
                UsuarioDB.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );

        return !(cursor.getCount() > 0);
    }

    public long crearUsuario(Usuario us){
        SQLiteDatabase db = usuariosHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(UsuarioDB.COLUMN_EMAIL, us.getEmail());
        values.put(UsuarioDB.COLUMN_SEUDONIMO, us.getSeudonimo());
        values.put(UsuarioDB.COLUMN_PASS, us.getPass());
        values.put(UsuarioDB.COLUMN_NOMBRES, us.getNombres());
        values.put(UsuarioDB.COLUMN_APELLIDOS, us.getApellidos());

        return db.insert(UsuarioDB.TABLE_NAME, null, values);
    }

    public Usuario getUsuarioBySeudonimoAndPass(String seudonimo, String pass ) {
        SQLiteDatabase db = usuariosHelper.getReadableDatabase();

        String selection =
                UsuarioDB.COLUMN_SEUDONIMO + " = ? AND " +
                UsuarioDB.COLUMN_PASS + " = ?"
        ;
        String[] selectionArgs = {seudonimo, pass};

        Cursor cursor = db.query(
                UsuarioDB.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
        Usuario us = null;

        if(cursor.moveToNext()) us = new Usuario(cursor);

        cursor.close();

        return us;
    }

    public Usuario getUsuarioBySeudonimo(String seudonimo) {
        SQLiteDatabase db = usuariosHelper.getReadableDatabase();

        String selection =
                UsuarioDB.COLUMN_SEUDONIMO + " = ?";
        String[] selectionArgs = {seudonimo};

        Cursor cursor = db.query(
                UsuarioDB.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );
        Usuario us = null;

        if(cursor.moveToNext()) us = new Usuario(cursor);

        cursor.close();

        return us;
    }

    public void crearUsuariosPrueba(){
        List<Usuario> Usuarios = new ArrayList<>();

        Usuarios.add(new Usuario(
                "user",
                "user@prueba.com",
                "123456",
                "pedro",
                "rodriguez"
        ));

        Usuarios.add(new Usuario(
                "admin",
                "admin@prueba.com",
                "123456",
                "administrador",
                ""
        ));

        Usuarios.add(new Usuario(
                "jesualex",
                "jesualex@gmail.com",
                "123456",
                "Jesús",
                "Garcia"
        ));

        for(Usuario us : Usuarios){
            crearUsuario(us);
        }
    }
}
