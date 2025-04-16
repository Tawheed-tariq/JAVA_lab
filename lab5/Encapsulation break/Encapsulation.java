class Encapsulation {
    public static void main(String[] args) {
        EncapsulatedClass obj = new EncapsulatedClass();
        obj.setData(new StringBuilder("Tawheed"));
        System.out.println("The value of data is: " + obj.getData());

        // Breaking encapsulation by modifying the mutable object directly
        StringBuilder data = obj.getData();
        data.append(" Tariq");
        System.out.println("The value of data after encapsulation break is: " + obj.getData());
    }
}

class EncapsulatedClass {
    private StringBuilder data;

    public void setData(StringBuilder data) {
        this.data = data;
    }

    // vulnerable method --> causes encapsulation break
    public StringBuilder getData() {
        return data;
    }

    // safe method --> returns a copy of the data
    public StringBuilder getDataCopy() {
        return new StringBuilder(data.toString());
    }

};