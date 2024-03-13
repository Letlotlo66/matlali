package com.example.matlali;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.InputStream;
import java.util.Arrays;

public class HelloApplication extends Application {



    private String[] imageNames = {
            "Lesotho.png", "Malawi.png", "SA.png",
            "Angola.png", "Botswana.png", "Mozambique.png",
            "Namibia.png", "Swaziland.png", "Zambia.png"
    };

    private int currentIndex = 0;

    @Override
    public void start(Stage primaryStage) {
        // Create a GridPane for the gallery
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(15);
        gridPane.setVgap(10);

        // Add Lesotho thumbnail and button
        ImageView lesothoImg = createThumbnail("Lesotho.png");
        Button lesothoBtn = createViewButton("Lesotho", 0);
        gridPane.add(lesothoImg, 0, 0);
        gridPane.add(lesothoBtn, 0, 1);

        // Add Malawi thumbnail and button
        ImageView malawiImg = createThumbnail("Malawi.png");
        Button malawiBtn = createViewButton("Malawi", 1);
        gridPane.add(malawiImg, 1, 0);
        gridPane.add(malawiBtn, 1, 1);

        // Add South Africa thumbnail and button
        ImageView sAfricaImg = createThumbnail("SA.png");
        Button sAfricaBtn = createViewButton("South Africa", 2);
        gridPane.add(sAfricaImg, 2, 0);
        gridPane.add(sAfricaBtn, 2, 1);

        // Add Angola thumbnail and button
        ImageView angolaImg = createThumbnail("Angola.png");
        Button angolaBtn = createViewButton("Angola", 3);
        gridPane.add(angolaImg, 0, 2);
        gridPane.add(angolaBtn, 0, 3);

        // Add Botswana thumbnail and button
        ImageView botswanaImg = createThumbnail("Botswana.png");
        Button botswanaBtn = createViewButton("Botswana", 4);
        gridPane.add(botswanaImg, 1, 2);
        gridPane.add(botswanaBtn, 1, 3);

        // Add Mozambique thumbnail and button
        ImageView mozambiqueImg = createThumbnail("Mozambique.png");
        Button mozambiqueBtn = createViewButton("Mozambique", 5);
        gridPane.add(mozambiqueImg, 2, 2);
        gridPane.add(mozambiqueBtn, 2, 3);

        // Add Namibia thumbnail and button
        ImageView namibiaImg = createThumbnail("Namibia.png");
        Button namibiaBtn = createViewButton("Namibia", 6);
        gridPane.add(namibiaImg, 0, 4);
        gridPane.add(namibiaBtn, 0, 5);

        // Add Swaziland thumbnail and button
        ImageView swazilandImg = createThumbnail("Swaziland.png");
        Button swazilandBtn = createViewButton("Swaziland", 7);
        gridPane.add(swazilandImg, 1, 4);
        gridPane.add(swazilandBtn, 1, 5);

        // Add Zambia thumbnail and button
        ImageView zambiaImg = createThumbnail("Zambia.png");
        Button zambiaBtn = createViewButton("Zambia", 8);
        gridPane.add(zambiaImg, 2, 4);
        gridPane.add(zambiaBtn, 2, 5);

        // Create a scene and set it in the stage
        Scene scene = new Scene(gridPane, 380, 400);
        primaryStage.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        primaryStage.setTitle("SADC Flags Gallery");
        primaryStage.show();
    }

    // Method to create a thumbnail ImageView
    private ImageView createThumbnail(String imageName) {
        InputStream inputStream = getClass().getResourceAsStream(imageName);
        if (inputStream != null) {
            Image image = new Image(inputStream);
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(100);
            imageView.setFitHeight(100);
            imageView.setPreserveRatio(true);
            return imageView;
        } else {
            System.err.println("Image file '" + imageName + "' not found!");
            // You can choose to return a placeholder image or handle the error differently
            return new ImageView(); // Return an empty ImageView to prevent NullPointerException
        }
    }

    // Method to create a button
    private Button createViewButton(String text, int index) {
        Button button = new Button(text);
        button.setOnAction(event -> viewFullImage(index));
        return button;
    }

    // Method to display full-size image in a separate window
    private void viewFullImage(int index) {
        currentIndex = index;
        ImageView fullImageView = new ImageView(new Image(getClass().getResourceAsStream(imageNames[currentIndex])));
        fullImageView.setFitWidth(800);
        fullImageView.setFitHeight(500);

        Button prevButton = new Button("Previous");
        prevButton.setOnAction(event -> {
            currentIndex = (currentIndex - 1 + imageNames.length) % imageNames.length;
            fullImageView.setImage(new Image(getClass().getResourceAsStream(imageNames[currentIndex])));
        });

        Button nextButton = new Button("Next");
        nextButton.setOnAction(event -> {
            currentIndex = (currentIndex + 1) % imageNames.length;
            fullImageView.setImage(new Image(getClass().getResourceAsStream(imageNames[currentIndex])));
        });


        GridPane buttonPane = new GridPane();
        buttonPane.add(prevButton, 0, 0);
        buttonPane.add(nextButton, 1, 0);

        Scene scene = new Scene(new GridPane() {
            {
                add(fullImageView, 0, 0);
                add(buttonPane, 0, 1);
            }
        });
        Stage fullImageStage = new Stage();
        fullImageStage.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        fullImageStage.setFullScreen(true);
        fullImageStage.setTitle("Southern African Development Community");
        fullImageStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
