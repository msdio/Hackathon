/* DB 파일을 열고 읽고 정보를 가져올 수 있는 클래스 */
package com.example.hackathon;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    protected  static String TAG = "DBHelper";

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "shop_list.db";
    private static final String TABLE_NAME = "shop_list";
    private static String DB_PATH = "C:/Users/Kim/Desktop/shop_list.db";

    private final Context mContext;
    private SQLiteDatabase mDatabase;

    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);

        if(Build.VERSION.SDK_INT >= 17) {
            DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
        }
        else {
            DB_PATH = "/data/data" + context.getPackageName() + "/databases/";
        }

        this.mContext = context;
    }

    //DB 파일 열기
    public boolean OpenDBFile() throws SQLException {
        if(!CheckDBFileExist()) {
            CreateDB();
        }

        String mPath = DB_PATH + DB_NAME;
        try {
            mDatabase = SQLiteDatabase.openDatabase(mPath, null, SQLiteDatabase.CREATE_IF_NECESSARY);
            Log.e(TAG, "[SUCCESS] " + DB_NAME + " are Opened");
        }
        catch(SQLException sqlException) {
            Log.e(TAG, "[ERROR]" + "Can't Open Database");
        }

        return mDatabase != null;
    }

    //DB 파일 존재 여부 확인
    public boolean CheckDBFileExist() {
        File file = new File(DB_PATH + DB_NAME);
        return file.exists();
    }

    //Create DB
    public void CreateDB() throws SQLException {
        this.getReadableDatabase();
        this.close();

        try {
            CopyDBFile();
            Log.e(TAG, "[SUCCESS] " + DB_NAME + " are Created");
        }
        catch(IOException ioException) {
            Log.e(TAG, "[ERROR] " + "Unable to create " + DB_NAME); //error msg
            throw new Error(TAG);
        }
    }

    //Copy DB
    public void CopyDBFile() throws IOException {
        InputStream inputStream = mContext.getAssets().open(DB_NAME);
        String outputFileName = DB_PATH + DB_NAME;
        OutputStream outputStream = new FileOutputStream(outputFileName);

        byte[] buffer = new byte[1024];
        int length;
        while((length = inputStream.read(buffer)) > 0) {
            outputStream.write(buffer, 0, length);
        }

        outputStream.flush();
        outputStream.close();
        inputStream.close();
    }
    
    //Table 정보 가져오기
    public List getTableData() {
        try {
            List mList = new ArrayList(); //테이블 정보 저장

            String sql = "SELECT * FROM " + TABLE_NAME;

            Cursor mCursor = mDatabase.rawQuery(sql, null);
            
            if(mCursor != null) { //테이블 끝까지 읽기
                while(mCursor.moveToNext()) {
                    Shop_list shopList = new Shop_list(); //row 저장

                    shopList.setIndex(mCursor.getInt(0));
                    shopList.setName(mCursor.getString(1));
                    shopList.setAddr(mCursor.getString(2));
                    shopList.setZipcode(mCursor.getInt(3));
                    shopList.setLongitude(mCursor.getDouble(4));
                    shopList.setLatitude(mCursor.getDouble(5));

                    mList.add(shopList);
                    
                }
            }

            return mList;
        } catch (SQLException mSQLException) {
            Log.e(TAG, mSQLException.toString());
            throw mSQLException;
        }
    }

    //close DB
    public void CloseDBFile() {
        if(mDatabase != null) {
            mDatabase.close();
        }
    }

    @Override
    public synchronized void close() {
        CloseDBFile();
        super.close();
    }


    //DB 생성 시에 호출
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }
    
    
    //달력 주소 조회
    public String getShopAddr(String shop_name) {
        String shop_addr;

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT addr FROM shop_list WHERE name=shop_name", null);
        
        if(cursor.getCount() != 0) { //데이터가 존재할 경우
            shop_addr = cursor.getString(cursor.getColumnIndexOrThrow("shop_addr"));
        } else {
            shop_addr = "unable to find address";
        }
        
        
        return shop_addr;
    }
}