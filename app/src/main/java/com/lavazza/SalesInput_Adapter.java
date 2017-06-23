package com.lavazza;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by SensitiveTech on 06-May-17.
 */

public class SalesInput_Adapter extends RecyclerView.Adapter<SalesInput_Adapter.ViewHolder>  implements AdapterView.OnItemSelectedListener {

    private ArrayList<PreviewData> android;
    private Context context;

    //    String cus_id;
    public boolean status=false;

    public SalesInput_Adapter(Context context, ArrayList<PreviewData> android) {
        this.android = android;
        this.context = context;
//        this.cus_id = cus_id;
    }

    @Override
    public SalesInput_Adapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.machine_reading_new, viewGroup, false);
        return new SalesInput_Adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SalesInput_Adapter.ViewHolder viewHolder, int i) {
        final PreviewData data=android.get(i);

        viewHolder.serial_no.setText(data.getSerialNo());
        viewHolder.product.setText(data.getProduct());
        viewHolder.opening.setText(data.getOpening());
        viewHolder.closing.setText(data.getClosing());
        viewHolder.consumption.setText(data.getConsumption());

//        if(data.getProduct().equalsIgnoreCase("tea"))
        if(data.getProduct().equalsIgnoreCase("Tea Water"))
        {
            Picasso.with(context).load(R.drawable.tea).fit().error(R.drawable.dummy).into(viewHolder.image);
        }
        else if (data.getProduct().equalsIgnoreCase("Hot Soup"))
        {
            Picasso.with(context).load(R.drawable.rsz_1coffee).fit().error(R.drawable.dummy).into(viewHolder.image);
        }
        else if(data.getProduct().equalsIgnoreCase("RISTRETTO"))
        {
            Picasso.with(context).load(R.drawable.cup1).fit().error(R.drawable.dummy).into(viewHolder.image);
        }
        else if(data.getProduct().equalsIgnoreCase("ESPRESSO"))
        {
            Picasso.with(context).load(R.drawable.cup1).fit().error(R.drawable.dummy).into(viewHolder.image);
        }
        else if(data.getProduct().equalsIgnoreCase("HOT CHOCOLATE"))
        {
            Picasso.with(context).load(R.drawable.cup1).fit().error(R.drawable.dummy).into(viewHolder.image);
        }
        else if(data.getProduct().equalsIgnoreCase("CAPPUCCINO"))
        {
            Picasso.with(context).load(R.drawable.cup1).fit().error(R.drawable.dummy).into(viewHolder.image);
        }
        else if(data.getProduct().equalsIgnoreCase("CAFELATTE"))
        {
            Picasso.with(context).load(R.drawable.cup1).fit().error(R.drawable.dummy).into(viewHolder.image);
        }
        else if(data.getProduct().equalsIgnoreCase("HOT MILK"))
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

