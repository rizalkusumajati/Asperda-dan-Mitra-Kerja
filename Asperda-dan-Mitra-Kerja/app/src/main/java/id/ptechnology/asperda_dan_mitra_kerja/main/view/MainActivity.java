package id.ptechnology.asperda_dan_mitra_kerja.main.view;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import id.ptechnology.asperda_dan_mitra_kerja.R;
import id.ptechnology.asperda_dan_mitra_kerja.account.view.AccountFragment;
import id.ptechnology.asperda_dan_mitra_kerja.dashboard.view.DashboardFragment;
import id.ptechnology.asperda_dan_mitra_kerja.login.view.LoginFragment;
import id.ptechnology.asperda_dan_mitra_kerja.main.presenter.MainPresenter;
import id.ptechnology.asperda_dan_mitra_kerja.main.presenter.MainPresenterImp;
import id.ptechnology.asperda_dan_mitra_kerja.model.Constant;
import id.ptechnology.asperda_dan_mitra_kerja.preferences.PrefHelper;
import id.ptechnology.asperda_dan_mitra_kerja.preferences.PrefKey;
import id.ptechnology.asperda_dan_mitra_kerja.registrasi.view.RegistrasiFragment;

import static id.ptechnology.asperda_dan_mitra_kerja.model.Constant.mGoogleApiClient;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks,
        com.google.android.gms.location.LocationListener {
    private DashboardFragment dashboardFragment,fragment;
    private LoginFragment loginFragment;
    private RegistrasiFragment registrasiFragment;
    private ProgressDialog progressDialog, progressDialog1;
    private MainPresenter presenter;
    private NavigationView navigationView;
    private TextView tv_namaPerusahaan, tv_nama;
    public GoogleApiClient googleApiClient;
    private static final int RC_SIGN_IN = 9001;
    private CallbackManager callbackManager;
    private LocationManager manager;
    private static final int REQUEST_CODE = 0;
    private AccountFragment accountFragment;
    // private LocationListener locationListener;
    private Location location;
    private String provider;


    LocationRequest mLocationRequest;
    //GoogleApiClient mLocationClient;
    Location mCurrentLocation;


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {


        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        gotoHome();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (Constant.getmGoogleApiClient() != null)
            Constant.getmGoogleApiClient().connect();
        else {

            Constant.setmGoogleApiClient(presenter.createGoogleSignIn(googleApiClient, this, this, this,this));
        }

        presenter.handleOnStart(Constant.getmGoogleApiClient(), this);
    //    mGoogleApiClient.connect();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        presenter = new MainPresenterImp();
        dashboardFragment = new DashboardFragment();
        registrasiFragment = new RegistrasiFragment();
        loginFragment = new LoginFragment();
        accountFragment=new AccountFragment();
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        System.out.println("isLogin " + PrefHelper.getBoolean(PrefKey.PREF_LOGIN));
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        callbackManager = CallbackManager.Factory.create();
        manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);


        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Authenticating, please wait!");
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(false);

        progressDialog1 = new ProgressDialog(this);
        progressDialog1.setMessage("Fetching location, please wait!");
        progressDialog1.setIndeterminate(false);
        progressDialog1.setCancelable(false);

       // presenter.tryRetrofit();
        if (PrefHelper.getBoolean(PrefKey.PREF_LOGIN)) {
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

        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        criteria.setCostAllowed(true);
        criteria.setPowerRequirement(Criteria.POWER_MEDIUM);

        provider = manager.getBestProvider(criteria, true);

        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps();
        }
            else {
                Log.i("LocChange", "else condition");
                gotoHome();
            }







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

    public void hideItem() {

        Menu nav_Menu = navigationView.getMenu();
        nav_Menu.findItem(R.id.nav_login).setVisible(false);
        nav_Menu.findItem(R.id.nav_logout).setVisible(true);
    }

    public void showItem() {

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
            gotoSetting();
        } else if (id == R.id.nav_login) {
            gotoLogin();
        } else if (id == R.id.nav_logout) {
            logout();
            //mGoogleApiClient.stopAutoManage(LoginFragment);
            // mGoogleApiClient.disconnect();
            // Constant.setmGoogleApiClient(null);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void gotoHome() {
        Constant.setOnDashboard(true);
        presenter.gotoFragment(this, dashboardFragment);


    }

    public void gotoLogin() {
        presenter.gotoFragment(this, loginFragment);


    }

    public void gotoSetting(){
        presenter.gotoFragment(this,accountFragment);
    }

    public void gotoRegister() {
        presenter.gotoFragment(this, registrasiFragment);

    }

    public void changeName() {
        View header = navigationView.getHeaderView(0);
        /*View view=navigationView.inflateHeaderView(R.layout.nav_header_main);*/
        tv_namaPerusahaan = (TextView) header.findViewById(R.id.tv_namaPerusahaan);
        tv_nama = (TextView) header.findViewById(R.id.tv_nama);
        tv_namaPerusahaan.setText(PrefHelper.getString(PrefKey.PREF_LOGIN_NAMA_PERUSAHAAN));
        tv_nama.setText(PrefHelper.getString(PrefKey.PREF_LOGIN_NAME));
    }

    public void defaultName() {
        View header = navigationView.getHeaderView(0);
        /*View view=navigationView.inflateHeaderView(R.layout.nav_header_main);*/
        tv_namaPerusahaan = (TextView) header.findViewById(R.id.tv_namaPerusahaan);
        tv_nama = (TextView) header.findViewById(R.id.tv_nama);
        tv_namaPerusahaan.setText("Company Name");
        tv_nama.setText("Your Name");
    }

    public void logout() {

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
        if (requestCode == RC_SIGN_IN)
            presenter.handleActivityResult(requestCode, RC_SIGN_IN, data, this);
        else if (requestCode == REQUEST_CODE && resultCode == 0) {
            String provider = Settings.Secure.getString(getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
            if (manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {



                gotoHome();
//                manager.getLastKnownLocation(this.provider);
//                gotoHome();
                //Start searching for location and update the location text when update available.
                // Do whatever you want

            } else {
                buildAlertMessageNoGps();
                //Users did not switch on the GPS
            }
        }else{
            callbackManager.onActivityResult(requestCode, resultCode, data);

        }

    }



    public void signInGoogle() {
        presenter.signInGoogle(MainActivity.this, Constant.getmGoogleApiClient(), RC_SIGN_IN);
    }

    public void loginFacebook(LoginButton loginButton) {
        presenter.loginFb(loginButton, callbackManager, MainActivity.this);

    }


    public void buildAlertMessageNoGps() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("GPS harus diaktifkan untuk menggunakan aplikasi ini")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        dialog.dismiss();
                        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivityForResult(intent, REQUEST_CODE);
                    }
                });
//                .setNegativeButton("No", new DialogInterface.OnClickListener() {
//                    public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
//                        dialog.cancel();
//                    }
//                });
        final AlertDialog alert = builder.create();
        alert.show();

    }

    @Override
    public void onLocationChanged(Location location) {
        Log.i("Google","OnLocationchange");
        Constant.setMyLokasi(location);

        if (fragment!=null){

            if (fragment.isVisible()) {
                fragment.setListViewData();
            }
        }

        //  progressDialog1.dismiss();
       // gotoHome();
    }



    @Override
    public void onConnected(@Nullable Bundle bundle) {
       // progressDialog1.show();
        if (Constant.onDashboard) {
            fragment = (DashboardFragment) getSupportFragmentManager().findFragmentById(R.id.content_main);
        }
        Log.i("GoogleApi","connected");
        mLocationRequest = LocationRequest.create();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(1000); // Update location every second


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }





}
