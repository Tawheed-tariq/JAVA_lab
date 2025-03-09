public class MainOverloading{
    
    public static void main(String args, int num){
        System.out.println("main function with a string and an integer: " + args + ", " + num);
    }
    public static void main(String args[]){
        System.out.println("main function with no parameters");
        main(4);
        main("string", 3);
    }


    public static void main(int num){
        System.out.println("main function with an integer: " + num);
    }
}