package sample;

public class Section {
    private int id;
    private String sectionName;
    Section(int id, String sectionName){
        this.id=id;
        this.sectionName=sectionName;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id=id;
    }

    public String getSectionName(){
        return sectionName;
    }

    public void setSectionName(String sectionName){
        this.sectionName=sectionName;
    }
}
