package com.wq.qiniu;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;

import com.wq.MyApplication;
import com.wq.ReqParams;
import com.wq.allowurl.base.AbsRuleHandler;
import com.wq.allowurl.callback.NetFrameworkCallBack;
import com.wq.allowurl.inter.IGetUrlNetFramework;

/**
 * Create by wq on 2018/1/11.
 */
@SuppressLint("all")
public class QiNiuNetFrameworkImpl implements IGetUrlNetFramework<ReqParams> {
    @Override
    public void load(final AbsRuleHandler<ReqParams> ruleHandler, final NetFrameworkCallBack callBack) {
        // 参数
        ReqParams params = ruleHandler.getParams();

        // 模拟网络请求
        new Thread() {
            @Override
            public void run() {
                // 请求服务器中
                SystemClock.sleep(2000);

                // 请求结果返回
                boolean result = true;
                if (result) {
                    // 模拟网络请求成功
                    onSuccess("http://memberdata.***.com/17f0627291760d0800d4af3f0371c269?e=1512374575&token=dTVWOtVUIiuiyke-tBBl8pl1w6sdK3iO_kE4p9yQ:lLQPEeHQ0ZYPkn894zID1YAeIe4");
                } else {
                    // 模拟网络请求失败
                    onError();
                }
            }

            // 成功
            private void onSuccess(final String s) {
                Looper mainLooper = MyApplication.context.getMainLooper();
                new Handler(mainLooper).post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.success(s);
                    }
                });
            }

            // 失败
            private void onError() {
                Looper mainLooper = MyApplication.context.getMainLooper();
                new Handler(mainLooper).post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.error();
                    }
                });
            }
        }.start();
    }
}
