package com.wq.allowurldemo;

import android.app.Application;
import android.content.Context;

import com.wq.allowurl.AllowUrl;
import com.wq.allowurldemo.qiniu.io.QiNiuIO;

/**
 *
 * Created by wq on 2018/1/11.
 */

public class MyApplication extends Application {
    public static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        AllowUrl.create().io(new QiNiuIO(this));
    }
}
