package com.lavazza;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.lavazza.app.AppConfig;
import com.lavazza.app.AppController;
import com.lavazza.helper.SQLiteHandler;
import com.lavazza.helper.SessionManager;
import com.lavazza.helper.SessionManager_New;
import com.rey.material.widget.ProgressView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class Login extends AppCompatActivity {

    private static final String TAG = Login.class.getSimpleName();
    Button login;
    TextView forgot;
    EditText name,password;

    private ProgressDialog pDialog;
    private SessionManager session;
    private SessionManager_New sessionManager_new;
    private SQLiteHandler db;


    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;
    private Boolean saveLogin;

    ProgressView progressBar;

    String cus_id;
    String user_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final android.support.v7.app.ActionBar ab = getSupportActionBar();
        if (ab != null) {
//            ab.setDisplayHomeAsUpEnabled(true);
            ab.hide();

        }

//        progressBar= (ProgressView) findViewById(R.id.loading_spinner);

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();

        saveLogin = loginPreferences.getBoolean("saveLogin", false);
        if (saveLogin == true) {
            cus_id=loginPreferences.getString("cus_id", "");
            user_type=loginPreferences.getString("user_type", "");
//            password.setText(loginPreferences.getString("password", ""));
//            saveLoginCheckBox.setChecked(true);
        }

        // Session manager
        session = new SessionManager(getApplicationContext());
        sessionManager_new = new SessionManager_New(getApplicationContext());

//        if (session.isLoggedIn()) {
        if (sessionManager_new.isLoggedIn()) {
        if (session.isLoggedIn()) {
            /*if (user_type.equalsIgnoreCase("customer")) {
                Intent intent = new Intent(Login.this,
                        MainActivityCustomer.class);
                intent.putExtra("id", cus_id);
                intent.putExtra("utype", user_type);
//            Toast.makeText(this, cus_id+"", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                finish();

            } else {*/
                // User is already logged in. Take him to main activity
                Intent intent = new Intent(Login.this,
                        MainActivity.class);
                intent.putExtra("id", cus_id);
                intent.putExtra("utype", user_type);
//            Toast.makeText(this, cus_id+"", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                finish();
            //}
        }
        }

        // SQLite database handler
        db = new SQLiteHandler(getApplicationContext());

        name= (EditText) findViewById(R.id.name);
        password= (EditText) findViewById(R.id.password);



        login= (Button) findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent i=new Intent(Login.this,MainActivity.class);
                startActivity(i);*/


                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(name.getWindowToken(), 0);

                String name1=name.getText().toString();
                String pass=password.getText().toString();


                boolean isConnected = ConnectivityReceiver.isConnected();

                if (!name1.isEmpty() && !pass.isEmpty()) {
                    if(isConnected) {
                        // login user
//                        if(checkAndRequestPermissions()) {
                        // carry on the normal flow, as the case of  permissions  granted.

                        checkLogin(name1, pass);

//                        }
                    }
                    else
                    {
                        Toast.makeText(Login.this,"Sorry! Not Connected To Internet",Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Prompt user to enter credentials
                    Toast.makeText(getApplicationContext(),
                            "Please enter the credentials!", Toast.LENGTH_LONG)
                            .show();
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


        forgot= (TextView) findViewById(R.id.forgot);
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
//                Intent intent=new Intent(Login.this,ForgotPassword.class);
//                startActivity(intent);

            }
        });

    }

    private void checkLogin(final String name1, final String password1) {
        // Tag used to cancel the request
        String tag_string_req = "req_login";
/*

        Toast.makeText(Login.this,name1,Toast.LENGTH_SHORT).show();
        Toast.makeText(Login.this,password1,Toast.LENGTH_SHORT).show();
*/

        pDialog.setMessage("Logging in ...");
        showDialog();

//        progressBar.setVisibility(View.VISIBLE);

        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_LOGIN, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Login Response: " + response.toString());
                hideDialog();
//                progressBar.setVisibility(View.GONE);
                Toast.makeText(Login.this,"Working without error in response??",Toast.LENGTH_SHORT).show();             //***********************

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");
                    Toast.makeText(Login.this,"Working with error??",Toast.LENGTH_SHORT).show();                        //***********************
                    // Check for error node in json
                    if (!error) {

                        Toast.makeText(Login.this,"Working without error??",Toast.LENGTH_SHORT).show();                 //***********************

//                        Toast.makeText(Login.this,password1,Toast.LENGTH_SHORT).show();


                        session.setLogin(true);

                        JSONObject user = jObj.getJSONObject("user");
//                        String password = user.getString("password");
                         user_type = user.getString("USERTYPE");     //getting usertype from login.php
                         cus_id=user.getString("CUSTOMERID");             //getting customer id from login.php
                        Toast.makeText(Login.this, user_type+","+cus_id, Toast.LENGTH_SHORT).show();

                        sessionManager_new.createLoginSession(cus_id,user_type);



                        loginPrefsEditor.putBoolean("saveLogin", true);
                        loginPrefsEditor.putString("id", cus_id);
                        loginPrefsEditor.putString("utype", user_type);
                        loginPrefsEditor.commit();

                        /*if(user_type.equalsIgnoreCase("customer"))
                        {
                            Intent intent = new Intent(Login.this,
                                    MainActivityCustomer.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("utype", user_type);
                            bundle.putString("id", cus_id);
//                        bundle.putString("location",location);
                            intent.putExtras(bundle);
                            startActivity(intent);
//                        finish();
                            name.setText("");
                            password.setText("");
                        }
                        else {*/

                            Intent intent = new Intent(Login.this,
                                    MainActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("utype", user_type);       //passing user type to MainActivity.class
                            bundle.putString("id", cus_id);             //passing customer id to MainActivity.class
//                        bundle.putString("location",location);
                            intent.putExtras(bundle);
                            startActivity(intent);
//                        finish();
                            name.setText("");
                            password.setText("");
                        //}
                    } else {
                        // Error in login. Get the error message
                        String errorMsg = jObj.getString("error_msg");
                        Toast.makeText(getApplicationContext(),
                                errorMsg, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
                hideDialog();
//                progressBar.setVisibility(View.GONE);
            }
        }) {

            @Override
            protected java.util.Map<String, String> getParams() {
                // Posting parameters to login url
                java.util.Map<String, String> params = new HashMap<String, String>();
                params.put("USERNAME", name1);                  //passing entered username to login.php
                params.put("PASSWORD", password1);          //passing entered password to login.php

                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }


    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
//            Intent intent = new Intent(Kids_Party.this,MainNavigation.class);
//            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
            if (doubleBackToExitPressedOnce) {
                Intent startMain = new Intent(Intent.ACTION_MAIN);
                startMain.addCategory(Intent.CATEGORY_HOME);
                startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(startMain);
                //moveTaskToBack(true);
                return;
            }

            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this.getBaseContext(), "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
//        }
    }

}
