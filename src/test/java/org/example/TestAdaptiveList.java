package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TestAdaptiveList {

    @Test
    void testAddRemoveSequence() {
        AdaptiveList<Integer> list = new AdaptiveList<>();

        for (int i = 1; i <= 7; i++) {
            list.add(i * 10);
            assertEquals(i, list.size());

            if (i == 1) {
                assertEquals("SINGLE", list.getInternalRepresentation());
            } else if (i >= 2 && i <= 5) {
                assertEquals("ARRAY_5", list.getInternalRepresentation());
            } else {
                assertEquals("ARRAY_LIST", list.getInternalRepresentation());
            }
        }

        assertEquals(7, list.size());
        assertEquals(10, list.get(0));
        assertEquals(70, list.get(6));

        for (int i = 7; i >= 1; i--) {
            int removed = list.remove();
            assertEquals(i * 10, removed);
            assertEquals(i - 1, list.size());

            if (i - 1 == 5) {
                assertEquals("ARRAY_5", list.getInternalRepresentation());
            } else if (i - 1 == 1) {
                assertEquals("SINGLE", list.getInternalRepresentation());
            } else if (i - 1 == 0) {
                assertEquals("NULL", list.getInternalRepresentation());
            }
        }

        assertTrue(list.isEmpty());
    }

    @Test
    void testGetSetOperations() {
        AdaptiveList<String> list = new AdaptiveList<>();

        for (int i = 0; i < 4; i++) {
            list.add("elem_" + i);
        }

        assertEquals(4, list.size());
        assertEquals("ARRAY_5", list.getInternalRepresentation());
        assertEquals("elem_0", list.get(0));
        assertEquals("elem_2", list.get(2));

        list.set(1, "MODIFIED");
        list.set(3, "CHANGED");

        assertEquals("MODIFIED", list.get(1));
        assertEquals("CHANGED", list.get(3));
        assertEquals("elem_0", list.get(0));
        assertEquals("elem_2", list.get(2));
    }

    @Test
    void testWithStrings() {
        AdaptiveList<String> list = new AdaptiveList<>();
        String[] words = {"Hello", "World", "Java", "Adaptive", "List", "Hi", "Apple"};

        for (int i = 0; i < words.length; i++) {
            list.add(words[i]);
            assertEquals(i + 1, list.size());
            assertEquals(words[i], list.get(i));

            if (i + 1 == 1) {
                assertEquals("SINGLE", list.getInternalRepresentation());
            } else if (i + 1 >= 2 && i + 1 <= 5) {
                assertEquals("ARRAY_5", list.getInternalRepresentation());
            } else {
                assertEquals("ARRAY_LIST", list.getInternalRepresentation());
            }
        }

        assertEquals(7, list.size());
        assertEquals("Hello", list.get(0));
        assertEquals("Apple", list.get(6));
    }
}