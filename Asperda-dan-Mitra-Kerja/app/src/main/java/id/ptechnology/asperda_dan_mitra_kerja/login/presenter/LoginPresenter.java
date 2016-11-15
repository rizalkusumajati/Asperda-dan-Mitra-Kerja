package id.ptechnology.asperda_dan_mitra_kerja.login.presenter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;

import id.ptechnology.asperda_dan_mitra_kerja.login.view.LoginFragment;
import id.ptechnology.asperda_dan_mitra_kerja.main.view.MainActivity;
import id.ptechnology.asperda_dan_mitra_kerja.registrasi.view.RegistrasiFragment;

/**
 * Created by macmini2 on 11/11/16.
 */

public interface LoginPresenter {

    void gotoRegistrasi(LoginFragment loginFragment, RegistrasiFragment registrasiFragment);
    void login(String username, String password, MainActivity activity, ProgressDialog progressDialog);
    void getMemberDetail(String path, MainActivity activity);

    boolean isUsernameValid(String username);
    boolean validate (String username,String password);

    //login g+
//    GoogleApiClient createGoogleSignIn(GoogleApiClient mGoogleApiClient, Context context, FragmentActivity fragmentActivity, GoogleApiClient.OnConnectionFailedListener connectionFailedListener);
//    void handleOnStart(GoogleApiClient mGoogleApiClient,MainActivity activity);
//    void handleSignInResult(GoogleSignInResult result, MainActivity activity);
//    void signInGoogle(Activity activity, GoogleApiClient mGoogleApiClient, int RC_SIGN_IN);
//    void handleActivityResult(int requestCode, int RC_SIGN_IN, Intent data, MainActivity activity);
}
