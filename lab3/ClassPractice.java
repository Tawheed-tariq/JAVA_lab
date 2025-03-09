public class ClassPractice {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        AnotherClass obj = new AnotherClass();
        obj.anotherMethod();
    }
}

class AnotherClass {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        ClassPractice.main(args);
    }

    public void anotherMethod() {
        System.out.println("Hello, this is my method in AnotherClass!");
    }
}