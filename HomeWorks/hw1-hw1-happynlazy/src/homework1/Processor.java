//in order to create a subclass we most extend it to this class
//every class java class can have only one direct superclass
package homework1;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import com.google.gson.*;
import java.util.HashMap;
import java.io.FileReader;
import java.io.FileNotFoundException;


public class Processor{

    private static HashMap<String, String> pHmap;
    private static String request;
    private static Processor TheObject;
    static Users[] users;
    static Users tempUser;
    //useless constructor
    public Processor(){
    }

    public static Processor ProcessorFactory(HashMap<String, String> hmap){
        pHmap = hmap;
        request = pHmap.get("request");
        switch(request){
            case "math":
                TheObject = new Add(hmap);
                System.out.println("math request");
                break;

            case "post":
                TheObject =  new Post(hmap);
                System.out.println("Post request");
                break;

            case "users":
                System.out.println("User request");

                if(TheObject instanceof Users) {//lazy loading of some sort
                    System.out.println("went inside lazy");
                   tempUser.setUsers(new Users[]{Users.getUser(Integer.valueOf(hmap.get("userid")))});
                   TheObject = tempUser;
                }//end of if
                else
                {
                    System.out.println("went inside else");
                    //I loaded the users data once into the static
                    Gson gson = new GsonBuilder().setPrettyPrinting().create();
                    BufferedReader br;

                    try{
                        br = new BufferedReader(new FileReader("src/data.json"));
                        System.out.println("found file");
                        JsonParser jsonParser = new JsonParser();
                        JsonObject obj = jsonParser.parse(br).getAsJsonObject();

                        tempUser = new Users();

                        users = gson.fromJson(obj.get("users"), Users[].class);//what happens here?, what does the fromJson do, when wrapping the class?

                        Users.loadAll();

                        tempUser.setUsers(new Users[]{Users.getUser(Integer.valueOf(hmap.get("userid")))});
                        TheObject = tempUser;
                    }catch (FileNotFoundException e){
                        e.printStackTrace();
                    }//end of catch
                }
                break;
            default:System.out.println("invalid request");
        }
        return TheObject;
    }

    /**
     * @return response object
     **/
    public String process(){
        //we could use the hashmap.
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Response obj = new Response(pHmap,TheObject);
        String value = gson.toJson(obj);
        return value;
    }
}
