package com.example.hackathon;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Reservation_1 extends AppCompatActivity {
    TextView shopName, shopAddr, shopZipcode;
    ConstraintLayout dayService;
    ImageView dayServiceCheck;
    Boolean flag = false; //당일 픽업 버튼 클릭 여부 판별 (true 가 선택된 상태)
    RadioGroup radioGroup;
    RadioButton dayBtn, nightBtn;
    Button nextBtn;
    ImageView closeBtn;
    TextView selDate;
    LinearLayout menuHome, menuBoard, menuUser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reserve_1);

        //hide action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        //id 찾기
        shopName = (TextView) findViewById(R.id.shop_name);
        shopAddr = (TextView) findViewById(R.id.shop_addr);
        shopZipcode = (TextView) findViewById(R.id.shop_zipcode);
        dayService = (ConstraintLayout) findViewById(R.id.day_service);
        dayServiceCheck = (ImageView) findViewById(R.id.day_service_uncheck);
        radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        dayBtn = (RadioButton) findViewById(R.id.radio_button_day);
        nightBtn = (RadioButton) findViewById(R.id.radio_button_night);
        nextBtn = (Button) findViewById(R.id.reserve_1_ok);
        closeBtn = (ImageView) findViewById(R.id.close_1);
        selDate = (TextView) findViewById(R.id.sel_date);
        menuHome = (LinearLayout) findViewById(R.id.home_menu_re1);
        menuBoard = (LinearLayout) findViewById(R.id.board_menu_re1);
        menuUser = (LinearLayout) findViewById(R.id.user_menu_re1);


        ///* 바텀 메뉴 *///
        //예약하기 버튼
        menuHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        
        //세탁소 이름 설정
        Intent intent = getIntent();
        String shopNameString = intent.getStringExtra("name");
        shopName.setText(shopNameString);
        
        //세탁소 주소 설정
        String shopAddrString = shopNameString + " 의 주소";
        shopAddr.setText(shopAddrString);
        
        //세탁소 우편번호 설정
        shopZipcode.setText(shopNameString + " 의 우편번호");

        //닫기 버튼
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent1);
            }
        });


        //날짜 선택을 위한 datePickerDialog
        Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE");
                Date date = new Date(mYear, mMonth, dayOfMonth-1);
                String temp = simpleDateFormat.format(date);
                String mDayString = getDay(temp);

                selDate.setText(year+"-"+(month+1)+"-"+dayOfMonth+" ("+mDayString+")");
            }
        }, mYear, mMonth, mDay);

        //날짜 선택
        selDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show();
            }
        });


        //당일 픽업 버튼 클릭 시
        dayService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flag == false) {
                    dayService.setBackground(getDrawable(R.drawable.round_menus_selected));
                    dayServiceCheck.setImageResource(R.drawable.checked);
                    flag = true;
                }
                else {
                    dayService.setBackground(getDrawable(R.drawable.round_menus));
                    dayServiceCheck.setImageResource(R.drawable.unchecked);
                    flag = false;
                }
            }
        });

        //시간 선택시
        dayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radioGroup.check(R.id.radio_button_day);
                dayBtn.setBackground(getDrawable(R.drawable.round_menus_selected));
                nightBtn.setBackground(getDrawable(R.drawable.round_menus));
            }
        });
        nightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radioGroup.check(R.id.radio_button_night);
                nightBtn.setBackground(getDrawable(R.drawable.round_menus_selected));
                dayBtn.setBackground(getDrawable(R.drawable.round_menus));
            }
        });
        
        
        
        //선택 완료 버튼 클릭 시
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getApplicationContext(), Reservation_2.class);
                intent1.putExtra("name", shopNameString);
                intent1.putExtra("dayService", flag);
                startActivity(intent1);
            }
        });

    }

    public static String getDay(String day) {
        String dayKor;

        switch (day) {
            case "Sunday":
                dayKor = "일";
                break;
            case "Monday":
                dayKor = "월";
                break;
            case "Tuesday":
                dayKor = "화";
                break;
            case "Wednesday":
                dayKor = "수";
                break;
            case "Thursday":
                dayKor = "목";
                break;
            case "Friday":
                dayKor = "금";
                break;
            default:
                dayKor = "토";
                break;
        }

        return dayKor;

    }


    //Load DB
    List shopList = new ArrayList();

    private void initLoadShopListDB() {
        DBHelper dbHelper = new DBHelper(getApplicationContext());
        dbHelper.OpenDBFile();

        shopList = dbHelper.getTableData();
        Log.e("test", String.valueOf(shopList.size()));

        dbHelper.close();


    }

}
