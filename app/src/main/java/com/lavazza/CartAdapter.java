package com.lavazza;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
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
import android.widget.Toast;

import com.lavazza.helper.SQLiteHandler;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;



public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder>  implements AdapterView.OnItemSelectedListener {

    private ArrayList<Cart_Data> android;
    private Context context;
    int mYear, mMonth, mDay, mHour, mMinute;

    String name,date,time,quantity,uom;
    int image,quantity1;
    String cus_id;
    public boolean status=false;

    public CartAdapter(Context context, ArrayList<Cart_Data> android, String cus_id) {
        this.android = android;
        this.context = context;
        this.cus_id = cus_id;
    }

    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cart1, viewGroup, false);
        return new CartAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CartAdapter.ViewHolder viewHolder, int i) {
        final Cart_Data data=android.get(i);



//        viewHolder.tv_android.setText(android.get(i).getHotel_name());
//        Picasso.with(context).load(android.get(i).getHotel_image_url()).resize(240, 120).into(viewHolder.img_android);
        viewHolder.name.setText(data.getName());
//        name=data.getSerialNo();
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
//                                date=dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
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

//////////////////////////////////////////////////
//        final String[] quan1={"Select","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20"};
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
////                quantity=viewHolder.quantity.getSelectedItem().toString();
////                Toast.makeText(context, quan_item+"", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });


///////////////////////////////////

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

        ////////////////////////////////////////////////////
        String[] uom1={"Select","250gm","500gm","750gm","1000gm"};
//        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(context, R.layout.simple_spinner_item, R.id.item, uom1);
////        CustomAdapter adapter = new CustomAdapter(context,quan1);
//        viewHolder.uom.setAdapter(adapter1);
//        viewHolder.uom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//
//                String uom_item=viewHolder.uom.getSelectedItem().toString();
////                uom=viewHolder.uom.getSelectedItem().toString();
////                Toast.makeText(context, uom_item+"", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });


        viewHolder.uom.setText(data.getUom());
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, R.layout.simple_spinner_item , uom1);
//        viewHolder.uom.setAdapter(adapter);
//        viewHolder.uom.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                viewHolder.uom.showDropDown();
//            }
//        });






////////////////////////////////////////////

        viewHolder.quantity.setText(data.getQuantity());
//        quantity1= Integer.parseInt(data.getQuantity());
        quantity1= Integer.parseInt(data.getQuantity());
//        quantity1 = Integer.parseInt(viewHolder.quantity.getText().toString());
        viewHolder.inc1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                view.startAnimation(AnimationUtils.loadAnimation(context, R.anim.image_click));
                status=true;
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
                viewHolder.db.updateUom(data.getName(), viewHolder.quantity.getText().toString(), data.getUom(), cus_id);

            }
        });
        viewHolder.dec1.setOnClickListener(new View.OnClickListener() {
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

                    status=true;
//                    viewHolder.dec1.setVisibility(View.VISIBLE);
                    viewHolder.quantity.setText(Integer.toString(quan));
//                    Toast.makeText(context, viewHolder.quantity.getText().toString()+"", Toast.LENGTH_SHORT).show();
                    viewHolder.db.updateUom(data.getName(), viewHolder.quantity.getText().toString(), data.getUom(), cus_id);

                }

            }
        });


//        if(status)
//        {
////            viewHolder.db.addUser(data.getSerialNo(), quantity, uom, data.getImageId(), cus_id);
//            Toast.makeText(context, data.getQuantity()+"", Toast.LENGTH_SHORT).show();
////            viewHolder.db.updateUom(data.getName(), data.getQuantity(),cus_id);
//        }


//        viewHolder.inc2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                int count=;
//                int co = viewHolder.counter1+250;
//                viewHolder.uom.setText(Integer.toString(viewHolder.counter1+250));
//
//            }
//        });
//        viewHolder.dec2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                int de= viewHolder.counter1-250;
//                if((viewHolder.counter1-250)<=0)
//                {
////                   viewHolder.dec2.setVisibility(View.GONE);
//                    Toast.makeText(context, "Please Increase the UOM", Toast.LENGTH_SHORT).show();
//                    viewHolder.counter1=0;
//                }
//                else {
////                    viewHolder.dec2.setVisibility(View.VISIBLE);
//                    viewHolder.uom.setText(Integer.toString(viewHolder.counter1-250));
//
//                }
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

        Picasso.with(context).load(data.getImage()).error(R.drawable.dummy).resize(300, 260).into(viewHolder.img_android);

//        image=data.getImageId();

        viewHolder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                view.startAnimation(AnimationUtils.loadAnimation(context, R.anim.image_click));
                final Dialog dialogBuilder = new Dialog(view.getContext());
                dialogBuilder.requestWindowFeature(Window.FEATURE_NO_TITLE);
//                LayoutInflater inflater1 =getLayoutInflater();
//                View dialogView1 = inflater1.inflate(R.layout.grid, null);
                final View dialogView1 = LayoutInflater.from(context).inflate(R.layout.deletepopup, null);
                dialogBuilder.setContentView(dialogView1);


                LinearLayout yes= (LinearLayout) dialogView1.findViewById(R.id.yes);
                LinearLayout no= (LinearLayout) dialogView1.findViewById(R.id.no);

                yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                String c_id=cus_id;
                Toast.makeText(context, "Product Removed", Toast.LENGTH_SHORT).show();
//                Integer taggedPosition = (Integer) view.getTag();

//                Toast.makeText(context, ""+viewHolder.getAdapterPosition()+1, Toast.LENGTH_SHORT).show();
                int row= viewHolder.getAdapterPosition();
                int ro1=row+1;
//                Toast.makeText(context, ro1+""+viewHolder.getAdapterPosition(), Toast.LENGTH_SHORT).show();

                viewHolder.db.delete(data.getName(),data.getQuantity(),data.getUom(),c_id);



                android.remove(viewHolder.getAdapterPosition());
                //                viewHolder.db.deletOneRecord(ro1);
//                viewHolder.db.delete(ro1);
                notifyItemRemoved(viewHolder.getAdapterPosition());
                notifyItemRangeChanged(viewHolder.getAdapterPosition(), android.size());

                        dialogBuilder.cancel();

                    }
                });

                no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        dialogBuilder.cancel();

                    }
                });


//                dialogBuilder.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation_2;
                dialogBuilder.show();
            }
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
        private TextView name;
        private TextView date;
        private Spinner time;
        //        private TextView quality;
//        private Spinner quantity;
        private TextView quantity;
//        private Spinner uom;
        private TextView uom;
        ImageView inc1,inc2,dec1,dec2;
        //        private TextView mail;
        private ImageView img_android;
        private ImageView remove;
        private Button call,email,map;

        int counter=0;
        int counter1=0;
        private SQLiteHandler db;

        public ViewHolder(View view) {
            super(view);

            db = new SQLiteHandler(context);

            name= (TextView) view.findViewById(R.id.name);
//            date = (TextView)view.findViewById(R.id.date);
//            time = (Spinner) view.findViewById(R.id.time);

            quantity = (TextView) view.findViewById(R.id.quantity);
            uom = (TextView) view.findViewById(R.id.uom);
            img_android = (ImageView) view.findViewById(R.id.imageView);

            inc1= (ImageView) view.findViewById(R.id.inc);
//            inc2= (ImageView) view.findViewById(R.id.inc1);
            dec1= (ImageView) view.findViewById(R.id.dec);
//            dec2= (ImageView) view.findViewById(R.id.dec1);

            remove= (ImageView) view.findViewById(R.id.remove);
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

