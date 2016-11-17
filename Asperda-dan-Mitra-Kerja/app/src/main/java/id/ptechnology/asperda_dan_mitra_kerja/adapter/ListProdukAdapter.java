package id.ptechnology.asperda_dan_mitra_kerja.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

import id.ptechnology.asperda_dan_mitra_kerja.R;
import id.ptechnology.asperda_dan_mitra_kerja.model.Constant;

/**
 * Created by ASUS on 09/11/2016.
 */

public class ListProdukAdapter extends BaseAdapter {

    private Activity activity;
    private ArrayList<HashMap<String, String>> data= new ArrayList<>();
    private static LayoutInflater inflater = null;



    public ListProdukAdapter(Activity a, ArrayList<HashMap<String, String>> d) {
        activity = a;
        data = d;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }
    public void addItem(HashMap<String, String> item){
        Log.i("AddAdapter","onAddItem");
        data.add(item);
        notifyDataSetChanged();
    }
    public void clear(){
        data.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {

        return position;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    public int getCount() {
        return data.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;


        if (convertView == null)
            vi = inflater.inflate(R.layout.list_detail_produk, null);

        TextView namaKeterangan=(TextView)vi.findViewById(R.id.tv_namaKeterangan);
        TextView namaMobil=(TextView)vi.findViewById(R.id.tv_namaMobil);
        TextView lokasiMobil=(TextView)vi.findViewById(R.id.tv_lokasiMobil);
        ImageView fotoMobil=(ImageView) vi.findViewById(R.id.iv_fotoProduk);
       // TextView sopir=(TextView)vi.findViewById(R.id.tv_sopir);
        //TextView bbm=(TextView)vi.findViewById(R.id.tv_bbm);
        TextView harga=(TextView)vi.findViewById(R.id.tv_harga);

        HashMap<String, String> song = new HashMap<String, String>();
        song = data.get(position);

        if (song.get(Constant.KEY_LOKASI_MOBIL).equals("Belum ada Produk yang dimasukkan")){
            fotoMobil.setVisibility(View.GONE);
            namaKeterangan.setVisibility(View.GONE);
            namaMobil.setVisibility(View.GONE);
            lokasiMobil.setText(song.get(Constant.KEY_LOKASI_MOBIL));
            //   sopir.setText(song.get(Constant.KEY_SOPIR_MOBIL));
            //  bbm.setText(song.get(Constant.KEY_BBM_MOBIL));
            harga.setVisibility(View.GONE);
        }
        else {

            namaKeterangan.setText(song.get(Constant.KEY_NAMA_KETERANGAN));
            namaMobil.setText(song.get(Constant.KEY_NAMA_MOBIL));
            lokasiMobil.setText(song.get(Constant.KEY_LOKASI_MOBIL));
            //   sopir.setText(song.get(Constant.KEY_SOPIR_MOBIL));
            //  bbm.setText(song.get(Constant.KEY_BBM_MOBIL));
            harga.setText("Rp." + song.get(Constant.KEY_HARGA_MOBIL));
            Picasso.with(activity).load(Constant.URL_PIC_COMPANY+song.get( Constant.KEY_PIC_COMPANY)).into(fotoMobil);
        }

        // Setting all values in list


        return vi;
    }
}

