package com.braiso_22.upkeep_app.usecases.creation;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.braiso_22.upkeep_app.R;
import com.braiso_22.upkeep_app.model.vo.Component;
import com.braiso_22.upkeep_app.model.vo.Upkeep;
import com.braiso_22.upkeep_app.utils.TextUtils;
import com.braiso_22.upkeep_app.viewmodel.ViewModel;

public class UpkeepCreationActivity extends AppCompatActivity {

    EditText date, time;
    Button cancel, create;
    ViewModel vm;
    Bundle bundle;
    Upkeep upkeep;
    Component component;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upkeep_creation);
        vm = new ViewModel(getApplication());
        bundle = getIntent().getExtras();
        initViews();
        onClickButtons();
    }

    private void initViews() {
        date = findViewById(R.id.upkeepDateEditText);
        time = findViewById(R.id.upkeepTimeEditText);
        cancel = findViewById(R.id.cancelUpkeepCreationButton);
        create = findViewById(R.id.createUpkeepCreationButton);
        if (bundle.containsKey("upkeep")) {
            upkeep = (Upkeep) bundle.getSerializable("upkeep");
            date.setText(upkeep.getDate());
            time.setText(upkeep.getHour());
        }
        component = (Component) bundle.getSerializable("component");
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void onClickButtons() {
        cancel.setOnClickListener(v -> finish());
        create.setOnClickListener(v -> {
            if (!TextUtils.areFieldsEmpty(date, time) && TextUtils.isDate(date)
                    && TextUtils.isTime(time)) {
                if (!bundle.containsKey("upkeep")) {
                    insertUpkeep();
                } else {
                    updateUpkeep();
                }
            } else {
                Toast.makeText(this, getResources().getText(R.string.wrong_data), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void insertUpkeep() {
        String date = this.date.getText().toString();
        String time = this.time.getText().toString();
        Upkeep upkeep = new Upkeep(date, time, component.getId());
        vm.insert(upkeep);
        finish();
    }

    private void updateUpkeep() {
        String date = this.date.getText().toString();
        String time = this.time.getText().toString();
        vm.update(new Upkeep(upkeep.getId(), date, time, upkeep.getComponent()));
        finish();
    }
}