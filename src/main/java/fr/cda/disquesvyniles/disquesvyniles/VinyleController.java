package fr.cda.disquesvyniles.disquesvyniles;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.MessageFormat;

public class VinyleController {
    @FXML
    private Button effacerButton;

    @FXML
    private Button rechercherVinyle;

    @FXML
    private TextField champsTitre;

    @FXML
    private ComboBox champsGenre;

    @FXML
    private DatePicker champsDate;

    @FXML
    private TextField champsMin;

    @FXML
    private TextField champsMax;

    @FXML
    private CheckBox checkboxDiscogs;

    @FXML
    private CheckBox checkboxFnac;

    @FXML
    private CheckBox checkboxVinylcorner;

    @FXML
    private CheckBox checkboxLeboncoin;

    @FXML
    private CheckBox checkboxMesvinyles;

    @FXML
    private CheckBox checkboxCulturefactory;

    @FXML
    private TextArea champsResultat;

    @FXML
    private MenuItem EnvoiCourriel;



    public void effacerChamps() {

        champsTitre.setText("");
       champsGenre.setValue(null) ;
        champsDate.setValue(null);
        champsMin.setText("");
        champsMax.setText("");
        champsResultat.setText("");
        checkboxDiscogs.setSelected(false);
        checkboxFnac.setSelected(false);
        checkboxVinylcorner.setSelected(false);
        checkboxLeboncoin.setSelected(false);
        checkboxMesvinyles.setSelected(false);
        checkboxCulturefactory.setSelected(false);
    }

    public void quitterFichier() {

        Platform.exit();
    }

    public void onPopupClick() throws IOException {
        PopupScene();
    }

    public void PopupScene() throws IOException {

        Stage popupwindow=new Stage();

        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("Envoi courriel");
        Label label1= new Label("saisie du courriel");
        Label label2= new Label("Veuillez saisir l'email de l'expéditeur");
        TextField textField = new TextField();
        Button button1= new Button("Envoyer");
        Button button2= new Button("Annuler");
        button1.setOnAction(e -> popupwindow.close());
        button2.setOnAction(e -> popupwindow.close());
        VBox layout= new VBox(10);
        layout.getChildren().addAll(label1);
        layout.getChildren().addAll(label2);
        layout.getChildren().addAll(textField);
        layout.getChildren().addAll(button1);
        layout.getChildren().addAll(button2);
        layout.setAlignment(Pos.CENTER);
        Scene scene1= new Scene(layout, 300, 250);
        popupwindow.setScene(scene1);
        popupwindow.showAndWait();

    }
}
