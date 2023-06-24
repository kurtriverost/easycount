package com.example.easycount;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;

import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    private final String TAG = LoginActivity.class.getSimpleName();

    private static final int WRITE_EXTERNAL_STORAGE = 101;
    private static final String USER_INFO  = "USER_INFO";
    private static final String USER_INFO_NAME  = "USER_INFO_NAME";
    private static final String USER_INFO_EMAIL  = "USER_INFO_EMAIL";
    private static final String USER_INFO_COMPANIES = "USER_INFO_COMPANIES";
    private static final String USER_INFO_ROLE = "USER_INFO_ROLE";
    private boolean isFromLogin = false;



    private TextView login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login = findViewById(R.id.textView);


    }


    private void checkDevicePermission(String permission, int requestCode){
        if(ContextCompat.checkSelfPermission(LoginActivity.this, permission) == PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(LoginActivity.this, new String[] {permission}, requestCode);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == WRITE_EXTERNAL_STORAGE){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                if(isFromLogin){
                    isFromLogin = false;

                }
            }
        }
    }
}