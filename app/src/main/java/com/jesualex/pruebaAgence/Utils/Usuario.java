package com.jesualex.pruebaAgence.Utils;

import android.database.Cursor;

import com.jesualex.pruebaAgence.SQLite.contracts.UsuariosContract.UsuarioDB;
/**
 * Created by Jesualex.
 */
public class Usuario {
    private int id;
    private String seudonimo;
    private String email;
    private String pass;
    private String nombres;
    private String apellidos;

    public Usuario(String seudonimo, String email, String pass, String nombres, String apellidos) {
        this.seudonimo = seudonimo;
        this.email = email;
        this.pass = pass;
        this.nombres = nombres;
        this.apellidos = apellidos;
    }

    public Usuario() {
    }

    public Usuario(Cursor cursor){

        setId(cursor.getInt(cursor.getColumnIndexOrThrow(UsuarioDB._ID)));

        setEmail(cursor.getString(cursor.getColumnIndexOrThrow(UsuarioDB.COLUMN_EMAIL)));

        setSeudonimo(cursor.getString(cursor.getColumnIndexOrThrow(UsuarioDB.COLUMN_SEUDONIMO)));

        setPass(cursor.getString(cursor.getColumnIndexOrThrow(UsuarioDB.COLUMN_PASS)));

        setNombres(cursor.getString(cursor.getColumnIndexOrThrow(UsuarioDB.COLUMN_NOMBRES)));

        setApellidos(cursor.getString(cursor.getColumnIndexOrThrow(UsuarioDB.COLUMN_APELLIDOS)));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSeudonimo() {
        return seudonimo;
    }

    public void setSeudonimo(String seudonimo) {
        this.seudonimo = seudonimo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
}
