package com.braiso_22.upkeep_app.usecases.termsandconditions;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.braiso_22.upkeep_app.R;

public class TermsDialogFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.terms0);
        // getString() from 3 to 31

        String termsFinal = getString(R.string.terms3) + getString(R.string.terms4) +
                getString(R.string.terms5) + getString(R.string.terms6) + getString(R.string.terms7)
                + getString(R.string.terms8) + getString(R.string.terms9) +
                getString(R.string.terms10) + getString(R.string.terms11) +
                getString(R.string.terms12) + getString(R.string.terms13) +
                getString(R.string.terms14) + getString(R.string.terms15) +
                getString(R.string.terms16) + getString(R.string.terms17) +
                getString(R.string.terms18) + getString(R.string.terms19) +
                getString(R.string.terms20) + getString(R.string.terms21) +
                getString(R.string.terms22) + getString(R.string.terms23) +
                getString(R.string.terms24) + getString(R.string.terms25) +
                getString(R.string.terms26) + getString(R.string.terms27) +
                getString(R.string.terms28) + getString(R.string.terms29) +
                getString(R.string.terms30) + getString(R.string.terms31);

        builder.setMessage(termsFinal);
        builder.setPositiveButton(R.string.accept, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        return builder.create();
    }


}
