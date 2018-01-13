package com.wq.allowurl;

/**
 *
 * Create by wq on 2018/1/11.
 */
@SuppressWarnings({ "all" })
public class Util {

    /**
     * 是否超时
     */
    private static boolean isTimeOut(String url) {
        return isTimeOut(getOutTime(url));
    }

    /**
     * 是否超时
     */
    static boolean isTimeOut(long outTime) {
        //Logger.e(TAG, "系统 = " + System.currentTimeMillis() / 1000 + " 过期时间 = " + (outTime - 60));
        return System.currentTimeMillis() / 1000 > outTime - 60;
    }

    /**
     * 获取过期时长
     *
     * @return 过期时长 ms
     */
    static long getOutTime(final String url) {
        long result = 0;
        try {
            String timeKey = "?e=";
            String timeStr = url.substring(url.indexOf(timeKey) + timeKey.length(), url.indexOf("&token="));
            result = Long.parseLong(timeStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取host加上key e:http://memberdata.***.com/17f0627291760d0800d4af3f0371c269
     */
    static String getHostAndKey(final String url) {
        if (url.contains("&token=")) {
            return getRemoveSuffix(url);
        }
        return url;
    }

    static String getKey(final String url) {
        String hostAndKey = getHostAndKey(url);
        if (url.contains("&token=")) {
            return getRemoveSuffix(url);
        }
        return url;
    }

    /**
     * 获取host加上key e:http://memberdata.***.com/17f0627291760d0800d4af3f0371c269
     */
    private static String getRemoveSuffix(String url) {
        String removeSuffixUrl = url;
        int index = url.indexOf("?");
        if (index != -1) {
            removeSuffixUrl = url.substring(0, index);
        }
        return removeSuffixUrl;
    }
}
