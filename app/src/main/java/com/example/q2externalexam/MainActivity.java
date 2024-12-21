package com.example.q2externalexam;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomsheet.BottomSheetDialog;

public class MainActivity extends AppCompatActivity {

    private Switch switchEmail;
    private Switch switchSms;
    private Switch switchPush;
    private Button buttonSave;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        switchEmail = findViewById(R.id.switch_email);
        switchSms = findViewById(R.id.switch_sms);
        switchPush = findViewById(R.id.switch_push);
        buttonSave = findViewById(R.id.button_save);


        sharedPreferences = getSharedPreferences("NotificationPrefs", MODE_PRIVATE);

        loadPreferences();
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showConfirmationDialog();
            }
        });
    }

    private void loadPreferences() {
        switchEmail.setChecked(sharedPreferences.getBoolean("email_notifications", false));
        switchSms.setChecked(sharedPreferences.getBoolean("sms_notifications", false));
        switchPush.setChecked(sharedPreferences.getBoolean("push_notifications", false));
    }

    private void savePreferences() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("email_notifications", switchEmail.isChecked());
        editor.putBoolean("sms_notifications", switchSms.isChecked());
        editor.putBoolean("push_notifications", switchPush.isChecked());
        editor.apply();
    }

    private void showConfirmationDialog() {
        BottomSheetDialog dialog = new BottomSheetDialog(this);
        dialog.setContentView(R.layout.bottom_sheet_confirmation);

        Button buttonConfirm = dialog.findViewById(R.id.button_confirm);
        if (buttonConfirm != null) {
            buttonConfirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    savePreferences();
                    dialog.dismiss();
                }
            });
        }

        dialog.show();
    }
}

