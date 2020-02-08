package com.example.century;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {


    LinearLayout llNavGuest, llNavStaff = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) { //method main yang tidak boleh diubah
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //if(savedInstanceState.hasFileDescriptors()) // checking callback

        //https://www.codepolitan.com/rest-api-client-sederhana-dengan-retrofit-pada-android-studio-58986d62c46ae

        declareComponent();

        //  Arahkan navigasi guest
        //


        // Arahkan navigasi staff

        llNavStaff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goStaff = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(goStaff);
            }
        });





    }


    private void declareComponent(){ //method
            llNavGuest = findViewById(R.id.ll_nav_guest);
            llNavStaff = findViewById(R.id.ll_nav_staff);
    }
}
