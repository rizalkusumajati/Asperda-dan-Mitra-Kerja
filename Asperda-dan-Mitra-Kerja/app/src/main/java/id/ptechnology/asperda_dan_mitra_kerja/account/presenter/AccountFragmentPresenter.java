package id.ptechnology.asperda_dan_mitra_kerja.account.presenter;

import android.app.Activity;
import android.widget.Spinner;

import java.util.ArrayList;

/**
 * Created by macmini2 on 11/21/16.
 */

public interface AccountFragmentPresenter {
    boolean validate(String namaPerusahaan,String alamatPerusahaan,String kodePosPerusahaan,String nomorTelepon1,String emailPerusahaan,String tahunberdiri_company,String contactPerusahaan,
                     String contactPhonePerusahaan);

    boolean isEmailValid(String email);

    void updateCompany(String id_user,String id_member,String nama_company,String ket_company,String bentuk_company,String alamat_company,
                          String kota_company,String kodepos_company,String tlp1_company,String tlp2_company,String email_company,String website_company,
                          String tahunberdiri_company,String npwp_company,String pkp_company,String referensi_company,String latitude_company,
                          String longitude_company,String listbidang_company,String pic_company);

    void getKota(ArrayList<String> kota, Spinner spinnerKota, Activity activity);

    void setKota(ArrayList<String> kota, Spinner spinnerKota, Activity activity);

    void getBentukCompany(ArrayList<String> bentukCompany,Spinner spinnerPerusahaan,Activity activity);

    void setBentukCompany(ArrayList<String> bentukCompany,Spinner spinnerPerusahaan,Activity activity);
}
