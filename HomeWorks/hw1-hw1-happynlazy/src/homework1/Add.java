package homework1;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

public class Add extends Processor{
    //trying something


    int a = 0;
    int b = 0;
    int sum =0;

    String date;
    //private boolean right =false;

    
    Add(){}
    Add(HashMap<String, String> hmap){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now;
        //String reques_nd= tempArray.get(1);
        if(!hmap.get("b").equals("0")){//add exception for casting string to ints
            try {
                a = Integer.valueOf(hmap.get("a"));
                b = Integer.valueOf(hmap.get("b"));
                sum = a + b;
                //right = true;
            }catch(NumberFormatException e){

                System.out.println(e.getMessage());
            }
        }
        else if (hmap.get("b").equals("0")){
            try {
                a = Integer.valueOf(hmap.get("a"));
                sum = a+1;
            }catch (NumberFormatException e){System.out.println(e.getMessage());}
           // right = true;
        }
        else{
            //right =false;
            System.out.println("Error occurred");
        }
        now = LocalDateTime.now();
        date = now.format(formatter);
    }

}
