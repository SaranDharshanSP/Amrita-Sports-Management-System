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

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;

public class AProfile extends Application {
    private static final String FILE_PATH = "profile_data.txt";

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

        Button addButton = new Button("ADD SPORT");
        Button deleteButton = new Button("DELETE SPORT");
        Button modifyButton = new Button("MODIFY SPORT");

        addButton.setOnAction(event -> addSport(table));
        deleteButton.setOnAction(event -> deleteSport(table));
        modifyButton.setOnAction(event -> modifySport(table));

        HBox buttonBox = new HBox(10);
        buttonBox.setPadding(new Insets(10));
        buttonBox.getChildren().addAll(addButton, deleteButton, modifyButton);
        buttonBox.setAlignment(Pos.CENTER);

        root.setBottom(buttonBox);

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
        table.setItems(createDummyData()); // Populate table with dummy data

        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setTableMenuButtonVisible(true);

        return table;
    }

    private ObservableList<ProfileEntry> createDummyData() {
        ObservableList<ProfileEntry> data = FXCollections.observableArrayList(
                new ProfileEntry(1, "Basketball", "First", "Amritamayi House"),
                new ProfileEntry(2, "Football", "Fourth", "Jyothirmayi House"),
                new ProfileEntry(3, "Cricket", "Second", "Anandhamayi House"),
                new ProfileEntry(4, "Tennis", "Third", "Chimayi House")
                // Add more dummy data as needed
        );
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

        public void setSport(String sport) {
            this.sport.set(sport);
        }

        public String getPosition() {
            return position.get();
        }

        public SimpleStringProperty positionProperty() {
            return position;
        }

        public void setPosition(String position) {
            this.position.set(position);
        }

        public String getHouse() {
            return house.get();
        }

        public SimpleStringProperty houseProperty() {
            return house;
        }

        public void setHouse(String house) {
            this.house.set(house);
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

    private void addSport(TableView<ProfileEntry> table) {
        Dialog<ProfileEntry> dialog = new Dialog<>();
        dialog.setTitle("Add Sport");
        dialog.setHeaderText("Enter the details:");

        ButtonType addButton = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButton, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        TextField sportField = new TextField();
        sportField.setPromptText("Sport");
        TextField positionField = new TextField();
        positionField.setPromptText("Position");
        TextField houseField = new TextField();
        houseField.setPromptText("House Name");

        grid.add(new Label("Sport:"), 0, 0);
        grid.add(sportField, 1, 0);
        grid.add(new Label("Position:"), 0, 1);
        grid.add(positionField, 1, 1);
        grid.add(new Label("House Name:"), 0, 2);
        grid.add(houseField, 1, 2);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButton) {
                String sport = sportField.getText();
                String position = positionField.getText();
                String house = houseField.getText();
                return new ProfileEntry(table.getItems().size() + 1, sport, position, house);
            }
            return null;
        });

        Optional<ProfileEntry> result = dialog.showAndWait();
        result.ifPresent(entry -> {
            table.getItems().add(entry);
            saveDataToFile(table.getItems());
        });
    }

    private void deleteSport(TableView<ProfileEntry> table) {
        ProfileEntry selectedEntry = table.getSelectionModel().getSelectedItem();
        if (selectedEntry != null) {
            table.getItems().remove(selectedEntry);
            saveDataToFile(table.getItems());
        } else {
            showAlert("No sport selected", "Please select a sport to delete.");
        }
    }

    private void modifySport(TableView<ProfileEntry> table) {
        ProfileEntry selectedEntry = table.getSelectionModel().getSelectedItem();
        if (selectedEntry != null) {
            Dialog<ProfileEntry> dialog = new Dialog<>();
            dialog.setTitle("Modify Sport");
            dialog.setHeaderText("Modify the details:");

            ButtonType saveButton = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(saveButton, ButtonType.CANCEL);

            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);

            TextField sportField = new TextField(selectedEntry.getSport());
            sportField.setPromptText("Sport");
            TextField positionField = new TextField(selectedEntry.getPosition());
            positionField.setPromptText("Position");
            TextField houseField = new TextField(selectedEntry.getHouse());
            houseField.setPromptText("House Name");

            grid.add(new Label("Sport:"), 0, 0);
            grid.add(sportField, 1, 0);
            grid.add(new Label("Position:"), 0, 1);
            grid.add(positionField, 1, 1);
            grid.add(new Label("House Name:"), 0, 2);
            grid.add(houseField, 1, 2);

            dialog.getDialogPane().setContent(grid);

            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == saveButton) {
                    String sport = sportField.getText();
                    String position = positionField.getText();
                    String house = houseField.getText();
                    selectedEntry.setSport(sport);
                    selectedEntry.setPosition(position);
                    selectedEntry.setHouse(house);
                    return selectedEntry;
                }
                return null;
            });

            Optional<ProfileEntry> result = dialog.showAndWait();
            result.ifPresent(modifiedEntry -> {
                table.refresh(); // Refresh the table view to reflect the changes
                saveDataToFile(table.getItems());
            });
        } else {
            showAlert("No sport selected", "Please select a sport to modify.");
        }
    }

    private void saveDataToFile(ObservableList<ProfileEntry> data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (ProfileEntry entry : data) {
                writer.write(entry.getSno() + "," + entry.getSport() + "," + entry.getPosition() + "," + entry.getHouse());
                writer.newLine();
            }
        } catch (IOException e) {
            showAlert("Error", "An error occurred while saving the data to the file.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
