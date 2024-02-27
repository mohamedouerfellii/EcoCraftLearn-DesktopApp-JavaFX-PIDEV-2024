package tn.SIRIUS.controller.students;

import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import org.controlsfx.control.Rating;
import tn.SIRIUS.entities.Productsevaluation;

public class ItemReviewProduct {

    @FXML
    private Text CommentProduct;

    @FXML
    private HBox HboxContainerReview;

    @FXML
    private Rating RatingProduct;

    public void setData(Productsevaluation productsevaluation) {
        RatingProduct.setRating(productsevaluation.getRate());
        CommentProduct.setText(productsevaluation.getReview());
    }



}


