package com.boss.ui_a4_talent_scout;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    public static final int REQUEST_CODE = 111;
    private Button login;
    private EditText userName;
    private EditText password;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        login = findViewById(R.id.btnLogin);
        login.setEnabled(false);
        login.setOnClickListener(this);
        userName = findViewById(R.id.edUserName);
        userName.setHintTextColor(Color.WHITE);
        userName.setTextColor(Color.WHITE);
        userName.addTextChangedListener(watcher);
        password = findViewById(R.id.edPassword);
        password.setHintTextColor(Color.WHITE);
        password.setTextColor(Color.WHITE);
        password.addTextChangedListener(watcher);
    }

    private TextWatcher watcher = new TextWatcher() {
        @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
        @Override public void afterTextChanged(Editable s) { }
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (userName.getText().length() > 0 && password.getText().length() > 0) {
                login.setEnabled(true);
            } else {
                login.setEnabled(false);
            }
        }
    };

    @Override
    public void onClick(View v) {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("result", REQUEST_CODE);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }
}
