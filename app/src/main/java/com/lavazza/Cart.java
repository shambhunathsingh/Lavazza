package com.lavazza;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
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
import com.lavazza.helper.SQLiteHandler;
import com.lavazza.helper.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class Cart extends AppCompatActivity {

    private static final String TAG = Cart.class.getSimpleName();
//    public static String [] name={"CFR80521"};
    public static String [] name;
    public static String [] pname1;

//    public static String [] quantity={"1"};
    public static String [] quantity;
    public static String [] pquantity1;

    public static String [] uom;
    public static String [] puom1;

//    public static int [] image={R.drawable.lavazza1};
    public static String [] image;


    TextView date;
    LinearLayout cont_shop,checkout;
//    Spinner time;
    TextView time;
    int mYear, mMonth, mDay, mHour, mMinute;

    private SQLiteHandler db;
    String cus_id;

    RecyclerView recyclerView;
    ArrayList<Cart_Data> androidVersions;

    private SessionManager session;
    //    private SQLiteHandler db;
    private ProgressDialog pDialog;

    String p_date,p_time;
    String lavazza;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle bundle =getIntent().getExtras();
        cus_id=bundle.getString("cus_id");
        lavazza=bundle.getString("lavazza");

        db = new SQLiteHandler(Cart.this);

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        ArrayList<String> name1=db.getName(cus_id);
        name=new String[name1.size()];
        name=name1.toArray(name);

        ArrayList<String> quan1=db.getQuantity(cus_id);
        quantity=new String[quan1.size()];
        quantity=quan1.toArray(quantity);

        ArrayList<String> uom1=db.getUom(cus_id);
        uom=new String[uom1.size()];
        uom=uom1.toArray(uom);

//        ArrayList<Integer> image1=db.getImage();
//        image= new Integer[image1.size()];
//        image=image1.toArray(image);
//        image=ArrayUtils.toPrimitive(images);


        ////////for int image
//        ArrayList<Integer> image1=db.getImage();
//        image= new int[image1.size()];
//        for(int i = 0; i < image1.size(); i++) {
//            if (image1.get(i) != null) {
//                image[i] = image1.get(i);
////                Toast.makeText(this, image[i]+"", Toast.LENGTH_SHORT).show();
//            }
//        }
////////////

        ArrayList<String> image1=db.getImage(cus_id);
        image=new String[image1.size()];
        image=image1.toArray(image);

        date= (TextView) findViewById(R.id.r_date);
//        time= (Spinner) findViewById(R.id.time);
        time= (TextView) findViewById(R.id.d_date);

        final android.support.v7.app.ActionBar ab = getSupportActionBar();
        if(ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
//            ab.setTitle(Html.fromHtml("<font color='#0e2c4d'>Cart</font>"));
//            ab.setTitle(name);
        }
        initViews();

        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        String date1 = mDay + "-" + (mMonth + 1) + "-" + mYear;
//        Toast.makeText(this, "", Toast.LENGTH_SHORT).show();

        date.setText(date1);

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(view.getContext(),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                    date.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
//                                date=dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
//                                Toast.makeText(context, date+"", Toast.LENGTH_SHORT).show();
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();

            }
        });

        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(view.getContext(),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                time.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
//                                date=dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
//                                Toast.makeText(context, date+"", Toast.LENGTH_SHORT).show();
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();

            }
        });


//        String[] time1={"Select TIme","8AM-10AM","10AM-12PM","12PM-2PM","2PM-5PM"};
//        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(Cart.this, R.layout.simple_spinner_item, R.id.item, time1);
////        CustomAdapter adapter = new CustomAdapter(context,quan1);
//        time.setAdapter(adapter2);
//        time.setPrompt("Select Time");
//        time.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
////                String[] quan1={"100pack","200packs","300packs","400packs"};
////                CustomAdapter adapter = new CustomAdapter(view.getContext(),quan1);
////                viewHolder.quantity.setAdapter(adapter);
//                p_time=time.getSelectedItem().toString();
////                time=viewHolder.time.getSelectedItem().toString();
////                Toast.makeText(context, time_item+"", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });


        cont_shop= (LinearLayout) findViewById(R.id.cont_shop);
        checkout= (LinearLayout) findViewById(R.id.checkout);

        cont_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Cart.this,Product.class);
                Bundle bundle1=new Bundle();
                bundle1.putString("cus_id",cus_id);
                bundle1.putString("lavazza",lavazza);
                intent.putExtras(bundle1);
                startActivity(intent);
                finish();
            }
        });

        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String c_id=cus_id;

                ArrayList<String> name1=db.getName(cus_id);
                pname1=new String[name1.size()];
                pname1=name1.toArray(pname1);

                ArrayList<String> quan1=db.getQuantity(cus_id);
                pquantity1=new String[quan1.size()];
                pquantity1=quan1.toArray(pquantity1);

                ArrayList<String> uom1=db.getUom(cus_id);
                puom1=new String[uom1.size()];
                puom1=uom1.toArray(puom1);


                p_date=date.getText().toString();
                p_time=time.getText().toString();
                if(p_date.equalsIgnoreCase("Request Date"))
                {
                    Toast.makeText(Cart.this, "Select Request Date", Toast.LENGTH_SHORT).show();
                }
                else if(p_time.equalsIgnoreCase("Delivery Date"))
                {
                    Toast.makeText(Cart.this, "Select Delivery Date", Toast.LENGTH_SHORT).show();
                }
                else {

                    for (int i = 0; i < pname1.length; i++) {
                        registerUser(pname1[i], pquantity1[i], puom1[i], c_id, p_date, p_time);
//                        Toast.makeText(Cart.this, pname1[i]+","+pquantity1[i]+","+c_id+","+p_date+","+p_time, Toast.LENGTH_SHORT).show();
                    }

                    AlertDialog.Builder alert = new AlertDialog.Builder(Cart.this);
                    alert.setTitle("Order Confirmed");
                    alert.setMessage("Thanks for your purchase.");

//                    LayoutInflater inflater = BMI.this.getLayoutInflater();
//                    final View dialogView = inflater.inflate(R.layout.bmi_result, null);
//                    alert.setView(dialogView);
//// ...Irrelevant code for customizing the buttons and title
////                LayoutInflater inflater = getLayoutInflater();
////                View dialogView = inflater.inflate(R.layout.bmi_result, null);
////                dialogBuilder.setContentView(dialogView);
//
//                    result1 = (TextView) dialogView.findViewById(R.id.result1);
//                    result2 = (TextView) dialogView.findViewById(R.id.result2);
////                result= (Button) dialogView.findViewById(R.id.result);
//
//                    result1.setText(String.valueOf(bmiValue));
//                    result2.setText(bmiInterpretation);
                    alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            db.deleteUsers(cus_id);
                            Intent intent =new Intent(Cart.this,MainActivity.class);
                            intent.putExtra("id",cus_id);
                            intent.putExtra("lavazza",lavazza);
                            startActivity(intent);

                        }
                    });
                    alert.show();

                }

//                db.deleteUsers(cus_id);

//                Cart_Data data=androidVersions.get(androidVersions.size());
//                String m=data.getQuantity();
//
//                Toast.makeText(Cart.this,m+"", Toast.LENGTH_SHORT).show();

//                for (int i=0;i<pname1.length;i++)
//                {
//                    Toast.makeText(Cart.this, pname1[i]+","+pquantity1[i]+","+puom1[i], Toast.LENGTH_SHORT).show();
//                }


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

    private void initViews() {
//        final RippleView rippleView = (RippleView) findViewById(R.id.rect);
        recyclerView = (RecyclerView) findViewById(R.id.card_recycler_view);
        recyclerView.setHasFixedSize(true);
//        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 2);
//        RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));

//        getData();

        androidVersions = prepareData();
        CartAdapter adapter = new CartAdapter(Cart.this, androidVersions, cus_id);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
//                String name=getString(R.string.title_activity_client_info);

//                switch (position) {
//                    case 0:
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
//                        break;
//                    default:
//                        break;
//                }
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    private ArrayList<Cart_Data> prepareData(){
//        String [] person={distance};
        ArrayList<Cart_Data> android_version = new ArrayList<>();
        for(int i=0;i<name.length;i++){
            Cart_Data androidVersion = new Cart_Data();
            androidVersion.setName(name[i]);
//            androidVersion.setDate(date[i]);
//            androidVersion.setTime(time[i]);
            androidVersion.setQuantity(quantity[i]);
            androidVersion.setUom(uom[i]);
            androidVersion.setImage(image[i]);
//            Toast.makeText(this, image[i]+"", Toast.LENGTH_SHORT).show();
            android_version.add(androidVersion);
        }
        return android_version;
    }

    private void registerUser(final String name, final String quantity, final String uom, final String c_id, final String p_date, final String p_time) {
        // Tag used to cancel the request
//        Toast.makeText(getApplicationContext(), "Marked Present", Toast.LENGTH_SHORT).show();
        String tag_string_req = "req_register";
//        Toast.makeText(FullScannerActivity.this, "Register", Toast.LENGTH_SHORT).show();
        pDialog.setMessage("Adding ...");
//        showDialog();
//        progressBar.setVisibility(View.);


        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_REGISTER, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Register Response: " + response.toString());
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
                Log.e(TAG, "Registration Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
//                hideDialog();
            }
        }) {

            @Override
            protected java.util.Map<String, String> getParams() {
                // Posting params to register url
                java.util.Map<String, String> params = new HashMap<String, String>();
                params.put("p_serial", name);
                params.put("quantity", quantity );
                params.put("uom", uom);
                params.put("c_id", c_id );
                params.put("p_date", p_date );
                params.put("p_time", p_time );


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

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.cart_delete, menu);
//        return true;
//
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == android.R.id.home)
        {

            finish();
            Intent intent = new Intent(Cart.this,Product.class);
            Bundle bundle=new Bundle();
            bundle.putString("cus_id",cus_id);
            bundle.putString("lavazza",lavazza);
            intent.putExtras(bundle);
            startActivity(intent);
            return true;
        }
//        if(id == R.id.action_delete)
//        {
//            db.deleteUsers(cus_id);
//
//            Intent intent = getIntent();
//            overridePendingTransition(0, 0);
//            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//            finish();
//            overridePendingTransition(0, 0);
//            startActivity(intent);
//
//            return true;
//        }
        return super.onOptionsItemSelected(item);
    }


}
