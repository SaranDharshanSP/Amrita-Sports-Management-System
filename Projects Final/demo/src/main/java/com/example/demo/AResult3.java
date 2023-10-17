package com.example.demo;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.*;
import java.util.Optional;
import java.util.Scanner;

public class AResult3 extends Application {
    private TableView<AResultEntry> tableView;

    public static class AResultEntry {
        private final SimpleStringProperty sport;
        private final SimpleStringProperty men;
        private final SimpleStringProperty women;

        public AResultEntry(String sport, String men, String women) {
            this.sport = new SimpleStringProperty(sport);
            this.men = new SimpleStringProperty(men);
            this.women = new SimpleStringProperty(women);
        }

        public String getSport() {
            return sport.get();
        }

        public void setSport(String sport) {
            this.sport.set(sport);
        }

        public String getMen() {
            return men.get();
        }

        public void setMen(String men) {
            this.men.set(men);
        }

        public String getWomen() {
            return women.get();
        }

        public void setWomen(String women) {
            this.women.set(women);
        }

        public SimpleStringProperty sportProperty() {
            return sport;
        }

        public SimpleStringProperty menProperty() {
            return men;
        }

        public SimpleStringProperty womenProperty() {
            return women;
        }
    }

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        HBox header = createHeader();

        // Create the TableView
        tableView = createTableView();
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Set the initial table data
        tableView.setItems(loadDataFromFile());

        Image leftImage = new Image("C:\\Users\\Nivedha\\Desktop\\Amrita\\Sem 2\\Java\\Projects Final\\image3.jpg");
        ImageView leftImageView = new ImageView(leftImage);
        leftImageView.setFitWidth(400);
        leftImageView.setFitHeight(400);
        HBox leftRightHBox = new HBox(10); // Spacing between left and right elements
        leftRightHBox.setPadding(new Insets(10));
        leftRightHBox.getChildren().addAll(leftImageView, tableView);
        leftRightHBox.setAlignment(Pos.CENTER);

        // Add the TableView and imageBox to the root BorderPane
        root.setCenter(leftRightHBox);
        root.setTop(header);

        // Create buttons
        Button deleteButton = new Button("DELETE SPORT");
        Button modifyButton = new Button("MODIFY SPORT");

        // Set event handler for delete button
        deleteButton.setOnAction(event -> {
            AResultEntry selectedEntry = tableView.getSelectionModel().getSelectedItem();
            if (selectedEntry != null) {
                tableView.getItems().remove(selectedEntry);
                saveDataToFile(tableView.getItems());
            } else {
                showAlert("No sport selected", "Please select a sport to delete.");
            }
        });

        // Set event handler for modify button
        modifyButton.setOnAction(event -> {
            AResultEntry selectedEntry = tableView.getSelectionModel().getSelectedItem();
            if (selectedEntry != null) {
                Dialog<AResultEntry> dialog = new Dialog<>();
                dialog.setTitle("Modify Sport");
                dialog.setHeaderText("Modify the details:");

                ButtonType saveButton = new ButtonType("Save", ButtonBar.ButtonData.OK_DONE);
                dialog.getDialogPane().getButtonTypes().addAll(saveButton, ButtonType.CANCEL);

                GridPane grid = new GridPane();
                grid.setHgap(10);
                grid.setVgap(10);

                TextField sportField = new TextField(selectedEntry.getSport());
                sportField.setPromptText("Sport");
                TextField menField = new TextField(selectedEntry.getMen());
                menField.setPromptText("Men");
                TextField womenField = new TextField(selectedEntry.getWomen());
                womenField.setPromptText("Women");

                grid.add(new Label("Sport:"), 0, 0);
                grid.add(sportField, 1, 0);
                grid.add(new Label("Men:"), 0, 1);
                grid.add(menField, 1, 1);
                grid.add(new Label("Women:"), 0, 2);
                grid.add(womenField, 1, 2);

                dialog.getDialogPane().setContent(grid);

                dialog.setResultConverter(dialogButton -> {
                    if (dialogButton == saveButton) {
                        String sport = sportField.getText();
                        String men = menField.getText();
                        String women = womenField.getText();
                        selectedEntry.setSport(sport);
                        selectedEntry.setMen(men);
                        selectedEntry.setWomen(women);
                        return selectedEntry;
                    }
                    return null;
                });

                Optional<AResultEntry> result = dialog.showAndWait();
                result.ifPresent(modifiedEntry -> {
                    tableView.refresh(); // Refresh the table view to reflect the changes
                    saveDataToFile(tableView.getItems());
                });
            } else {
                showAlert("No sport selected", "Please select a sport to modify.");
            }
        });



        // Create an HBox for the buttons
        HBox buttonBox = new HBox(10);
        buttonBox.setPadding(new Insets(10));
        buttonBox.getChildren().addAll(deleteButton, modifyButton);
        buttonBox.setAlignment(Pos.CENTER);

        // Add the buttonBox to the root BorderPane
        root.setBottom(buttonBox);

        Scene scene = new Scene(root, 900, 700);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Amrita Sports Management System - ASMS ");
        primaryStage.show();
    }
    private ObservableList<AResultEntry> loadDataFromFile() {
        ObservableList<AResultEntry> data = FXCollections.observableArrayList();
        try (Scanner scanner = new Scanner(new File("data2.txt"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] values = line.split(",");
                if (values.length == 3) {
                    String sport = values[0];
                    String men = values[1];
                    String women = values[2];
                    AResultEntry entry = new AResultEntry(sport, men, women);
                    data.add(entry);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    private TableView<AResultEntry> createTableView() {
        TableView<AResultEntry> tableView = new TableView<>();

        TableColumn<AResultEntry, String> col1 = new TableColumn<>("Sport");
        TableColumn<AResultEntry, String> col2 = new TableColumn<>("Men");
        TableColumn<AResultEntry, String> col3 = new TableColumn<>("Women");

        col1.setCellValueFactory(data -> data.getValue().sportProperty());
        col2.setCellValueFactory(data -> data.getValue().menProperty());
        col3.setCellValueFactory(data -> data.getValue().womenProperty());

        tableView.getColumns().addAll(col1, col2, col3);

        return tableView;
    }


    private void saveDataToFile(ObservableList<AResultEntry> data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("data.txt"))) {
            for (AResultEntry entry : data) {
                writer.write(entry.getSport() + "," + entry.getMen() + "," + entry.getWomen());
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

    private HBox createHeader() {
        Label headingLabel = new Label("\tAMRITAMAYI");
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
            AResults resuts = new AResults();
            resuts.start(new Stage());
        });
        loginButton.setOnAction(actionEvent -> {
            LoginPage Login = new LoginPage();
            Login.start(new Stage());
        });
        profile.setOnAction(actionEvent -> {
            AProfile proff = new AProfile();
            proff.start(new Stage());
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
