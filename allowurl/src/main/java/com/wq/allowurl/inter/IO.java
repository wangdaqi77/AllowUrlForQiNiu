package com.wq.allowurl.inter;

/**
 * Create by wq on 2018/1/11.
 */
public interface IO {
    IGetUrlNetFramework netFramework();     // 从网络获取
    IGetUrlDiskFramework diskFramework();   // 从磁盘缓存获取
}
