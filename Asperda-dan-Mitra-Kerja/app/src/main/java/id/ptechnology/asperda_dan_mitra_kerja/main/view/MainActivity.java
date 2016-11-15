package id.ptechnology.asperda_dan_mitra_kerja.main.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import id.ptechnology.asperda_dan_mitra_kerja.dashboard.view.DashboardFragment;
import id.ptechnology.asperda_dan_mitra_kerja.R;
import id.ptechnology.asperda_dan_mitra_kerja.login.view.LoginFragment;
import id.ptechnology.asperda_dan_mitra_kerja.main.presenter.MainPresenter;
import id.ptechnology.asperda_dan_mitra_kerja.main.presenter.MainPresenterImp;
import id.ptechnology.asperda_dan_mitra_kerja.model.Constant;
import id.ptechnology.asperda_dan_mitra_kerja.preferences.PrefHelper;
import id.ptechnology.asperda_dan_mitra_kerja.preferences.PrefKey;
import id.ptechnology.asperda_dan_mitra_kerja.registrasi.view.RegistrasiFragment;

import static id.ptechnology.asperda_dan_mitra_kerja.model.Constant.mGoogleApiClient;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, GoogleApiClient.OnConnectionFailedListener {
    private DashboardFragment dashboardFragment;
    private LoginFragment loginFragment;
    private RegistrasiFragment registrasiFragment;
    private ProgressDialog progressDialog;
    private MainPresenter presenter;
    private NavigationView navigationView;
    private TextView tv_namaPerusahaan,tv_nama;
    public GoogleApiClient googleApiClient;
    private static final int RC_SIGN_IN = 9001;

    @Override
    protected void onStart() {
        super.onStart();

        if (Constant.getmGoogleApiClient() != null)
            Constant.getmGoogleApiClient().connect();
        else {

            Constant.setmGoogleApiClient(presenter.createGoogleSignIn(googleApiClient, this, this, this));
        }

        presenter.handleOnStart(Constant.getmGoogleApiClient(),this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        presenter=new MainPresenterImp();
        dashboardFragment=new DashboardFragment();
        registrasiFragment=new RegistrasiFragment();
        loginFragment=new LoginFragment();
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        System.out.println("isLogin " + PrefHelper.getBoolean(PrefKey.PREF_LOGIN));

        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Authenticating, please wait!");
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(false);



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
            //mGoogleApiClient.stopAutoManage(LoginFragment);
           // mGoogleApiClient.disconnect();
           // Constant.setmGoogleApiClient(null);
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

    public void gotoRegister(){
        getSupportFragmentManager().beginTransaction()

                .replace(R.id.content_main, registrasiFragment)
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

        presenter.handleLogout(MainActivity.this);

        defaultName();
        showItem();
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d("Google", "onConnectionFailed:" + connectionResult);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.handleActivityResult(requestCode,RC_SIGN_IN,data,this);
    }

    public void signInGoogle(){
        presenter.signInGoogle(MainActivity.this,Constant.getmGoogleApiClient(),RC_SIGN_IN);
    }
}
