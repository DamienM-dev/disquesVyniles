module fr.cda.disquesvyniles {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires htmlunit;
    requires sib.api.v3.sdk;
    requires javax.mail.api;
    requires dotenv.java;


    opens fr.cda.disquesvyniles to javafx.fxml;
    exports fr.cda.disquesvyniles;
}