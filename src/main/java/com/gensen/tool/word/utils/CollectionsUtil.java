package com.gensen.tool.word.utils;

import java.util.Collection;

/**
 * @author Gensen.Lee
 * @date 2019/12/27 11:57
 */
public class CollectionsUtil {

    public static boolean isNull(Collection collection){
        if (collection == null || collection.isEmpty() || collection.size() <= 0){
            return true;
        }
        return false;
    }

    public static boolean isNotNull(Collection collection){
        return !isNull(collection);
    }

}
