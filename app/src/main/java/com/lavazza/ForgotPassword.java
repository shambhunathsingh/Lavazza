package com.lavazza;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lavazza.app.AppConfig;
import com.lavazza.app.RequestHandler;

import java.util.HashMap;

public class ForgotPassword extends AppCompatActivity {

    String mail,password,passw,confpass;
    EditText security,pass,conpass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final android.support.v7.app.ActionBar ab = getSupportActionBar();
        if(ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
//            ab.setTitle(name);
        }

        security = (EditText) findViewById(R.id.pemail);
//          getpass = (TextView) findViewById(R.id.textView3);

        pass= (EditText) findViewById(R.id.fpassword);
        conpass= (EditText) findViewById(R.id.fconfirm_password);

        Button ok = (Button) findViewById(R.id.submit);
        Button cancel = (Button) findViewById(R.id.cancel);


//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mail=security.getText().toString().trim();
                passw=pass.getText().toString().trim();
                confpass=conpass.getText().toString().trim();

                if(mail.isEmpty())
                {
                    Toast.makeText(ForgotPassword.this, "Enter Email", Toast.LENGTH_SHORT).show();
                }
                else if(passw.isEmpty())
                {
                    Toast.makeText(ForgotPassword.this, "Enter Password", Toast.LENGTH_SHORT).show();
                }
                else if(confpass.isEmpty())
                {
                    Toast.makeText(ForgotPassword.this, "Enter Confirm Password", Toast.LENGTH_SHORT).show();
                }
                else if(!passw.equals(confpass))
                {
                    Toast.makeText(ForgotPassword.this, "Password & Confirm password are not matched ", Toast.LENGTH_SHORT).show();
                }
                else {

//                    updateEmployee();
                    getMail();
//                    ValidateLogin(mail,passw);
                }

            }
        });


        cancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //dialog.dismiss();

                Intent i=new Intent(ForgotPassword.this,Login.class);
                startActivity(i);
                finish();
            }
        });

    }

    private void updateEmployee(){
        mail=security.getText().toString().trim();
        passw=pass.getText().toString().trim();
        confpass=conpass.getText().toString().trim();


        class UpdateEmployee extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ForgotPassword.this,"Updating...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(ForgotPassword.this,s,Toast.LENGTH_SHORT).show();
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put(AppConfig.KEY_PASSWORD,passw);
                hashMap.put(AppConfig.KEY_EMAIL,mail);
//                hashMap.put(AppConfig.KEY_EMP_DESG,desg);
//                hashMap.put(AppConfig.KEY_EMP_SAL,salary);

                RequestHandler rh = new RequestHandler();

                String s = rh.sendPostRequest(AppConfig.URL_UPDATE_PASSWORD,hashMap);

                return s;
            }
        }

        UpdateEmployee ue = new UpdateEmployee();
        ue.execute();
    }

    private void getMail(){
        final String mail1=security.getText().toString().trim();



        class GetMail extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(ForgotPassword.this,"Checking...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
//                Toast.makeText(Forgot_Password.this,s,Toast.LENGTH_SHORT).show();
                if(s.equalsIgnoreCase("Success"))
                {
                    updateEmployee();
                }
                else
                {
                    Toast.makeText(ForgotPassword.this, "Please check your Email ID", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> hashMap = new HashMap<>();
//                hashMap.put(AppConfig.KEY_PASSWORD,passw);
                hashMap.put(AppConfig.KEY_EMAIL,mail1);
//                hashMap.put(AppConfig.KEY_EMP_DESG,desg);
//                hashMap.put(AppConfig.KEY_EMP_SAL,salary);

                RequestHandler rh = new RequestHandler();

                String s = rh.sendPostRequest(AppConfig.URL_GET_MAIL,hashMap);

                return s;
            }
        }

        GetMail ue = new GetMail();
        ue.execute();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == android.R.id.home)
        {
            finish();
//            Intent intent = new Intent(Nurseries.this,MainNavigation.class);
//            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
