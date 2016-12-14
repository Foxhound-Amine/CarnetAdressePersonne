/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astrolabe31.runningfx.controller;

import astrolabe31.runningfx.MainApp;
import astrolabe31.runningfx.model.Person;
import astrolabe31.runningfx.util.DateUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


/**
 *
 * @author Administrator
 */
public class FXMLDocumentController  {
	@FXML
    private TableView<Person> personTable;

    @FXML
    private TableColumn<Person, String> firstNameColumn;
    @FXML
    private TableColumn<Person, String> lastNameColumn;
    
    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label streetLabel;
    @FXML
    private Label postalCodeLabel;
    @FXML
    private Label cityLabel;
    @FXML
    private Label birthdayLabel;

    @FXML
    private MainApp mainApp;
    
    
    
    public FXMLDocumentController(){
    	
    }
    
    @FXML
    private void initialize() {
    	System.out.println("Controller initialize");
    	 firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firtsNameProperty());
         lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
       
         
         //clear person détail
         showPersonDetails(null);
         
      // Listen for selection changes and show the person details when changed.
         personTable.getSelectionModel().selectedItemProperty().addListener(
                 (observable, oldValue, newValue) -> showPersonDetails(newValue));
         System.out.println("Controller fin initialize");
    } 
    

    
    public void setMainApp(MainApp mainApp){
    	System.out.println("Controller la liste");
    	this.mainApp = mainApp;
        // Add observable list data to the table
    	
    	personTable.setItems(mainApp.getPersonData());
    	
    }

	public TableView<Person> getEleveTable() {
		return personTable;
	}
	
	private void showPersonDetails(Person person) {
	  
		if (person != null) {
	        // Fill the labels with info from the person object.
	        firstNameLabel.setText(person.getFirstName());
	        lastNameLabel.setText(person.getLastName());
	        streetLabel.setText(person.getStreet());
	        postalCodeLabel.setText(Integer.toString(person.getPostalCode()));
	        cityLabel.setText(person.getCity());
	        birthdayLabel.setText(DateUtil.format(person.getBirthday()));
	        // TODO: We need a way to convert the birthday into a String!
	        // birthdayLabel.setText(...);
	    } else {
	        // Person is null, remove all the text.
	        firstNameLabel.setText("");
	        lastNameLabel.setText("");
	        streetLabel.setText("");
	        postalCodeLabel.setText("");
	        cityLabel.setText("");
	        birthdayLabel.setText("");
	    }
	}
	/**
	 * Called when the user clicks on the delete button.
	 */
	@FXML
	private void handleDeletePerson() {
	    int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
	    if (selectedIndex >= 0) {
	        personTable.getItems().remove(selectedIndex);
	    } else {
	        // Nothing selected.
	        Alert alert = new Alert(AlertType.WARNING);
	        alert.initOwner(mainApp.getStage());
	        alert.setTitle("No Selection");
	        alert.setHeaderText("No Person Selected");
	        alert.setContentText("Please select a person in the table.");

	        alert.showAndWait();
	    }
	}
    
	/**
	 * Called when the user clicks the new button. Opens a dialog to edit
	 * details for a new person.
	 */
	@FXML
	private void handleNewPerson() {
	    Person tempPerson = new Person();
	    boolean okClicked = mainApp.showPersonEditDialog(tempPerson);
	    if (okClicked) {
	        mainApp.getPersonData().add(tempPerson);
	    }
	}

	/**
	 * Called when the user clicks the edit button. Opens a dialog to edit
	 * details for the selected person.
	 */
	@FXML
	private void handleEditPerson() {
	    Person selectedPerson = personTable.getSelectionModel().getSelectedItem();
	    if (selectedPerson != null) {
	        boolean okClicked = mainApp.showPersonEditDialog(selectedPerson);
	        if (okClicked) {
	            showPersonDetails(selectedPerson);
	        }

	    } else {
	        // Nothing selected.
	        Alert alert = new Alert(AlertType.WARNING);
	        alert.initOwner(mainApp.getStage());
	        alert.setTitle("No Selection");
	        alert.setHeaderText("No Person Selected");
	        alert.setContentText("Please select a person in the table.");

	        alert.showAndWait();
	    }
	}
}
