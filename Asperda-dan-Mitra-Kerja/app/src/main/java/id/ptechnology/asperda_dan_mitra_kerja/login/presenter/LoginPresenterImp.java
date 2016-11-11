package id.ptechnology.asperda_dan_mitra_kerja.login.presenter;

import android.app.ProgressDialog;
import android.text.TextUtils;

import java.util.List;

import id.ptechnology.asperda_dan_mitra_kerja.R;
import id.ptechnology.asperda_dan_mitra_kerja.api.MemberResponse;
import id.ptechnology.asperda_dan_mitra_kerja.login.view.LoginFragment;
import id.ptechnology.asperda_dan_mitra_kerja.main.view.MainActivity;
import id.ptechnology.asperda_dan_mitra_kerja.model.Constant;
import id.ptechnology.asperda_dan_mitra_kerja.preferences.PrefHelper;
import id.ptechnology.asperda_dan_mitra_kerja.preferences.PrefKey;
import id.ptechnology.asperda_dan_mitra_kerja.registrasi.view.RegistrasiFragment;
import id.ptechnology.asperda_dan_mitra_kerja.api.LoginResponse;
import id.ptechnology.asperda_dan_mitra_kerja.api.ServiceGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by macmini2 on 11/11/16.
 */

public class LoginPresenterImp implements LoginPresenter {

    private LoginView loginView;

    public LoginPresenterImp(LoginView loginView){
        this.loginView=loginView;
    }


    @Override
    public void gotoRegistrasi(LoginFragment loginFragment, RegistrasiFragment registrasiFragment) {
        loginFragment.getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_main, registrasiFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void login(String username, String password, final MainActivity activity , final ProgressDialog progressDialog) {
        progressDialog.show();
        new ServiceGenerator().loginApi(username, password, new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                progressDialog.dismiss();
                System.out.println("Response code: "+ response.code());
               if (response.code()==200) {
                   System.out.println("Response body: " + response.body().getStat());
                   PrefHelper.setBoolean(PrefKey.PREF_LOGIN,true);
                   PrefHelper.setString(PrefKey.PREF_LOGIN_ID,response.body().getIdMember());

                   getMemberDetail(response.body().getIdMember(),activity);
                   System.out.println("Login Fragment " +PrefHelper.getBoolean(PrefKey.PREF_LOGIN));
                   activity.hideItem();
                   activity.gotoHome();
               }
                else {
                   loginView.showToast("Username dan Password tidak ditemukan.");

               }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                progressDialog.dismiss();
                loginView.showToast("Kesalahan koneksi : Periksa koneksi internet anda");
            }
        });
    }

    @Override
    public void getMemberDetail(final String path, final MainActivity activity) {

        new ServiceGenerator().getMemberId(path, new Callback<List<MemberResponse>>() {
            @Override
            public void onResponse(Call<List<MemberResponse>> call, Response<List<MemberResponse>> response) {
                System.out.println("id member : "+path);
                System.out.println("Response code: "+ response.code());
                if (response.code()==200){
                    System.out.println("Response get Id member : "+response.body().get(0).getEmailMember());
                    PrefHelper.setString(PrefKey.PREF_LOGIN_NAME,response.body().get(0).getEmailMember());
                    System.out.println("Pref Email :"+ PrefHelper.getString(PrefKey.PREF_LOGIN_NAME));
                    activity.changeName();
                }

            }

            @Override
            public void onFailure(Call<List<MemberResponse>> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });

    }

    @Override
    public boolean isUsernameValid(String username) {

        return username.contains("@");
    }

    @Override
    public boolean validate(String username, String password) {
        if (TextUtils.isEmpty(username)) {
            loginView.showUsernameKosongValidation();
            return false;
        }
        else {
            if(!isUsernameValid(username)){
                loginView.showValidationEmailError();
                return false;
            }
        }

        if(TextUtils.isEmpty(password)){
            loginView.showPasswordKosongValidation();
            return false;
        }
        return true;
    }
}
