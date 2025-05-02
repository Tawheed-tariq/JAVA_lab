class Box<T extends Number>{
    private T val;

    public Box(T val){
        this.val = val;
    }

    public T getVal(){
        return val;
    }

    public void isSame(Box<? extends Number> obj){
        if (this.val.getClass() == obj.val.getClass()){
            System.out.println("Same type");
        } else {
            System.out.println("Different type");
        }
    }
}

class Main{
    public static void main(String[] args) {
        Box<Integer> intBox = new Box<>(10);
        Box<Integer> intBox2 = new Box<>(20);
        Box<Double> doubleBox = new Box<>(10.5);
        Box<Float> floatBox = new Box<>(10.5f);
        // Box<String> stringBox = new Box<>("Hello"); 

        System.out.println("Integer " + intBox.getVal());
        System.out.println("Double " + doubleBox.getVal());
        System.out.println("Float " + floatBox.getVal());

        intBox.isSame(doubleBox);
        intBox.isSame(intBox2);
    }
}