package com.example.ux;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.stage.StageStyle;
/*text2.setFont(Font.font(family, FontWeight.BOLD, size));
 */
import java.util.*;

public class Main extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }
     ///добавляем метод создания окна newWind()
    public void Wind1(Stage stage) {
        Stage wind = new Stage(StageStyle.DECORATED);
        TextField tf = new TextField();
        wind.initModality(Modality.NONE);
        wind.initStyle(StageStyle.UTILITY);
        VBox root = new VBox(15.0);
        Scene scene = new Scene(root,120.0, 50.0);
        root.getChildren().addAll(tf);
        tf.addEventHandler(ActionEvent.ACTION, event -> {
            tf.getText();
            try{
                var coef = Double.parseDouble(tf.getText());
                if (coef>0)
                {
                    if (!stage.isShowing()) stage.show();
                    stage.setWidth(stage.getWidth() * coef );
                    stage.setHeight(stage.getHeight() * coef );

                }
                else stage.close();
            }
            catch (NumberFormatException e){
                System.out.println(e.toString());
            }
            System.out.println("tf - Handler");

            // event.consume();
        });

        wind.setTitle("Wind1");
        wind.setScene(scene);
        wind.show();
    }
    public void Wind2() {
        Stage wind = new Stage(StageStyle.DECORATED);
        wind.initStyle(StageStyle.UTILITY);

        VBox vbox = new VBox();
        HBox hBox1 = new HBox();
        HBox hBox2 = new HBox();
        HBox hBox3 = new HBox();
        HBox hBox4 = new HBox();
        HBox hBox5 = new HBox();
        HBox hBox6 = new HBox();
        Label label1 = new Label("1");
        CheckBox checkBox1 = new CheckBox("1");
        hBox1.getChildren().addAll(label1, checkBox1);
        Label label2 = new Label("2");
        CheckBox checkBox2 = new CheckBox("2");
        hBox2.getChildren().addAll(label2, checkBox2);
        Label label3 = new Label("3");
        CheckBox checkBox3 = new CheckBox("3");
        hBox3.getChildren().addAll(label3, checkBox3);
        Label label4 = new Label("4");
        CheckBox checkBox4 = new CheckBox("4");
        hBox4.getChildren().addAll(label4, checkBox4);
        Label label5 = new Label("5");
        CheckBox checkBox5 = new CheckBox("5");
        hBox5.getChildren().addAll(label5, checkBox5);
        Label label6 = new Label("6");
        CheckBox checkBox6 = new CheckBox("6");
        hBox6.getChildren().addAll(label6, checkBox6);
        vbox.getChildren().addAll(
                hBox1,
                hBox2,
                hBox3,
                hBox4,
                hBox5,
                hBox6
        );
        vbox.setAlignment(Pos.TOP_CENTER);
        Scene scene = new Scene(vbox,150.0, 160.0);


        wind.setTitle("Wind2");
        wind.setScene(scene);
        wind.show();
    }
    public void Wind3() {
        Stage wind = new Stage();
        HBox root = new HBox();
        root.setAlignment(Pos.TOP_CENTER);
        Button addButton = new Button("Добавить");
        Button delButton = new Button("Удалить");
        root.getChildren().addAll(addButton, delButton);
        Scene scene3 = new Scene(root,150.0, 50.0);
        wind.setTitle("Wind3");
        wind.setScene(scene3);
        wind.show();
    }
    public HBox addTextFields() {
        VBox vbox = new VBox();
        HBox hBox1 = new HBox();
        HBox hBox2 = new HBox();
        HBox hBox3 = new HBox();
        HBox hBox4 = new HBox();
        HBox hBox5 = new HBox();
        HBox hBox6 = new HBox();
        Label label1 = new Label("1");
        TextField textField1 = new TextField("1");
        hBox1.getChildren().addAll(label1, textField1);
        Label label2 = new Label("2");
        TextField textField2 = new TextField("2");
        hBox2.getChildren().addAll(label2, textField2);
        Label label3 = new Label("3");
        TextField textField3 = new TextField("3");
        hBox3.getChildren().addAll(label3, textField3);
        Label label4 = new Label("4");
        TextField textField4 = new TextField("4");
        hBox4.getChildren().addAll(label4, textField4);
        Label label5 = new Label("5");
        TextField textField5 = new TextField("5");
        hBox5.getChildren().addAll(label5, textField5);
        Label label6 = new Label("6");
        TextField textField6 = new TextField("6");
        hBox6.getChildren().addAll(label6, textField6);
        vbox.getChildren().addAll(
                hBox1,
                hBox2,
                hBox3,
                hBox4,
                hBox5,
                hBox6
        );
        var scroll = new ScrollPane(vbox);
        TextArea  textArea = new TextArea();
        textArea.setDisable(true);
        HBox hBox = new HBox();
        hBox.getChildren().addAll(scroll,textArea);
        hBox.setAlignment(Pos.TOP_CENTER);

        return hBox;
    }
        @Override
    public void start(Stage stage) throws Exception {
        VBox root = new VBox();
        root.setAlignment(Pos.TOP_CENTER);
        stage.setResizable(false);
        Scene scene = new Scene(root, 500.0, 350.0);
        // ----- Add Text Field ----- //
        ArrayList<TextField> textFields = new ArrayList<TextField>(6);
        ArrayList<CheckBox> checkBoxes = new ArrayList<CheckBox>(6);
        VBox vboxTF = new VBox();
        VBox vboxCheck = new VBox();
        for (int i = 0; i < 6; i++) {
            var strLabel = String.valueOf(i+1);
            var textF = new TextField(strLabel);
            textFields.add(textF);
            vboxTF.getChildren().add(new HBox(new Label(strLabel),textF));
            vboxCheck.getChildren().add(new HBox(new Label(strLabel),new CheckBox(strLabel)));
        }
      /*  for (int i = 0; i < 6; i++) {
            vboxCheck.getChildren().add(new HBox(new Label(String.valueOf(i+1)),checkBoxes.get(i)));
        }*/
        var scroll = new ScrollPane(vboxTF);
        TextArea  textArea = new TextArea();
        textArea.setDisable(true);
        HBox hBoxTF = new HBox();
        hBoxTF.getChildren().addAll(scroll,textArea);
        hBoxTF.setAlignment(Pos.TOP_CENTER);
        // Config Buttons //
        Button button1 = new Button("Первая кнопка");
        Button button2 = new Button("Вторая кнопка");
        Button button3 = new Button("Третья кнопка");
        button1.setOnAction(event-> {
            Wind1(stage);
        });
        button2.setOnAction(event-> {
            Stage wind = new Stage(StageStyle.DECORATED);
            wind.initStyle(StageStyle.UTILITY);
            vboxCheck.setAlignment(Pos.TOP_CENTER);
            var scrollCheck = new ScrollPane(vboxCheck);
            Scene scene2 = new Scene(scrollCheck,150.0, 160.0);
            wind.setTitle("Wind2");
            wind.setScene(scene2);
            wind.show();
            });
            button3.setOnAction(event-> {
                Stage wind3 = new Stage();
                VBox root3 = new VBox();
                root3.setAlignment(Pos.TOP_CENTER);
                TextField textFieldNum = new TextField("1");
                Button addButton = new Button("Добавить");
                addButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        var l = String.valueOf(textFields.size()+1);
                        Label add_label =new Label(l);
                        TextField add_tf =  new TextField(l);
                        CheckBox add_check = new CheckBox(l);
                        textFields.add(add_tf);
                        checkBoxes.add(add_check);
                        vboxCheck.getChildren().add(new HBox(new Label(l),add_check));
                        vboxTF.getChildren().add(new HBox(new Label(l),add_tf));
                    }
                });
                Button delButton = new Button("Удалить");
                delButton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        if (textFields.size() > 0) {
                            var id = Integer.parseInt(textFieldNum.getText())-1;
                            textFields.remove(id);
                            vboxTF.getChildren().remove(id);
                        }
                        }
                });
                root3.getChildren().addAll(textFieldNum,addButton, delButton);
                root3.setSpacing(5);
                Scene scene3 = new Scene(root3,180.0, 120.0);
                wind3.setTitle("Wind3");
                wind3.setScene(scene3);
                wind3.show();
            });
            HBox hBox = new HBox();
            hBox.getChildren().addAll(button1,button2,button3);
            hBox.setAlignment(Pos.TOP_CENTER);
        // ----- Add Elements on Stage ----- //
        root.getChildren().addAll(hBoxTF);
        root.getChildren().addAll(hBox);
        // -----   Config Stage  ----- //
        stage.setTitle("Последовательность вызова обработчиков");
        stage.setScene(scene);
        stage.show();
    }
}