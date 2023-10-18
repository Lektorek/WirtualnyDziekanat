package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

public class FXMLDocumentController implements Initializable {


    @FXML private TextField filterField;
    @FXML private TextField addID;
    @FXML private TextField addIndex;
    @FXML private TextField addName;
    @FXML private TextField addSurname;
    @FXML private TextField addAdress;
    @FXML private TextField addBDate;
    @FXML private TextField addEmail;
    @FXML private ComboBox addStudyType;
    @FXML private ComboBox addSection;
    @FXML private Button deleteButton;
    @FXML private Button addButton;
    @FXML private Button saveButton;
    @FXML private TableView<Student> tableview;
    @FXML private TableColumn<Student, String> index;
    @FXML private TableColumn<Student, String> name;
    @FXML private TableColumn<Student, String> ID;
    @FXML private TableColumn<Student, String> surname;
    @FXML private TableColumn<Student, String> email;
    @FXML private TableColumn<Student, String> studyType;
    @FXML private TableColumn<Student, String> section;


    private final ObservableList<Student> dataList = FXCollections.observableArrayList();
    private final ObservableList<Section> sectionList = FXCollections.observableArrayList();
    private final ObservableList<StudyType> studyTypeList = FXCollections.observableArrayList();

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {      
                               
        ID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        index.setCellValueFactory(new PropertyValueFactory<>("index"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        surname.setCellValueFactory(new PropertyValueFactory<>("surname"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        studyType.setCellValueFactory(new PropertyValueFactory<>("studyType"));
        section.setCellValueFactory(new PropertyValueFactory<>("section"));



        FileController.readFile(studyTypeList,"kierunki.txt","StudyType");
        FileController.readFile(sectionList,"wydzialy.txt","Section");
        FileController.readFile(dataList,"test.txt","Student");

        for(Section section:sectionList) addSection.getItems().add(section.getSectionName());
        for(StudyType studyType:studyTypeList) addStudyType.getItems().add(studyType.getStudyType());


        deleteButton.setOnAction(e -> {
            Student selectedItem = tableview.getSelectionModel().getSelectedItem();
            dataList.remove(selectedItem);
        });

        addButton.setOnAction(e ->{
            if(checkInteger(addID.getText())&&checkInteger(addIndex.getText())&&checkEmpty(addName.getText(),addSurname.getText(),addBDate.getText(),addAdress.getText(),addEmail.getText())&&checkSelected(addSection,addStudyType))
            dataList.add(new Student(Integer.parseInt(addID.getText()),Integer.parseInt(addIndex.getText()),addName.getText(),addSurname.getText(),addAdress.getText(),addBDate.getText(),addEmail.getText(),addSection.getSelectionModel().getSelectedItem().toString(),addStudyType.getSelectionModel().getSelectedItem().toString()));
            System.out.println(addID.textProperty().getValue().toString());
        });

        saveButton.setOnAction(e -> FileController.printFile(dataList,"test.txt"));



        // Zamiana list w celu natychmiastowego wyswietlenia na ekranie
        FilteredList<Student> filteredData = new FilteredList<>(dataList);


		filterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(student -> {
				// Jesli filtr jest pusty wyswietl wszystko
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				

				String lowerCaseFilter = newValue.toLowerCase();
				
				if (student.getName().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true;
				} else if (student.getSurname().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }
                else if (student.getEmail().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }
                else if (student.getSection().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }
                else if (student.getStudyType().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }
                else if (String.valueOf(student.getID()).indexOf(lowerCaseFilter)!=-1) {
                    return true;
                }
                else if (String.valueOf(student.getIndex()).indexOf(lowerCaseFilter)!=-1) {
                    return true;
                }
                else return false;
			});
		});
		

		SortedList<Student> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(tableview.comparatorProperty());
		tableview.setItems(sortedData);

        
    }

    private boolean checkInteger(String textInput){
        try{
            Integer.parseInt(textInput);
        }catch(Exception e){
            AlertBox alertBox = new AlertBox();
            alertBox.display("Błąd","Wprowadź odpowiedni typ danych!");
            return false;
        }
        return true;
    }

    private boolean checkEmpty(String... textInput){
       for(String i:textInput){
           if(i.isEmpty()) {
               AlertBox alertBox = new AlertBox();
               alertBox.display("Błąd","Wszystkie pola muszą być uzupełnione!");
               return false;
           }
       }
        return true;
    }

    private boolean checkSelected(ComboBox... box){
        for(ComboBox b:box){
            if(b.getValue()==null) {
                AlertBox alertBox = new AlertBox();
                alertBox.display("Błąd","Wybierz odpowiednią pozycje!");
                return false;
            }
        }
        return true;
    }
}
