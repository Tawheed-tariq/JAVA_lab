class Stack<T>{
    private int maxSize;
    private int top;
    private T[] stackArray;
    
    Stack(int size){
        this.maxSize = size;
        this.stackArray = (T[]) new Object[maxSize]; 
        this.top = -1;
    }

    public void push(T value){
        if (isFull()) {
            System.out.println("Stack is full");
        } else {
            stackArray[++top] = value;
        }
    }

    public T pop(){
        if (isEmpty()) {
            System.out.println("Stack is empty");
            return null; 
        } else {
            return stackArray[top--];
        }
    }

    public boolean isEmpty(){
        return (top == -1);
    }

    public boolean isFull(){
        return (top == maxSize - 1);
    }
}

class Main{
    public static void main(String[] args){
        Stack<Integer> stack = new Stack<>(5);
        stack.push(10);
        stack.push(20);
        stack.push(30);
        System.out.println("Pop " + stack.pop());
        System.out.println("pop "+ stack.pop());
        System.out.println("pop "+ stack.pop());
        System.out.println("pop "+ stack.pop());
    }
}