package com.thomashan.collection.immutable;

import java.util.Set;

public interface ImmutableSet<E> extends Set<E> {
    static <E> ImmutableSet<E> of(E... e) {
        return ImmutableSetImpl.of(e);
    }

    static <E> ImmutableSet<E> of(Set<E> set) {
        return ImmutableSetImpl.of(set);
    }

    /**
     * Use plus instead of add to get a new instance of ImmutableSet.
     * This can be chained to return the final result e.g set.plus(e1).plus(e2)
     *
     * @param e element to add
     * @return
     */
    ImmutableSet<E> plus(E e);

    /**
     * Use minus instead of remove to get a new instance of ImmutableSet.
     * This can be chained to return the final result e.g set.remove(e1).remove(e2)
     *
     * @param e element to remove
     * @return
     */
    ImmutableSet<E> minus(E e);
}
