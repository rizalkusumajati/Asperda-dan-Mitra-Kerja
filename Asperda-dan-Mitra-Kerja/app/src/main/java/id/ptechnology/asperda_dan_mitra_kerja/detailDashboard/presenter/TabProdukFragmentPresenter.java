package id.ptechnology.asperda_dan_mitra_kerja.detailDashboard.presenter;

import android.app.Activity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

import id.ptechnology.asperda_dan_mitra_kerja.adapter.ListProdukAdapter;

/**
 * Created by macmini2 on 11/15/16.
 */

public interface TabProdukFragmentPresenter {

    void getProdukByMember(ArrayList<HashMap<String, String>> listDetail, ListProdukAdapter adapter, ListView listView, Activity activity);
    void setListView(ArrayList<HashMap<String, String>> listDetail, ListProdukAdapter adapter, ListView listView, Activity activity);

}
