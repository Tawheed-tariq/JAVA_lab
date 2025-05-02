class NonGeneric{

    public <T> void display(T obj){
        System.out.println("Object: " + obj + " Type is: " + obj.getClass().getName());
    }

    public <T> void printArray(T[] array){
        for(T element : array){
            System.out.println("Element: " + element);
        }
        System.out.println();
    }

    public <T, E> NonGeneric(T obj, E obj2){
        System.out.println("Object 1: " + obj + " Type is: " + obj.getClass().getName());
        System.out.println("Object 2: " + obj2 + " Type is: " + obj2.getClass().getName());
    }

}

class Main{
    public static void main(String[] args){
        NonGeneric obj = new NonGeneric("Sonia", 123);
        obj.display("Except_me!");
        obj.display(123);
        obj.display(45.67);

        String[] stringArray = {"Kaalu", "baingan", "hii"};
        Integer[] intArray = {1, 2, 3, 4, 5};

        obj.printArray(stringArray);
        obj.printArray(intArray);
    }
}