module tn.SIRIUS {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens tn.SIRIUS.EcoCraftLearning to javafx.fxml;

    opens tn.SIRIUS.controller.students to javafx.fxml;



    exports tn.SIRIUS.EcoCraftLearning;
    exports tn.SIRIUS.controller.students;

    exports tn.SIRIUS.controller.admins;
    opens tn.SIRIUS.controller.admins to javafx.fxml;

}