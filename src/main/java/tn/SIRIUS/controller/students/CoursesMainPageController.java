package tn.SIRIUS.controller.students;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import tn.SIRIUS.entities.Course;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class CoursesMainPageController implements Initializable {
    @FXML
    private GridPane coursesGridPane;
    private List<Course> courses;
    @FXML
    private Button allCoursesBtn;
    @FXML
    private Button newestCoursesBtn;
    @FXML
    private Button topRatedCoursesBtn;
    @FXML
    private Button mostPopularCoursesBtn;
    @Override
    public void initialize(URL url, ResourceBundle rb){
        allCoursesBtn.setStyle("-fx-opacity: 1;");
    }
    public void loadCoursesItem(int column,int row,Course course) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/gui/students/coursesMainPageItem.fxml"));
        Parent root = fxmlLoader.load();
        CoursesMainPageItemController controller = fxmlLoader.getController();
        controller.setData(course,this);
        coursesGridPane.add(root,column,row);
    }
    public void getCourses(List<Course> courses){
        if(this.courses == null) this.courses = courses;
        int column = 0;
        int row = 1;
        coursesGridPane.getChildren().clear();
        for(Course course : courses){
            try{
                if(column == 2){
                    column = 0;
                    row++;
                }
                loadCoursesItem(column++,row,course);
            }catch(IOException e){
                System.out.println(e.getMessage());
            }
        }
    }
    @FXML
    public void allCoursesBtnClicked(MouseEvent event){
        allCoursesBtn.setStyle("-fx-opacity: 1;");
        newestCoursesBtn.setStyle("-fx-opacity: 0.6;");
        topRatedCoursesBtn.setStyle("-fx-opacity: 0.6;");
        mostPopularCoursesBtn.setStyle("-fx-opacity: 0.6;");
        getCourses(courses);
    }
    @FXML
    public void newestCoursesBtnClicked(MouseEvent event) {
        allCoursesBtn.setStyle("-fx-opacity: 0.6;");
        newestCoursesBtn.setStyle("-fx-opacity: 1;");
        topRatedCoursesBtn.setStyle("-fx-opacity: 0.6;");
        mostPopularCoursesBtn.setStyle("-fx-opacity: 0.6;");
        getCourses(courses.stream().sorted((c1, c2) -> c2.getPostedDate().compareTo(c1.getPostedDate())).collect(Collectors.toList()));
    }
    @FXML
    public void topRatedCoursesBtnClicked(MouseEvent event){
        allCoursesBtn.setStyle("-fx-opacity: 0.6;");
        newestCoursesBtn.setStyle("-fx-opacity: 0.6;");
        topRatedCoursesBtn.setStyle("-fx-opacity: 1;");
        mostPopularCoursesBtn.setStyle("-fx-opacity: 0.6;");
    }
    @FXML
    public void mostPopularCoursesBtnClicked(MouseEvent event){
        allCoursesBtn.setStyle("-fx-opacity: 0.6;");
        newestCoursesBtn.setStyle("-fx-opacity: 0.6;");
        topRatedCoursesBtn.setStyle("-fx-opacity: 0.6;");
        mostPopularCoursesBtn.setStyle("-fx-opacity: 1;");
    }
}
