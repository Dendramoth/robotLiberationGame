package com.mycompany.robotliberation;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainApp extends Application {
    
    public LoadAllImages loadAllImages = new LoadAllImages();

    @Override
    public void start(Stage stage) throws Exception {
        VBox mainPanel = new VBox();
        
        GameMainInfrastructure gameMainInfrastructure = new GameMainInfrastructure(stage, mainPanel);
        gameMainInfrastructure.beginGameLoop();

        Scene scene = new Scene(mainPanel);
        scene.getStylesheets().add("/styles/Styles.css");

        stage.setTitle("Robot Liberation Day");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
