package com.lavazza;

import android.animation.ObjectAnimator;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lavazza.app.AppConfig;
import com.lavazza.app.AppController;
import com.lavazza.app.RequestHandler;
import com.lavazza.helper.SQLiteHandler;
import com.rey.material.widget.ProgressView;

import java.util.HashMap;

import static android.R.attr.rating;

public class FeedBack extends AppCompatActivity implements ConnectivityReceiver.ConnectivityReceiverListener{

    String cus_id,lavazza;
    String rate_coffee = "0.0",rate_service = "0.0",rate_total = "0.0";
    EditText thought;
    Button submit,
            rateCofBtn1,rateCofBtn2,rateCofBtn3,rateCofBtn4,rateCofBtn5,rateCofBtn6,rateCofBtn7,rateCofBtn8,rateCofBtn9,
            rateSerBtn1,rateSerBtn2,rateSerBtn3,rateSerBtn4,rateSerBtn5,rateSerBtn6,rateSerBtn7,rateSerBtn8,rateSerBtn9;
            /*rateAllBtn1,rateAllBtn2,rateAllBtn3,rateAllBtn4,rateAllBtn5,rateAllBtn6,rateAllBtn7,rateAllBtn8,rateAllBtn9*/
    TextView cofRatVal;
    TextView serRatVal;
    //TextView allRatVal;

    ProgressView progressBar;
    int badge=0;
    SQLiteHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        progressBar = (ProgressView)findViewById(R.id.loading_spinner);
        thought = (EditText)findViewById(R.id.thought);

        cofRatVal = (TextView)findViewById(R.id.cofRatVal);
        serRatVal = (TextView)findViewById(R.id.serRatVal);
        //allRatVal = (TextView)findViewById(R.id.allRatVal);

        rateCofBtn1 = (Button)findViewById(R.id.rateCofBtn1);
        rateCofBtn2 = (Button)findViewById(R.id.rateCofBtn2);
        rateCofBtn3 = (Button)findViewById(R.id.rateCofBtn3);
        rateCofBtn4 = (Button)findViewById(R.id.rateCofBtn4);
        rateCofBtn5 = (Button)findViewById(R.id.rateCofBtn5);
        rateCofBtn6 = (Button)findViewById(R.id.rateCofBtn6);
        rateCofBtn7 = (Button)findViewById(R.id.rateCofBtn7);
        rateCofBtn8 = (Button)findViewById(R.id.rateCofBtn8);
        rateCofBtn9 = (Button)findViewById(R.id.rateCofBtn9);

        rateSerBtn1 = (Button)findViewById(R.id.rateSerBtn1);
        rateSerBtn2 = (Button)findViewById(R.id.rateSerBtn2);
        rateSerBtn3 = (Button)findViewById(R.id.rateSerBtn3);
        rateSerBtn4 = (Button)findViewById(R.id.rateSerBtn4);
        rateSerBtn5 = (Button)findViewById(R.id.rateSerBtn5);
        rateSerBtn6 = (Button)findViewById(R.id.rateSerBtn6);
        rateSerBtn7 = (Button)findViewById(R.id.rateSerBtn7);
        rateSerBtn8 = (Button)findViewById(R.id.rateSerBtn8);
        rateSerBtn9 = (Button)findViewById(R.id.rateSerBtn9);

/*        rateAllBtn1 = (Button)findViewById(R.id.rateAllBtn1);
        rateAllBtn2 = (Button)findViewById(R.id.rateAllBtn2);
        rateAllBtn3 = (Button)findViewById(R.id.rateAllBtn3);
        rateAllBtn4 = (Button)findViewById(R.id.rateAllBtn4);
        rateAllBtn5 = (Button)findViewById(R.id.rateAllBtn5);
        rateAllBtn6 = (Button)findViewById(R.id.rateAllBtn6);
        rateAllBtn7 = (Button)findViewById(R.id.rateAllBtn7);
        rateAllBtn8 = (Button)findViewById(R.id.rateAllBtn8);
        rateAllBtn9 = (Button)findViewById(R.id.rateAllBtn9);*/

        submit = (Button)findViewById(R.id.submit);

        setSupportActionBar(toolbar);


        Bundle bundle =getIntent().getExtras();
        cus_id=bundle.getString("cus_id");
        lavazza=bundle.getString("lavazza");

        checkConnection();

        db=new SQLiteHandler(FeedBack.this);
//        badgeCount = db.cartcount(cus_id);
//        Toast.makeText(this, db.getCartCount(cus_id)+"", Toast.LENGTH_SHORT).show();
        badge = db.getCartCount(cus_id);

//Starting of Rating Button Operations
        //Starting of Coffee Rating Button Operations
        rateCofBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rateCofBtn1.setBackgroundColor(Color.parseColor("#CC0000"));
                rateCofBtn2.setBackgroundColor(Color.parseColor("#FFFFFF"));
                rateCofBtn3.setBackgroundColor(Color.parseColor("#FFFFFF"));
                rateCofBtn4.setBackgroundColor(Color.parseColor("#FFFFFF"));
                rateCofBtn5.setBackgroundColor(Color.parseColor("#FFFFFF"));
                rateCofBtn6.setBackgroundColor(Color.parseColor("#FFFFFF"));
                rateCofBtn7.setBackgroundColor(Color.parseColor("#FFFFFF"));
                rateCofBtn8.setBackgroundColor(Color.parseColor("#FFFFFF"));
                rateCofBtn9.setBackgroundColor(Color.parseColor("#FFFFFF"));

                rate_coffee = "1.0";
                cofRatVal.setText(rate_coffee);
            }
        });
        rateCofBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rateCofBtn1.setBackgroundColor(Color.parseColor("#CC0000"));
                rateCofBtn2.setBackgroundColor(Color.parseColor("#FF3333"));
                rateCofBtn3.setBackgroundColor(Color.parseColor("#FFFFFF"));
                rateCofBtn4.setBackgroundColor(Color.parseColor("#FFFFFF"));
                rateCofBtn5.setBackgroundColor(Color.parseColor("#FFFFFF"));
                rateCofBtn6.setBackgroundColor(Color.parseColor("#FFFFFF"));
                rateCofBtn7.setBackgroundColor(Color.parseColor("#FFFFFF"));
                rateCofBtn8.setBackgroundColor(Color.parseColor("#FFFFFF"));
                rateCofBtn9.setBackgroundColor(Color.parseColor("#FFFFFF"));
                rate_coffee = "1.5";
                cofRatVal.setText(rate_coffee);
            }
        });
        rateCofBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rateCofBtn1.setBackgroundColor(Color.parseColor("#CC0000"));
                rateCofBtn2.setBackgroundColor(Color.parseColor("#FF3333"));
                rateCofBtn3.setBackgroundColor(Color.parseColor("#FF8000"));
                rateCofBtn4.setBackgroundColor(Color.parseColor("#FFFFFF"));
                rateCofBtn5.setBackgroundColor(Color.parseColor("#FFFFFF"));
                rateCofBtn6.setBackgroundColor(Color.parseColor("#FFFFFF"));
                rateCofBtn7.setBackgroundColor(Color.parseColor("#FFFFFF"));
                rateCofBtn8.setBackgroundColor(Color.parseColor("#FFFFFF"));
                rateCofBtn9.setBackgroundColor(Color.parseColor("#FFFFFF"));
                rate_coffee = "2.0";
                cofRatVal.setText(rate_coffee);
            }
        });
        rateCofBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rateCofBtn1.setBackgroundColor(Color.parseColor("#CC0000"));
                rateCofBtn2.setBackgroundColor(Color.parseColor("#FF3333"));
                rateCofBtn3.setBackgroundColor(Color.parseColor("#FF8000"));
                rateCofBtn4.setBackgroundColor(Color.parseColor("#FFFF66"));
                rateCofBtn5.setBackgroundColor(Color.parseColor("#FFFFFF"));
                rateCofBtn6.setBackgroundColor(Color.parseColor("#FFFFFF"));
                rateCofBtn7.setBackgroundColor(Color.parseColor("#FFFFFF"));
                rateCofBtn8.setBackgroundColor(Color.parseColor("#FFFFFF"));
                rateCofBtn9.setBackgroundColor(Color.parseColor("#FFFFFF"));
                rate_coffee = "2.5";
                cofRatVal.setText(rate_coffee);
            }
        });
        rateCofBtn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rateCofBtn1.setBackgroundColor(Color.parseColor("#CC0000"));
                rateCofBtn2.setBackgroundColor(Color.parseColor("#FF3333"));
                rateCofBtn3.setBackgroundColor(Color.parseColor("#FF8000"));
                rateCofBtn4.setBackgroundColor(Color.parseColor("#FFFF66"));
                rateCofBtn5.setBackgroundColor(Color.parseColor("#CCFF99"));
                rateCofBtn6.setBackgroundColor(Color.parseColor("#FFFFFF"));
                rateCofBtn7.setBackgroundColor(Color.parseColor("#FFFFFF"));
                rateCofBtn8.setBackgroundColor(Color.parseColor("#FFFFFF"));
                rateCofBtn9.setBackgroundColor(Color.parseColor("#FFFFFF"));
                rate_coffee = "3.0";
                cofRatVal.setText(rate_coffee);
            }
        });
        rateCofBtn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rateCofBtn1.setBackgroundColor(Color.parseColor("#CC0000"));
                rateCofBtn2.setBackgroundColor(Color.parseColor("#FF3333"));
                rateCofBtn3.setBackgroundColor(Color.parseColor("#FF8000"));
                rateCofBtn4.setBackgroundColor(Color.parseColor("#FFFF66"));
                rateCofBtn5.setBackgroundColor(Color.parseColor("#CCFF99"));
                rateCofBtn6.setBackgroundColor(Color.parseColor("#66FF66"));
                rateCofBtn7.setBackgroundColor(Color.parseColor("#FFFFFF"));
                rateCofBtn8.setBackgroundColor(Color.parseColor("#FFFFFF"));
                rateCofBtn9.setBackgroundColor(Color.parseColor("#FFFFFF"));
                rate_coffee = "3.5";
                cofRatVal.setText(rate_coffee);
            }
        });
        rateCofBtn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rateCofBtn1.setBackgroundColor(Color.parseColor("#CC0000"));
                rateCofBtn2.setBackgroundColor(Color.parseColor("#FF3333"));
                rateCofBtn3.setBackgroundColor(Color.parseColor("#FF8000"));
                rateCofBtn4.setBackgroundColor(Color.parseColor("#FFFF66"));
                rateCofBtn5.setBackgroundColor(Color.parseColor("#CCFF99"));
                rateCofBtn6.setBackgroundColor(Color.parseColor("#66FF66"));
                rateCofBtn7.setBackgroundColor(Color.parseColor("#00CC00"));
                rateCofBtn8.setBackgroundColor(Color.parseColor("#FFFFFF"));
                rateCofBtn9.setBackgroundColor(Color.parseColor("#FFFFFF"));
                rate_coffee = "4.0";
                cofRatVal.setText(rate_coffee);
            }
        });
        rateCofBtn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rateCofBtn1.setBackgroundColor(Color.parseColor("#CC0000"));
                rateCofBtn2.setBackgroundColor(Color.parseColor("#FF3333"));
                rateCofBtn3.setBackgroundColor(Color.parseColor("#FF8000"));
                rateCofBtn4.setBackgroundColor(Color.parseColor("#FFFF66"));
                rateCofBtn5.setBackgroundColor(Color.parseColor("#CCFF99"));
                rateCofBtn6.setBackgroundColor(Color.parseColor("#66FF66"));
                rateCofBtn7.setBackgroundColor(Color.parseColor("#00CC00"));
                rateCofBtn8.setBackgroundColor(Color.parseColor("#00994C"));
                rateCofBtn9.setBackgroundColor(Color.parseColor("#FFFFFF"));
                rate_coffee = "4.5";
                cofRatVal.setText(rate_coffee);
            }
        });
        rateCofBtn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rateCofBtn1.setBackgroundColor(Color.parseColor("#CC0000"));
                rateCofBtn2.setBackgroundColor(Color.parseColor("#FF3333"));
                rateCofBtn3.setBackgroundColor(Color.parseColor("#FF8000"));
                rateCofBtn4.setBackgroundColor(Color.parseColor("#FFFF66"));
                rateCofBtn5.setBackgroundColor(Color.parseColor("#CCFF99"));
                rateCofBtn6.setBackgroundColor(Color.parseColor("#66FF66"));
                rateCofBtn7.setBackgroundColor(Color.parseColor("#00CC00"));
                rateCofBtn8.setBackgroundColor(Color.parseColor("#00994C"));
                rateCofBtn9.setBackgroundColor(Color.parseColor("#006600"));
                rate_coffee = "5.0";
                cofRatVal.setText(rate_coffee);
            }
        });//Ending of Coffee Rating Button Operations

        //Starting of Service Rating Button Operations
        rateSerBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rateSerBtn1.setBackgroundColor(Color.parseColor("#CC0000"));
                rateSerBtn2.setBackgroundColor(Color.parseColor("#FFFFFF"));
                rateSerBtn3.setBackgroundColor(Color.parseColor("#FFFFFF"));
                rateSerBtn4.setBackgroundColor(Color.parseColor("#FFFFFF"));
                rateSerBtn5.setBackgroundColor(Color.parseColor("#FFFFFF"));
                rateSerBtn6.setBackgroundColor(Color.parseColor("#FFFFFF"));
                rateSerBtn7.setBackgroundColor(Color.parseColor("#FFFFFF"));
                rateSerBtn8.setBackgroundColor(Color.parseColor("#FFFFFF"));
                rateSerBtn9.setBackgroundColor(Color.parseColor("#FFFFFF"));
                rate_service = "1.0";
                serRatVal.setText(rate_service);
            }
        });
        rateSerBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rateSerBtn1.setBackgroundColor(Color.parseColor("#CC0000"));
                rateSerBtn2.setBackgroundColor(Color.parseColor("#FF3333"));
                rateSerBtn3.setBackgroundColor(Color.parseColor("#FFFFFF"));
                rateSerBtn4.setBackgroundColor(Color.parseColor("#FFFFFF"));
                rateSerBtn5.setBackgroundColor(Color.parseColor("#FFFFFF"));
                rateSerBtn6.setBackgroundColor(Color.parseColor("#FFFFFF"));
                rateSerBtn7.setBackgroundColor(Color.parseColor("#FFFFFF"));
                rateSerBtn8.setBackgroundColor(Color.parseColor("#FFFFFF"));
                rateSerBtn9.setBackgroundColor(Color.parseColor("#FFFFFF"));
                rate_service = "1.5";
                serRatVal.setText(rate_service);
            }
        });
        rateSerBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rateSerBtn1.setBackgroundColor(Color.parseColor("#CC0000"));
                rateSerBtn2.setBackgroundColor(Color.parseColor("#FF3333"));
                rateSerBtn3.setBackgroundColor(Color.parseColor("#FF8000"));
                rateSerBtn4.setBackgroundColor(Color.parseColor("#FFFFFF"));
                rateSerBtn5.setBackgroundColor(Color.parseColor("#FFFFFF"));
                rateSerBtn6.setBackgroundColor(Color.parseColor("#FFFFFF"));
                rateSerBtn7.setBackgroundColor(Color.parseColor("#FFFFFF"));
                rateSerBtn8.setBackgroundColor(Color.parseColor("#FFFFFF"));
                rateSerBtn9.setBackgroundColor(Color.parseColor("#FFFFFF"));
                rate_service = "2.0";
                serRatVal.setText(rate_service);
            }
        });
        rateSerBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rateSerBtn1.setBackgroundColor(Color.parseColor("#CC0000"));
                rateSerBtn2.setBackgroundColor(Color.parseColor("#FF3333"));
                rateSerBtn3.setBackgroundColor(Color.parseColor("#FF8000"));
                rateSerBtn4.setBackgroundColor(Color.parseColor("#FFFF66"));
                rateSerBtn5.setBackgroundColor(Color.parseColor("#FFFFFF"));
                rateSerBtn6.setBackgroundColor(Color.parseColor("#FFFFFF"));
                rateSerBtn7.setBackgroundColor(Color.parseColor("#FFFFFF"));
                rateSerBtn8.setBackgroundColor(Color.parseColor("#FFFFFF"));
                rateSerBtn9.setBackgroundColor(Color.parseColor("#FFFFFF"));
                rate_service = "2.5";
                serRatVal.setText(rate_service);
            }
        });
        rateSerBtn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rateSerBtn1.setBackgroundColor(Color.parseColor("#CC0000"));
                rateSerBtn2.setBackgroundColor(Color.parseColor("#FF3333"));
                rateSerBtn3.setBackgroundColor(Color.parseColor("#FF8000"));
                rateSerBtn4.setBackgroundColor(Color.parseColor("#FFFF66"));
                rateSerBtn5.setBackgroundColor(Color.parseColor("#CCFF99"));
                rateSerBtn6.setBackgroundColor(Color.parseColor("#FFFFFF"));
                rateSerBtn7.setBackgroundColor(Color.parseColor("#FFFFFF"));
                rateSerBtn8.setBackgroundColor(Color.parseColor("#FFFFFF"));
                rateSerBtn9.setBackgroundColor(Color.parseColor("#FFFFFF"));
                rate_service = "3.0";
                serRatVal.setText(rate_service);
            }
        });
        rateSerBtn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rateSerBtn1.setBackgroundColor(Color.parseColor("#CC0000"));
                rateSerBtn2.setBackgroundColor(Color.parseColor("#FF3333"));
                rateSerBtn3.setBackgroundColor(Color.parseColor("#FF8000"));
                rateSerBtn4.setBackgroundColor(Color.parseColor("#FFFF66"));
                rateSerBtn5.setBackgroundColor(Color.parseColor("#CCFF99"));
                rateSerBtn6.setBackgroundColor(Color.parseColor("#66FF66"));
                rateSerBtn7.setBackgroundColor(Color.parseColor("#FFFFFF"));
                rateSerBtn8.setBackgroundColor(Color.parseColor("#FFFFFF"));
                rateSerBtn9.setBackgroundColor(Color.parseColor("#FFFFFF"));
                rate_service = "3.5";
                serRatVal.setText(rate_service);
            }
        });
        rateSerBtn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rateSerBtn1.setBackgroundColor(Color.parseColor("#CC0000"));
                rateSerBtn2.setBackgroundColor(Color.parseColor("#FF3333"));
                rateSerBtn3.setBackgroundColor(Color.parseColor("#FF8000"));
                rateSerBtn4.setBackgroundColor(Color.parseColor("#FFFF66"));
                rateSerBtn5.setBackgroundColor(Color.parseColor("#CCFF99"));
                rateSerBtn6.setBackgroundColor(Color.parseColor("#66FF66"));
                rateSerBtn7.setBackgroundColor(Color.parseColor("#00CC00"));
                rateSerBtn8.setBackgroundColor(Color.parseColor("#FFFFFF"));
                rateSerBtn9.setBackgroundColor(Color.parseColor("#FFFFFF"));
                rate_service = "4.0";
                serRatVal.setText(rate_service);
            }
        });
        rateSerBtn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rateSerBtn1.setBackgroundColor(Color.parseColor("#CC0000"));
                rateSerBtn2.setBackgroundColor(Color.parseColor("#FF3333"));
                rateSerBtn3.setBackgroundColor(Color.parseColor("#FF8000"));
                rateSerBtn4.setBackgroundColor(Color.parseColor("#FFFF66"));
                rateSerBtn5.setBackgroundColor(Color.parseColor("#CCFF99"));
                rateSerBtn6.setBackgroundColor(Color.parseColor("#66FF66"));
                rateSerBtn7.setBackgroundColor(Color.parseColor("#00CC00"));
                rateSerBtn8.setBackgroundColor(Color.parseColor("#00994C"));
                rateSerBtn9.setBackgroundColor(Color.parseColor("#FFFFFF"));
                rate_service = "4.5";
                serRatVal.setText(rate_service);
            }
        });
        rateSerBtn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rateSerBtn1.setBackgroundColor(Color.parseColor("#CC0000"));
                rateSerBtn2.setBackgroundColor(Color.parseColor("#FF3333"));
                rateSerBtn3.setBackgroundColor(Color.parseColor("#FF8000"));
                rateSerBtn4.setBackgroundColor(Color.parseColor("#FFFF66"));
                rateSerBtn5.setBackgroundColor(Color.parseColor("#CCFF99"));
                rateSerBtn6.setBackgroundColor(Color.parseColor("#66FF66"));
                rateSerBtn7.setBackgroundColor(Color.parseColor("#00CC00"));
                rateSerBtn8.setBackgroundColor(Color.parseColor("#00994C"));
                rateSerBtn9.setBackgroundColor(Color.parseColor("#006600"));
                rate_service = "5.0";
                serRatVal.setText(rate_service);
            }
        });//Ending of Service Rating Button Operations


/*        //Starting of Overall Rating Button Operations
        rateAllBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rateAllBtn1.setBackgroundColor(0xCC0000);
                rate_total = "1.0";
                allRatVal.setText(rate_total);
            }
        });
        rateAllBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rateAllBtn2.setBackgroundColor(0xFF3333);
                rate_total = "1.5";
                allRatVal.setText(rate_total);
            }
        });
        rateAllBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rateAllBtn3.setBackgroundColor(0xFF8000);
                rate_total = "2.0";
                allRatVal.setText(rate_total);
            }
        });
        rateAllBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rateAllBtn4.setBackgroundColor(0xFFFF66);
                rate_total = "2.5";
                allRatVal.setText(rate_total);
            }
        });
        rateAllBtn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rateAllBtn5.setBackgroundColor(0xCCFF99);
                rate_total = "3.0";
                allRatVal.setText(rate_total);
            }
        });
        rateAllBtn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rateAllBtn6.setBackgroundColor(0x66FF66);
                rate_total = "3.5";
                allRatVal.setText(rate_total);
            }
        });
        rateAllBtn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rateAllBtn7.setBackgroundColor(0x00CC00);
                rate_total = "4.0";
                allRatVal.setText(rate_total);
            }
        });
        rateAllBtn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rateAllBtn8.setBackgroundColor(0x00994C);
                rate_total = "4.5";
                allRatVal.setText(rate_total);
            }
        });
        rateAllBtn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rateAllBtn9.setBackgroundColor(0x006600);
                rate_total = "5.0";
                allRatVal.setText(rate_total);
            }
        });//Ending of Overall Rating Button Operations*/

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String thought1=thought.getText().toString().trim();
                String cus_id1=cus_id;

                if(rate_coffee.equalsIgnoreCase("0.0"))
                {
                    Toast.makeText(FeedBack.this, "Please give Coffee Product Rating", Toast.LENGTH_SHORT).show();
                }
                else if(rate_service.equalsIgnoreCase("0.0"))
                {
                    Toast.makeText(FeedBack.this, "Please give Services Rating", Toast.LENGTH_SHORT).show();
                }
                else if(thought1.isEmpty())
                {
                    Toast.makeText(FeedBack.this, "Please Write Your Thoughts", Toast.LENGTH_SHORT).show();
                }
                else if(rate_total.equalsIgnoreCase("0.0"))
                {
                    Toast.makeText(FeedBack.this, "Please give Overall Rating", Toast.LENGTH_SHORT).show();
                }
                else
                {
//                    Toast.makeText(FeedBack.this, product1+","+service1+","+rating1, Toast.LENGTH_SHORT).show();
                    register(cus_id1,rate_coffee,rate_service,thought1,rate_total);

                }

            }
        });


        final android.support.v7.app.ActionBar ab = getSupportActionBar();
        if(ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
//            ab.setIcon(R.drawable.logo_back);
//            ab.setTitle(Html.fromHtml("<font color='#0e2c4d'>Feedback</font>"));
            ab.setTitle("");
        }

//        RatingBar myRatingBar = (RatingBar) findViewById(R.id.rate1);
//        float current = myRatingBar.getRating();
//
//        ObjectAnimator anim = ObjectAnimator.ofFloat(myRatingBar, "rating", current, 5f);
//        anim.setDuration(1000);
//        anim.start();

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
            Toast.makeText(FeedBack.this, message, Toast.LENGTH_SHORT).show();
//            Toast toast=Toast.makeText(this,"Toast:Gravity.TOP",Toast.LENGTH_SHORT);
//            toast.setGravity(Gravity.CENTER,0,0);
//            toast.setView(viewLayout);
//            toast.show();

//            color = Color.RED;

        }


    }

    private void register(final String cus_id, final String product_rating, final String service_rating, final String thoughts, final String overall_rating) {
        class RegisterUser extends AsyncTask<String, Void, String> {
            ProgressDialog loading;



            @Override
            protected void onPreExecute() {
                super.onPreExecute();
//                loading = ProgressDialog.show(FeedBack.this, "Please Wait",null, true, true);
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
//                loading.dismiss();
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
/*
                coffe_product.setRating(0);
                service.setRating(0);
                rating.setRating(0);
                thought.setText("");*/
            }

            @Override
            protected String doInBackground(String... params) {

                HashMap<String, String> data = new HashMap<String,String>();
                data.put("cus_id",cus_id);
                data.put("product_rating",product_rating);
                data.put("service_rating",service_rating);
                data.put("thoughts",thoughts);
                data.put("overall_rating",overall_rating);

                RequestHandler rh = new RequestHandler();
//                String s = rh.sendGetRequestParam(AppConfig.URL_GET_USER_ALL, catid, subcatid);
                String s = rh.sendPostRequest(AppConfig.URL_FEEDBACK, data);
                return s;

            }
        }

        RegisterUser ru = new RegisterUser();
        ru.execute();

    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//
//        if(id == android.R.id.home)
//        {
//            if(lavazza.equalsIgnoreCase("customer")) {
//                finish();
//                Intent intent = new Intent(FeedBack.this, MainActivityCustomer.class);
//                Bundle bundle = new Bundle();
//                bundle.putString("id", cus_id);
//                bundle.putString("lavazza", lavazza);
//                intent.putExtras(bundle);
//                startActivity(intent);
//                return true;
//            }
//            else
//            {
//                finish();
//                Intent intent = new Intent(FeedBack.this, MainActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putString("id", cus_id);
//                bundle.putString("lavazza", lavazza);
//                intent.putExtras(bundle);
//                startActivity(intent);
//                return true;
//
//            }
//        }
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//        getMenuInflater().inflate(R.menu.feedback_menu, menu);
        getMenuInflater().inflate(R.menu.main, menu);
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

                Intent intent=new Intent(FeedBack.this,Cart.class);
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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        if(id == android.R.id.home)
        {
            if(lavazza.equalsIgnoreCase("customer")) {
                finish();
                Intent intent = new Intent(FeedBack.this, MainActivityCustomer.class);
                Bundle bundle = new Bundle();
                bundle.putString("id", cus_id);
                bundle.putString("lavazza", lavazza);
                intent.putExtras(bundle);
                startActivity(intent);
                return true;
            }
            else
            {
                finish();
                Intent intent = new Intent(FeedBack.this, MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("id", cus_id);
                bundle.putString("lavazza", lavazza);
                intent.putExtras(bundle);
                startActivity(intent);
                return true;

            }
        }
       else  if (id == R.id.action_cart) {

            Intent intent=new Intent(FeedBack.this,Cart.class);
            Bundle bundle=new Bundle();
            bundle.putString("cus_id",cus_id);
            intent.putExtra("lavazza",lavazza);
            intent.putExtras(bundle);
            startActivity(intent);

            return true;
        }
        else if (id == R.id.user) {

            Intent intent=new Intent(FeedBack.this,CustomerPortal.class);
            Bundle bundle=new Bundle();
            bundle.putString("cus_id",cus_id);
            intent.putExtra("lavazza",lavazza);
            intent.putExtras(bundle);
            startActivity(intent);

            return true;
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
