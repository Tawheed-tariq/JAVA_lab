public class Strings{
    public static void main(String []args){
        String str = new String("Sonia");
        System.out.println("String using Constructor: " + str);
        char ch[] = {'T', 'A', 'W', 'H', 'E', 'E', 'D'};
        String str1 = new String(ch);
        System.out.println("String using character Array: " + str1);

        String str2 = "Sibgat";
        System.out.println("String using literal: " + str2);

        System.out.println("Concat: " + str.concat(" Yadav"));
        System.out.println("Compare to: " + str.compareTo(str2));

        System.out.println("Char At: " + str2.charAt(3));
        char[] ch_new = new char[10];
        str1.getChars(2, 5, ch_new, 2);
        System.out.println(new String(ch_new));

        System.out.println("Bytes:" + str.getBytes());
        for (char c : str.toCharArray()) {
            System.out.print(c + " ");
        }
        System.out.println("");
        System.out.println("Region Matches:" + str2.regionMatches(0, str, 1, 4));

        System.out.println("Starts with:" + str1.startsWith("TA"));

        System.out.println("Index of " + str1.indexOf('E', 3));

        System.out.println("Substring: " + str2.substring(3));
        System.out.println("Replace: " + str1.replace('A', 'S'));

        String lastString = "          K A A L U                   ";
        System.out.println("Trim: " + lastString.trim());

        System.out.println("Strip: " + lastString.strip() );
        System.out.println("Strip leading: " + lastString.stripLeading() );
        System.out.println("String Trailing: " + lastString.stripTrailing());

        System.out.println("Join: " + String.join(";", str, str1, str2));
    }
}
