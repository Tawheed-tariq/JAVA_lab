class Emp{
    String name;
    int empId;

    @Override
    public Emp clone() throws CloneNotSupportedException {
        Emp emp = new Emp();
        emp.name = this.name;
        emp.empId = this.empId;
        return emp;
    }

    @Override
    public String toString() {
        return "Emp [name=" + this.name + ", empId=" + this.empId + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        Emp emp = (Emp) obj;
        return this.empId == emp.empId && this.name == emp.name;
    }

    public static void main(String []args){
        Emp emp1 = new Emp();
        emp1.name = "Kaalu";
        emp1.empId = 000;

        try {
            Emp emp2 = emp1.clone();
            System.out.println("Employee 2 name " + emp2.name);
            System.out.println("Employee 2 ID " + emp2.empId);
        } catch (Exception e) {
            System.err.println("Clone not supported" + e);
        }

        Emp emp3 = new Emp();
        emp3.name = "Kaalu";
        emp3.empId = 000;
        System.out.println("emp1 == emp3 " + (emp1 == emp3));
        System.out.println("emp1.equals(emp3) " + emp1.equals(emp3));

        System.out.println(emp1);
    }
}