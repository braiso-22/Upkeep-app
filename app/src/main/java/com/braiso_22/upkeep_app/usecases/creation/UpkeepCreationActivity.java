package com.braiso_22.upkeep_app.usecases.creation;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.braiso_22.upkeep_app.R;
import com.braiso_22.upkeep_app.model.vo.Upkeep;
import com.braiso_22.upkeep_app.utils.TextUtils;
import com.braiso_22.upkeep_app.viewmodel.ViewModel;

public class UpkeepCreationActivity extends AppCompatActivity {

    EditText date, time, component;
    Button cancel, create;
    ViewModel vm;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upkeep_creation);
        vm = new ViewModel(getApplication());
        initViews();
        onClickButtons();
    }

    private void initViews() {
        date = findViewById(R.id.upkeepDateEditText);
        time = findViewById(R.id.upkeepTimeEditText);
        component = findViewById(R.id.upkeepComponentEditText);
        cancel = findViewById(R.id.cancelUpkeepCreationButton);
        create = findViewById(R.id.createUpkeepCreationButton);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void onClickButtons() {
        cancel.setOnClickListener(v -> finish());
        create.setOnClickListener(v -> {
            if (!TextUtils.areFieldsEmpty(date, time, component) &&
                    TextUtils.isDate(date) && TextUtils.isTime(time)) {
                saveUpkeep();
            } else {
                Toast.makeText(this, getResources().getText(R.string.wrong_data), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void saveUpkeep() {
        String date = this.date.getText().toString();
        String time = this.time.getText().toString();
        int component = Integer.valueOf(this.component.getText().toString());
        Upkeep upkeep = new Upkeep(date, time, component);
        vm.insert(upkeep);
        finish();
    }
}