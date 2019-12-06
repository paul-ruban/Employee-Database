//DATA CONTAINS INFORMATION ABOUT AN EMPLOYEE
import java.util.Arrays;
import java.util.List;

public class Data{
    private int ID, SIN, salary; // employee's ID, SIN and salary, hight of a node
    private String name, dept, address; // employee's name, department and address
    //CORPORATE DEPARTMENTS
    private List<String> depts  = Arrays.asList("MARKETING", "FINANCE", "IT", "HR", "OPERATIONS");

    public Data(){} //default constructor
    //constructors
    public Data(int ID, String name, int SIN, String dept, String address, int salary){
        this.ID = ID;
        this.name = name;
        this.SIN = SIN;
        this.dept = dept;
        this.address = address;
        this.salary = salary;
    }
    public Data(String record){
        String [] entries = record.split("\t");
        this.ID = Integer.parseInt(entries[0]);
        this.name = entries[1];
        this.SIN = Integer.parseInt(entries[2]);
        this.dept = entries[3];
        this.address = entries[4];
        this.salary = Integer.parseInt(entries[5]);
    }
    public int getID(){
        return this.ID;
    }
    public int getSIN(){
        return this.SIN;
    }
    public List<String> getDepts(){return this.depts;};
    public int getDeptsSize(){return this.depts.size();}
    public void setName(String name) {this.name = name;}
    public void setSIN(int SIN) {this.SIN = SIN;}
    public void setDept(String dept) {this.dept = dept;}
    public void setAddress(String address) {this.address = address;}
    public void setSalary(int salary) {this.salary = salary;}
    public String toString(){
        return String.join("\t",Integer.toString(ID), name, Integer.toString(SIN),
                dept, address, Integer.toString(salary));// tab is used as a separator
    }
    public void printData(Data data){
        System.out.println(data.toString());
    }
}
