package id.ptechnology.asperda_dan_mitra_kerja.login.presenter;

/**
 * Created by macmini2 on 11/11/16.
 */

public interface LoginView {
    void showValidationEmailError();
    void showUsernameKosongValidation();
    void showPasswordKosongValidation();
    void showToast(String message);
}
