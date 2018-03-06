package com.wq.allowurldemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.wq.allowurl.Allow;
import com.wq.allowurl.callback.OnAllowValueCallBack;
import com.wq.allowurldemo.qiniu.QiNiuRuleHander;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        test(null);
    }

    public void test(View view) {
        int count = 0;
        while (true) {
            count++;
            if (count > 3) break;

            ImageView imageView = new ImageView(this);
            ReqParams reqParams = new ReqParams();
            reqParams.setTestParam("http://memberdata.***.com/17f0627291760d0800d4af3f0371c269?e=1510000000&token=HAHA-HEHE_HEIHEI");
            Allow.load(new QiNiuRuleHander(reqParams), imageView, new OnAllowValueCallBack<ImageView, String>() {
                @Override
                public void success(ImageView target, String qnUrl) {
                    // 图片框架加载图片 ...
                    if (qnUrl != null) {
                        Log.i("MainActivity", "url : " + qnUrl);
                    }
                }
            });

        }
    }
}
