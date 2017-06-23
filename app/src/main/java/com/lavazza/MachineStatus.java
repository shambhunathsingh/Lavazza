package com.lavazza;


import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.lavazza.app.AppConfig;
import com.lavazza.app.AppController;
import com.lavazza.app.RequestHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class MachineStatus extends Fragment implements ConnectivityReceiver.ConnectivityReceiverListener {
    RecyclerView recyclerView;
    private String JSON_STRING;

    public static String [] serialN0={"2013","2014","2015","2016","2017","2018"};

    public static int [] image={R.drawable.coffee_machine_yellow,R.drawable.coffee_machine_red,R.drawable.coffee_machine_green,R.drawable.coffee_machine_yellow,R.drawable.coffee_machine_green,R.drawable.coffee_machine_green};

    int mYear, mMonth, mDay, mHour, mMinute;
    String time1;
    int time2;

    String cus_id;

    public MachineStatus() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_machine_status, container, false);
        // Inflate the layout for this fragment
        //        final RippleView rippleView = (RippleView) findViewById(R.id.rect);

        checkConnection();

        getCustomerId();


        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

//        Toast.makeText(getActivity().getApplication(), mHour+":"+mMinute, Toast.LENGTH_SHORT).show();
//        addTime();
//        time1=mHour+":"+mMinute;
//        time2= Integer.parseInt(time1);
//        time2=Integer.valueOf(String.valueOf(mHour) +":"+ String.valueOf(mMinute));


        recyclerView = (RecyclerView) view.findViewById(R.id.card_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity().getApplication(), 2);
//        RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplication());
        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));

        getData();

//        ArrayList<LiveData> androidVersions = prepareData();
//
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




        return view;
    }

    private void checkConnection() {
        boolean isConnected = ConnectivityReceiver.isConnected();
        showSnack(isConnected);
    }

    private void getCustomerId()
    {
        LiveSales activity = (LiveSales) getActivity();
        cus_id = activity.getCustomer();
//        Toast.makeText(getActivity().getApplication(), cus_id+"", Toast.LENGTH_SHORT).show();

    }

    public void addTime()
    {
        time2=Integer.valueOf(mHour +":"+ mMinute);
        Toast.makeText(getActivity().getApplication(), time2+"", Toast.LENGTH_SHORT).show();
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
            Toast.makeText(getActivity().getApplication(), message, Toast.LENGTH_SHORT).show();
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

//    @Override
//    protected void onResume() {
//        super.onResume();
//
//        // register connection status listener
//        AppController.getInstance().setConnectivityListener(this);
//    }


    private void getData(){
        final String catid="3";
//        final String cus_id="1";
        class GetData extends AsyncTask<Void,Void,String> {
            ProgressDialog progressDialog;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
//                progressDialog = ProgressDialog.show(Toy_store.this, "Fetching Data", "Please wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
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



    private ArrayList<LiveData> prepareData(){
//        String [] person={distance};
        ArrayList<LiveData> android_version = new ArrayList<>();
        for(int i=0;i<serialN0.length;i++){
            LiveData androidVersion = new LiveData();
            androidVersion.setSerial_no(serialN0[i]);
//            androidVersion.setImageId(image[i]);
//            androidVersion.setDate(date[i]);
//            androidVersion.setTime(time[i]);
//            androidVersion.setQuality(quality[i]);
//            androidVersion.setStatus(status[i]);
//            androidVersion.setImageId(image[i]);
//            androidVersion.setCart(cart_image[i]);
//            Toast.makeText(this, serialN0[i]+","+image[i], Toast.LENGTH_SHORT).show();
            android_version.add(androidVersion);
        }
        return android_version;
    }


    private void prepareData(String s) {
//        String [] person={distance};
        JSONObject jsonObject = null;
        final ArrayList<LiveData> android_version = new ArrayList<>();
        try {
            jsonObject = new JSONObject(s);
            JSONArray array = jsonObject.getJSONArray(AppConfig.TAG_JSON_ARRAY);

            for (int i = 0; i < array.length(); i++) {
                JSONObject j = array.getJSONObject(i);
                LiveData androidVersion = new LiveData();

//            androidVersion.setTitle(title[i]);
                androidVersion.setSerial_no(getSerial(j));

                androidVersion.setM_time(getTime(j));

//            androidVersion.setPerson(person[i]);
//                androidVersion.setPerson(getLocation(j));
//
////            androidVersion.setDetail(loc[i]);
//                androidVersion.setDetail(getArea(j));
////           androidVersion.setMail(mail[i]);
////            androidVersion.setImageId(image[i]);
//                androidVersion.setImageId(getImage(j));
//
////                androidVersion.setMobile(j.getString(AppConfig.TAG_MOBILE));
////                androidVersion.setEmail(j.getString(AppConfig.TAG_EMAIL));
//                androidVersion.setWebsite(j.getString(AppConfig.TAG_WEBSITE));
//                androidVersion.setHours(j.getString(AppConfig.TAG_HOURS));
////                androidVersion.setLocation(j.getString(AppConfig.TAG_LOCATION));
//
//                androidVersion.setMobile(getPhone1(j));
//                androidVersion.setEmail(getEmail1(j));
//                androidVersion.setLocation(getLoc1(j));

                android_version.add(androidVersion);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        LiveDataAdapter adapter = new LiveDataAdapter(getActivity().getApplication(), android_version);
        recyclerView.setAdapter(adapter);
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

    private String getSerial(JSONObject j){
        String serial = null;
        try {
            serial = j.getString(AppConfig.TAG_SERIALNO);
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
}
