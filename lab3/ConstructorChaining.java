public class ConstructorChaining {
    int x;
    int y;

    public ConstructorChaining(int x) {
        this(x, 0); 
    }

    public ConstructorChaining(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "ConstructorChaining{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public static void main(String[] args) {
        ConstructorChaining obj2 = new ConstructorChaining(5);
        ConstructorChaining obj3 = new ConstructorChaining(5, 10);

        System.out.println(obj2);
        System.out.println(obj3);
    }
}