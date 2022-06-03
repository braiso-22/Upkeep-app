package com.braiso_22.upkeep_app.usecases.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.braiso_22.upkeep_app.R;
import com.braiso_22.upkeep_app.databinding.ActivityNotOwnerHomeBinding;
import com.braiso_22.upkeep_app.usecases.home.common.fragments.ComponentListFragment;
import com.braiso_22.upkeep_app.usecases.home.owner.fragments.FleetsListFragment;
import com.braiso_22.upkeep_app.usecases.home.owner.fragments.UsersListFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class NotOwnerHomeActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {

    ActivityNotOwnerHomeBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNotOwnerHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.bottomNavigationView.setOnItemSelectedListener(this);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.recent:
                break;
            case R.id.actual_table:
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(binding.fragmentContainerView.getId(), new ComponentListFragment());
                ft.commit();
                break;
            case R.id.profile:
                break;
        }
        return true;
    }
}