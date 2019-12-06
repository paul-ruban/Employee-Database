import java.io.*;
import java.util.*;

public class Main{
    public static void main(String args[]) throws IOException {
        Random r = new Random();
        int count = 2000; // number of employees in the initial DB
        File file = new File("output.txt");
        FileWriter fw;
        fw = new FileWriter(file);
        // write a header with field names
        String header = "ID\tName\tSIN\tDepartment\tAddress\tSalary\n";
        fw.write(header);
        Data data;
        //GENERATES RANDOM DATA
        r.generate();
        //WRITE ACTUAL RECORDS THAT ARE RANDOMLY GENERATED
        for (int i = 1; i <= count; i++){
            data = r.random();
            fw.write( data + "\n");
        }
        fw.close();
        Scanner scanner = new Scanner(file);
        scanner.nextLine().split("\t");//read the first line and disregard it
        ArrayList<Data> arr = new ArrayList<Data>();
        //READ RECORDS FROM THE FILE TO THE ARRAYLIST
        while (scanner.hasNext()){
            String record = scanner.nextLine();
            data = new Data(record);
            arr.add(data);
        }
        // heap will sort the records in place by the primary key
        Heap heap = new Heap();
        heap.sort(arr);// ELEMENTS ARE NOW SORTED
        ///BST will sort the records by secondary key
        BST tree = new BST();
        // fill in the BST based on sin
        for (Data element: arr)
            tree.setRoot(tree.add(tree.root(), element));
        //ELEMENTS ARE NOW SORTED IN THE BST BY SIN
        //ADDING FUNCTIONALITY TO THE PROGRAM
        List<String> commands = Arrays.asList("ADD", "SORT", "DISPLAY", "SEARCH", "UPDATE", "DELETE", "EXIT");
        String command;
        System.out.println("WELCOME TO EMPLOYEE DATABASE! CHOOSE A COMMAND FROM THE LIST!");//GREETING MESSAGE
        //COMMANDS AVAILABLE
        for (String option : commands)
            System.out.print("| " + option + " ");
        System.out.println();
        Scanner scan = new Scanner(System.in);
        Functions f = new Functions();
        //EXECUTE UNLESS EXIT IS PROMPTED
        while(true) {
            System.out.println("##############################################################");
            System.out.print("COMMAND: ");
            command = scan.next();
            command = command.toUpperCase();
            switch (commands.indexOf(command)) {
                case -1://PROMPT NOT FOUND
                    System.out.println("'" + command + "' IS NOT A COMMAND!");
                    break;
                case 0://ADD
                    //ADDS A RECORD and updates both ARRAYLIST AND BST
                    f.insert(arr, tree);
                    break;
                case 1://SORT
                    //DISPLAY SORTED RECORDS
                    f.sort(arr, tree, header);
                    break;
                case 2://DISPLAY
                    heap.print(arr);
                    break;
                case 3://SEARCH
                    data = f.search(arr, tree);
                    System.out.println((data == null) ? "RECORD IS NOT FOUND!" : data);
                    break;
                case 4://UPDATE
                    f.update(arr, tree);
                    break;
                case 5://DELETE
                    f.delete(arr, tree);
                    break;
                case 6://EXIT
                    return;
            }
        }
    }
}
