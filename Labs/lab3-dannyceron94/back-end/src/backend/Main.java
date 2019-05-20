package backend;

import com.google.gson.Gson;

import static spark.Spark.*;

class Data{
    public final int temp;
    public final String message;
    public Data(int temp,String data){
        this.message = data;
        this.temp = temp;
    }
        }

public class Main {

    public static void main(String[] args) {
        Gson gson = new Gson();

        port(6000);
        // calling get will make your app start listening for the GET path with the /hello endpoint
//        get("/hello", (req, res) -> "Whats Up!");
        get("/hello", (req, res) -> {
            Data data = new Data(70,"Sunny Babe");
        return gson.toJson(data);
        });
    }
}
