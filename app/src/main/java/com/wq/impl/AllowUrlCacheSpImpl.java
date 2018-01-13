package com.wq.impl;

import android.content.Context;
import android.content.SharedPreferences;

import com.wq.allowurl.base.AbsRuleHandler;
import com.wq.allowurl.inter.IAllowUrDiskFramework;

/**
 * Create by wq on 2018/1/11.
 */
@SuppressWarnings("all")
public class AllowUrlCacheSpImpl implements IAllowUrDiskFramework {
    private static AllowUrlCacheSpImpl INSTANCE;
    private static final String SP_NAME = "qn_urls";
    private final SharedPreferences mSp;
    private final SharedPreferences.Editor mEdit;

    public static AllowUrlCacheSpImpl getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new AllowUrlCacheSpImpl(context);
        }
        return INSTANCE;
    }

    public AllowUrlCacheSpImpl(Context context) {
        if (null == context){
            throw new NullPointerException("context can't null !!");
        }
        mSp = context.getApplicationContext().getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        mEdit = mSp.edit();
    }

    public void save(AbsRuleHandler ruleHandler, String url) {
        mEdit.putString(ruleHandler.getKey(), url).apply();
    }

    public String getDownUrl(AbsRuleHandler ruleHandler) {
        return mSp.getString(ruleHandler.getKey(), null);
    }
}
