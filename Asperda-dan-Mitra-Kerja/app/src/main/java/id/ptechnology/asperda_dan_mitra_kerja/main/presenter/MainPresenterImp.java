package id.ptechnology.asperda_dan_mitra_kerja.main.presenter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationServices;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import id.ptechnology.asperda_dan_mitra_kerja.R;
import id.ptechnology.asperda_dan_mitra_kerja.api.MemberResponse;
import id.ptechnology.asperda_dan_mitra_kerja.api.ServiceGenerator;
import id.ptechnology.asperda_dan_mitra_kerja.main.view.MainActivity;
import id.ptechnology.asperda_dan_mitra_kerja.model.Constant;
import id.ptechnology.asperda_dan_mitra_kerja.preferences.PrefHelper;
import id.ptechnology.asperda_dan_mitra_kerja.preferences.PrefKey;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import static id.ptechnology.asperda_dan_mitra_kerja.model.Constant.mGoogleApiClient;
import static id.ptechnology.asperda_dan_mitra_kerja.model.Constant.namaGoogle;

/**
 * Created by macmini2 on 11/11/16.
 */

public class MainPresenterImp implements MainPresenter {
    private String TAG="Google";
    private GoogleApiClient mGoogleApiClient1;
    private static final int REQUEST_CODE = 0;
    @Override
    public void tryRetrofit() {
        new ServiceGenerator().getMember(new Callback<List<MemberResponse>>() {
            @Override
            public void onResponse(Call<List<MemberResponse>> call, Response<List<MemberResponse>> response) {
                System.out.println("Response code: "+ response.code());

               // System.out.println("Response body: "+response.body().get(1).getNamaMember());
            }

            @Override
            public void onFailure(Call<List<MemberResponse>> call, Throwable t) {
                System.out.println("Response failed: "+t.getMessage());
            }
        });
    }

    @Override
    public void handleLogout(Activity activity) {
        switch (PrefHelper.getString(PrefKey.PREF_LOGIN_VIA)){
            case "api":
                PrefHelper.setBoolean(PrefKey.PREF_LOGIN,false);
                PrefHelper.clearPreference(PrefKey.PREF_LOGIN_ID);
                PrefHelper.clearPreference(PrefKey.PREF_LOGIN_NAME);

                break;
            case "google":
                logout(Constant.getmGoogleApiClient(),activity);
               // mGoogleApiClient.stopAutoManage();

             //   Constant.setmGoogleApiClient(null);
                break;
            case "facebook":
                LoginManager.getInstance().logOut();
                break;
            default:
                break;
        }
    }

    @Override
    public void gotoFragment(MainActivity activity,Fragment fragment) {
       activity.getSupportFragmentManager().beginTransaction()

                .replace(R.id.content_main, fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public GoogleApiClient createGoogleSignIn(GoogleApiClient mGoogleApiClient, Context context, FragmentActivity fragmentActivity, GoogleApiClient.OnConnectionFailedListener connectionFailedListener, GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(context)
                .enableAutoManage(fragmentActivity /* FragmentActivity */, connectionFailedListener /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(connectionCallbacks)
                .build();
        Constant.setmGoogleApiClient(mGoogleApiClient);
        //Constant.mGoogleApiClient=mGoogleApiClient;
        return mGoogleApiClient;
    }

    @Override
    public void signInGoogle(Activity activity, GoogleApiClient mGoogleApiClient, int RC_SIGN_IN) {
        // [START signIn]

        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        activity.startActivityForResult(signInIntent, RC_SIGN_IN);

        // [END signIn]
    }

    @Override
    public void logout(GoogleApiClient mGoogleApiClient, Activity activity) {
        handleOnStartLogout(activity);
        //mGoogleApiClient.connect();

        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        // [START_EXCLUDE]
                        Log.i("LogoutStatus",status.toString());
                        // [END_EXCLUDE]
                    }
                });


    }

    @Override
    public void handleOnStart(GoogleApiClient mGoogleApiClient, final Activity activity) {
        //mGoogleApiClient=Constant.getmGoogleApiClient();
        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
        if (opr.isDone()) {
            // If the user's cached credentials are valid, the OptionalPendingResult will be "done"
            // and the GoogleSignInResult will be available instantly.
            Log.d(TAG, "Got cached sign-in");
            GoogleSignInResult result = opr.get();
            handleSignInResult(result,activity);
        } else {
            // If the user has not previously signed in on this device or the sign-in has expired,
            // this asynchronous branch will attempt to sign in the user silently.  Cross-device
            // single sign-on will occur in this branch.

            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(GoogleSignInResult googleSignInResult) {

                    handleSignInResult(googleSignInResult,activity);
                }
            });
        }
    }

    @Override
    public void handleActivityResult(int requestCode, int RC_SIGN_IN, Intent data, Activity activity) {
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result,activity);
        }
    }

    @Override
    public void handleSignInResult(GoogleSignInResult result, Activity activity) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            String personName = acct.getDisplayName();
//            String personPhotoUrl = acct.getPhotoUrl().toString();
            String email = acct.getEmail();


            Log.e(TAG, "Name: " + personName + ", email: " + email
            );

            Log.i(TAG,"Result : "+acct.getFamilyName());

            Constant.setEmailGoogle(email);
            Constant.setNamaGoogle(personName);

            getMemberByEmail(activity);


            //   updateUI(true);
           // goToLogout(activity, personName,email,"google");
        } else {
            // Signed out, show unauthenticated UI.
            //  updateUI(false);
        }
    }

    @Override
    public void handleOnStartLogout(Context context) {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient1 = new GoogleApiClient.Builder(context)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
       mGoogleApiClient1.connect();
    }

    @Override
    public void getMemberByEmail(final Activity activity) {
       // progressDialog.show();
        new ServiceGenerator().getMemberByEmail(Constant.getEmailGoogle(), new Callback<List<MemberResponse>>() {
            @Override
            public void onResponse(Call<List<MemberResponse>> call, Response<List<MemberResponse>> response) {
              //  progressDialog.dismiss();
                if (response.code()==200){
                    if (response.body().size()!=0){

                        PrefHelper.setString(PrefKey.PREF_LOGIN_NAME, response.body().get(0).getNamaMember());
                        PrefHelper.setString(PrefKey.PREF_LOGIN_NAMA_PERUSAHAAN, response.body().get(0).getEmailMember());
                        PrefHelper.setString(PrefKey.PREF_LOGIN_VIA,"google");

                        Constant.setEmailGoogleFound(true);

                        ((MainActivity)activity).hideItem();

                        ((MainActivity)activity).changeName();
                        ((MainActivity)activity).gotoHome();
                    }
                    else {

                        Constant.setEmailGoogleFound(false);
                        logout(Constant.getmGoogleApiClient(),activity);
                        ((MainActivity)activity).gotoRegister();

                    }
                }
            }

            @Override
            public void onFailure(Call<List<MemberResponse>> call, Throwable t) {
             //   progressDialog.dismiss();


            }
        });
    }



    @Override
    public void loginFb(LoginButton loginButton, CallbackManager callbackManager, final Activity activity) {
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                Log.v("LoginActivity", response.toString());

                                // Application code
                                Bundle bFacebookData = getFacebookData(object);
                                System.out.println("Email Fb : "+bFacebookData.getString("email"));

                                Constant.setEmailFacebook(bFacebookData.getString("email"));
                                Constant.setNamaFacebook(bFacebookData.getString("name"));
                                getMemberByFb(activity);

                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,gender");
                request.setParameters(parameters);
                request.executeAsync();




                System.out.println("User ID: "
                        + loginResult.getAccessToken().getUserId()
                        + "\n" +
                        "Auth Token: "
                        + loginResult.getAccessToken().getToken()
                        + "\n" +
                        "Name"
                        +loginResult.getAccessToken().getToken());
            }

            @Override
            public void onCancel() {
                Log.i("CancelFb","Login Cancel");
            }

            @Override
            public void onError(FacebookException error) {
                Log.i("ErrorFb","Login Error" + error.getMessage());
            }
        });
    }

    @Override
    public Bundle getFacebookData(JSONObject object) {
        try {
            Bundle bundle = new Bundle();
            String id = object.getString("id");

            try {
                URL profile_pic = new URL("https://graph.facebook.com/" + id + "/picture?width=200&height=150");
                Log.i("profile_pic", profile_pic + "");
                bundle.putString("profile_pic", profile_pic.toString());

            } catch (MalformedURLException e) {
                e.printStackTrace();
                return null;
            }

            bundle.putString("idFacebook", id);
            if (object.has("first_name"))
                bundle.putString("first_name", object.getString("first_name"));
            if (object.has("last_name"))
                bundle.putString("last_name", object.getString("last_name"));
            if (object.has("email"))
                bundle.putString("email", object.getString("email"));
            if (object.has("gender"))
                bundle.putString("gender", object.getString("gender"));
            if (object.has("birthday"))
                bundle.putString("birthday", object.getString("birthday"));
            if (object.has("location"))
                bundle.putString("location", object.getJSONObject("location").getString("name"));

            return bundle;
        }
        catch(JSONException e) {
            Log.d(TAG,"Error parsing JSON");
        }

        return null;
    }

    @Override
    public void getMemberByFb(final Activity activity) {
        new ServiceGenerator().getMemberByEmail(Constant.getEmailFacebook(), new Callback<List<MemberResponse>>() {
            @Override
            public void onResponse(Call<List<MemberResponse>> call, Response<List<MemberResponse>> response) {
                //  progressDialog.dismiss();
                if (response.code()==200){
                    if (response.body().size()!=0){

                        PrefHelper.setString(PrefKey.PREF_LOGIN_NAME, response.body().get(0).getNamaMember());
                        PrefHelper.setString(PrefKey.PREF_LOGIN_NAMA_PERUSAHAAN, response.body().get(0).getEmailMember());
                        PrefHelper.setString(PrefKey.PREF_LOGIN_VIA,"facebook");

                        Constant.setEmailFacebookFound(true);

                        ((MainActivity)activity).hideItem();

                        ((MainActivity)activity).changeName();
                        ((MainActivity)activity).gotoHome();
                    }
                    else {

                        Constant.setEmailFacebookFound(false);
                        LoginManager.getInstance().logOut();
                        ((MainActivity)activity).gotoRegister();

                    }
                }
            }

            @Override
            public void onFailure(Call<List<MemberResponse>> call, Throwable t) {
                //   progressDialog.dismiss();


            }
        });
    }

    @Override
    public void buildAlertMessageNoGps(final MainActivity activity) {

            final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
            builder.setMessage("GPS harus diaktifkan untuk menggunakan aplikasi ini")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                            dialog.dismiss();
                            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                            activity.startActivityForResult(intent, REQUEST_CODE);
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


}
