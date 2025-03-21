class Outer{
    void method1(){
        class Inner{
            void method2(){
                System.out.println("This is method of inner class made inside method of Outer class");
            }
        }

        Inner obj = new Inner();
        obj.method2();
    }
}

public class MethodLocal{
    public static void main(String[] args) {
        Outer o1 = new Outer();
        o1.method1();
    }
}