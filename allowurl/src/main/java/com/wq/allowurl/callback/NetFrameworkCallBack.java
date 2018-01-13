package com.wq.allowurl.callback;

import com.wq.allowurl.base.AbsRuleHandler;
import com.wq.allowurl.inter.IGetUrlNetFramework;

/**
 * @see IGetUrlNetFramework#load(AbsRuleHandler, NetFrameworkCallBack)
 * Create by wq on 2018/1/11.
 */
public interface NetFrameworkCallBack {
    void success(String url);
    void error();
}
