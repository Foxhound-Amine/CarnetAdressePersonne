/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astrolabe31.runningfx;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import astrolabe31.runningfx.controller.FXMLDocumentController;
import astrolabe31.runningfx.controller.RootLayoutController;




/**
 *
 * @author Administrator
 */
public class RunningFx extends Application {
	private static final Logger log = LoggerFactory.getLogger(RunningFx.class);
    private Stage stage;
    private BorderPane rootLayout;
    //private Parent root; 
    //private AnchorPane anchorPane;
    //private EntityManagerFactory emf ;
    //public  EntityManager em;
    
    
    
    
    @Override
    public void start(Stage stage) throws Exception {
       this.stage = stage;
      // this.stage.setTitle("run");
       
       initRootLayout();
      // readRunningTable();
       showRunningOverview();
        
    }
    
    public void initRootLayout(){
             //modif code original rgt
        // Localisation du fichier FXML.
        URL url = getClass().getResource("/view/RootLayout.fxml");
        // Chargement du bundle:
        ResourceBundle bundle = ResourceBundle.getBundle("astrolabe31/runningfx/strings");
        // Creation du loader.
        FXMLLoader fxmlLoader = new FXMLLoader(url, bundle);
                 
       // Accès au contrôleur.
       // RootLayoutController  controllerR = (RootLayoutController) fxmlLoader.getController();
        
       
        try {
          //rootLayout = new BorderPane();
          rootLayout = (BorderPane) fxmlLoader.load(); //attention au document fxml qui doit contenir <BoderPane>
           //root = (Parent)fxmlLoader.load(); // ok
           //anchorPane = (AnchorPane)fxmlLoader.load();
            
            
            Scene scene = new Scene(rootLayout);
           //Scene scene = new Scene(root);
           //Scene scene = new Scene(anchorPane);   
           scene.getStylesheets().add("/styles/caspian-rgt.css");
        
        stage.setScene(scene);
        stage.show();
            
        } catch (IOException ex) {
        	log.error(ex.toString()); 
        	//getLogger(RunningFx.class.getName()).log(Level.SEVERE, null, ex);
        }

  
        
    
}    

/**
     * Shows the person overview inside the root layout.
     */
    public void showRunningOverview() {
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
        
        controllerD.setMainApp(null);
        
        
        } catch (IOException e) {
            e.printStackTrace();
        }
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

	


	public static Logger getLog() {
		return log;
	}
    
    
    
}
