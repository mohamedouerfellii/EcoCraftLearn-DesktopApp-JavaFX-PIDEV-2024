module tn.SIRIUS {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens tn.SIRIUS.EcoCraftLearning to javafx.fxml;
    opens tn.SIRIUS.controller.tutors to javafx.fxml;
    exports tn.SIRIUS.EcoCraftLearning;
}
