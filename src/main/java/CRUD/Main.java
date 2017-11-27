package CRUD;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;

public class Main extends Application {



    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader loader = new FXMLLoader();

//        String filePathString = "src/main/java/CRUD/Controller.java";
//
//        File f = new File(filePathString);
//        if(f.exists()) {
//            System.out.println("File exists");
//            System.out.println(f.getAbsoluteFile());
//        }
//        else
//            System.out.println("No file");
//
        FileInputStream fileInputStream = new FileInputStream(new File("src/main/java/CRUD/bnkseekCRUD.fxml"));
        Parent root = loader.load(fileInputStream);


        primaryStage.setTitle("BNKSEEK CRUD");
        primaryStage.setScene(new Scene(root, 1200, 600));
        primaryStage.setOnCloseRequest(event -> {Platform.exit();System.exit(0);});
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }


}
