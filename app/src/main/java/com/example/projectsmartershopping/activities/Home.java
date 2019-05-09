package com.example.projectsmartershopping.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Button;

import com.example.projectsmartershopping.Fragments.AccountFragment;
import com.example.projectsmartershopping.Fragments.CartFragment;
import com.example.projectsmartershopping.Fragments.HomeFragment;
import com.example.projectsmartershopping.R;


public class Home extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private Button setting, orders, to_be_shipped, shipped, to_be_reviewed, in_dispute, help_center;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);

        Fragment home = new HomeFragment();
        loadFragment(home);

    }

    private boolean loadFragment (Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment = null;

        switch (menuItem.getItemId()) {
            case R.id.navigation_home:
                fragment = new HomeFragment();
                break;
            case R.id.navigation_cart:
                fragment = new CartFragment();
                break;
            case R.id.navigation_account:
                fragment = new AccountFragment();
                break;
        }
        return loadFragment(fragment);
    }

}