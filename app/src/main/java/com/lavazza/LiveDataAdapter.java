package com.lavazza;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.lavazza.helper.SQLiteHandler;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import android.widget.Filter;
import android.widget.Filterable;

/**
 * Created by 7 on 02/03/2017.
 */

public class LiveDataAdapter extends RecyclerView.Adapter<LiveDataAdapter.ViewHolder> implements Filterable{
    private ArrayList<LiveData> android;
    private ArrayList<LiveData> mFilteredList;
    private Context context;
    int mYear, mMonth, mDay, mHour, mMinute;

    //    Data data;
    String name,date,time,quantity,uom;
    int image;

    public LiveDataAdapter(Context context, ArrayList<LiveData> android) {
        this.android = android;
        this.context = context;
        mFilteredList = android;

    }

    @Override
    public LiveDataAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
//        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.livemachine, viewGroup, false);
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.livemachine1, viewGroup, false);
        return new LiveDataAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final LiveDataAdapter.ViewHolder viewHolder,  int i) {
        final LiveData data=android.get(i);
//        data=android.get(i);

//        viewHolder.tv_android.setText(android.get(i).getHotel_name());
//        Picasso.with(context).load(android.get(i).getHotel_image_url()).resize(240, 120).into(viewHolder.img_android);
        viewHolder.sno.setText(data.getSerial_no());
//        name=data.getSerialNo();



//        imageView.setColorFilter(ContextCompat.getColor(context,R.color.COLOR_YOUR_COLOR));
//        mImageView.setColorFilter(ContextCompat.getColor(getContext(), R.color.green_500));

//        ImageView imageViewIcon = (ImageView) listItem.findViewById(R.id.imageViewIcon);
//        imageViewIcon.setColorFilter(getContext().getResources().getColor(R.color.blue));


        ///////////input sales reading

//        viewHolder.input.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent =new Intent(context,SalesInput.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                Bundle bundle =new Bundle();
//                bundle.putString("sno",data.getSerial_no());
////                Toast.makeText(context, data.getSerial_no()+"", Toast.LENGTH_SHORT).show();
//                intent.putExtras(bundle);
//                context.startActivity(intent);
//
//            }
//        });

        //////////////////////////////


////////////////////////////////set the image
//        Picasso.with(context).load(data.getImageId()).resize(300, 260).into(viewHolder.img_android);
//////////////////////////////////



//        image=data.getImageId();
//        Picasso.with(context).load(data.getCart()).into(viewHolder.cart);

//        final int m=i;
//        viewHolder.cart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
////                Toast.makeText(context, viewHolder.getAdapterPosition()+","+m, Toast.LENGTH_SHORT).show();
////                if(name.isEmpty() && (date.matches("") || date.trim().equalsIgnoreCase("null") || date.isEmpty() || date.equalsIgnoreCase(null)) && time.isEmpty() && quantity.isEmpty() && uom.isEmpty())
////                {
////                    Toast.makeText(context, "Please Enter All The Details", Toast.LENGTH_SHORT).show();
////                }
////                else {
////                    Toast.makeText(context, name + "," + date + "," + time + "," + quantity + "," + uom + "\n" + image, Toast.LENGTH_SHORT).show();
////                }
//
////               if( viewHolder.getAdapterPosition() == m)
////               {
//
//                if (viewHolder.quantity.getSelectedItem().toString().equalsIgnoreCase("0")) {
//                    Toast.makeText(context, "Please Select The Quantity", Toast.LENGTH_SHORT).show();
//                } else if (viewHolder.uom.getSelectedItem().toString().equalsIgnoreCase("Select")) {
//                    Toast.makeText(context, "Please Select The UOM", Toast.LENGTH_SHORT).show();
//                } else {
//
//                    viewHolder.db.addUser(data.getSerialNo(), quantity, uom, data.getImageId());
////                    Toast.makeText(context, name + "," + quantity + "," + uom, Toast.LENGTH_SHORT).show();
//                    Toast.makeText(context, "Added to the Cart", Toast.LENGTH_SHORT).show();
////                    date="date";
////                    viewHolder.date.setText("Choose Date");
//
//
//                }
//            }
////            }
//        });


        //////image display
//        Picasso.with(context).load(data.getImageId()).fit().into(viewHolder.image);

        if(data.getStatus().equalsIgnoreCase("ideal")) {
//            Picasso.with(context).load(data.getImageId()).fit().into(viewHolder.image);
            viewHolder.image.setColorFilter(ContextCompat.getColor(context, R.color.yellow));
        }
        else if(data.getStatus().equalsIgnoreCase("work"))
        {
//            Picasso.with(context).load(data.getImageId()).fit().into(viewHolder.image);
            viewHolder.image.setColorFilter(ContextCompat.getColor(context, R.color.green));

        }
        else
        {
//            Picasso.with(context).load(data.getImageId()).fit().into(viewHolder.image);
            viewHolder.image.setColorFilter(ContextCompat.getColor(context, R.color.red));
        }
        /////


//        viewHolder.call.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(context, "Call Button is clicked", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        viewHolder.email.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(context, "Mail Button is clicked", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        viewHolder.map.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(context, "Map Button is clicked", Toast.LENGTH_SHORT).show();
//            }
//        });

//        animate(viewHolder);

    }

    public void setFilter(List<LiveData> countryModels) {
        android = new ArrayList<>();
        android.addAll(countryModels);
        notifyDataSetChanged();
    }

    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();

                if (charString.isEmpty()) {

                    mFilteredList = android;
                } else {

                    ArrayList<LiveData> filteredList = new ArrayList<>();

                    for (LiveData androidVersion : android) {

//                        if (androidVersion.getApi().toLowerCase().contains(charString) || androidVersion.getName().toLowerCase().contains(charString) || androidVersion.getVer().toLowerCase().contains(charString)) {
                        if (androidVersion.getSerial_no().contains(charString) || androidVersion.getStatus().toLowerCase().contains(charString) || androidVersion.getImageId().contains(charString) ) {

                            filteredList.add(androidVersion);
                        }
                    }

                    mFilteredList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mFilteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mFilteredList = (ArrayList<LiveData>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    @Override
    public int getItemCount() {
        return android.size();
    }


//    @Override
//    public void onClick(View view) {
//
//    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView sno;
        //        private TextView input;
        private pl.droidsonroids.gif.GifTextView input;
        private SQLiteHandler db;
        private ImageView image;

        public ViewHolder(View view) {
            super(view);

            db = new SQLiteHandler(context);
            sno= (TextView) view.findViewById(R.id.sno);
//            input= (pl.droidsonroids.gif.GifTextView) view.findViewById(R.id.input);
            image= (ImageView) view.findViewById(R.id.machine);

        }
    }

    public void animate(RecyclerView.ViewHolder viewHolder) {
        final Animation animAnticipateOvershoot = AnimationUtils.loadAnimation(context, R.anim.anticipate);
        viewHolder.itemView.setAnimation(animAnticipateOvershoot);
    }
}
