package com.jesualex.pruebaAgence.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.jesualex.pruebaAgence.SQLite.Utils.UsuariosUtils;

/**
 * Created by Jesualex.
 */

public class SessionManager {
    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";
    private static final String KEY_USUARIO = "user";
    private static String TAG = SessionManager.class.getSimpleName();
    private static final int DATABASE_VERSION = 1;
    private Context context;
    private UsuariosUtils usuariosUtils;

    public SessionManager(Context context) {
        this.context = context;
        this.usuariosUtils = new UsuariosUtils(context, DATABASE_VERSION);
        if(this.usuariosUtils.isEmpty()) this.usuariosUtils.crearUsuariosPrueba();
    }

    /**
     * @param seudonimo
     * @param pass
     * @return null|Usuario
     */
    public Usuario setLogin(String seudonimo, String pass) {
        Usuario us = this.usuariosUtils.getUsuarioBySeudonimoAndPass(seudonimo,pass);

        if(us != null){
            SharedPreferences pref = context.getSharedPreferences(TAG, Context.MODE_PRIVATE);
            Editor editor = pref.edit();
            editor.putString(KEY_USUARIO, seudonimo);
            editor.putBoolean(KEY_IS_LOGGED_IN, true);
            editor.apply();
        }
        return us;
    }

    public Usuario getUsuario(){
        SharedPreferences pref = context.getSharedPreferences(TAG, Context.MODE_PRIVATE);
        String usuario = pref.getString(KEY_USUARIO, "");
        return this.usuariosUtils.getUsuarioBySeudonimo(usuario);
    }

    public void logout(){
        SharedPreferences pref = context.getSharedPreferences(TAG, Context.MODE_PRIVATE);
        Editor editor = pref.edit();
        editor.putBoolean(KEY_IS_LOGGED_IN, false);
        editor.apply();
    }

    public boolean isLoggedIn() {
        SharedPreferences pref = context.getSharedPreferences(TAG, Context.MODE_PRIVATE);
        return pref.getBoolean(KEY_IS_LOGGED_IN, false);
    }
}
