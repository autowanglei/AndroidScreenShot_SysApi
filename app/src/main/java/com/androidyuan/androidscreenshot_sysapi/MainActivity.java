package com.androidyuan.androidscreenshot_sysapi;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.androidyuan.lib.screenshot.ScreenShotActivity;

import java.lang.ref.SoftReference;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
    }

    public void onClickShot( View view ) {
        Intent intent = new Intent(this, ScreenShotActivity.class);

        String path = new SoftReference<>(getApplicationContext()).get().getExternalFilesDir("screenshot")
                .getAbsoluteFile() + "/" + SystemClock.currentThreadTimeMillis() + ".png";
        intent.putExtra(ScreenShotActivity.SCREEN_SHOT_PATH, path);
        startActivity(intent);
    }
}
