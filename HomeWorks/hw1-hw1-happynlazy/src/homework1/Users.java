package homework1;

import javax.jws.soap.SOAPBinding;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import com.google.gson.*;//added this even if we download gson.


public class Users extends Processor{

    // Use Java generics to avoid linear searching for users by id, makes it O(1) instead of O(n)
    private  static Map<Integer, Users> useridDict = new HashMap<>();
    private static ArrayList<Users> allUsers = new ArrayList<>();
    //__________modified by Danny_________
    Users[] users;//I could use another class to store the data.
    String date;

    public void setUsers(Users[]temp){
        this.users = temp;
    }
    public void setUsers(){

    }

    public void setUsername(String username) {
        this.username = username;
        System.out.println(username);
    }

    private  String username;

    public void setUserid(int userid) {
        this.userid = userid;
        System.out.println(userid);
    }

    private  int userid;

    public Users(){
        allUsers.add(this);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now;
        now = LocalDateTime.now();
        date = now.format(formatter);
    }

    public Users(String username, int userid){
        this.username = username;
        this.userid = userid;
        allUsers.add(this);
        useridDict.put(userid, this);
    }

    public static Users getUser(int userid){
        return useridDict.get(userid);
    }

    public void register(){
        useridDict.put(userid, this);
    }

    public static void loadAll(){
        for(int i = 0 ; i < allUsers.size(); i++){
            allUsers.get(i).register();
        }
    }



}
