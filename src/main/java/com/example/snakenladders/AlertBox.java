package com.example.snakenladders;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {
    public static void display(String message, String title) {
        Stage window = new Stage() ;
        window.initModality(Modality.APPLICATION_MODAL) ;
        window.setTitle(title);
        window.setMinWidth(300) ;
        window.setMinHeight(300) ;

        Label label = new Label() ;
        label.setText(message);
        Button button = new Button("Close the window") ;
        button.setOnAction(e -> window.close());

        VBox layout = new VBox(10) ;
        layout.getChildren().addAll(label,button) ;
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout) ;
        window.setScene(scene) ;
        window.showAndWait() ;

    }
}
