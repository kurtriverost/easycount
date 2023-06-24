package utilities;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;

import androidx.core.content.ContextCompat;

public class RequestPermissionUtils {
    private final static String TAG = RequestPermissionUtils.class.getSimpleName();

    public final static String[] PERMISSION_STRINGS = new String[]{"android.permission.READ_PHONE_STATE"};

    public final static int PERMISSION_REQUEST_CODE = 77;

    public static boolean checkPermission(Context context){
        boolean areAllPermisionGranted = true;

        for(int i = 0; i < PERMISSION_STRINGS.length; i++){
            boolean isGranted = ContextCompat.checkSelfPermission(context, PERMISSION_STRINGS[i]) == PackageManager.PERMISSION_GRANTED;
            Log.d(TAG, PERMISSION_STRINGS[i] + " permision: " + isGranted);
            areAllPermisionGranted = areAllPermisionGranted && isGranted;
        }

        return areAllPermisionGranted;
    }

    public static boolean hasPermissions(Context context, String[] permissions) {
        int res;
        for (String perms : permissions){
            res = context.checkCallingOrSelfPermission(perms);
            if (res != PackageManager.PERMISSION_GRANTED)
                return false;
        }
        return true;
    }

    public static boolean shouldShowRequestPermissionRationalePhoneAndLocation(Activity activity){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return (activity.shouldShowRequestPermissionRationale(Manifest.permission.READ_PHONE_STATE) ||
                    activity.shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION) ||
                    activity.shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_COARSE_LOCATION));
        } else {
            return true;
        }
    }

    public static boolean shouldShowRequestPermissionRationaleCamera(Activity activity){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return (activity.shouldShowRequestPermissionRationale(Manifest.permission.CAMERA));
        } else {
            return true;
        }
    }

    public static boolean shouldShowRequestPermissionRationaleCameraAndStorage(Activity activity){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return (activity.shouldShowRequestPermissionRationale(Manifest.permission.CAMERA) ||
                    activity.shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE));
        } else {
            return true;
        }
    }

    public static boolean shouldShowRequestPermissionRationaleCameraPhoneLocation(Activity activity){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return (activity.shouldShowRequestPermissionRationale(Manifest.permission.CAMERA) ||
                    activity.shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_COARSE_LOCATION) ||
                    activity.shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION) ||
                    activity.shouldShowRequestPermissionRationale(Manifest.permission.READ_PHONE_STATE));
        } else {
            return true;
        }
    }

    public static boolean shouldShowRequestPermissionRationaleSplashActivity(Activity activity){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S){
                return (activity.shouldShowRequestPermissionRationale(Manifest.permission.READ_PHONE_STATE) ||
                        activity.shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION) ||
                        activity.shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_COARSE_LOCATION) ||
                        activity.shouldShowRequestPermissionRationale(Manifest.permission.BLUETOOTH_CONNECT) ||
                        activity.shouldShowRequestPermissionRationale(Manifest.permission.BLUETOOTH_SCAN));
            }else{
                return (activity.shouldShowRequestPermissionRationale(Manifest.permission.READ_PHONE_STATE) ||
                        activity.shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION) ||
                        activity.shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_COARSE_LOCATION));
            }
        }else{
            return true;
        }

    }

    public static void requestNecessaryPermissions(Activity activity, String[] permissions, int permissionRequestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            activity.requestPermissions(permissions, permissionRequestCode);
        }
    }

    public static void goToAppPermissionSettings(Activity activity){
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = Uri.fromParts("package", activity.getPackageName(), null);
        intent.setData(uri);
        activity.startActivity(intent);
    }
}