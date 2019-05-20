package qa2019;

import static org.testng.AssertJUnit.*;
import static org.testng.internal.junit.ArrayAsserts.assertArrayEquals;

import com.google.common.collect.Collections2;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader;
import org.testng.annotations.Test;
import org.testng.internal.junit.ArrayAsserts;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


public class Main {
   // public static ArrayList<Integer> sorted;

    public static void main(String[] args) {
        System.out.println("Hello");
    }

//    @Test
//    public static void testStuff() {
//        assertEquals(3.0f, 3.0f);
//        assertEquals(4.0f, 3.0f);
//    }


    public static ArrayList sort(ArrayList<Integer> temp){
        ArrayList sorted = new ArrayList<>();

        //sorted = temp;
        Collections.sort(temp);
        for(int o:temp){
            int tempVal = o%2;
            if(tempVal !=1){
                sorted.add(o);
            }
        }

        return sorted;
    }

    public static ArrayList sortOdd(ArrayList<Integer> temp){
        ArrayList sorted = new ArrayList<>();

        //sorted = temp;
        Collections.sort(temp);
        for(int o:temp){
            int tempVal = o%2;
            if(tempVal ==1){
                sorted.add(o);
            }
        }

        return sorted;
    }

    @Test
    public static void testeven(){
        ArrayList<Integer> temp = new ArrayList<>();
        //for testing purposes
        temp.add(0);
        temp.add(4);
        temp.add(7);
        temp.add(23);
        temp.add(10);
        temp =sort(temp);
        for (int value:temp) {
            assertEquals(value%2,0);
        }
    }

    @Test
    public static void empty(){
        ArrayList<Integer> temp = new ArrayList<>();
        //for testing purposes
        temp.add(0);
        temp.add(4);
        temp.add(7);
        temp.add(23);
        temp.add(10);
        temp =sort(temp);
        assertFalse(temp.isEmpty());//this will fail because it is never initialized in this method
    }

    @Test
    public static void sortedArray(){
        ArrayList<Integer> temp = new ArrayList<>();
        //for testing purposes
        temp.add(0);
        temp.add(4);
        temp.add(7);
        temp.add(23);
        temp.add(10);
        temp =sort(temp);
        boolean test= false;
        for (int i=0;temp.size()>i;i++){
            int before = i-1;
            if (before<0){before=0;}

            if (temp.get(i)>=temp.get(before)) {
                test = true;
            }
            assertTrue(test);
        }
    }

    @Test
    public static void something(){
        ArrayList<Integer> temp = new ArrayList<>();
        //for testing purposes
        temp.add(0);
        temp.add(4);
        temp.add(7);
        temp.add(23);
        temp.add(10);
        temp =sortOdd(temp);

        for (int value:temp) {
            assertEquals(value%2,1);
        }
    }
}