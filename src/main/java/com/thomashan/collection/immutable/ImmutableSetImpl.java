package com.thomashan.collection.immutable;

import com.google.common.annotations.VisibleForTesting;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* default */
@SuppressWarnings("PMD.TooManyMethods")
final class ImmutableSetImpl<E> implements ImmutableSet<E> {
    private final transient Set<E> set;

    private ImmutableSetImpl(Set<E> set) {
        this.set = set;
    }

    @VisibleForTesting
    static <E> ImmutableSetImpl<E> of(E... e) {
        if (0 == e.length) {
            return new ImmutableSetImpl<>(Collections.unmodifiableSet(Collections.emptySet()));
        }

        return new ImmutableSetImpl(Collections.unmodifiableSet(new HashSet<>(Arrays.asList(e))));
    }

    @VisibleForTesting
    static <E> ImmutableSetImpl<E> of(Set<E> set) {
        if (set instanceof ImmutableSet) {
            return new ImmutableSetImpl<>(set);
        }

        return new ImmutableSetImpl<>(Collections.unmodifiableSet(set));
    }

    @Override
    public ImmutableSet<E> plus(E e) {
        Set<E> newSet = new HashSet<>(set);
        newSet.add(e);

        return of(newSet);
    }

    @Override
    public ImmutableSet<E> minus(E e) {
        Set<E> newSet = new HashSet<>(set);
        newSet.remove(e);

        return of(newSet);
    }

    @Override
    public int size() {
        return set.size();
    }

    @Override
    public boolean isEmpty() {
        return set.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return set.contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        return set.iterator();
    }

    @Override
    public Object[] toArray() {
        return set.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return set.toArray(a);
    }

    @Override
    public boolean add(E e) {
        return set.add(e);
    }

    @Override
    public boolean remove(Object o) {
        return set.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return set.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return set.addAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return set.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return set.retainAll(c);
    }

    @Override
    public void clear() {
        set.clear();
    }

    @Override
    public boolean equals(Object obj) {
        return set.equals(obj);
    }

    @Override
    public int hashCode() {
        return set.hashCode();
    }
}
