public class BooleanOperation {
    public static void main(String[] args) {
        boolean a = true;
        boolean b = false;

        boolean andResult = a && b;
        System.out.println("a AND b: " + andResult);

        boolean orResult = a || b;
        System.out.println("a OR b: " + orResult);

        boolean notA = !a;
        System.out.println("NOT a: " + notA);

        boolean xorResult = a ^ b;
        System.out.println("a XOR b: " + xorResult);
    }
}