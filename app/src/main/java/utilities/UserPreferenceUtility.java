package utilities;

import android.content.Context;
import android.content.SharedPreferences;

public class UserPreferenceUtility {

    private static final String PREFS_USER = "PREFERENCES_USER";
    private static final String USER_LOGGED_IN = "USER_LOGGED_IN";

    public static Boolean isLoggedIn(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREFS_USER, Context.MODE_PRIVATE);
        return preferences.getBoolean(USER_LOGGED_IN, false);
    }
}