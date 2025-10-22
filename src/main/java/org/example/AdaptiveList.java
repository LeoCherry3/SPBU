package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Список с адаптивным внутренним представлением данных
 */
public class AdaptiveList<T> {
    private int size;
    private Object data;

    public AdaptiveList() {
        this.size = 0;
        this.data = null;
    }

    /**
     * Добавление элемента в конец списка
     *
     * @param element: элемент для добавления
     */
    public void add(T element) {
        int oldSize = size;
        size++;
        switchRepresentationIfNeeded(oldSize, size);
        addToCurrentRepresentation(element);
    }

    /**
     * Удаление элемента из конца списка
     *
     * @return удалённый элемент
     */
    public T remove() {  //TODO: rename to pop
        if (size == 0) {
            throw new IllegalStateException("Cannot remove from empty list");
        }
        int oldSize = size;
        T removedElement = getElementAt(size - 1);
        size--;

        switchRepresentationIfNeeded(oldSize, size);

        if (size >= 2 && size <= 4 && data instanceof Object[]) {
            ((Object[]) data)[oldSize - 1] = null;
        }
        return removedElement;
    }

    /**
     * Метод для получения элемента по индексу
     *
     * @return элемент
     */
    public T get(int index) {
        checkIndex(index);
        return getElementAt(index);
    }


    /**
     * Установка элемента по индексу
     *
     * @param index индекс изменяемого элемента
     * @param element новый элемент
     */
    @SuppressWarnings("unchecked")
    public void set(int index, T element) {
        checkIndex(index);

        if (size == 1) {
            data = element;
        } else if (size >= 2 && size <= 5) {
            ((Object[]) data)[index] = element;
        } else {
            ((List<T>) data).set(index, element);
        }
    }

    /**
     * @return текущий размер списка
     */
    public int size() {
        return size;
    }

    /**
     * @return boolean
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Возвращает тип внутреннего представления
     */
    public String getInternalRepresentation() {
        if (size == 0) return "NULL";
        if (size == 1) return "SINGLE";
        if (size >= 2 && size <= 5) return "ARRAY_5";
        return "ARRAY_LIST";
    }

    /**
     * Смена внутреннего типа при удалении/добавлении элемента на границе 0-1, 1-2, 5-6
     *
     * @param oldSize старый размер списка
     * @param newSize новый размер списка
     */
    @SuppressWarnings("unchecked")
    private void switchRepresentationIfNeeded(int oldSize, int newSize) {
        if (newSize == 0 && oldSize == 1) {
            // single -> null
            data = null;
        } else if (newSize == 1 && oldSize == 2) {
            // array -> single
            data = ((Object[]) data)[0];
        } else if (newSize == 2 && oldSize == 1) {
            // single -> array
            T singleElement = (T) data;  // unchecked but we know its type
            Object[] array = new Object[5];
            array[0] = singleElement;
            data = array;
        } else if (newSize == 6 && oldSize == 5) {
            // array -> ArrayList
            Object[] array = (Object[]) data;
            List<T> list = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                list.add((T) array[i]);  // unchecked
            }
            data = list;
        } else if (newSize == 5 && oldSize == 6) {
            // ArrayList -> array
            List<T> list = (List<T>) data;  // unchecked
            Object[] array = new Object[5];
            for (int i = 0; i < 5; i++) {
                array[i] = list.get(i);
            }
            data = array;
        }
    }

    /**
     * Добавление элемента в соответствующую внутреннюю структуру
     *
     * @param element новый элемент
     */
    @SuppressWarnings("unchecked")
    private void addToCurrentRepresentation(T element) {
        if (size == 1) {
            data = element;
        } else if (size >= 2 && size <= 5) {
            ((Object[]) data)[size - 1] = element;
        } else if (size >= 6) {
            ((List<T>) data).add(element);
        }
    }

    /**
     * Получение элемента по индексу
     *
     * @param index индекс
     * @return элемент с данным индексом в списке
     */
    @SuppressWarnings("unchecked")
    private T getElementAt(int index) {
        if (size == 1) {
            return (T) data;  // unchecked cast
        } else if (size >= 2 && size <= 5) {
            return (T) ((Object[]) data)[index];
        } else {
            return ((List<T>) data).get(index);
        }
    }

    /**
     * Проверка, что данный индекс существует в списке
     *
     * @param index индекс
     */
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(
                    "Index: " + index + ", Size: " + size
            );
        }
    }

    @Override
    public String toString() {
        if (size == 0) return "AdaptiveList[]";

        StringBuilder sb = new StringBuilder("AdaptiveList[");
        for (int i = 0; i < size; i++) {
            if (i > 0) sb.append(", ");
            sb.append(get(i));
        }
        sb.append("]");
        return sb.toString();
    }
}