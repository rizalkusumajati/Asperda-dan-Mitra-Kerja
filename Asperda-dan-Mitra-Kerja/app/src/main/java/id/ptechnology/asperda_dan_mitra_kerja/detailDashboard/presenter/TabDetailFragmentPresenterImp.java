package id.ptechnology.asperda_dan_mitra_kerja.detailDashboard.presenter;

import android.app.ProgressDialog;
import android.location.Location;
import android.util.Log;

import java.util.List;

import id.ptechnology.asperda_dan_mitra_kerja.api.CompanyResponse;
import id.ptechnology.asperda_dan_mitra_kerja.api.ServiceGenerator;
import id.ptechnology.asperda_dan_mitra_kerja.model.Constant;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by macmini2 on 11/14/16.
 */

public class TabDetailFragmentPresenterImp implements TabDetailFragmentPresenter{
    private TabDetailFragmentView tabDetailFragmentView;



    public TabDetailFragmentPresenterImp(TabDetailFragmentView tabDetailFragmentView){
        this.tabDetailFragmentView=tabDetailFragmentView;
    }


    @Override
    public void getCompanyByMember(final ProgressDialog progressDialog){
        progressDialog.show();
        Log.i("InCompany","Method CompanyByMember");
        new ServiceGenerator().getCompanyByMember(Constant.getIdCompany(), new Callback<List<CompanyResponse>>() {
            @Override
            public void onResponse(Call<List<CompanyResponse>> call, Response<List<CompanyResponse>> response) {
                progressDialog.dismiss();
                Log.i("InCompany",""+response.code());
                if (response.code()==200){
                    List<CompanyResponse> companyResponse=response.body();

                    Constant.setCompanyByMember(companyResponse);

                    for (CompanyResponse companyResponse1:Constant.getCompanyByMember()){
                        Location lokasiPerusahaan=new Location("provider");
                        lokasiPerusahaan.setLatitude(Double.parseDouble(companyResponse1.getLatitudeCompany()));
                        lokasiPerusahaan.setLongitude(Double.parseDouble(companyResponse1.getLongitudeCompany()));

                        companyResponse1.setJarak(Math.floor(Constant.getMyLokasi().distanceTo(lokasiPerusahaan)));
                    }

                    Log.i("CompanyMember",Constant.getCompanyByMember().get(0).getNamaCompany());
                    String nomorHp="";
                    if (Constant.getCompanyByMember().get(0).getTlp1Company()!=null){
                        nomorHp=Constant.getCompanyByMember().get(0).getTlp1Company();
                    }
                    else if (Constant.getCompanyByMember().get(0).getTlp2Company()!=null){
                        nomorHp=Constant.getCompanyByMember().get(0).getTlp2Company();
                    }
                    else {
                        nomorHp="unavailable";
                    }


                    tabDetailFragmentView.setData(response.body().get(0).getPicCompany(),response.body().get(0).getNamaCompany(),response.body().get(0).getAlamatCompany(),response.body().get(0).getKotacompany(),response.body().get(0).getEmailCompany(),response.body().get(0).getKetCompany(),response.body().get(0).getJarak(),nomorHp);
                }

            }

            @Override
            public void onFailure(Call<List<CompanyResponse>> call, Throwable t) {
                progressDialog.dismiss();
                Log.i("InCompany",""+t.getMessage());
            }
        });
    }
}
