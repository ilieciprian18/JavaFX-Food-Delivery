package MainApplicationUI;

import Classes.Review;
import Services.SQLConnect;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Vector;
import java.util.prefs.Preferences;

public class ReviewPageController {

    @FXML
    private AnchorPane spaceShowReviews;

    public void ReviewPageController(){

    }



    public void initialize() throws SQLException {

        Preferences userPreferences = Preferences.userRoot();
        String IdRestaurant = userPreferences.get("idRestaurant","none");
        SQLConnect SQL = new SQLConnect();

        Vector<String> selectedRestaurantInfo = new Vector<>();
        selectedRestaurantInfo = SQL.getRestaurantInfo(IdRestaurant);

        int paneColor = 0;

        Label restaurantNameShow = new Label();
        if( selectedRestaurantInfo.elementAt(0).equals("KFC"))
        {
            restaurantNameShow.setText("Kentucky Fried Chicken");
        }
        else {
            restaurantNameShow.setText(selectedRestaurantInfo.elementAt(0));
        }
        restaurantNameShow.setAlignment(Pos.TOP_CENTER);
        restaurantNameShow.setFont(new Font("System", 16));
        restaurantNameShow.setStyle("-fx-font-weight: bold");
        restaurantNameShow.setPrefWidth(176);
        restaurantNameShow.setPrefHeight(25);
        restaurantNameShow.setLayoutX(14);
        restaurantNameShow.setLayoutY(53);
        restaurantNameShow.setTextFill(Paint.valueOf("#000000"));
        spaceShowReviews.getChildren().add(restaurantNameShow);

        Label restaurantCityShow = new Label();
        restaurantCityShow.setText(selectedRestaurantInfo.elementAt(2));
        restaurantCityShow.setAlignment(Pos.TOP_CENTER);
        restaurantCityShow.setFont(new Font("System", 16));
        restaurantCityShow.setStyle("-fx-font-style: italic");
        restaurantCityShow.setPrefWidth(90);
        restaurantCityShow.setPrefHeight(25);
        restaurantCityShow.setLayoutX(58);
        restaurantCityShow.setLayoutY(78);
        restaurantCityShow.setTextFill(Paint.valueOf("#000000"));
        spaceShowReviews.getChildren().add(restaurantCityShow);

        int numberReviews = SQL.getNumberReviewsRestaurant(IdRestaurant);

        Label restaurantReviewsCount = new Label();
        restaurantReviewsCount.setText(numberReviews + " Reviews");
        restaurantReviewsCount.setAlignment(Pos.TOP_CENTER);
        restaurantReviewsCount.setFont(new Font("System", 16));
        restaurantReviewsCount.setStyle("-fx-font-weight: bold");
        restaurantReviewsCount.setPrefWidth(102);
        restaurantReviewsCount.setPrefHeight(25);
        restaurantReviewsCount.setLayoutX(56);
        restaurantReviewsCount.setLayoutY(175);
        restaurantReviewsCount.setTextFill(Paint.valueOf("#000000"));
        spaceShowReviews.getChildren().add(restaurantReviewsCount);

        float restaurantReviewRating = 0;
        restaurantReviewRating = Float.valueOf(selectedRestaurantInfo.elementAt(1));
        System.out.println(restaurantReviewRating);


        Label restaurantRatingShow = new Label();
        restaurantRatingShow.setText("(" +restaurantReviewRating + ")");
        restaurantRatingShow.setAlignment(Pos.TOP_CENTER);
        restaurantRatingShow.setFont(new Font("System", 16));
        restaurantRatingShow.setPrefWidth(31);
        restaurantRatingShow.setPrefHeight(25);
        restaurantRatingShow.setLayoutX(92);
        restaurantRatingShow.setLayoutY(200);
        restaurantRatingShow.setTextFill(Paint.valueOf("#000000"));
        spaceShowReviews.getChildren().add(restaurantRatingShow);

        int countFilled = 0;
        int needToFill = (int)restaurantReviewRating;
        int layoutStars = 34;
        if( restaurantReviewRating - (int)restaurantReviewRating > 0.5)
        {
            needToFill++;
        }

        for( int i =0; i < 5; i++)
        {
            FontAwesomeIcon iconStar = new FontAwesomeIcon();
            iconStar.setGlyphName("STAR");
            iconStar.setSize("2em");
            if( countFilled < needToFill)
            {
                iconStar.setFill(Paint.valueOf("#000000"));
                countFilled++;
            }
            else {
                iconStar.setFill(Paint.valueOf("#ffffff"));
            }
            iconStar.setStroke(Paint.valueOf("#000000"));
            iconStar.setStrokeWidth(1);
            iconStar.setStrokeType(StrokeType.CENTERED);
            iconStar.setLayoutX(layoutStars);
            iconStar.setLayoutY(246);
            spaceShowReviews.getChildren().add(iconStar);

            layoutStars= layoutStars + 31;

        }

        Label restaurantScheduleShow = new Label();
        restaurantScheduleShow.setText("Schedule");
        restaurantScheduleShow.setAlignment(Pos.CENTER_LEFT);
        restaurantScheduleShow.setFont(new Font("System", 16));
        restaurantScheduleShow.setStyle("-fx-font-weight: bold");
        restaurantScheduleShow.setLayoutX(68);
        restaurantScheduleShow.setLayoutY(311);
        restaurantScheduleShow.setTextFill(Paint.valueOf("#000000"));
        spaceShowReviews.getChildren().add(restaurantScheduleShow);

        Vector<String> scheduleDays = new Vector<>();
        scheduleDays.add("Luni");
        scheduleDays.add("Marti");
        scheduleDays.add("Miercuri");
        scheduleDays.add("Joi");
        scheduleDays.add("Vineri");
        scheduleDays.add("Sambata");
        scheduleDays.add("Duminica");

        int scheduleDayLayout = 359;

        for(int i = 0; i< scheduleDays.size();i++)
        {
            Label scheduleDayShow = new Label();
            scheduleDayShow.setText(scheduleDays.elementAt(i));
            scheduleDayShow.setAlignment(Pos.TOP_LEFT);
            scheduleDayShow.setFont(new Font("System", 14));
            scheduleDayShow.setPrefWidth(70);
            scheduleDayShow.setPrefHeight(20);
            scheduleDayShow.setLayoutX(30);
            scheduleDayShow.setLayoutY(scheduleDayLayout);
            scheduleDayShow.setTextFill(Paint.valueOf("#000000"));
            spaceShowReviews.getChildren().add(scheduleDayShow);


            //modify hours
            Label scheduleHoursShow = new Label();
            scheduleHoursShow.setText("10:00 - 22:00");
            scheduleHoursShow.setAlignment(Pos.CENTER_LEFT);
            scheduleHoursShow.setFont(new Font("System", 14));
            scheduleHoursShow.setPrefWidth(80);
            scheduleHoursShow.setPrefHeight(20);
            scheduleHoursShow.setLayoutX(98);
            scheduleHoursShow.setLayoutY(scheduleDayLayout);
            scheduleHoursShow.setTextFill(Paint.valueOf("#000000"));
            spaceShowReviews.getChildren().add(scheduleHoursShow);

            scheduleDayLayout = scheduleDayLayout + 20;

        }

        Button backButton = new Button();
        backButton.setText("Back to Restaurant");
        backButton.setPrefWidth(117);
        backButton.setPrefHeight(27);
        backButton.setLayoutX(45);
        backButton.setLayoutY(560);
        backButton.getStylesheets().add(getClass().getResource("Images/buttonStyle.css").toExternalForm());
        backButton.setTextFill(Paint.valueOf("white"));
        backButton.setFont(new Font("System",12));
        backButton.setOnAction(this::goBackToRestaurant);
        spaceShowReviews.getChildren().add(backButton);

        //implementam reviews
        Vector<Review> reviewsRestaurant = new Vector<>();
        reviewsRestaurant = SQL.getReviewsRestaurant(IdRestaurant);
        System.out.println("reviws " + reviewsRestaurant.size());

        double layoutVerticalReviews = 0; // max 4
        int countVerticalReviews = 0;
        double layout = 0;
        int reviewSizeShow =  reviewsRestaurant.size();
        int actualDisplayNumber = 0;

        /*
        if ( reviewSizeShow > 8)
        {
            reviewSizeShow = 8;
        }

         */


        System.out.println("Numarul este de: "  + reviewSizeShow);
        for(int i = 0; i < reviewSizeShow;i++) {
            String descCopyReview = reviewsRestaurant.elementAt(i).getReviewDescription().replace(" ", "");
            System.out.println(descCopyReview);
            if (descCopyReview.length() > 0) {
                System.out.println("intra");
                actualDisplayNumber++;
                Pane reviewPane = new Pane();
                if (paneColor % 2 == 0) {
                    reviewPane.setStyle("-fx-background-color:  #D3D3D3");
                } else {
                    reviewPane.setStyle("-fx-background-color:  #E8E8E8");
                }


                reviewPane.setPrefWidth(323);
                reviewPane.setPrefHeight(154.25);
                countVerticalReviews++;
                System.out.println("Count este: " + countVerticalReviews);
                if (countVerticalReviews <= 4) {
                    reviewPane.setLayoutX(204);
                    reviewPane.setLayoutY(layoutVerticalReviews);
                    layoutVerticalReviews = layoutVerticalReviews + 154.25;
                    if (paneColor % 2 != 0) {
                        reviewPane.setStyle("-fx-background-color:  #D3D3D3");
                    } else {
                        reviewPane.setStyle("-fx-background-color:  #E8E8E8");
                    }

                } else {

                    reviewPane.setLayoutX(527);
                    reviewPane.setLayoutY(layout);
                    layout = layout + 154.25;
                }
                spaceShowReviews.getChildren().add(reviewPane);

                Label usernameReviewShow = new Label();
                usernameReviewShow.setText(reviewsRestaurant.elementAt(i).getUsernameReview());
                usernameReviewShow.setAlignment(Pos.CENTER_LEFT);
                usernameReviewShow.setFont(new Font("System", 16));
                usernameReviewShow.setStyle("-fx-font-weight: bold");
                usernameReviewShow.setLayoutX(14);
                usernameReviewShow.setLayoutY(14);
                usernameReviewShow.setTextFill(Paint.valueOf("#000000"));
                reviewPane.getChildren().add(usernameReviewShow);

                Text reviewTextShow = new Text();
                reviewTextShow.setText(reviewsRestaurant.elementAt(i).getReviewDescription());
                reviewTextShow.setFont(new Font("System",12));
                reviewTextShow.setLayoutX(16);
                reviewTextShow.setLayoutY(60);
                reviewTextShow.setWrappingWidth(291.13671875);
                reviewPane.getChildren().add(reviewTextShow);

                int numberReviewStars = reviewsRestaurant.elementAt(i).getReviewRate();
                int colorCountStar = 0;
                int layourStarsReview = 162;
                for(int index = 0; index <5; index++)
                {
                    FontAwesomeIcon iconStarReview = new FontAwesomeIcon();
                    iconStarReview.setGlyphName("STAR");
                    iconStarReview.setSize("1.5em");
                    if(colorCountStar < numberReviewStars)
                    {
                        iconStarReview.setFill(Paint.valueOf("#000000"));
                        colorCountStar++;
                    }
                    else {
                        iconStarReview.setFill(Paint.valueOf("#ffffff"));
                    }
                    iconStarReview.setStroke(Paint.valueOf("#000000"));
                    iconStarReview.setStrokeWidth(1);
                    iconStarReview.setStrokeType(StrokeType.CENTERED);
                    iconStarReview.setLayoutX(layourStarsReview);
                    iconStarReview.setLayoutY(32);
                    reviewPane.getChildren().add(iconStarReview);

                    layourStarsReview = layourStarsReview + 30;

                }
                paneColor++;
            }
            if(actualDisplayNumber == 8)
            {
                break;
            }
        }




    }

    public void goBackToRestaurant(ActionEvent event){

        MainFoodDeliveryApplication m = new MainFoodDeliveryApplication();
        try {
            m.changeScene("UserRestaurantOrder.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void goToOrder(ActionEvent event) throws IOException {

        MainFoodDeliveryApplication m = new MainFoodDeliveryApplication();
        m.changeScene("userHome.fxml");

    }



}
