package com.braiso_22.upkeep_app.usecases.creation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.braiso_22.upkeep_app.R;
import com.braiso_22.upkeep_app.model.vo.Task;
import com.braiso_22.upkeep_app.model.vo.Upkeep;
import com.braiso_22.upkeep_app.utils.TextUtils;
import com.braiso_22.upkeep_app.viewmodel.ViewModel;

public class TaskCreationActivity extends AppCompatActivity {

    EditText length, name, description, operator;
    Button cancel, create;
    ViewModel vm;
    Bundle bundle;
    Task task;
    Upkeep upkeep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_creation);
        vm = new ViewModel(getApplication());
        bundle = getIntent().getExtras();
        initViews();
        onClickButtons();
    }

    private void initViews() {
        length = findViewById(R.id.taskLengthEditText);
        name = findViewById(R.id.taskNameEditText);
        description = findViewById(R.id.taskDescriptionEditText);
        operator = findViewById(R.id.taskOperatorEditText);
        cancel = findViewById(R.id.cancelTaskCreationButton);
        create = findViewById(R.id.createTaskCreationButton);
        if (bundle.containsKey("task")) {
            task = (Task) bundle.getSerializable("task");
            length.setText(String.valueOf(task.getLength()));
            name.setText(task.getName());
            description.setText(task.getDescription());
            operator.setText(String.valueOf(task.getOperator()));
        }
        upkeep = (Upkeep) bundle.getSerializable("upkeep");
    }

    private void onClickButtons() {
        cancel.setOnClickListener(v -> finish());
        create.setOnClickListener(v -> {
            if (!TextUtils.areFieldsEmpty(length, name, description, operator) &&
                    TextUtils.checkNumeric(length, operator)) {
                if (!bundle.containsKey("task")) {
                    insertTask();
                } else {
                    updateTask();
                }

            } else {
                Toast.makeText(this, getResources().getText(R.string.wrong_data), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void insertTask() {
        Task task = new Task(Integer.parseInt(length.getText().toString()),
                name.getText().toString(),
                description.getText().toString(),
                upkeep.getId(),
                Integer.parseInt(operator.getText().toString()));
        vm.insert(task);
        finish();
    }

    private void updateTask() {
        String length = this.length.getText().toString();
        String name = this.name.getText().toString();
        String description = this.description.getText().toString();
        int upkeep = this.task.getUpkeep();
        String operator = this.operator.getText().toString();

        vm.update(new Task(task.getId(), Integer.parseInt(length), name, description, upkeep, Integer.parseInt(operator)));
        finish();
    }


}