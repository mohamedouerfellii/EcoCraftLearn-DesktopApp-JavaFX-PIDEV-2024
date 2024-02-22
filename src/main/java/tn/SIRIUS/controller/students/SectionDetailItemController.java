package tn.SIRIUS.controller.students;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import tn.SIRIUS.entities.Section;

public class SectionDetailItemController {
    @FXML
    private Label sectionTitle;
    @FXML
    private Text sectionDescription;
    private Section section;
    private CoursesMainPageController coursesMainPageController;
    public void setData(Section section,CoursesMainPageController controller){
        sectionTitle.setText(section.getTitle());
        sectionDescription.setText("\" "+section.getDescription()+" \"");
        this.section = section;
        coursesMainPageController = controller;
    }

}
