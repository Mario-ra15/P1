package Batallas;

public class Rappers {
    private String realName;
    private String stageName;
    private String birth; //s ha de mirar el format
    private String nationality;
    private int level;
    private String photo;

    public Rappers(String realName, String stageName, String birth, String nationality, int level, String photo) {
        this.realName = realName;
        this.stageName = stageName;
        this.birth = birth;
        this.nationality = nationality;
        this.level = level;
        this.photo = photo;
    }

    public String getRealName() {
        return realName;
    }
}
