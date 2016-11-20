package com.happycoding.uniquehust.accountplus.global;

import java.lang.reflect.Type;
import java.util.HashMap;
import com.happycoding.uniquehust.accountplus.R;
import com.happycoding.uniquehust.accountplus.details.MainActivity;

/**
 * Created by yifan on 11/18/16.
 */

public class TypeKeyValue {
    public static HashMap<Integer, Integer> idTypeMap = new HashMap<>();
    public static HashMap<Integer, Integer> typeIdMap = new HashMap<>();
    public TypeKeyValue() {
        for (int i = 0; i < 17; i++) {
            idTypeMap.put(R.drawable.button_bag + i, R.string.type_bag + i);
            typeIdMap.put(R.string.type_bag + i, R.drawable.button_bag + i);
        }
    }
}
