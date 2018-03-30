package com.jesualex.pruebaAgence;

import android.content.Intent;

import android.os.Bundle;
import android.support.v4.graphics.TypefaceCompatUtil;
import android.support.v7.app.AppCompatActivity;

import com.jesualex.pruebaAgence.Utils.SessionManager;

/**
 * Created by Jesualex.
 */
public class CargaActivity extends AppCompatActivity {
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.session = new SessionManager(getApplicationContext());
        if (session.isLoggedIn()) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }else{
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
    }
}
