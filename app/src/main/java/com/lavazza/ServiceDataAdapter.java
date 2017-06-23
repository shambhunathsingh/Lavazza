package com.lavazza;

/**
 * Created by SensitiveTech on 09-May-17.
 */

import android.content.Context;
import android.media.Image;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.lavazza.helper.SQLiteHandler;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;



public class ServiceDataAdapter extends RecyclerView.Adapter<ServiceDataAdapter.ViewHolder>  implements AdapterView.OnItemSelectedListener {

    private ArrayList<Service> android;
    private Context context;

    //    String cus_id;
    public boolean status=false;

    public ServiceDataAdapter(Context context, ArrayList<Service> android) {
        this.android = android;
        this.context = context;
//        this.cus_id = cus_id;
    }

    @Override
    public ServiceDataAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.service_request_new, viewGroup, false);
        return new ServiceDataAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ServiceDataAdapter.ViewHolder viewHolder, int i) {
        final Service data=android.get(i);

        viewHolder.serial_no.setText(data.getSerial_no());
        viewHolder.e_code.setText(data.getError_code());
        viewHolder.e_message.setText(data.getError_message());
        viewHolder.location.setText(data.getLocation());
        viewHolder.remark.setText(data.getRemark());
        viewHolder.date.setText(data.getDate());
        viewHolder.time.setText(data.getTime());



    }

    @Override
    public int getItemCount() {
        return android.size();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView e_code, serial_no, location, e_message, remark,date,time;
        ImageView image;

        public ViewHolder(View view) {
            super(view);


            serial_no= (TextView) view.findViewById(R.id.serial);
            e_code= (TextView) view.findViewById(R.id.e_code);
            location= (TextView) view.findViewById(R.id.location);
            e_message= (TextView) view.findViewById(R.id.e_message);
            remark= (TextView) view.findViewById(R.id.remark);
            date= (TextView) view.findViewById(R.id.date);
            time= (TextView) view.findViewById(R.id.time);



        }
    }

    public void animate(RecyclerView.ViewHolder viewHolder) {
        final Animation animAnticipateOvershoot = AnimationUtils.loadAnimation(context, R.anim.anticipate);
        viewHolder.itemView.setAnimation(animAnticipateOvershoot);
    }
}

