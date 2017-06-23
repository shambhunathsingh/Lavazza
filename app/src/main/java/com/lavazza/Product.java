package com.lavazza;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lavazza.app.AppConfig;
import com.lavazza.app.AppController;
import com.lavazza.app.RequestHandler;
import com.lavazza.helper.SQLiteHandler;
import com.rey.material.widget.ProgressView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Product extends AppCompatActivity implements ConnectivityReceiver.ConnectivityReceiverListener{
    RecyclerView recyclerView;
    private String JSON_STRING;

    public static String [] serialN0={"CFR80521","CFR80522","CFR80523"};
//    public static String [] date={"21-2-2017","21-2-2017","24-2-2017"};
//    public static String [] time={"10:30AM","10:30AM","1:00AM"};
    public static String [] quality={"100pack","100pack","100pack"};
    public static String [] status={"Yes","No","Yes"};
    public static int [] image={R.drawable.lavazza1,R.drawable.image1,R.drawable.lavazza1};
//    public static int [] cart_image={R.drawable.ic_action_cart,R.drawable.ic_action_cart,R.drawable.ic_action_cart};

    String cus_id,lavazza;

    static Button notifCount;
    static int mNotifCount = 0;

    int badge=0;

    SQLiteHandler db;

    private Menu menu;

    int badgeCount = 0;
    ProgressView progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        checkConnection();
        Bundle bundle =getIntent().getExtras();
        cus_id=bundle.getString("cus_id");
        lavazza=bundle.getString("lavazza");

        db=new SQLiteHandler(Product.this);
//        badgeCount = db.cartcount(cus_id);
//        Toast.makeText(this, db.getCartCount(cus_id)+"", Toast.LENGTH_SHORT).show();
        badge = db.getCartCount(cus_id);

        progressBar= (ProgressView) findViewById(R.id.loading_spinner);

//        if(badgeCount == 0)
//        {
//            badge=badgeCount+1;
//        }
//        else {
//            badge = badgeCount + 1;
//        }

//        Toast.makeText(this, badge+"", Toast.LENGTH_SHORT).show();




//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
////                        .setAction("Action", null).show();
//
//                Intent inten=new Intent(Product.this,ProductRequest.class);
//                startActivity(inten);
//            }
//        });

        final android.support.v7.app.ActionBar ab = getSupportActionBar();
        if(ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
//            ab.setTitle(Html.fromHtml("<font color='#0e2c4d'>Product Request</font>"));
//            ab.setTitle(name);
        }
        initViews();

    }

    public String getCusid()
    {
        return cus_id;
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
            Toast.makeText(Product.this, message, Toast.LENGTH_SHORT).show();
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
                String s = rh.sendGetRequestParam(AppConfig.URL_GET_PRODUCT_LIST, cus_id);
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
        final ArrayList<Data> android_version = new ArrayList<>();
        try {
            jsonObject = new JSONObject(s);
            JSONArray array = jsonObject.getJSONArray(AppConfig.TAG_JSON_ARRAY);

            for (int i = 0; i < array.length(); i++) {
                JSONObject j = array.getJSONObject(i);
                Data androidVersion = new Data();

//            androidVersion.setTitle(title[i]);
                androidVersion.setSerialNo(getName(j));

                androidVersion.setImageId(getImage(j));

                androidVersion.setProduct_no(getProdNo(j));

                androidVersion.setDescription(getDesc(j));

//                androidVersion.setQuality(quality[i]);
//                androidVersion.setStatus(status[i]);

                android_version.add(androidVersion);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //        ArrayList<Data> androidVersions = prepareData();
        DataAdapter adapter = new DataAdapter(Product.this, android_version, cus_id1);
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

    private String getName(JSONObject j){
       String name1 = null;
        try {
            name1 = j.getString("p_serial");
//            Toast.makeText(Parks.this, name+"", Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return name1;
    }

    private String getImage(JSONObject j){
        String image = null;
        try {
            image = j.getString("p_image");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return image;
    }


    private String getProdNo(JSONObject j){
        String prod_no = null;
        try {
            prod_no = j.getString("product_no");
//            Toast.makeText(Parks.this, name+"", Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return prod_no;
    }


    private String getDesc(JSONObject j){
        String desc = null;
        try {
            desc = j.getString("description");
//            Toast.makeText(Parks.this, name+"", Toast.LENGTH_SHORT).show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return desc;
    }

//    @Override
//    public boolean onPrepareOptionsMenu(Menu menu) {
//
//        menu.clear(); // Clear the menu first
//
//            /* Add the menu items */
//        getMenuInflater().inflate(R.menu.cart_main, menu);
////        getMenuInflater().inflate(R.menu.main, menu);
//        MenuItem item = menu.findItem(R.id.action_cart);
//        MenuItemCompat.setActionView(item, R.layout.badge_main);
//        RelativeLayout notifCount = (RelativeLayout) MenuItemCompat.getActionView(item);
//
////        supportInvalidateOptionsMenu();
//
//        TextView tv = (TextView) notifCount.findViewById(R.id.actionbar_notifcation_textview);
//        tv.setText(String.valueOf(badge));
//
//        ImageView imag= (ImageView) notifCount.findViewById(R.id.image);
//        imag.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent intent=new Intent(Product.this,Cart.class);
//                Bundle bundle=new Bundle();
//                bundle.putString("cus_id",cus_id);
//                intent.putExtra("lavazza",lavazza);
//                intent.putExtras(bundle);
//                startActivity(intent);
//                finish();
//
//            }
//        });
//
//        return super.onPrepareOptionsMenu(menu);
//    }

    public void refreshActivity()
    {
        finish();
        overridePendingTransition( 0, 0);
        startActivity(getIntent());
        overridePendingTransition( 0, 0);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        this.menu=menu;
//        getMenuInflater().inflate(R.menu.cart_main, menu);
//        return true;

//////////////////////////////////this is executed//////////
        getMenuInflater().inflate(R.menu.cart_main, menu);
//        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem item = menu.findItem(R.id.action_cart);
        MenuItemCompat.setActionView(item, R.layout.badge);
        RelativeLayout notifCount = (RelativeLayout) MenuItemCompat.getActionView(item);

//        supportInvalidateOptionsMenu();

        TextView tv = (TextView) notifCount.findViewById(R.id.actionbar_notifcation_textview);
        tv.setText(String.valueOf(badge));

        ImageView imag= (ImageView) notifCount.findViewById(R.id.image);
        imag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(Product.this,Cart.class);
                Bundle bundle=new Bundle();
                bundle.putString("cus_id",cus_id);
                intent.putExtra("lavazza",lavazza);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
            }
        });

        return super.onCreateOptionsMenu(menu);


        /////////////this is executed///

//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.cart_main, menu);
//
//        View count = menu.findItem(R.id.action_cart).getActionView();
//        TextView image = (TextView) count.findViewById(R.id.count1);
//        image.setText(badgeCount);

//        return super.onCreateOptionsMenu(menu);



//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.cart_main, menu);

//        MenuItem item = menu.findItem(R.id.action_cart);
//        MenuItemCompat.setActionView(item, R.layout.feed_update_count);
//        notifCount = (Button) MenuItemCompat.getActionView(item);
//
//
//
////        View count = menu.findItem(R.id.badge).getActionView();
////        notifCount = (Button) count.findViewById(R.id.notif_count);
//        notifCount.setText(String.valueOf(mNotifCount));
//        return super.onCreateOptionsMenu(menu);
    }

    public void updateCount()
    {

        badge = db.getCartCount(cus_id);

//        MenuItem item = menu.findItem(R.id.action_cart);
        MenuItem item = menu.findItem(R.id.action_cart);
        MenuItemCompat.setActionView(item, R.layout.badge);
//        MenuItemCompat.setActionView(item, R.layout.badge_main);
        RelativeLayout notifCount = (RelativeLayout) MenuItemCompat.getActionView(item);

//        supportInvalidateOptionsMenu();

        TextView tv = (TextView) notifCount.findViewById(R.id.actionbar_notifcation_textview);
        tv.setText(String.valueOf(badge));

        ImageView imag= (ImageView) notifCount.findViewById(R.id.image);
        imag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(Product.this,Cart.class);
                Bundle bundle=new Bundle();
                bundle.putString("cus_id",cus_id);
                intent.putExtra("lavazza",lavazza);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
            }
        });

    }


    private void setNotifCount(int count){
        mNotifCount = count;
        invalidateOptionsMenu();
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
//        Intent intent = new Intent(Product.this,MainActivity.class);
//        intent.putExtra("id",cus_id);
//        intent.putExtra("lavazza",lavazza);
//        startActivity(intent);

        if(lavazza.equalsIgnoreCase("customer")) {
            finish();
            Intent intent = new Intent(Product.this, MainActivityCustomer.class);
            intent.putExtra("id", cus_id);
            intent.putExtra("lavazza", lavazza);
            startActivity(intent);
//            return true;
        }
        else
        {
            finish();
            Intent intent = new Intent(Product.this, MainActivity.class);
            intent.putExtra("id", cus_id);
            intent.putExtra("lavazza", lavazza);
            startActivity(intent);
//            return true;

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == android.R.id.home)
        {
            if(lavazza.equalsIgnoreCase("customer")) {
                finish();
                Intent intent = new Intent(Product.this, MainActivityCustomer.class);
                intent.putExtra("id", cus_id);
                intent.putExtra("lavazza", lavazza);
                startActivity(intent);
                return true;
            }
            else
            {
                finish();
                Intent intent = new Intent(Product.this, MainActivity.class);
                intent.putExtra("id", cus_id);
                intent.putExtra("lavazza", lavazza);
                startActivity(intent);
                return true;

            }
//            return true;
        }
        if(id == R.id.action_cart)
        {
            VersionHelper.refreshActionBarMenu(this);
            Intent intent=new Intent(Product.this,Cart.class);
            Bundle bundle=new Bundle();
            bundle.putString("cus_id",cus_id);
            intent.putExtra("lavazza",lavazza);
            intent.putExtras(bundle);
            startActivity(intent);
            finish();

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
