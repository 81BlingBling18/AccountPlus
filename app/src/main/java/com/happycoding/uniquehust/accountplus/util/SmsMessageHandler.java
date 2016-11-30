package com.happycoding.uniquehust.accountplus.util;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yifan on 11/30/16.
 */

public class SmsMessageHandler {

    public static ArrayList<String> handleMessage(String msg) {
        String pattern = "^\\D*?(\\d{4})\\D{2}(\\d{4})(\\D*?)(支取|收入)\\(?(\\D*?)\\)?人民币([0-9|.]+)元";
        ArrayList<String> list = new ArrayList<>();
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(msg);

        if (m.find()) {
            for (int i = 1; i <= 6; i++) {
                list.add(m.group(i));
            }
        }

        return list;
    }
}
