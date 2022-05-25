package com.braiso_22.upkeep_app.usecases.creation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.braiso_22.upkeep_app.R;
import com.braiso_22.upkeep_app.model.vo.Task;
import com.braiso_22.upkeep_app.utils.TextUtils;
import com.braiso_22.upkeep_app.viewmodel.ViewModel;

public class TaskCreationActivity extends AppCompatActivity {

    EditText length, name, description, upkeep, operator;
    Button cancel, create;
    ViewModel vm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_creation);
        vm = new ViewModel(getApplication());
        initViews();
        onClickButtons();
    }

    private void initViews() {
        length = findViewById(R.id.taskLengthEditText);
        name = findViewById(R.id.taskNameEditText);
        description = findViewById(R.id.taskDescriptionEditText);
        upkeep = findViewById(R.id.taskUpkeepEditText);
        operator = findViewById(R.id.taskOperatorEditText);
        cancel = findViewById(R.id.cancelTaskCreationButton);
        create = findViewById(R.id.createTaskCreationButton);
    }

    private void onClickButtons() {
        cancel.setOnClickListener(v -> finish());
        create.setOnClickListener(v -> {
            if (!TextUtils.areFieldsEmpty(length, name, description, upkeep, operator) &&
                    TextUtils.checkNumeric(length, upkeep, operator)) {
                saveTask();
            } else {
                Toast.makeText(this, getResources().getText(R.string.wrong_data), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void saveTask() {
        Task task = new Task(Integer.parseInt(length.getText().toString()),
                name.getText().toString(),
                description.getText().toString(),
                Integer.parseInt(upkeep.getText().toString()),
                Integer.parseInt(operator.getText().toString()));
        vm.insert(task);
        finish();
    }


}