import java.util.Scanner;

class NumOutOfRangeException extends Exception {
    public NumOutOfRangeException(String message) {
        super(message);
        System.out.println("you have encountered a number out of range exception: " + message);
        
    }
}

class IncorrectChoiceException extends Exception {
    public IncorrectChoiceException(String message) {
        super(message);
        System.out.println("you have encountered a incorrect choice exception: " + message);
        
    }
}


public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Enter a positive number: ");
            int num = sc.nextInt();
            if (num < 0) {
                throw new NumOutOfRangeException("Number is out of range!");
            }
            System.out.println("You entered: " + num);
            sc.nextLine(); //new line comsume


            System.out.print("Enter your choice (1. Add, 2. Subtract): ");
            int choice = sc.nextInt();
            if (choice != 1 && choice != 2) {
                throw new IncorrectChoiceException("please choose either 1 or 2");
            }
            System.out.println("You selected: " + (choice == 1 ? "Add" : "Subtract"));

        } catch (NumOutOfRangeException | IncorrectChoiceException e) {
            System.out.println("Exception: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Invalid input!");
        }
    }
}