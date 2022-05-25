package com.braiso_22.upkeep_app.usecases.creation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.braiso_22.upkeep_app.R;
import com.braiso_22.upkeep_app.model.vo.Boat;
import com.braiso_22.upkeep_app.utils.TextUtils;
import com.braiso_22.upkeep_app.viewmodel.ViewModel;

public class BoatCreationActivity extends AppCompatActivity {

    private EditText name,registration,code,fleet;
    private Button create,cancel;
    private ViewModel vm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boat_creation);
        vm = new ViewModel(this.getApplication());
        initViews();
        onClickButtons();
    }
    private void initViews(){
        name = findViewById(R.id.boatNameEditText);
        registration = findViewById(R.id.boatRegistrationEditText);
        code = findViewById(R.id.boatCodeEditText);
        fleet = findViewById(R.id.boatFleetEditText);
        create = findViewById(R.id.createBoatCreationButton);
        cancel = findViewById(R.id.cancelBoatCreationButton);
    }

    private void onClickButtons(){
        create.setOnClickListener(v -> {
            if(!TextUtils.areFieldsEmpty(name,registration,code,fleet) && TextUtils.checkNumeric(fleet)){
                saveBoat();
            }else{
                Toast.makeText(this,getResources().getText(R.string.wrong_data),Toast.LENGTH_LONG).show();
            }
        });
        cancel.setOnClickListener(v -> finish());
    }
    private void saveBoat(){
        String code = this.code.getText().toString();
        String name = this.name.getText().toString();
        String registration = this.registration.getText().toString();
        int fleet = Integer.parseInt(this.fleet.getText().toString());

        Boat boat = new Boat(code,name,registration,fleet);
        vm.insert(boat);
        finish();
    }
}