package com.braiso_22.upkeep_app.usecases.creation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.braiso_22.upkeep_app.R;
import com.braiso_22.upkeep_app.model.vo.Fleet;
import com.braiso_22.upkeep_app.utils.TextUtils;
import com.braiso_22.upkeep_app.viewmodel.ViewModel;

public class FleetCreationActivity extends AppCompatActivity {

    private EditText name, owner;
    private Button create, cancel;
    private ViewModel vm;
    Bundle bundle;
    Fleet fleet = null;

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
        owner = findViewById(R.id.fleetOwnerEditText);
        create = findViewById(R.id.createFleetCreationButton);
        cancel = findViewById(R.id.cancelFleetCreationButton);
        if (bundle != null) {
            fleet = (Fleet) bundle.getSerializable("fleet");
            name.setText(fleet.getName());
            owner.setText(String.valueOf(fleet.getOwner()));
        }
    }

    private void onClickButtons() {
        create.setOnClickListener(v -> {
            if (!TextUtils.areFieldsEmpty(name, owner) && TextUtils.checkNumeric(owner)) {
                if (bundle == null) {
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
        int owner = Integer.parseInt(this.owner.getText().toString());
        Fleet fleet = new Fleet(name, owner);
        vm.insert(fleet);
        finish();
    }
    private void updateFleet(){
        String name = this.name.getText().toString();
        int owner = Integer.parseInt(this.owner.getText().toString());
        Fleet fleet = new Fleet(this.fleet.getId(),name, owner);
        vm.update(fleet);
        finish();
    }


}