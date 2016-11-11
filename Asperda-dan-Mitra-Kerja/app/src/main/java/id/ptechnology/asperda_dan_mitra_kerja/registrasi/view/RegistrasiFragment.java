package id.ptechnology.asperda_dan_mitra_kerja.registrasi.view;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import id.ptechnology.asperda_dan_mitra_kerja.R;
import id.ptechnology.asperda_dan_mitra_kerja.login.view.LoginFragment;
import id.ptechnology.asperda_dan_mitra_kerja.registrasi.presenter.RegisterPresenter;
import id.ptechnology.asperda_dan_mitra_kerja.registrasi.presenter.RegisterPresenterImp;
import id.ptechnology.asperda_dan_mitra_kerja.registrasi.presenter.RegisterView;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegistrasiFragment extends Fragment implements RegisterView {
    private TextInputEditText etNama,etEmail,etAlamat,etNomor,etNamaPerusahaan,etPassword,etInputUlang;
    private Button btnDaftar;
    private RegisterPresenter presenter;
    private ProgressDialog progressDialog;
    private LoginFragment loginFragment;

    public RegistrasiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_registrasi, container, false);

        etNama=(TextInputEditText)view.findViewById(R.id.et_nama);
        etEmail=(TextInputEditText)view.findViewById(R.id.et_email);
        etAlamat=(TextInputEditText)view.findViewById(R.id.et_alamat);
        etNomor=(TextInputEditText)view.findViewById(R.id.et_nomor);
        etNamaPerusahaan=(TextInputEditText)view.findViewById(R.id.et_namaPerusahaan);
        etPassword=(TextInputEditText)view.findViewById(R.id.et_password);
        etInputUlang=(TextInputEditText)view.findViewById(R.id.et_inputUlang);
        btnDaftar=(Button)view.findViewById(R.id.btn_daftar);
        progressDialog=new ProgressDialog(getContext());
        progressDialog.setMessage("Loading, please wait!");
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(false);
        presenter=new RegisterPresenterImp(this);
        loginFragment=new LoginFragment();

        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (presenter.validate(etEmail.getText().toString(),etPassword.getText().toString())){
                    presenter.register(etEmail.getText().toString(),etPassword.getText().toString(),etNama.getText().toString(),
                                       etAlamat.getText().toString(),null,null,null,etNomor.getText().toString(),null,
                                        etNamaPerusahaan.getText().toString(),null,progressDialog, RegistrasiFragment.this,loginFragment);
                }
            }
        });





        return view;
    }

    @Override
    public void showValidationEmailError() {
        etEmail.setError(getString(R.string.error_invalid_email));
        etEmail.requestFocus();
    }

    @Override
    public void showEmailKosongValidation() {
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

    }
}
