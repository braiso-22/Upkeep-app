package com.braiso_22.upkeep_app.usecases.creation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.braiso_22.upkeep_app.R;
import com.braiso_22.upkeep_app.model.vo.Store;
import com.braiso_22.upkeep_app.utils.TextUtils;
import com.braiso_22.upkeep_app.viewmodel.ViewModel;

public class StoreCreationActivity extends AppCompatActivity {

    EditText code, name, brand, model, serialNumber, observations, numStock, minStock, task;
    Button cancel, create;
    ViewModel vm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_creation);
        vm = new ViewModel(getApplication());
        initViews();
        onClickButtons();
    }

    private void initViews(){
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
    }
    private void onClickButtons(){
        cancel.setOnClickListener(v -> finish());
        create.setOnClickListener(v -> {
            if(!TextUtils.areFieldsEmpty(code, name, brand, model, serialNumber, observations,
                    numStock, minStock, task) && TextUtils.checkNumeric(numStock, minStock, task)){
                saveStore();
            }else{
                Toast.makeText(this, getResources().getText(R.string.wrong_data), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void saveStore(){
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
}