class Interfaces{
    public static void main(String []args){
        Dog dog = new Dog();
        dog.eat();
        dog.sleep();

        Car car = new Car();
        car.start();
        car.stop();
        car.eat();
        car.sleep();
    }
}

// Interface
interface Animal{
    void eat();
    void sleep();
}

interface Vehicle extends Animal{
    void start();
    void stop();
}


class Dog implements Animal{
    public void eat(){
        System.out.println("Dog is eating");
    }
    public void sleep(){
        System.out.println("Dog is sleeping");
    }
}


class Car implements Vehicle{
    public void eat(){
        System.out.println("Car is eating");
    }
    public void sleep(){
        System.out.println("Car is sleeping");
    }
    public void start(){
        System.out.println("Car is starting");
    }
    public void stop(){
        System.out.println("Car is stopping");
    }
}