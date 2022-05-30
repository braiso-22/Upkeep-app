package com.braiso_22.upkeep_app.usecases.creation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.braiso_22.upkeep_app.R;
import com.braiso_22.upkeep_app.model.vo.Service;
import com.braiso_22.upkeep_app.utils.TextUtils;
import com.braiso_22.upkeep_app.viewmodel.ViewModel;

public class ServiceCreationActivity extends AppCompatActivity {

    EditText name, code, boat;
    Button create, cancel;
    ViewModel vm;
    Bundle bundle;
    Service service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_creation);
        vm = new ViewModel(this.getApplication());
        bundle = getIntent().getExtras();
        initViews();
        onClickButtons();
    }

    private void initViews() {
        name = findViewById(R.id.serviceNameEditText);
        code = findViewById(R.id.serviceCodeEditText);
        boat = findViewById(R.id.serviceBoatEditText);
        create = findViewById(R.id.createServiceCreationButton);
        cancel = findViewById(R.id.cancelServiceCreationButton);
        if (bundle != null) {
            service = (Service) bundle.getSerializable("service");
            name.setText(service.getName());
            code.setText(service.getCode());
            boat.setText(String.valueOf(service.getBoat()));
        }
    }

    private void onClickButtons() {
        create.setOnClickListener(v -> {
            if (!TextUtils.areFieldsEmpty(name, code, boat)) {
                if (bundle == null) {
                    insertService();
                } else {
                    updateService();
                }
            } else {
                Toast.makeText(this, getResources().getText(R.string.wrong_data), Toast.LENGTH_LONG).show();
            }
        });
        cancel.setOnClickListener(v ->

                finish());
    }

    private void insertService() {
        String code = this.code.getText().toString();
        String name = this.name.getText().toString();
        int boat = Integer.valueOf(this.boat.getText().toString());

        vm.insert(new Service(code, name, boat));
        finish();
    }

    private void updateService() {
        String code = this.code.getText().toString();
        String name = this.name.getText().toString();
        int boat = Integer.valueOf(this.boat.getText().toString());
        int id = service.getId();
        vm.update(new Service(id, code, name, boat));
        finish();
    }
}