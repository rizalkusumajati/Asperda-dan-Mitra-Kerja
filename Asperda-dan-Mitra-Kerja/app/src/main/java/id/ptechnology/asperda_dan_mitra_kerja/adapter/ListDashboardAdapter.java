package id.ptechnology.asperda_dan_mitra_kerja.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;
import id.ptechnology.asperda_dan_mitra_kerja.R;
import id.ptechnology.asperda_dan_mitra_kerja.model.Constant;

/**
 * Created by macmini2 on 11/9/16.
 */

public class ListDashboardAdapter extends BaseAdapter {

    private Activity activity;
    private ArrayList<HashMap<String, String>> data= new ArrayList<>();
    private static LayoutInflater inflater = null;



    public ListDashboardAdapter(Activity a, ArrayList<HashMap<String, String>> d) {
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
            vi = inflater.inflate(R.layout.list_dashboard, null);

        TextView nama = (TextView) vi.findViewById(R.id.nama);
        TextView tanggal = (TextView) vi.findViewById(R.id.alamat);
        RatingBar ratingBar=(RatingBar)vi.findViewById(R.id.rating);
        //ImageView logoCompany=(ImageView)vi.findViewById(R.id.iv_logoCompany);
        CircleImageView circleImageView=(CircleImageView)vi.findViewById(R.id.profile_image);


        HashMap<String, String> song = new HashMap<String, String>();
        song = data.get(position);

        nama.setText(song.get(Constant.KEY_NAMA));
        tanggal.setText(song.get( Constant.KEY_ALAMAT));
        ratingBar.setRating(Float.parseFloat(song.get( Constant.KEY_RATING)));
        Picasso.with(activity).load(Constant.URL_PIC_COMPANY+song.get( Constant.KEY_PIC_COMPANY)).into(circleImageView);
        Log.e("picasso",Constant.URL_PIC_COMPANY+song.get( Constant.KEY_PIC_COMPANY));


        // Setting all values in list


        return vi;
    }
}
