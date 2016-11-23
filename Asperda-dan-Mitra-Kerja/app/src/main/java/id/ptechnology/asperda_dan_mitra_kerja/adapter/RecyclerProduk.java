package id.ptechnology.asperda_dan_mitra_kerja.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import id.ptechnology.asperda_dan_mitra_kerja.R;
import id.ptechnology.asperda_dan_mitra_kerja.model.Constant;

/**
 * Created by macmini2 on 11/23/16.
 */

public class RecyclerProduk extends RecyclerView.Adapter<RecyclerViewHolder> {

    private ArrayList<HashMap<String, String>> itemList;
    private Context context;

    public RecyclerProduk(Context context, ArrayList<HashMap<String, String>> dataset) {
        this.itemList = dataset;
        this.context = context;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_detail_produk, null);
        RecyclerViewHolder rcv = new RecyclerViewHolder(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {

        if (itemList.size()>0) {
            holder.namaMobil.setText(itemList.get(position).get(Constant.KEY_NAMA_MOBIL));
            holder.hargaMobil.setText("Rp." + itemList.get(position).get(Constant.KEY_HARGA_MOBIL));
            Picasso.with(context).load(Constant.URL_PIC_COMPANY + itemList.get(position).get(Constant.KEY_PIC_COMPANY)).into(holder.countryPhoto);
        }


        //holder.countryPhoto.setImageResource(itemList.get(position));
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }
}
