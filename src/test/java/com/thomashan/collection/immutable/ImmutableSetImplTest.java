package com.thomashan.collection.immutable;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Iterator;

import static com.thomashan.collection.immutable.ImmutableSetImpl.of;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SuppressWarnings("PMD.TooManyMethods")
public class ImmutableSetImplTest {
    @Test
    public void testSize() {
        assertEquals(0, of().size());
    }

    @Test
    public void testIsEmpty_GivenEmptyList_ReturnsTrue() {
        assertTrue(of().isEmpty());
    }

    @Test
    public void testIsEmpty_GivenNonEmptyList_ReturnsFalse() {
        assertFalse(of(new Object()).isEmpty());
    }

    @Test
    public void testContains_GivenObjectIsInList_ReturnsTrue() {
        Object object = new Object();

        assertTrue(of(object).contains(object));
    }

    @Test
    public void testContains_GivenObjectIsNotInList_ReturnsFalse() {
        Object object = new Object();

        assertFalse(of(object).contains(new Object()));
    }

    @Test
    public void testIterator_ReturnsIterator() {
        assertTrue(of().iterator() instanceof Iterator);
    }

    @Test
    public void testToArray() {
        assertArrayEquals(new Object[0], of().toArray());
    }

    @Test
    public void testToArray_GivenIntegerArray_ReturnIntegerArray() {
        assertArrayEquals(new Integer[0], of().toArray(new Integer[0]));
    }

    @Test
    public void testAdd_ThrowsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> of().add(new Object()));
    }

    @Test
    public void testRemove_ThrowsUnsupportedOperationException() {
        Object object = new Object();

        assertThrows(UnsupportedOperationException.class, () -> of(object).remove(object));
    }

    @Test
    public void testContainsAll_GivenAllObjectAreInTheList_ReturnsTrue() {
        Object object = new Object();

        assertTrue(of(object).contains(object));
    }

    @Test
    public void testContainsAll_GivenAllObjectNotInTheList_ReturnsFalse() {
        assertFalse(of(new Object()).contains(new Object()));
    }

    @Test
    public void testAddAll_ThrowsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> of().addAll(Collections.singleton(new Object())));
    }

    @Test
    public void testRemoveAll_ThrowsUnsupportedOperationException() {
        Object object = new Object();

        assertThrows(UnsupportedOperationException.class, () -> of(object).removeAll(Collections.singleton(object)));
    }

    @Test
    public void testRetainAll_ThrowsUnsupportedOperationException() {
        Object object = new Object();

        assertThrows(UnsupportedOperationException.class, () -> of(object).retainAll(Collections.singleton(object)));
    }

    @Test
    public void testClear_ThrowsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> of().clear());
    }

    @Test
    public void testPlus_ReturnsImmutableSet() {
        assertTrue(of().plus(new Object()) instanceof ImmutableSet);
    }

    @Test
    public void testPlus_ReturnsNewInstanceOfImmutableSet() {
        ImmutableSet<Object> list = of();

        assertNotSame(list, list.plus(new Object()));
    }

    @Test
    public void testPlus_ReturnsNewInstanceOfImmutableSetWithNewElementAdded() {
        assertEquals(1, of().plus(new Object()).size());
    }

    @Test
    public void testMinus_ReturnsImmutableSet() {
        assertTrue(of().minus(new Object()) instanceof ImmutableSet);
    }

    @Test
    public void testMinus_ReturnsNewInstanceOfImmutableSet() {
        Object object = new Object();
        ImmutableSet<Object> list = of(object);

        assertNotSame(list, list.minus(object));
    }

    @Test
    public void testMinus_ReturnsNewInstanceOfImmutableSetWithNewElementAdded() {
        Object object = new Object();

        assertEquals(0, of().minus(object).size());
    }
}
