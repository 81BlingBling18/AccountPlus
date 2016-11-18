package com.happycoding.uniquehust.accountplus.global;

import java.lang.reflect.Type;
import java.util.HashMap;
import com.happycoding.uniquehust.accountplus.R;

/**
 * Created by yifan on 11/18/16.
 */

public class TypeKeyValue {
    public static HashMap<Integer, Integer> typeMap = null;
    public TypeKeyValue() {
        for (int i = 0; i < 17; i++) {
            typeMap.put(R.drawable.button_bag + i, R.string.type_bag + i);
        }
    }
}
