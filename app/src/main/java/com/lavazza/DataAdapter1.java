package com.lavazza;

/**
 * Created by 7 on 25/03/2017.
 */

import android.content.Context;
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



import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.lavazza.helper.SQLiteHandler;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Calendar;

public class DataAdapter1 extends RecyclerView.Adapter<DataAdapter1.ViewHolder>  implements AdapterView.OnItemSelectedListener{
    private ArrayList<Data1> android;
    private Context context;
    int mYear, mMonth, mDay, mHour, mMinute;

    //    Data data;
    String name,date,time,quantity,uom;
    int image;
    String cus_id;

    public DataAdapter1(Context context, ArrayList<Data1> android, String cus_id) {
        this.android = android;
        this.context = context;
        this.cus_id = cus_id;
    }

    @Override
    public DataAdapter1.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.dataadapterlayout1, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final DataAdapter1.ViewHolder viewHolder,  int i) {
        final Data1 data=android.get(i);
//        data=android.get(i);


//        viewHolder.tv_android.setText(android.get(i).getHotel_name());
//        Picasso.with(context).load(android.get(i).getHotel_image_url()).resize(240, 120).into(viewHolder.img_android);
        viewHolder.sno.setText(data.getSerialNo());
        name=data.getSerialNo();

        viewHolder.description.setText(data.getDescription());


//        date="date";
//        viewHolder.date.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//// Get Current Date
//                final Calendar c = Calendar.getInstance();
//                mYear = c.get(Calendar.YEAR);
//                mMonth = c.get(Calendar.MONTH);
//                mDay = c.get(Calendar.DAY_OF_MONTH);
//
//                DatePickerDialog datePickerDialog = new DatePickerDialog(view.getContext(),
//                        new DatePickerDialog.OnDateSetListener() {
//
//                            @Override
//                            public void onDateSet(DatePicker view, int year,
//                                                  int monthOfYear, int dayOfMonth) {
//
//                                viewHolder.date.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
//                                 date=dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
////                                Toast.makeText(context, date+"", Toast.LENGTH_SHORT).show();
//                            }
//                        }, mYear, mMonth, mDay);
//                datePickerDialog.show();
//
//            }
//        });




//        viewHolder.date.setText(data.getDate());

//        viewHolder.time.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // Get Current Time
//                final Calendar c = Calendar.getInstance();
//                mHour = c.get(Calendar.HOUR_OF_DAY);
//                mMinute = c.get(Calendar.MINUTE);
//
//                // Launch Time Picker Dialog
//                TimePickerDialog timePickerDialog = new TimePickerDialog(view.getContext(),
//                        new TimePickerDialog.OnTimeSetListener() {
//
//                            @Override
//                            public void onTimeSet(TimePicker view, int hourOfDay,
//                                                  int minute) {
//
//                                viewHolder.time.setText(hourOfDay + ":" + minute);
//
//                            }
//                        }, mHour, mMinute, false);
//                timePickerDialog.show();
//            }
//        });

//        String[] time1={"8AM-10AM","10AM-12PM","12PM-2PM","2PM-5PM"};
//        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(context, R.layout.simple_spinner_item, R.id.item, time1);
////        CustomAdapter adapter = new CustomAdapter(context,quan1);
//        viewHolder.time.setAdapter(adapter2);
//        viewHolder.time.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
////                String[] quan1={"100pack","200packs","300packs","400packs"};
////                CustomAdapter adapter = new CustomAdapter(view.getContext(),quan1);
////                viewHolder.quantity.setAdapter(adapter);
//                String time_item=viewHolder.time.getSelectedItem().toString();
//                time=viewHolder.time.getSelectedItem().toString();
////                Toast.makeText(context, time_item+"", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });




//        viewHolder.time.setText(data.getTime());
//        viewHolder.quality.setText(data.getQuality());
//        viewHolder.status.setText(data.getStatus());

//        viewHolder.quantity.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                ArrayList<String> quan=new ArrayList<String>();
//                quan.add("100pack");
//                quan.add("200pack");
//                quan.add("300pack");
//                quan.add("400pack");
//                quan.add("500pack");
//
////                String[] quan1={"100pack","200packs","300packs","400packs"};
////                ArrayAdapter<String> adapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_item ,quan1);
////                viewHolder.quantity.setAdapter(adapter);
//
//
////                CustomAdapter customAdapter=new CustomAdapter(context,quan1);
////                viewHolder.quantity.setAdapter(customAdapter);
//
//            }
//        });
//        ArrayList<String> quan=new ArrayList<String>();
//                quan.add("100pack");
//                quan.add("200pack");
//                quan.add("300pack");
//                quan.add("400pack");
//                quan.add("500pack");

//        final String[] quan1={"0","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20"};
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, R.layout.simple_spinner_item, R.id.item, quan1);
////        CustomAdapter adapter = new CustomAdapter(context,quan1);
//        viewHolder.quantity.setAdapter(adapter);
//        viewHolder.quantity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
////                String[] quan1={"100pack","200packs","300packs","400packs"};
////                CustomAdapter adapter = new CustomAdapter(view.getContext(),quan1);
////                viewHolder.quantity.setAdapter(adapter);
//                String quan_item=viewHolder.quantity.getSelectedItem().toString();
//                quantity=viewHolder.quantity.getSelectedItem().toString();
////                Toast.makeText(context, quan_item+"", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });




//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {



//                ArrayAdapter arrayAdapter=new ArrayAdapter(context, android.R.layout.simple_spinner_item ,quan);
//                // Drop down layout style - list view with radio button
////                arrayAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
//
//                // attaching data adapter to spinner
//                viewHolder.quantity.setAdapter(arrayAdapter);

//                CustomAdapter customAdapter=new CustomAdapter(context,quan1);
//                viewHolder.quantity.setAdapter(customAdapter);
//            }
//        });

//        String[] uom1={"Select","250gm","500gm","750gm","1000gm"};
//        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(context, R.layout.simple_spinner_item, R.id.item, uom1);
////        CustomAdapter adapter = new CustomAdapter(context,quan1);
//        viewHolder.uom.setAdapter(adapter1);
//        viewHolder.uom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//
//                String uom_item=viewHolder.uom.getSelectedItem().toString();
//                uom=viewHolder.uom.getSelectedItem().toString();
////                Toast.makeText(context, uom_item+"", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
//        viewHolder.uom.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                ArrayAdapter arrayAdapter=new ArrayAdapter(context, android.R.layout.simple_spinner_item ,quan);
////                 Drop down layout style - list view with radio button
////                arrayAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
////
////                 attaching data adapter to spinner
////                viewHolder.quantity.setAdapter(arrayAdapter);
//            }
//        });

        Picasso.with(context).load(data.getImageId()).error(R.drawable.dummy).resize(300, 260).into(viewHolder.img_android);
//        Toast.makeText(context, data.getImageId()+"", Toast.LENGTH_SHORT).show();

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
//                String result="";
//                if (viewHolder.quantity.getSelectedItem().toString().equalsIgnoreCase("0")) {
//                    Toast.makeText(context, "Please Select The Quantity", Toast.LENGTH_SHORT).show();
//                } else if (viewHolder.uom.getSelectedItem().toString().equalsIgnoreCase("Select")) {
//                    Toast.makeText(context, "Please Select The UOM", Toast.LENGTH_SHORT).show();
//                } else {
////                        String quan1=viewHolder.db.getUpdateQuantity(data.getSerialNo(), quantity, uom, cus_id);
//
//                    if(viewHolder.db.hasObject(data.getSerialNo(), quantity, uom, cus_id))
//                    {
//                        Toast.makeText(context, data.getSerialNo()+" already present in Cart", Toast.LENGTH_SHORT).show();
//                    }
//                    else if(viewHolder.db.hasObject1(data.getSerialNo(),uom, cus_id))
//                    {
//                        String quan1=viewHolder.db.getUpdateQuantity(data.getSerialNo(), quantity, uom, cus_id);
//                        try {
//                            int quan = Integer.parseInt(quan1) + Integer.parseInt(data.getQuality());
//                            result = "" + quan;
//                            viewHolder.db.updateQuantity(data.getSerialNo(), result, uom, cus_id);
//                        }
//                        catch(NumberFormatException ex){
//                            //either a or b is not a number
//                            Toast.makeText(context, "Please Update the "+data.getSerialNo()+" Quantity in Cart", Toast.LENGTH_SHORT).show();
//                        }
//
//                    }
//                    else {
//                        viewHolder.db.addUser(data.getSerialNo(), quantity, uom, data.getImageId(), cus_id);
////                    Toast.makeText(context, data.getImageId()+"", Toast.LENGTH_SHORT).show();
//                        Toast.makeText(context, "Added to the Cart", Toast.LENGTH_SHORT).show();
////                    date="date";
////                    viewHolder.date.setText("Choose Date");
//                    }
//
//
//                }
//            }
////            }
//        });


//        Picasso.with(context).load(data.getImageId()).fit().into(viewHolder.img_android);

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
//
//        animate(viewHolder);

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

//    @Override
//    public void onClick(View view) {
//
//    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView sno;
        private TextView description;

        //        private TextView mail;
        private ImageView img_android;


        private SQLiteHandler db;

        public ViewHolder(View view) {
            super(view);

            db = new SQLiteHandler(context);
            sno= (TextView) view.findViewById(R.id.sno);
            description = (TextView) view.findViewById(R.id.description);
            img_android = (ImageView) view.findViewById(R.id.imageView);

//            cart= (ImageView) view.findViewById(R.id.submit_product);
//            call = (Button) view.findViewById(R.id.call);
//            email = (Button) view.findViewById(R.id.mail);
//            map = (Button) view.findViewById(R.id.map);


        }
    }

    public void animate(RecyclerView.ViewHolder viewHolder) {
        final Animation animAnticipateOvershoot = AnimationUtils.loadAnimation(context, R.anim.anticipate);
        viewHolder.itemView.setAnimation(animAnticipateOvershoot);
    }
}
