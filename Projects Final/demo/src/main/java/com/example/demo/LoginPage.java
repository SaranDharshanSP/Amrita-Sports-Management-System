package com.example.demo;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
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

public class LoginPage extends Application {
    static String usernameString="";
    String passwordString="";
    boolean a=true;
    static String type="";
    @Override
    public void start(Stage primaryStage) {

        BorderPane root = new BorderPane();
        HBox header = createHeader();
        SplitPane content = new SplitPane();
        VBox leftContent = new VBox();
        leftContent.setAlignment(Pos.CENTER);
        leftContent.setPadding(new Insets(10));
        leftContent.setStyle("-fx-background-color: #000000;");
        String[] imageUrls= {"C:\\Users\\Nivedha\\Desktop\\Amrita\\Sem 2\\Java\\Projects Final\\logo.png",
                "C:\\Users\\Nivedha\\Desktop\\Amrita\\Sem 2\\Java\\Projects Final\\lock.png",
        "C:\\Users\\Nivedha\\Desktop\\Amrita\\Sem 2\\Java\\Projects Final\\download.jpeg"};
        ImageView imageView = new ImageView(imageUrls[0]);
        ImageView imageView1 = new ImageView(imageUrls[1]);
        ImageView imageView2 = new ImageView(imageUrls[2]);
        imageView.setFitWidth(450);
        imageView.setFitHeight(200);
        imageView1.setFitWidth(128);
        imageView1.setFitHeight(128);
        imageView2.setFitWidth(300);
        imageView2.setFitHeight(200);
        leftContent.getChildren().addAll(imageView);
        leftContent.getChildren().addAll(imageView2);
        VBox rightContent = new VBox();
        rightContent.setAlignment(Pos.CENTER);
        rightContent.setPadding(new Insets(10));
        rightContent.setStyle("-fx-background-color: #000000;");
        Text heading=new Text("\nSIGN-IN");
        rightContent.getChildren().addAll(imageView1);
        heading.setFill(Color.WHITE);
        heading.setStyle("-fx-font-size: 24; -fx-font-weight: bold;");

        TextField username=new TextField();
        username.setPromptText("*Enter Username/EmailID");
        username.setPrefWidth(200);
        username.setMaxWidth(200);
        username.setAlignment(Pos.CENTER_LEFT);
        username.textProperty().addListener((observable, oldValue, newValue) -> {
            this.usernameString = newValue.trim();
        });
        TextField password=new TextField();
        password.setPromptText("*Enter Password");
        password.setPrefWidth(200);
        password.setMaxWidth(200);
        password.setAlignment(Pos.CENTER_LEFT);
        password.textProperty().addListener((observable, oldValue, newValue) -> {
            this.passwordString = newValue.trim();
        });
        Text message=new Text("");
        message.setFill(Color.RED);
        message.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        Button signinButton=new Button("SIGN-IN");
        signinButton.setStyle("-fx-background-color: yellow; -fx-text-fill: black;");
        signinButton.setOnAction(event->{
            if(passwordString==null || usernameString==null || passwordString.isEmpty() || usernameString.isEmpty()){
                message.setText("Please enter all fields");
                message.setFill(Color.RED);
            }
            else{
                try {
                    File file=new File("LoginCreditiantials.txt");
                    Scanner scanner=new Scanner(file);
                    this.a=true;
                    while (scanner.hasNextLine()){
                        String[] splitStr=scanner.nextLine().split(",");
                        if(splitStr[0].equals(usernameString) && splitStr[0].contains("admin.edu")&& splitStr[1].equals(passwordString)) {
                            message.setText("REDIRECTING-IN........");
                            message.setFill(Color.GREEN);
                            this.a = false;
                            type = splitStr[1];
                            Stage currentStage = (Stage) signinButton.getScene().getWindow();
                            currentStage.hide();
                            AResults aresults = new AResults();
                            aresults.start(new Stage());
                        }  else if  (splitStr[0].equals(usernameString) && splitStr[0].contains("student.edu")&& splitStr[1].equals(passwordString)) {
                            message.setText("REDIRECTING-IN........");
                            message.setFill(Color.GREEN);
                            this.a = false;
                            type = splitStr[1];
                            Stage currentStage = (Stage) signinButton.getScene().getWindow();
                            currentStage.hide();
                            System.out.println(124455);
                            Results results= new Results();
                            results.start(new Stage());
                            break;
                        }
                    }
                    if (a){
                        message.setText("Entered credentials are incorrect");
                        message.setFill(Color.RED);
                    }
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
        });

        VBox temp1=new VBox(10,username,password,signinButton,message);
        temp1.setAlignment(Pos.CENTER);
        VBox temp2=new VBox(50,heading,temp1);
        temp2.setAlignment(Pos.CENTER);

        rightContent.getChildren().addAll(temp2);

        // Set the left and right halves of the content in the split pane
        content.getItems().addAll(leftContent, rightContent);
        content.setDividerPositions(0.5);

        // Set the header, content, and footer in the border pane
        root.setTop(header);
        root.setCenter(content);

        Scene scene = new Scene(root, 1000, 700);

        // Set the scene on the primary stage
        primaryStage.setScene(scene);

        // Set the title of the stage
        primaryStage.setTitle("Amrita Sports Management System - ASMS ");

        // Display the stage
        primaryStage.show();
    }

    private HBox createHeader() {
        Label headingLabel = new Label(" Amrita Sports Management System \n                           ASMS");
        headingLabel.setFont(Font.font("Arial", FontWeight.BOLD, 36));
        headingLabel.setTextFill(Color.WHITE);

        HBox headerBox = new HBox(headingLabel);
        headerBox.setAlignment(Pos.CENTER);
        headerBox.setPadding(new Insets(20));
        headerBox.setStyle("-fx-background-color: #000000;");
        headerBox.setMinHeight(150);

        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.rgb(0, 0, 0, 0.5));
        dropShadow.setRadius(4);
        dropShadow.setOffsetX(0);
        dropShadow.setOffsetY(2);
        headingLabel.setEffect(dropShadow);

        return headerBox;
    }

    public static void main(String[] args) {
        launch(args);
    }
}