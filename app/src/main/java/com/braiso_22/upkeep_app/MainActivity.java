package com.braiso_22.upkeep_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.braiso_22.upkeep_app.vo.Boat;
import com.braiso_22.upkeep_app.vo.Fleet;
import com.braiso_22.upkeep_app.vo.users.Owner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}