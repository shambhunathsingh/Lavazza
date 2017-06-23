package com.lavazza;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.lavazza.app.AppConfig;
import com.lavazza.app.AppController;
import com.lavazza.helper.SQLiteHandler;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by 7 on 04/03/2017.
 */

public class SalesDataAdapter extends RecyclerView.Adapter<SalesDataAdapter.ViewHolder>  {

    private ArrayList<SalesData> android;
    private Context context;
    private int consumption;
    String serial_no,cus_id;

    public SalesDataAdapter(Context context, ArrayList<SalesData> android, String serial_no, String cus_id) {
        this.android = android;
        this.context = context;
        this.serial_no = serial_no;
        this.cus_id = cus_id;
    }

    @Override
    public SalesDataAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.livesale_product, viewGroup, false);
        return new SalesDataAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SalesDataAdapter.ViewHolder viewHolder,  int i) {
        final SalesData data=android.get(i);
//        data=android.get(i);


        viewHolder.product.setText(data.product);
        viewHolder.open.setText(data.open_read);
        viewHolder.close.setText(data.close_read);

        viewHolder.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                consumption= (Integer.parseInt(viewHolder.close.getText().toString())-Integer.parseInt(viewHolder.open.getText().toString()));
                String consum= String.valueOf(consumption);

                viewHolder.consume.setText(consum);

                registerUser(serial_no,cus_id, viewHolder.product.getText().toString(), viewHolder.open.getText().toString(), viewHolder.close.getText().toString(), consum);


            }
        });

//        viewHolder.tv_android.setText(android.get(i).getHotel_name());
//        Picasso.with(context).load(android.get(i).getHotel_image_url()).resize(240, 120).into(viewHolder.img_android);
//        viewHolder.sno.setText(data.getSerialNo());
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

//        Picasso.with(context).load(data.getImageId()).resize(300, 260).into(viewHolder.img_android);

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

    private void registerUser(final String serial_no, final String cus_id, final String p_name, final String opening, final String closing, final String consumption) {
        // Tag used to cancel the request
//        Toast.makeText(getApplicationContext(), "Marked Present", Toast.LENGTH_SHORT).show();
        String tag_string_req = "req_register";
//        Toast.makeText(FullScannerActivity.this, "Register", Toast.LENGTH_SHORT).show();
//        pDialog.setMessage("Adding ...");
//        showDialog();
//        progressBar.setVisibility(View.);


        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_ADD_READING, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
//                Log.d(TAG, "Register Response: " + response.toString());
//                hideDialog();
//                Toast.makeText(Register.this, "Register successfully", Toast.LENGTH_SHORT).show();

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");
                    if (!error) {
                        // User successfully stored in MySQL
                        // Now store the user in sqlite
//                        String uid = jObj.getString("uid");

//                        JSONObject user = jObj.getJSONObject("user");
//                        String name2 = user.getString("name");
//                        String phone2 = user.getString("phone");
//                        String email2 = user.getString("email");
//                        String vehicleno2 = user.getString("vehicleno");
//                        String model2 = user.getString("model");
//                        String comment2 = user.getString("comment");
//                        String rate2 = user.getString("rate");
//                        String ratecomment2 = user.getString("ratecomment");
//
//                        // Inserting row in users table
//                        db.addUser(name2, phone2, email2, vehicleno2, model2, comment2, rate2, ratecomment2);
//                        db.addUser(name, organisation, email, phone, address);

                        Toast.makeText(context, "Added Successfully", Toast.LENGTH_SHORT).show();

                        // Launch login activity
//                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
//                        startActivity(intent);
//                        finish();

                    } else {

                        // Error occurred in registration. Get the error
                        // message
                        String errorMsg = jObj.getString("error_msg");
                        Toast.makeText(context,
                                errorMsg, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
//                Log.e(TAG, "Registration Error: " + error.getMessage());
                Toast.makeText(context,
                        error.getMessage(), Toast.LENGTH_SHORT).show();
//                hideDialog();
            }
        }) {

            @Override
            protected java.util.Map<String, String> getParams() {
                // Posting params to register url
                java.util.Map<String, String> params = new HashMap<String, String>();
                params.put("serial_no", serial_no);
                params.put("cus_id", cus_id);
                params.put("p_name", p_name);
                params.put("opening", opening);
                params.put("closing", closing);
                params.put("consumption", consumption);


                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
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
        private EditText product;
        private EditText open,close;
        private TextView consume;

        private Button submit;
        private SQLiteHandler db;

        public ViewHolder(View view) {
            super(view);

            db = new SQLiteHandler(context);
            product= (EditText) view.findViewById(R.id.product);
            open= (EditText) view.findViewById(R.id.open);
            close= (EditText) view.findViewById(R.id.close);

            consume= (TextView) view.findViewById(R.id.consume);

            submit= (Button) view.findViewById(R.id.submit);



        }
    }

    public void animate(RecyclerView.ViewHolder viewHolder) {
        final Animation animAnticipateOvershoot = AnimationUtils.loadAnimation(context, R.anim.anticipate);
        viewHolder.itemView.setAnimation(animAnticipateOvershoot);
    }
}
