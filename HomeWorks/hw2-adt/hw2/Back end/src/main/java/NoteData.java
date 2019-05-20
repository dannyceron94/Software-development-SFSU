import org.bson.types.ObjectId;

public class NoteData {

    String data;
    ObjectId _id;
    String date;
    public void setData(String data) {
        this.data = data;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public String get_id(){
        return this._id.toString();
    }

    public String getData() {
        return data;
    }

    public String getDate() {
        return date;
    }
}
