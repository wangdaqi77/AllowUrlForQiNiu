package com.wq.allowurldemo.qiniu;

import com.wq.allowurl.base.AbsRuleHandler;
import com.wq.allowurldemo.ReqParams;

/**
 * Create by wq on 2018/1/11.
 */
@SuppressWarnings("all")
public class QiNiuRuleHander extends AbsRuleHandler<ReqParams, String> {
    // http://memberdata.***.com/17f0627291760d0800d4af3f0371c269
    // ?e=1512374575
    // &token=dTVWOtVUIiuiyke-tBBl8pl1w6sdK3iO_kE4p9yQ:lLQPEeHQ0ZYPkn894zID1YAeIe4
    private String qnUrl;

    public QiNiuRuleHander(ReqParams disallowUrl) {
        super(disallowUrl);
    }

    @Override
    public String getKey() {
        String testParam = getParams().getTestParam();
        String hostAndKey = getHostAndKey(testParam);  //"http://memberdata.***.com/17f0627291760d0800d4af3f0371c269"
        int lastIndexOf = hostAndKey.lastIndexOf("/");
        if (lastIndexOf != -1) {
            return hostAndKey.substring(lastIndexOf + 1);
        }
        return hostAndKey;// 17f0627291760d0800d4af3f0371c269
    }

    @Override
    public boolean isDisallow() {
        return isTimeOut(getOutTime());
    }

    @Override
    public String getAllowValue() {
        return qnUrl;
    }

    @Override
    public void setAllowValue(String s) {
        this.qnUrl = s;
    }

    public long getOutTime() {
        long result = 0;
        try {
            String timeKey = "?e=";
            String timeStr = getAllowValue().substring(getAllowValue().indexOf(timeKey) + timeKey.length(), getAllowValue().indexOf("&token="));
            result = Long.parseLong(timeStr);
        } catch (Exception e) {
            // no thing
        }
        return result;
    }

    /**
     * 是否超时
     * 此处过期时长1小时
     */
    private boolean isTimeOut(long outTime) {
        return System.currentTimeMillis() / 1000 > outTime;
    }

    /**
     * 获取host加上key e:http://memberdata.***.com/17f0627291760d0800d4af3f0371c269
     */
    static String getHostAndKey(final String url) {
        if (url.contains("&token=")) {
            String hostAndKey = url;
            int index = url.indexOf("?");
            if (index != -1) {
                hostAndKey = url.substring(0, index);
                return hostAndKey;
            }
        }
        return url;
    }
}
