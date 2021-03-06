package id.ptechnology.asperda_dan_mitra_kerja.dashboard.presenter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.location.Location;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import id.ptechnology.asperda_dan_mitra_kerja.Utility;
import id.ptechnology.asperda_dan_mitra_kerja.adapter.ListDashboardAdapter;
import id.ptechnology.asperda_dan_mitra_kerja.api.CompanyResponse;
import id.ptechnology.asperda_dan_mitra_kerja.api.ServiceGenerator;
import id.ptechnology.asperda_dan_mitra_kerja.main.view.MainActivity;
import id.ptechnology.asperda_dan_mitra_kerja.model.Constant;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.R.id.list;

/**
 * Created by macmini2 on 11/14/16.
 */

public class DashboardPresenterImp implements DashboardPresenter {

    DashboardView dashboardView;


    public DashboardPresenterImp(DashboardView dashboardView){
        this.dashboardView=dashboardView;
    }




    @Override
    public void getCompanyList(final ArrayList<HashMap<String, String>> listDetail, final ArrayList<HashMap<String, String>> listDetailMitra, final ListDashboardAdapter adapter, final ListDashboardAdapter adapterMitra, final ListView listView, final ListView listViewMitra, final Activity activity, final ProgressDialog progressDialog){
       // progressDialog.show();
        new ServiceGenerator().getCompany(new Callback<List<CompanyResponse>>() {
            @Override
            public void onResponse(Call<List<CompanyResponse>> call, Response<List<CompanyResponse>> response) {
              // progressDialog.dismiss();
                System.out.println("Response code: "+ response.code());
                if (response.code()==200) {

                    List<CompanyResponse> companyResponses = response.body();

//                    for (CompanyResponse companyResponse:response.body()){
//                        CompanyResponse temporary=new CompanyResponse();
//                        temporary.setIdMember(companyResponse.getIdMember());
//                        temporary.setNamaCompany(companyResponse.getNamaCompany());
//                        temporary.setKetCompany(companyResponse.getKetCompany());
//                        temporary.setBentukCompany(companyResponse.getBentukCompany());
//                        temporary.setAlamatCompany(companyResponse.getAlamatCompany());
//                        temporary.setKotacompany(companyResponse.getKotacompany());
//                        temporary.setKodeposCompany(companyResponse.getKodeposCompany());
//
//                    }

                    Constant.setCompanyData(companyResponses);

                    for (CompanyResponse companyResponse:Constant.getCompanyData()){
                        Location lokasiPerusahaan=new Location("provider");
                        lokasiPerusahaan.setLatitude(Double.parseDouble(companyResponse.getLatitudeCompany()));
                        lokasiPerusahaan.setLongitude(Double.parseDouble(companyResponse.getLongitudeCompany()));

                        companyResponse.setJarak(Constant.getMyLokasi().distanceTo(lokasiPerusahaan));
                    }

                    setListView(listDetail,listDetailMitra,adapter,adapterMitra,listView,listViewMitra,activity,progressDialog);
                }
                else {
                    progressDialog.dismiss();
                    dashboardView.showToast(response.message());
                }
            }

            @Override
            public void onFailure(Call<List<CompanyResponse>> call, Throwable t) {
                progressDialog.dismiss();
                dashboardView.showToast("Periksa Koneksi Internet Anda");
            }
        });



    }

    @Override
    public void getMitraList(final ArrayList<HashMap<String, String>> listDetail, final ArrayList<HashMap<String, String>> listDetailMitra, final ListDashboardAdapter adapter, final ListDashboardAdapter adapterMitra, final ListView listView, final ListView listViewMitra, final Activity activity, final ProgressDialog progressDialog) {
        progressDialog.show();
        new ServiceGenerator().getMitra(new Callback<List<CompanyResponse>>() {

            @Override
            public void onResponse(Call<List<CompanyResponse>> call, Response<List<CompanyResponse>> response) {
                progressDialog.dismiss();
                System.out.println("Response code: "+ response.code());
                if (response.code()==200) {
                    List<CompanyResponse> mitraResponse = response.body();
                    Constant.setMitraData(mitraResponse);

                    for (CompanyResponse companyResponse:Constant.getMitraData()){
                        Location lokasiPerusahaan=new Location("provider");
                        lokasiPerusahaan.setLatitude(Double.parseDouble(companyResponse.getLatitudeCompany()));
                        lokasiPerusahaan.setLongitude(Double.parseDouble(companyResponse.getLongitudeCompany()));

                        companyResponse.setJarak(Constant.getMyLokasi().distanceTo(lokasiPerusahaan));
                    }

                    setListView(listDetail,listDetailMitra,adapter,adapterMitra,listView,listViewMitra,activity,progressDialog);
                }
                else {
                    dashboardView.showToast(response.message());
                }
            }

            @Override
            public void onFailure(Call<List<CompanyResponse>> call, Throwable t) {
                progressDialog.dismiss();

            }
        });
    }


    @Override
    public void setListView(ArrayList<HashMap<String, String>> listDetail, ArrayList<HashMap<String, String>> listDetailMitra, ListDashboardAdapter adapter, ListDashboardAdapter adapterMitra, ListView listView, ListView listViewMitra, Activity activity,ProgressDialog progressDialog){

        listDetail = new ArrayList<HashMap<String, String>>();
        listDetailMitra = new ArrayList<HashMap<String, String>>();
        adapter = new ListDashboardAdapter(activity, listDetail);
        adapterMitra=new ListDashboardAdapter(activity,listDetailMitra);
        adapterMitra.clear();
        adapter.clear();
        if (Constant.getCompanyData()==null){
            getCompanyList(listDetail,listDetailMitra,adapter,adapterMitra,listView,listViewMitra,activity,progressDialog);
           //setListView(listDetail,listDetailMitra,adapter,adapterMitra,listView,listViewMitra,activity,progressDialog);
        }
        else {
            for (CompanyResponse companyResponse:Constant.getCompanyData()){
                Location lokasiPerusahaan=new Location("provider");
                lokasiPerusahaan.setLatitude(Double.parseDouble(companyResponse.getLatitudeCompany()));
                lokasiPerusahaan.setLongitude(Double.parseDouble(companyResponse.getLongitudeCompany()));

                companyResponse.setJarak(Constant.getMyLokasi().distanceTo(lokasiPerusahaan));
            }
            List<CompanyResponse>companyResponses=Constant.getCompanyData();
            Collections.sort(companyResponses, new Comparator<CompanyResponse>() {
                @Override
                public int compare(CompanyResponse companyResponse, CompanyResponse t1) {

                    return (int)companyResponse.getJarak()-(int)t1.getJarak();
                }
            });
//            Collections.sort(companyResponses, Collections.reverseOrder());
        //    Comparator<Double> compareDouble = (companyResponses.get(0).getJarak());
            //Collections.sort(companyResponses);
          //  Collections.sort(List<CompanyResponse> list, Comparator<CompanyResponse> companyResponses.)



            for (CompanyResponse companyResponse : Constant.getCompanyData()) {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put(Constant.KEY_NAMA, companyResponse.getNamaCompany());
                map.put(Constant.KEY_ALAMAT, companyResponse.getAlamatCompany());
                map.put(Constant.KEY_RATING, "2");
                map.put(Constant.KEY_PIC_COMPANY, companyResponse.getPicCompany());
                String jarak="";
                if (companyResponse.getJarak()>=1000){
                    jarak=(Math.floor(companyResponse.getJarak()/1000))+" km";
                }
                else {
                    jarak=(Math.floor(companyResponse.getJarak()))+" m";
                }

                map.put(Constant.KEY_JARAK,jarak);


                Log.i("Company", companyResponse.getNamaCompany());
                adapter.addItem(map);
            }

            listView.setAdapter(adapter);
            Utility.setListViewHeightBasedOnChildren(listView);
        }


        if (Constant.getMitraData()==null){
            getMitraList(listDetail,listDetailMitra,adapter,adapterMitra,listView,listViewMitra,activity,progressDialog);
            //setListView(listDetail,listDetailMitra,adapter,adapterMitra,listView,listViewMitra,activity,progressDialog);
        }
        else {
            for (CompanyResponse companyResponse:Constant.getMitraData()){
                Location lokasiPerusahaan=new Location("provider");
                lokasiPerusahaan.setLatitude(Double.parseDouble(companyResponse.getLatitudeCompany()));
                lokasiPerusahaan.setLongitude(Double.parseDouble(companyResponse.getLongitudeCompany()));

                companyResponse.setJarak(Constant.getMyLokasi().distanceTo(lokasiPerusahaan));
            }
            for (CompanyResponse companyResponse : Constant.getMitraData()) {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put(Constant.KEY_NAMA, companyResponse.getNamaCompany());
                map.put(Constant.KEY_ALAMAT, companyResponse.getAlamatCompany());
                map.put(Constant.KEY_RATING, "2");
                map.put(Constant.KEY_PIC_COMPANY, companyResponse.getPicCompany());


                String jarak="";
                if (companyResponse.getJarak()>=1000){
                    jarak=Double.toString(Math.floor(companyResponse.getJarak()/1000))+" km";
                }
                else {
                    jarak=Double.toString(Math.floor(companyResponse.getJarak()))+" m";
                }

                map.put(Constant.KEY_JARAK,jarak);

                Log.i("Company", companyResponse.getNamaCompany());
                adapterMitra.addItem(map);
            }

            listViewMitra.setAdapter(adapterMitra);
            Utility.setListViewHeightBasedOnChildren(listViewMitra);
        }


        progressDialog.dismiss();
    }

    @Override
    public String getIdCompany(int i) {
       return Constant.getCompanyData().get(i).getIdMember();

    }

    @Override
    public String getIdMitra(int i) {
        return Constant.getMitraData().get(i).getIdMember();
    }


}
