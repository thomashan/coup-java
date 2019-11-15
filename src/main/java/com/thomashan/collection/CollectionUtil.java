package com.thomashan.collection;

import java.util.ArrayList;
import java.util.List;

public final class CollectionUtil {
    private CollectionUtil() {
        // prevent instantiation
    }

    public static <E> List<E> add(List<E> list, E element) {
        List<E> newList = new ArrayList<>(list);
        newList.add(element);

        return newList;
    }
}
