package john.blog.utils;

public interface Queue<E> {

    boolean isEmpty();

    int size();

    void enqueue(E e);

    E dequeue();

    E getFront();

}
