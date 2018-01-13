package com.wq.allowurl;

import com.wq.allowurl.callback.NetFrameworkCallBack;
import com.wq.allowurl.callback.OnAllowUrlSuccessListener;
import com.wq.allowurl.entity.BufferEntity;
import com.wq.allowurl.base.AbsRuleHandler;
import com.wq.allowurl.inter.IGetUrlDiskFramework;
import com.wq.allowurl.inter.IGetUrlNetFramework;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Create by wq on 2018/1/11.
 * 一个key对应一个缓冲区
 */
class GetAllowUrlBuffer<P, T> implements NetFrameworkCallBack {
    private AbsRuleHandler<P> mRuleHandler;
    private IGetUrlDiskFramework mGetUrlFromDiskCache;
    private IGetUrlNetFramework<P> mGetUrlNetFramework;
    private List<SoftReference<BufferEntity<T>>> mBuffer = new ArrayList<>();
    private String mAllowUrl;
    private boolean mRequesting;

    GetAllowUrlBuffer(IGetUrlNetFramework<P> getUrlFramework, IGetUrlDiskFramework getUrlFromDiskCache, AbsRuleHandler<P> ruleHandler) {
        this.mGetUrlNetFramework = getUrlFramework;
        this.mGetUrlFromDiskCache = getUrlFromDiskCache;
        this.mRuleHandler = ruleHandler;
    }

    private void append(T target, OnAllowUrlSuccessListener<T> callBack) {
        mBuffer.add(new SoftReference<>(new BufferEntity<>(target, callBack)));
    }

    void start(T target, OnAllowUrlSuccessListener<T> callBack) {
        // 从本地获取下载地址
        if (null != mGetUrlFromDiskCache && null == mAllowUrl) {
            mAllowUrl = mGetUrlFromDiskCache.getDownUrl(mRuleHandler);
            mRuleHandler.setAllowUrl(mAllowUrl);
        }

        if (!mRuleHandler.isDisallow()) {
            if (callBack != null) {
                callBack.success(target, mAllowUrl);
            }
            return;
        }

        append(target, callBack);

        if (mRequesting) {
            return;
        }

        if (mGetUrlNetFramework != null) {
            mRequesting = true;
            mGetUrlNetFramework.load(mRuleHandler, this);
        }
    }

    @Override
    public void success(String url) {
        try {
            mAllowUrl = UriDecodeFix.fix(url);
            mRuleHandler.setAllowUrl(mAllowUrl);
            if (null != mAllowUrl) {
                mRequesting = false;
                for (SoftReference<BufferEntity<T>> next : mBuffer) {
                    if (next.get() != null) {
                        next.get().success(mAllowUrl);
                    }
                }
            }
            mBuffer.clear();
            if (mGetUrlFromDiskCache != null) {
                mGetUrlFromDiskCache.save(mRuleHandler, mAllowUrl);
            }
        } catch (Exception e) {
            e.printStackTrace();
            error();
        }
    }

    @Override
    public void error() {
        mRequesting = false;
    }
}
