package com.lavazza;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.lavazza.app.AppConfig;
import com.lavazza.app.AppController;
import com.lavazza.app.RequestHandler;
import com.rey.material.widget.ProgressView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ServiceRequestSent extends AppCompatActivity implements ListView.OnItemClickListener, ConnectivityReceiver.ConnectivityReceiverListener{

    private ListView listView;
    RecyclerView recyclerView;
    private String JSON_STRING;

    private ActionBar actionBar;

    // Refresh menu item
    private MenuItem refreshMenuItem;
    String sno,cus_id,lavazza;

    ProgressView progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_request_sent);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        checkConnection();

        final android.support.v7.app.ActionBar ab = getSupportActionBar();
        if(ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
//            ab.setTitle(Html.fromHtml("<font color='#0e2c4d'>Service Request</font>"));
        }

        Bundle bundle=getIntent().getExtras();
        sno = bundle.getString("sno");
        cus_id=bundle.getString("cus_id");
        lavazza=bundle.getString("lavazza");

        progressBar= (ProgressView) findViewById(R.id.loading_spinner);
//        listView = (ListView) findViewById(R.id.listView);
//        listView.setOnItemClickListener(this);
//        getJSON();

        initViews();



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
            Toast.makeText(ServiceRequestSent.this, message, Toast.LENGTH_SHORT).show();
//            Toast toast=Toast.makeText(this,"Toast:Gravity.TOP",Toast.LENGTH_SHORT);
//            toast.setGravity(Gravity.CENTER,0,0);
//            toast.setView(viewLayout);
//            toast.show();

//            color = Color.RED;

        }


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

        getData();

    }

    private void getData(){

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
//                progressBar.setVisibility(View.GONE);
//                progressDialog.dismiss();
                progressBar.setVisibility(View.GONE);
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
                String s = rh.sendGetRequestParam(AppConfig.URL_GET_SERVICE_REQUEST,cus_id);
                return s;
            }
        }
        GetData gd = new GetData();
        gd.execute();
    }

    private void prepareData(String s) {
//        String [] person={distance};
        String cus_id1=cus_id;
        JSONObject jsonObject = null;
        final ArrayList<Service> android_version = new ArrayList<>();
        try {
            jsonObject = new JSONObject(s);
            JSONArray array = jsonObject.getJSONArray(AppConfig.TAG_JSON_ARRAY);

            for (int i = 0; i < array.length(); i++) {
                JSONObject j = array.getJSONObject(i);
                Service androidVersion = new Service();

//            androidVersion.setTitle(title[i]);
                androidVersion.setSerial_no(getSerial(j));
                androidVersion.setError_code(getE_Code(j));
                androidVersion.setError_message(getE_Msg(j));
                androidVersion.setLocation(getLocation(j));
                androidVersion.setRemark(getRemark(j));
                androidVersion.setDate(getDate(j));
                androidVersion.setTime(getTime(j));



//                androidVersion.setQuality(quality[i]);
//                androidVersion.setStatus(status[i]);

                android_version.add(androidVersion);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //        ArrayList<Data> androidVersions = prepareData();
        ServiceDataAdapter adapter = new ServiceDataAdapter(ServiceRequestSent.this, android_version);
        recyclerView.setAdapter(adapter);
//        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new ClickListener() {
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
//
//        }));
    }


    private String getSerial(JSONObject j){
        String name1 = null;
        try {
            name1 = j.getString("serial_no");
//            Toast.makeText(Parks.this, name+"", Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return name1;
    }

    private String getE_Code(JSONObject j){
        String image = null;
        try {
            image = j.getString("error_code");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return image;
    }


    private String getE_Msg(JSONObject j){
        String prod_no = null;
        try {
            prod_no = j.getString("error_message");
//            Toast.makeText(Parks.this, name+"", Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return prod_no;
    }


    private String getLocation(JSONObject j){
        String desc = null;
        try {
            desc = j.getString("location");
//            Toast.makeText(Parks.this, name+"", Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return desc;
    }

    private String getRemark(JSONObject j){
        String desc = null;
        try {
            desc = j.getString("remark");
//            Toast.makeText(Parks.this, name+"", Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return desc;
    }

    private String getDate(JSONObject j){
        String desc = null;
        try {
            desc = j.getString("date");
//            Toast.makeText(Parks.this, name+"", Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return desc;
    }
    private String getTime(JSONObject j){
        String desc = null;
        try {
            desc = j.getString("time");
//            Toast.makeText(Parks.this, name+"", Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return desc;
    }



    private void showEmployee(){
        JSONObject jsonObject = null;
        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
        try {
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(AppConfig.TAG_JSON_ARRAY);

            for(int i = 0; i<result.length(); i++){
                JSONObject jo = result.getJSONObject(i);
//                String id = jo.getString(AppConfig.TAG_ID);
                String location = jo.getString("location");
                String serial_no = jo.getString("serial_no");
                String error_code = jo.getString("error_code");
                String error_message = jo.getString("error_message");
                String remark = jo.getString("remark");



                HashMap<String,String> reading = new HashMap<>();
//                employees.put(AppConfig.TAG_ID,id);
                reading.put("location",location);
                reading.put("serial_no",serial_no);
                reading.put("error_code",error_code);
                reading.put("error_message",error_message);
                reading.put("remark",remark);

                list.add(reading);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListAdapter adapter = new SimpleAdapter(
                ServiceRequestSent.this, list, R.layout.display_reading_service,
                new String[]{"location", "serial_no", "error_code", "error_message", "remark"},
                new int[]{R.id.location, R.id.serialno, R.id.e_code, R.id.e_msg, R.id.remark});

        listView.setAdapter(adapter);
    }

    private void getJSON(){
        class GetJSON extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressBar.setVisibility(View.VISIBLE);
//                loading = ProgressDialog.show(ServiceRequestSent.this,"Fetching Data","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
//                loading.dismiss();
                progressBar.setVisibility(View.GONE);
                JSON_STRING = s;
                showEmployee();
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(AppConfig.URL_GET_SERVICE_REQUEST,cus_id);
                return s;
            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        Intent intent = new Intent(this, ViewEmployee.class);
//        HashMap<String,String> map =(HashMap)parent.getItemAtPosition(position);
//        String empId = map.get(AppConfig.TAG_ID).toString();
//        intent.putExtra(AppConfig.EMP_ID,empId);
//        startActivity(intent);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu., menu);
//
//        return super.onCreateOptionsMenu(menu);
//    }

    @Override
    public void onBackPressed() {
//        Intent intent = new Intent(ServiceRequestSent.this,LiveSalesSE.class);
//        intent.putExtra("cus_id",cus_id);
//        intent.putExtra("lavazza",lavazza);
//        startActivity(intent);
//        finish();
        if(lavazza.equalsIgnoreCase("customer")) {
            finish();
            Intent intent = new Intent(ServiceRequestSent.this, MainActivityCustomer.class);
            intent.putExtra("id", cus_id);
            intent.putExtra("lavazza", lavazza);
            startActivity(intent);

        }
        else
        {
            finish();
            Intent intent = new Intent(ServiceRequestSent.this, MainActivity.class);
            intent.putExtra("id", cus_id);
            intent.putExtra("lavazza", lavazza);
            startActivity(intent);


        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == android.R.id.home)
        {
//            finish();
//            Intent intent = new Intent(ServiceRequestSent.this,MainActivityCustomer.class);
//            Bundle bundle=new Bundle();
//            bundle.putString("id",cus_id);
//            bundle.putString("lavazza",lavazza);
//            intent.putExtras(bundle);
//            startActivity(intent);
//            return true;

            if(lavazza.equalsIgnoreCase("customer")) {
                finish();
                Intent intent = new Intent(ServiceRequestSent.this, MainActivityCustomer.class);
                intent.putExtra("id", cus_id);
                intent.putExtra("lavazza", lavazza);
                startActivity(intent);
                return true;
            }
            else
            {
                finish();
                Intent intent = new Intent(ServiceRequestSent.this, MainActivity.class);
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
