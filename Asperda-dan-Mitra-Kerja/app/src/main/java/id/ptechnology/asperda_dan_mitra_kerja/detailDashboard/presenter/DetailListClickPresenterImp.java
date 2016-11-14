package id.ptechnology.asperda_dan_mitra_kerja.detailDashboard.presenter;

import android.util.Log;

import java.util.List;

import id.ptechnology.asperda_dan_mitra_kerja.api.CompanyResponse;
import id.ptechnology.asperda_dan_mitra_kerja.api.ServiceGenerator;
import id.ptechnology.asperda_dan_mitra_kerja.detailDashboard.view.TabDetailFragment;
import id.ptechnology.asperda_dan_mitra_kerja.model.Constant;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by macmini2 on 11/14/16.
 */

public class DetailListClickPresenterImp implements DetailListClickPresenter {



    @Override
    public void getCompanyByMember(){
        Log.i("InCompany","Method CompanyByMember");
        new ServiceGenerator().getCompanyByMember(Constant.getIdCompany(), new Callback <List<CompanyResponse>>() {
            @Override
            public void onResponse(Call<List<CompanyResponse>> call, Response<List<CompanyResponse>>response) {
                Log.i("InCompany",""+response.code());
                if (response.code()==200){
                    List<CompanyResponse> companyResponse=response.body();

                    Constant.setCompanyByMember(companyResponse);
                    Log.i("CompanyMemberParent",Constant.getCompanyByMember().get(0).getNamaCompany());

                }

            }

            @Override
            public void onFailure(Call<List<CompanyResponse>> call, Throwable t) {
                Log.i("InCompany",""+t.getMessage());
            }
        });
    }
}
