package com.lavazza;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.lavazza.app.AppConfig;
import com.lavazza.app.AppController;
import com.rey.material.widget.ProgressView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class ServiceRequest extends AppCompatActivity implements ConnectivityReceiver.ConnectivityReceiverListener{

    String cus_id,lavazza;
    EditText location,serial,e_code,e_message,remark;
    Button submit;
    ProgressView progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_request);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle bundle =getIntent().getExtras();
        cus_id=bundle.getString("cus_id");
        lavazza=bundle.getString("lavazza");

        checkConnection();


        final android.support.v7.app.ActionBar ab = getSupportActionBar();
        if(ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
//            ab.setTitle(Html.fromHtml("<font color='#0e2c4d'>Service Request</font>"));
//            ab.setTitle(name);
        }

        progressBar= (ProgressView) findViewById(R.id.loading_spinner);


        location= (EditText) findViewById(R.id.location);
        serial= (EditText) findViewById(R.id.serialno);
        e_code= (EditText) findViewById(R.id.e_code);
        e_message= (EditText) findViewById(R.id.e_message);
        remark= (EditText) findViewById(R.id.remark);

        submit= (Button) findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String location1=location.getText().toString().trim();
                String serial1=serial.getText().toString().trim();
                String e_code1=e_code.getText().toString().trim();
                String e_message1=e_message.getText().toString().trim();
                String remark1=remark.getText().toString().trim();
                String cus_id1=cus_id;

                if(location1.isEmpty())
                {
                    Toast.makeText(ServiceRequest.this, "Enter the Location", Toast.LENGTH_SHORT).show();
                }
                else if(serial1.isEmpty())
                {
                    Toast.makeText(ServiceRequest.this, "Enter the Machine Serial No.", Toast.LENGTH_SHORT).show();
                }
                else if(e_code1.isEmpty())
                {
                    Toast.makeText(ServiceRequest.this, "Enter the Error Code", Toast.LENGTH_SHORT).show();
                }
                else if(e_message1.isEmpty())
                {
                    Toast.makeText(ServiceRequest.this, "Enter the Error Message", Toast.LENGTH_SHORT).show();
                }
                else if(remark1.isEmpty())
                {
                    Toast.makeText(ServiceRequest.this, "Enter the Remark", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    registerUser(cus_id1,location1,serial1,e_code1,e_message1,remark1);
                    location.setText("");
                    serial.setText("");
                    e_code.setText("");
                    e_message.setText("");
                    remark.setText("");
                }
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

    private void checkConnection() {
        boolean isConnected = ConnectivityReceiver.isConnected();
        showSnack(isConnected);
    }
    private void showSnack(boolean isConnected) {
        String message;
        int color;
        if (isConnected) {
            message = "Good! Connected to Internet";
//            Toast.makeText(MainNavigation.this, message, Toast.LENGTH_SHORT).show();
//            color = Color.WHITE;
        } else {
            message = "Sorry! Not connected to internet";
            Toast.makeText(ServiceRequest.this, message, Toast.LENGTH_SHORT).show();
//            Toast toast=Toast.makeText(this,"Toast:Gravity.TOP",Toast.LENGTH_SHORT);
//            toast.setGravity(Gravity.CENTER,0,0);
//            toast.setView(viewLayout);
//            toast.show();

//            color = Color.RED;

        }


    }


    private void registerUser(final String cus_id, final String location, final String serial_no, final String e_code, final String e_message, final String remark) {
        // Tag used to cancel the request
//        Toast.makeText(getApplicationContext(), "Marked Present", Toast.LENGTH_SHORT).show();
        String tag_string_req = "req_register";
//        Toast.makeText(FullScannerActivity.this, "Register", Toast.LENGTH_SHORT).show();
//        pDialog.setMessage("Adding ...");
//        showDialog();
//        progressBar.setVisibility(View.);
        progressBar.setVisibility(View.VISIBLE);


        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_ADD_SERVICE_REQUEST, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
//                Log.d(TAG, "Register Response: " + response.toString());
//                hideDialog();
                progressBar.setVisibility(View.GONE);


                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");
                    if (!error) {
                        Toast.makeText(ServiceRequest.this, "Service Request Added", Toast.LENGTH_SHORT).show();
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

//                        Toast.makeText(Cart.this, "Product Added to Database", Toast.LENGTH_SHORT).show();

                        // Launch login activity
//                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
//                        startActivity(intent);
//                        finish();

                    } else {

                        // Error occurred in registration. Get the error
                        // message
                        String errorMsg = jObj.getString("error_msg");
                        Toast.makeText(getApplicationContext(),
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
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
//                hideDialog();
                progressBar.setVisibility(View.GONE);
            }
        }) {

            @Override
            protected java.util.Map<String, String> getParams() {
                // Posting params to register url
                java.util.Map<String, String> params = new HashMap<String, String>();
                params.put("cus_id", cus_id);
                params.put("location", location );
                params.put("serial_no", serial_no);
                params.put("error_code", e_code );
                params.put("error_message", e_message );
                params.put("remark", remark );


                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == android.R.id.home)
        {
//            finish();
//            Intent intent = new Intent(ServiceRequest.this,MainActivity.class);
//            Bundle bundle=new Bundle();
//            bundle.putString("id",cus_id);
//            bundle.putString("lavazza",lavazza);
//            intent.putExtras(bundle);
//            startActivity(intent);
//            return true;

            if(lavazza.equalsIgnoreCase("customer")) {
                finish();
                Intent intent = new Intent(ServiceRequest.this, MainActivityCustomer.class);
                intent.putExtra("id", cus_id);
                intent.putExtra("lavazza", lavazza);
                startActivity(intent);
                return true;
            }
            else
            {
                finish();
                Intent intent = new Intent(ServiceRequest.this, MainActivity.class);
                intent.putExtra("id", cus_id);
                intent.putExtra("lavazza", lavazza);
                startActivity(intent);
                return true;

            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // register connection status listener
        AppController.getInstance().setConnectivityListener(this);
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        showSnack(isConnected);
    }
}
