package fr.cda.disquesvyniles.disquesvyniles;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class VinyleApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(VinyleApplication.class.getResource("ihmAccueil.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 500);
        stage.setTitle("Accueil");
        stage.setScene(scene);
        stage.show();
    }

    public void start1(Stage stage1) throws  IOException {
        FXMLLoader fxmlLoader1 = new FXMLLoader(VinyleApplication.class.getResource("ihmBDD.fxml"));
        Scene scene1 = new Scene(fxmlLoader1.load(), 700, 500);
        stage1.setTitle("Paramétres de la base de donnée");
        stage1.setScene(scene1);
        stage1.show();
    }


    public static void main(String[] args) {
        launch();
    }
}