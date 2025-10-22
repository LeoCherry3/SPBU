package org.example;

/**
 * Демонстрация работы AdaptiveList
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("AdaptiveList Demonstartion\n");

        // Test add and remove elements
        testAddRemoveSequence();

        System.out.println("\n" + "=".repeat(50) + "\n");

        // Test getter/setter
        testGetSetOperations();

        System.out.println("\n" + "=".repeat(50) + "\n");

        // Test with strings
        testWithStrings();
    }

    private static void testAddRemoveSequence() {
        System.out.println("Test 1: Addition and removal");

        AdaptiveList<Integer> list = new AdaptiveList<>();

        for (int i = 1; i <= 7; i++) {
            list.add(i * 10);
            System.out.printf("After appends %2d: %-30s [%s]%n",
                    i * 10, list, list.getInternalRepresentation());
        }

        System.out.println(" Removing... ");

        while (!list.isEmpty()) {
            int removed = list.remove();
            System.out.printf("After deletion  %2d: %-30s [%s]%n",
                    removed, list, list.getInternalRepresentation());
        }
    }

    private static void testGetSetOperations() {
        System.out.println("Test 2: getters and setters");

        AdaptiveList<String> list = new AdaptiveList<>();

        // Fill with 1..4
        for (int i = 0; i < 4; i++) {
            list.add("elem_" + i);
        }

        System.out.println("Original list: " + list);
        System.out.println("Current list type: " + list.getInternalRepresentation());

        System.out.println("get(0) = " + list.get(0));
        System.out.println("get(2) = " + list.get(2));

        list.set(1, "MODIFIED");
        list.set(3, "CHANGED");
        System.out.println("After set(1, 'MODIFIED'), set(3, 'CHANGED'):");
        System.out.println("List: " + list);
    }

    private static void testWithStrings() {
        System.out.println("Test 3: strings");

        AdaptiveList<String> list = new AdaptiveList<>();

        String[] words = {"Hello", "World", "Java", "Adaptive", "List", "Hi", "Apple"};

        for (String word : words) {
            list.add(word);
            System.out.printf("Added '%-7s': %-40s [%s]%n",
                    word, list, list.getInternalRepresentation());
        }
    }
}