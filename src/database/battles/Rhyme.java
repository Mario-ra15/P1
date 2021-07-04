package database.battles;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.LinkedList;
import java.util.List;


/**
 * En esta clase guardamos las rimas predeterminadas de los participantes y tambien nos permite calcular el valor de las riamas.
 *
 *
 * Te els atributs on separem les dos rimes en dos atributos diferentes, también tiene tiene el metodo que recibimos de
 * batalla la información y abtenemos las riams indicadas.
 */
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
    private String rima;

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

    /**
     * Recive la rima y la separa en terminaciones, y calcula el número de terminaciones correctas.
     * @param rima la frase que diu el participant
     * @return el número de rimes correctes
     */

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

    /**
     * Método que obtiene la rima del personaje indicado
     * @param batalles La inforamción de las batallas
     * @param level el nivel del personaje
     * @param ronda la ronda de la batalla a la que van
     * @param tema de quin tema es la batalla
     * @return la rima deseada
     */

    public String getRima(database.battles.Root batalles, int level, int ronda,int tema){
        if(level == 1) {
            if(batalles.getThemes().get(tema).getRhymes().get(0).get1().size() == 1){
                if(ronda == 1) rima = (batalles.getThemes().get(tema).getRhymes().get(0).get1().get(0));
                if (ronda == 2) rima = "nada";
            }else if(batalles.getThemes().get(tema).getRhymes().get(0).get1().size()== 0) {
                rima = "nada";
            }else {
                if(ronda == 1) rima = (batalles.getThemes().get(tema).getRhymes().get(0).get1().get(0));
                if(ronda == 2) rima = (batalles.getThemes().get(tema).getRhymes().get(0).get1().get(1));
            }
        }else{
            if(batalles.getThemes().get(tema).getRhymes().get(0).get2().size() == 1) {
                if(ronda == 1) rima = (batalles.getThemes().get(tema).getRhymes().get(0).get2().get(0));
                if (ronda == 2) rima = "nada";

            }else if(batalles.getThemes().get(tema).getRhymes().get(0).get2().size() == 0) {
                rima = "nada";
            }else {
                if(ronda == 1) rima = (batalles.getThemes().get(tema).getRhymes().get(0).get2().get(0));
                if(ronda == 2) rima = (batalles.getThemes().get(tema).getRhymes().get(0).get2().get(1));
            }
        }

        return rima;
    }

}
