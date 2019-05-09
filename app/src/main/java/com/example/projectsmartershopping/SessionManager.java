package com.example.projectsmartershopping;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.projectsmartershopping.Fragments.CartFragment;
import com.example.projectsmartershopping.Fragments.HomeFragment;
import com.example.projectsmartershopping.activities.Home;
import com.example.projectsmartershopping.activities.LoginActivity;
import com.example.projectsmartershopping.activities.logoutActivity;

import java.util.HashMap;

public class SessionManager {

    SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;
    public Context context;
    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "LOGIN";
    private static final String LOGIN = "IS_LOGIN";
    public static final String NAME = "NAME";
    public static final String EMAIL = "EMAIL";
    public static final String ID = "ID";
    public static final String PHONE = "PHONE";
    public static final String ADDRESS = "ADDRESS";
    public static final String STREET = "STREET";

    public SessionManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = sharedPreferences.edit();

    }

    public void createSession(String name, String email, String id) {
        editor.putBoolean(LOGIN, true);
        editor.putString(NAME, name);
        editor.putString(EMAIL, email);
        editor.putString(ID, id);
        editor.apply();
    }

    public boolean isLogin() {
        return sharedPreferences.getBoolean(LOGIN, false);
    }

    public void checkLogin() {
        if (!this.isLogin()) {
            Intent i = new Intent(context, LoginActivity.class);
            context.startActivity(i);
            ((logoutActivity) context).finish();
        }
    }

    public HashMap<String, String> getUserDetail() {
        HashMap<String, String> user = new HashMap<>();
        user.put(NAME, sharedPreferences.getString(NAME, null));
        user.put(EMAIL, sharedPreferences.getString(EMAIL, null));
        user.put(ID, sharedPreferences.getString(ID, null));
        return user;
    }

    public void logout() {
        editor.clear();
        editor.commit();
        Intent i = new Intent(context, CartFragment.class);
        context.startActivity(i);
        ((logoutActivity) context).finish();
    }

    public void createSession(String name, String email, String id, String phone, String address, String street) {
        editor.putBoolean(LOGIN, true);
        editor.putString(NAME, name);
        editor.putString(EMAIL, email);
        editor.putString(ID, id);
        editor.putString(PHONE, phone);
        editor.putString(ADDRESS, address);
        editor.putString(STREET, street);
        editor.apply();
    }
}