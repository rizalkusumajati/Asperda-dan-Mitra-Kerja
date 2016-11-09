package id.anterin.asperda_dan_mitra_kerja.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import id.anterin.asperda_dan_mitra_kerja.R;
import id.anterin.asperda_dan_mitra_kerja.model.Constant;

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


        // Setting all values in list


        return vi;
    }
}

