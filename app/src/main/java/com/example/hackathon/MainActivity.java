package com.example.hackathon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Build;
import android.os.Bundle;

import android.provider.ContactsContract;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    ImageView mainMenu, closeNav;
    DrawerLayout drawerLayout;
    View drawerView; //메인화면 네비게이션
    ImageView img_jw; //장운 사진
    ImageView shop1, shop2, shop3, shop4, shopPlus; //내 주변 가게
    TextView shopName1, shopName2, shopName3, shopName4;
    TextView shopName;
    LinearLayout menuReserve, menuBoard, menuUser;

    //날씨 배너 변수 선언
    ViewPager2 mBanner;
    FragmentStateAdapter bannerAdapter;
    int num_page = 2;
    
    


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //hide action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();


        //id 찾기
        mainMenu = (ImageView) findViewById(R.id.menu_main);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerView = (View) findViewById(R.id.drawer_view);
        drawerLayout.setDrawerListener(listener);
        closeNav = (ImageView) findViewById(R.id.nav_close);
        shop1 = (ImageView) findViewById(R.id.around_me_1);
        shop2 = (ImageView) findViewById(R.id.around_me_2);
        shop3 = (ImageView) findViewById(R.id.around_me_3);
        shop4 = (ImageView) findViewById(R.id.around_me_4);
        shopPlus = (ImageView) findViewById(R.id.around_me_plus);
        shopName1 = (TextView) findViewById(R.id.around_me_1_text);
        shopName2 = (TextView) findViewById(R.id.around_me_2_text);
        shopName3 = (TextView) findViewById(R.id.around_me_3_text);
        shopName4 = (TextView) findViewById(R.id.around_me_4_text);
        shopName = (TextView) findViewById(R.id.shop_name);
        menuReserve = (LinearLayout) findViewById(R.id.menu_reserve);
        menuBoard = (LinearLayout) findViewById(R.id.menu_board);
        menuUser = (LinearLayout) findViewById(R.id.menu_user);



        ///* 바텀 메뉴 *///
        //예약하기 버튼
        menuReserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });



        ///* 날씨 배너 *///
        //viewpager2
        mBanner = findViewById(R.id.viewpager);
        //Adapter
        bannerAdapter = new BannerAdapter(this, num_page);
        mBanner.setAdapter(bannerAdapter);
        //viewpager setting
        mBanner.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        //배너 시작지점
        mBanner.setCurrentItem(500);
        //최대 이미지 개수 설정
        mBanner.setOffscreenPageLimit(2);

        mBanner.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                if(positionOffsetPixels ==0) {
                    mBanner.setCurrentItem(position);
                }
            }

        });


        //이미지 원형처리
        img_jw = (ImageView) findViewById(R.id.img_jw);
        img_jw.setBackground(new ShapeDrawable(new OvalShape()));
        if(Build.VERSION.SDK_INT>=21) {
            img_jw.setClipToOutline(true);
        }



        //메인 메뉴에서 네비게이션 창 열기
        mainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(drawerView);
            }
        });
        //x버튼 누르면 네비게이션 창 닫기
        closeNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.closeDrawer(drawerView);
            }
        });


        ///* 내 주변가게를 클릭하면 예약화면으로 넘어가기 *///
        shop1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Reservation_1.class); //클릭한 가게의 정보 넘기기
                intent.putExtra("name", shopName1.getText());
                startActivity(intent);
            }
        });

        shop2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Reservation_1.class);
                intent.putExtra("name", shopName2.getText());
                startActivity(intent);
            }
        });

        shop3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Reservation_1.class);
                intent.putExtra("name", shopName3.getText());
                startActivity(intent);
            }
        });

        shop4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Reservation_1.class);
                intent.putExtra("name", shopName4.getText());
                startActivity(intent);
            }
        });



    }

    //drawer 리스너
    DrawerLayout.DrawerListener listener = new DrawerLayout.DrawerListener() {
        @Override
        public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

        }

        @Override
        public void onDrawerOpened(@NonNull View drawerView) {

        }

        @Override
        public void onDrawerClosed(@NonNull View drawerView) {

        }

        @Override
        public void onDrawerStateChanged(int newState) {

        }
    };


}