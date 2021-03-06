package com.braiso_22.upkeep_app.usecases.creation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.braiso_22.upkeep_app.R;
import com.braiso_22.upkeep_app.model.vo.Component;
import com.braiso_22.upkeep_app.model.vo.Service;
import com.braiso_22.upkeep_app.utils.TextUtils;
import com.braiso_22.upkeep_app.viewmodel.ViewModel;

public class ComponentCreationActivity extends AppCompatActivity {

    EditText code, name, brand, model, serialNumber, observations;
    Button cancel, create;
    ViewModel vm;
    Bundle bundle;
    Component component;
    Service service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_component_creation);
        vm = new ViewModel(this.getApplication());
        bundle = getIntent().getExtras();
        initViews();
        onClickButtons();
    }

    private void initViews() {
        code = findViewById(R.id.componentCodeEditText);
        name = findViewById(R.id.componentNameEditText);
        brand = findViewById(R.id.componentBrandEditText);
        model = findViewById(R.id.componentModelEditText);
        serialNumber = findViewById(R.id.componentSerialNumberEditText);
        observations = findViewById(R.id.componentObservationsEditText);
        cancel = findViewById(R.id.cancelComponentCreationButton);
        create = findViewById(R.id.createComponentCreationButton);
        if (bundle.containsKey("component")) {
            component = (Component) bundle.getSerializable("component");
            code.setText(component.getCode());
            name.setText(component.getName());
            brand.setText(component.getBrand());
            model.setText(component.getModel());
            serialNumber.setText(component.getSerialNumber());
            observations.setText(component.getObservations());
        }
        service = (Service) bundle.getSerializable("service");
    }

    private void onClickButtons() {
        cancel.setOnClickListener(v -> finish());
        create.setOnClickListener(v -> {
            if (!TextUtils.areFieldsEmpty(name, brand, code, model, serialNumber, observations)) {
                if (!bundle.containsKey("component")) {
                    insertComponent();
                } else {
                    updateComponent();
                }
            } else {
                Toast.makeText(this, getResources().getText(R.string.wrong_data), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void insertComponent() {
        String code = this.code.getText().toString();
        String name = this.name.getText().toString();
        String brand = this.brand.getText().toString();
        String model = this.model.getText().toString();
        String serialNumber = this.serialNumber.getText().toString();
        String observations = this.observations.getText().toString();

        vm.insert(new Component(code, name, brand, model, serialNumber, observations, service.getId()));
        finish();
    }

    private void updateComponent() {
        String code = this.code.getText().toString();
        String name = this.name.getText().toString();
        String brand = this.brand.getText().toString();
        String model = this.model.getText().toString();
        String serialNumber = this.serialNumber.getText().toString();
        String observations = this.observations.getText().toString();

        vm.update(new Component(component.getId(), code, name, brand, model, serialNumber, observations, component.getService()));
        finish();
    }
}