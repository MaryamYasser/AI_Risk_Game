package sample.View;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.Modality;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.geometry.Pos;

public class PopUp {

    static boolean answer;

    public static void Error(String title,String message){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);

        Label passerror = new Label(message);


        passerror.setStyle(
                "-fx-font-size: 20px;" +
                "    -fx-font-weight: bold;" +
                "    -fx-font-family: \"Krungthep\";" +
                "    -fx-text-fill: #ffdbc5;" +
                "    -fx-effect: dropshadow(three-pass-box , rgba(0,0,0,0.6) , 5, 0.0 , 0 , 1);");




        VBox popup = new VBox(10);
        popup.getChildren().add(passerror);
        popup.setAlignment(Pos.CENTER);
        popup.setStyle("-fx-background-color: slategrey");
        Scene error= new Scene(popup,150,150);
        window.setScene(error);
        window.showAndWait();
    }

    }






