
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

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Profile extends Application {
    @Override
    public void start(Stage primaryStage) {

        BorderPane root = new BorderPane();
        HBox header = createHeader();

        TableView<ProfileEntry> table = createTable();

        VBox centerBox = new VBox(10, table);
        centerBox.setAlignment(Pos.CENTER);
        centerBox.setPadding(new Insets(10));

        root.setCenter(centerBox);

        root.setTop(header);

        Scene scene = new Scene(root, 900, 700);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Amrita Sports Management System - ASMS ");
        primaryStage.show();
    }

    private TableView<ProfileEntry> createTable() {
        TableView<ProfileEntry> table = new TableView<>();

        TableColumn<ProfileEntry, String> snoColumn = new TableColumn<>("S.no");
        snoColumn.setCellValueFactory(data -> data.getValue().snoProperty());
        snoColumn.setPrefWidth(100);

        TableColumn<ProfileEntry, String> sportColumn = new TableColumn<>("Sport");
        sportColumn.setCellValueFactory(data -> data.getValue().sportProperty());
        sportColumn.setPrefWidth(200);

        TableColumn<ProfileEntry, String> positionColumn = new TableColumn<>("Position");
        positionColumn.setCellValueFactory(data -> data.getValue().positionProperty());
        positionColumn.setPrefWidth(200);

        TableColumn<ProfileEntry, String> houseColumn = new TableColumn<>("House Name");
        houseColumn.setCellValueFactory(data -> data.getValue().houseProperty());
        houseColumn.setPrefWidth(200);

        table.getColumns().addAll(snoColumn, sportColumn, positionColumn, houseColumn);
        table.setItems(loadDataFromFile()); // Populate table with dummy data

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setTableMenuButtonVisible(true);

        return table;
    }

    private ObservableList<ProfileEntry> loadDataFromFile() {
        ObservableList<ProfileEntry> data = FXCollections.observableArrayList();
        try (Scanner scanner = new Scanner(new File("profile_data.txt"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] values = line.split(",");
                if (values.length == 4) {
                    int sno = Integer.parseInt(values[0]);
                    String sport = values[1];
                    String position = values[2];
                    String house = values[3];
                    ProfileEntry entry = new ProfileEntry(sno, sport, position, house);
                    data.add(entry);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }


    // ProfileEntry class to represent each row in the table
    public static class ProfileEntry {
        private final SimpleStringProperty sport;
        private final SimpleStringProperty position;
        private final SimpleStringProperty house;
        private final SimpleStringProperty sno;

        public ProfileEntry(int sno, String sport, String position, String house) {
            this.sno = new SimpleStringProperty(String.valueOf(sno));
            this.sport = new SimpleStringProperty(sport);
            this.position = new SimpleStringProperty(position);
            this.house = new SimpleStringProperty(house);
        }

        public String getSport() {
            return sport.get();
        }

        public SimpleStringProperty sportProperty() {
            return sport;
        }

        public String getPosition() {
            return position.get();
        }

        public SimpleStringProperty positionProperty() {
            return position;
        }

        public String getHouse() {
            return house.get();
        }

        public SimpleStringProperty houseProperty() {
            return house;
        }

        public String getSno() {
            return sno.get();
        }

        public SimpleStringProperty snoProperty() {
            return sno;
        }
    }

    private HBox createHeader() {
        Label headingLabel = new Label("\tMy Profile");
        headingLabel.setFont(Font.font("Arial", FontWeight.BOLD, 36));
        headingLabel.setTextFill(Color.WHITE);
        headingLabel.setAlignment(Pos.TOP_CENTER);

        Label headingLabel1 = new Label("- Achievements");
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