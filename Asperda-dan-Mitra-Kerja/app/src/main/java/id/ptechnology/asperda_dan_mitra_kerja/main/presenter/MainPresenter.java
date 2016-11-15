package id.ptechnology.asperda_dan_mitra_kerja.main.presenter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by macmini2 on 11/11/16.
 */

public interface MainPresenter {

    void tryRetrofit();
    void handleLogout(Activity activity);
    GoogleApiClient createGoogleSignIn(GoogleApiClient mGoogleApiClient, Context context, FragmentActivity fragmentActivity, GoogleApiClient.OnConnectionFailedListener connectionFailedListener);
    void signInGoogle(Activity activity, GoogleApiClient mGoogleApiClient, int RC_SIGN_IN);
    void logout(GoogleApiClient mGoogleApiClient, Activity activity);
    void handleOnStart(GoogleApiClient mGoogleApiClient, final Activity activity);
    void handleActivityResult(int requestCode,int RC_SIGN_IN, Intent data,Activity activity);
    void handleSignInResult(GoogleSignInResult result, Activity activity);
    void handleOnStartLogout(Context context);
    void getMemberByEmail(Activity activity);

}
