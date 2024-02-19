package tn.SIRIUS.controller.students;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import tn.SIRIUS.entities.Course;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CoursesMainPageController implements Initializable {
    @FXML
    private GridPane coursesGridPane;
    private List<Course> courses;
    @Override
    public void initialize(URL url, ResourceBundle rb){
    }
    public void getCourses(List<Course> courses){
        this.courses = courses;
        int column = 0;
        int row = 1;
        coursesGridPane.getChildren().clear();
        for(Course course : courses){
            System.out.println("teeest");
            try{
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/gui/students/coursesMainPageItem.fxml"));
                Parent root = fxmlLoader.load();
                CoursesMainPageItemController controller = fxmlLoader.getController();
                controller.setData(course,this);
                if(column == 4){
                    column = 0;
                    row++;
                }
                coursesGridPane.add(root,column++,row);
            }catch(IOException e){
                System.out.println(e.getMessage());
            }
        }
    }
    public void setCourses(){

    }
}
