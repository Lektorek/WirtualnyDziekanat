package sample;

public class StudyType {
    private int id;
    private String studyType;
    StudyType(int id, String studyType){
        this.id=id;
        this.studyType = studyType;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id=id;
    }

    public String getStudyType(){
        return studyType;
    }

    public void setStudyType(String studyType){
        this.studyType = studyType;
    }
}
