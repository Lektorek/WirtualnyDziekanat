package sample;

import javafx.collections.ObservableList;

import java.io.*;
import java.util.Scanner;

public class FileController {


    static void readFile(ObservableList dataList, String path, String dataType){
        try {
            Scanner sc = new Scanner(new File(path));

            if(dataType.equals("Student")) {
                while (sc.hasNextLine()) {
                    String[] temp = sc.nextLine().split(" ");
                    dataList.add(new Student(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]), temp[2], temp[3], temp[4], temp[5], temp[6], temp[7], temp[8]));
                }
            }
            if(dataType.equals("Section")) {
                while (sc.hasNextLine()) {
                    String[] temp = sc.nextLine().split("/");
                    dataList.add(new Section(Integer.parseInt(temp[0]),temp[1]));
                }
            }
            if(dataType.equals("StudyType")) {
                while (sc.hasNextLine()) {
                    String[] temp = sc.nextLine().split("/");
                    dataList.add(new StudyType(Integer.parseInt(temp[0]),temp[1]));
                }
            }
            if(dataType.equals("String")) {
                while (sc.hasNextLine()) {
                    String temp = sc.nextLine();
                    dataList.add(temp);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void printFile(ObservableList<Student> dataList,String path){
        try {
            PrintWriter pW = new PrintWriter(new FileOutputStream(path,false));
            dataList.forEach(student -> {
                pW.write(student.getID()+" ");
                pW.write(student.getIndex()+" ");
                pW.write(student.getName()+" ");
                pW.write(student.getSurname()+" ");
                pW.write(student.getAddress()+" ");
                pW.write(student.getBirthDate()+" ");
                pW.write(student.getEmail()+" ");
                pW.write(student.getSection().replaceAll(" ","_")+" ");
                pW.write(student.getStudyType().replaceAll(" ","_")+" ");
                pW.write("\n");
            });
            pW.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
