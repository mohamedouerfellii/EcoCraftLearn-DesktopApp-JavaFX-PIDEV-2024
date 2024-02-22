package tn.SIRIUS.controller.students;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import tn.SIRIUS.entities.Section;

public class SectionItemPlayController {
    @FXML
    private Text sectionTitle;
    private CoursesMainPageController mainPageController;
    private Section section;
    public void setData(Section section, CoursesMainPageController controller){
        sectionTitle.setText(section.getTitle());
        this.section = section;
        this.mainPageController = controller;
    }
    @FXML
    public void changeSection(MouseEvent event){
        mainPageController.setSectionPlay(section);
    }
}
