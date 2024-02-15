package tn.SIRIUS.controller.shared;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import tn.SIRIUS.controller.tutors.CoursesMainPageController;
import tn.SIRIUS.entities.Section;

import java.net.URL;
import java.util.ResourceBundle;

public class SectionsDetailsTitleItemController implements Initializable {
    @FXML
    private Text sectionTitle;
    @FXML
    private AnchorPane sectionTitleContainer;
    private CoursesMainPageController coursesMainPageController;
    private int idSection;
    private Section sectionItem;
    @Override
    public void initialize(URL url, ResourceBundle rb){

    }
    public void setData(Section section){
        idSection = section.getIdSection();
        sectionTitle.setText(section.getTitle());
        sectionItem = section;
    }
    public void setCoursesMainPageController(CoursesMainPageController controller){
        coursesMainPageController = controller;
    }
    @FXML
    public void changeSection(MouseEvent event){
        coursesMainPageController.setSectionPlay(sectionItem);
    }
}
