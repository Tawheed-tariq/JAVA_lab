class TypeConversions {
    public static void main(String[] args) {
        int intVal = 100;
        double doubleVal = intVal;
        System.out.println("Implicit conversion from int to double: " + doubleVal);

        double anotherDoubleVal = 9.78;
        int anotherIntVal = (int) anotherDoubleVal;
        System.out.println("Explicit conversion from double to int: " + anotherIntVal);

        int intToByteVal = 130;
        byte byteVal = (byte) intToByteVal;
        System.out.println("Explicit conversion from int to byte: " + byteVal);

        char charVal = 'A';
        byte charToByteVal = (byte) charVal;
        System.out.println("Explicit conversion from char to byte: " + charToByteVal);

        short shortVal = (short) charVal;
        System.out.println("Explicit conversion from char to short: " + shortVal);

        int intToFloat = 17;
        float floatVal = intToFloat;
        System.out.println("Implicit conversion from int to float: "+ floatVal);

        int intToChar = 40;
        charVal = (char) intToChar;
        System.out.println("Explicit conversion from int to char: " + charVal);

        float floatToInt = 30.01f;
        intVal = (int) floatToInt;
        System.out.println("Explicit conversion from float to int: " + intVal);

        double charToDouble = charVal;
        System.out.println("Implicit conversion from char to double: " + charToDouble);
    }
}