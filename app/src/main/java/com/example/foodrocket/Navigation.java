package com.example.foodrocket;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;


public class Navigation extends AppCompatActivity {
    private static final String TAG = Navigation.class.getSimpleName();
    ChipNavigationBar bottomNav;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_activity);

        bottomNav = findViewById(R.id.bottom_nav);

        if (savedInstanceState == null) {
            bottomNav.setItemSelected(R.id.home, true);
            fragmentManager = getSupportFragmentManager();
            MenuFragment menuFragment = new MenuFragment();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, menuFragment)
                    .commit();
        }

        bottomNav.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int id) {
                Fragment fragment = null;
                switch (id) {
                    case R.id.home:
                        fragment = new MenuFragment();
                        break;
                    case R.id.offers:
                        fragment = new OffersFragment();
                        break;
                    case R.id.cart:
                        fragment = new CartFragment();
                        break;
                    case R.id.account:
                        fragment = new AccountFragment();
                        break;
                }

                if (fragment != null) {
                    fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, fragment)
                            .commit();
                } else {
                    Log.e(TAG, "Error in creating fragment");
                }
            }
        });

    }
}
