package com.hanergy.screenshotdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.hanergy.screenshot.ScreenShotActivity;


public class MainActivity extends AppCompatActivity {
    public static final int SCREEN_SHOT_REQUEST_CODE = 0x828;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
    }

    public void onClickShot( View view ) {
        startActivityForResult(new Intent(this, ScreenShotActivity.class), SCREEN_SHOT_REQUEST_CODE);

    }

    @Override
    protected void onActivityResult( int requestCode, int resultCode, Intent data ) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case SCREEN_SHOT_REQUEST_CODE:
                switch (resultCode) {
                    case ScreenShotActivity.SCREEN_SHOT_RESULT_CODE:
                        String path = data.getStringExtra(ScreenShotActivity.SCREEN_SHOT_PATH);
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }

    }
}
