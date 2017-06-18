package com.shantoo.develop.library.utils;

import java.lang.reflect.Field;
import java.util.List;

/**
 * 字符串的工具类
 */
public class StringUtil {

    /**
     * 判断字符串是否有值，如果为null或者是空字符串或者只有空格或者为"null"字符串，则返回true，否则则返回false
     */
    public static boolean isEmpty(String value) {
        return value == null || "".equalsIgnoreCase(value.trim()) || "null".equalsIgnoreCase(value.trim());
    }

    /**
     * 将一个String[] 中的数据转换成String
     */
    public static String toString(String[] array) {
        if (array == null) {
            return null;
        }
        String str = "";
        for (String string : array) {
            str += string;
        }
        return str;
    }

    /**
     * 将一个List<String> 中的数据转换成String
     */
    public static String toString(List<String> list) {
        if (list == null) {
            return null;
        }
        String str = "";
        for (String string : list) {
            str += string;
        }
        return str;
    }

    /**
     * 将一个List<String> 中的数据转换成String[]
     */
    public static String[] toStringArray(List<String> list) {
        return list.toArray(new String[list.size()]);
    }

    /**
     * 校验是否是正确的电话号码
     */
    public static boolean checkTel(String tel) {
        boolean flag = false;
        if (!isEmpty(tel) && tel.length() == 11) {
           /* if (tel.startsWith("130") ||
                    tel.startsWith("131") ||
                    tel.startsWith("132") ||
                    tel.startsWith("133") ||
                    tel.startsWith("134") ||
                    tel.startsWith("135") ||
                    tel.startsWith("136") ||
                    tel.startsWith("137") ||
                    tel.startsWith("138") ||
                    tel.startsWith("139") ||
                    tel.startsWith("147") ||
                    tel.startsWith("150") ||
                    tel.startsWith("151") ||
                    tel.startsWith("152") ||
                    tel.startsWith("153") ||
                    tel.startsWith("155") ||
                    tel.startsWith("156") ||
                    tel.startsWith("157") ||
                    tel.startsWith("158") ||
                    tel.startsWith("159") ||
                    tel.startsWith("186") ||
                    tel.startsWith("187") ||
                    tel.startsWith("188") ||
                    tel.startsWith("189")) {
                flag = true;
            }*/
            flag = true;
        }
        return flag;
    }

    /**
     * Object To json String
     *
     * @param obj
     * @return json String
     */
    public static String objToJSONString(Object obj) {
        // 初始化返回值
        String json = "str_empty";
        if (obj == null) {
            return json;
        }
        StringBuilder buff = new StringBuilder();
        Field[] fields = obj.getClass().getFields();
        try {
            buff.append("[");
            buff.append("{");
            int i = 0;
            for (Field field : fields) {
                if (i != 0) {
                    buff.append(",");
                }
                buff.append(field.getName());
                buff.append(":");
                buff.append("\"");
                buff.append(field.get(obj) == null ? "" : field.get(obj));
                buff.append("\"");
                i++;
            }
            buff.append("}");
            buff.append("]");
            json = buff.toString();
        } catch (Exception e) {
            throw new RuntimeException("cause:" + e.toString());
        }
        return json;
    }

    public static String listToString(List list) {
        StringBuffer sb = new StringBuffer("");
        if (null != list) {
            String[] str = new String[list.size()];
            for (int i = 0; i < list.size(); i++) {
                str[i] = list.get(i).toString();
            }
            arrayToString(str);
            sb.append(arrayToString(str));
        }
        return sb.toString();
    }

    /**
     * 把数组转换成'',格式的字符串输出
     *
     * @param array
     * @return
     */
    public static String arrayToString(String[] array) {
        StringBuffer sb = new StringBuffer("");
        if (null != array) {
            for (int i = 0; i < array.length - 1; i++) {
                sb.append("'")
                        .append(array[i])
                        .append("'")
                        .append(",");
            }
            if (array.length > 0) {
                sb.append("'").append(array[array.length - 1]).append("'");
            }
        }
        return sb.toString();
    }

    /**
     * Convert an array of strings to one string.
     * Put the 'separator' string between each element.
     *
     * @param array
     * @param separator
     * @return
     */
    public static String arrayToString(String[] array, String separator) {
        StringBuffer stringBuffer = new StringBuffer();
        if (array == null) {
            return "";
        }
        if (array.length > 0) {
            stringBuffer.append(array[0]);
            for (int i = 1; i < array.length; i++) {
                stringBuffer.append(separator);
                stringBuffer.append(array[i]);
            }
        }
        return stringBuffer.toString();
    }
}
