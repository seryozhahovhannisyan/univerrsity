package com.university.web.util;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class CommonValidator {

    public static boolean isEmpty(Set<?> set) {
        return set == null || set.size() == 0;
    }

    public static boolean isEmpty(List<?> list) {
        return list == null || list.size() == 0;
    }

    public static boolean isEmpty(Object[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isEmpty(String data) {
        return data == null || data.trim().length() == 0;
    }

    public static boolean isEmpty(Map<?, ?> map) {
        return map == null || map.size() == 0;
    }

    public static boolean isEmailAddress(String email) {
        return email != null && !email.isEmpty() && email.matches("^[a-z,A-Z]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z0-9]{2,4})$");
    }

    public static boolean isPositiveNumber(String str) {

        if(str == null){
            return false;
        }

        try {
            Integer pos = Integer.parseInt(str);
            return pos > 0 ? true : false;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}