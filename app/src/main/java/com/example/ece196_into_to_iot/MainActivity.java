package com.example.ece196_into_to_iot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;


import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    TextView title_text_view;
    Button button1, button2;

    TextView humidity_textview, temperature_textview;

    DatabaseReference myDatabase = FirebaseDatabase.getInstance().getReference();
    DatabaseReference tempReference = FirebaseDatabase.getInstance().getReference("Sensors/temperature");
    DatabaseReference humidityReference = FirebaseDatabase.getInstance().getReference("Sensors/humidity");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title_text_view = findViewById(R.id.title_label);
        humidity_textview = findViewById(R.id.humidity_value);
        temperature_textview = findViewById(R.id.temperature_value);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);

        tempReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Double data = (Double) dataSnapshot.getValue();
                String string_data = data.toString();
                temperature_textview.setText(string_data.substring(0, Math.min(5, string_data.length())));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        humidityReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Double data = (Double) dataSnapshot.getValue();
                String string_data = data.toString();
                humidity_textview.setText(string_data.substring(0, Math.min(5, string_data.length())));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        tempReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Double data = (Double) dataSnapshot.getValue();
                String string_data = data.toString();
                temperature_textview.setText(string_data.substring(0, Math.min(5, string_data.length())));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        humidityReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Double data = (Double) dataSnapshot.getValue();
                String string_data = data.toString();
                humidity_textview.setText(string_data.substring(0, Math.min(5, string_data.length())));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void buttonClick(View v){
        Button button = (Button) v;

        if(button == button1){
            title_text_view.setText("Button 1 Clicked!");
            myDatabase.child("Users").child("ECE196").child("email").setValue("ece196@ucsd.edu");

        }
        else if(button == button2){
            title_text_view.setText("Button 2 Clicked!");
            myDatabase.child("Users").child("ECE196").child("email").setValue("ece196@gmail.com");
        }
    }

    public void ledButtonClick(View v){
        // Changing led color appropriately
        Button button = (Button)v ;
        ColorStateList colorStateList = button.getBackgroundTintList();

        if(colorStateList != null){
            if(colorStateList.getDefaultColor() == ContextCompat.getColor(this, R.color.red)){
                myDatabase.child("LEDs").child("LED1").child("red").setValue(true);
                myDatabase.child("LEDs").child("LED1").child("green").setValue(false);
                myDatabase.child("LEDs").child("LED1").child("blue").setValue(false);
            }
            else if(colorStateList.getDefaultColor() == ContextCompat.getColor(this, R.color.green)){
                myDatabase.child("LEDs").child("LED1").child("red").setValue(false);
                myDatabase.child("LEDs").child("LED1").child("green").setValue(true);
                myDatabase.child("LEDs").child("LED1").child("blue").setValue(false);
            }
            else if(colorStateList.getDefaultColor() == ContextCompat.getColor(this, R.color.blue)){
                myDatabase.child("LEDs").child("LED1").child("red").setValue(false);
                myDatabase.child("LEDs").child("LED1").child("green").setValue(false);
                myDatabase.child("LEDs").child("LED1").child("blue").setValue(true);
            }
            else if(colorStateList.getDefaultColor() == ContextCompat.getColor(this, R.color.white)){
                myDatabase.child("LEDs").child("LED1").child("red").setValue(true);
                myDatabase.child("LEDs").child("LED1").child("green").setValue(true);
                myDatabase.child("LEDs").child("LED1").child("blue").setValue(true);
            }
            else if(colorStateList.getDefaultColor() == ContextCompat.getColor(this, R.color.magenta)){
                myDatabase.child("LEDs").child("LED1").child("red").setValue(true);
                myDatabase.child("LEDs").child("LED1").child("green").setValue(false);
                myDatabase.child("LEDs").child("LED1").child("blue").setValue(true);
            }
            else if(colorStateList.getDefaultColor() == ContextCompat.getColor(this, R.color.cyan)){
                myDatabase.child("LEDs").child("LED1").child("red").setValue(false);
                myDatabase.child("LEDs").child("LED1").child("green").setValue(true);
                myDatabase.child("LEDs").child("LED1").child("blue").setValue(true);
            }
            else if(colorStateList.getDefaultColor() == ContextCompat.getColor(this, R.color.yellow)){
                myDatabase.child("LEDs").child("LED1").child("red").setValue(true);
                myDatabase.child("LEDs").child("LED1").child("green").setValue(true);
                myDatabase.child("LEDs").child("LED1").child("blue").setValue(false);
            }
        }
        else {
            myDatabase.child("LEDs").child("LED1").child("red").setValue(false);
            myDatabase.child("LEDs").child("LED1").child("green").setValue(false);
            myDatabase.child("LEDs").child("LED1").child("blue").setValue(false);
        }
    }
}
