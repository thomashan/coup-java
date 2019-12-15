package com.thomashan.collection.immutable;

import java.util.Collection;
import java.util.List;


public interface ImmutableList<E> extends List<E> {
    static <E> ImmutableList<E> of(E... e) {
        return ImmutableListImpl.of(e);
    }

    static <E> ImmutableList<E> of(List<E> list) {
        return ImmutableListImpl.of(list);
    }

    static <E> ImmutableList<E> of(Collection<E> colletion) {
        return ImmutableListImpl.of(colletion);
    }

    /**
     * Use plus instead of add to get a new instance of ImmutableList.
     * This can be chained to return the final result e.g list.plus(e1).plus(e2)
     *
     * @param e element to add
     * @return
     */
    ImmutableList<E> plus(E e);

    /**
     * Use minus instead of remove to get a new instance of ImmutableList.
     * This can be chained to return the final result e.g list.remove(e1).remove(e2)
     *
     * @param e element to remove
     * @return
     */
    ImmutableList<E> minus(E e);

    ImmutableList<E> addOrSet(int index, E e);
}
