package com.example.afrasali.fb1;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class RegisterActivity extends AppCompatActivity {
    ImageView img;
    byte[] imgbyte;
    Spinner mSpin,ySpin,dSpin;
    String[] month={"month","jan","feb","march","april","may","june","july","aug","sep","oct","nov","dec"};
    String[] year={"year","1995","1996","1997","1998","1999","2000","2001","2002","2003"};
    String[] date={"date","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","29","30","31"};
    String ansMonth;
    String ansDate;
    String ansYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
       img=(ImageView)findViewById(R.id.imageView);
        mSpin=(Spinner)findViewById(R.id.monthSpin);
        ySpin=(Spinner)findViewById(R.id.yearSpin);
        dSpin=(Spinner)findViewById(R.id.dateSpin);
        SpinnerAdapter s=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,month);
        mSpin.setAdapter(s);
        SpinnerAdapter s1=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,year);
        ySpin.setAdapter(s1);
        SpinnerAdapter s2=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,date);
        dSpin.setAdapter(s2);
        mSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(RegisterActivity.this,month[position], Toast.LENGTH_SHORT).show();
                ansMonth=month[position];

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        ySpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               // Toast.makeText(RegisterActivity.this,month[position], Toast.LENGTH_SHORT).show();
                ansYear=year[position];

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        dSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               // Toast.makeText(RegisterActivity.this,month[position], Toast.LENGTH_SHORT).show();
                ansDate=date[position];

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    public void PUpClick(View view){
        Intent i=new Intent(Intent.ACTION_PICK);
        i.setType("image/*");
        startActivityForResult(i,102);
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        if (requestCode==102 &&resultCode==RESULT_OK && data!=null){
            Uri uri=data.getData();

            try {
                InputStream in=getContentResolver().openInputStream(uri);
                Bitmap bmp= BitmapFactory.decodeStream(in);
                img.setImageBitmap(bmp);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
    }






    public void SaveClick(View  view){
        EditText user=(EditText)findViewById(R.id.RegName);
        EditText pwd=(EditText)findViewById(R.id.RegPwd);
        EditText email=(EditText)findViewById(R.id.RegEmail);

        //toSting
        String usertxt=user.getText().toString().trim();
        String pwdtxt=pwd.getText().toString().trim();
        String emailtxt=email.getText().toString().trim();
        SaveUser saveuser=new SaveUser(this);

        ContentValues values=new ContentValues();
        imgbyte=convertimagetobyte( img);               //calling imaage byte
        values.put("vchar_name",usertxt);
        values.put("vchar_pwd",pwdtxt);
        values.put("vchar_email",emailtxt);
        values.put("pic",imgbyte);
        values.put("date",ansDate);
        values.put("month",ansMonth);
        values.put("year",ansYear);
        SQLiteDatabase db=saveuser.getWritableDatabase();
        long row=db.insert("tbl_user",null,values);
        Toast.makeText(this, "Successfully Registerd", Toast.LENGTH_SHORT).show();
        Intent i=new Intent(RegisterActivity.this,LoginActivity.class);
        startActivity(i);
        if(row>1){

            db = saveuser.getReadableDatabase();
            String[] columns={"vchar_name","vchar_pwd","pic"};
            Cursor c=db.query("tbl_user",columns,null,null,null,null,null);

            while(c.moveToNext()){
                Toast.makeText(this, ""+c.getString(0)+c.getString(1), Toast.LENGTH_SHORT).show();
               }
        }
    }

    private byte[] convertimagetobyte(ImageView img) {
        Bitmap  bitmap =((BitmapDrawable)img.getDrawable()).getBitmap();
        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,70,stream);
        byte[] dat=stream.toByteArray();
        return dat;
    }
}
