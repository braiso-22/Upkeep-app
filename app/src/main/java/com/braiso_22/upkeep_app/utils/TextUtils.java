package com.braiso_22.upkeep_app.utils;

import android.widget.EditText;

public class TextUtils {

    public static boolean areFieldsEmpty(EditText... fields) {
        for (EditText field : fields) {
            if (field.getText().toString().isEmpty()) {
                return true;
            }
        }
        return false;
    }
    public static boolean isNumeric(EditText... fields) {
        for (EditText field : fields) {
            if (!field.getText().toString().matches("[0-9]+")) {
                return false;
            }
        }
        return true;
    }
}
