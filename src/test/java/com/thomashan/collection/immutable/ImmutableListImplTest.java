package com.thomashan.collection.immutable;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Iterator;
import java.util.ListIterator;

import static com.thomashan.collection.immutable.ImmutableListImpl.of;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SuppressWarnings("PMD.TooManyMethods")
public class ImmutableListImplTest {
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

        assertTrue(of(object).containsAll(Collections.singleton(object)));
    }

    @Test
    public void testContainsAll_GivenAllObjectNotInTheList_ReturnsFalse() {
        assertFalse(of(new Object()).containsAll(Collections.singleton(new Object())));
    }

    @Test
    public void testAddAll_ThrowsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> of().addAll(Collections.singleton(new Object())));
    }

    @Test
    public void testAddAllWithIndex_ThrowsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> of().addAll(0, Collections.singleton(new Object())));
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
    public void testGet_GivenObjectIsInTheList_ReturnsObject() {
        Object object = new Object();

        assertEquals(object, of(object).get(0));
    }

    @Test
    public void testGet_GivenObjectIsNotInTheList_ThrowsIndexOutOfBoundsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> of().get(0));
    }

    @Test
    public void testAddWithIndex_ThrowsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> of().add(0, new Object()));
    }

    @Test
    public void testRemoveWithIndex_ThrowsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> of(new Object()).remove(0));
    }

    @Test
    public void testIndexOf_GivenObjectIsInTheList_ReturnsFirstIndex() {
        Object object = new Object();

        assertEquals(0, of(object, object).indexOf(object));
    }

    @Test
    public void testIndexOf_GivenObjectIsNotInTheList_ReturnsNegativeOne() {
        assertEquals(-1, of().indexOf(new Object()));
    }

    @Test
    public void testLastIndexOf_GivenObjectIsInTheList_ReturnsLastIndex() {
        Object object = new Object();

        assertEquals(1, of(object, object).lastIndexOf(object));
    }

    @Test
    public void testLastIndexOf_GivenObjectIsNotInTheList_ReturnsNegativeOne() {
        assertEquals(-1, of().lastIndexOf(new Object()));
    }

    @Test
    public void testListIterator_ReturnsListIterator() {
        assertTrue(of().listIterator() instanceof ListIterator);
    }

    @Test
    public void testListIteratorWithIndex_ReturnsListIterator() {
        assertTrue(of().listIterator(0) instanceof ListIterator);
    }

    @Test
    public void testListIteratorWithIndex_ReturnsListIteratorWithCorrectPosition() {
        Object object = new Object();

        assertEquals(object, of(new Object(), object).listIterator(1).next());
    }

    @Test
    public void testSubList_ReturnsImmutableList() {
        Object object = new Object();

        assertTrue(of(object, object).subList(0, 0) instanceof ImmutableList);
    }

    @Test
    public void testSubList_ReturnImmutableListWithRightElements() {
        Object object = new Object();

        assertEquals(of(object), of(object, object).subList(0, 1));
    }

    @Test
    public void testPlus_ReturnsImmutableList() {
        assertTrue(of().plus(new Object()) instanceof ImmutableList);
    }

    @Test
    public void testPlus_ReturnsNewInstanceOfImmutableList() {
        ImmutableList<Object> list = of();

        assertNotSame(list, list.plus(new Object()));
    }

    @Test
    public void testPlus_ReturnsNewInstanceOfImmutableListWithNewElementAdded() {
        assertEquals(1, of().plus(new Object()).size());
    }

    @Test
    public void testMinus_ReturnsImmutableList() {
        assertTrue(of().minus(new Object()) instanceof ImmutableList);
    }

    @Test
    public void testMinus_ReturnsNewInstanceOfImmutableList() {
        Object object = new Object();
        ImmutableList<Object> list = of(object);

        assertNotSame(list, list.minus(object));
    }

    @Test
    public void testMinus_ReturnsNewInstanceOfImmutableListWithNewElementAdded() {
        Object object = new Object();

        assertEquals(0, of().minus(object).size());
    }

    @Test
    public void testAddOrSet_ReturnsImmutableList() {
        assertTrue(of().addOrSet(0, new Object()) instanceof ImmutableList);
    }

    @Test
    public void testAddOrSet_ReturnsNewInstanceOfImmutableList() {
        Object object = new Object();
        ImmutableList<Object> list = of(object);

        assertNotSame(list, list.addOrSet(0, object));
    }

    @Test
    public void testAddOrSet_GivenObjectDoesNotExistInIndex_ReturnsNewInstanceOfImmutableListWithNewElementAdded() {
        assertEquals(1, of().addOrSet(0, new Object()).size());
    }

    @Test
    public void testAddOrSet_GivenObjectExistsInIndex_ReturnsNewInstanceOfImmutableListWitOldElementReplacedByNewElement() {
        Object object = new Object();
        ImmutableList<Object> list = of(new Object());

        assertEquals(of(object), list.addOrSet(0, object));
    }

    @Test
    public void testOf_ListParameter() {
        ImmutableListImpl list1 = ImmutableListImpl.of(1, 2);
        ImmutableListImpl list2 = ImmutableListImpl.of(list1);

        assertFalse(list2.getBackingList() instanceof ImmutableList);
    }
}
