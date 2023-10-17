package com.example.demo;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class AResults extends Application {
    @Override
    public void start(Stage primaryStage) {

        BorderPane root = new BorderPane();
        HBox header = createHeader();
        GridPane gridPane = createGridPane();


        // Add images to the boxes
        ImageView image1 = createClickableImageView("C:\\Users\\Nivedha\\Desktop\\Amrita\\Sem 2\\Java\\Projects Final\\image1.jpg","Scene 1");
        ImageView image2 = createClickableImageView1("C:\\Users\\Nivedha\\Desktop\\Amrita\\Sem 2\\Java\\Projects Final\\image2.jpg","Scene 2");
        ImageView image3 = createClickableImageView2("C:\\Users\\Nivedha\\Desktop\\Amrita\\Sem 2\\Java\\Projects Final\\image3.jpg","Scene 3");
        ImageView image4 = createClickableImageView3("C:\\Users\\Nivedha\\Desktop\\Amrita\\Sem 2\\Java\\Projects Final\\image4.jpg","Scene 4");

        image1.prefHeight(400);
        image1.maxWidth(30);
        image2.maxHeight(350);
        image2.prefWidth(350);
        image3.maxHeight(350);
        image3.prefWidth(350);
        image4.prefWidth(350);
        image4.maxHeight(350);

        StackPane pane1 = createPaddedStackPane(image1,new Insets(10, 0, 0, 0),"Position - I"); // Add left padding to image 1
        StackPane pane2 = createPaddedStackPane(image2,new Insets(10, 0, 0, 0),"Position - II"); // Add left padding to image 2
        StackPane pane3 = createPaddedStackPane(image3, new Insets(10, 0, 0, 0),"Position - III"); // Add right padding to image 3
        StackPane pane4 = createPaddedStackPane(image4, new Insets(0, 0, 0, 40),"Position - IV"); // Add right padding to image 4

        gridPane.add(pane1, 0, 0);
        gridPane.add(pane2, 1, 0);
        gridPane.add(pane3, 0, 1);
        gridPane.add(pane4, 1, 1);

        gridPane.setAlignment(Pos.CENTER);
        root.setCenter(gridPane);
        gridPane.setStyle("-fx-background-color: #FFF9B7;");
        root.setTop(header);
        Scene scene = new Scene(root, 1000, 700);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Amrita Sports Management System - ASMS ");
        primaryStage.show();
    }


    private GridPane createGridPane() {
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10));

        return gridPane;
    }
    private HBox createHeader() {
        Label headingLabel = new Label("\t\t     RESULTS\n   THE FOUR HOUSES OF AMRITA");
        headingLabel.setFont(Font.font("Arial", FontWeight.BOLD, 36));
        headingLabel.setTextFill(Color.WHITE);
        headingLabel.setAlignment(Pos.CENTER);

        Button homeButton = new Button("HOME");
        Button loginButton = new Button("LOGIN");
        Button profile = new Button("MY PROFILE");
        Button news= new Button("Next Season Countdown");
        news.setOnAction(actionEvent ->  {
            UpcomingEventsPage aeslts = new UpcomingEventsPage();
            aeslts.start(new Stage());
        });
        homeButton.setOnAction(actionEvent -> {
            AResults aresults = new AResults();
            aresults.start(new Stage());
        });
        loginButton.setOnAction(actionEvent -> {
            LoginPage Login = new LoginPage();
            Login.start(new Stage());
        });
        profile.setOnAction(actionEvent -> {
            AProfile Aprof= new AProfile();
            Aprof.start(new Stage());
        });
        HBox buttonBox = new HBox(10);
        buttonBox.setPadding(new Insets(10));
        buttonBox.getChildren().addAll(homeButton, loginButton);
        buttonBox.setAlignment(Pos.CENTER_LEFT);

        HBox profileBox = new HBox(profile);
        profileBox.setAlignment(Pos.CENTER_RIGHT);
        HBox.setHgrow(profileBox, Priority.ALWAYS);

        HBox headerBox = new HBox(10);
        headerBox.getChildren().addAll(buttonBox, headingLabel, profileBox,news);
        headerBox.setPadding(new Insets(10));
        headerBox.setAlignment(Pos.CENTER);
        headerBox.setStyle("-fx-background-color: #c0052a;");
        headerBox.setMinHeight(100);
        return headerBox;
    }

    private ImageView createClickableImageView(String imagePath, String targetSceneTitle) {
        ImageView imageView = new ImageView(new Image(imagePath));
        imageView.setFitWidth(300);
        imageView.setPreserveRatio(true);

        imageView.setOnMouseClicked(event -> {
            AResult1 aresult1 = new AResult1();
            aresult1.start(new Stage());

        });
        return imageView;
    }
    private ImageView createClickableImageView1(String imagePath, String targetSceneTitle) {
        ImageView imageView = new ImageView(new Image(imagePath));
        imageView.setFitWidth(300);
        imageView.setPreserveRatio(true);

        imageView.setOnMouseClicked(event -> {
            AResult2 aresult1 = new AResult2();
            aresult1.start(new Stage());

        });
        return imageView;
    }
    private ImageView createClickableImageView2(String imagePath, String targetSceneTitle) {
        ImageView imageView = new ImageView(new Image(imagePath));
        imageView.setFitWidth(300);
        imageView.setPreserveRatio(true);

        imageView.setOnMouseClicked(event -> {
            AResult3 aresult1 = new AResult3();
            aresult1.start(new Stage());

        });
        return imageView;
    }
    private ImageView createClickableImageView3(String imagePath, String targetSceneTitle) {
        ImageView imageView = new ImageView(new Image(imagePath));
        imageView.setFitWidth(300);
        imageView.setPreserveRatio(true);

        imageView.setOnMouseClicked(event -> {
            AResult4 aresult1 = new AResult4();
            aresult1.start(new Stage());

        });
        return imageView;
    }
    private StackPane createPaddedStackPane(ImageView imageView, Insets padding,String text) {
        StackPane stackPane = new StackPane(imageView);

        TextField textField = new TextField();
        textField.setPrefWidth(50);
        textField.setAlignment(Pos.CENTER);
        textField.setText(text);
        textField.setEditable(false);

        stackPane.getChildren().add(textField);
        StackPane.setAlignment(textField, Pos.TOP_CENTER);
        StackPane.setMargin(textField, padding);

        return stackPane;
    }
    public static void main(String[] args) {
        launch(args);
    }
}