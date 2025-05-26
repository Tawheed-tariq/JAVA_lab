public class dequeueInterface<T> {
    private Object[] elements;
    private int front;
    private int rear;
    private int size;
    private int capacity;

    public dequeueInterface(int capacity) {
        this.capacity = capacity;
        elements = new Object[capacity];
        front = -1;
        rear = -1;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public void addFirst(T e) {
        if (isFull()) {
            System.out.println("Deque is full");
            return;
        }
        if (isEmpty()) {
            front = rear = 0;
        } else {
            front = (front - 1 + capacity) % capacity;
        }
        elements[front] = e;
        size++;
    }

    public void addLast(T e) {
        if (isFull()) {
            System.out.println("Deque is full");
            return;
        }
        if (isEmpty()) {
            front = rear = 0;
        } else {
            rear = (rear + 1) % capacity;
        }
        elements[rear] = e;
        size++;
    }

    public T removeFirst() {
        if (isEmpty()) {
            System.out.println("Deque is empty");
            return null;
        }
        T value = (T) elements[front];
        elements[front] = null;
        if (front == rear) { // only one element
            front = rear = -1;
        } else {
            front = (front + 1) % capacity;
        }
        size--;
        return value;
    }

    public T removeLast() {
        if (isEmpty()) {
            System.out.println("Deque is empty");
            return null;
        }
        T value = (T) elements[rear];
        elements[rear] = null;
        if (front == rear) { // only one element
            front = rear = -1;
        } else {
            rear = (rear - 1 + capacity) % capacity;
        }
        size--;
        return value;
    }

    public T peekFirst() {
        if (isEmpty()) {
            System.out.println("Deque is empty");
            return null;
        }
        return (T) elements[front];
    }

    public T peekLast() {
        if (isEmpty()) {
            System.out.println("Deque is empty");
            return null;
        }
        return (T) elements[rear];
    }

    public static void main(String[] args) {
        dequeueInterface<String> deque = new dequeueInterface<>(5);

        deque.addLast("one");
        deque.addLast("two");
        deque.addFirst("zero");

        System.out.println("First: " + deque.peekFirst()); // zero
        System.out.println("Last: " + deque.peekLast());   // two

        System.out.println("Removed First: " + deque.removeFirst()); // zero
        System.out.println("Removed Last: " + deque.removeLast());   // two

        System.out.println("Now First: " + deque.peekFirst()); // one
    }
}