/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astrolabe31.runningfx;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import astrolabe31.runningfx.controller.BirthdayStatisticsController;
import astrolabe31.runningfx.controller.FXMLDocumentController;
import astrolabe31.runningfx.controller.PersonEditDialogController;
import astrolabe31.runningfx.controller.RootLayoutController;
import astrolabe31.runningfx.model.Person;
import astrolabe31.runningfx.model.PersonListWrapper;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;


/**
 *
 * @author Administrator
 */
public class MainApp extends Application {
	private static final Logger log = LoggerFactory.getLogger(MainApp.class);
    private Stage stage;
    private BorderPane rootLayout;
    //private Parent root; 
    //private AnchorPane anchorPane;
    //private EntityManagerFactory emf ;
    //public  EntityManager em;
    
    private ObservableList<Person> personData = FXCollections.observableArrayList();
    
    
    @Override
    public void start(Stage stage) throws Exception {
       this.stage = stage;
      // this.stage.setTitle("run");
       this.stage.getIcons().add(new Image("file:resources/images/address-book-512.png"));
       initRootLayout();
       //remplir par données de test
       //readRunningTable();
       showPersonOverview();
        
    }
    
    public void initRootLayout(){
             //modif code original rgt
        // Localisation du fichier FXML.
        URL url = getClass().getResource("/view/RootLayout.fxml");
        // Chargement du bundle:
        ResourceBundle bundle = ResourceBundle.getBundle("astrolabe31/runningfx/strings");
        // Creation du loader.
        FXMLLoader fxmlLoader = new FXMLLoader(url, bundle);
       
        try {
          //rootLayout = new BorderPane();
          rootLayout = (BorderPane) fxmlLoader.load(); //attention au document fxml qui doit contenir <BoderPane>
           //root = (Parent)fxmlLoader.load(); // ok
           //anchorPane = (AnchorPane)fxmlLoader.load();
            
            Scene scene = new Scene(rootLayout);
           //Scene scene = new Scene(root);
           //Scene scene = new Scene(anchorPane);   
           //scene.getStylesheets().add("/styles/caspian-rgt.css");
        
        stage.setScene(scene);
        
     // Accès au contrôleur.
        RootLayoutController  controllerR = (RootLayoutController) fxmlLoader.getController();
        controllerR.setMainApp(this);
        
        stage.show();
            
        } catch (IOException ex) {
        	log.error(ex.toString()); 
        	//getLogger(RunningFx.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        

     // Try to load last opened person file.
        File file = getPersonFilePath();
        if (file != null) {
            loadPersonDataFromFile(file);
        }
        
    
}    

/**
     * Shows the person overview inside the root layout.
     */
    public void showPersonOverview() {
        try {
            // Load person overview.
        //    FXMLLoader loader = new FXMLLoader();
        //    loader.setLocation(TestFxml.class.getResource("view/FHMLDocument.fxml"));
        //    AnchorPane personOverview = (AnchorPane) loader.load();
            
        URL url = getClass().getResource("/view/FXMLDocument.fxml");
        // Chargement du bundle:
        ResourceBundle bundle = ResourceBundle.getBundle("astrolabe31/runningfx/strings");
        // Creation du loader.
        FXMLLoader fxmlLoader = new FXMLLoader(url, bundle);
        
        AnchorPane runningOverview = (AnchorPane) fxmlLoader.load();    

        // Set person overview into the center of root layout.
        
        rootLayout.setCenter(runningOverview);
        
        // Accès au contrôleur.
        FXMLDocumentController  controllerD =  fxmlLoader.getController();
        
        controllerD.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    private void readRunningTable(){
   	 //RunningDaoInterface jpaRunningDao = new JpaRunningDao();
       // List<Running> jpaRunning = jpaRunningDao.findRunnings();
        System.out.println();
        System.out.println("Liste des run en base avec JPA");
       // for (Running run : jpaRunning) {
       //     System.out.println(run);
       //     runData.add(run);
       // }
        
     // Add some sample data
        //String firstname, String lastname,String street,String postalCode,String city
        personData.add(new Person("Hans", "Muster"));
        personData.add(new Person("Ruth", "Mueller"));
        personData.add(new Person("Heinz", "Kurz"));
        personData.add(new Person("Cornelia", "Meier"));
        personData.add(new Person("Werner", "Meyer"));
        
        
        System.out.println("fin des run en base avec JPA");
        
   }
    
    /**
     * Opens a dialog to edit details for the specified person. If the user
     * clicks OK, the changes are saved into the provided person object and true
     * is returned.
     *
     * @param person the person object to be edited
     * @return true if the user clicked OK, false otherwise.
     */
    public boolean showPersonEditDialog(Person person) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
        	
        	  URL url = getClass().getResource("/view/PersonEditDialog.fxml");
              // Chargement du bundle:
              ResourceBundle bundle = ResourceBundle.getBundle("astrolabe31/runningfx/strings");
              // Creation du loader.
              FXMLLoader loader = new FXMLLoader(url, bundle);
        	
        	
            //FXMLLoader loader = new FXMLLoader();
            //loader.setLocation(MainApp.class.getResource("/view/PersonEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Person");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(stage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            PersonEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setPerson(person);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Returns the person file preference, i.e. the file that was last opened.
     * The preference is read from the OS specific registry. If no such
     * preference can be found, null is returned.
     * 
     * @return
     */
    public File getPersonFilePath() {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        String filePath = prefs.get("filePath", null);
        if (filePath != null) {
            return new File(filePath);
        } else {
            return null;
        }
    }

    /**
     * Sets the file path of the currently loaded file. The path is persisted in
     * the OS specific registry.
     * 
     * @param file the file or null to remove the path
     */
    public void setPersonFilePath(File file) {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        if (file != null) {
            prefs.put("filePath", file.getPath());

            // Update the stage title.
            stage.setTitle("AddressApp - " + file.getName());
        } else {
            prefs.remove("filePath");

            // Update the stage title.
            stage.setTitle("AddressApp");
        }
    }
    // XML 
    /**
     * Loads person data from the specified file. The current person data will
     * be replaced.
     * 
     * @param file
     */
    public void loadPersonDataFromFile(File file) {
        try {
            JAXBContext context = JAXBContext
                    .newInstance(PersonListWrapper.class);
            Unmarshaller um = context.createUnmarshaller();

            // Reading XML from the file and unmarshalling.
            PersonListWrapper wrapper = (PersonListWrapper) um.unmarshal(file);

            personData.clear();
            personData.addAll(wrapper.getPersons());

            // Save the file path to the registry.
            setPersonFilePath(file);

        } catch (Exception e) { // catches ANY exception
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not load data");
            alert.setContentText("Could not load data from file:\n" + file.getPath());

            alert.showAndWait();
        }
    }

    /**
     * Saves the current person data to the specified file.
     * 
     * @param file
     */
    public void savePersonDataToFile(File file) {
        try {
            JAXBContext context = JAXBContext
                    .newInstance(PersonListWrapper.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // Wrapping our person data.
            PersonListWrapper wrapper = new PersonListWrapper();
            wrapper.setPersons(personData);

            // Marshalling and saving XML to the file.
            m.marshal(wrapper, file);

            // Save the file path to the registry.
            setPersonFilePath(file);
        } catch (Exception e) { // catches ANY exception
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not save data");
            alert.setContentText("Could not save data to file:\n" + file.getPath());

            alert.showAndWait();
        }
    } 
    
    
    
    
    /**
     * Opens a dialog to show birthday statistics.
     */
    public void showBirthdayStatistics() {
        try {
        	
      	  URL url = getClass().getResource("/view/BirthdayStatistics.fxml");
          // Chargement du bundle:
          ResourceBundle bundle = ResourceBundle.getBundle("astrolabe31/runningfx/strings");
          // Creation du loader.
          FXMLLoader loader = new FXMLLoader(url, bundle);
        	
         // Load the fxml file and create a new stage for the popup.
         //   FXMLLoader loader = new FXMLLoader();
         //   loader.setLocation(MainApp.class.getResource("view/BirthdayStatistics.fxml"));
            
            
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Birthday Statistics");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(stage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the persons into the controller.
            BirthdayStatisticsController controller = loader.getController();
            controller.setPersonData(personData);

            dialogStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Returns the data as an observable list of Persons. 
     * @return
     */
    public ObservableList<Person> getPersonData() {
        return personData;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public Stage getStage() {
        return stage;
    }

	public BorderPane getRootLayout() {
		return rootLayout;
	}

	public void setRootLayout(BorderPane rootLayout) {
		this.rootLayout = rootLayout;
	}


	public void setRunData(ObservableList<Person> personData) {
		this.personData = personData;
	}

	public static Logger getLog() {
		return log;
	}
    
    
    
}
