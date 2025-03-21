class Outer{
    static int x; //here x is created in memory before object of outer is created 
    int y;

    static class Inner{
        void m1(Outer o){
            System.out.println("value of x is "+x);
            System.out.println("value of y is " + o.y);
        }

        void m3(){
            System.out.println("value of x is "+x);
            // System.out.println("value of y is " + y); //this gives error 
        }
    }

    class AnotherInner{
        void m2(){
            System.out.println("Value of x " + x);
            System.out.println("Value of y " + y);
        }
    }
}

public class StaticInner{
    public static void main(String[] args) {
        var o1 = new Outer();
        var i2 = o1.new AnotherInner();
        var i1 = new Outer.Inner();

        i1.m1(o1);
        Outer.x = 15;
        i1.m3();

        i2.m2();
    }
}