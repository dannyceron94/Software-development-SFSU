package backend;

import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import spark.Spark;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static spark.Spark.port;
import static spark.Spark.post;


//import com.mongodb.MongoClient;



class Data {

    public final String message;

    public Data(String message) {

        this.message = message;
    }
}

//class DBase{
//    private  MongoCollection<Document> collection;
//    private  MongoDatabase database;
//    public   MongoClient mongoClient;
//    DBase(){
//
//    }
//
//
//    public void runDatabase()
//    {
//
//        //mongoClient = new MongoClient( "localhost" , 27017);
//
//    }//end of runDatabase method
//
//    public MongoClient getMongoClient() {
//        return mongoClient;
//    }
//
//    public  MongoCollection<Document> getCollection() {
//        collection = database.getCollection("collection");
//        return collection;
//    }
//
//    public  MongoDatabase getDatabase() {
//        database = mongoClient.getDatabase("dataBase");
//        return database;
//    }
//}//end of sever Class



public class Main {

    public static void main(String[] args) {
        ArrayList<Notes> dataArray = new ArrayList<>();

        MongoClient mongoClient = new MongoClient("localhost",27017);
        MongoDatabase database = mongoClient.getDatabase("dataBase");
        MongoCollection<Document> collection = database.getCollection("collection");

        Gson gson = new Gson();
        port(4000);



         //this part stores the note to database.
        post("/store",(req,res)->{
            System.out.println("Start");
            System.out.println(req.body());

//            System.out.println((char[]) req.attribute("value"));
            System.out.println("done");
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();



//            String param = req.queryParams("notes");
            Document doc = new Document("data",req.body())
                    .append("date",dateFormat.format(date));
//
            collection.insertOne(doc);
            return 200;
        });


        //this part list things
        Spark.get("/list",(req,res)->{
           // ArrayList<String> DocArray = new ArrayList<>();

            //show something
            Response tempRes = new Response();
            String response = "No thing here";

            MongoCursor<Document> cursor = collection.find().iterator();
            try {

                while (cursor.hasNext()) {
                    Notes tempNote = new Notes();
                    String jsonString = cursor.next().toJson();
                    NoteData noteObject = gson.fromJson(jsonString,NoteData.class);
                    //noteObject.set_id();//sets a string variable to the ID

                    tempNote.setDate(noteObject.getDate());
                    tempNote.setData(noteObject.getData());
                    tempNote.set_id(noteObject.get_id());


                    dataArray.add(tempNote);
                    //System.out.println(dataArray.size());                    System.out.println(gson.toJson(dataArray.get(0)));

                }

                tempRes.setResponses(dataArray);

                response = gson.toJson(tempRes);
                dataArray.clear();
                System.out.println(response);

                return response;
            } finally {
                cursor.close();
            }
        });

        // calling get will make your app start listening for the GET path with the /hello endpoint
        Spark.get("/hello", (req, res) -> {
//            Data data = new Data(70, "NAH");

//            return gson.toJson(data);
            return "idiot";
        });

        Spark.delete("/delete",(req,res)->{
            // Annihilate something

            //String deleteParam = req.queryParams("note");
            System.out.println("                                           stuff                                    ");
            System.out.println(req.body());

            //collection.deleteOne(eq("data","test"));

            return 200;
        });
    }
}
