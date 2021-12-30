package com.example.hackathon;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import org.w3c.dom.Text;

public class Reservation_3 extends AppCompatActivity {
    ImageView backBtn, closeBtn;
    Button nextBtn;
    Button changePhone;
    TextView phoneNum;
    EditText phoneNumEdit;
    Boolean flag = true;
    LinearLayout menuHome, menuBoard, menuUser;

    //카드 배너
    private ViewPager2 mBanner;
    private FragmentStateAdapter bannerAdapter;
    private int num_page = 3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reserve_3);

        //hide action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Intent intent = getIntent();
        String shopNameString = intent.getStringExtra("name");

        //id 값 찾기
        nextBtn = (Button) findViewById(R.id.reserve_3_ok);
        closeBtn = (ImageView) findViewById(R.id.close_3);
        backBtn = (ImageView) findViewById(R.id.back_3);
        changePhone = (Button) findViewById(R.id.change_phone);
        phoneNum = (TextView) findViewById(R.id.phone_num);
        phoneNumEdit = (EditText) findViewById(R.id.phone_num_edit);
        menuHome = (LinearLayout) findViewById(R.id.home_menu_re3);
        menuBoard = (LinearLayout) findViewById(R.id.board_menu_re3);
        menuUser = (LinearLayout) findViewById(R.id.user_menu_re3);


        ///* 바텀 메뉴 *///
        //예약하기 버튼
        menuHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        
        
        //전화번호 변경 버튼
        changePhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flag == true) {
                    phoneNum.setVisibility(View.GONE);
                    phoneNumEdit.setVisibility(View.VISIBLE);
                    flag = false;
                }
                else {
                    String phoneTemp = phoneNumEdit.getText().toString();
                    phoneNum.setText(phoneTemp);
                    phoneNumEdit.setVisibility(View.GONE);
                    phoneNum.setVisibility(View.VISIBLE);
                    flag = true;
                }

            }
        });

        //뒤로가기 버튼
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getApplicationContext(), Reservation_2.class);
                intent1.putExtra("name", shopNameString);
                startActivity(intent1);
            }
        });

        //닫기 버튼
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent2);
            }
        });

        //주문하기 버튼
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(getApplicationContext(), Reservation_4.class);
                intent3.putExtra("name", shopNameString);
                startActivity(intent3);
            }
        });


        ///* 카드 배너 *///
        //viewpager2
        mBanner = findViewById(R.id.card_list);
        //Adapter
        bannerAdapter = new CardAdapter(this, num_page);
        mBanner.setAdapter(bannerAdapter);
        //viewpager setting
        mBanner.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        //배너 시작지점
        mBanner.setCurrentItem(1000);
        //최대 이미지 개수 설정
        mBanner.setOffscreenPageLimit(2);

        mBanner.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                if(positionOffsetPixels == 0) {
                    mBanner.setCurrentItem(position);
                }
            }
        });
        
        
        //카드 화면 캐러셀 레이아웃
        final float pageMargin = getResources().getDimensionPixelOffset(R.dimen.pageMargin);
        final float pageOffset = getResources().getDimensionPixelOffset(R.dimen.offset);

        mBanner.setPageTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float myOffset = position * -(2*pageOffset + pageMargin);

                if(mBanner.getOrientation() == ViewPager2.ORIENTATION_HORIZONTAL) {
                    if(ViewCompat.getLayoutDirection(mBanner) == ViewCompat.LAYOUT_DIRECTION_RTL) {
                        page.setTranslationX(-myOffset);
                    } else {
                        page.setTranslationX(myOffset);
                    }
                } else {
                    page.setTranslationY(myOffset);
                }

                

            }
        });
        

    }
}
