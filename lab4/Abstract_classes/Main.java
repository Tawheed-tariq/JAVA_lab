abstract class Animal {
    public abstract void sound();

    public void sleep() {
        System.out.println("Zzz");
    }
}

class Dog extends Animal {
    @Override
    public void sound() {
        System.out.println("Woof");
    }
}

final class Cat {
    public void sound() {
        System.out.println("Meow");
    }
}

// Uncommenting the following line will cause an error because a final class cannot be abstract
// abstract final class Bird {
//     public abstract void sound();
// }

public class Main {
    public static void main(String[] args) {
        Dog myDog = new Dog();
        myDog.sound();
        myDog.sleep();

        Cat myCat = new Cat();
        myCat.sound();
    }
}