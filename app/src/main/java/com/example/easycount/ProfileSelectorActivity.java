package com.example.easycount;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ProfileSelectorActivity extends AppCompatActivity {

    private final String TAG = ProfileSelectorActivity.class.getSimpleName();
    private boolean[] areProfilesEmpty = { true, true, true, true };

    private EditText profileNameEditText;
    private Button firstProfileButton;
    private Button secondProfileButton;
    private Button thirdProfileButton;
    private Button fourthProfileButton;

    private static final int WRITE_EXTERNAL_STORAGE = 101;
    private static final String USER_INFO  = "USER_INFO";
    private static final String USER_INFO_NAME  = "USER_INFO_NAME";
    private static final String USER_INFO_EMAIL  = "USER_INFO_EMAIL";
    private static final String USER_INFO_COMPANIES = "USER_INFO_COMPANIES";
    private static final String USER_INFO_ROLE = "USER_INFO_ROLE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_selector);
        setViewElements();
        setListeners();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == WRITE_EXTERNAL_STORAGE){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){


            }
        }
    }

    public void setViewElements(){
        firstProfileButton = findViewById(R.id.first_profile_btn);
        secondProfileButton = findViewById(R.id.second_profile_btn);
        thirdProfileButton = findViewById(R.id.third_profile_btn);
        fourthProfileButton = findViewById(R.id.fourth_profile_btn);
    }

    private void setListeners(){
        firstProfileButton.setOnClickListener(v -> {
            if (areProfilesEmpty[0]){
                showEnterProfileNameDialog();
            } else {
                Intent intent = new Intent(this, CounterListActivity.class);
                intent.putExtra("profileName", "perfil 1");
                startActivity(intent);
            }
        });

        secondProfileButton.setOnClickListener(v -> {
            if (areProfilesEmpty[1]){
                showEnterProfileNameDialog();
            } else {
                Intent intent = new Intent(this, CounterListActivity.class);
                intent.putExtra("profileName", "perfil 2");
                startActivity(intent);
            }
        });

        thirdProfileButton.setOnClickListener(v -> {
            if (areProfilesEmpty[2]){
                showEnterProfileNameDialog();
            } else {
                Intent intent = new Intent(this, CounterListActivity.class);
                intent.putExtra("profileName", "perfil 3");
                startActivity(intent);
            }
        });

        fourthProfileButton.setOnClickListener(v -> {
            if (areProfilesEmpty[3]){
                showEnterProfileNameDialog();
            } else {
                Intent intent = new Intent(this, CounterListActivity.class);
                intent.putExtra("profileName", "perfil 4");
                startActivity(intent);
            }
        });
    }

    public void showEnterProfileNameDialog(){
        final Dialog discountDialog = new Dialog(ProfileSelectorActivity.this);
        discountDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        discountDialog.setContentView(R.layout.dialog_enter_new_profile_name);
        discountDialog.setCanceledOnTouchOutside(false);
        discountDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        profileNameEditText = discountDialog.findViewById(R.id.dialog_profile_name_edit_text);
        Button okButton = discountDialog.findViewById(R.id.dialog_ok_button);
        Button cancelButton = discountDialog.findViewById(R.id.dialog_cancel_button);



        okButton.setOnClickListener(view -> {
            String profileName = profileNameEditText.getText().toString();
            Intent intent = new Intent(this, CounterListActivity.class);
            intent.putExtra("profileName", "profileName");
            startActivity(intent);
        });


        cancelButton.setOnClickListener(view -> {
            discountDialog.dismiss();
        });
        discountDialog.show();
    }
}
