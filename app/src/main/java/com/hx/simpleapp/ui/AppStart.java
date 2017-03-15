package com.hx.simpleapp.ui;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.hx.simpleapp.AppContext;
import com.hx.simpleapp.AppManager;
import com.hx.simpleapp.R;
import com.hx.simpleapp.router.Router;
import com.hx.simpleapp.util.BasicFunction;
import com.hx.simpleapp.util.SystemBarTintManager;

import java.util.Random;

public class AppStart extends Activity {

    private static final long TIME_DELAY = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Activity activity = AppManager.getActivity(MainActivity.class);
        if (activity != null && !activity.isFinishing()) {
            finish();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        View root = findViewById(R.id.activity_app_start);
        if (root != null && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP
                && Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintResource(R.color.transparent);
        }


        /*TypeTextView ttvSay = (TypeTextView) findViewById(R.id.ttv_say);
        ttvSay.start(getRandomSentence());
        ttvSay.setOnTypeViewListener(new TypeTextView.OnTypeViewListener() {
            @Override
            public void onTypeStart() {

            }

            @Override
            public void onTypeOver() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Router.showMain(AppStart.this);
                    }
                }, TIME_DELAY);

            }
        });*/


        ImageView iv_logo = (ImageView) findViewById(R.id.iv_logo);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.1f, 1.0f);
        alphaAnimation.setDuration(TIME_DELAY);
        iv_logo.startAnimation(alphaAnimation);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //判断网络是否可用
                if (!BasicFunction.isNetworkConnected(AppStart.this)) {
                    //没有网络
                    AppContext.toastShort(R.string.tip_network_error);
                }else {
                    Router.showMain(AppStart.this);
                }

            }
        });


    }


    @TargetApi(19)
    protected void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }


    private String getRandomSentence() {
        String[] array = getResources().getStringArray(R.array.sentence);
        int index = new Random().nextInt(array.length);
        return array[index];
    }


}
