import org.bson.types.ObjectId;

import java.lang.reflect.Type;

//we might not need this class.
public class Notes {

    private String _id;
    private String data;
    private String date;


    Notes(){}

    Notes(String notes){
        this.data = notes;

    }


    public void setDate(String date) {
        this.date = date;
    }

    public void set_id( String id) {
        this._id=id;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public String getDate() {
        return date;
    }

    public String getId() {
        return _id;
    }
}
