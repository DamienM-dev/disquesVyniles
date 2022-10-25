package fr.cda.disquesvyniles.disquesvyniles;

import javafx.fxml.FXML;
import javafx.scene.control.*;

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

    public void effacerChamps() {

        champsTitre.setText("");
       champsGenre.setValue(null);
        champsDate.setValue(null);
        champsMin.setText("");
        champsMax.setText("");
        checkboxDiscogs.setSelected(false);
        checkboxFnac.setSelected(false);
        checkboxVinylcorner.setSelected(false);
        checkboxLeboncoin.setSelected(false);
        checkboxMesvinyles.setSelected(false);
        checkboxCulturefactory.setSelected(false);

    }
}