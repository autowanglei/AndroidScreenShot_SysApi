package com.androidyuan.lib.screenshot;


import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.media.projection.MediaProjectionManager;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;

/**
 * @author wanglei
 * @date 2017/11/16 13:43
 * @description 完全透明 只是用于弹出权限申请的窗
 */
public class ScreenShotActivity extends Activity {

    public static final int REQUEST_MEDIA_PROJECTION = 0x2893;
    public static final String SCREEN_SHOT_PATH = "screen_shot_path";
    private String screenShotPath;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        //        setTheme(android.R.style.Theme_Dialog);//这个在这里设置 之后导致 的问题是 背景很黑
        super.onCreate(savedInstanceState);
        //如下代码 只是想 启动一个透明的Activity 而上一个activity又不被pause
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        getWindow().setDimAmount(0f);
        screenShotPath = getIntent().getStringExtra(SCREEN_SHOT_PATH);
        requestScreenShot();
    }

    public void requestScreenShot() {
        if (Build.VERSION.SDK_INT >= 21) {
            startActivityForResult(((MediaProjectionManager) getSystemService("media_projection"))
                    .createScreenCaptureIntent(), REQUEST_MEDIA_PROJECTION);
        } else {
            toast("版本过低,无法截屏");
        }
    }

    private void toast( String str ) {
        Toast.makeText(ScreenShotActivity.this, str, Toast.LENGTH_LONG).show();
    }

    protected void onActivityResult( int requestCode, int resultCode, Intent data ) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_MEDIA_PROJECTION: {
                if (resultCode == -1 && data != null) {
                    Shotter shotter = new Shotter(ScreenShotActivity.this, data, screenShotPath);
                    shotter.startScreenShot(new Shotter.OnShotListener() {
                        @Override
                        public void onFinish( String screenShotPath ) {
                            toast("shot finish!");
                            TODO
                            finish(); // don't forget finish activity
                        }
                    });
                }
            }
        }
    }


}