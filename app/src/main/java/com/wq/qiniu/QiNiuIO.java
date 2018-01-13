package com.wq.qiniu;

import android.content.Context;

import com.wq.allowurl.impl.GetUrlFromDiskCacheSpImpl;
import com.wq.allowurl.inter.IGetUrlDiskFramework;
import com.wq.allowurl.inter.IGetUrlNetFramework;
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
    public IGetUrlNetFramework netFramework() {
        return new QiNiuNetFrameworkImpl();
    }

    @Override
    public IGetUrlDiskFramework diskFramework() {
        return new GetUrlFromDiskCacheSpImpl(mContext);
    }
}
