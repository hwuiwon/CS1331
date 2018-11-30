package edu.gatech.cs1331.hw7;

import javafx.collections.ModifiableObservableListBase;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author hkim944
 * @version 1.0
 * @param <E> Type parameter
 */

public class LinkedQueue<E> extends ModifiableObservableListBase<E>
        implements Iterable<E>, SimpleQueue<E> {

    private int queueSize;
    private LinkedQueueNode<E> first, last;

    /**
     * Adds an element to the queue
     * @param element Element that will get added to the queue
     */
    @Override
    public void enqueue(E element) {
        if (element != null) {
            super.add(queueSize, element);
        }
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
            return super.remove(0);
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
            Iterator it = iterator();
            int count = 0;
            while (it.hasNext()) {
                E element = (E) it.next();
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
        if (element != null) {
            LinkedQueueNode tmp = new LinkedQueueNode(element);
            if (index == 0) {
                tmp.setNext(first);
                this.first = tmp;
            } else if (index > queueSize || index < 0) {
                throw new IndexOutOfBoundsException();
            } else {
                LinkedQueueNode node = first;
                for (int i = 0; i < index - 1; i++) {
                    node = node.getNext();
                }
                tmp.setNext(node.getNext());
                node.setNext(tmp);
            }
            queueSize++;
        }
    }

    /**
     * Throws an UnsupportedOperationException
     * @param index Index of where element will get replaced
     * @param element Element that will replace
     */
    @Override
    protected E doSet(int index, Object element) {
        throw new UnsupportedOperationException();
    }

    /**
     * Removes an element from the queue at a specific index
     * @return Element that is removed
     * @param index Index of an element
     */
    @Override
    protected E doRemove(int index) {
        LinkedQueueNode<E> tmp = this.first;
        LinkedQueueNode<E> tmp2;
        if (index > queueSize - 1 || index < 0) {
            throw new IndexOutOfBoundsException();
        } else if (index == 0) {
            this.first = this.first.getNext();
            queueSize--;
            return tmp.getData();
        } else {
            for (int i = 0; i < index - 1; i++) {
                tmp = tmp.getNext();
            }
            tmp2 = tmp.getNext();
            tmp.setNext(tmp2.getNext());
            queueSize--;
        }
        return tmp2.getData();
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