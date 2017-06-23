package com.lavazza;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lavazza.helper.SQLiteHandler;

public class Lavazza extends AppCompatActivity {

    WebView mWebView;
    String url1,name,cus_id,lavazza;

    int badge=0;

    SQLiteHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lavazza);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle bundle=getIntent().getExtras();
        name=bundle.getString("name");
        url1=bundle.getString("url");
        cus_id=bundle.getString("cus_id");
        lavazza=bundle.getString("lavazza");



        db=new SQLiteHandler(Lavazza.this);
//        badgeCount = db.cartcount(cus_id);
//        Toast.makeText(this, db.getCartCount(cus_id)+"", Toast.LENGTH_SHORT).show();
        badge = db.getCartCount(cus_id);

        final android.support.v7.app.ActionBar ab = getSupportActionBar();
        if(ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setTitle(name);
//            ab.setTitle(Html.fromHtml("<font color='#0e2c4d'>"+name+"</font>"));
        }

        mWebView= (WebView) findViewById(R.id.webview);
        startWebView(url1);
        mWebView.getSettings().setBuiltInZoomControls(true);
        mWebView.getSettings().setDisplayZoomControls(false);

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
                    progressDialog = new ProgressDialog(Lavazza.this);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.cart_main, menu);
//        return true;

        getMenuInflater().inflate(R.menu.cart_main, menu);
//        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem item = menu.findItem(R.id.action_cart);
//        MenuItemCompat.setActionView(item, R.layout.badge_main);
        MenuItemCompat.setActionView(item, R.layout.badge);
        RelativeLayout notifCount = (RelativeLayout) MenuItemCompat.getActionView(item);

        TextView tv = (TextView) notifCount.findViewById(R.id.actionbar_notifcation_textview);
        tv.setText(String.valueOf(badge));

        ImageView imag= (ImageView) notifCount.findViewById(R.id.image);
        imag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(Lavazza.this,Cart.class);
                Bundle bundle=new Bundle();
                bundle.putString("cus_id",cus_id);
                intent.putExtra("lavazza",lavazza);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == android.R.id.home)
        {
            if(lavazza.equalsIgnoreCase("customer")) {
                finish();
                Intent intent = new Intent(Lavazza.this, MainActivityCustomer.class);
                Bundle bundle = new Bundle();
                bundle.putString("id", cus_id);
                intent.putExtra("lavazza", lavazza);
                intent.putExtras(bundle);
                startActivity(intent);
                return true;
            }
            else
            {
                finish();
                Intent intent = new Intent(Lavazza.this, MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("id", cus_id);
                intent.putExtra("lavazza", lavazza);
                intent.putExtras(bundle);
                startActivity(intent);
                return true;
            }
        }

        if (id == R.id.action_cart) {

            Intent intent=new Intent(Lavazza.this,Cart.class);
            Bundle bundle=new Bundle();
            bundle.putString("cus_id",cus_id);
            intent.putExtras(bundle);
            startActivity(intent);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
