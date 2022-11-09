package fr.cda.disquesvyniles;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;




import sendinblue.*;
import sendinblue.auth.*;
import sibModel.*;
import sibApi.AccountApi;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.AddressException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;

import java.io.IOException;

public class VinyleApplication extends Application {
    @Override
    /** lancement de la IHM accueil **/

    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(VinyleApplication.class.getResource("ihmAccueil.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 500);
        stage.setTitle("Accueil");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws MessagingException {
        launch();

}
}
