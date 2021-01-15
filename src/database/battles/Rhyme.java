package database.battles;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.LinkedList;
import java.util.List;

public class Rhyme {

    @SerializedName("1")
    @Expose
    private List<String> _1 = null;

    @SerializedName("2")
    @Expose
    private List<String> _2 = null;

    private String terminacio1;
    private String terminacio2;
    private String terminacio3;
    private String terminacio4;
    private String frase1;
    private String frase2;
    private String frases3;
    private String frases4;


    public List<String> get1() {
        return _1;
    }




    public void set1(List<String> _1) {
        this._1 = _1;
    }

    public List<String> get2() {
        return _2;
    }

    public void set2(List<String> _2) {
        this._2 = _2;
    }

    public int calculaRima(String rima){
        int n = 0;
        String[] frase = rima.split("\n");
        String frase1 = frase[0];
        String frase2 = frase[1];
        String frase3 = frase[2];
        String frase4 = frase[3];
        // separem las termiancions
        terminacio1 = frase1.substring(frase[0].length()-3,frase[0].length()-1);
        terminacio2 = frase2.substring(frase[1].length()-3,frase[1].length()-1);
        terminacio3 = frase3.substring(frase[2].length()-3,frase[2].length()-1);
        terminacio4 = frase4.substring(frase[3].length()-3,frase[3].length()-1);
        //comparem las terminacions
        if(terminacio1.equals(terminacio2) || terminacio1.equals(terminacio3) || terminacio1.equals(terminacio4)){ n ++;}
        if(terminacio2.equals(terminacio1) || terminacio2.equals(terminacio3) || terminacio2.equals(terminacio4)){ n ++;}
        if(terminacio3.equals(terminacio1) || terminacio3.equals(terminacio2) || terminacio3.equals(terminacio4)){ n ++;}
        if(terminacio4.equals(terminacio1) || terminacio4.equals(terminacio2) || terminacio4.equals(terminacio3)){ n ++;}
        return n;
    }

}
