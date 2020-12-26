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

}
