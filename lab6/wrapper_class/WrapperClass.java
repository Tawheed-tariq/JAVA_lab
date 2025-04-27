import java.math.BigInteger;

class WrapperClass{
    public static void main(String []args){
        BigInteger strt = BigInteger.ONE;
        BigInteger end = new BigInteger("10");
        BigInteger one = BigInteger.ONE;

        while(strt.compareTo(end) <= 0){
            System.out.println(strt);
            strt = strt.add(one);
        }

        // methods of wrapper classes
        Integer i = Integer.parseInt("199");
        Integer j = Integer.parseInt("199");
        int outBoxed = i;
        System.out.println("Integer: " + i);
        System.out.println("Parse Int:" + Integer.parseInt("10"));
        System.out.println("Parse Int:" + Integer.parseInt("FF", 16));
        System.out.println("Value of:" + Integer.valueOf("55"));
        System.out.println("Out boxing:" + outBoxed);

        Character ch =  Character.valueOf('S');
        System.out.println("Value: " + Character.valueOf('F'));

        System.out.println("== : " + (i == j));
        System.out.println("== : " + (i == outBoxed));
        System.out.println("== : " + (i.equals(j)));
    }
}