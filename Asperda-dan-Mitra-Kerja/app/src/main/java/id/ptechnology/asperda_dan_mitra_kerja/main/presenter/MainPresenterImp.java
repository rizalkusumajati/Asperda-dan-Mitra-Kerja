package id.ptechnology.asperda_dan_mitra_kerja.main.presenter;

import java.util.List;

import id.ptechnology.asperda_dan_mitra_kerja.api.MemberResponse;
import id.ptechnology.asperda_dan_mitra_kerja.api.ServiceGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by macmini2 on 11/11/16.
 */

public class MainPresenterImp implements MainPresenter {

    @Override
    public void tryRetrofit() {
        new ServiceGenerator().getMember(new Callback<List<MemberResponse>>() {
            @Override
            public void onResponse(Call<List<MemberResponse>> call, Response<List<MemberResponse>> response) {
                System.out.println("Response code: "+ response.code());

               // System.out.println("Response body: "+response.body().get(1).getNamaMember());
            }

            @Override
            public void onFailure(Call<List<MemberResponse>> call, Throwable t) {
                System.out.println("Response failed: "+t.getMessage());
            }
        });
    }
}
