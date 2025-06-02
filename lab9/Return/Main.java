public class Main {
    public static void main(String[] args) {
        System.out.println("Result: " + divide(10, 0));
    }

    public static int divide(int a, int b) {
        try {
            System.out.println("Trying division...");
            if (b == 0) {
                throw new ArithmeticException("Division by zero!");
            }
            return a / b;  
        } catch (ArithmeticException e) {
            System.out.println("Caught exception: " + e.getMessage());
            return -1;     
        } finally {
            System.out.println("Finally block always executes");
        }
    }
}