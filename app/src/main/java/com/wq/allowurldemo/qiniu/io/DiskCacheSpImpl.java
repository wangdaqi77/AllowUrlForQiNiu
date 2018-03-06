package com.wq.allowurldemo.qiniu.io;

import android.content.Context;
import android.content.SharedPreferences;

import com.wq.allowurl.io.IDisk;
import com.wq.allowurl.rule.AbsRuleHandler;

/**
 * Create by wq on 2018/1/11.
 */
@SuppressWarnings("all")
public class DiskCacheSpImpl implements IDisk<String> {
    private static DiskCacheSpImpl INSTANCE;
    private static final String SP_NAME = "qn_urls";
    private final SharedPreferences mSp;
    private final SharedPreferences.Editor mEdit;

    public static DiskCacheSpImpl getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new DiskCacheSpImpl(context);
        }
        return INSTANCE;
    }

    public DiskCacheSpImpl(Context context) {
        if (null == context){
            throw new NullPointerException("context can't null !!");
        }
        mSp = context.getApplicationContext().getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        mEdit = mSp.edit();
    }

    public void save(AbsRuleHandler ruleHandler, String url) {
        mEdit.putString(ruleHandler.getKey(), url).apply();
    }

    public String getAllowValue(AbsRuleHandler ruleHandler) {
        return mSp.getString(ruleHandler.getKey(), null);
    }
}
