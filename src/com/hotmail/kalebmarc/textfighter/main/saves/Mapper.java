package com.hotmail.kalebmarc.textfighter.main.saves;

import com.hotmail.kalebmarc.textfighter.main.Handle;
import com.hotmail.kalebmarc.textfighter.main.Ui;

import java.util.*;

public class Mapper {
    public static Map<String, Object> data;

    public static boolean contains(String key) {
        return data.containsKey(key);
    }

    public static boolean exists() {
        return data != null;
    }

    public static boolean getBoolean(String key) {
        Object tempObject = get(key);

        if (tempObject instanceof Boolean)
            return (Boolean) tempObject;
        if (tempObject instanceof String)
            if (tempObject.toString().equalsIgnoreCase("true") || tempObject.toString().equalsIgnoreCase("1"))
                return true;
            else if (tempObject.toString().equalsIgnoreCase("false") || tempObject.toString().equalsIgnoreCase("0"))
                return false;
        if (tempObject instanceof Number)
            if (((Number) tempObject).intValue() == 1)
                return true;
            else if (((Number) tempObject).intValue() == 0)
                return false;
        return false;
    }

    public static boolean hasValue(String key) {
        Object tempObject = data.get(key);

        return (tempObject != null);
    }

    public static boolean isEmpty() {
        return data.isEmpty() || data == null;
    }

    public static byte getByte(String key) {
        Object tempObject = get(key);

        if (tempObject instanceof Byte)
            return (Byte) tempObject;
        if (tempObject instanceof String)
            if (Ui.isNumber(tempObject.toString()))
                return Byte.parseByte(tempObject.toString());
        if (tempObject instanceof Number)
            return Byte.parseByte(tempObject.toString());
        return -1;
    }

    public static char getChar(String key) {
        Object tempObject = get(key);

        if (tempObject instanceof Character)
            return (Character) tempObject;
        if (tempObject instanceof String)
            return tempObject.toString().charAt(0);
        if (tempObject instanceof Number)
            return tempObject.toString().charAt(0);
        return '\u0000';
    }

    public static double getDouble(String key) {
        Object tempObject = get(key);

        if (tempObject instanceof Double)
            return (Double) tempObject;
        if (tempObject instanceof String)
            if (Ui.isDecimalNumber(tempObject.toString()))
                return Double.parseDouble(tempObject.toString());
        if (tempObject instanceof Number)
            return Double.parseDouble(tempObject.toString());
        return -1;
    }

    public static int getInteger(String key) {
        Object tempObject = get(key);

        if (tempObject instanceof Integer)
            return (Integer) tempObject;
        if (tempObject instanceof String)
            if (Ui.isNumber(tempObject.toString()))
                return Integer.parseInt(tempObject.toString());
        if (tempObject instanceof Number)
            return Integer.parseInt(tempObject.toString());
        return -1;
    }

    public static List<?> getList(String key) {
        Object tempObject = get(key);

        if (tempObject instanceof List<?>)
            return (List) tempObject;
        return null;
    }

    public static long getLong(String key) {
        Object tempObject = get(key);

        if (tempObject instanceof Long)
            return (Long) tempObject;
        if (tempObject instanceof String)
            if (Ui.isNumber(tempObject.toString()))
                return Long.parseLong(tempObject.toString());
        if (tempObject instanceof Number)
            return Long.parseLong(tempObject.toString());
        return -1;
    }

    public static Map<?, ?> getMap(String key) {
        Object tempObject = get(key);

        if (tempObject instanceof Map<?, ?>)
            return (Map) tempObject;
        return null;
    }

    public static Map<String, Object> getValues() {
        if (!isEmpty())
            return data;
        return null;
    }

    public static Object get(String key) {
        if (isEmpty())
            return null;

        final String[] nodes = key.split("\\.");
        Map curMap = data;

        for (int i = 0; i <= nodes.length - 1; ++i) {
            Object child = curMap.get(nodes[i]);

            if (child == null) return null;
            else if (!(child instanceof Map)) {
                if (i == nodes.length - 1)
                    return child;
                else return null;
            }

            curMap = (Map) child;
        }
        return null;
    }

    public static Set<String> getKeys() {
        if (!isEmpty())
            return data.keySet();
        return new HashSet<>();
    }

    public static short getShort(String key) {
        Object tempObject = get(key);

        if (tempObject instanceof Short)
            return (Short) tempObject;
        if (tempObject instanceof String)
            if (Ui.isNumber(tempObject.toString()))
                return Short.parseShort(tempObject.toString());
        if (tempObject instanceof Number)
            return Short.parseShort(tempObject.toString());
        return -1;
    }

    public static String getString(String key) {
        Object tempObject = get(key);

        if (tempObject instanceof String)
            return (String) tempObject;
        return null;
    }

    //Thanks http://stackoverflow.com/a/38951302/3291305 !
public static void set(String key, Object object) {

if (!exists())
return;

final String[] nodes = key.split("\\.");

        Map<String, Object> cur = data;

for (int i = 0; i <= nodes.length - 2; ++i) {
Object val = cur.get(nodes[i]);
if (val == null) {
val = new LinkedHashMap();
cur.put(nodes[i], val);
} else if (!(val instanceof Map)) {
Handle.error("There was a problem saving your game.");
}
            cur = (Map<String, Object>) val;
        }
cur.put(nodes[nodes.length - 1], object);
}
}
