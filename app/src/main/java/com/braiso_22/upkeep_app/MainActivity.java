package com.braiso_22.upkeep_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.braiso_22.upkeep_app.model.db.UpkeepsRoomDatabase;
import com.braiso_22.upkeep_app.model.vo.*;
import com.braiso_22.upkeep_app.model.vo.users.*;
import com.braiso_22.upkeep_app.viewmodel.ViewModel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewModel viewModel = new ViewModel(this.getApplication());

        viewModel.getAllFleets().observe(this, fleets -> {
            for (Fleet fleet : fleets) {
                System.out.println(fleet.getName());
            }
        });

        viewModel.getAllManagers().observe(this, managers -> {
            for (Manager manager : managers) {
                System.out.println(manager.getName());
            }
        });
    }
}