package id.ptechnology.asperda_dan_mitra_kerja.registrasi.presenter;

/**
 * Created by macmini2 on 11/11/16.
 */

public interface RegisterView {
    void showValidationEmailError();
    void showEmailKosongValidation();
    void showPasswordKosongValidation();
    void showToast(String message);
}
