package id.ptechnology.asperda_dan_mitra_kerja.registrasi.presenter;

import android.app.ProgressDialog;

import id.ptechnology.asperda_dan_mitra_kerja.login.view.LoginFragment;
import id.ptechnology.asperda_dan_mitra_kerja.registrasi.view.RegistrasiFragment;

/**
 * Created by macmini2 on 11/11/16.
 */

public interface RegisterPresenter {

    boolean validate(String email, String password, String nama,String alamat,String nomor,String company);
    boolean isEmailValid(String email);

    void register(String email, String password, String nama_member, String alamat_member, String tmplahir_member,
                  String tgllahir_member, String tlp_member, String handphone_member, String kota_member, String nama_company,
                  String pic_member, ProgressDialog progressDialog, RegistrasiFragment registrasiFragment,LoginFragment loginFragment);

    void gotoLogin(RegistrasiFragment registrasiFragment, LoginFragment loginFragment);
}
