package com.lavazza;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.lavazza.app.AppConfig;
import com.lavazza.app.AppController;
import com.lavazza.helper.SQLitePreview;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class SalesInput extends AppCompatActivity {

    TextView date,s_no;
    TextView consume;
    EditText product,opening,closing;

    //    Spinner product;
    String sno,cus_id,lavazza;
    int consumption=0,open_read=0,close_read=0;
    int mYear, mMonth, mDay, mHour, mMinute;
    Button submit;
    boolean status1=false;
    boolean status2=false;

    RecyclerView recyclerView;
    String number1;
    int num;
    static int num1;
    private int consumption1;
    LinearLayout add,preview;

    SQLitePreview db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_input);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        num1=0;

        Bundle bundle =getIntent().getExtras();
        sno = bundle.getString("sno");
        cus_id=bundle.getString("cus_id");
        lavazza=bundle.getString("lavazza");
//        Toast.makeText(this, sno+"", Toast.LENGTH_SHORT).show();


        db=new SQLitePreview(SalesInput.this);

        final android.support.v7.app.ActionBar ab = getSupportActionBar();
        if(ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
//            ab.setTitle(Html.fromHtml("<font color='#0e2c4d'>Machine Readings</font>"));
//            ab.setTitle(name);
        }

        date= (TextView) findViewById(R.id.date);
//        consume= (TextView) findViewById(R.id.consume1);
        s_no= (TextView) findViewById(R.id.mc_serial);
//        number= (EditText) findViewById(R.id.number);
//        customer= (EditText) findViewById(R.id.customer);
//        open= (EditText) findViewById(R.id.open);
//        close= (EditText) findViewById(R.id.close);
//        product= (Spinner) findViewById(R.id.product);

//        submit= (Button) findViewById(R.id.submit);

        recyclerView = (RecyclerView) findViewById(R.id.card_recycler_view);

        s_no.setText(sno);

        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        String date1=mDay + "-" + (mMonth + 1) + "-" + mYear;
//        Toast.makeText(this, "", Toast.LENGTH_SHORT).show();

        date.setText(date1);

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

//                int year=Calendar.YEAR;
//                int day=Calendar.DAY_OF_MONTH;
//                Toast.makeText(SalesInput.this, mYear+","+mDay, Toast.LENGTH_SHORT).show();

                DatePickerDialog datePickerDialog = new DatePickerDialog(view.getContext(),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                date.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
//                                 date=dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
//                                Toast.makeText(context, date+"", Toast.LENGTH_SHORT).show();
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

//        s_no.setText(sno);

//        open.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            status1=true;
//
//            }
//        });
//
//        close.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                status2=true;

//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        String open_read1=open.getText().toString();
//            String close_read1=close.getText().toString();
//
//            open_read= Integer.parseInt(open_read1);
//            close_read= Integer.parseInt(close_read1);
//
////                Toast.makeText(SalesInput.this, open_read+","+close_read, Toast.LENGTH_SHORT).show();
//
//            int read=close_read-open_read;
//            if(read>=0)
//            {
//                consume.setText(read+"");
//            }
//            else {
//                Toast.makeText(SalesInput.this, "Please Give the Correct Reading", Toast.LENGTH_SHORT).show();
//            }
//
//
//                    }
//                }, 5000);


//            }
//        });



//            String open_read1 = open.getText().toString().trim();
//            String close_read1 = close.getText().toString().trim();
//        try {
//            open_read = Integer.parseInt(open_read1);
//            close_read = Integer.parseInt(close_read1);
//
//            Toast.makeText(this, open_read + "," + close_read, Toast.LENGTH_SHORT).show();
//        }
//        catch(NumberFormatException e){
//            Toast.makeText(this, e+"", Toast.LENGTH_SHORT).show();
//
//        }

//        consumption=close_read-open_read;
//
//        if(consumption>=0)
//        {
//            consume.setText(consumption);
//        }
//        else{
//            Toast.makeText(this, "Please Give Correct Value", Toast.LENGTH_SHORT).show();
//        }


//        String[] product_item={"Select","Tea","Coffee","Milk","Lime Tea"};
//        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(SalesInput.this, R.layout.simple_spinner_item, product_item);
////        CustomAdapter adapter = new CustomAdapter(context,quan1);
//        product.setAdapter(adapter2);
//        product.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
////                String[] quan1={"100pack","200packs","300packs","400packs"};
////                CustomAdapter adapter = new CustomAdapter(view.getContext(),quan1);
////                viewHolder.quantity.setAdapter(adapter);
//                String time_item=product.getSelectedItem().toString();
////                time=viewHolder.time.getSelectedItem().toString();
////                Toast.makeText(SalesInput.this, time_item+"", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });

//        if((status1==true) && (status2==true))
//        {
//
//            String open_read1=open.getText().toString();
//            String close_read1=close.getText().toString();
//
//            open_read= Integer.parseInt(open_read1);
//            close_read= Integer.parseInt(close_read1);
//
////                Toast.makeText(SalesInput.this, open_read+","+close_read, Toast.LENGTH_SHORT).show();
//
//            int read=close_read-open_read;
//            if(read>=0)
//            {
//                consume.setText(read+"");
//            }
//            else {
//                Toast.makeText(SalesInput.this, "Please Give the Correct Reading", Toast.LENGTH_SHORT).show();
//            }
//
//        }

        product= (EditText) findViewById(R.id.product);
        opening= (EditText) findViewById(R.id.open);
        closing= (EditText) findViewById(R.id.close);
        consume= (TextView) findViewById(R.id.consume);








        add= (LinearLayout) findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                view.startAnimation(AnimationUtils.loadAnimation(SalesInput.this, R.anim.image_click));

                String open1=opening.getText().toString();
                String close1=closing.getText().toString();
                String product1=product.getText().toString();

                boolean isConnected = ConnectivityReceiver.isConnected();

                if(product1.isEmpty())
                {
                    Toast.makeText(SalesInput.this, "Enter The Product", Toast.LENGTH_SHORT).show();
                }
                else if(open1.isEmpty())
                {
                    Toast.makeText(SalesInput.this, "Enter The Opening Reading", Toast.LENGTH_SHORT).show();
                }
                else if(close1.isEmpty())
                {
                    Toast.makeText(SalesInput.this, "Enter The Closing Reading", Toast.LENGTH_SHORT).show();
                }
                else {

                    if(isConnected) {
                        consumption = (Integer.parseInt(closing.getText().toString()) - Integer.parseInt(opening.getText().toString()));
                        String consum = String.valueOf(consumption);

                        consume.setText(consum);
                        db.addUser(sno, product.getText().toString(), opening.getText().toString(), closing.getText().toString(), consum, cus_id);

                        product.setText("");
                        opening.setText("");
                        closing.setText("");
//                        consume.setText("");
//                                registerUser(sno, cus_id, product.getText().toString(), open.getText().toString(), close.getText().toString(), consum);
                    }
                    else
                    {
                        Toast.makeText(SalesInput.this,"Sorry! Not Connected To Internet",Toast.LENGTH_SHORT).show();
                    }
                }

                /////////working code/////////////////
//                LinearLayout linearLayout=(LinearLayout) findViewById(R.id.linear);
//                LayoutInflater factory = LayoutInflater.from(SalesInput.this);
//                View dialogView1 = factory.inflate(R.layout.livesale_product, null);
//                final EditText open= (EditText) dialogView1.findViewById(R.id.open);
//                final EditText close= (EditText) dialogView1.findViewById(R.id.close);
//                final EditText product= (EditText) dialogView1.findViewById(R.id.product);
//                final TextView consume= (TextView) dialogView1.findViewById(R.id.consume);
//
//
//
//                Button submit = (Button) dialogView1.findViewById(R.id.submit);
//                submit.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                        String open1=open.getText().toString();
//                        String close1=close.getText().toString();
//                        String product1=product.getText().toString();
//
//                        boolean isConnected = ConnectivityReceiver.isConnected();
//
//                        if(product1.isEmpty())
//                        {
//                            Toast.makeText(SalesInput.this, "Enter The Product", Toast.LENGTH_SHORT).show();
//                        }
//                        else if(open1.isEmpty())
//                        {
//                            Toast.makeText(SalesInput.this, "Enter The Opening Reading", Toast.LENGTH_SHORT).show();
//                        }
//                        else if(close1.isEmpty())
//                        {
//                            Toast.makeText(SalesInput.this, "Enter The Closing Reading", Toast.LENGTH_SHORT).show();
//                        }
//                        else {
//
//                            if(isConnected) {
//                                consumption = (Integer.parseInt(close.getText().toString()) - Integer.parseInt(open.getText().toString()));
//                                String consum = String.valueOf(consumption);
//
//                                consume.setText(consum);
//                                db.addUser(sno, product.getText().toString(), open.getText().toString(), close.getText().toString(), consum, cus_id);
////                                registerUser(sno, cus_id, product.getText().toString(), open.getText().toString(), close.getText().toString(), consum);
//                            }
//                            else
//                            {
//                                Toast.makeText(SalesInput.this,"Sorry! Not Connected To Internet",Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    }
//                });
//                linearLayout.addView(dialogView1);

                ////////////////////////////////////////////////////





//                number1=number.getText().toString().trim();
//                if(number1.isEmpty())
//                {
//                    Toast.makeText(SalesInput.this,"Enter the Number Of Product",Toast.LENGTH_SHORT).show();
//
//                }
//                else {
//                    num = Integer.parseInt(number1);
//
//
////                    num=1;
////                    num1=num1+num;
//
//                    recyclerView.setVisibility(View.VISIBLE);
////                    submit.setVisibility(View.INVISIBLE);
//
////                String open_read1=open.getText().toString();
////                String close_read1=close.getText().toString();
//
////                open_read= Integer.parseInt(open_read1);
////                close_read= Integer.parseInt(close_read1);
////
//////                Toast.makeText(SalesInput.this, open_read+","+close_read, Toast.LENGTH_SHORT).show();
////
////                int read=close_read-open_read;
////                if(read>=0)
////                {
////                    consume.setText(Integer.toString(read));
////                }
////                else {
////                    Toast.makeText(SalesInput.this, "Please Give the Correct Reading", Toast.LENGTH_SHORT).show();
////                }
//
//                    recyclerView.setHasFixedSize(true);
////                RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity().getApplication(), 2);
////        RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
//                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(SalesInput.this);
//                    recyclerView.setLayoutManager(layoutManager);
////        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
//
////                getData();
//
//                    ArrayList<SalesData> androidVersions = prepareData();
//
//
//                    SalesDataAdapter adapter = new SalesDataAdapter(SalesInput.this, androidVersions,sno,cus_id);
//                    recyclerView.setAdapter(adapter);
////        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity().getApplication(), recyclerView, new ClickListener() {
////            @Override
////            public void onClick(View view, int position) {
//////                String name=getString(R.string.title_activity_client_info);
////
////                switch (position) {
////                    case 0:
////                        name=getString(R.string.bloom);
////                        Intent a5 = new Intent(Fitness.this, Client_info.class);
////                        Bundle abundle5=new Bundle();
////                        abundle5.putString("name",name);
////                        abundle5.putString("cname","");
////                        abundle5.putString("cdetail","95938671");
////                        abundle5.putString("mail","bloomfamilyyoga@gmail.com");
////                        abundle5.putString("category","");
//////                        bund1le3.putString("address","145/6 Al Inshirah Street Madinat Qaboos Sultanate of Oman");
////                        abundle5.putString("address","Madinat Qaboos");
////                        abundle5.putInt("image",image[0]);
////                        abundle5.putString("website","www.bloomfamilyyoga.com");
////                        abundle5.putString("hour","Sun, Mon:09:30-05:30, \nTue::09:00-06:30, \nThur::09:00-12:00,\n Fri & Sat, closed.");
////                        abundle5.putString("id", String.valueOf(position));
////                        abundle5.putDouble("latitude",23.6040398);
////                        abundle5.putDouble("longitude",58.4595607);
////                        abundle5.putString("kilometer",distance);
////                        a5.putExtras(abundle5);
//////                        l.putExtra("id", position);
////                        startActivity(a5);
////                        break;
////                    default:
////                        break;
////                }
////            }
////
////            @Override
////            public void onLongClick(View view, int position) {
////
////            }
////        }));
//
//
//                }
            }
        });


        preview= (LinearLayout) findViewById(R.id.preview);
        preview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(AnimationUtils.loadAnimation(SalesInput.this, R.anim.image_click));

                Intent intent=new Intent(SalesInput.this,Preview.class);
                intent.putExtra("sno",sno);
                intent.putExtra("cus_id", cus_id);
                intent.putExtra("lavazza", lavazza);
                startActivity(intent);
            }
        });



//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    private ArrayList<SalesData> prepareData(){
//        String [] person={distance};
//            int num= Integer.parseInt(number1);
        ArrayList<SalesData> android_version = new ArrayList<>();
        for(int i=0;i<num;i++){
//        for(int i=0;i<num1;i++){
            SalesData androidVersion = new SalesData();
//            androidVersion.setSerial_no(serialN0[i]);
//            androidVersion.setDate(date[i]);
//            androidVersion.setTime(time[i]);
//            androidVersion.setQuality(quality[i]);
//            androidVersion.setStatus(status[i]);
//            androidVersion.setImageId(image[i]);
//            androidVersion.setCart(cart_image[i]);
//            Toast.makeText(this, serialN0[i]+","+image[i], Toast.LENGTH_SHORT).show();
            android_version.add(androidVersion);
        }
        return android_version;
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

                        Toast.makeText(SalesInput.this, "Added Successfully", Toast.LENGTH_SHORT).show();

                        // Launch login activity
//                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
//                        startActivity(intent);
//                        finish();

                    } else {

                        // Error occurred in registration. Get the error
                        // message
                        String errorMsg = jObj.getString("error_msg");
                        Toast.makeText(SalesInput.this,
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
                Toast.makeText(SalesInput.this, error.getMessage(), Toast.LENGTH_SHORT).show();
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
    public void onBackPressed() {
//        Intent intent = new Intent(SalesInput.this,LiveSalesSE.class);
//        intent.putExtra("cus_id",cus_id);
//        intent.putExtra("lavazza",lavazza);
//        startActivity(intent);
//        finish();


//        if(lavazza.equalsIgnoreCase("customer")) {
//
//            Intent intent = new Intent(SalesInput.this, LiveSales.class);
//            intent.putExtra("cus_id", cus_id);
//            intent.putExtra("lavazza", lavazza);
//            startActivity(intent);
//            finish();
//        }
//        else
//        {

        Intent intent = new Intent(SalesInput.this, LiveSalesSE.class);
        intent.putExtra("cus_id", cus_id);
        intent.putExtra("lavazza", lavazza);
        startActivity(intent);
        finish();


//        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.reading_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == android.R.id.home)
        {

//            Intent intent = new Intent(SalesInput.this,LiveSalesSE.class);
//            intent.putExtra("cus_id",cus_id);
//            intent.putExtra("lavazza",lavazza);
//            startActivity(intent);
//            finish();
//            return true;

//            if(lavazza.equalsIgnoreCase("customer")) {
//
//                Intent intent = new Intent(SalesInput.this, LiveSales.class);
//                intent.putExtra("cus_id", cus_id);
//                intent.putExtra("lavazza", lavazza);
//                startActivity(intent);
//                finish();
//                return true;
//            }
//            else
//            {

            Intent intent = new Intent(SalesInput.this, LiveSalesSE.class);
            intent.putExtra("cus_id", cus_id);
            intent.putExtra("lavazza", lavazza);
            startActivity(intent);
            finish();
            return true;

//            }
        }
        return super.onOptionsItemSelected(item);
    }


}
