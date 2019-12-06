/*PROVIDES RANDOM DATA FOR THE PROGRAM*/
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Random extends Data{
    ArrayList<Integer> ID = new ArrayList<>(), SIN = new ArrayList<>();
    ArrayList<String> firstName = new ArrayList<>();
    ArrayList<String> lastName = new ArrayList<>();
    ArrayList<String> address = new ArrayList<>();
    //GENERATES A RANDOM ALPHA STRING OF LENGTH A

    public String randName(){
        return new String();
    }
    public String randAddress(){
        return new String();
    }
    //GENERATES A RANDOM NUMBER WITHIN [0; A)
    public int random(int a){
        return (int)(Math.random() * a);
    }
    //GENERATES A RANDOM DEPARTMENT FROM THE COMPANY
    public String dept(List<String> list, int size){
        return list.get(random(size));
    }
    //GENERATES A RANDOM NUMBER WITHIN [A; B]
    public int random(int a, int b){
        return  a + (int)((Math.random() * (b - a) + 1) );
    }
    //CREATES A RANDOM RECORD OF PREVIOSLY RANDOMISED ENTRIES
    public Data random(){
        int id = ID.get(0);
        ID.remove(0);
        String name = firstName.get(random(firstName.size() - 1)) + " " + lastName.get(random(lastName.size() - 1));
        int sin = SIN.get(0);
        SIN.remove(0);
        String dept = dept(getDepts(), getDeptsSize());
        String addr = random(1,9999) + ", " + address.get(random(address.size() - 1)) + ", Montreal, QC";
        int salary = random(100000);
        return new Data(id, name, sin, dept, addr, salary);
    }
    //GENERATES TWO COLLECTIONS OF IDs AND THEN SHUFFLES THEM IN PLACE TO LATER FEED RANDOM ENTRIES
    public void generate() throws FileNotFoundException {
        for (int i = 1000; i < 9999; i++)
            ID.add(i);
        for (int i = 900000000; i < 900009999; i++)
            SIN.add(i);
        Collections.shuffle(ID);
        Collections.shuffle(SIN);

        File firstNameFile = new File("src/names/names_first.txt");
        Scanner scanner = new Scanner(firstNameFile);
        while (scanner.hasNext())
            firstName.add(scanner.nextLine());

        File lastNameFile = new File("src/names/names_last.txt");
        scanner = new Scanner(lastNameFile);
        while (scanner.hasNext())
            lastName.add(scanner.nextLine());

        File addressFile = new File("src/names/names_address.txt");
        scanner = new Scanner(addressFile);
        while (scanner.hasNext())
            address.add(scanner.nextLine());

    }
}
