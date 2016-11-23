package id.ptechnology.asperda_dan_mitra_kerja.account.presenter;

import android.app.Activity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import id.ptechnology.asperda_dan_mitra_kerja.R;
import id.ptechnology.asperda_dan_mitra_kerja.api.AccountResponse;
import id.ptechnology.asperda_dan_mitra_kerja.api.BentukCompanyResponse;
import id.ptechnology.asperda_dan_mitra_kerja.api.KotaResponse;
import id.ptechnology.asperda_dan_mitra_kerja.api.ServiceGenerator;
import id.ptechnology.asperda_dan_mitra_kerja.model.Constant;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by macmini2 on 11/21/16.
 */

public class AccountFragmentPresenterImp implements AccountFragmentPresenter {

    private AccountFragmentView accountFragmentView;


    public AccountFragmentPresenterImp(AccountFragmentView accountFragmentView){
        this.accountFragmentView=accountFragmentView;
    }

    @Override
    public boolean validate(String namaPerusahaan, String alamatPerusahaan, String kodePosPerusahaan, String nomorTelepon1, String emailPerusahaan,String tahunberdiri_company, String contactPerusahaan, String contactPhonePerusahaan) {
        if(TextUtils.isEmpty(namaPerusahaan)){
            accountFragmentView.showNamaPerusahaanError();
            return false;
        }

        if(TextUtils.isEmpty(alamatPerusahaan)){
            accountFragmentView.showAlamatPerusahaanError();
            return false;
        }

        if(TextUtils.isEmpty(kodePosPerusahaan)){
            accountFragmentView.showKodePosPerusahaanError();
            return false;
        }

        if(TextUtils.isEmpty(nomorTelepon1)){
            accountFragmentView.showNomorTelephone1PerusahaanError();
            return false;
        }


        if (TextUtils.isEmpty(emailPerusahaan)) {
            accountFragmentView.showEmailPerusahaanError();
            return false;
        }
        else {
            if(!isEmailValid(emailPerusahaan)){
                accountFragmentView.showValidationEmailError();
                return false;
            }
        }

        if(TextUtils.isEmpty(tahunberdiri_company)){
            accountFragmentView.showTahunPerusahaanError();
            return false;
        }


        if(TextUtils.isEmpty(contactPerusahaan)){
            accountFragmentView.showContactPerusahaanError();
            return false;
        }

        if(TextUtils.isEmpty(contactPhonePerusahaan)){
            accountFragmentView.showContactPerusahaan2Error();
            return false;
        }



        return true;
    }

    @Override
    public boolean isEmailValid(String email) {
        return email.contains("@");
    }

    @Override
    public void updateCompany(final String id_user, final String id_member, final String nama_company, String ket_company, final String bentuk_company, String alamat_company, String kota_company, String kodepos_company, String tlp1_company, String tlp2_company, String email_company, String website_company, String tahunberdiri_company, String npwp_company, String pkp_company, final String referensi_company, String latitude_company, String longitude_company, String listbidang_company, String pic_company) {
        new ServiceGenerator().updateCompany(id_user, id_member, nama_company, ket_company,
                bentuk_company, alamat_company,  kota_company,  kodepos_company,
                tlp1_company, tlp2_company, email_company, website_company,
                tahunberdiri_company,  npwp_company, pkp_company,
                referensi_company, latitude_company, longitude_company,
                listbidang_company, pic_company, new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Log.i("Account","Response Code :"+response.code());
                        if (response.code()==200){
                            accountFragmentView.showToast("Update success");
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                        Log.i("Account",t.getMessage());
                        Log.e("Account",id_user+" "+id_member+" "+nama_company+" "+bentuk_company);
                            accountFragmentView.showToast("Kesalahan koneksi :"+t.getMessage());
                    }
                });


    }

    @Override
    public void getKota(final ArrayList<String> kota, final Spinner spinnerKota, final Activity activity) {
        new ServiceGenerator().getKota(new Callback<List<KotaResponse>>() {
            @Override
            public void onResponse(Call<List<KotaResponse>> call, Response<List<KotaResponse>> response) {
                Log.i("ResponseKota","response kota : "+response.code());
                if (response.code()==200){
                    Constant.setKotaResponses(response.body());
                    setKota(kota,spinnerKota,activity);
                }
            }

            @Override
            public void onFailure(Call<List<KotaResponse>> call, Throwable t) {
                Log.i("ResponseKota","response kota failed : "+t.getMessage());
            }
        });
    }

    @Override
    public void setKota(ArrayList<String> kota, Spinner spinnerKota, Activity activity) {
        for (KotaResponse kotaResponse:Constant.getKotaResponses()){
            kota.add(kotaResponse.getNamaKota());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(activity, R.layout.spinner_item, kota);
        spinnerKota.setAdapter(adapter);
    }

    @Override
    public void getBentukCompany(final ArrayList<String> bentukCompany, final Spinner spinnerPerusahaan, final Activity activity) {
        new ServiceGenerator().getBentukCompany(new Callback<List<BentukCompanyResponse>>() {
            @Override
            public void onResponse(Call<List<BentukCompanyResponse>> call, Response<List<BentukCompanyResponse>> response) {
                if (response.code()==200){
                    Constant.setBentukCompanyResponses(response.body());
                    setBentukCompany(bentukCompany,spinnerPerusahaan,activity);
                }
            }

            @Override
            public void onFailure(Call<List<BentukCompanyResponse>> call, Throwable t) {
                Log.i("ResponseBentuk","response bentuk failed : "+t.getMessage());
            }
        });
    }

    @Override
    public void setBentukCompany(ArrayList<String> bentukCompany,Spinner spinnerPerusahaan,Activity activity) {
        for (BentukCompanyResponse bentukCompanyResponse:Constant.getBentukCompanyResponses()){
            bentukCompany.add(bentukCompanyResponse.getBentukCompany());
        }

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(activity,R.layout.spinner_item, bentukCompany);
        spinnerPerusahaan.setAdapter(adapter1);
    }
}
