package com.example.component_exemple;/*
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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.image.BufferedImage;
import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class Clipbrd extends Application {

 private final DataFormat df = new DataFormat("type/my_type");
   
   public static void main(String[] args) {
      Application.launch(args);
   }

   @Override
   public void start(Stage stage) throws Exception {
      VBox root = new VBox(15.0);
      root.setAlignment(Pos.CENTER);
      Button button1 = new Button("Получить данные из буфера");
      Button button2 = new Button("Добавить данные в буфер");
      root.getChildren().addAll(button2, button1);
      Scene scene = new Scene(root, 400.0, 300.0);
      stage.setTitle("Работа с буфером обмена");
      stage.setScene(scene);
      button1.setOnAction(event -> {
         newWindow();
      });
      button2.setOnAction(event -> {
         newWindow2();
      });
      stage.show();
   }
   
   public void newWindow() {
      Stage window = new Stage();
      VBox pane = new VBox(5.0);
      pane.setAlignment(Pos.CENTER);
      pane.setPadding(new Insets(20.0));
      Button button = new Button("Получить данные из буфера");
      Button button2 = new Button("Очистить буфер");
      Label label = new Label("DataFormat.PLAIN_TEXT");
      TextField text = new TextField();
      Label label2 = new Label("DataFormat.HTML");
      TextField text2 = new TextField();
      Label label3 = new Label("DataFormat.RTF");
      TextField text3 = new TextField();

      Label label7 = new Label("DataFormat.IMAGE");
      TextField text7 = new TextField();
      
      Label label4 = new Label("");
      label4.setWrapText(true);

      pane.getChildren().addAll(button, button2, label, text, label2, text2, label3, 
                                text3,  label7, text7, label4);
      button.setOnAction(event -> {
         text.setText("");
         text2.setText("");
         text3.setText("");
         text7.setText("");
         Clipboard cb = Clipboard.getSystemClipboard();
         if (cb.hasString()) {
            text.setText(cb.getString());
         }
         if (cb.hasHtml()) {
            text2.setText(cb.getHtml());
         }
         if (cb.hasRtf()) {
            text3.setText(cb.getRtf());
         }

         if (cb.hasImage()) {
            try {
               Image img = cb.getImage();
               text7.setText("Image: w=" + img.getWidth() + 
                                   " h=" + img.getHeight());
               newWindow3(img, "Изображение из буфера обмена - getImage() JavaFX");
            } catch (Exception e) {
               text7.setText("Ошибка получения изображения");
            }
            Transferable tr = 
                  Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null);
            if (tr != null && tr.isDataFlavorSupported(DataFlavor.imageFlavor)) {
               try {
                  java.awt.Image im = (java.awt.Image) tr.getTransferData(
                                                       DataFlavor.imageFlavor);
                  if (im != null) {
                     BufferedImage bi = new BufferedImage(im.getWidth(null),
                                   im.getHeight(null), BufferedImage.TYPE_INT_ARGB_PRE);
                     Graphics bg = bi.getGraphics();
                     bg.drawImage(im, 0, 0, null);
                     bg.dispose();
                     //Image img = SwingFXUtils.toFXImage(bi, null);
                    // newWindow3(img, "Изображение из буфера обмена - getTransferData() AWT");
                  }
               } catch (Exception e) {
                  System.out.println("Ошибка получения изображения");
               }
            }
         }
        
         if (cb.hasContent(df)) {
            Point p = (Point) cb.getContent(df);
            System.out.println(p);
         }
         label4.setText("Типы: " + cb.getContentTypes().toString());
      });
      button2.setOnAction(event -> {
         text.setText("");
         text2.setText("");
         text3.setText("");
         text7.setText("");
         label4.setText("");
         Clipboard cb = Clipboard.getSystemClipboard();
         cb.clear();
      });
      Scene scene = new Scene(pane, 400, 500);
      window.setScene(scene);
      window.setTitle("Получение данных из буфера обмена");
      window.show();
   }
   
   public void newWindow2() {
      Stage window = new Stage();
      VBox pane = new VBox(15.0);
      pane.setAlignment(Pos.CENTER);
      pane.setPadding(new Insets(20.0));
      Button button = new Button("Добавить текст в буфер обмена");
      Button button3 = new Button("Добавить объект в буфер обмена");
      Button button4 = new Button("Добавить изображение в буфер обмена");
      Button button2 = new Button("Очистить поля");
      Label label = new Label("DataFormat.PLAIN_TEXT");
      TextField text = new TextField();
      Label label2 = new Label("DataFormat.HTML");
      TextField text2 = new TextField();
      Label label3 = new Label("DataFormat.RTF");
      TextField text3 = new TextField();
      pane.getChildren().addAll(label, text, label2, text2, label3, 
                                text3, button, button2, button3, button4);
      button.setOnAction(event -> {
         String txt = text.getText();
         String txt2 = text2.getText();
         String txt3 = text3.getText();
         Clipboard cb = Clipboard.getSystemClipboard();
         ClipboardContent content = new ClipboardContent();
         if (txt.length() != 0) {
            content.putString(txt);
         }
         if (txt2.length() != 0) {
            content.putHtml(txt2);
         }
         if (txt3.length() != 0) {
            content.putRtf(txt3);
         }
         cb.setContent(content);

         text.setText("");
         text2.setText("");
         text3.setText("");
      });
      button2.setOnAction(event -> {
         text.setText("");
         text2.setText("");
         text3.setText("");
      });
      button3.setOnAction(event -> {
         Clipboard cb = Clipboard.getSystemClipboard();
         ClipboardContent content = new ClipboardContent();
         content.put(df, new Point(10, 20));
         cb.setContent(content);
      });
      button4.setOnAction(event -> {
         try { 
           Image im = new Image(
                           getClass().getResourceAsStream("image.png"));
            Clipboard cb = Clipboard.getSystemClipboard();
            ClipboardContent content = new ClipboardContent();
            if (content.putImage(im)) {
               if (!cb.setContent(content)) {
                  System.out.println("Error setContent()");
               }
            }
            else {
               System.out.println("Error putImage()");
            }
         } catch (Exception e) {
            System.out.println("Не удалось загрузить изображение"+e);
         }
      });
      
      Scene scene = new Scene(pane, 400, 500);
      window.setScene(scene);
      window.setTitle("Добавление данных в буфер обмена");
      window.show();
   }
   
   public void newWindow3(Image img, String title) {
      Stage window = new Stage();
      ScrollPane pane = new ScrollPane();
      ImageView iv = new ImageView();
      iv.setImage(img);
      pane.setContent(iv);
      Scene scene = new Scene(pane, 600, 600);
      window.setScene(scene);
      window.setTitle(title);
      window.show();
   }
}

class Point implements Serializable {
   private static final long serialVersionUID = -730791726267447983L;
   public double x, y;

   public Point(double x, double y) {
      this.x = x;
      this.y = y;
   }
   @Override
   public String toString() {
      return "Point [x=" + x + ", y=" + y + "]";
   }
}