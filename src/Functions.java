/*PROVIDES FUNCTIONALITY FOR THE PROGRAM*/
import java.util.*;

public class Functions extends Random{
    private Scanner scan = new Scanner(System.in);
    private String input;
    private Heap heap = new Heap();
    public void insert(ArrayList arr, BST tree){
        Data data = inputData();
        if(heap.search(arr, data.getID()) != null)
            System.out.println("DATA WAS NOT ADDED! TWO EMPLOYEES CANNOT SHARE THE SAME ID!");
        else if(tree.search(tree.root(), data.getSIN()) != null)
            System.out.println("DATA WAS NOT ADDED! TWO EMPLOYEES CANNOT SHARE THE SAME SIN!");
        else{
            heap.insert(arr, data);
            tree.add(tree.root(), data);
            System.out.println("RECORD IS ADDED!\n" + data);
        }
    }
    public Data inputData(){
        int id = inputID();
        String name = inputName();
        int sin = inputSIN();
        String dpt = inputDept();
        String address = inputAddress();
        int salary = inputSalary();
        return new Data(id, name, sin, dpt, address, salary);
    }
    // CHECKS IF A STRING IS AN INTEGER
    public boolean isInt(String s){
        try{
            Integer.parseInt(s);
        }
        catch (NumberFormatException e){
            return false;
        }
        catch (NullPointerException e){
            return false;
        }
        return true;
    }
    //FOR SIN WE NEED TO HAVE A 9-DIGIT NUMBER. THIS CHECKS THAT
    public boolean from(int x, int a, int b){
        return (x >= a && x <= b) ? true : false;
    }
    //PROMPTS USER TO INPUT ID
    public int inputID(){
        System.out.print("ID: ");
        String error = "ID IS A POSITIVE NUMBER!";
        input = scan.nextLine();
        if(isInt(input) && Integer.parseInt(input) >= 0)
            return Integer.parseInt(input);
        else {
            System.out.println(error);
            return inputID();
        }
    }
    //PROMPTS USER TO INPUT NAME
    public String inputName(){
        System.out.print("NAME: ");
        return inputString();
    }
    //CHECKS IF AN INPUT SIN IS LEGIT
    public boolean isSIN(int sin){
        return (from(sin, 100000000, 999999999)) ? true : false;
    }
    //PROMPTS USER TO INPUT SIN
    public int inputSIN(){
        System.out.print("SIN: ");
        String error = "SIN IS A 9-DIGIT NUMBER!";
        input = scan.nextLine();
        if(isInt(input) && isSIN(Integer.parseInt(input)))
            return Integer.parseInt(input);
        else {
            System.out.println(error);
            return inputSIN();
        }
    }
    //PROMPTS USER TO INPUT DEPARTMENT BY SPECIFYING ITS NUMBER
    public String inputDept(){
        System.out.print("DEPARTMENTS: ");
        for (String dept : getDepts())
            System.out.print(getDepts().indexOf(dept) + ": " + dept + " | ");
        System.out.println();
        System.out.print("DEPT: ");
        if(scan.hasNextInt()){
            int dept = scan.nextInt();
            scan.nextLine();//consume leftovers
                return (dept >= 0 && dept < getDeptsSize()) ? getDepts().get(dept) : inputDept();
        }
        else {
            scan.nextLine();//consume the line
            return inputDept();
        }
    }
    //PROMPTS USER TO INPUT ADDRESS
    public String inputAddress(){
        System.out.print("ADDRESS: ");
        return inputString();
    }
    //PROMPTS USER TO INPUT SALARY
    public int inputSalary(){
        System.out.print("SALARY: ");
        String error = "SALARY IS A POSITIVE NUMBER!";
        input = scan.nextLine();
        if(isInt(input) && Integer.parseInt(input) >= 0)
            return Integer.parseInt(input);
        else {
            System.out.println(error);
            return inputSalary();
        }
    }
    //SEARCHES FOR DATA
    public Data search(ArrayList arr, BST tree){
            System.out.print("ID/SIN: ");
            input = scan.nextLine().toUpperCase();
            if (input.equals("ID"))
                return heap.search(arr, inputID());
            else if(input.equals("SIN"))
                return tree.getData(tree.search(tree.root(), inputSIN()));
            else{
                System.out.println("'"+ input + "' IS NOT A KEY!");
                return search(arr, tree);
            }
    }
    //SORTS DATA BY ID OR SIN AND THEN DISPLAYS IT
    public void sort(ArrayList arr, BST tree, String header){
        System.out.print("ID/SIN: ");
        input = scan.nextLine().toUpperCase();
        if (input.equals("ID"))
        {
            System.out.print(header);
            heap.print(arr);
        }
        else if(input.equals("SIN")){
            System.out.print(header);
            tree.inOrder(tree.root());
        }
        else{
            System.out.println("'"+ input + "' IS NOT A KEY!");
            sort(arr, tree, header);
        }
    }
    //UPDATES A RECORD
    public void update(ArrayList arr, BST tree){
        int id = inputID();
        Data data = heap.search(arr, id);
        if(data == null){
            System.out.println("THERE IS NO EMPLOYEE WITH ID: " + id);
            return;
        }
        String name = inputName();
        int sin = inputSIN();
        BST.Node node= tree.search(tree.root(), sin);
        if (node != null && node.data != data){
            System.out.println("UPDATE IS IMPOSSIBLE!\nA PERSON WITH SIN " + sin + " IS ALREADY IN THE DATABASE!");
            return;
        }
        data.setName(name);
        tree.deleteNode(tree, data);
        data.setSIN(sin);
        data.setAddress(inputAddress());
        data.setDept(inputDept());
        data.setSalary(inputSalary());
        tree.add(tree.root(), data);
        System.out.println("RECORD IS UPDATED!\n" + data);
    }
    //DELETES A RECORD
    public void delete(ArrayList arr, BST tree){
        int id = inputID();
        Data data = heap.search(arr, id);
        if(data == null){
            System.out.println("THERE IS NO EMPLOYEE WITH ID: " + id);
            return;
        }
        System.out.print("ARE YOU SURE YOU WANT TO DELETE THIS RECORD?\n" + data + "\nYES/NO: ");
        if(!scan.nextLine().toUpperCase().contains("YES")){
            System.out.println("RECORD WAS NOT DELETED!");
            return;
        }

        tree.deleteNode(tree, data);
        arr.remove(data);
        System.out.println("RECORD IS DELETED!");
    }
    //WE DO NOT ALLOW EMPTY INPUT, THIS CHECKS INPUT FOR EMPTINESS
    public String inputString(){
        if(scan.hasNextLine()){
            input = scan.nextLine().toUpperCase();
            if(input.isEmpty())
                return inputString();
            else
                return input;
        }
        return inputString();
    }
}
