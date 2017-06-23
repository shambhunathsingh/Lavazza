package com.lavazza;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Vibrator;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.lavazza.helper.SQLiteHandler;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Calendar;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder>  implements AdapterView.OnItemSelectedListener{
    private ArrayList<Data> android;
    private Context context;
    int mYear, mMonth, mDay, mHour, mMinute;

//    Data data;
    String name,date,time,quantity,uom;
           int image;
    String cus_id;
    int quantity1;

    public DataAdapter(Context context, ArrayList<Data> android, String cus_id) {
        this.android = android;
        this.context = context;
        this.cus_id = cus_id;
    }

    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.dataadapterlayout_new, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final DataAdapter.ViewHolder viewHolder,  int i) {
        final Data data=android.get(i);
//        data=android.get(i);


//        viewHolder.tv_android.setText(android.get(i).getHotel_name());
//        Picasso.with(context).load(android.get(i).getHotel_image_url()).resize(240, 120).into(viewHolder.img_android);
        viewHolder.sno.setText(data.getSerialNo());
        name=data.getSerialNo();


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


        ///////////spinner quantity method /////////

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
/////////////////////////////////////////////////////////

//        viewHolder.quantity.setText(data.getQuantity());
//        quantity1= Integer.parseInt(data.getQuantity());
        quantity1= Integer.parseInt(viewHolder.quantity.getText().toString());
//        quantity1 = Integer.parseInt(viewHolder.quantity.getText().toString());
        viewHolder.inc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(AnimationUtils.loadAnimation(context, R.anim.image_click));

//                status=true;
//             quantity1 = Integer.parseInt(viewHolder.quantity.getText().toString());
//                viewHolder.counter++;
                int quan2= Integer.parseInt(viewHolder.quantity.getText().toString());
                int quan=quan2+1;
//                quantity1++;
//                Toast.makeText(context, quan+"", Toast.LENGTH_SHORT).show();
//                viewHolder.quantity.setText(Integer.toString(viewHolder.counter));
                viewHolder.quantity.setText(Integer.toString(quan));
//                Toast.makeText(context, data.getName()+","+viewHolder.quantity.getText().toString()+"", Toast.LENGTH_SHORT).show();
//                viewHolder.db.updateUom(data.getName(),viewHolder.quantity.getText().toString() ,cus_id);
//                viewHolder.db.updateUom(data.getName(), viewHolder.quantity.getText().toString(), data.getUom(), cus_id);

            }
        });
        viewHolder.dec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(AnimationUtils.loadAnimation(context, R.anim.image_click));

//                int mi=viewHolder.counter--;
                int quan2= Integer.parseInt(viewHolder.quantity.getText().toString());
                int quan=quan2-1;

//                quantity1 = Integer.parseInt(viewHolder.quantity.getText().toString());
//                int mi=quantity1--;
                if(quan<=0)
                {
                    Toast.makeText(context, "Please Increase the Quantity", Toast.LENGTH_SHORT).show();
//                    viewHolder.counter=0;
                    quantity1=0;
                }
                else {

//                    status=true;
//                    viewHolder.dec1.setVisibility(View.VISIBLE);
                    viewHolder.quantity.setText(Integer.toString(quan));
//                    Toast.makeText(context, viewHolder.quantity.getText().toString()+"", Toast.LENGTH_SHORT).show();
//                    viewHolder.db.updateUom(data.getName(), viewHolder.quantity.getText().toString(), data.getUom(), cus_id);

                }

            }
        });










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
        String[] uom1={"1KG","750GM","500GM","250GM",};
//        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(context, R.layout.simple_spinner_item, R.id.item, uom1);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(context, R.layout.simple_dropdown, R.id.item, uom1);
//        CustomAdapter adapter = new CustomAdapter(context,quan1);
        viewHolder.uom.setAdapter(adapter1);
        viewHolder.uom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String uom_item=viewHolder.uom.getSelectedItem().toString();
                uom=viewHolder.uom.getSelectedItem().toString();
//                Toast.makeText(context, uom_item+"", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
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

        final int m=i;
        viewHolder.cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                view.startAnimation(AnimationUtils.loadAnimation(context, R.anim.image_click));

//                Toast.makeText(context, viewHolder.getAdapterPosition()+","+m, Toast.LENGTH_SHORT).show();
//                if(name.isEmpty() && (date.matches("") || date.trim().equalsIgnoreCase("null") || date.isEmpty() || date.equalsIgnoreCase(null)) && time.isEmpty() && quantity.isEmpty() && uom.isEmpty())
//                {
//                    Toast.makeText(context, "Please Enter All The Details", Toast.LENGTH_SHORT).show();
//                }
//                else {
//                    Toast.makeText(context, name + "," + date + "," + time + "," + quantity + "," + uom + "\n" + image, Toast.LENGTH_SHORT).show();
//                }

//               if( viewHolder.getAdapterPosition() == m)
//               {
                    String result="";
                    if (viewHolder.quantity.getText().toString().equalsIgnoreCase("0")) {
                        Toast.makeText(context, "Please Select The Quantity", Toast.LENGTH_SHORT).show();
                    } else if (viewHolder.uom.getSelectedItem().toString().equalsIgnoreCase("Select")) {
                        Toast.makeText(context, "Please Select The UOM", Toast.LENGTH_SHORT).show();
                    } else {
//                        String quan1=viewHolder.db.getUpdateQuantity(data.getSerialNo(), quantity, uom, cus_id);

//                        if(viewHolder.db.hasObject(data.getSerialNo(), quantity, uom, cus_id))
                            if(viewHolder.db.hasObject(data.getSerialNo(), viewHolder.quantity.getText().toString(), uom, cus_id))
                        {
                            Toast.makeText(context, data.getSerialNo()+" already present in Cart", Toast.LENGTH_SHORT).show();
                        }
                        else if(viewHolder.db.hasObject1(data.getSerialNo(),uom, cus_id))
                        {
//                            String quan1=viewHolder.db.getUpdateQuantity(data.getSerialNo(), quantity, uom, cus_id);
                            String quan1=viewHolder.db.getUpdateQuantity(data.getSerialNo(), viewHolder.quantity.getText().toString(), uom, cus_id);
                          try {
//                              int quan = Integer.parseInt(quan1) + Integer.parseInt(data.getQuality());
                              int quan = Integer.parseInt(quan1) + Integer.parseInt(viewHolder.quantity.getText().toString());
                              result = "" + quan;
                              viewHolder.db.updateQuantity(data.getSerialNo(), result, uom, cus_id);
                              Toast.makeText(context, "Updated the "+data.getSerialNo()+" Quantity", Toast.LENGTH_SHORT).show();
                          }
                          catch(NumberFormatException ex){
                              //either a or b is not a number
                              Toast.makeText(context, "Please Update the "+data.getSerialNo()+" Quantity in Cart", Toast.LENGTH_SHORT).show();
                          }

                        }
                        else {
//                                Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
//                                // Vibrate for 500 milliseconds
//                                v.vibrate(500);

//                            viewHolder.db.addUser(data.getSerialNo(), quantity, uom, data.getImageId(), cus_id);
                            viewHolder.db.addUser(data.getSerialNo(),  viewHolder.quantity.getText().toString(), uom, data.getImageId(), cus_id);
                                Toast.makeText(context, "Added to the Cart", Toast.LENGTH_SHORT).show();
//                            Toast toast=Toast.makeText(context, "Added to the Cart", Toast.LENGTH_SHORT);
//                                toast.setGravity(Gravity.CENTER,0,0);
//                                toast.show();

                                if(context instanceof Product){
//                                    ((Product)context).refreshActivity();
                                    ((Product)context).updateCount();
                                }





//                    date="date";
//                    viewHolder.date.setText("Choose Date");
                        }


                    }
                }
//            }
        });


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

//        animate(viewHolder);


        viewHolder.prod_no.setText(data.getProduct_no());

        viewHolder.desc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                view.startAnimation(AnimationUtils.loadAnimation(context, R.anim.image_click));
                final Dialog dialogBuilder = new Dialog(view.getContext());
                dialogBuilder.requestWindowFeature(Window.FEATURE_NO_TITLE);
//                LayoutInflater inflater1 =getLayoutInflater();
//                View dialogView1 = inflater1.inflate(R.layout.grid, null);
                final View dialogView1 = LayoutInflater.from(context).inflate(R.layout.show_description, null);
                dialogBuilder.setContentView(dialogView1);

                TextView description= (TextView) dialogView1.findViewById(R.id.desc);

                description.setText(data.getDescription());

                dialogBuilder.show();
            }
        });

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
        private TextView date;
        private TextView prod_no;
        private ImageView desc;
        private Spinner time;
        private TextView quantity;
//        private Spinner quantity;
        private Spinner uom;
        //        private TextView mail;
        private ImageView img_android;
        private ImageView cart;
        private Button call,email,map;
        private SQLiteHandler db;
        private ImageView inc,dec;

        public ViewHolder(View view) {
            super(view);

            db = new SQLiteHandler(context);
            sno= (TextView) view.findViewById(R.id.sno);
//            date = (TextView)view.findViewById(R.id.date);
//            time = (Spinner) view.findViewById(R.id.time);
            quantity = (TextView) view.findViewById(R.id.quantity);
            uom = (Spinner) view.findViewById(R.id.uom);
            img_android = (ImageView) view.findViewById(R.id.imageView);

            inc= (ImageView) view.findViewById(R.id.inc);
            dec= (ImageView) view.findViewById(R.id.dec);

            cart= (ImageView) view.findViewById(R.id.submit_product);
            desc= (ImageView) view.findViewById(R.id.info);
            prod_no= (TextView) view.findViewById(R.id.productno);


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
