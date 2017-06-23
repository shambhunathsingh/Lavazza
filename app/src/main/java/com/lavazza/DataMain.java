package com.lavazza;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class DataMain extends AppCompatActivity {
    WebView mWebView;
    String name;
    String cus_id,lavazza;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle bundle =getIntent().getExtras();
        cus_id=bundle.getString("cus_id");
        lavazza=bundle.getString("lavazza");


        name="Data Science";
        final android.support.v7.app.ActionBar ab = getSupportActionBar();
        if(ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
//            ab.setTitle(Html.fromHtml("<font color='#0e2c4d'>"+name+"</font>"));
            ab.setTitle(name);
        }

        mWebView= (WebView) findViewById(R.id.webview);
        startWebView("http://52.40.5.175");

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

    }

    private void startWebView(String url) {


        //Create new webview Client to show progress dialog
        //When opening a url or click on link

        mWebView.setWebViewClient(new WebViewClient() {
            ProgressDialog progressDialog;

            //If you will not use this method url links are opeen in new brower not in webview
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            //Show loader on url load
            public void onLoadResource (WebView view, String url) {
                if (progressDialog == null) {
                    // in standard case YourActivity.this
                    progressDialog = new ProgressDialog(DataMain.this);
                    progressDialog.setMessage("Loading...");
                    progressDialog.show();
                }
            }
            public void onPageFinished(WebView view, String url) {
                try{
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                        progressDialog = null;
                    }
                }catch(Exception exception){
                    exception.printStackTrace();
                }
            }

        });

        // Javascript inabled on webview
        mWebView.getSettings().setJavaScriptEnabled(true);

        // Other webview options
        /*
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        webView.setScrollbarFadingEnabled(false);
        webView.getSettings().setBuiltInZoomControls(true);
        */

        /*
         String summary = "<html><body>You scored <b>192</b> points.</body></html>";
         webview.loadData(summary, "text/html", null);
         */

        //Load url in webview
        mWebView.loadUrl(url);

    }

    // Open previous opened link from history on webview when back button pressed

    @Override
    // Detect when the back button is pressed
    public void onBackPressed() {
        if(mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            // Let the system handle the back button
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == android.R.id.home)
        {
            if(lavazza.equalsIgnoreCase("customer")) {

                finish();
//            Intent intent = new Intent(Kids_Special.this,MainNavigation.class);
//            startActivity(intent);
                Intent intent = new Intent(DataMain.this, MainActivityCustomer.class);
                Bundle bundle = new Bundle();
                bundle.putString("id", cus_id);
                bundle.putString("lavazza", lavazza);
                intent.putExtras(bundle);
                startActivity(intent);
                return true;
            }
            else {
                finish();
//            Intent intent = new Intent(Kids_Special.this,MainNavigation.class);
//            startActivity(intent);
                Intent intent = new Intent(DataMain.this, MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("id", cus_id);
                bundle.putString("lavazza", lavazza);
                intent.putExtras(bundle);
                startActivity(intent);
                return true;
            }
        }

//        if (id == R.id.action_cart) {
//
//            Intent intent=new Intent(Lavazza.this,Cart.class);
//            Bundle bundle=new Bundle();
//            bundle.putString("cus_id",cus_id);
//            intent.putExtras(bundle);
//            startActivity(intent);
//
//            return true;
//        }
        return super.onOptionsItemSelected(item);
    }


}
