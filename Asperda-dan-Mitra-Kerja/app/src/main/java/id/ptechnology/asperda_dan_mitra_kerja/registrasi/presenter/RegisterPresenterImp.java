package id.ptechnology.asperda_dan_mitra_kerja.registrasi.presenter;

import android.app.ProgressDialog;
import android.text.TextUtils;

import id.ptechnology.asperda_dan_mitra_kerja.R;
import id.ptechnology.asperda_dan_mitra_kerja.api.RegisterResponse;
import id.ptechnology.asperda_dan_mitra_kerja.api.ServiceGenerator;
import id.ptechnology.asperda_dan_mitra_kerja.login.view.LoginFragment;
import id.ptechnology.asperda_dan_mitra_kerja.preferences.PrefHelper;
import id.ptechnology.asperda_dan_mitra_kerja.preferences.PrefKey;
import id.ptechnology.asperda_dan_mitra_kerja.registrasi.view.RegistrasiFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by macmini2 on 11/11/16.
 */

public class RegisterPresenterImp implements RegisterPresenter {
    private RegisterView registerView;

    public RegisterPresenterImp(RegisterView registerView){
        this.registerView=registerView;
    }

    @Override
    public boolean validate(String email, String password, String nama,String alamat,String nomor,String company) {
        if(TextUtils.isEmpty(nama)){
            registerView.showNamaRequired();
            return false;
        }


        if (TextUtils.isEmpty(email)) {
            registerView.showEmailKosongValidation();
            return false;
        }
        else {
            if(!isEmailValid(email)){
                registerView.showValidationEmailError();
                return false;
            }
        }

        if(TextUtils.isEmpty(alamat)){
            registerView.showAlamatRequired();
            return false;
        }

        if(TextUtils.isEmpty(nomor)){
            registerView.showNomorRequired();
            return false;
        }

        if(TextUtils.isEmpty(company)){
            registerView.showCompanyRequired();
            return false;
        }

        if(TextUtils.isEmpty(password)){
            registerView.showPasswordKosongValidation();
            return false;
        }
        return true;
    }

    @Override
    public boolean isEmailValid(String email) {
        return email.contains("@");
    }

    @Override
    public void register(String email, String password, String nama_member, String alamat_member, String tmplahir_member, String tgllahir_member, String tlp_member, String handphone_member, String kota_member, String nama_company, String pic_member, final ProgressDialog progressDialog, final RegistrasiFragment registrasiFragment, final LoginFragment loginFragment) {
        progressDialog.show();
        new ServiceGenerator().registerApi(email, password, nama_member, alamat_member, tmplahir_member, tgllahir_member, tlp_member, handphone_member, kota_member, nama_company, pic_member, new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                progressDialog.dismiss();
                System.out.println("Response code: "+ response.code());
                if (response.code()==200) {
                    System.out.println("Response body: " + response.body().getStat());

                    registerView.showToast("Register Success");
                    gotoLogin(registrasiFragment,loginFragment);


                }
                else {
                    registerView.showToast("Terjadi kesalahan.");

                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                progressDialog.dismiss();
                registerView.showToast("Kesalahan koneksi : Periksa koneksi internet anda");
            }
        });
    }

    @Override
    public void gotoLogin(RegistrasiFragment registrasiFragment, LoginFragment loginFragment) {
        registrasiFragment.getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_main, loginFragment)
                .addToBackStack(null)
                .commit();
    }
}
