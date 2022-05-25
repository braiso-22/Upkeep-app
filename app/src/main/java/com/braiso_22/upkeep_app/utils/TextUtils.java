package com.braiso_22.upkeep_app.utils;

import android.os.Build;
import android.widget.EditText;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;
import java.time.LocalTime;

public class TextUtils {

    public static boolean areFieldsEmpty(EditText... fields) {
        for (EditText field : fields) {
            if (field.getText().toString().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkNumeric(EditText... field) {
        for (EditText field1 : field) {
            if (!field1.getText().toString().matches("[0-9]+")) {
                return false;
            }
        }
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static boolean isDate(EditText field) {
        try {
            LocalDate.parse(field.getText().toString(),
                    java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static boolean isTime(EditText field) {
        try {
            LocalTime.parse(field.getText().toString(),
                    java.time.format.DateTimeFormatter.ofPattern("HH:mm"));
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
