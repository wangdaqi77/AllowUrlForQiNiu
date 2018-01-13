package com.wq.allowurl;

import android.net.Uri;

public class UriDecodeFix {
    private static final String ALLOWED_URI_CHARS = "@#&=*+-_.,:!?()/~'%";
    public static String fix(String uri) {
        return Uri.encode(uri, ALLOWED_URI_CHARS);
    }
}
