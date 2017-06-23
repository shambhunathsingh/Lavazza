package com.lavazza;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AnimationUtils;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lavazza.helper.SQLiteHandler;
import com.lavazza.helper.SessionManager;
import com.lavazza.helper.SessionManager_New;

public class MainActivityCustomer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    GridView gridView;
    public static String [] nameList={"LIVE SALES","PRODUCT","SERVICES","FEEDBACK"};
    public static int [] images={R.drawable.ic_action_live,R.drawable.ic_action_product,R.drawable.ic_action_services,R.drawable.ic_action_feedback};

    protected View view;
    private ImageButton btnNext, btnFinish;
    private ViewPager intro_images;
    private LinearLayout pager_indicator;
    private int dotsCount;
    private ImageView[] dots;
    private ViewPagerAdapter mAdapter;
    private int[] mImageResources = {
            R.drawable.photo1,
            R.drawable.photo2,
            R.drawable.photo3
    };

    String cus_id,lavazza;
    private SessionManager session;
    private SessionManager_New sessionManager_new;
    int badge=0;
    SQLiteHandler db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_customer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle bundle=getIntent().getExtras();
//        String lavazza=bundle.getString("lavazza");
        cus_id=bundle.getString("id");
        lavazza=bundle.getString("lavazza");

        db=new SQLiteHandler(MainActivityCustomer.this);
//        badgeCount = db.cartcount(cus_id);
//        Toast.makeText(this, db.getCartCount(cus_id)+"", Toast.LENGTH_SHORT).show();
        badge = db.getCartCount(cus_id);

        final android.support.v7.app.ActionBar ab = getSupportActionBar();
        if(ab != null) {
            //            ab.setIcon(R.drawable.rsz_12logo1);
//            ab.setIcon(R.drawable.rsz_12lavazza);
//            ab.setIcon(R.drawable.logo);
            ab.setTitle("");


//            ab.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
//            ab.setCustomView(R.layout.logo);


//            ab.setDisplayShowCustomEnabled(true);
//            View cView = getLayoutInflater().inflate(R.layout.logo, null);
//            ab.setCustomView(cView);

//            ab.setDisplayHomeAsUpEnabled(true);
//            ab.setTitle(name);
//            ab.setTitle(Html.fromHtml("<font color='#000'>MCentre</font>"));

//            ab.setIcon(R.drawable.lavazza_logo);


            //////////////working code/////////////
//
//            ab.setDisplayOptions(ab.getDisplayOptions()
//                    | android.support.v7.app.ActionBar.DISPLAY_SHOW_CUSTOM);
//            ImageView imageView = new ImageView(ab.getThemedContext());
////            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
//            imageView.setImageResource(R.drawable.logowithpadding);
//            ActionBar.LayoutParams layoutParams = new ActionBar.LayoutParams(
//                    20,
//                    5, Gravity.START
//                    | Gravity.CENTER_VERTICAL);
////            layoutParams.leftMargin = 10;
////            layoutParams.topMargin=10;
//            imageView.setLayoutParams(layoutParams);
//            ab.setCustomView(imageView);
            ////////////////////////////////
        }

        session = new SessionManager(getApplicationContext());
        sessionManager_new = new SessionManager_New(getApplicationContext());

        if (!session.isLoggedIn()) {
//            logoutUser();

            session.setLogin(true);
        }

//        assert lavazza != null;
//        if(lavazza.equalsIgnoreCase("manager")) {
//            ManagerFragment productFragment = new ManagerFragment();
//            Bundle bundle2 = getIntent().getExtras();
//            productFragment.setArguments(bundle2);
//
//            android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//            fragmentTransaction.replace(R.id.fragment_container, productFragment);
//            fragmentTransaction.commit();
//        }
//        else
//        {
        ManagerFragment customerFragment = new ManagerFragment();
//            Bundle bundle1 = getIntent().getExtras();
        Bundle bundle1=new Bundle();
        bundle1.putString("cus_id",cus_id);
        bundle1.putString("lavazza",lavazza);
//        Toast.makeText(MainActivity.this,cus_id,Toast.LENGTH_SHORT).show();
        customerFragment.setArguments(bundle1);

        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, customerFragment);
        fragmentTransaction.commit();

//        }

//        gridView= (GridView) findViewById(R.id.grid);
//        gridView.setAdapter(new CustomAndroidGridViewAdapter(getBaseContext(), nameList, images));
//        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//                switch (i)
//                {
//                    case 0:
//                        break;
//                    case 1: Intent intent= new Intent(MainActivity.this,Product.class);
//                            startActivity(intent);
//                        break;
//
//                    case 2: Intent intent1= new Intent(MainActivity.this,ServiceRequest.class);
//                            startActivity(intent1);
//                        break;
//                    case 3: Intent intent2= new Intent(MainActivity.this,FeedBack.class);
//                        startActivity(intent2);
//                        break;
//                }
//
//            }
//        });

//        intro_images = (ViewPager) findViewById(R.id.pager_introduction);
//        pager_indicator = (LinearLayout) findViewById(R.id.viewPagerCountDots);
//
////        btnNext.setOnClickListener(this);
////        btnFinish.setOnClickListener(this);
//
//        mAdapter = new ViewPagerAdapter(MainActivity.this, mImageResources);
//        intro_images.setAdapter(mAdapter);
//        intro_images.setCurrentItem(0);
//        intro_images.setOnPageChangeListener(this);
//        setUiPageViewController();

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (doubleBackToExitPressedOnce) {
                Intent startMain = new Intent(Intent.ACTION_MAIN);
                startMain.addCategory(Intent.CATEGORY_HOME);
                startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(startMain);
                //moveTaskToBack(true);
                return;
            }

            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this.getBaseContext(), "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main_activity_customer, menu);
//        return true;

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

                Intent intent=new Intent(MainActivityCustomer.this,Cart.class);
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
        if (id == R.id.action_cart) {

            Intent intent=new Intent(MainActivityCustomer.this,Cart.class);
            Bundle bundle=new Bundle();
            bundle.putString("cus_id",cus_id);
            intent.putExtra("lavazza",lavazza);
            intent.putExtras(bundle);
            startActivity(intent);

            return true;
        }

        else if (id == R.id.user) {

            Intent intent=new Intent(MainActivityCustomer.this,CustomerPortal.class);
            Bundle bundle=new Bundle();
            bundle.putString("cus_id",cus_id);
            intent.putExtra("lavazza",lavazza);
            intent.putExtras(bundle);
            startActivity(intent);

            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.profile) {
            // Handle the camera action
        } else if (id == R.id.orders) {

        } else if (id == R.id.portal) {
            Intent intent1= new Intent(MainActivityCustomer.this,CustomerPortal.class);
            startActivity(intent1);

        } else if (id == R.id.services) {
            Intent intent1= new Intent(MainActivityCustomer.this,ServiceRequestSent.class);
            Bundle bundle2 = new Bundle();
            bundle2.putString("cus_id", cus_id);
            bundle2.putString("lavazza", lavazza);
            intent1.putExtras(bundle2);
            startActivity(intent1);

        } else if (id == R.id.feedback) {

            Intent intent2= new Intent(MainActivityCustomer.this,FeedBack.class);
            Bundle bundle3 = new Bundle();
            bundle3.putString("cus_id", cus_id);
            bundle3.putString("lavazza", lavazza);
            intent2.putExtras(bundle3);
            startActivity(intent2);


        }
        else if (id == R.id.help) {

        }
        else if (id == R.id.logout) {
            sessionManager_new.logoutUser();
            session.setLogin(false);
            Intent intent=new Intent(MainActivityCustomer.this,Login.class);
            startActivity(intent);
            finish();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
