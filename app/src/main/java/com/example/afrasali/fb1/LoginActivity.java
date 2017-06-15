package com.example.afrasali.fb1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
RelativeLayout rw,r1;
    TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        rw=(RelativeLayout)findViewById(R.id.rw);
        r1=(RelativeLayout)findViewById(R.id.r1);
        rw.setVisibility(View.GONE);
    }
    public void RegHereClick(View view){
        Intent i=new Intent(LoginActivity.this,RegisterActivity.class);
        startActivity(i);
    }

    public void LClick(View view){
        SaveUser s=new SaveUser(this);
        EditText user=(EditText)findViewById(R.id.GetUser);
        EditText pwd=(EditText)findViewById(R.id.GetPwd);
        t=(TextView)findViewById(R.id.warn);


        String luser=user.getText().toString().toLowerCase().trim();
        String lpwd=pwd.getText().toString().toLowerCase().trim();
        int count;
        count=s.LoginCheck(luser);

        if (count==0){
            Toast.makeText(this, "Incorrect UserName", Toast.LENGTH_SHORT).show();
            rw.setVisibility(View.VISIBLE);
            t.setText("The email address you have enterd doesnot match any account ,signUp for an account");


        }
        else{
            int count1=s.PCheck(luser,lpwd);
              if(count1==0){
                  Toast.makeText(this, "Incorrect Password", Toast.LENGTH_SHORT).show();
              }
              else {
                  rw.setVisibility(View.GONE);


                  Intent i=new Intent(LoginActivity.this,NewsFeed.class);
                  i.putExtra("name",luser);
                  i.putExtra("password",lpwd);
                  startActivity(i);
                  Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show();
              }
        }


    }
}
