package com.lavazza;

import android.content.Context;
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

/**
 * Created by SensitiveTech on 06-May-17.
 */

public class PreviewDataAdapter  extends RecyclerView.Adapter<PreviewDataAdapter.ViewHolder>  implements AdapterView.OnItemSelectedListener {

    private ArrayList<PreviewData> android;
    private Context context;

//    String cus_id;
    public boolean status=false;

    public PreviewDataAdapter(Context context, ArrayList<PreviewData> android) {
        this.android = android;
        this.context = context;
//        this.cus_id = cus_id;
    }

    @Override
    public PreviewDataAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
//        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.display_reading, viewGroup, false);
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.machine_reading_new, viewGroup, false);
        return new PreviewDataAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final PreviewDataAdapter.ViewHolder viewHolder, int i) {
        final PreviewData data=android.get(i);

        viewHolder.serial_no.setText(data.getSerialNo());
        viewHolder.product.setText(data.getProduct());
        viewHolder.opening.setText(data.getOpening());
        viewHolder.closing.setText(data.getClosing());
        viewHolder.consumption.setText(data.getConsumption());

        if(data.getProduct().equalsIgnoreCase("tea"))
        {
            Picasso.with(context).load(R.drawable.tea).fit().error(R.drawable.dummy).into(viewHolder.image);
        }
        else if (data.getProduct().equalsIgnoreCase("coffee"))
        {
            Picasso.with(context).load(R.drawable.rsz_1coffee).fit().error(R.drawable.dummy).into(viewHolder.image);
        }
        else if(data.getProduct().equalsIgnoreCase("milk"))
        {
            Picasso.with(context).load(R.drawable.milk).fit().error(R.drawable.dummy).into(viewHolder.image);
        }


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

        TextView serial_no, product, opening, closing, consumption;
        ImageView image;

        public ViewHolder(View view) {
            super(view);


            serial_no= (TextView) view.findViewById(R.id.serial);
            product= (TextView) view.findViewById(R.id.pname);
            opening= (TextView) view.findViewById(R.id.opening);
            closing= (TextView) view.findViewById(R.id.closing);
            consumption= (TextView) view.findViewById(R.id.consumption);

            image= (ImageView) view.findViewById(R.id.imageView);





        }
    }

    public void animate(RecyclerView.ViewHolder viewHolder) {
        final Animation animAnticipateOvershoot = AnimationUtils.loadAnimation(context, R.anim.anticipate);
        viewHolder.itemView.setAnimation(animAnticipateOvershoot);
    }
}

