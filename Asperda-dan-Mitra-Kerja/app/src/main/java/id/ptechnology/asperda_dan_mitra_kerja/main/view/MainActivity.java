package id.ptechnology.asperda_dan_mitra_kerja.main.view;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import id.ptechnology.asperda_dan_mitra_kerja.dashboard.view.DashboardFragment;
import id.ptechnology.asperda_dan_mitra_kerja.R;
import id.ptechnology.asperda_dan_mitra_kerja.login.view.LoginFragment;
import id.ptechnology.asperda_dan_mitra_kerja.main.presenter.MainPresenter;
import id.ptechnology.asperda_dan_mitra_kerja.main.presenter.MainPresenterImp;
import id.ptechnology.asperda_dan_mitra_kerja.preferences.PrefHelper;
import id.ptechnology.asperda_dan_mitra_kerja.preferences.PrefKey;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private DashboardFragment dashboardFragment;
    private LoginFragment loginFragment;
    private MainPresenter presenter;
    private NavigationView navigationView;
    private TextView tv_namaPerusahaan,tv_nama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        presenter=new MainPresenterImp();
        dashboardFragment=new DashboardFragment();
        loginFragment=new LoginFragment();
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        System.out.println("isLogin " + PrefHelper.getBoolean(PrefKey.PREF_LOGIN));




       // presenter.tryRetrofit();
        if (PrefHelper.getBoolean(PrefKey.PREF_LOGIN)){
            hideItem();
            changeName();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        gotoHome();


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            int fragments = getSupportFragmentManager().getBackStackEntryCount();
            if (fragments == 1) {
                finish();
            } else {
                if (getFragmentManager().getBackStackEntryCount() > 1) {
                    getFragmentManager().popBackStack();
                } else {
                    super.onBackPressed();
                }
            }
        }
    }

    public void hideItem()
    {

        Menu nav_Menu = navigationView.getMenu();
        nav_Menu.findItem(R.id.nav_login).setVisible(false);
        nav_Menu.findItem(R.id.nav_logout).setVisible(true);
    }

    public void showItem()
    {

        Menu nav_Menu = navigationView.getMenu();
        nav_Menu.findItem(R.id.nav_logout).setVisible(false);
        nav_Menu.findItem(R.id.nav_login).setVisible(true);
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
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            gotoHome();
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        else if (id == R.id.nav_login) {
            gotoLogin();
        }else if (id == R.id.nav_logout) {
            logout();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void gotoHome(){
        getSupportFragmentManager().beginTransaction()

                .replace(R.id.content_main, dashboardFragment)
                .addToBackStack(null)
                .commit();

    }

    public void gotoLogin(){
        getSupportFragmentManager().beginTransaction()

                .replace(R.id.content_main, loginFragment)
                .addToBackStack(null)
                .commit();

    }

    public void changeName(){
        View header=navigationView.getHeaderView(0);
        /*View view=navigationView.inflateHeaderView(R.layout.nav_header_main);*/
        tv_namaPerusahaan = (TextView)header.findViewById(R.id.tv_namaPerusahaan);
        tv_nama = (TextView)header.findViewById(R.id.tv_nama);
        tv_namaPerusahaan.setText(PrefHelper.getString(PrefKey.PREF_LOGIN_NAMA_PERUSAHAAN));
        tv_nama.setText(PrefHelper.getString(PrefKey.PREF_LOGIN_NAME));
    }

    public void defaultName(){
        View header=navigationView.getHeaderView(0);
        /*View view=navigationView.inflateHeaderView(R.layout.nav_header_main);*/
        tv_namaPerusahaan = (TextView)header.findViewById(R.id.tv_namaPerusahaan);
        tv_nama = (TextView)header.findViewById(R.id.tv_nama);
        tv_namaPerusahaan.setText("Company Name");
        tv_nama.setText("Your Name");
    }

    public void logout(){
        PrefHelper.setBoolean(PrefKey.PREF_LOGIN,false);
        PrefHelper.clearPreference(PrefKey.PREF_LOGIN_ID);
        PrefHelper.clearPreference(PrefKey.PREF_LOGIN_NAME);
        defaultName();
        showItem();
    }



}
