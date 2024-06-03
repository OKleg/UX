package com.example.lab1;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        AnchorPane ap = new AnchorPane();
        VBox menuProfil = new VBox(
                new Hyperlink("О пользователе"),
                new Hyperlink("Оценки"),
                new Hyperlink("Календарь"),
                new Hyperlink("Сообщения"),
                new Hyperlink("Личные файлы"),
                new Hyperlink("Отчеты"),
                new Hyperlink("Настройки"),
                new Hyperlink("Выход")
        );
        // upper-right sign out control
        TitledPane profilPane = new TitledPane("Профиль", menuProfil);
        profilPane.setExpanded(false);

        AnchorPane.setTopAnchor( profilPane, 10.0d );
        AnchorPane.setRightAnchor( profilPane, 10.0d );
        Label Title = new Label("Name Web App");
        VBox languages = new VBox(
                new Label("Русский(ru)"),
                new Label("English(en)")
        );
        TitledPane languagePane = new TitledPane("Язык", languages);
        ObservableList<String> lang =
                FXCollections.observableArrayList("Русский(ru)", "English(en)");
        ComboBox<String> cbLanguage = new ComboBox<>(lang);
        cbLanguage.setValue(lang.get(0));
        languagePane.setExpanded(false);
        HBox leftTopCorner = new HBox(5,Title,cbLanguage);
        leftTopCorner.setPadding(new Insets(5,5,5,5));
        AnchorPane.setTopAnchor( leftTopCorner, 10.0d );
        AnchorPane.setLeftAnchor( leftTopCorner, 10.0d );
        // lower-left status label
        Hyperlink statusLabel = new Hyperlink("Служба поддержки");
        ap.getChildren().add( statusLabel );

        AnchorPane.setBottomAnchor( statusLabel, 10.0d );
        AnchorPane.setLeftAnchor( statusLabel, 10.0d );



        Hyperlink connLabel = new Hyperlink("Соцсети");

        HBox connHBox = new HBox();
        connHBox.setSpacing( 4.0d );
        connHBox.setAlignment(Pos.BOTTOM_RIGHT);
        connHBox.getChildren().addAll( connLabel );

        AnchorPane.setBottomAnchor( connHBox, 10.0d );
        AnchorPane.setRightAnchor( connHBox, 10.0d );

        ap.getChildren().add( connHBox );
        TilePane tilePane = new TilePane(Orientation.HORIZONTAL,5.0,5.0);
        tilePane.setPrefColumns(3);
        tilePane.setTileAlignment( Pos.CENTER );


        FlowPane rightFlow = new FlowPane();
        rightFlow.getChildren().add(new Button("Главная"));
        rightFlow.getChildren().add(new Button("Новости"));
        rightFlow.getChildren().add(new Button("Наши достижения"));
        rightFlow.getChildren().add(new Button("Материалы"));
        rightFlow.getChildren().add(new Button("О себе"));
        rightFlow.getChildren().add(new Button("Контакты"));
        rightFlow.setOrientation(Orientation.VERTICAL);
        rightFlow.setPadding(new Insets(10));
        rightFlow.setVgap(5);
        VBox Navigation = new VBox(new Label("Навигация"),
                rightFlow
                );

        Navigation.setAlignment(Pos.CENTER_LEFT);
        Navigation.setPadding(new Insets(10,10,10,10));

        TilePane tpCurses = new TilePane();
        tpCurses.setPrefColumns(3);
        tpCurses.setPrefRows(3);
        tpCurses.setTileAlignment( Pos.CENTER_LEFT );

        tpCurses.getChildren().addAll(
                new Circle(40, Color.RED),
                new Rectangle( 80, 80, Color.GREEN ),
                new Rectangle( 80, 80, Color.BLUE ),
                new Circle( 40,  Color.YELLOW ),
                new Rectangle( 80, 80, Color.CYAN ),
                new Rectangle( 80, 80, Color.PURPLE ),
                new Circle( 40,  Color.BROWN ),
                new Rectangle( 80, 80, Color.PINK ),
                new Rectangle( 80, 80, Color.ORANGE )
        );
        tpCurses.setPrefTileHeight(85);
        tpCurses.setPrefTileWidth(82);

        VBox Courses = new VBox(new Label("Курсы"), tpCurses);
        Courses.setAlignment(Pos.CENTER_LEFT);
        Courses.setPadding(new Insets(10,10,10,10));

        VBox Other = new VBox(
                new VBox(
                    new Text("Контакты"),
                    new Text("Email: mmcs@sdedu.ru"),
                    new Text("Иванов Иван Иванович")
                ),
                new VBox(
                        new Rectangle( 150, 200, Color.GREEN )
                )
        );
        Other.setPadding(new Insets(10,10,10,10));
        Other.setAlignment(Pos.CENTER_RIGHT);
        tilePane.getChildren().addAll(Navigation,Courses,Other);



//        tilePane.setPrefTileHeight(3);
        //tilePane.setPrefTileWidth(70);
        AnchorPane.setTopAnchor( tilePane, 40.0d );
        AnchorPane.setBottomAnchor( tilePane, 40.0d );
        AnchorPane.setRightAnchor( tilePane, 10.0d );
        AnchorPane.setLeftAnchor( tilePane, 10.0d );
        AnchorPane.setBottomAnchor(Courses,10.0d);
        AnchorPane.setTopAnchor(Courses,10.0d);
        AnchorPane.setRightAnchor(Other,10.0d);
        AnchorPane.setBottomAnchor(Other,10.0d);
        AnchorPane.setTopAnchor(Other,10.0d);
        ap.getChildren().add(Other);
        ap.getChildren().add( tilePane );
        ap.getChildren().add( leftTopCorner );
        ap.getChildren().add( profilPane );

        Scene scene = new Scene(ap);

        stage.setTitle("Использование компоновщиков");
        stage.setScene( scene );
        stage.setMaxWidth(800);
        stage.setMaxHeight(450);
        stage.setMinWidth(780);
        stage.setWidth(800);
        stage.setHeight(450);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}