//CREATES HEAP TO SORT THE ELEMENTS IN DB AND KEEP THEM SORTED IF UPDATED
import java.util.ArrayList;
import java.util.Collections;

public class Heap extends Data{
    private int middle;
    //SORT THE HEAP
    public void sort(ArrayList<Data> arr){
        int n = arr.size();
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);
        for (int i = n - 1; i >= 0; i--){
            Collections.swap(arr, 0, i);
            heapify(arr, i, 0);
        }
    }
    //METHOD THAT HELPS SORTING
    public void heapify(ArrayList<Data> arr, int n, int i){
        int max = i;
        int left = 2 * i + 1;
        int right = left + 1;
        Data x, xLeft, xRight;
        if(left < n){
            x = arr.get(max);
            xLeft = arr.get(left);
            if (x.getID() < xLeft.getID())
                max = left;
        }
        if(right < n){
            x = arr.get(max);
            xRight = arr.get(right);
            if (x.getID() < xRight.getID())
                max = right;
        }
        if(max != i) {
            Collections.swap(arr, i, max);
            heapify(arr, n, max);
        }
    }
    //SEARCH FOR A RECORD BY ID
    public Data search(ArrayList<Data> arr, int id){
        int left = 0, right = arr.size() - 1;
        while (left <= right){
            middle = left + (right - left) / 2;
            Data mid = arr.get(middle);
            if(id == mid.getID())
                return mid;
            else if (mid.getID() < id)
                left = middle + 1;
            else if (id < mid.getID())
                right = middle - 1;
        }
        //records the indext where an element would go
        middle = left;
        return null;
    }
    //PRINTS RECORDS
    public void print(ArrayList<Data> arr){
        for (Data element : arr)
            printData(element);
    }
    //INSERTS AN ELEMENT IN HEAP
    public void insert(ArrayList<Data> arr, Data data){
        int id = data.getID();
        if(search(arr, id) != null)
            System.out.println();
        else
            arr.add(middle, data);
    }
}
