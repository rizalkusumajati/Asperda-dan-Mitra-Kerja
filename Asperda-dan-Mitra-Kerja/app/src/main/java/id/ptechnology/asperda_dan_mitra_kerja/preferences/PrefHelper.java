package id.ptechnology.asperda_dan_mitra_kerja.preferences;

import android.content.SharedPreferences;

import id.ptechnology.asperda_dan_mitra_kerja.AsperdaApp;

/**
 * Created by macmini2 on 11/11/16.
 */

public class PrefHelper
{
    private static SharedPreferences preferences;

    public static void clearAllPreferences()
    {
        initPref();
        SharedPreferences.Editor localEditor = preferences.edit();
        localEditor.clear();
        localEditor.apply();
    }

    public static void clearPreference(PrefKey paramPrefKey)
    {
        initPref();
        SharedPreferences.Editor localEditor = preferences.edit();
        localEditor.remove(paramPrefKey.toString());
        localEditor.apply();
    }

    public static boolean getBoolean(PrefKey paramPrefKey)
    {
        initPref();
        return preferences.getBoolean(paramPrefKey.toString(), false);
    }

    public static int getInt(PrefKey paramPrefKey)
    {
        initPref();
        return preferences.getInt(paramPrefKey.toString(), -1);
    }

    public static String getString(PrefKey paramPrefKey)
    {
        initPref();
        return preferences.getString(paramPrefKey.toString(), "");
    }

    private static void initPref()
    {
        preferences = AsperdaApp.getInstance().getSharedPreferences();
    }

    public static void setBoolean(PrefKey paramPrefKey, boolean paramBoolean)
    {
        initPref();
        SharedPreferences.Editor localEditor = preferences.edit();
        localEditor.putBoolean(paramPrefKey.toString(), paramBoolean);
        localEditor.apply();
    }

    public static void setInt(PrefKey paramPrefKey, int paramInt)
    {
        initPref();
        SharedPreferences.Editor localEditor = preferences.edit();
        localEditor.putInt(paramPrefKey.toString(), paramInt);
        localEditor.apply();
    }

    public static void setString(PrefKey paramPrefKey, String paramString)
    {
        initPref();
        SharedPreferences.Editor localEditor = preferences.edit();
        localEditor.putString(paramPrefKey.toString(), paramString);
        localEditor.apply();
    }
}
