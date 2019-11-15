package com.boss.ui_a4_talent_scout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent i = new Intent(this, LoginActivity.class);
        startActivityForResult(i, LoginActivity.REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == LoginActivity.REQUEST_CODE) {
                Intent i = new Intent(this, GenreActivity.class);
                startActivityForResult(i, GenreActivity.REQUEST_CODE);
            } else if (requestCode == GenreActivity.REQUEST_CODE) {

            }
        }
    }
}
