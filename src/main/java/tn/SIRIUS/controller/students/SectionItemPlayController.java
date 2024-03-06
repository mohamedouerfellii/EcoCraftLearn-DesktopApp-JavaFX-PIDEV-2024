package tn.SIRIUS.controller.students;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import tn.SIRIUS.entities.Section;

import java.util.Objects;

public class SectionItemPlayController {
    @FXML
    private Text sectionTitle;
    @FXML
    private ImageView imgDoneLock;
    private CoursesMainPageController mainPageController;
    private Section section;
    private boolean isFinished;
    private boolean isFirst;
    public void setData(Section section, CoursesMainPageController controller,boolean isFinished,boolean isFirst){
        sectionTitle.setText(section.getTitle());
        if(!isFinished && !isFirst)
            imgDoneLock.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icons/dark/lock-2-fill.png"))));
        else if(isFinished)
            imgDoneLock.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icons/dark/checkbox-circle-fillgg.png"))));
        this.isFinished = isFinished;
        this.isFirst = isFirst;
        this.section = section;
        this.mainPageController = controller;
    }
    @FXML
    public void changeSection(MouseEvent event){
        if((!isFinished && isFirst) || (isFinished))
                mainPageController.setSectionPlay(section,isFinished);
    }
}
