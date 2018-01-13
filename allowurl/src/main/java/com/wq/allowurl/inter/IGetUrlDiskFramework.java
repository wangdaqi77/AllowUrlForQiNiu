package com.wq.allowurl.inter;

import com.wq.allowurl.base.AbsRuleHandler;

/**
 *
 * Create by wq on 2018/1/11.
 */

public interface IGetUrlDiskFramework {
    void save(AbsRuleHandler ruleHandler, String url);

    /**
     * 缓存不存在请return null
     */
    String getDownUrl(AbsRuleHandler ruleHandler);
}
