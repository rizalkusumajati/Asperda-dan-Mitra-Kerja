package id.ptechnology.asperda_dan_mitra_kerja.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import id.ptechnology.asperda_dan_mitra_kerja.R;

/**
 * Created by macmini2 on 11/23/16.
 */

public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView namaMobil,hargaMobil;
    public ImageView countryPhoto;

    public RecyclerViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        namaMobil = (TextView)itemView.findViewById(R.id.tv_namaMobil);
        countryPhoto = (ImageView)itemView.findViewById(R.id.country_photo);
        hargaMobil=(TextView)itemView.findViewById(R.id.tv_harga);
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(view.getContext(), "Clicked Product Position = " + getPosition(), Toast.LENGTH_SHORT).show();
    }
}
