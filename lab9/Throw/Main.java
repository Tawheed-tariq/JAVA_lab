public class Main {
    public static void main(String[] args) {
        try {
            m1();
        } catch (Exception e) {
            System.out.println("in main: " + e.getMessage());
        }
    }

    public static void m1() throws Exception {
        try {
            throw new Exception("thow from m1 method");
        } catch (Exception e) {
            System.out.println("in method m1");
            throw e;
        }
    }
}