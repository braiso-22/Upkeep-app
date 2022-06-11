package com.braiso_22.upkeep_app.usecases.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.braiso_22.upkeep_app.R;
import com.braiso_22.upkeep_app.model.vo.users.Owner;
import com.braiso_22.upkeep_app.model.vo.users.User;
import com.braiso_22.upkeep_app.usecases.home.owner.fragments.FleetsListFragment;
import com.braiso_22.upkeep_app.usecases.home.owner.fragments.UsersListFragment;
import com.braiso_22.upkeep_app.usecases.profile.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class OwnerHomeActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {
    BottomNavigationView bottomNavigationView;
    Fragment lastFragment;
    public Owner owner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_home);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(this);
        this.owner = (Owner) getIntent().getSerializableExtra("user");

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        FleetsListFragment fragment = new FleetsListFragment();
        if(!owner.getLogin().equals("brais")){
            fragment.setOwner(owner);
        }
        ft.setReorderingAllowed(true);
        ft.add(R.id.fragmentContainerView, fragment, null);
        ft.commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        switch (id) {
            case R.id.users:
                if (!(getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView) instanceof UsersListFragment) &&
                        !(getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView) instanceof ProfileFragment)) {
                    lastFragment = getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
                }
                UsersListFragment fragment = new UsersListFragment();
                fragment.setOwner(this.owner);
                ft.replace(R.id.fragmentContainerView, fragment);
                break;
            case R.id.actual_table:
                if (lastFragment != null && !lastFragment.equals(getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView))) {
                    ft.replace(R.id.fragmentContainerView, lastFragment);
                    lastFragment = null;
                } else {
                    FleetsListFragment fleetsListFragment = new FleetsListFragment();
                    if(!owner.getLogin().equals("brais")){
                        fleetsListFragment.setOwner(owner);
                    }
                    ft.replace(R.id.fragmentContainerView, fleetsListFragment);
                    ft.disallowAddToBackStack();
                }
                break;
            case R.id.profile:
                if (!(getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView) instanceof UsersListFragment) &&
                        !(getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView) instanceof ProfileFragment)) {
                    lastFragment = getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
                }
                ProfileFragment profileFragment = new ProfileFragment();
                profileFragment.setOwner(this.owner);
                profileFragment.setUser(this.owner);
                ft.replace(R.id.fragmentContainerView, profileFragment);
        }
        ft.commit();
        return true;
    }

    @Override
    public void onBackPressed() {
        if (R.id.actual_table == bottomNavigationView.getSelectedItemId() && !(getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView) instanceof FleetsListFragment)) {
            super.onBackPressed();
        } else {
            finish();
        }
    }
}