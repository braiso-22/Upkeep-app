package com.braiso_22.upkeep_app.usecases.termsandconditions;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.braiso_22.upkeep_app.R;
import com.braiso_22.upkeep_app.databinding.ActivityTermsAndConditionsBinding;
import com.braiso_22.upkeep_app.usecases.onboarding.OnBoardingActivity;

public class TermsAndConditionsActivity extends AppCompatActivity {

    ActivityTermsAndConditionsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityTermsAndConditionsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String firstText = getString(R.string.terms1);
        String link = getString(R.string.terms2);
        String complete = firstText + " " + link;

        SpannableString spannableString = new SpannableString(complete);
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(android.view.View widget) {
                TermsDialogFragment termsDialogFragment = new TermsDialogFragment();
                termsDialogFragment.show(getSupportFragmentManager(), "TermsDialogFragment");
            }

            @Override
            public void updateDrawState(android.text.TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
            }
        };
        spannableString.setSpan(clickableSpan, firstText.length(), complete.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        binding.termsLinkTextView.setText(spannableString);
        binding.termsLinkTextView.setMovementMethod(LinkMovementMethod.getInstance());
        binding.button.setOnClickListener(v -> {
            if (binding.termsCheckBox.isChecked()) {
                Intent intent = new Intent(this, OnBoardingActivity.class);
                startActivity(intent);
                finish();
            }else{
                Toast.makeText(this, getString(R.string.must_accept), Toast.LENGTH_SHORT).show();
            }
        });
    }
}