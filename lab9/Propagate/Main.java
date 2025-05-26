import java.util.Scanner;


public class Main {
    public static void propagateException(int a, int b) throws ArithmeticException {
        int result = a / b;
    }

    public static void handleException(int a, int b) {
        try {
            int result = a / b;
        } catch (ArithmeticException e) {
            System.out.println("Handled exception: " + e.getMessage());
        }
    }

    public static void main(String[] args) throws ArithmeticException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter two integers (a and b): ");
        int a = sc.nextInt();
        int b = sc.nextInt();


        System.out.println("Handling exception:");
        handleException(a, b);

        //Propagate the exception to JVM
        System.out.println("\n\nPropagating exception:");
        propagateException(a, b); 
    }
}