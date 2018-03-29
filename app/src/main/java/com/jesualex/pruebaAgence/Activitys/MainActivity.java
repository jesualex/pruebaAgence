package com.jesualex.pruebaAgence.Activitys;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

import com.jesualex.pruebaAgence.R;

import Utils.WebViewClient;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private WebViewClient webViewClient;
    private WebView mainWebView;
    private DrawerLayout mainDrawer;

    /**
     * @param webView WebView al que se le cargara la Uri
     * @param url url a cargar en el WebView
     */
    private void setWebview(WebView webView, String url){
        if(this.webViewClient == null)
            this.webViewClient = new WebViewClient();

        webView.setWebViewClient(this.webViewClient);
        webView.loadUrl(url);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mainDrawer = findViewById(R.id.drawer_layout);
        this.mainWebView = findViewById(R.id.mainWebView);
        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, this.mainDrawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        this.mainDrawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        if (this.mainDrawer.isDrawerOpen(GravityCompat.START))
            this.mainDrawer.closeDrawer(GravityCompat.START);
        else if(this.mainWebView.canGoBack())
            this.mainWebView.goBack();
        else
            super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        switch (item.getItemId()){

            case R.id.goToGoogle:
                String url = "http://www.google.com";
                this.setWebview( this.mainWebView, url );
            break;

            case R.id.sendNotification:

            break;
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
