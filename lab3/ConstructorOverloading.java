public class ConstructorOverloading {
    int id;
    String name;
    int age;

    // NO-Argument constructor
    public ConstructorOverloading() {
        this.name = "Taarai zameen par";
    }

    // Constructor with one parameter
    public ConstructorOverloading(int id) {
        this.id = id;
    }

    // Constructor with two parameters
    public ConstructorOverloading(int id, String name) {
        this(id);
        this.name = name;
    }

    // Constructor with three parameters
    public ConstructorOverloading(int id, String name, int age) {
        this(id, name);
        this.age = age;
    }

    public void display() {
        System.out.println("ID: " + id + ", Name: " + name + ", Age: " + age);
    }

    public static void main(String[] args) {
        ConstructorOverloading obj1 = new ConstructorOverloading();
        ConstructorOverloading obj2 = new ConstructorOverloading(1);
        ConstructorOverloading obj3 = new ConstructorOverloading(2, "kali charan");
        ConstructorOverloading obj4 = new ConstructorOverloading(3, "kali charan", 25);

        obj1.display();
        obj2.display();
        obj3.display();
        obj4.display();
    }
}