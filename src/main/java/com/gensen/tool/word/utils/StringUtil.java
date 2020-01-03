package com.gensen.tool.word.utils;

import java.util.Random;

/**
 * @author Gensen.Lee
 * @date 2019/12/27 15:53
 */
public class StringUtil {

    private static String CHARCONSTANT = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";


    public static boolean isEmpty(String str) {
        if (str == null || str.length() <= 0 || str == ""){
            return true;
        }
        return false;
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }


    /**
     * 随机字符串
     *
     * @param length
     * @return
     */
    public static String randomString(int length) {
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(61);
            sb.append(CHARCONSTANT.charAt(number));
        }
        return sb.toString();
    }

}
