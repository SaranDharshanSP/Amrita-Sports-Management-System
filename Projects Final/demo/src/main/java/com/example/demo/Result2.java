package com.example.demo;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

public class Result2 extends Application {
    @Override
    public void start(Stage primaryStage) {

        BorderPane root = new BorderPane();
        HBox header = createHeader();


        // Create the TableView
        TableView<String[]> tableView = createTableView();
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        TableColumn<String[], String> col1 = (TableColumn<String[], String>) tableView.getColumns().get(0);
        TableColumn<String[], String> col2 = (TableColumn<String[], String>) tableView.getColumns().get(1);
        TableColumn<String[], String> col3 = (TableColumn<String[], String>) tableView.getColumns().get(2);
        col1.setPrefWidth(150);
        col2.setPrefWidth(150);
        col3.setPrefWidth(150);
        Image leftImage = new Image("C:\\Users\\Nivedha\\Desktop\\Amrita\\Sem 2\\Java\\Projects Final\\image2.jpg");
        ImageView leftImageView = new ImageView(leftImage);
        leftImageView.setFitWidth(400);
        leftImageView.setFitHeight(400);

        HBox leftRightHBox = new HBox(10); // Spacing between left and right elements
        leftRightHBox.setPadding(new Insets(10));
        leftRightHBox.getChildren().addAll(leftImageView,tableView);
        leftRightHBox.setAlignment(Pos.CENTER);

        // Add the TableView and imageBox to the root BorderPane
        root.setCenter(leftRightHBox);
        root.setTop(header);
        Scene scene = new Scene(root, 900, 700);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Amrita Sports Management System - ASMS ");
        primaryStage.show();
    }

    private TableView<String[]> createTableView() {
        TableView<String[]> tableView = new TableView<>();

        // Create table columns
        TableColumn<String[], String> col1 = new TableColumn<>("SPORTS");
        TableColumn<String[], String> col2 = new TableColumn<>("MEN");
        TableColumn<String[], String> col3 = new TableColumn<>("WOMEN");

        // Set the data property value factory for each column
        col1.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()[0]));
        col2.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()[1]));
        col3.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()[2]));

        // Add the columns to the TableView
        tableView.getColumns().addAll(col1, col2, col3);


        return tableView;
    }

    private HBox createHeader() {
        Label headingLabel = new Label("\tANANDAMAYI");
        headingLabel.setFont(Font.font("Arial", FontWeight.BOLD, 36));
        headingLabel.setTextFill(Color.WHITE);
        headingLabel.setAlignment(Pos.TOP_CENTER);

        Label headingLabel1 = new Label("- Points Table");
        headingLabel1.setFont(Font.font("Arial", FontWeight.BOLD, 36));
        headingLabel1.setTextFill(Color.WHITE);
        headingLabel1.setAlignment(Pos.CENTER);

        Button homeButton = new Button("HOME");
        Button loginButton = new Button("LOGIN ");
        Button profile = new Button("MY PROFILE");
        homeButton.setOnAction(actionEvent -> {
            Results rests = new Results();
            rests.start(new Stage());
        });
        loginButton.setOnAction(actionEvent -> {
            LoginPage Login = new LoginPage();
            Login.start(new Stage());
        });
        profile.setOnAction(actionEvent -> {
            Profile prroff= new Profile();
            prroff.start(new Stage());
        });

        HBox buttonBox = new HBox(10);
        buttonBox.setPadding(new Insets(10));
        buttonBox.getChildren().addAll(homeButton, loginButton);
        buttonBox.setAlignment(Pos.CENTER_LEFT);

        HBox profileBox = new HBox(profile);
        profileBox.setAlignment(Pos.CENTER_RIGHT);
        HBox.setHgrow(profileBox, Priority.ALWAYS);

        HBox headerBox = new HBox(10);
        headerBox.getChildren().addAll(buttonBox, headingLabel, headingLabel1, profileBox);
        headerBox.setPadding(new Insets(10));
        headerBox.setAlignment(Pos.CENTER);
        headerBox.setStyle("-fx-background-color: #c0052a;");
        headerBox.setMinHeight(100);
        HBox.setHgrow(headerBox, Priority.ALWAYS);

        headerBox.setFillHeight(true);

        return headerBox;
    }






    public static void main(String[] args) {
        launch(args);
    }
}