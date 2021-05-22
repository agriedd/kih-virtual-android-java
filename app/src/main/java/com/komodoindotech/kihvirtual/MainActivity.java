package com.komodoindotech.kihvirtual;

import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {


    Handler handler = new Handler();
    Boolean backPressed = false;

    /**
     *
     * @param savedInstanceState
     * @// TODO: 5/7/2021 tekan home 2x maka refresh listArticleViewModel
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
//                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);


    }

    @Override
    public void onBackPressed() {
        if(backPressed){
            super.onBackPressed();
        } else {
            Snackbar.make(findViewById(R.id.container), "Tekan sekali lagi untuk menutup aplikasi", 2000).show();
            backPressed = true;
            handler.postDelayed(() -> backPressed = false, 2000);
        }
    }
}