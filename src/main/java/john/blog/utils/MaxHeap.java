package john.blog.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.NoSuchElementException;

public class MaxHeap<E extends Comparable<E>> {

    private ArrayList<E> heap; // this class implements a max heap, which has the largest value at the top

    public MaxHeap() {
        heap = new ArrayList<>();
    }

    public void add(E e) {
        heap.add(e);
        siftUp(heap.size() - 1);
    }

    public E peek() {
        return isEmpty() ? null : heap.get(0);
    }

    public E pop() {
        if (isEmpty()) {
            return null;
        }

        // Get the largest element
        E deletedValue = heap.get(0);

        // Put the last element in the heap to the position of the deleted value
        heap.set(0, heap.get(heap.size() - 1));
        // Remove the last element (reduce the heap's size by 1)
        heap.remove(heap.size() - 1);

        siftDown(0);

        return deletedValue;
    }

    private void siftUp(int index) {
        E newValue = heap.get(index);

        while (index > 0 && newValue.compareTo(heap.get(parentIndex(index))) > 0) {
            heap.set(index, heap.get(parentIndex(index)));
            index = parentIndex(index);
        }

        heap.set(index, newValue);
    }

    private void siftDown(int index) {
        int lastHeapIndex = heap.size() - 1;
        int childToSwap;

        while (index < lastHeapIndex) {
            int leftChildIndex = leftChildIndex(index);
            int rightChildIndex = rightChildIndex(index);

            if (leftChildIndex <= lastHeapIndex) {
                if (rightChildIndex <= lastHeapIndex) {
                    childToSwap = heap.get(leftChildIndex).compareTo(heap.get(rightChildIndex)) > 0 ? leftChildIndex : rightChildIndex;
                } else {
                    childToSwap = leftChildIndex;
                }

                if (heap.get(index).compareTo(heap.get(childToSwap)) < 0) {
                    Collections.swap(heap, index, childToSwap);
                    index = childToSwap;
                } else {
                    break;
                }

            } else {
                break;
            }
        }
    }

    public int parentIndex(int index) {
        return (index - 1) / 2;
    }

    public int leftChildIndex(int index) {
        return 2 * index + 1;
    }

    public int rightChildIndex(int index) {
        return 2 * index + 2;
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    public int size() {
        return heap.size();
    }
}
