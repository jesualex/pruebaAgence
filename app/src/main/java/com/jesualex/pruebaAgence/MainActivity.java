package com.jesualex.pruebaAgence;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.jesualex.pruebaAgence.Utils.SessionManager;
import com.jesualex.pruebaAgence.Utils.Usuario;
import com.jesualex.pruebaAgence.Utils.WebViewClient;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Jesualex.
 */
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private WebViewClient webViewClient;
    private WebView mainWebView;
    private DrawerLayout mainDrawer;
    private SessionManager sessionManager;
    private Usuario usuario;


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

    private int lanzarNotificacion(String CHANNEL_ID, String title, String subtitle, int icon){
        Date currentTime = Calendar.getInstance().getTime();
        int notificationId = (int) currentTime.getTime();
        CHANNEL_ID = CHANNEL_ID != null ? CHANNEL_ID : getPackageName() + "-not-default";

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, CHANNEL_ID )
                .setSmallIcon(icon)
                .setContentTitle(title)
                .setContentText(subtitle)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        notificationManager.notify(CHANNEL_ID, notificationId, mBuilder.build());

        return  notificationId;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sessionManager = new SessionManager(this);
        usuario = sessionManager.getUsuario();

        if(!sessionManager.isLoggedIn() || usuario == null){
            startActivity(new Intent(this, CargaActivity.class));
            finish();
        }

        setContentView(R.layout.activity_main);

        mainDrawer = findViewById(R.id.drawer_layout);
        mainWebView = findViewById(R.id.mainWebView);

        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, this.mainDrawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        this.mainDrawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View drawerHead = navigationView.getHeaderView(0);
        TextView drawerHeadTitle = drawerHead.findViewById(R.id.navHeaderTitle);
        TextView drawerHeadSubtitle = drawerHead.findViewById(R.id.navHeaderSubtitle);

        drawerHeadTitle.setText(String.format("%s %s", usuario.getNombres(), usuario.getApellidos()));
        drawerHeadSubtitle.setText(usuario.getEmail() );
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
                lanzarNotificacion(null, "Notification", "Agence Test", R.drawable.ic_notifications_white);
            break;

            case R.id.logout:
                sessionManager.logout();
                startActivity(new Intent(this, CargaActivity.class));
                finish();
            break;
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
