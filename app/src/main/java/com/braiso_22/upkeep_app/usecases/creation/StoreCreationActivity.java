package com.braiso_22.upkeep_app.usecases.creation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.braiso_22.upkeep_app.R;
import com.braiso_22.upkeep_app.model.vo.Store;
import com.braiso_22.upkeep_app.model.vo.Task;
import com.braiso_22.upkeep_app.utils.TextUtils;
import com.braiso_22.upkeep_app.viewmodel.ViewModel;

public class StoreCreationActivity extends AppCompatActivity {

    EditText code, name, brand, model, serialNumber, observations, numStock, minStock, task;
    Button cancel, create;
    ViewModel vm;
    Bundle bundle;
    Store store;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_creation);
        vm = new ViewModel(getApplication());
        bundle = getIntent().getExtras();
        initViews();
        onClickButtons();
    }

    private void initViews() {
        code = findViewById(R.id.storeCodeEditText);
        name = findViewById(R.id.storeNameEditText);
        brand = findViewById(R.id.storeBrandEditText);
        model = findViewById(R.id.storeModelEditText);
        serialNumber = findViewById(R.id.storeSerialNumberEditText);
        observations = findViewById(R.id.storeObservationsEditText);
        numStock = findViewById(R.id.storeNumStockEditText);
        minStock = findViewById(R.id.storeMinStockEditText);
        task = findViewById(R.id.storeTaskEditText);
        cancel = findViewById(R.id.cancelStoreCreationButton);
        create = findViewById(R.id.createStoreCreationButton);
        if (bundle != null) {
            store = (Store) bundle.getSerializable("store");
            code.setText(store.getCode());
            name.setText(store.getName());
            brand.setText(store.getBrand());
            model.setText(store.getModel());
            serialNumber.setText(store.getSerialNumber());
            observations.setText(store.getObservations());
            numStock.setText(String.valueOf(store.getNumStock()));
            minStock.setText(String.valueOf(store.getMinStock()));
            task.setText(String.valueOf(store.getTask()));
        }
    }

    private void onClickButtons() {
        cancel.setOnClickListener(v -> finish());
        create.setOnClickListener(v -> {
            if (!TextUtils.areFieldsEmpty(code, name, brand, model, serialNumber, observations,
                    numStock, minStock, task) && TextUtils.checkNumeric(numStock, minStock, task)) {
                if (bundle == null) {
                    insertStore();
                } else {
                    updateStore();
                }
            } else {
                Toast.makeText(this, getResources().getText(R.string.wrong_data), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void insertStore() {
        Store store = new Store(code.getText().toString(),
                name.getText().toString(),
                brand.getText().toString(),
                model.getText().toString(),
                serialNumber.getText().toString(),
                observations.getText().toString(),
                Integer.parseInt(numStock.getText().toString()),
                Integer.parseInt(minStock.getText().toString()),
                Integer.parseInt(task.getText().toString()));
        vm.insert(store);
        finish();
    }

    private void updateStore() {
        String code = this.code.getText().toString();
        String name = this.name.getText().toString();
        String brand = this.brand.getText().toString();
        String model = this.model.getText().toString();
        String serialNumber = this.serialNumber.getText().toString();
        String observations = this.observations.getText().toString();
        int numStock = Integer.parseInt(this.numStock.getText().toString());
        int minStock = Integer.parseInt(this.minStock.getText().toString());
        int task = Integer.parseInt(this.task.getText().toString());
        vm.update(new Store(store.getId(), code, name, brand, model, serialNumber, observations, numStock, minStock, task));
        finish();
    }
}