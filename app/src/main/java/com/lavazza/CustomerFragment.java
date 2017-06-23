package com.lavazza;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;

import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class CustomerFragment extends Fragment implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener{
    GridView gridView;
    public static String [] nameList={"Live Sales","Customer","Services","Data"};
    public static int [] images={R.drawable.sales,R.drawable.customer_1,R.drawable.services_1,R.drawable.data_1};

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

    LinearLayout coffee1,coffee2,coffee3,coffee4;
    String cus_id,lavazza;

    SliderLayout sliderLayout;
    HashMap<String,Integer> Hash_file_maps ;

    ImageView portal;

    public CustomerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_customer, container, false);

        cus_id = getArguments().getString("cus_id");
        lavazza = getArguments().getString("lavazza");
//        Toast.makeText(getActivity().getApplication(), cus_id+"", Toast.LENGTH_SHORT).show();

        gridView= (GridView) view.findViewById(R.id.grid);
        gridView.setAdapter(new CustomAndroidGridViewAdapter(getActivity().getApplication(), nameList, images));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                switch (i)
                {
                    case 0:
                        view.startAnimation(AnimationUtils.loadAnimation(getActivity().getApplication(), R.anim.image_click));

                        if (lavazza.equalsIgnoreCase("customer")) {
                            Intent intent = new Intent(getActivity().getApplication(), LiveSales.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("cus_id", cus_id);
                            bundle.putString("lavazza", lavazza);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                        else
                        {

                            Intent intent = new Intent(getActivity().getApplication(), LiveSalesSE.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("cus_id", cus_id);
                            bundle.putString("lavazza", lavazza);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                        break;
                    case 1:
                        view.startAnimation(AnimationUtils.loadAnimation(getActivity().getApplication(), R.anim.image_click));
                        ManagerFragment customerFragment = new ManagerFragment();
//                        Bundle bundle1 = getActivity().getIntent().getExtras();
                        Bundle bundle1= new Bundle();
                        bundle1.putString("cus_id",cus_id);
                        bundle1.putString("lavazza",lavazza);
                        customerFragment.setArguments(bundle1);

                        android.support.v4.app.FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_container, customerFragment);
                        fragmentTransaction.commit();

//                        Intent intent= new Intent(getActivity().getApplication(),Product.class);
//                        startActivity(intent);
                        break;

                    case 2:
                        if(lavazza.equalsIgnoreCase("customer"))
                        {
                            view.startAnimation(AnimationUtils.loadAnimation(getActivity().getApplication(), R.anim.image_click));
                            Intent intent1 = new Intent(getActivity().getApplication(), ServiceRequestSent.class);
                            Bundle bundle2 = new Bundle();
                            bundle2.putString("cus_id", cus_id);
                            bundle2.putString("lavazza", lavazza);
                            intent1.putExtras(bundle2);
                            startActivity(intent1);

                        }
                        else {
                            view.startAnimation(AnimationUtils.loadAnimation(getActivity().getApplication(), R.anim.image_click));
                            Intent intent1 = new Intent(getActivity().getApplication(), ServiceRequest.class);
                            Bundle bundle2 = new Bundle();
                            bundle2.putString("cus_id", cus_id);
                            bundle2.putString("lavazza", lavazza);
                            intent1.putExtras(bundle2);
                            startActivity(intent1);
                        }
                        break;
                    case 3:
                        view.startAnimation(AnimationUtils.loadAnimation(getActivity().getApplication(), R.anim.image_click));
                        Intent intent2= new Intent(getActivity().getApplication(),DataMain.class);
                        Bundle bundle3 = new Bundle();
                        bundle3.putString("cus_id", cus_id);
                        bundle3.putString("lavazza", lavazza);
                        intent2.putExtras(bundle3);
                        startActivity(intent2);
                        break;
                }

            }
        });

        Hash_file_maps = new HashMap<String, Integer>();
//        Hash_file_maps.clear();
        sliderLayout = (SliderLayout)view.findViewById(R.id.slider);

        Hash_file_maps.put("photo1",R.drawable.help);
        Hash_file_maps.put("photo2",R.drawable.photo1);
        Hash_file_maps.put("photo3",R.drawable.photo2);
        Hash_file_maps.put("photo4",R.drawable.photo3);

//        Hash_file_maps.put("Android", R.drawable.help);
//        Hash_file_maps.put("Android CupCake1", R.drawable.photo1);
//        Hash_file_maps.put("Android Donut", R.drawable.photo2);
//        Hash_file_maps.put("Android Eclair", R.drawable.photo3);

//        Hash_file_maps.put("Android Froyo", "http://androidblog.esy.es/images/froyo-4.png");
//        Hash_file_maps.put("Android GingerBread", "http://androidblog.esy.es/images/gingerbread-5.png");

        for(String name : Hash_file_maps.keySet()){

            TextSliderView textSliderView = new TextSliderView(getActivity().getApplication());
            textSliderView
//                    .description(name)
                    .image(Hash_file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra",name);
            sliderLayout.addSlider(textSliderView);
        }
        sliderLayout.setPresetTransformer(SliderLayout.Transformer.Accordion);
        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
//        sliderLayout.setCustomAnimation(new DescriptionAnimation());
        sliderLayout.setDuration(3000);
        sliderLayout.addOnPageChangeListener(this);

//        intro_images = (ViewPager) view.findViewById(R.id.pager_introduction);
//        pager_indicator = (LinearLayout)view.findViewById(R.id.viewPagerCountDots);

//        btnNext.setOnClickListener(this);
//        btnFinish.setOnClickListener(this);

        coffee1= (LinearLayout) view.findViewById(R.id.coffee1);
        coffee2= (LinearLayout) view.findViewById(R.id.coffee2);
        coffee3= (LinearLayout) view.findViewById(R.id.coffee3);
        coffee4= (LinearLayout) view.findViewById(R.id.coffee4);

        coffee1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                coffee1.animate()
//                        .rotationX(360).rotationY(360)
//                        .setDuration(1000)
//                        .setInterpolator(new LinearInterpolator())
//                        .setListener(new AnimatorListenerAdapter() {
//                            @Override
//                            public void onAnimationEnd(Animator animator) {
//                                coffee1.setRotationX(0);
//                                coffee1.setRotationY(0);
//
//                                Intent intent=new Intent(getActivity().getApplication(),Lavazza.class);
//                                Bundle bundle=new Bundle();
//                                bundle.putString("cus_id",cus_id);
//                                bundle.putString("lavazza",lavazza);
//                                bundle.putString("name","The Coffee made For You");
//                                bundle.putString("url","http://www.lavazza.in/in/at-home/blends/");
//                                intent.putExtras(bundle);
//                                startActivity(intent);
//                            }
//                        });

                Intent intent=new Intent(getActivity().getApplication(),Lavazza.class);
                Bundle bundle=new Bundle();
                bundle.putString("cus_id",cus_id);
                bundle.putString("lavazza",lavazza);
                bundle.putString("name","The Coffee made For You");
                bundle.putString("url","http://www.lavazza.in/in/at-home/blends/");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        coffee2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                coffee2.animate()
//                        .rotationX(360).rotationY(360)
//                        .setDuration(1000)
//                        .setInterpolator(new LinearInterpolator())
//                        .setListener(new AnimatorListenerAdapter() {
//                            @Override
//                            public void onAnimationEnd(Animator animator) {
//                                coffee2.setRotationX(0);
//                                coffee2.setRotationY(0);
//
//                                Intent intent=new Intent(getActivity().getApplication(),Lavazza.class);
//                                Bundle bundle=new Bundle();
//                                bundle.putString("cus_id",cus_id);
//                                bundle.putString("lavazza",lavazza);
//                                bundle.putString("name","For Espresso Professional");
//                                bundle.putString("url","http://www.lavazza.in/in/away-from-home/horeca-professionals/");
//                                intent.putExtras(bundle);
//                                startActivity(intent);
//                            }
//                        });

//                Intent intent=new Intent(getActivity().getApplication(),Lavazza.class);
//                Bundle bundle=new Bundle();
//                bundle.putString("cus_id",cus_id);
//                bundle.putString("name","For Espresso Professional");
//                bundle.putString("url","http://www.lavazza.in/in/away-from-home/horeca-professionals/");
//                intent.putExtras(bundle);
//                startActivity(intent);


                Intent intent=new Intent(getActivity().getApplication(),Lavazza.class);
                Bundle bundle=new Bundle();
                bundle.putString("cus_id",cus_id);
                bundle.putString("lavazza",lavazza);
                bundle.putString("name","Great Coffee and More");
                bundle.putString("url","http://www.lavazza.in/in/lavazza-world/company/fresh-and-honest/");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        coffee3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                coffee3.animate()
//                        .rotationX(360).rotationY(360)
//                        .setDuration(1000)
//                        .setInterpolator(new LinearInterpolator())
//                        .setListener(new AnimatorListenerAdapter() {
//                            @Override
//                            public void onAnimationEnd(Animator animator) {
//                                coffee3.setRotationX(0);
//                                coffee3.setRotationY(0);
//
//                                Intent intent=new Intent(getActivity().getApplication(),Lavazza.class);
//                                Bundle bundle=new Bundle();
//                                bundle.putString("cus_id",cus_id);
//                                bundle.putString("lavazza",lavazza);
//                                bundle.putString("name","Your Coffee Break");
//                                bundle.putString("url","http://www.lavazza.in/in/at-office/");
//                                intent.putExtras(bundle);
//                                startActivity(intent);
//                            }
//                        });

                Intent intent=new Intent(getActivity().getApplication(),Lavazza.class);
                Bundle bundle=new Bundle();
                bundle.putString("cus_id",cus_id);
                bundle.putString("lavazza",lavazza);
                bundle.putString("name","Your Coffee Break");
                bundle.putString("url","http://www.lavazza.in/in/at-office/");
                intent.putExtras(bundle);
                startActivity(intent);


            }
        });

        coffee4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                coffee4.animate()
//                        .rotationX(360).rotationY(360)
//                        .setDuration(1000)
//                        .setInterpolator(new LinearInterpolator())
//                        .setListener(new AnimatorListenerAdapter() {
//                            @Override
//                            public void onAnimationEnd(Animator animator) {
//                                coffee4.setRotationX(0);
//                                coffee4.setRotationY(0);
//
//                                Intent intent=new Intent(getActivity().getApplication(),Lavazza.class);
//                                Bundle bundle=new Bundle();
//                                bundle.putString("cus_id",cus_id);
//                                bundle.putString("lavazza",lavazza);
//                                bundle.putString("name","Great Coffee and More");
//                                bundle.putString("url","http://www.lavazza.in/in/lavazza-world/company/fresh-and-honest/");
//                                intent.putExtras(bundle);
//                                startActivity(intent);
//                            }
//                        });


                Intent intent=new Intent(getActivity().getApplication(),Lavazza.class);
                Bundle bundle=new Bundle();
                bundle.putString("cus_id",cus_id);
                bundle.putString("lavazza",lavazza);
                bundle.putString("name","For Espresso Professional");
                bundle.putString("url","http://www.lavazza.in/in/away-from-home/horeca-professionals/");
                intent.putExtras(bundle);
                startActivity(intent);
//                Intent intent=new Intent(getActivity().getApplication(),Lavazza.class);
//                Bundle bundle=new Bundle();
//                bundle.putString("cus_id",cus_id);
//                bundle.putString("name","Great Coffee and More");
//                bundle.putString("url","http://www.lavazza.in/in/lavazza-world/company/fresh-and-honest/");
//                intent.putExtras(bundle);
//                startActivity(intent);

            }
        });


//        mAdapter = new ViewPagerAdapter(getActivity().getApplication(), mImageResources);
//        intro_images.setAdapter(mAdapter);
//        intro_images.setCurrentItem(0);
//        intro_images.setOnPageChangeListener(this);
//        setUiPageViewController();


//        portal= (ImageView) view.findViewById(R.id.portal);
//        portal.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                view.startAnimation(AnimationUtils.loadAnimation(getActivity().getApplication(), R.anim.image_click));
//
//                Intent i =new Intent(getActivity().getApplication(),CustomerPortal.class);
//                startActivity(i);
//            }
//        });

        return view;
    }

    @Override
    public void onStop() {

        sliderLayout.stopAutoCycle();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                sliderLayout.startAutoCycle();
            }
        }, 2000);

        super.onStop();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

//        Toast.makeText(getActivity().getApplication(),slider.getBundle().get("extra") + "",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

    @Override
    public void onPageSelected(int position) {

        Log.d("Slider Demo", "Page Changed: " + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {}


//    private void setUiPageViewController() {
//
//        dotsCount = mAdapter.getCount();
//        dots = new ImageView[dotsCount];
//
//        for (int i = 0; i < dotsCount; i++) {
//            dots[i] = new ImageView(getActivity().getApplication());
//            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.nonselecteditem_dot));
//
//            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
//                    LinearLayout.LayoutParams.WRAP_CONTENT,
//                    LinearLayout.LayoutParams.WRAP_CONTENT
//            );
//
//            params.setMargins(4, 0, 4, 0);
//
//            pager_indicator.addView(dots[i], params);
//        }
//
//        dots[0].setImageDrawable(getResources().getDrawable(R.drawable.selecteditem_dot));
//    }



//    @Override
//    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//    }
//
//    @Override
//    public void onPageSelected(int position) {
//        for (int i = 0; i < dotsCount; i++) {
//            dots[i].setImageDrawable(getResources().getDrawable(R.drawable.nonselecteditem_dot));
//        }
//
//        dots[position].setImageDrawable(getResources().getDrawable(R.drawable.selecteditem_dot));
//
//        if (position + 1 == dotsCount) {
////            btnNext.setVisibility(View.GONE);
////            btnFinish.setVisibility(View.VISIBLE);
//        } else {
////            btnNext.setVisibility(View.VISIBLE);
////            btnFinish.setVisibility(View.GONE);
//        }
//    }
//
//    @Override
//    public void onPageScrollStateChanged(int state) {
//
//    }

}

