package fr.cda.disquesvyniles;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;



public class BddController {

    @FXML
    private Button fermer;



    public void quitterFichier() {

        Platform.exit();
    }
}
