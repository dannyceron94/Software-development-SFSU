/*
created on feb 17, 2019
Purpose of this class is to parse the imputstream line to be able
to read the endpoints
it will return an array with the keywords of the endpoints
*/
package homework1;

import java.util.ArrayList;

public class GetParse {
    //trying something

    //Variables
    private String Inline;//stores constructor parameter
    private ArrayList<String> keys = new ArrayList<>();//stores parsed keywords

    /*
    @param line
     * */
    public GetParse(String line){

        Inline = line;
        this.parseWord(line);
    }

    public GetParse(){}//useless constructor to prevent an error in case it is instanciated with no params

        /*
    @param line
    splits the string by the spaces and "/", it also excludes the 1st and last words
    of the string.  it only stores the endpoints
     */
    public void parseWord(String line){
        String[] tokens = line.split("\\t|/|<|>|\\?|=%3C|%3E|=%3C|&=%3C|&|=| ");
//        for(String word: tokens){
//
//            keys.add(word);
//        }
        int n = tokens.length-2;//to ignore the the word GET from the string.
        if(n>0){
            for(int i=1;i<n;i++){
                if(!tokens[i].equals("")){
                    this.keys.add(tokens[i]);
                }

            }
        }

    }//end of parseWord method
    /*
    @return the keys, an arraylist with the endpoints
    */
    public ArrayList getKeys() {
        return this.keys;
    }
}
