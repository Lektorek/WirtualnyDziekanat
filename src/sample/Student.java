/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;


public class Student extends Human{
    private int id;
    private int index;
    private String email;
    private String section;
    private String studyType;
    
    Student(int id, int index, String name, String surname, String address, String birthDate, String email, String section, String studyType) {
       super(name, surname, address, birthDate);
       this.id = id;
       this.index = index;
       this.email =  email;
       this.section = section;
       this.studyType = studyType;
    }


    public int getID() {
        return this.id;
    }

    public void setID(int id) {
        this.id=id;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String mail) {
        this.email=email;
    }


    public int getIndex() { return index; }

    public void setIndex(int index) { this.index = index; }


    public String getSection() { return section; }

    public void setSection(String section) { this.section = section; }


    public String getStudyType() { return studyType; }

    public void setStudyType(String studyType) { this.studyType = studyType; }
}
    
