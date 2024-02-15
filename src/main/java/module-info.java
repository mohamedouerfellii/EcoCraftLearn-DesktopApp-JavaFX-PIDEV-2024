module tn.SIRIUS {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.sql;

    opens tn.SIRIUS.EcoCraftLearning to javafx.fxml;
    opens tn.SIRIUS.controller.shared to javafx.fxml;
    opens tn.SIRIUS.controller.tutors to javafx.fxml,javafx.media;
    exports tn.SIRIUS.EcoCraftLearning;
}
