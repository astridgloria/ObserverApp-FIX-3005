package com.example.amiramaulina.gpstrackerapptrial1;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.style.ImageSpan;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.concurrent.atomic.AtomicBoolean;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth = FirebaseAuth.getInstance();

        user = auth.getCurrentUser();


        if(user == null)
        {
            setContentView(R.layout.activity_main);


        }
        else
        {
            Intent myIntent = new Intent(MainActivity.this,MyNavigationTutorial.class);
            startActivity(myIntent);
            finish();

        }
        WebView view = (WebView) findViewById(R.id.textContent);
        WebSettings webSettings = view.getSettings();
        webSettings.setDefaultFontSize(21);
        String text;
        text = "<html><body><p align=\"justify\">";
        text+= "Welcome to Ambient Assisted Living - Family Tracker App. <br />" +
                "This is an application to keep track of your family member's health condition. <br /><br />" +
                "To get started, click on the menu below. ";
        text+= "</p></body></html>";
        view.loadData(text, "text/html", "utf-8");


    }


    public void getStarted_click(View v)
    {

        Intent myintent = new Intent(MainActivity.this,RegisterEmailActivity.class);
        startActivity(myintent);
        finish();

    }

    public void LoginUser(View v)
    {

        Intent myIntent = new Intent(MainActivity.this,LoginEmailActivity.class);
        startActivity(myIntent);
        finish();

    }







}
