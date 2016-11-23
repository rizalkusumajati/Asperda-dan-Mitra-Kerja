package id.ptechnology.asperda_dan_mitra_kerja.detailDashboard.presenter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

import id.ptechnology.asperda_dan_mitra_kerja.adapter.ListProdukAdapter;
import id.ptechnology.asperda_dan_mitra_kerja.adapter.RecyclerProduk;

/**
 * Created by macmini2 on 11/15/16.
 */

public interface TabProdukFragmentPresenter {

    void getProdukByMember(ArrayList<HashMap<String, String>> listDetail, RecyclerProduk adapter, RecyclerView listView, Activity activity);
    void setListView(ArrayList<HashMap<String, String>> listDetail, RecyclerProduk adapter, RecyclerView listView, Activity activity);

}
