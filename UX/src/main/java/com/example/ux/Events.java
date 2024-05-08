package com.example.ux;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Events extends Application {
   
   public static void main(String[] args) {
      Application.launch(args);
   }

   @Override
   public void start(Stage stage) throws Exception {
      VBox root = new VBox(15.0);
      root.setAlignment(Pos.CENTER);
      Button button = new Button("Нажми меня");
      root.getChildren().addAll(button);
      Scene scene = new Scene(root, 500.0, 150.0);
      button.setOnAction(event -> {
         System.out.println("button - setOnAction");
         // event.consume();
      });
      button.addEventHandler(ActionEvent.ACTION, event -> {
         System.out.println("button - Handler");
         // event.consume();
      });
/*      button.addEventHandler(ActionEvent.ACTION, event -> {
         System.out.println("button - Handler2");
         // event.consume();
      });*/
      button.addEventFilter(ActionEvent.ACTION, event -> {
         System.out.println("button - Filter");
         // event.consume();
      });
/*      button.addEventFilter(ActionEvent.ACTION, event -> {
         System.out.println("button - Filter2");
         // event.consume();
      });*/
      root.addEventHandler(ActionEvent.ACTION, event -> {
         System.out.println("root - Handler");
         // event.consume();
      });
      root.addEventFilter(ActionEvent.ACTION, event -> {
         System.out.println("root - Filter");
         // event.consume();
      });
      scene.addEventHandler(ActionEvent.ACTION, event -> {
         System.out.println("scene - Handler");
         // event.consume();
      });
      scene.addEventFilter(ActionEvent.ACTION, event -> {
         System.out.println("scene - Filter");
         // event.consume();
      });
      stage.addEventHandler(ActionEvent.ACTION, event -> {
         System.out.println("stage - Handler");
      });
      stage.addEventFilter(ActionEvent.ACTION, event -> {
         System.out.println("stage - Filter");
         // event.consume();
      });
      stage.setTitle("Последовательность вызова обработчиков");
      stage.setScene(scene);
      stage.show();
   }
}