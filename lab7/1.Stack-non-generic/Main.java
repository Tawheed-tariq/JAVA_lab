class Stack{
    private int maxSize;
    private int top;
    private Object[] stackArray;

    Stack(int size){
        this.maxSize = size;
        this.stackArray = new Object[maxSize];
        this.top = -1;
    }

    public void push(Object value){
        if (isFull()) {
            System.out.println("Stack is full");
        } else {
            stackArray[++top] = value;
        }
    }

    public Object pop(){
        if (isEmpty()) {
            System.out.println("Stack is empty");
            return -1; 
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

    public void divideAll(){
        for (int i = 0; i <= top; i++) {
            
            stackArray[i] = (Integer) stackArray[i] / 2;
        }
    }
}

class Main{
    public static void main(String[] args) {
        Stack stack = new Stack(5);
        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.push("sonia");
        // stack.divideAll();
        System.out.println("Pop " + stack.pop());
        System.out.println("Pop " + stack.pop());
        System.out.println("Pop " + stack.pop());
        System.out.println("Pop " + stack.pop());

        
    }
}