package com.wq.allowurldemo.qiniu.io;

import android.content.Context;

import com.wq.allowurl.io.IDisk;
import com.wq.allowurl.io.INet;
import com.wq.allowurl.io.IO;

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
    public INet netFramework() {
        return new QiNiuNetImpl();
    }

    @Override
    public IDisk diskFramework() {
        return new DiskCacheSpImpl(mContext);
    }
}
