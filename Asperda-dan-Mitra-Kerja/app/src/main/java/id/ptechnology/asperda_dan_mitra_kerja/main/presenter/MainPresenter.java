package id.ptechnology.asperda_dan_mitra_kerja.main.presenter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.facebook.CallbackManager;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import id.ptechnology.asperda_dan_mitra_kerja.main.view.MainActivity;
import retrofit2.Callback;

/**
 * Created by macmini2 on 11/11/16.
 */

public interface MainPresenter {

    void tryRetrofit();
    void handleLogout(Activity activity);
    void gotoFragment(MainActivity activity, Fragment fragment);
    GoogleApiClient createGoogleSignIn(GoogleApiClient mGoogleApiClient, Context context, FragmentActivity fragmentActivity, GoogleApiClient.OnConnectionFailedListener connectionFailedListener);
    void signInGoogle(Activity activity, GoogleApiClient mGoogleApiClient, int RC_SIGN_IN);
    void logout(GoogleApiClient mGoogleApiClient, Activity activity);
    void handleOnStart(GoogleApiClient mGoogleApiClient, final Activity activity);
    void handleActivityResult(int requestCode,int RC_SIGN_IN, Intent data,Activity activity);
    void handleSignInResult(GoogleSignInResult result, Activity activity);
    void handleOnStartLogout(Context context);
    void getMemberByEmail(Activity activity);

    //login fb
    void loginFb(LoginButton loginButton, CallbackManager callbackManager,Activity activity);
    Bundle getFacebookData(JSONObject object);
    void getMemberByFb(Activity activity);

    //gps
    void buildAlertMessageNoGps(MainActivity activity);

}
