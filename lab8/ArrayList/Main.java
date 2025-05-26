import java.util.ArrayList;
import java.util.Iterator;

public class Main {
  public static void main(String[] args) {
    ArrayList<String> cars = new ArrayList<String>();
    cars.add("Volvo");
    cars.add("BMW");
    cars.add("Ford");
    cars.add("Mazda");
    cars.add(3, "abc");
    System.out.println(cars);
    cars.remove(1);
    System.out.println("After removal: " + cars);


    ArrayList<String> brands = new ArrayList<String>();
    brands.add("Microsoft");
    brands.add("W3Schools");
    brands.add("Apple");
    
    // brands.addAll(cars);
    boolean ret = brands.addAll(1, cars);

    System.out.println("Printing brands and cars" + brands + " returned: " + ret);

    brands.clear();

    System.out.println("Printing brands after clear: " + brands);

    System.out.println(cars.contains("G Wagon"));
    System.out.println(cars.contains("BMW"));


    ArrayList<Integer> list = new ArrayList<Integer>();
    list.ensureCapacity(5);
    for(int i = 0; i < 5; i++) {
      list.add(i);
    }

    System.out.println("Printing list: " + list + " size: " + list.size());
    list.add(4);

    System.out.println("Is list empty? " + list.isEmpty());


    Iterator<String> it = cars.iterator();
    
    while(it.hasNext()) {
      System.out.println(it.next());
    }

  }
}