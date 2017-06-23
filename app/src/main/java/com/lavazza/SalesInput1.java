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
import android.view.Menu;
import android.view.MenuInflater;
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

//public class SalesInput1 extends AppCompatActivity  implements ListView.OnItemClickListener{
public class SalesInput1 extends AppCompatActivity implements ConnectivityReceiver.ConnectivityReceiverListener{

    RecyclerView recyclerView;



    private ListView listView;

    private String JSON_STRING;

    private ActionBar actionBar;

    // Refresh menu item
    private MenuItem refreshMenuItem;
    String sno,cus_id,lavazza;
    ProgressView progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_input1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final android.support.v7.app.ActionBar ab = getSupportActionBar();
        if(ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
//            ab.setTitle(Html.fromHtml("<font color='#0e2c4d'>Machine Readings</font>"));
        }

        checkConnection();

        Bundle bundle=getIntent().getExtras();
        sno = bundle.getString("sno");
        cus_id=bundle.getString("cus_id");
        lavazza=bundle.getString("lavazza");

//        listView = (ListView) findViewById(R.id.listView);
//        listView.setOnItemClickListener(this);
//        getJSON();

        initViews();

        progressBar= (ProgressView) findViewById(R.id.loading_spinner);


//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

    //////////working code//////////////
//    private void showEmployee(){
//        JSONObject jsonObject = null;
//        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
//        try {
//            jsonObject = new JSONObject(JSON_STRING);
//            JSONArray result = jsonObject.getJSONArray(AppConfig.TAG_JSON_ARRAY);
//
//            for(int i = 0; i<result.length(); i++){
//                JSONObject jo = result.getJSONObject(i);
////                String id = jo.getString(AppConfig.TAG_ID);
//                String serial_no = jo.getString("serial_no");
//                String p_name = jo.getString("p_name");
//                String opening = jo.getString("opening");
//                String closing = jo.getString("closing");
//                String consumption = jo.getString("consumption");
//
//
//                HashMap<String,String> reading = new HashMap<>();
////                employees.put(AppConfig.TAG_ID,id);
//                reading.put("serial_no",serial_no);
//                reading.put("p_name",p_name);
//                reading.put("opening",opening);
//                reading.put("closing",closing);
//                reading.put("consumption",consumption);
//
//                list.add(reading);
//            }
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        ListAdapter adapter = new SimpleAdapter(
//                SalesInput1.this, list, R.layout.display_reading,
//                new String[]{"serial_no", "p_name", "opening", "closing", "consumption"},
//                new int[]{R.id.serial, R.id.pname, R.id.opening, R.id.closing, R.id.consumption});
//
//        listView.setAdapter(adapter);
//    }
//
//    private void getJSON(){
//        class GetJSON extends AsyncTask<Void,Void,String> {
//
//            ProgressDialog loading;
//            @Override
//            protected void onPreExecute() {
//                super.onPreExecute();
//                loading = ProgressDialog.show(SalesInput1.this,"Fetching Data","Wait...",false,false);
//            }
//
//            @Override
//            protected void onPostExecute(String s) {
//                super.onPostExecute(s);
//                loading.dismiss();
//                JSON_STRING = s;
//                showEmployee();
//            }
//
//            @Override
//            protected String doInBackground(Void... params) {
//                RequestHandler rh = new RequestHandler();
//                String s = rh.sendGetRequestParam(AppConfig.URL_GET_ALL,sno);
//                return s;
//            }
//        }
//        GetJSON gj = new GetJSON();
//        gj.execute();
//    }
//
//    @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
////        Intent intent = new Intent(this, ViewEmployee.class);
////        HashMap<String,String> map =(HashMap)parent.getItemAtPosition(position);
////        String empId = map.get(AppConfig.TAG_ID).toString();
////        intent.putExtra(AppConfig.EMP_ID,empId);
////        startActivity(intent);
//    }

////////////////////////////////////////////////////////////




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

//        ArrayList<Data> androidVersions = prepareData();
//        DataAdapter adapter = new DataAdapter(Product.this, androidVersions);
//        recyclerView.setAdapter(adapter);
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
            Toast.makeText(SalesInput1.this, message, Toast.LENGTH_SHORT).show();
//            Toast toast=Toast.makeText(this,"Toast:Gravity.TOP",Toast.LENGTH_SHORT);
//            toast.setGravity(Gravity.CENTER,0,0);
//            toast.setView(viewLayout);
//            toast.show();

//            color = Color.RED;

        }


    }


//    private ArrayList<Data> prepareData(){
////        String [] person={distance};
//        ArrayList<Data> android_version = new ArrayList<>();
//        for(int i=0;i<serialN0.length;i++){
//            Data androidVersion = new Data();
//            androidVersion.setSerialNo(serialN0[i]);
////            androidVersion.setDate(date[i]);
////            androidVersion.setTime(time[i]);
//            androidVersion.setQuality(quality[i]);
//            androidVersion.setStatus(status[i]);
//            androidVersion.setImageId(image[i]);
////            androidVersion.setCart(cart_image[i]);
////            Toast.makeText(this, serialN0[i]+","+image[i], Toast.LENGTH_SHORT).show();
//            android_version.add(androidVersion);
//        }
//        return android_version;
//    }

    private void getData(){
        final String catid="3";
//        final String cus_id="1";
        class GetData extends AsyncTask<Void,Void,String> {
            ProgressDialog progressDialog;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
//                progressBar.setVisibility(View.VISIBLE);
//                progressDialog = ProgressDialog.show(Toy_store.this, "Fetching Data", "Please wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
//                progressBar.setVisibility(View.GONE);
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
                String s = rh.sendGetRequestParam(AppConfig.URL_GET_ALL, sno);
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
        final ArrayList<PreviewData> android_version = new ArrayList<>();
        try {
            jsonObject = new JSONObject(s);
            JSONArray array = jsonObject.getJSONArray(AppConfig.TAG_JSON_ARRAY);

            for (int i = 0; i < array.length(); i++) {
                JSONObject j = array.getJSONObject(i);
                PreviewData androidVersion = new PreviewData();

//            androidVersion.setTitle(title[i]);
                androidVersion.setSerialNo(getSerial(j));

                androidVersion.setProduct(getProduct(j));

                androidVersion.setOpening(getOpeing(j));

                androidVersion.setClosing(getClosing(j));

                androidVersion.setConsumption(getConsumption(j));


//                androidVersion.setQuality(quality[i]);
//                androidVersion.setStatus(status[i]);

                android_version.add(androidVersion);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //        ArrayList<Data> androidVersions = prepareData();
        SalesInput_Adapter adapter = new SalesInput_Adapter(SalesInput1.this, android_version);
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

    private String getProduct(JSONObject j){
        String image = null;
        try {
            image = j.getString("p_name");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return image;
    }


    private String getOpeing(JSONObject j){
        String prod_no = null;
        try {
            prod_no = j.getString("opening");
//            Toast.makeText(Parks.this, name+"", Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return prod_no;
    }


    private String getClosing(JSONObject j){
        String desc = null;
        try {
            desc = j.getString("closing");
//            Toast.makeText(Parks.this, name+"", Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return desc;
    }

    private String getConsumption(JSONObject j){
        String desc = null;
        try {
            desc = j.getString("consumption");
//            Toast.makeText(Parks.this, name+"", Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return desc;
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
//        Intent intent = new Intent(SalesInput1.this,LiveSalesSE.class);
//        intent.putExtra("cus_id",cus_id);
//        intent.putExtra("lavazza",lavazza);
//        startActivity(intent);
//        finish();

        if(lavazza.equalsIgnoreCase("customer")) {

            Intent intent = new Intent(SalesInput1.this, LiveSales.class);
            intent.putExtra("cus_id", cus_id);
            intent.putExtra("lavazza", lavazza);
            startActivity(intent);
            finish();

        }
        else
        {

            Intent intent = new Intent(SalesInput1.this, LiveSalesSE.class);
            intent.putExtra("cus_id", cus_id);
            intent.putExtra("lavazza", lavazza);
            startActivity(intent);
            finish();


        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.reading_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


//        switch (item.getItemId()) {
////            case R.id.action_search:
////                // search action
////                return true;
////            case R.id.action_location_found:
////                // location found
////                LocationFound();
////                return true;
////            case R.id.action_refresh:
////                // refresh
//////                finish();
//////                startActivity(getIntent());
////
////                Intent intent = getIntent();
////                overridePendingTransition(0, 0);
////                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
////                finish();
////                overridePendingTransition(0, 0);
////                startActivity(intent);
//////
//////                refreshMenuItem = item;
//////                // load the data from server
//////                new SyncData().execute();
////                return true;
//
//
//
//
//            case android.R.id.home:
//                Intent intent =new Intent(SalesInput1.this,LiveSales.class);
//                intent.putExtra("cus_id",cus_id);
//                intent.putExtra("lavazza",lavazza);
//                finish();
//                return true;
////            case R.id.action_check_updates:
////                // check for updates action
////                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }

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

                Intent intent = new Intent(SalesInput1.this, LiveSales.class);
                intent.putExtra("cus_id", cus_id);
                intent.putExtra("lavazza", lavazza);
                startActivity(intent);
                finish();
                return true;
            }
            else
            {

                Intent intent = new Intent(SalesInput1.this, LiveSalesSE.class);
                intent.putExtra("cus_id", cus_id);
                intent.putExtra("lavazza", lavazza);
                startActivity(intent);
                finish();
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
