package com.example.easycount;

import android.content.pm.PackageManager;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.TextView;

public class ProfileSelectorActivity extends AppCompatActivity {

    private final String TAG = ProfileSelectorActivity.class.getSimpleName();
    private Boolean isFirstProfileEmpty = true;

    private static final int WRITE_EXTERNAL_STORAGE = 101;
    private static final String USER_INFO  = "USER_INFO";
    private static final String USER_INFO_NAME  = "USER_INFO_NAME";
    private static final String USER_INFO_EMAIL  = "USER_INFO_EMAIL";
    private static final String USER_INFO_COMPANIES = "USER_INFO_COMPANIES";
    private static final String USER_INFO_ROLE = "USER_INFO_ROLE";
    private TextView login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_selector);


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == WRITE_EXTERNAL_STORAGE){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){


            }
        }
    }
}