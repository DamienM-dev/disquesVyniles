module fr.cda.disquesvyniles.disquesvyniles {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens fr.cda.disquesvyniles.disquesvyniles to javafx.fxml;
    exports fr.cda.disquesvyniles.disquesvyniles;
}