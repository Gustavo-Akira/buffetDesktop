package gustavoakira.javafx.buffet;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;

    @Override
    public void start(Stage stage) throws IOException {
    	primaryStage = stage;
    	primaryStage.setTitle("Buffet");
    	initRootLayout();
    	setInitScene();
    }
    
    private void setInitScene() {
    	try {
    		AnchorPane parent = (AnchorPane)loadFXML("views/PartyList");
    		
    		rootLayout.setCenter(parent);
    		
    	}catch(IOException ex) {
    		ex.printStackTrace();
    	}
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    
    public void initRootLayout() {
    	 try {
    		 rootLayout = (BorderPane) loadFXML("Main");
    		 Scene scene = new Scene(rootLayout);
    		 primaryStage.setScene(scene);
    		 primaryStage.show();
    	 } catch (IOException e) {
             e.printStackTrace();
         }
    }

    public static void main(String[] args) {
        launch();
    }

}