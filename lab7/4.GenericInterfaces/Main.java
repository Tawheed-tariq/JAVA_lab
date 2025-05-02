interface Box<T>{
    void set(T item);
    T get();
}

class SimpleBox<T, E> implements Box<T>{
    private T item;
    private E item2;

    @Override
    public void set(T item){
        this.item = item;
    }

    @Override
    public T get(){
        return item;
    }

    public void setSecond(E item){
        this.item2 = item;
    }

    public E getSecond(){
        return item2;
    }
}

public class Main{
    public static void main(String[] args){
        SimpleBox<String, Integer> box = new SimpleBox<>();
        box.set("Hello");
        box.setSecond(123);
        System.out.println("Item: " + box.get() + " Type is: " + box.get().getClass().getName());
        System.out.println("Second Item: " + box.getSecond() + " Type is: " + box.getSecond().getClass().getName());
    }
}