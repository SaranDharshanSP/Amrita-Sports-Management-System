
package com.example.demo;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


    public class UpcomingEventsPage extends Application {

        private static final LocalDate EVENT_DATE = LocalDate.of(2023, 12, 31); // Replace with your desired event date

        private Label countdownLabel;

        @Override
        public void start(Stage primaryStage) {
            VBox root1 = createLayout();

            BorderPane root = new BorderPane();
            HBox header = createHeader();
            root.setTop(header);
            root.setCenter(root1);
            Scene scene = new Scene(root, 900, 700);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Amrita Sports Management System - ASMS ");
            primaryStage.show();

            updateCountdown(); // Update the countdown label initially

            // Update the countdown label every second
            new Thread(() -> {
                try {
                    while (true) {
                        Thread.sleep(1000);
                        updateCountdown();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        private VBox createLayout() {
            VBox vbox = new VBox();
            vbox.setAlignment(Pos.CENTER);
            vbox.setSpacing(10);
            vbox.setPadding(new Insets(20));

            Label titleLabel = new Label("Upcoming Event:");
            titleLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 18px;");

            countdownLabel = new Label();
            countdownLabel.setStyle("-fx-font-size: 24px;");

            vbox.getChildren().addAll(titleLabel, countdownLabel);
            return vbox;
        }

        private void updateCountdown() {
            LocalDate currentDate = LocalDate.now();
            long daysRemaining = ChronoUnit.DAYS.between(currentDate, EVENT_DATE);

            if (daysRemaining > 0) {
                countdownLabel.setText("Days remaining: " + daysRemaining);
            } else {
                countdownLabel.setText("Event has already taken place.");
            }
        }


    private HBox createHeader() {
        Label headingLabel = new Label("\tNews");
        headingLabel.setFont(Font.font("Arial", FontWeight.BOLD, 36));
        headingLabel.setTextFill(Color.WHITE);
        headingLabel.setAlignment(Pos.TOP_CENTER);

        Label headingLabel1 = new Label("- Upcoming Event");
        headingLabel1.setFont(Font.font("Arial", FontWeight.BOLD, 36));
        headingLabel1.setTextFill(Color.WHITE);
        headingLabel1.setAlignment(Pos.CENTER);

        Button homeButton = new Button("HOME");
        Button loginButton = new Button("LOGIN ");
        Button profile = new Button("MY PROFILE");

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