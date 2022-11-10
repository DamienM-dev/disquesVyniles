package fr.cda.disquesvyniles;


import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.FileChooser;


import fr.cda.scol.Fnac;
import fr.cda.scol.CultureFactory;
import fr.cda.scol.VinyleCorner;
import fr.cda.scol.Discogs;
import fr.cda.scol.Leboncoin;
import fr.cda.scol.mesvinyles;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.*;
import java.text.MessageFormat;
import java.util.Properties;

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
    @FXML
    private MenuItem enregistrerBdd;
    @FXML
    private MenuItem SceneConfigurationBdd;

    @FXML

    private MenuItem modeEmploi;



    @FXML
    /** recherche des infos pour le scrapping et incrementation du text area **/
    protected void RechercheInfo() throws IOException {

        String rechercherTitre = champsTitre.getText();
        String rechercheGenre = champsGenre.getValue().toString();
        String rechercherDate = champsDate.getValue().toString().substring(0, 4);
        String recherchePrixMin = champsMin.getText();
        String recherchePrixMinPrixMin = champsMax.getText();
        boolean rechercheDiscogs = checkboxDiscogs.isSelected();
        boolean rechercheFnac = checkboxFnac.isSelected();
        boolean rechercheVinylCorner = checkboxVinylcorner.isSelected();
        boolean rechercheLeboncoin = checkboxLeboncoin.isSelected();
        boolean rechercheMesVinyles = checkboxMesvinyles.isSelected();
        boolean rechercheCultureFactory = checkboxCulturefactory.isSelected();
        String res = "";

        if(rechercheFnac) {

            res += Fnac.FnacScrapping(rechercherTitre);
        }

       else if(rechercheCultureFactory) {
            res += CultureFactory.CultureFactoryScrapping(rechercherTitre);
        }
        else if(rechercheVinylCorner) {
            res += VinyleCorner.VinylCornerScrapping(rechercherTitre);
        }
        else if(rechercheDiscogs) {
            res += Discogs.DiscogsScrapping(String.valueOf(rechercherTitre));
        }
        else if(rechercheLeboncoin) {
            res += Leboncoin.ScrapLeboncoin(rechercherTitre);
        } else if (rechercheMesVinyles) {

            res += mesvinyles.ScrapingMesvinyles(rechercherTitre);

        }
        champsResultat.setText(res);
    }
public void ouvrirModeEmploi() {
    FileChooser fileChooser = new FileChooser();
    FileChooser.ExtensionFilter extFilter =
            new FileChooser.ExtensionFilter("PDF files (*.pdf)", "*.pdf");
    fileChooser.getExtensionFilters().add(extFilter);
    fileChooser.setInitialDirectory(new File("./guides/scrapping.pdf"));
    File selectedFile = fileChooser.showSaveDialog(null);
    String path = selectedFile.getAbsolutePath();
}

    @FXML
    /** enregistrement du fichier de la recherche dans un dossier **/

    public void EnregistrementFichier() throws IOException {
        /* Si text area vide, j'affiche un message */
        if (enregistrerBdd.getText().equals("")){
            enregistrerBdd.setText("Il n'y a aucun article à enregistrer");
        }else{
            /* Sinon creation du fichier */
            String rechercheFichier = enregistrerBdd.getText();
            try {
                FileChooser fileChooser = new FileChooser();
                FileChooser.ExtensionFilter extFilter =
                        new FileChooser.ExtensionFilter("TEXT files (*.txt)", "*.txt");
            fileChooser.getExtensionFilters().add(extFilter);
                fileChooser.setInitialDirectory(new File("./ResultatDeRecherches"));
                fileChooser.setTitle("");
                File selectedFile = fileChooser.showSaveDialog(null);
                String path = selectedFile.getAbsolutePath();

                PrintWriter ecrire = new PrintWriter(new BufferedWriter
                        (new FileWriter(path)));
                ecrire.println(champsResultat.getText());
                ecrire.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /** reset des champs **/
    public void effacerChamps() {

        champsTitre.setText("");
        champsGenre.setValue(null);
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

    /** Lancement de la pop up de config bdd **/

    public void SceneConfigurationBdd() throws IOException {


        ConfigurationBdd();
    }


    /** configuration du bouton quitter **/

    public void quitterFichier() {

        Platform.exit();
    }

    public void onPopupClick() throws IOException {
        PopupScene();
    }

    /** popup de l envoi de email **/
    public void PopupScene() throws IOException {

        Stage popupwindow=new Stage();

        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("Envoi courriel");
        Label label1= new Label("saisie du courriel");
        Label label2= new Label("Veuillez saisir l'email de l'expéditeur");
        TextField textField  = new TextField();
        Button button1= new Button("Envoyer");
        Button button2= new Button("Annuler");
        button1.setOnAction(e -> {
            MailSender.send(textField.getText(),champsResultat.getText());
            popupwindow.close();
        });

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
    /** lancement popup validation bdd **/
    public void PopUpValidationBDD() throws IOException {
        PopupSceneValidationBDD();
    }

    /** creation de la popup validation bdd **/
    public void PopupSceneValidationBDD() throws IOException {

        Stage popupwindow=new Stage();

        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("Transmission BDD");
        Label label1= new Label("Transmission des données à la base de données");
        Label label2= new Label("Cliquez sur Valider pour lancer la transmission: ");
        Button button1= new Button("Valider");
        Button button2= new Button("Annuler");
        button2.setOnAction(e -> popupwindow.close());
        VBox layout= new VBox(10);
        layout.getChildren().addAll(label1);
        layout.getChildren().addAll(label2);
        layout.getChildren().addAll(button1);
        layout.getChildren().addAll(button2);
        layout.setAlignment(Pos.CENTER);
        Scene scene1= new Scene(layout, 300, 250);
        popupwindow.setScene(scene1);
        popupwindow.showAndWait();
    }
    /** ihm de la config de bdd **/

    public void ConfigurationBdd() throws  IOException {

        try {
            Stage stage1=new Stage();

            FXMLLoader fxmlLoader1 = new FXMLLoader(VinyleApplication.class.getResource("ihmBDD.fxml"));
            Scene scene1 = new Scene(fxmlLoader1.load(), 700, 600);
            stage1.setTitle("Paramétres de la base de donnée");
            stage1.setScene(scene1);
            stage1.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


