package com.example.hackathon;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class Reservation_2 extends AppCompatActivity {
    ImageView backBtn, closeBtn;
    Button nextBtn;
    TextView min1, num1, plus1, min2, num2, plus2, min3, num3, plus3, min4, num4, plus4, min5, num5, plus5;
    private int count1 = 0, count2 = 0, count3 = 0, count4 = 0, count5 = 0;
    TextView totalAmount;
    LinearLayout menuHome, menuBoard, menuUser;
    RadioGroup rg;
    RadioButton rb1, rb2, rb3, rb4, rb5;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reserve_2);

        //hide action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();


        //가게 이름 받아오기
        Intent intent = getIntent();
        String shopNameString = intent.getStringExtra("name");

        //id 값 찾기
        backBtn = (ImageView) findViewById(R.id.back_2);
        closeBtn = (ImageView) findViewById(R.id.close_2);
        nextBtn = (Button) findViewById(R.id.reserve_2_ok);
        min1 = (TextView) findViewById(R.id.minus_1);
        num1 = (TextView) findViewById(R.id.number_1);
        plus1 = (TextView) findViewById(R.id.plus_1);
        min2 = (TextView) findViewById(R.id.minus_2);
        num2 = (TextView) findViewById(R.id.number_2);
        plus2 = (TextView) findViewById(R.id.plus_2);
        min3 = (TextView) findViewById(R.id.minus_3);
        num3 = (TextView) findViewById(R.id.number_3);
        plus3 = (TextView) findViewById(R.id.plus_3);
        min4 = (TextView) findViewById(R.id.minus_4);
        num4 = (TextView) findViewById(R.id.number_4);
        plus4 = (TextView) findViewById(R.id.plus_4);
        min5 = (TextView) findViewById(R.id.minus_5);
        num5 = (TextView) findViewById(R.id.number_5);
        plus5 = (TextView) findViewById(R.id.plus_5);
        totalAmount = (TextView) findViewById(R.id.total_amount);
        menuHome = (LinearLayout) findViewById(R.id.home_menu_re2);
        menuBoard = (LinearLayout) findViewById(R.id.board_menu_re2);
        menuUser = (LinearLayout) findViewById(R.id.user_menu_re2);
        rg = (RadioGroup) findViewById(R.id.radio_group_2);
        rb1 = (RadioButton) findViewById(R.id.radio_tshirts);
        rb2 = (RadioButton) findViewById(R.id.radio_suit);
        rb3 = (RadioButton) findViewById(R.id.radio_shorts);
        rb4 = (RadioButton) findViewById(R.id.radio_shoe);
        rb5 = (RadioButton) findViewById(R.id.radio_bedsheeets);




        ///* 바텀 메뉴 *///
        //예약하기 버튼
        menuHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });


        //뒤로가기 버튼
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getApplicationContext(), Reservation_1.class);
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
                Intent intent3 = new Intent(getApplicationContext(), Reservation_3.class);
                intent3.putExtra("name", shopNameString);
                startActivity(intent3);
            }
        });


        //옷 종류 선택하는 라디오 그룹
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                rb1.setTextColor(Color.BLACK);
                rb2.setTextColor(Color.BLACK);
                rb3.setTextColor(Color.BLACK);
                rb4.setTextColor(Color.BLACK);
                rb5.setTextColor(Color.BLACK);
                RadioButton selected = (RadioButton) findViewById(id);
                selected.setTextColor(getResources().getColor(R.color.blue));
            }
        });


        //플러스 마이너스 버튼 클릭시
        min1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(count1>0) {
                    count1--;
                    num1.setText(count1 + "");
                }
            }
        });
        plus1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count1++;
                num1.setText(count1+"");
            }
        });

        min2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(count2>0) {
                    count2--;
                    num2.setText(count2 + "");
                }
            }
        });
        plus2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count2++;
                num2.setText(count2+"");
            }
        });

        min3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(count3>0) {
                    count3--;
                    num3.setText(count3 + "");
                }
            }
        });
        plus3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count3++;
                num3.setText(count3+"");
            }
        });

        min4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(count4>0) {
                    count4--;
                    num4.setText(count4 + "");
                }
            }
        });
        plus4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count4++;
                num4.setText(count4+"");
            }
        });

        min5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(count5>0) {
                    count5--;
                    num5.setText(count5 + "");
                }
            }
        });
        plus5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count5++;
                num5.setText(count5+"");
            }
        });



        String totalAmountCloth = "";
        /*
        if(count1 != 0) {
            //totalAmountCloth += "와이셔츠 "+Integer.toString(count1)+", ";
            String temp = "와이셔츠 "+Integer.toString(count1)+", ";
            totalAmountCloth += temp;
        }
        if(count2 != 0) {
            totalAmountCloth += "여성 블라우스 "+Integer.toString(count2)+", ";
        }
        if(count3 != 0) {
            totalAmountCloth += "반팔 티셔츠 "+Integer.toString(count3)+", ";
        }
        if(count4 != 0) {
            totalAmountCloth += "니트 / 스웨터 "+Integer.toString(count4)+", ";
        }
        if(count5 != 0) {
            totalAmountCloth += "원피스 "+Integer.toString(count5);
        }
        */

        totalAmountCloth += "와이셔츠 "+Integer.toString(count1)+", ";

        String finalTotalAmountCloth = totalAmountCloth;
        totalAmount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                totalAmount.setText(finalTotalAmountCloth);
            }
        });


    }
}
