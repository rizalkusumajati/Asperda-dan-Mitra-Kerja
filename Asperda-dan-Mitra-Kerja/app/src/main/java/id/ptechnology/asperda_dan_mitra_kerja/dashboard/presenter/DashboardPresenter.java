package id.ptechnology.asperda_dan_mitra_kerja.dashboard.presenter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

import id.ptechnology.asperda_dan_mitra_kerja.adapter.ListDashboardAdapter;
import id.ptechnology.asperda_dan_mitra_kerja.main.view.MainActivity;

/**
 * Created by macmini2 on 11/14/16.
 */

public interface DashboardPresenter {

    void getCompanyList(ArrayList<HashMap<String, String>> listDetail, ArrayList<HashMap<String, String>> listDetailMitra, ListDashboardAdapter adapter, ListDashboardAdapter adapterMitra, ListView listView, ListView listViewMitra, Activity activity,ProgressDialog progressDialog);
    void getMitraList(ArrayList<HashMap<String, String>> listDetail, ArrayList<HashMap<String, String>> listDetailMitra, ListDashboardAdapter adapter, ListDashboardAdapter adapterMitra, ListView listView, ListView listViewMitra, Activity activity,ProgressDialog progressDialog);


    void setListView(ArrayList<HashMap<String, String>> listDetail, ArrayList<HashMap<String, String>> listDetailMitra, ListDashboardAdapter adapter, ListDashboardAdapter adapterMitra, ListView listView, ListView listViewMitra, Activity activity,ProgressDialog progressDialog);
    String getIdCompany(int i);
    String getIdMitra(int i);


}
