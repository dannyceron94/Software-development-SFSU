package backend;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Response {
    private String date;
    private String responseCode = "OK";
    private ArrayList<String> resp;
    private ArrayList<Notes> res;
    private ArrayList<NoteData> respponse;
    Response(){
        SimpleDateFormat formatter = new SimpleDateFormat();
        Date today = new Date();
        this.date = formatter.format(today);
    }

//    public void setResponse(String[] response) {
//        //this.response = response;
//    }

    public void setResponse(ArrayList<String> responses) {
        this.resp = responses;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public void setResponses(ArrayList<Notes> response) {
        this.res = response;
    }

    public void setRes(ArrayList<NoteData> res) {
        this.respponse = res;
    }

    public String getDate() {
        return date;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void addResponse(Notes temp){
        if (this.res==null){
            this.res = new ArrayList<>();
        }
        this.res.add(temp);
    }

    public void addNoteResponse(NoteData temp)
    {
        if (this.respponse == null){
            this.respponse = new ArrayList<>();
        }
        this.respponse.add(temp);
    }

    public ArrayList<String> getResp() {
        return resp;
    }

    public ArrayList<Notes> getRes() {
        return res;
    }
}
