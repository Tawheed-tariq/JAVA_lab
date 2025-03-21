public class RegularInner{
    public static void main(String[] args) {
        Outer o1 = new Outer();
        Outer.Inner i1 = o1.new Inner();

        i1.method2();
    }
}

class Outer{
    private void method1(){
        System.out.println("This is a private method of Outer class");
    }

    class Inner{
        void method2(){
            System.out.println("This is method of inner class calling private method of Outer class");
            method1();
        }
    }
}