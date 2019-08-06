package tgn.com.emergencyapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceManager {
    private static final String SHR_PRF = "emergencyprf";
    private Context context;
    SharedPreferences sharedPreferences;

    public PreferenceManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(SHR_PRF, Context.MODE_PRIVATE);
    }



    public void putString(String key, String value) {
        sharedPreferences.edit().putString(key, value).apply();
    }

    public String getString(String key) {
        return sharedPreferences.getString(key, "");
    }

    public void putBoolean(String key, boolean value) {
        sharedPreferences.edit().putBoolean(key, value).apply();
    }

    public boolean getBoolean(String key) {
        return sharedPreferences.getBoolean(key, false);
    }

    public void putInt(String key, int value) {
        sharedPreferences.edit().putInt(key, value).apply();
    }

    public int getInt(String key) {
        return sharedPreferences.getInt(key, 0);
    }

}
