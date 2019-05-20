package homework1;

import sun.util.logging.resources.logging;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class SimpleSever {
    //trying something

    private static SimpleSever simpleSeverSingleton;//you dont have to create any object when u use static
    private static Processor ResPro;
    private static HashMap<String, String> hmap = new HashMap<String, String>();


    private SimpleSever() {

    }//end of constructor

    public static SimpleSever getInstance(){
        if(simpleSeverSingleton == null){
            simpleSeverSingleton = new SimpleSever();
        }
        return simpleSeverSingleton;
    }//end of getInstance method

    public static void run(){
        ServerSocket ding;
        Socket dong = null;
        String resource = null;
        try {
            ding = new ServerSocket(1299);
            System.out.println("Opened socket " + 1299);
            while (true) {
                // keeps listening for new clients, one at a time
                try {
                    dong = ding.accept(); // waits for client here
                } catch (IOException e) {
                    System.out.println("Error opening socket");
                    System.exit(1);
                }

                InputStream stream = dong.getInputStream();
                BufferedReader in = new BufferedReader(new InputStreamReader(stream));

                try {
                    // read the first line to get the request method, URI and HTTP version
                    String line = in.readLine();
                    System.out.println("----------REQUEST START---------");

                    ArrayList<String> theArray;

                    theArray = new GetParse(line).getKeys();//in this case this is better to no instanciate to save memory?
                    System.out.println(theArray.size());
                    hmap.put("a","0");
                    hmap.put("b","0");
                    hmap.put("c","0");
                    hmap.put("userid","0");
                    hmap.put("request","null");

                    if(theArray.size()>=4&&theArray.get(0).equals("math")){

                        if(theArray.size()==4&&theArray.get(0).equals("math")) {
                            hmap.replace("request", theArray.get(0));
                            hmap.replace(theArray.get(2), theArray.get(3));
//                        System.out.println("");
                        }
                        else if (theArray.size()==6&&theArray.get(0).equals("math")){
                            hmap.replace("request", theArray.get(0));
                            hmap.replace(theArray.get(2),theArray.get(3));
                            hmap.replace(theArray.get(4),theArray.get(5));
                        }
                        else{
                            System.out.println("ERROR in math call");
                        }
                    }
                    else if(theArray.size()==3&&theArray.get(1).equals("userid")){
                        if(theArray.get(0).equals("users")) {
                            hmap.replace("request",theArray.get(0));
                            hmap.replace("userid", theArray.get(2));
                            System.out.println("user or post: "+hmap.get("userid"));
                        }
                        else{
                            System.out.println("ERROR in user");
                        }
                    }
                    else
                    {
                        System.out.println("No request");
                    }
                    for (String a:theArray){System.out.println(a);}

                    ResPro = Processor.ProcessorFactory(hmap);

                    line = in.readLine();
                    while (line != null && line.trim().length() > 0) {
                        int index = line.indexOf(": ");
                        if (index > 0) {
                            System.out.println(line);
                        } else {
                            break;
                        }
                        line = in.readLine();
                    }
                    System.out.println("----------REQUEST END---------\n\n");
                } catch (IOException e) {
                    System.out.println("Error reading");
                    System.exit(1);
                }

                BufferedOutputStream out = new BufferedOutputStream(dong.getOutputStream());
                PrintWriter writer = new PrintWriter(out, true);  // char output to the client

                // every response will always have the status-line, date, and server name
                writer.println("HTTP/1.1 200 OK");
                writer.println("Server: TEST");
                writer.println("Connection: close");
                writer.println("Content-type: text/html");
                writer.println("");

                // Body of our response
                try {
                    writer.println(ResPro.process());
                }catch(NullPointerException e){

                    System.out.println(e.getMessage());
                }

                dong.close();
            }
        } catch (IOException e) {
            System.out.println("Error opening socket");
            System.exit(1);
        }
    }//end of run method
}
