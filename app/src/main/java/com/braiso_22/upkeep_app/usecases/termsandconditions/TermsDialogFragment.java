package com.braiso_22.upkeep_app.usecases.termsandconditions;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.braiso_22.upkeep_app.R;

public class TermsDialogFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.terms0);

        ScrollView scrollView = new ScrollView(getActivity());
        LinearLayout relativeLayout = new LinearLayout(getActivity());
        relativeLayout.setOrientation(LinearLayout.VERTICAL);

        for (int i = 3; i <= 37; i++) {
            int resId = this.getResources().getIdentifier("terms" + i, "string", this.getActivity().getPackageName());

            String text = this.getResources().getString(resId);
            TextView textView = new TextView(getActivity());
            textView.setText(text);
            switch (i) {
                case 5:
                case 9:
                case 15:
                case 19:
                case 23:
                case 30:
                case 33:
                case 35:
                    textView.setText("\n" + textView.getText());
                    textView.setTextSize(18);
                    textView.setTextColor(getSecondaryColor());
                    break;
                default:
                    textView.setTextSize(16);
                    break;
            }
            relativeLayout.addView(textView);
        }
        scrollView.addView(relativeLayout);
        builder.setView(scrollView);
        builder.setPositiveButton(R.string.accept, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // get the checkbox item of the activity
                CheckBox checkBox = getActivity().findViewById(R.id.termsCheckBox);
                checkBox.setChecked(true);

            }
        });
        return builder.create();
    }

    private int getSecondaryColor() {
        TypedValue typedValue = new TypedValue();
        getActivity().getTheme().resolveAttribute(com.google.android.material.R.attr.colorOnSecondary, typedValue, true);
        TypedArray arr = getActivity().obtainStyledAttributes(typedValue.data, new int[]{com.google.android.material.R.attr.colorOnSecondary});
        return arr.getColor(0, -1);
    }
}
