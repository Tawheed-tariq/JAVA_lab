public class Main {
    public static void main(String[] args) {
        // Unhandled Exception this will crash the program
        // int a = 5 / 0; 

        // Handling exceptions using try-catch
        try {
            int b = 10 / 0;
        } catch (ArithmeticException e) {
            System.out.println("arithemtic exception found: " + e.getMessage() + "\n\n");
        }

        // try-catch-finally
        try {
            int[] arr = new int[2];
            System.out.println(arr[3]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("out of bound exception: " + e.getMessage());
        } finally {
            System.out.println("at last we print this line \n\n");
        }

        try {
            System.out.println("inside try-finally block");
        } finally {
            System.out.println("finally block executed \n\n");
        }

        try {
            String s = null;
            System.out.println(s.length());
        } catch (NullPointerException | ArithmeticException e) {
            System.out.println("exception : " + e.getMessage());
        }

        try {
            int[] arr = new int[2];
            arr[2] = 5;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("array index out of bount : " + e.getMessage());
        } catch (Exception e) {
            System.out.println("general exception: " + e.getMessage());
        }

        // Nested try-catch
        try {
            try {
                // int x = 5 / 0; uncomment to see inner exception
                
                String s = null;
                System.out.println(s.length());
            } catch (ArithmeticException e) {
                System.out.println("inner catch: " + e.getMessage());
            }
        } catch (NullPointerException e) {
            System.out.println("outer catch: " + e.getMessage());
        }
    }
}