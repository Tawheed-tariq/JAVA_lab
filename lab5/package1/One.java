package package1;

public class One {
    public void display() {
        System.out.println("This is class One in package1");
    }

    public static void main(String []args){
        One obj = new One();
        obj.display();
    }
}
