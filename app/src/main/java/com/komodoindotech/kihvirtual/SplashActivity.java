package com.komodoindotech.kihvirtual;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class SplashActivity extends AppCompatActivity {

    private static final int AUTO_SKIP_DELAY_MILLIS = 2000;

    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */
    private static final int UI_ANIMATION_DELAY = 3000;
    private final Handler mHideHandler = new Handler();
    private final Handler mSkipHandler = new Handler();

    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        hide();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Runnable mSkipRunnable = () -> startActivity(new Intent(getApplicationContext(), MainActivity.class));
        mSkipHandler.postDelayed(mSkipRunnable, AUTO_SKIP_DELAY_MILLIS);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    private void hide() {
        // Hide UI first
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            View decorView = getWindow().getDecorView();
            // Hide the status bar.
            int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
            actionBar.hide();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.finish();
    }
}