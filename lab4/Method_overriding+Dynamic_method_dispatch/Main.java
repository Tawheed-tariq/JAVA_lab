public class Main{
    public static void main(String[] args) {
        Parent p = new Parent("name of parent");
        p.show();
        p.print_name();

        Child c = new Child("child name");
        c.display();
        c.show();
        c.print_name();

        Parent p1 = new Child("child name");
        // p1.display(); shows error because not inherited hence not overriden 
        p1.show();
        p1.print_name();
        p1.only_parent();
        // p1.only_child(); shows error because only present in child
    }
}

class Parent{
    String name;
    Parent(String name){
        this.name = name;
    }
    private void display(){
        System.out.println("Parent class display method");
    }

    void show(){
        System.out.println("Parent class show method");
    }

    void print_name(){
        System.out.println(name);
    }

    void only_parent(){
        System.out.println("This is of parent only");
    }
}

class  Child extends Parent{

    Child(String name){
        super(name);
    }

    void display(){
        System.out.println("Child class display method");
    }
    
    @Override
    void show(){
        System.out.println("Child class show method");
    }

    @Override
    void print_name(){
        System.out.println(name);
    }

    void only_child(){
        System.out.println("this method is only in child");
    }
}