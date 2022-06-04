package com.braiso_22.upkeep_app.usecases.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.braiso_22.upkeep_app.R;
import com.braiso_22.upkeep_app.databinding.ActivityNotOwnerHomeBinding;
import com.braiso_22.upkeep_app.model.vo.users.User;
import com.braiso_22.upkeep_app.usecases.home.common.fragments.ComponentListFragment;
import com.braiso_22.upkeep_app.usecases.home.owner.fragments.FleetsListFragment;
import com.braiso_22.upkeep_app.usecases.home.owner.fragments.UsersListFragment;
import com.braiso_22.upkeep_app.usecases.profile.ProfileFragment;
import com.google.android.material.navigation.NavigationBarView;

public class NotOwnerHomeActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {

    ActivityNotOwnerHomeBinding binding;
    static User user;
    Fragment lastFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNotOwnerHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.bottomNavigationView.setOnItemSelectedListener(this);
        this.user = (User) getIntent().getSerializableExtra("user");
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        switch (id) {
            case R.id.recent:
                break;
            case R.id.actual_table:
                if (lastFragment != null && !lastFragment.equals(getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView))) {
                    ft.replace(R.id.fragmentContainerView, lastFragment);
                    lastFragment = null;
                } else {
                    ft.replace(R.id.fragmentContainerView, new ComponentListFragment());
                    ft.disallowAddToBackStack();
                }
                break;
            case R.id.profile:
                if (!(getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView) instanceof UsersListFragment) &&
                        !(getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView) instanceof ProfileFragment)) {
                    lastFragment = getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
                }
                ft.replace(R.id.fragmentContainerView, new ProfileFragment(user));
                break;
        }
        ft.commit();
        return true;
    }

    @Override
    public void onBackPressed() {
        if (R.id.actual_table == binding.bottomNavigationView.getSelectedItemId() && !(getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView) instanceof ComponentListFragment)) {
            super.onBackPressed();
        } else {
            finish();
        }
    }
}