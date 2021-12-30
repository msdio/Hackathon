package com.example.hackathon;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class Reservation_4 extends AppCompatActivity {
    Button goMain;
    TextView shopName;
    LinearLayout menuHome, menuBoard, menuUser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reserve_4);

        //hide action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        //id 값 찾기
        goMain = (Button) findViewById(R.id.reserve_3_ok);
        shopName = (TextView) findViewById(R.id.fin_shop_name);
        menuHome = (LinearLayout) findViewById(R.id.home_menu_re4);
        menuBoard = (LinearLayout) findViewById(R.id.board_menu_re4);
        menuUser = (LinearLayout) findViewById(R.id.user_menu_re4);


        ///* 바텀 메뉴 *///
        //예약하기 버튼
        menuHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });


        //세탁소 이름
        Intent intent = getIntent();
        String shopNameString = intent.getStringExtra("name");
        shopName.setText(shopNameString);


        goMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}
