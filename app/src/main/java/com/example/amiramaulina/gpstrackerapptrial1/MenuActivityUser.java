package com.example.amiramaulina.gpstrackerapptrial1;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MenuActivityUser extends AppCompatActivity {


    LatLng friendLatLng;
    String latitude,longitude,name,userid,prevdate;
    Toolbar toolbar;

    Switch mSwitch;

    DatabaseReference nref;
    String fallstateTimestamp;
    String hrstateValueTimestamp;
    String[] fallcheck;
    String[] hrcheck;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_menu);

        //i = 1;
        //j = 2;
        /*mSwitch = findViewById(R.id.switch1);
        mSwitch.setOnCheckedChangeListener(checkBtnChange);*/

        Log.i("tes", "tes int i " + 1);
        Log.i("tes", "tes create hr " + hrcheck );
        Log.i("tes", "tes create fall " + fallcheck );
        Log.i("tes", "tes create time fall " + fallstateTimestamp);
        Log.i("tes", "tes create time hr " + hrstateValueTimestamp);



        fallcheck = new String[1];
        hrcheck = new String[1];


        Intent intent = getIntent();
        if(intent!=null)
        {
            latitude=intent.getStringExtra("latitude");
            longitude = intent.getStringExtra("longitude");
            name = intent.getStringExtra("name");
            userid = intent.getStringExtra("userid");
            prevdate = intent.getStringExtra("date");
        }

        nref = FirebaseDatabase.getInstance().getReference().child("Users").child(userid);
        nref.child("fallstate").child("nilaifallstate").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                fallstateTimestamp = dataSnapshot.child("Timestamp").getValue(String.class);
                Log.i("tes", "tes fall timestamp awal " + fallstateTimestamp);

                /*if (i == 1){
                    fallcheck[0] = fallstateTimestamp;
                    Log.i("tes", "tes fallcheck awal" + fallcheck[0]);
                }*/


            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {


            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }


        });

        nref.child("hrstate").child("nilaihrstate").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                hrstateValueTimestamp = dataSnapshot.child("Timestamp").getValue(String.class);

                Log.i("tes", "tes heart timestamp awal " + hrstateValueTimestamp);

                /*if (i == 1) {
                    hrcheck[0] = hrstateValueTimestamp;
                    Log.i("tes", "tes hrcheck awal" + hrcheck[0]);
                }*/

            }


            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        nref.child("fallstate").child("nilaifallstate").addValueEventListener(new ValueEventListener() {
            public void onDataChange(DataSnapshot dataSnapshot) {
                /*if (i == 2){
                    if (fallcheck[0] == null) {
                        fallcheck[0] = fallstateTimestamp;
                        Log.i("tes", "tes fallstring " + fallcheck[0]);
                    }
                    else if (!fallstateTimestamp.equals(fallcheck[0])){
                        Log.i("tes", "tes fallcheck akhir " + fallcheck[0]);
                        Log.i("tes", "tes fall timestamp " + fallstateTimestamp);
                        showNotificationFall();
                        fallcheck[0] = fallstateTimestamp;
                    }

                }
                else if (i == 1) {

                }*/

                if (fallcheck[0] == null) {
                    fallcheck[0] = fallstateTimestamp;
                    Log.i("tes", "tes fallstring " + fallcheck[0]);
                }
                else if (!fallstateTimestamp.equals(fallcheck[0])){
                    Log.i("tes", "tes fallcheck akhir " + fallcheck[0]);
                    Log.i("tes", "tes fall timestamp " + fallstateTimestamp);
                    showNotificationFall();
                    fallcheck[0] = fallstateTimestamp;
                }
            }
            public void onCancelled(DatabaseError databaseError) { }
        });

        nref.child("hrstate").child("nilaihrstate").addValueEventListener(new ValueEventListener() {
            public void onDataChange(DataSnapshot dataSnapshot) {
                /*if (i == 2){
                    if (hrcheck[0] == null) {
                        hrcheck[0] = hrstateValueTimestamp;
                        Log.i("tes", "tes hrstring " + hrcheck[0]);
                    } else if (!hrstateValueTimestamp.equals(hrcheck[0])) {
                        Log.i("tes", "tes hrcheck akhir " + hrcheck[0]);
                        Log.i("tes", "tes hr timestamp " + hrstateValueTimestamp);
                        showNotificationHR();
                        hrcheck[0] = hrstateValueTimestamp;
                    }
                }
                else if (i == 1){

                }*/
                if (hrcheck[0] == null) {
                    hrcheck[0] = hrstateValueTimestamp;
                    Log.i("tes", "tes hrstring " + hrcheck[0]);
                } else if (!hrstateValueTimestamp.equals(hrcheck[0])) {
                    Log.i("tes", "tes hrcheck akhir " + hrcheck[0]);
                    Log.i("tes", "tes hr timestamp " + hrstateValueTimestamp);
                    showNotificationHR();
                    hrcheck[0] = hrstateValueTimestamp;
                }


            }

            public void onCancelled(DatabaseError databaseError) { }
        });



    }

    /*CompoundButton.OnCheckedChangeListener checkBtnChange = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
            if (isChecked) {
                i = 2;
                Log.i("tes", "tes switch on. i= " + i);
            } else {
                i = 1;
                Log.i("tes", "tes switch off. i= " + i);
            }
        }
    };*/


    public void showNotificationFall() {
        final NotificationManager mgr = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder note = new NotificationCompat.Builder(getApplicationContext());
        note.setContentTitle("FALL DETECTED ON YOUR FAMILY MEMBER!");
        note.setContentText("Click here to see their location!");
        note.setTicker("FALL DETECTED ON YOUR FAMILY MEMBER!");
        note.setAutoCancel(true);
        note.setPriority(Notification.PRIORITY_HIGH);
        note.setVibrate(new long[] {0, 100, 100, 100});
        note.setDefaults(Notification.DEFAULT_SOUND);
        note.setSmallIcon(R.mipmap.ic_launcher);
        Intent notificationIntent = new Intent(getApplicationContext(), LiveMapActivity.class);
        notificationIntent.putExtra("latitude", latitude);
        notificationIntent.putExtra("longitude", longitude);
        notificationIntent.putExtra("name", name);
        notificationIntent.putExtra("userid", userid);
        notificationIntent.putExtra("date", prevdate);
        PendingIntent pi = PendingIntent.getActivity(getApplicationContext(), 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        note.setContentIntent(pi);
        mgr.notify(692030, note.build());
    }

    public void showNotificationHR() {
        final NotificationManager mgr = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder note = new NotificationCompat.Builder(getApplicationContext());
        note.setContentTitle("HEART RATE ABNORMALITY ON YOUR FAMILY MEMBER!");
        note.setContentText("Click here to check their location!");
        note.setTicker("HEART RATE ABNORMALITY ON YOUR FAMILY MEMBER!");
        note.setAutoCancel(true);
        note.setPriority(Notification.PRIORITY_HIGH);
        note.setVibrate(new long[] {0, 100, 100, 100});
        note.setDefaults(Notification.DEFAULT_SOUND);
        note.setSmallIcon(R.mipmap.ic_launcher);
        Intent notificationIntent = new Intent(getApplicationContext(), LiveMapActivity.class);
        notificationIntent.putExtra("latitude", latitude);
        notificationIntent.putExtra("longitude", longitude);
        notificationIntent.putExtra("name", name);
        notificationIntent.putExtra("userid", userid);
        notificationIntent.putExtra("date", prevdate);
        PendingIntent pi = PendingIntent.getActivity(getApplicationContext(), 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        note.setContentIntent(pi);
        mgr.notify(692030, note.build());
    }

    public void gotoLocation(View v){
        Intent myIntent = new Intent(MenuActivityUser.this,LiveMapActivity.class);
        myIntent.putExtra("latitude", latitude);
        myIntent.putExtra("longitude", longitude);
        myIntent.putExtra("name", name);
        myIntent.putExtra("userid", userid);
        myIntent.putExtra("date", prevdate);
        startActivity(myIntent);
    }

    //grafik hrstate
    public void gotoHRStateHistory(View v){
        Intent myIntent = new Intent(MenuActivityUser.this,HRStateHistory.class);
        myIntent.putExtra("userid", userid);
        myIntent.putExtra("name", name);
        startActivity(myIntent);
    }

    public void gotoFallHistory(View v){
        Intent myIntent = new Intent(MenuActivityUser.this,FallHistory.class);
        myIntent.putExtra("latitude", latitude);
        myIntent.putExtra("longitude", longitude);
        myIntent.putExtra("name", name);
        myIntent.putExtra("userid", userid);
        myIntent.putExtra("date", prevdate);
        startActivity(myIntent);
    }

    // grafik hrvalue
    public void gotoHRStatistics(View v){
        Intent myIntent = new Intent(MenuActivityUser.this,HRStatistics.class);
        myIntent.putExtra("userid", userid);
        myIntent.putExtra("name", name);
        startActivity(myIntent);
    }


}
