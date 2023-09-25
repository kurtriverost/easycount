package com.example.easycount;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;

import android.os.Build;
import androidx.annotation.NonNull;


import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import utilities.RequestPermissionUtils;
import utilities.UserPreferenceUtility;

public class SplashActivity extends AppCompatActivity {
    private static final String TAG = SplashActivity.class.getSimpleName();

    private final int PERMISSION_REQUEST_CODE = 223;
    private static boolean deniedPermission = false;
    private static String[] notGrantedPermissions;
    private static String[] necessaryPermissions;
    private ImageView splashImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        necessaryPermissions = new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE};
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!RequestPermissionUtils.hasPermissions(SplashActivity.this, necessaryPermissions)){
            if(deniedPermission && !RequestPermissionUtils.shouldShowRequestPermissionRationaleSplashActivity(SplashActivity.this)){
                showMessageOKCancel("Tiene que dar permisos a Teléfono (Ajustes -> Aplicaciones -> RFIDAdmin -> Permisos -> Memoria)",
                        (dialog, which) -> {
                            RequestPermissionUtils.goToAppPermissionSettings(SplashActivity.this);
                        });
            }else{
                checkPermissionsToRequest();
                RequestPermissionUtils.requestNecessaryPermissions(SplashActivity.this, notGrantedPermissions, PERMISSION_REQUEST_CODE);
            }
        }else{
            setupTimerTask();
            setSplashAnimation();
        }

    }

    // *** Esto hace el efecto de 1 segundo para que se vea el splash ***

    private void setupTimerTask() {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                goToHomeOrAuth();
            }

        };

        Timer timer = new Timer();
        timer.schedule(task, 3000);
    }


    // *** Método que envía al usuario al login si no esta registrado y al home si lo está. ***

    private void goToHomeOrAuth() {
        boolean isLoggedIn = UserPreferenceUtility.isLoggedIn(this);
        goToHomeActivity();
/*        if (isLoggedIn)
            goToHomeActivity();
        else
            goToLoginActivity();*/
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        boolean allowed = true;
        if (requestCode == PERMISSION_REQUEST_CODE) {
            for (int res : grantResults) {
                allowed = allowed && (res == PackageManager.PERMISSION_GRANTED);
            }
        } else {
            allowed = false;
        }

        if (!allowed)
            deniedPermission = true;
        else{
            setupTimerTask();
        }
    }

    private void checkPermissionsToRequest(){
        ArrayList<String> notGrantedArrayList = new ArrayList<>();
        for(String permission : necessaryPermissions){
            if(!RequestPermissionUtils.hasPermissions(SplashActivity.this, new String[] {permission})){
                notGrantedArrayList.add(permission);
            }
        }
        if(notGrantedArrayList.size() == necessaryPermissions.length){
            notGrantedPermissions = necessaryPermissions;
        }else{
            notGrantedPermissions = notGrantedArrayList.toArray(new String[0]);
        }
    }

    private void goToHomeActivity(){
        Intent homeIntent = new Intent();
        homeIntent.setClass(SplashActivity.this, MainActivity.class );
        startActivity(homeIntent);
        finish();
    }

    private void goToLoginActivity(){
        Intent login = new Intent().setClass(SplashActivity.this, LoginActivity.class );
        startActivity(login);
        finish();
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(SplashActivity.this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancelar", (dialog, which) -> {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        finishAffinity();
                    }
                    System.exit(0);
                })
                .create()
                .show();
    }

    private void setSplashAnimation(){
        splashImage = findViewById(R.id.splash_image);

        AlphaAnimation fadeIn = new AlphaAnimation(0.0f, 1.0f);
        fadeIn.setDuration(1000);
        fadeIn.setStartOffset(0);
        fadeIn.setFillAfter(true);

        AlphaAnimation fadeOut = new AlphaAnimation(1.0f, 0.0f);
        fadeOut.setDuration(1000);
        fadeOut.setStartOffset(1000);
        fadeOut.setFillAfter(true);

        fadeIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                splashImage.startAnimation(fadeOut);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        splashImage.startAnimation(fadeIn);
    }
}