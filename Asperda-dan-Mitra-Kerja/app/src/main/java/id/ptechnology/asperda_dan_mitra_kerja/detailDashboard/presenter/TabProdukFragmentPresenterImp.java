package id.ptechnology.asperda_dan_mitra_kerja.detailDashboard.presenter;

import android.app.Activity;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import id.ptechnology.asperda_dan_mitra_kerja.adapter.ListProdukAdapter;
import id.ptechnology.asperda_dan_mitra_kerja.api.CompanyResponse;
import id.ptechnology.asperda_dan_mitra_kerja.api.ProdukResponse;
import id.ptechnology.asperda_dan_mitra_kerja.api.ServiceGenerator;
import id.ptechnology.asperda_dan_mitra_kerja.model.Constant;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by macmini2 on 11/15/16.
 */

public class TabProdukFragmentPresenterImp implements TabProdukFragmentPresenter {
    @Override
    public void getProdukByMember(final ArrayList<HashMap<String, String>> listDetail, final ListProdukAdapter adapter, final ListView listView, final Activity activity) {
        new ServiceGenerator().getProdukByMember(Constant.getIdCompany(), new Callback<List<ProdukResponse>>() {
            @Override
            public void onResponse(Call<List<ProdukResponse>> call, Response<List<ProdukResponse>> response) {
                Log.i("InCompany",""+response.code());
                if (response.code()==200){
                    List<ProdukResponse> produkResponses=response.body();

                    Constant.setProdukBymember(produkResponses);
                    Log.i("CompanyMemberParent",Constant.getProdukBymember().get(0).getNamaProduct());

                    setListView(listDetail,adapter,listView,activity);

                }
            }

            @Override
            public void onFailure(Call<List<ProdukResponse>> call, Throwable t) {

            }
        });
    }

    @Override
    public void setListView(ArrayList<HashMap<String, String>> listDetail, ListProdukAdapter adapter, ListView listView, Activity activity){

        listDetail = new ArrayList<HashMap<String, String>>();
        adapter = new ListProdukAdapter(activity, listDetail);
        adapter.clear();

        if (Constant.getProdukBymember()==null){
            getProdukByMember(listDetail,adapter,listView,activity);
        }

        else {
            for (ProdukResponse produkResponse:Constant.getProdukBymember()){

                HashMap<String, String> map = new HashMap<String, String>();
                map.put(Constant.KEY_NAMA_KETERANGAN, produkResponse.getNamaProduct());

                map.put(Constant.KEY_NAMA_MOBIL, produkResponse.getNamaProduct());
                map.put(Constant.KEY_LOKASI_MOBIL,produkResponse.getKetProduct());
                //map.put(Constant.KEY_SOPIR_MOBIL,produkResponse.getKetProduct());
               // map.put(Constant.KEY_BBM_MOBIL,produkResponse.getKetProduct());
               // map.put(Constant.KEY_HARGA_MOBIL,produkResponse.getHargaProduct());


                adapter.addItem(map);


            }

            listView.setAdapter(adapter);
        }




    }
}
