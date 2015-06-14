package net.v4lproik.googlanime.mvc.models;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by joel on 08/05/2015.
 */

public enum Website{
    MAL(0);

    private int id;

    Website(int i) {

    }

    private static final Map<String, Website> INDEX = new HashMap() {{
        for (Website item : Website.values()) put(item.id, item);
    }};

    public static Website getTypeFromId(int value) {
        return INDEX.containsKey(value) ? INDEX.get(value) : null;
    }

    public static Website containsValue(String value) {
        try {
            return Website.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e){
            return null;
        }
    }
}

