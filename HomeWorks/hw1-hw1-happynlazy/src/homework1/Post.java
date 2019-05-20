package homework1;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

public class Post extends Processor{
    String userID = null;
    private LocalDateTime now;
    private String date;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    Post(){}

    Post(HashMap<String, String> hmap) {
        now = LocalDateTime.now();
        date = now.format(formatter);
        if (!hmap.get("userid").equals("0")) {
            userID = hmap.get("userid");

            if (hmap.get("request").equals("post")) {
                postr(userID);
            } else if (userID.equals("users")) {
                users(userID);
            }
        }
    }

    public void postr(String userid){

    }

    public void users(String userId){

    }
}
