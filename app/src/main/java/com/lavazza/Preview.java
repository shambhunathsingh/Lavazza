package com.lavazza;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
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

import java.util.ArrayList;
import java.util.HashMap;

public class Preview extends AppCompatActivity{

    SQLitePreview db;

    String sno,cus_id,lavazza;



    public static String [] serial;
    public static String [] product;
    public static String [] opening;
    public static String [] closing;
    public static String [] consumption;

    RecyclerView recyclerView;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle bundle=getIntent().getExtras();
        sno = bundle.getString("sno");
        cus_id=bundle.getString("cus_id");
        lavazza=bundle.getString("lavazza");


        db=new SQLitePreview(Preview.this);

        ArrayList<String> serial1=db.getSerial(sno);
        serial=new String[serial1.size()];
        serial=serial1.toArray(serial);

        ArrayList<String> prod1=db.getProduct(sno);
        product=new String[prod1.size()];
        product=prod1.toArray(product);

        ArrayList<String> open1=db.getOpen(sno);
        opening=new String[open1.size()];
        opening=open1.toArray(opening);

        ArrayList<String> close1=db.getClose(sno);
        closing=new String[close1.size()];
        closing=close1.toArray(closing);

        ArrayList<String> consu1=db.getConsume(sno);
        consumption=new String[consu1.size()];
        consumption=consu1.toArray(consumption);




        submit= (Button) findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final Dialog dialogBuilder = new Dialog(view.getContext());
                dialogBuilder.requestWindowFeature(Window.FEATURE_NO_TITLE);
//                LayoutInflater inflater1 =getLayoutInflater();
//                View dialogView1 = inflater1.inflate(R.layout.grid, null);
                final View dialogView1 = LayoutInflater.from(Preview.this).inflate(R.layout.sumit_popup, null);
                dialogBuilder.setContentView(dialogView1);


                LinearLayout yes= (LinearLayout) dialogView1.findViewById(R.id.yes);
                LinearLayout no= (LinearLayout) dialogView1.findViewById(R.id.no);

                yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                          for (int i=0;i<product.length;i++)
                            {
//                              db.addUser(serial[i], product[i], opening[i], closing[i], consumption[i], cus_id);
                                registerUser(serial[i], product[i], opening[i], closing[i], consumption[i], cus_id);

                            }
                        db.deleteUsers(sno,cus_id);
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


        final android.support.v7.app.ActionBar ab = getSupportActionBar();
        if(ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
//            ab.setTitle(Html.fromHtml("<font color='#0e2c4d'>Preview</font>"));
        }
        initViews();









//        ListAdapter adapter = new SimpleAdapter(
//                Preview.this, list, R.layout.display_reading,
//                new String[]{"serial_no", "p_name", "opening", "closing", "consumption"},
//                new int[]{R.id.serial, R.id.pname, R.id.opening, R.id.closing, R.id.consumption});
//
//        listView.setAdapter(adapter);



//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    private void initViews() {
//        final RippleView rippleView = (RippleView) findViewById(R.id.rect);
        recyclerView = (RecyclerView) findViewById(R.id.card_recycler_view);
        recyclerView.setHasFixedSize(true);
//        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 2);
//        RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));

        ArrayList<PreviewData> androidVersions = prepareData();
        PreviewDataAdapter adapter = new PreviewDataAdapter(Preview.this, androidVersions);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
//                String name=getString(R.string.title_activity_client_info);

                switch (position) {
                    case 0:
//                        name=getString(R.string.bloom);
//                        Intent a5 = new Intent(Fitness.this, Client_info.class);
//                        Bundle abundle5=new Bundle();
//                        abundle5.putString("name",name);
//                        abundle5.putString("cname","");
//                        abundle5.putString("cdetail","95938671");
//                        abundle5.putString("mail","bloomfamilyyoga@gmail.com");
//                        abundle5.putString("category","");
////                        bund1le3.putString("address","145/6 Al Inshirah Street Madinat Qaboos Sultanate of Oman");
//                        abundle5.putString("address","Madinat Qaboos");
//                        abundle5.putInt("image",image[0]);
//                        abundle5.putString("website","www.bloomfamilyyoga.com");
//                        abundle5.putString("hour","Sun, Mon:09:30-05:30, \nTue::09:00-06:30, \nThur::09:00-12:00,\n Fri & Sat, closed.");
//                        abundle5.putString("id", String.valueOf(position));
//                        abundle5.putDouble("latitude",23.6040398);
//                        abundle5.putDouble("longitude",58.4595607);
//                        abundle5.putString("kilometer",distance);
//                        a5.putExtras(abundle5);
////                        l.putExtra("id", position);
//                        startActivity(a5);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onLongClick(View view, int position) {

            }

        }));
    }

        private ArrayList<PreviewData> prepareData(){
//        String [] person={distance};
            String serialno=sno;
        ArrayList<PreviewData> android_version = new ArrayList<>();
        for(int i=0;i<product.length;i++){
            PreviewData androidVersion = new PreviewData();
            androidVersion.setSerialNo(serial[i]);
            androidVersion.setProduct(product[i]);
            androidVersion.setOpening(opening[i]);
            androidVersion.setClosing(closing[i]);
            androidVersion.setConsumption(consumption[i]);

            android_version.add(androidVersion);
        }
        return android_version;
    }

    private void registerUser(final String serial_no, final String p_name, final String opening, final String closing, final String consumption, final String cus_id) {
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

                        Toast.makeText(Preview.this, "Added Successfully", Toast.LENGTH_SHORT).show();

                        // Launch login activity
//                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
//                        startActivity(intent);
//                        finish();

                    } else {

                        // Error occurred in registration. Get the error
                        // message
                        String errorMsg = jObj.getString("error_msg");
                        Toast.makeText(Preview.this,
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
                Toast.makeText(Preview.this, error.getMessage(), Toast.LENGTH_SHORT).show();
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
        super.onBackPressed();
        if(lavazza.equalsIgnoreCase("customer")) {
            finish();
            Intent intent = new Intent(Preview.this, SalesInput1.class);
            intent.putExtra("cus_id", cus_id);
            intent.putExtra("lavazza", lavazza);
            intent.putExtra("sno",sno);
            startActivity(intent);

        }
        else
        {
            finish();
            Intent intent = new Intent(Preview.this, SalesInput.class);
            intent.putExtra("cus_id", cus_id);
            intent.putExtra("lavazza", lavazza);
            intent.putExtra("sno",sno);
            startActivity(intent);


        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == android.R.id.home)
        {
            if(lavazza.equalsIgnoreCase("customer")) {
                finish();
                Intent intent = new Intent(Preview.this, SalesInput1.class);
                intent.putExtra("sno",sno);
                intent.putExtra("cus_id", cus_id);
                intent.putExtra("lavazza", lavazza);
                startActivity(intent);
                return true;
            }
            else
            {
                finish();
                Intent intent = new Intent(Preview.this, SalesInput.class);
                intent.putExtra("sno",sno);
                intent.putExtra("cus_id", cus_id);
                intent.putExtra("lavazza", lavazza);
                startActivity(intent);
                return true;

            }
//            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
