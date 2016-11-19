package id.ptechnology.asperda_dan_mitra_kerja.login.view;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.login.widget.LoginButton;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;

import java.util.Arrays;

import id.ptechnology.asperda_dan_mitra_kerja.R;
import id.ptechnology.asperda_dan_mitra_kerja.login.presenter.LoginPresenter;
import id.ptechnology.asperda_dan_mitra_kerja.login.presenter.LoginPresenterImp;
import id.ptechnology.asperda_dan_mitra_kerja.login.presenter.LoginView;
import id.ptechnology.asperda_dan_mitra_kerja.main.view.MainActivity;
import id.ptechnology.asperda_dan_mitra_kerja.model.Constant;
import id.ptechnology.asperda_dan_mitra_kerja.preferences.PrefHelper;
import id.ptechnology.asperda_dan_mitra_kerja.preferences.PrefKey;
import id.ptechnology.asperda_dan_mitra_kerja.registrasi.view.RegistrasiFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements LoginView, GoogleApiClient.OnConnectionFailedListener{
    private Button registrasi;
    private RegistrasiFragment registrasiFragment;
    private LoginPresenter presenter;
    private TextInputEditText etEmail,etPassword;
    private Button login;
    private ProgressDialog progressDialog;
    private SignInButton signInButton;
    private LoginButton loginButton;
    public GoogleApiClient googleApiClient;
    private static final int RC_SIGN_IN = 9001;
    private final String TAG="Google";


    public LoginFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_login, container, false);
        presenter=new LoginPresenterImp(this);
        registrasi=(Button)view.findViewById(R.id.register);
        etEmail=(TextInputEditText)view.findViewById(R.id.et_email);
        etPassword=(TextInputEditText)view.findViewById(R.id.et_password);
        login=(Button)view.findViewById(R.id.btn_login);
        signInButton=(SignInButton)view.findViewById(R.id.sign_in_button);
        loginButton=(LoginButton)view.findViewById(R.id.login_button);
        Constant.setOnDashboard(false);

        signInButton.setSize(SignInButton.SIZE_STANDARD);
      //  signInButton.setScopes(new Scope[]{Plus.SCOPE_PLUS_LOGIN});

        progressDialog=new ProgressDialog(getContext());
        progressDialog.setMessage("Authenticating, please wait!");
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(false);

        setGooglePlusButtonText(signInButton,getString(R.string.login_google));

        //login handle
        //API
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(presenter.validate(etEmail.getText().toString(),etPassword.getText().toString())){

                    presenter.login(etEmail.getText().toString(),etPassword.getText().toString(), ((MainActivity)getActivity()),progressDialog);


                }
            }
        });

        //google
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).signInGoogle();
            }
        });

        //facebook
        ((MainActivity)getActivity()).loginFacebook(loginButton);



        //end login handle


        //redirect to register
        registrasiFragment=new RegistrasiFragment();
        registrasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Constant.getLoginIntegrate()!=null){
                    Constant.setLoginIntegrate(null);
                }
                presenter.gotoRegistrasi(LoginFragment.this,registrasiFragment);
            }
        });
        //end redirect to register
        return view;
    }

    protected void setGooglePlusButtonText(SignInButton signInButton, String buttonText) {
        // Find the TextView that is inside of the SignInButton and set its text
        for (int i = 0; i < signInButton.getChildCount(); i++) {
            View v = signInButton.getChildAt(i);

            if (v instanceof TextView) {
                TextView tv = (TextView) v;
                tv.setText(buttonText);
                return;
            }
        }
    }


    @Override
    public void showValidationEmailError() {
        etEmail.setError(getString(R.string.error_invalid_email));
        etEmail.requestFocus();

    }

    @Override
    public void showUsernameKosongValidation() {
        etEmail.setError(getString(R.string.error_field_required));
        etEmail.requestFocus();
    }

    @Override
    public void showPasswordKosongValidation() {
        etPassword.setError(getString(R.string.error_field_required));
        etPassword.requestFocus();
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
    }



}
