package edu.gatech.cs1331.hw7;

import javafx.collections.ModifiableObservableListBase;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author hkim944
 * @version 1.0
 * @param <E> Type parameter
 */

public class LinkedQueue<E> extends ModifiableObservableListBase
        implements Iterable, SimpleQueue<E> {

    private int queueSize;
    private LinkedQueueNode<E> first, last;

    /**
     * Adds an element to the queue
     * @param element Element that will get added to the queue
     */
    @Override
    public void enqueue(E element) {
        LinkedQueueNode<E> node = new LinkedQueueNode<>(element);
        if (isEmpty()) {
            first = node;
        } else {
            last.setNext(node);
        }
        last = node;
        queueSize++;
    }

    /**
     * Removes the least recently added element when queue is non-empty
     * @return element that is removed
     */
    @Override
    public E dequeue() {
        if (isEmpty()) {
            return null;
        } else {
            E element = first.getData();
            first = first.getNext();
            queueSize--;
            if (isEmpty()) {
                last = null;
            }
            return element;
        }
    }

    /**
     * @return element from the queue at a specific index
     * @param index Index of an element
     */
    @Override
    public E get(int index) {
        if (index > queueSize - 1 || index < 0) {
            throw new IndexOutOfBoundsException();
        } else {
            Iterator<E> it = iterator();
            int count = 0;
            while (it.hasNext()) {
                E element = it.next();
                if (count == index) {
                    return element;
                }
                count++;
            }
            return null;
        }
    }

    /**
     * @return size of queue
     */
    @Override
    public int size() {
        return queueSize;
    }

    /**
     * Adds an element to the queue at a specific index
     * @param index Index of where element will get added
     * @param element Element that is getting added
     */
    @Override
    protected void doAdd(int index, Object element) {
        if (index > queueSize || index < 0) {
            throw new IndexOutOfBoundsException();
        } else if (index == 0) {
            enqueue((E) element);
        } else {
            LinkedQueueNode node = first;
            for (int i = 0; i < index - 1; i++) {
                node = node.getNext();
            }
            LinkedQueueNode tmp = new LinkedQueueNode<>(element);
            tmp.setNext(node.getNext());
            node.setNext(tmp);
            queueSize++;
        }
    }

    /**
     * Throws an UnsupportedOperationException
     * @param index Index of where element will get replaced
     * @param element Element that will replace
     */
    @Override
    protected Object doSet(int index, Object element) {
        throw new UnsupportedOperationException();
    }

    /**
     * Removes an element from the queue at a specific index
     * @return Element that is removed
     * @param index Index of an element
     */
    @Override
    protected E doRemove(int index) {
        E tmp2;
        if (index > queueSize - 1 || index < 0) {
            throw new IndexOutOfBoundsException();
        } else if (index == 0) {
            tmp2 = dequeue();
        } else {
            LinkedQueueNode<E> node = first;
            LinkedQueueNode<E> tmp;
            for (int i = 0; i < index - 1; i++) {
                node = node.getNext();
            }
            tmp2 = (E) node.getNext();
            tmp = node.getNext().getNext();
            node.setNext(tmp);
            queueSize--;
        }
        return tmp2;
    }

    /**
     * Empties queue and sets size to 0
     */
    public void clear() {
        first = null;
        last = null;
        queueSize = 0;
    }

    /**
     * @return true when queue is empty, false otherwise
     */
    public boolean isEmpty() {
        return queueSize == 0;
    }

    /**
     * @return Iterator
     */
    public Iterator<E> iterator() {
        return new QueueIterator();
    }

    private class QueueIterator implements Iterator<E> {
        private LinkedQueueNode<E> tmp = first;

        @Override
        public boolean hasNext() {
            return tmp != null;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E element = tmp.getData();
            tmp = tmp.getNext();
            return element;
        }
    }
}