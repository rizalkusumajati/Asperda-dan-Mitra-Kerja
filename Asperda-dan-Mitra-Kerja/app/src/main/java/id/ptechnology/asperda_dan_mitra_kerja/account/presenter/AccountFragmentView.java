package id.ptechnology.asperda_dan_mitra_kerja.account.presenter;

/**
 * Created by macmini2 on 11/21/16.
 */

public interface AccountFragmentView {
    void showNamaPerusahaanError();
    void showAlamatPerusahaanError();
    void showKodePosPerusahaanError();
    void showNomorTelephone1PerusahaanError();
    void showEmailPerusahaanError();
    void showTahunPerusahaanError();
    void showContactPerusahaanError();
    void showContactPerusahaan2Error();
    void showValidationEmailError();
    void showToast(String message);
}
