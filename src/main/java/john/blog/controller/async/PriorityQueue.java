package john.blog.controller.async;

import john.blog.utils.MaxHeap;
import john.blog.utils.Queue;
import org.springframework.stereotype.Component;

@Component
public class PriorityQueue<E extends Comparable<E>> implements Queue<E> {

    private MaxHeap<E> heap = new MaxHeap<>();

    @Override
    public synchronized boolean isEmpty() {
        return heap.isEmpty();
    }

    @Override
    public synchronized int size() {
        return heap.size();
    }

    @Override
    public synchronized void enqueue(E e) {
        // Open a new thread when adding a new element into the priority queue
        new Thread(() -> {
            heap.add(e);
        }).start();
    }

    @Override
    public synchronized E dequeue() {
        return heap.pop();
    }

    @Override
    public synchronized E getFront() {
        return heap.peek();
    }
}
