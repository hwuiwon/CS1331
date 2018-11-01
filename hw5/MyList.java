/**
 * @author hkim944
 * @version 1.0
 * @param <E> generic type E
 */

public class MyList<E> implements List<E> {

    private E[] elements;
    private int size;

    /**
     * Creates MyList with capacity of 10 and size of 0
     */
    @SuppressWarnings("unchecked")
    public MyList() {
        elements = (E[]) new Object[INITIAL_CAPACITY];
        size = 0;
    }

    /**
     * Creates MyList with capacity of capacity
     * @param capacity Max capacity of MyList
     */
    @SuppressWarnings("unchecked")
    public MyList(int capacity) {
        super();
        elements = (E[]) new Object[capacity];
        size = 0;
    }

    /**
     * Adds an element to the last position of the list
     * @param e Element that gets added
     */
    @SuppressWarnings("unchecked")
    @Override
    public void add(E e) {
        if (e == null) {
            throw new IllegalArgumentException();
        } else {
            if (size == elements.length) {
                E[] backArray = (E[]) new Object[elements.length * 2];
                for (int i = 0; i < elements.length; i++) {
                    backArray[i] = elements[i];
                }
                elements = backArray;
            }
            elements[size++] = e;
        }
    }

    /**
     * Selects an element from the given index
     * @param index Index of object searching for
     * @return Value of an object in that index
     */
    @Override
    public E get(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        } else {
            return elements[index];
        }
    }

    /**
     * Replaces all instances of e with replaceWith
     * @param e Instance that will get replaced
     * @param replaceWith Exchanged value
     */
    @Override
    public void replace(E e, E replaceWith) {
        if (e == null || replaceWith == null) {
            throw new IllegalArgumentException();
        } else {
            for (int i = 0; i < size; i++) {
                if (elements[i].equals(e)) {
                    elements[i] = replaceWith;
                }
            }
        }
    }

    /**
     * Removes all instances of e
     * @param e Instance that will get removed
     * @return Total count of Object removed
     */
    @Override
    public int remove(E e) {
        if (e == null) {
            throw new IllegalArgumentException();
        } else {
            int count = 0;
            for (int i = 0; i < size; i++) {
                if (elements[i].equals(e)) {
                    elements[i] = null;
                    count++;
                }
            }
            size -= count;
            int tmp = 0;
            for (int i = 0; i < elements.length; i++) {
                if (elements[i] != null) {
                    elements[tmp++] = elements[i];
                }
            }
            while (elements.length > tmp) {
                elements[tmp++] = null;
            }
            return count;
        }
    }

    /**
     * Check how many instances of e are in the list
     * @param e Instance that will get searched through
     * @return Total count of Object same with e
     */
    @Override
    public int contains(E e) {
        if (e == null) {
            throw new IllegalArgumentException();
        } else {
            int count = 0;
            for (int i = 0; i < size; i++) {
                if (elements[i].equals(e)) {
                    count++;
                }
            }
            return count;
        }
    }

    /**
     * Checks if there are any objects in the list
     * @return Whether there is not an object in the List
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Make the entire List empty
     */
    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
    }

    /**
     * @return Size of an List
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns the array e that was input
     * @return array e that was input
     */
    @SuppressWarnings("unchecked")
    @Override
    public E[] toArray(E[] e) {
        for(int i = 0; i < e.length; i++) {
            if (i < size) {
                e[i] = elements[i];
            } else {
                e[i] = null;
            }
        }
        return e;
    }
}