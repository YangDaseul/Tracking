package com.example.tracking;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DataBaseHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDB = new DataBaseHelper(this);

//        addData();
        readData();
    }

    private void addData(){
        boolean isInserted = false;
        for (int i = 0; i < 40000; i++){
            int startTime = 20000 + i;
            isInserted = myDB.insertData(String.valueOf(startTime), "asd", "asd", "24", "M", "156", "54", "홍길동");

        }
//        boolean isInserted = myDB.insertData("20231223123235", "asd", "asd", "24", "M", "156", "54", "홍길동");

        if (isInserted == true){
            Toast.makeText(MainActivity.this, "데이터 추가 성공", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(MainActivity.this, "데이터 추가 실패", Toast.LENGTH_LONG).show();
        }
    }

    private void readData(){
        Cursor cursor = myDB.readData();
        if (cursor.getCount() == 0){
            Toast.makeText(MainActivity.this, "데이터 조회 실패", Toast.LENGTH_LONG).show();
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext()){
            buffer.append("START_TIME : " + cursor.getString(0) + "\n");
            buffer.append("TITLE : " + cursor.getString(1) + "\n");
            buffer.append("MEMO : " + cursor.getString(2) + "\n");
            buffer.append("AGE : " + cursor.getString(3) + "\n");
            buffer.append("GENDER : " + cursor.getString(4) + "\n");
            buffer.append("HEIGHT : " + cursor.getString(5) + "\n");
            buffer.append("WEIGHT : " + cursor.getString(6) + "\n");
            buffer.append("NAME : " + cursor.getString(7) + "\n");
        }
        showMessage("데이터", buffer.toString());

    }

    private void showMessage(String title, String msg){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.show();
    }


}