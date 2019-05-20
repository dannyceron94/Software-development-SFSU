
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
//import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.util.JSON;
import org.bson.Document;
import org.bson.json.JsonWriterSettings;
import org.bson.types.ObjectId;
import spark.QueryParamsMap;
import sun.util.resources.cldr.id.CurrencyNames_id;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Queue;

import static com.mongodb.client.model.Filters.eq;
import static spark.Spark.*;

public class Main {



    public static void main(String[] args) {
        ArrayList<String> DocArray = new ArrayList<>();
        ArrayList<Notes> dataArray = new ArrayList<>();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        port(1234);//local host
//----------------------------------Mongodb stuff, can be put into a method-----------------------------------
        //this cide is just opening the port of the database
        MongoClient mongoClient = MongoClients.create(
                MongoClientSettings.builder()
                        .applyToClusterSettings(builder ->
                                builder.hosts(Arrays.asList(new ServerAddress("localhost", 27017))))
                        .build());
        MongoDatabase database = mongoClient.getDatabase("dataBase");
        MongoCollection<Document> collection = database.getCollection("collection");
//---------------------------------End of Mongodb stuff-------------------------------------------------------


        //this is for post request, usually not work for endpoints. But i don't think we ever save data to database through endpoints
        post("/store",(req,res)->{//this only works for terminals or postman.
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();

            String param = req.queryParams("notes");
            Document doc = new Document("data",param)
                    .append("date",dateFormat.format(date));

            collection.insertOne(doc);
            return 200;
        });

        //this block of code, stores data from the end point to the server.
        get("/store",(req,res)->{
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();

            String param = req.queryParams("notes");
            Document doc = new Document("data",param)
                    .append("date",dateFormat.format(date));

            collection.insertOne(doc);
            return 200;
        });

//needs work
        delete("delete",(req,res)->{
            // Annihilate something
            return "deleted";
        });

        //list all data from the database on the web, like an api!!! so sweet!
        get("/list",(req,res)->{
            //show something
            return "List";
        });


        get("get",(req,res)->{
            //show something
            ArrayList<String> values = new ArrayList<>();
            Response response = new Response();
            NoteData tempData = new NoteData();
            Notes data = new Notes();
            String document ="not found";

            String valueID = req.queryParams("_id");
            System.out.println(valueID);

            //add some an exception or a method to prevent from crashing if it can't find a document.
            if (valueID!=null) {
                //add some an exception or a method to prevent from crashing if it can't find a document.

                Document found = collection.find(eq("_id",new ObjectId(valueID))).first();
                document = found.toJson();
                tempData = gson.fromJson(document, NoteData.class);

                data.set_id(tempData.get_id());
                data.setData(tempData.getData());
                data.setDate(tempData.getDate());
                response.addResponse(data);

                document = gson.toJson(response);

            }

            return document;
        });


        get("/hello", (req, res) -> "Hello World");
    }

}
