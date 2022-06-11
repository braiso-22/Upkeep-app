package com.braiso_22.upkeep_app.usecases.creation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.braiso_22.upkeep_app.R;
import com.braiso_22.upkeep_app.model.vo.Fleet;
import com.braiso_22.upkeep_app.model.vo.users.Owner;
import com.braiso_22.upkeep_app.utils.TextUtils;
import com.braiso_22.upkeep_app.viewmodel.ViewModel;

public class FleetCreationActivity extends AppCompatActivity {

    private EditText name;
    private Button create, cancel;
    private ViewModel vm;
    Bundle bundle;
    Fleet fleet = null;
    Owner owner = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fleet_creation);
        vm = new ViewModel(this.getApplication());
        bundle = getIntent().getExtras();
        initViews();
        onClickButtons();
    }

    private void initViews() {
        name = findViewById(R.id.fleetNameEditText);
        create = findViewById(R.id.createFleetCreationButton);
        cancel = findViewById(R.id.cancelFleetCreationButton);
        if (bundle.containsKey("fleet")) {
            fleet = (Fleet) bundle.getSerializable("fleet");
            name.setText(fleet.getName());
        }
        owner = (Owner) bundle.getSerializable("owner");
    }

    private void onClickButtons() {
        create.setOnClickListener(v -> {
            if (!TextUtils.areFieldsEmpty(name)) {
                if (!bundle.containsKey("fleet")) {
                    insertFleet();
                } else {
                    updateFleet();
                }
            } else {
                Toast.makeText(this, getResources().getText(R.string.wrong_data), Toast.LENGTH_LONG).show();
            }
        });
        cancel.setOnClickListener(v -> finish());
    }

    private void insertFleet() {
        String name = this.name.getText().toString();
        int ownerId = owner != null ? owner.getId() : 1;
        Fleet fleet = new Fleet(name, ownerId);
        vm.insert(fleet);
        finish();
    }

    private void updateFleet() {
        String name = this.name.getText().toString();
        Fleet fleet = new Fleet(this.fleet.getId(), name, this.fleet.getOwner());
        vm.update(fleet);
        finish();
    }


}