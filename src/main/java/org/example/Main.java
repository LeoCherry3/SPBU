package org.example;

/**
 * Демонстрация работы AdaptiveList
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("AdaptiveList Demonstartion\n");

        AdaptiveList<Integer> list = new AdaptiveList<>();

        System.out.println("Adding elements 1-6:");
        for (int i = 1; i <= 6; i++) {
            list.add(i);
            System.out.printf("Size: %d, Type: %s, List: %s%n",
                    list.size(), list.getInternalRepresentation(), list);
        }

        System.out.println("\nRemoving elements:");
        while (!list.isEmpty()) {
            int removed = list.remove();
            System.out.printf("Removed: %d, Size: %d, Type: %s, List: %s%n",
                    removed, list.size(), list.getInternalRepresentation(), list);
        }
    }

}