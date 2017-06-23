package com.lavazza;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.lavazza.app.AppConfig;
import com.lavazza.app.RequestHandler;
import com.rey.material.widget.ProgressView;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class LiveSalesSE extends AppCompatActivity implements Spinner.OnItemSelectedListener, ConnectivityReceiver.ConnectivityReceiverListener {

    private Spinner spinner,machine_sno;
    String cus_id,time,number;
    //An ArrayList for Spinner Items
    private ArrayList<String> livesale;
    private ArrayList<String> machine;

    //JSON Array
    private JSONArray result;

    RecyclerView recyclerView;

    private String JSON_STRING;

    String m_sno;
    String cus_id1,lavazza;

    ProgressView progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_sales_se);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        checkConnection();

        Bundle bundle =getIntent().getExtras();
        cus_id1=bundle.getString("cus_id");
        lavazza=bundle.getString("lavazza");




        recyclerView = (RecyclerView) findViewById(R.id.card_recycler_view);
        progressBar= (ProgressView) findViewById(R.id.loading_spinner);
//        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        final android.support.v7.app.ActionBar ab = getSupportActionBar();
        if(ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
//            ab.setTitle(Html.fromHtml("<font color='#0e2c4d'>Live Sales</font>"));
//            ab.setTitle(name);
//            ab.setTitle(Html.fromHtml("<font color='#000'>MCentre</font>"));
        }



        livesale = new ArrayList<String>();
        machine = new ArrayList<String>();
//        livesale.add("Select Customer");

        //Initializing Spinner
//        spinner = (Spinner) findViewById(R.id.customer);
        spinner = (Spinner) findViewById(R.id.spinner1);
//        machine_sno= (Spinner) findViewById(R.id.m_sno);
        machine_sno= (Spinner) findViewById(R.id.spinner2);
        spinner.setOnItemSelectedListener(this);
        getCustomerData();



//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    private void getCustomerData(){
        //Creating a string request
        StringRequest stringRequest = new StringRequest(AppConfig.URL_GET_ALL_CUSTOMER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject j = null;
                        try {
                            //Parsing the fetched Json String to JSON Object
                            j = new JSONObject(response);

                            //Storing the Array of JSON String to our JSON Array
                            result = j.getJSONArray(AppConfig.TAG_JSON_ARRAY);

                            //Calling method getStudents to get the students from the JSON Array
                            getCustomer(result);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        //Creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(LiveSalesSE.this);

        //Adding request to the queue
        requestQueue.add(stringRequest);
    }

    private void getCustomer(JSONArray j){
        //Traversing through all the items in the json array
        for(int i=0;i<j.length();i++){
            try {
                //Getting json object
                JSONObject json = j.getJSONObject(i);

                //Adding the name of the student to array list
                livesale.add(json.getString("name"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        //Setting adapter to show the items in the spinner
//        spinner.setAdapter(new ArrayAdapter<String>(getActivity().getApplication(), android.R.layout.simple_spinner_dropdown_item, students));
        spinner.setAdapter(new ArrayAdapter<String>(LiveSalesSE.this, R.layout.simple_spinner_item2, R.id.item, livesale));


//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(LiveSalesSE.this, android.R.layout.simple_dropdown_item_1line, livesales1);
//
//        spinner.setAdapter(adapter);
    }

    //Method to get cutomer id of a particular position
    private String getCustomerID(int position){
        String id="";
        try {
            //Getting object of given index
            JSONObject json = result.getJSONObject(position);

            //Fetching name from that object
            id = json.getString("id");
            getMachineData(id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //Returning the name
        return id;
    }

    //Doing the same with this method as we did with getCustomerID()
//    private String getNumber(int position){
//        String course="";
//        try {
//            JSONObject json = result.getJSONObject(position);
//            course = json.getString(AppConfig.TAG_NUMBER);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return course;
//    }

    //Doing the same with this method as we did with getCustomerID()
//    private String getTime(int position){
//        String session="";
//        try {
//            JSONObject json = result.getJSONObject(position);
//            session = json.getString(AppConfig.TAG_TIME);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return session;
//    }

    private void getMachineData(final String cus_id){
        //Creating a string request
        class GetData extends AsyncTask<Void,Void,String> {
            ProgressDialog progressDialog;
            JSONObject jsonObject = null;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                machine.clear();
//                progressDialog = ProgressDialog.show(Toy_store.this, "Fetching Data", "Please wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                machine.add("Select");
//                progressDialog.dismiss();
//                progressBar.setVisibility(View.GONE);
                JSON_STRING=s;
//                Toast.makeText(Toy_store.this, JSON_STRING+"", Toast.LENGTH_SHORT).show();
//                Toast.makeText(Retail_Brand_Main.this, s+"", Toast.LENGTH_SHORT).show();

                try {
                    jsonObject = new JSONObject(JSON_STRING);
                    JSONArray result = jsonObject.getJSONArray(AppConfig.TAG_JSON_ARRAY);
                    getMachine(result);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

//                parseJSON(s);
            }

            @Override
            protected String doInBackground(Void... params) {
//                BufferedReader bufferedReader = null;
//                try {
//                    URL url = new URL(AppConfig.URL_GET_ALL);
//                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
//                    StringBuilder sb = new StringBuilder();
//
//                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
//
//                    String json;
//                    while((json = bufferedReader.readLine())!= null){
//                        sb.append(json+"\n");
//                    }
//
//                    return sb.toString().trim();
//
//                }catch(Exception e){
//                    return null;
//                }

                RequestHandler rh = new RequestHandler();
//                String s = rh.sendGetRequestParam(AppConfig.URL_GET_USER_ALL, catid, subcatid);
                String s = rh.sendGetRequestParam(AppConfig.URL_GET_MACHINE_DATA, cus_id);
                return s;
            }
        }
        GetData gd = new GetData();
        gd.execute();
    }

    private void getMachine(JSONArray j){
        //Traversing through all the items in the json array
        for(int i=0;i<j.length();i++){
            try {
                //Getting json object
                JSONObject json = j.getJSONObject(i);

                //Adding the name of the student to array list
                machine.add(json.getString("m_sno"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        //Setting adapter to show the items in the spinner
//        spinner.setAdapter(new ArrayAdapter<String>(getActivity().getApplication(), android.R.layout.simple_spinner_dropdown_item, students));
        machine_sno.setAdapter(new ArrayAdapter<String>(LiveSalesSE.this, R.layout.simple_spinner_item2, R.id.item, machine));
        machine_sno.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                m_sno = machine_sno.getSelectedItem().toString();
                if(m_sno.equalsIgnoreCase("Select"))
                {
//                    initViews();
//                    Toast.makeText(LiveSalesSE.this, "Select the Machine ", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    initViews1(m_sno);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(LiveSalesSE.this, android.R.layout.simple_dropdown_item_1line, livesales1);
//
//        spinner.setAdapter(adapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {


        cus_id=getCustomerID(position);
//        Toast.makeText(this, cus_id+"", Toast.LENGTH_SHORT).show();
        initViews();
//        number=getNumber(position);
//        time=getTime(position);

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    private void initViews() {

//        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(LiveSalesSE.this, 3);
//        RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplication());
        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));

        getData();

        //        ArrayList<LiveData> androidVersions = prepareData();
//

//        LiveDataAdapter adapter = new LiveDataAdapter(getActivity().getApplication(), androidVersions);
//        recyclerView.setAdapter(adapter);
//        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity().getApplication(), recyclerView, new ClickListener() {
//            @Override
//            public void onClick(View view, int position) {
////                String name=getString(R.string.title_activity_client_info);
//
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
//            }
//
//            @Override
//            public void onLongClick(View view, int position) {
//
//            }
//        }));

    }

    private void initViews1(String m_sno) {

//        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(LiveSalesSE.this, 3);
//        RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplication());
        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));

        getSelectedData(m_sno);
//        getData();

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
            Toast.makeText(LiveSalesSE.this, message, Toast.LENGTH_SHORT).show();
//            Toast toast=Toast.makeText(this,"Toast:Gravity.TOP",Toast.LENGTH_SHORT);
//            toast.setGravity(Gravity.CENTER,0,0);
//            toast.setView(viewLayout);
//            toast.show();

//            color = Color.RED;

        }

//        Snackbar snackbar = Snackbar
//                .make(findViewById(R.id.fab), message, Snackbar.LENGTH_LONG);
//
//        View sbView = snackbar.getView();
//        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
//        textView.setTextColor(color);
//        snackbar.show();
    }


    private void getData(){
        final String catid="3";
//        final String cus_id="1";
        class GetData extends AsyncTask<Void,Void,String> {
            ProgressDialog progressDialog;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressBar.setVisibility(View.VISIBLE);
//                progressDialog = ProgressDialog.show(Toy_store.this, "Fetching Data", "Please wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                progressBar.setVisibility(View.GONE);
//                progressDialog.dismiss();
//                progressBar.setVisibility(View.GONE);
                JSON_STRING=s;
//                Toast.makeText(Toy_store.this, JSON_STRING+"", Toast.LENGTH_SHORT).show();
//                Toast.makeText(Retail_Brand_Main.this, s+"", Toast.LENGTH_SHORT).show();

                prepareData(s);
//                parseJSON(s);
            }

            @Override
            protected String doInBackground(Void... params) {
//                BufferedReader bufferedReader = null;
//                try {
//                    URL url = new URL(AppConfig.URL_GET_ALL);
//                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
//                    StringBuilder sb = new StringBuilder();
//
//                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
//
//                    String json;
//                    while((json = bufferedReader.readLine())!= null){
//                        sb.append(json+"\n");
//                    }
//
//                    return sb.toString().trim();
//
//                }catch(Exception e){
//                    return null;
//                }

                RequestHandler rh = new RequestHandler();
//                String s = rh.sendGetRequestParam(AppConfig.URL_GET_USER_ALL, catid, subcatid);
                String s = rh.sendGetRequestParam(AppConfig.URL_GET_USER_ALL, cus_id);
                return s;
            }
        }
        GetData gd = new GetData();
        gd.execute();
    }



//    private ArrayList<LiveData> prepareData(){
////        String [] person={distance};
//        ArrayList<LiveData> android_version = new ArrayList<>();
//        for(int i=0;i<serialN0.length;i++){
//            LiveData androidVersion = new LiveData();
//            androidVersion.setSerial_no(serialN0[i]);
////            androidVersion.setImageId(image[i]);
////            androidVersion.setDate(date[i]);
////            androidVersion.setTime(time[i]);
////            androidVersion.setQuality(quality[i]);
////            androidVersion.setStatus(status[i]);
////            androidVersion.setImageId(image[i]);
////            androidVersion.setCart(cart_image[i]);
////            Toast.makeText(this, serialN0[i]+","+image[i], Toast.LENGTH_SHORT).show();
//            android_version.add(androidVersion);
//        }
//        return android_version;
//    }


    private void getSelectedData(final String m_serial){
        final String catid="3";
//        final String cus_id="1";
        class GetData extends AsyncTask<Void,Void,String> {
            ProgressDialog progressDialog;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressBar.setVisibility(View.VISIBLE);
//                progressDialog = ProgressDialog.show(Toy_store.this, "Fetching Data", "Please wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                progressBar.setVisibility(View.GONE);
//                progressDialog.dismiss();
//                progressBar.setVisibility(View.GONE);
                JSON_STRING=s;
//                Toast.makeText(Toy_store.this, JSON_STRING+"", Toast.LENGTH_SHORT).show();
//                Toast.makeText(Retail_Brand_Main.this, s+"", Toast.LENGTH_SHORT).show();

//                prepareData(s);
                prepareDataMachine(s);
//                parseJSON(s);
            }

            @Override
            protected String doInBackground(Void... params) {
//                BufferedReader bufferedReader = null;
//                try {
//                    URL url = new URL(AppConfig.URL_GET_ALL);
//                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
//                    StringBuilder sb = new StringBuilder();
//
//                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
//
//                    String json;
//                    while((json = bufferedReader.readLine())!= null){
//                        sb.append(json+"\n");
//                    }
//
//                    return sb.toString().trim();
//
//                }catch(Exception e){
//                    return null;
//                }
                HashMap<String,String> params1 = new HashMap<>();
                params1.put("m_sno",m_serial);
                params1.put("cus_id",cus_id);


                RequestHandler rh = new RequestHandler();
//                String s = rh.sendGetRequestParam(AppConfig.URL_GET_USER_ALL, catid, subcatid);
                String s = rh.sendPostRequest(AppConfig.URL_GET_SELECTED_ALL, params1);
                return s;
            }
        }
        GetData gd = new GetData();
        gd.execute();
    }


    private void prepareData(String s) {
//        String [] person={distance};
        JSONObject jsonObject = null;
        final ArrayList<LiveData> android_version = new ArrayList<>();
        try {
            jsonObject = new JSONObject(s);
            JSONArray array = jsonObject.getJSONArray(AppConfig.TAG_JSON_ARRAY);

            for (int i = 0; i < array.length(); i++) {
//            for (int i = array.length() ; i > 0 ; i--) {

                JSONObject j = array.getJSONObject(i);
                LiveData androidVersion = new LiveData();

//            androidVersion.setTitle(title[i]);
                androidVersion.setSerial_no(getSerial(j));
                androidVersion.setStatus(getStatus1(j));
                androidVersion.setImageId(getImage1(j));


//                androidVersion.setM_time(getTime(j));

//            androidVersion.setPerson(person[i]);
//                androidVersion.setPerson(getLocation(j));
//
////            androidVersion.setDetail(loc[i]);
//                androidVersion.setDetail(getArea(j));
////           androidVersion.setMail(mail[i]);
////            androidVersion.setImageId(image[i]);
//                androidVersion.setImageId(getImage(j));
//



                android_version.add(androidVersion);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        LiveDataAdapter adapter = new LiveDataAdapter(LiveSalesSE.this, android_version);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(LiveSalesSE.this, recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
//                Toast.makeText(LiveSalesSE.this, android_version.size()+"", Toast.LENGTH_SHORT).show();
                LiveData item= android_version.get(position);
//                String name=getString(R.string.title_activity_client_info);

//                switch (position) {
//                    case 0:

//                if(cus_id.equalsIgnoreCase("1")) {

//                if(lavazza.equalsIgnoreCase("manager"))
//                {
                Intent intent = new Intent(LiveSalesSE.this, SalesInput.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Bundle bundle = new Bundle();
                bundle.putString("sno", item.getSerial_no());
                bundle.putString("cus_id", cus_id1);
                bundle.putString("lavazza", lavazza);
//                Toast.makeText(context, data.getSerial_no()+"", Toast.LENGTH_SHORT).show();
                intent.putExtras(bundle);
                startActivity(intent);
//                    finish();

//                }
//                else
//                {
//                    Intent intent =new Intent(LiveSalesSE.this,SalesInput1.class);
//                    intent.putExtra("sno", item.getSerial_no());
//                    startActivity(intent);
//
//                }
//                        break;
//                    default:
//                        break;
//                }
//                }
//                else
//                {
//                    Intent intent = new Intent(LiveSales.this, SalesInput.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    Bundle bundle = new Bundle();
//                    bundle.putString("sno", item.getSerial_no());
////                Toast.makeText(context, data.getSerial_no()+"", Toast.LENGTH_SHORT).show();
//                    intent.putExtras(bundle);
//                    startActivity(intent);
//                }
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

    }


    private void prepareDataMachine(String s) {
//        String [] person={distance};
        JSONObject jsonObject = null;
        final ArrayList<LiveData> android_version1 = new ArrayList<>();
        try {
            jsonObject = new JSONObject(s);
            JSONArray array = jsonObject.getJSONArray(AppConfig.TAG_JSON_ARRAY);

            for (int i = 0; i < array.length(); i++) {
//            for (int i = array.length() ; i > 0 ; i--) {

                JSONObject j = array.getJSONObject(i);
                LiveData androidVersion1 = new LiveData();

//            androidVersion.setTitle(title[i]);
                androidVersion1.setSerial_no(getSerial(j));
                androidVersion1.setStatus(getStatus1(j));
                androidVersion1.setImageId(getImage1(j));


//                androidVersion.setM_time(getTime(j));

//            androidVersion.setPerson(person[i]);
//                androidVersion.setPerson(getLocation(j));
//
////            androidVersion.setDetail(loc[i]);
//                androidVersion.setDetail(getArea(j));
////           androidVersion.setMail(mail[i]);
////            androidVersion.setImageId(image[i]);
//                androidVersion.setImageId(getImage(j));

                android_version1.add(androidVersion1);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        LiveDataAdapter adapter = new LiveDataAdapter(LiveSalesSE.this, android_version1);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(LiveSalesSE.this, recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
//                Toast.makeText(LiveSalesSE.this, android_version1.size()+"", Toast.LENGTH_SHORT).show();
                LiveData item= android_version1.get(position);
//                String name=getString(R.string.title_activity_client_info);

//                switch (position) {
//                    case 0:

//                if(cus_id.equalsIgnoreCase("1")) {

//                if(lavazza.equalsIgnoreCase("manager"))
//                {
                Intent intent = new Intent(LiveSalesSE.this, SalesInput.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Bundle bundle = new Bundle();
                bundle.putString("sno", item.getSerial_no());
                bundle.putString("cus_id", cus_id1);
                bundle.putString("lavazza", lavazza);
//                Toast.makeText(context, data.getSerial_no()+"", Toast.LENGTH_SHORT).show();
                intent.putExtras(bundle);
                startActivity(intent);
//                    finish();

//                }
//                else
//                {
//                    Intent intent =new Intent(LiveSalesSE.this,SalesInput1.class);
//                    intent.putExtra("sno", item.getSerial_no());
//                    startActivity(intent);
//
//                }
//                        break;
//                    default:
//                        break;
//                }
//                }
//                else
//                {
//                    Intent intent = new Intent(LiveSales.this, SalesInput.class);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    Bundle bundle = new Bundle();
//                    bundle.putString("sno", item.getSerial_no());
////                Toast.makeText(context, data.getSerial_no()+"", Toast.LENGTH_SHORT).show();
//                    intent.putExtras(bundle);
//                    startActivity(intent);
//                }
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

    }

    private String getSerial(JSONObject j){
        String serial = null;
        try {
            serial = j.getString(AppConfig.TAG_SERIALNO);

//            machine.add(serial);
//            Toast.makeText(getActivity().getApplication(), serial+"", Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return serial;
    }

    private String getImage1(JSONObject j){
        String serial = null;
        try {
            serial = j.getString(AppConfig.TAG_IMAGE);
//            Toast.makeText(getActivity().getApplication(), serial+"", Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return serial;
    }

    private String getStatus1(JSONObject j){
        String serial = null;
        try {
            serial = j.getString(AppConfig.TAG_STATUS);
//            Toast.makeText(getActivity().getApplication(), serial+"", Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return serial;
    }

    private int getTime(JSONObject j){
//        String area = null;
        int time=0;
        try {
            time = j.getInt(AppConfig.TAG_TIME);
//            Toast.makeText(getActivity().getApplication(), time+"", Toast.LENGTH_SHORT).show();



        } catch (JSONException e) {
            e.printStackTrace();
        }
        return time;
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        showSnack(isConnected);
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.spinner_menu, menu);
//
//        MenuItem item = menu.findItem(R.id.spinner);
//        spinner = (Spinner) MenuItemCompat.getActionView(item);
//
////        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
////               machine, android.R.layout.simple_spinner_item);
////        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(LiveSales.this, R.layout.simple_spinner_item1, R.id.item, machine);
//
//        spinner.setAdapter(adapter);
//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
////                String[] quan1={"100pack","200packs","300packs","400packs"};
////                CustomAdapter adapter = new CustomAdapter(view.getContext(),quan1);
////                viewHolder.quantity.setAdapter(adapter);
//                String machine1=spinner.getSelectedItem().toString();
////                time=viewHolder.time.getSelectedItem().toString();
////                Toast.makeText(LiveSales.this, machine1+"", Toast.LENGTH_SHORT).show();
//                if(machine1.equalsIgnoreCase("Select"))
//                {
////                    Toast.makeText(LiveSales.this, "Please Select The Machine", Toast.LENGTH_SHORT).show();
//                }
//                else {
////                    recyclerView.setHasFixedSize(true);
////                    RecyclerView.LayoutManager layoutManager = new GridLayoutManager(LiveSales.this, 2);
//////        RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
//////        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplication());
////                    recyclerView.setLayoutManager(layoutManager);
//                    getSelectedData(machine1);
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
//
//        return true;
//
//    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == android.R.id.home)
        {

            Intent intent = new Intent(LiveSalesSE.this,MainActivity.class);
            intent.putExtra("id",cus_id1);
            intent.putExtra("lavazza",lavazza);
            startActivity(intent);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
