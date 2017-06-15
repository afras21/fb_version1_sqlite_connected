package com.example.afrasali.fb1;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import static com.example.afrasali.fb1.R.id.SetProPic;

public class NewsFeed extends AppCompatActivity {
    ImageView img;
    TextView name,email,month,date,year;
    Bitmap b;
    byte[] blob;
    Bundle bundle;
    String user,pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_feed);
        img = (ImageView) findViewById(R.id.SetProPic);
        SaveUser s = new SaveUser(this);
        SQLiteDatabase db = s.getReadableDatabase();
        bundle=getIntent().getExtras();
        user=bundle.getString("name");
        pass=bundle.getString("password");
        String[] ar={user,pass};
        String[] arr = {"pic","vchar_name","vchar_email","date","month","year"};


        Cursor c = db.query("tbl_user", arr,"vchar_name=? and vchar_pwd=? ",ar, null, null, null);
        c.moveToFirst();
       // System.out.println("Count= "+c.getCount());
        blob = c.getBlob(0);
        b = BitmapFactory.decodeByteArray(blob, 0, blob.length);
        name=(TextView)findViewById(R.id.dispName);
        email=(TextView)findViewById(R.id.dispEmail);
        date=(TextView)findViewById(R.id.dispDate);
        month=(TextView)findViewById(R.id.dispMonth);
        year=(TextView)findViewById(R.id.dispYear);
        img.setImageBitmap(b);
        name.setText(c.getString(1));
        email.setText(c.getString(2));
        date.setText(c.getString(3));
        month.setText(c.getString(4));
        year.setText(c.getString(5));


    }

    public void camClick(View view){
        Toast.makeText(this, "Share Memories", Toast.LENGTH_SHORT).show();
    }

    public void audioClick(View view){
        Toast.makeText(this, "Share Audios", Toast.LENGTH_SHORT).show();
    }

    public void liveClick(View view){
        Toast.makeText(this, "BE Live On Fb", Toast.LENGTH_SHORT).show();
    }

    public void storyClick(View view){
        Toast.makeText(this, "Submit your stories", Toast.LENGTH_SHORT).show();
    }





}