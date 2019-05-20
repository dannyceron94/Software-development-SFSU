package homework1;

//Json stuff goes here
//I dont think this class should look like this.

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jdk.nashorn.internal.runtime.JSONListAdapter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
//import com.google.gson.*;

public class Response extends Processor{
    Users[] users;
//this might work;
    Processor SubClass;
    Response(){}

    public void setUsers(Users[] users) {
        this.users = users;
    }


    Response(HashMap<String,String>hmap){
    }

    Response(HashMap<String,String>hmap,Processor subclass){
        this.SubClass = subclass ;
    }



}
