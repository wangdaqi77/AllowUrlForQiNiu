package com.wq.qiniu;

import android.content.Context;

import com.wq.impl.AllowUrlCacheSpImpl;
import com.wq.allowurl.inter.IAllowUrDiskFramework;
import com.wq.allowurl.inter.IAllowUrNetFramework;
import com.wq.allowurl.inter.IO;

/**
 *
 * Create by wq on 2018/1/11.
 */

public class QiNiuIO implements IO {
    private Context mContext;

    public QiNiuIO(Context context) {
        this.mContext = context;
    }

    @Override
    public IAllowUrNetFramework netFramework() {
        return new QiNiuNetFrameworkImpl();
    }

    @Override
    public IAllowUrDiskFramework diskFramework() {
        return new AllowUrlCacheSpImpl(mContext);
    }
}
