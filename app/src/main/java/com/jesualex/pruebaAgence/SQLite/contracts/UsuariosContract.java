package com.jesualex.pruebaAgence.SQLite.contracts;

import android.provider.BaseColumns;

/**
 * Created by Jesualex.
 */
public class UsuariosContract {
    private UsuariosContract() {}

    public static class UsuarioDB implements BaseColumns {
        public static final String TABLE_NAME = "usuario";
        public static final String COLUMN_SEUDONIMO = "seudonimo";
        public static final String COLUMN_EMAIL = "email";
        public static final String COLUMN_NOMBRES = "nombres";
        public static final String COLUMN_APELLIDOS = "apellidos";
        public static final String COLUMN_PASS = "pass";
    }
}
