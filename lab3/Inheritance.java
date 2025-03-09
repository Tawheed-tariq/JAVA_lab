
class Animal {
    String category;
    Animal() {
        System.out.println("Animal is created");
    }
    Animal(String category){
        this.category = category;
        System.out.println("Category set = " + this.category);
    }
    void eat() {
        System.out.println("khana doo mereko");
    }
}



// Hierarchical Inheritance
class Cat extends Animal {
    Cat(){
        super("cat");
    }
    void meow() {
        System.out.println("meow meow meow meow");
    }
}

class Dog extends Animal {
    String name;
    Dog(){

    }
    Dog(String name){
        this.name = name;
        System.out.println("the name of dog is " + this.name);
    }

    public void bark(){
        System.out.println("bow bow bow");
    }
}

// Multilevel Inheritance
class Puppy extends Dog {
    Puppy(String name) {
        super.name = name;
        System.out.println("The name of puppy is " + super.name);
    }
    void weep() {
        System.out.println("weep weep");
    }
}

public class Inheritance {
    public static void main(String[] args) {
        Dog dog = new Dog("kaalu");
        dog.eat();
        dog.bark();

        Puppy puppy = new Puppy("chotu");
        puppy.eat();
        puppy.bark();
        puppy.weep();

        Cat cat = new Cat();
        cat.eat();
        cat.meow();
    }
}