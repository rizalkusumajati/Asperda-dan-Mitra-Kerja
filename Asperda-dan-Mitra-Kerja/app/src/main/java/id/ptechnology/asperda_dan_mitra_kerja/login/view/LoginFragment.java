package id.ptechnology.asperda_dan_mitra_kerja.login.view;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import id.ptechnology.asperda_dan_mitra_kerja.R;
import id.ptechnology.asperda_dan_mitra_kerja.login.presenter.LoginPresenter;
import id.ptechnology.asperda_dan_mitra_kerja.login.presenter.LoginPresenterImp;
import id.ptechnology.asperda_dan_mitra_kerja.login.presenter.LoginView;
import id.ptechnology.asperda_dan_mitra_kerja.main.view.MainActivity;
import id.ptechnology.asperda_dan_mitra_kerja.preferences.PrefHelper;
import id.ptechnology.asperda_dan_mitra_kerja.preferences.PrefKey;
import id.ptechnology.asperda_dan_mitra_kerja.registrasi.view.RegistrasiFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements LoginView{
    private Button registrasi;
    private RegistrasiFragment registrasiFragment;
    private LoginPresenter presenter;
    private TextInputEditText etEmail,etPassword;
    private Button login;
    private ProgressDialog progressDialog;


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

        progressDialog=new ProgressDialog(getContext());
        progressDialog.setMessage("Authenticating, please wait!");
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(false);

        //login handle

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(presenter.validate(etEmail.getText().toString(),etPassword.getText().toString())){

                    presenter.login(etEmail.getText().toString(),etPassword.getText().toString(), ((MainActivity)getActivity()),progressDialog);


                }
            }
        });

        //end login handle


        //redirect to register
        registrasiFragment=new RegistrasiFragment();
        registrasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.gotoRegistrasi(LoginFragment.this,registrasiFragment);
            }
        });
        //end redirect to register
        return view;
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
}
