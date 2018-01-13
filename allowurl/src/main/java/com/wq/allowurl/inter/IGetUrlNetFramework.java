package com.wq.allowurl.inter;

import com.wq.allowurl.callback.NetFrameworkCallBack;
import com.wq.allowurl.base.AbsRuleHandler;

/**
 *
 * Create by wq on 2018/1/11.
 */

public interface IGetUrlNetFramework<P> {
    void load(AbsRuleHandler<P> ruleHandler, NetFrameworkCallBack callBack);
}
