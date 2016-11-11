package id.ptechnology.asperda_dan_mitra_kerja.login.presenter;

import android.app.ProgressDialog;

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

}
