import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        LinkedList<String> cars = new LinkedList<String>();
        cars.add("Volvo");
        cars.add("BMW");
        cars.add("Ford");
        cars.add("Mazda");
        cars.add(1, "ABC");
        System.out.println("Initial list: " + cars);

        cars.addFirst("Toyota");
        System.out.println("After addFirst: " + cars);

        cars.addLast("Honda");
        System.out.println("After addLast: " + cars);

        System.out.println("First element: " + cars.getFirst());
        System.out.println("Last element: " + cars.getLast());

        cars.removeFirst();
        System.out.println("After removeFirst: " + cars);
        cars.removeLast();
        System.out.println("After removeLast: " + cars);

        cars.set(2, "Tesla");
        System.out.println("After set(2, \"Tesla\"): " + cars);

        cars.remove(1);
        System.out.println("After remove(1): " + cars);

        System.out.println("Contains 'Ford'? " + cars.contains("Ford"));

        System.out.println("Size: " + cars.size());

        cars.clear();
        System.out.println("After clear: " + cars);
    }
}