package com.lgz.crazy.common.utils.collection;

import java.util.Collection;

/**
 * Created by lgz on 2019/3/15.
 */
public class CollectionUtil {

    public static boolean isEmpty(Collection collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean isNotEmpty(Collection collection) {
        return !isEmpty(collection);
    }
    public static boolean isNull(Collection collection) {
        return collection == null;
    }

    public static boolean isNotNull(Collection collection) {
        return !isNull(collection);
    }


}
