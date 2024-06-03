/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class DnD extends Application{

    public static void main(String[] args) {
      Application.launch(args);
   }

   @Override
   public void start(Stage stage) throws Exception {
      VBox root = new VBox(15.0);
      root.setAlignment(Pos.CENTER);
      Button button1 = new Button("Запуск перетаскивания");
      Button button2 = new Button("Обработка сброса");
      Button button3 = new Button("Класс Dragboard");
      root.getChildren().addAll(button1, button2, button3);
      Scene scene = new Scene(root, 400.0, 300.0);
      stage.setTitle("Технология drag & drop");
      stage.setScene(scene);
      button1.setOnAction(event -> {
         newWindow();
      });
      button2.setOnAction(event -> {
         newWindow2();
      });
      button3.setOnAction(event -> {
         newWindow3();
      });
      stage.show();
   }
   
   public void newWindow() {
      Stage window = new Stage();
      VBox pane = new VBox(5.0);
      pane.setAlignment(Pos.CENTER);
      pane.setPadding(new Insets(20.0));
      Label label = new Label("Перетащите этот текст в любой текстовый редактор");
      label.setBorder(new Border(
            new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,
                  CornerRadii.EMPTY, new BorderWidths(3.0), new Insets(5.0))));
      label.setPadding(new Insets(10.0));
      label.setPrefSize(300.0, 300.0);
      label.setWrapText(true);
      label.setAlignment(Pos.CENTER);
      Label label2 = new Label();
      pane.getChildren().addAll(label, label2);
      Scene scene = new Scene(pane, 400, 400);
      
      scene.addEventFilter(DragEvent.ANY, event -> {
         System.out.println(event.getEventType());
      });
      
      label.addEventHandler(MouseEvent.DRAG_DETECTED, event -> {
         System.out.println("DRAG_DETECTED");
         
         Dragboard db = label.startDragAndDrop(TransferMode.COPY_OR_MOVE);
         ClipboardContent content = new ClipboardContent();
         content.putString(label.getText());
         db.setContent(content);
         event.consume();
      });
      
      label.addEventHandler(DragEvent.DRAG_DONE, event -> {
         System.out.println("DRAG_DONE");
         if (event.isAccepted()) {
            label2.setText("Сброс выполнен удачно. Режим: " +
                               event.getTransferMode());
         }
         else {
            label2.setText("Сброс не выполнен");
         }
         System.out.println("Режим: " +
               event.getTransferMode());
      });

      window.setScene(scene);
      window.setTitle("Запуск перетаскивания");
      window.show();
   }
   
   public void newWindow2() {
      final Border borderDefault = new Border(
            new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,
                  CornerRadii.EMPTY, new BorderWidths(3.0), new Insets(5.0)));
      final Border borderRed = new Border(
            new BorderStroke(Color.RED, BorderStrokeStyle.SOLID,
                  CornerRadii.EMPTY, new BorderWidths(3.0), new Insets(5.0)));
      final Border borderGreen = new Border(
            new BorderStroke(Color.GREEN, BorderStrokeStyle.SOLID,
                  CornerRadii.EMPTY, new BorderWidths(3.0), new Insets(5.0)));
      
      Stage window = new Stage();
      VBox pane = new VBox(15.0);
      pane.setAlignment(Pos.CENTER);
      Button button = new Button("Очистить поле");
      Label label = new Label("Перетащите текст сюда");
      label.setAlignment(Pos.CENTER);
      label.setPrefSize(300.0, 300.0);
      label.setWrapText(true);
      label.setBorder(borderDefault);
      label.setPadding(new Insets(10.0));

      Label lblScreen = new Label("Screen: ");
      Label lblScene = new Label("Scene: ");
      Label lblGet = new Label("");
      
      pane.getChildren().addAll(label, button, lblScreen, lblScene, lblGet);
      Scene scene = new Scene(pane, 400, 500);
      
      button.setOnAction(event -> {
         label.setText("");
      });
      scene.addEventHandler(DragEvent.DRAG_EXITED_TARGET, event -> {
         System.out.println("DRAG_EXITED_TARGET " + event.getTarget());
         System.out.println(event.getClass());
      });
      scene.addEventHandler(DragEvent.DRAG_ENTERED_TARGET, event -> {
         System.out.println("DRAG_ENTERED_TARGET " + event.getTarget());
         System.out.println("getGestureSource(): " + event.getGestureSource());
         System.out.println("getGestureTarget(): " + event.getGestureTarget());
      });
      //label.setOnDragOver(event -> {
      label.addEventHandler(DragEvent.DRAG_OVER, event -> {
         lblScene.setText("Scene: x=" + event.getSceneX() + 
               " y=" +  event.getSceneY());
         lblScreen.setText("Screen: x=" + event.getScreenX() + 
               " y=" +  event.getScreenY());
         lblGet.setText("getX=" + event.getX() + 
               " getY=" +  event.getY() + " getZ=" +  event.getZ());
         Dragboard db = event.getDragboard();
         if (db.hasString() || db.hasHtml() || db.hasRtf()) {
            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
         }
         else if (db.hasUrl()) {
            event.acceptTransferModes(TransferMode.COPY);
         }
         event.consume();
      });

      label.addEventHandler(DragEvent.DRAG_ENTERED, event -> {
         Dragboard db = event.getDragboard();
         if (db.hasString() || db.hasHtml() || db.hasRtf() || 
             db.hasUrl()) {
            label.setBorder(borderGreen);
         }
         else {
            label.setBorder(borderRed);
         }
         event.consume();
      });
 
      label.addEventHandler(DragEvent.DRAG_EXITED, event -> {
         label.setBorder(borderDefault);
         event.consume();
      });

      label.addEventHandler(DragEvent.DRAG_DROPPED, event -> {
         Dragboard db = event.getDragboard();
         boolean success = false;
         StringBuilder sb = new StringBuilder();
         if (db.hasString()) {
            sb.append(db.getString());
            sb.append("\n");
            success = true;
         }
         if (db.hasHtml()) {
            sb.append(db.getHtml());
            sb.append("\n");
            success = true;
         }
         if (db.hasRtf()) {
            sb.append(db.getRtf());
            sb.append("\n");
            success = true;
         }
         if (db.hasUrl()) {
            sb.append(db.getUrl());
            sb.append("\n");
            success = true;
         }
         event.setDropCompleted(success);
         event.consume();
         if (success) label.setText(sb.toString());
         else      label.setText("Не удалось сбросить");
      });


      window.setScene(scene);
      window.setTitle("Обработка сброса");
      window.show();
   }
   
   public void newWindow3() {
      Stage window = new Stage();
      VBox pane = new VBox(5.0);
      pane.setAlignment(Pos.CENTER);
      pane.setPadding(new Insets(20.0));
      Label label = new Label("Перетащите этот текст в любой текстовый редактор");
      label.setBorder(new Border(
            new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,
                  CornerRadii.EMPTY, new BorderWidths(3.0), new Insets(5.0))));
      label.setPadding(new Insets(10.0));
      label.setPrefSize(300.0, 300.0);
      label.setWrapText(true);
      label.setAlignment(Pos.CENTER);
      Label label2 = new Label();
      pane.getChildren().addAll(label, label2);
      Scene scene = new Scene(pane, 400, 400);
      
      scene.addEventFilter(DragEvent.ANY, event -> {
         System.out.println(event.getEventType());
      });

      label.addEventHandler(MouseEvent.DRAG_DETECTED, event -> {
         Dragboard db = label.startDragAndDrop(TransferMode.COPY_OR_MOVE);
         try {
            db.setDragView(new Image(
               getClass().getResourceAsStream("blue32x32.png")));
            db.setDragViewOffsetX(32.0);
            db.setDragViewOffsetY(32.0);
         } catch (Exception e) {
            System.out.println("Не удалось загрузить картинку");
         }
         ClipboardContent content = new ClipboardContent();
         content.putString(label.getText());
         db.setContent(content);
         event.consume();
      });
      
      label.addEventHandler(DragEvent.DRAG_DONE, event -> {
         System.out.println("DRAG_DONE");
         if (event.isAccepted()) {
            label2.setText("Сброс выполнен удачно. Режим: " +
                               event.getTransferMode());
         }
         else {
            label2.setText("Сброс не выполнен");
         }
         System.out.println("Режим: " +
               event.getTransferMode());
      });

      window.setScene(scene);
      window.setTitle("Класс Dragboard");
      window.show();
   }
}
    
