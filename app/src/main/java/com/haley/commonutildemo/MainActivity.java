package com.haley.commonutildemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.haley.commonutil.anr.AnrListener;
import com.haley.commonutil.anr.AppAnrHelper;
import com.haley.commonutil.logcatreceive.LogcatHelper;

public class MainActivity extends AppCompatActivity implements AnrListener {

    AppAnrHelper anrListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        anrListener = new AppAnrHelper("/data/anr/", this);
        anrListener.startWatching();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        anrListener.stopWatching();
    }

    public void test(View view) {
//        test anr
//        try {
//            Thread.sleep(30000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        LogcatHelper.saveLogcat();
    }

    @Override
    public void onAnr() {
        //@TODO 处理程序发生anr时的额外操作
    }
}
