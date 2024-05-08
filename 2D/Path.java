package com.lab2d.demo;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.ClosePath;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.FillRule;
import javafx.scene.shape.HLineTo;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.PathElement;
import javafx.scene.shape.QuadCurveTo;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.VLineTo;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Path extends Application {

   public static void main(String[] args) {
      Application.launch(args);
   }

   public void start(Stage stage) throws Exception {
      VBox root = new VBox(15.0);
      root.setAlignment(Pos.CENTER);
      root.setPadding(new Insets(20.0));
      Button button = new Button("Открыть окно");
      Button button2 = new Button("fillRule");
      Button button3 = new Button("LineTo");
      Button button4 = new Button("ArcTo");
      Button button5 = new Button("CubicCurveTo");
      Button button6 = new Button("QuadCurveTo");
      
      button.setOnAction(event -> {
         newWindow();
      });
      button2.setOnAction(event -> {
         newWindow2();
      });
      button3.setOnAction(event -> {
         newWindow3();
      });
      button4.setOnAction(event -> {
         newWindow4();
      });
      button5.setOnAction(event -> {
         newWindow5();
      });
      button6.setOnAction(event -> {
         newWindow6();
      });
      root.getChildren().addAll(button, button2, button3, button4, button5, button6);
      Scene scene = new Scene(root, 400.0, 300.0);
      stage.setTitle("Класс Path");
      stage.setScene(scene);
      stage.show();
   }

   public void newWindow() {
      Stage window = new Stage();
      Pane pane = new Pane();
      
      Path p = new Path();
      MoveTo moveTo = new MoveTo();
      moveTo.setX(20.0);
      moveTo.setY(20.0);
      System.out.println(moveTo.getX());
      System.out.println(moveTo.getY());
     
      VLineTo line1 = new VLineTo();
      line1.setY(200.0);
      HLineTo line2 = new HLineTo();
      line2.setX(200.0);
      p.getElements().addAll(moveTo, line1, line2);
      p.setFill(Color.RED);
      p.setStroke(Color.BLACK);
      p.setStrokeWidth(3.0);
      
      Path p2 = new Path(
            new MoveTo(250.0, 20.0),
            new VLineTo(200.0),
            new HLineTo(400.0),
            new ClosePath()
      );
      p2.setFill(Color.GREEN);
      p2.setStroke(Color.BLACK);
      p2.setStrokeWidth(3.0);
      
      ArrayList<PathElement> list = new ArrayList<PathElement>();
      list.add(new MoveTo(20.0, 300.0));
      list.add(new LineTo(200.0, 350.0));
      list.add(new LineTo(20.0, 450.0));
      list.add(new ClosePath());
      Path p3 = new Path(list);
      p3.setStroke(Color.BLACK);
      p3.setStrokeWidth(3.0);
      
      Path p4 = new Path();
      p4.getElements().add(new MoveTo(250.0, 300.0));
      p4.getElements().addAll(new HLineTo(450.0), new VLineTo(450.0));
      
      Path p5 = new Path();
      p5.setStroke(Color.RED);
      p5.getElements().add(new MoveTo(250.0, 350.0));
      HLineTo hline = new HLineTo(400.0);
      hline.setAbsolute(true);
      VLineTo vline = new VLineTo(50.0);
      vline.setAbsolute(false);
      p5.getElements().addAll(hline, vline);

      Path p6 = new Path(
            new MoveTo(250.0, 430.0),
            new HLineTo(400.0),
            new MoveTo(250.0, 440.0),
            new HLineTo(400.0),
            new MoveTo(250.0, 450.0),
            new HLineTo(400.0)
      );
      p6.setStroke(Color.GREEN);
      p6.setStrokeWidth(1.0);
      
      pane.getChildren().addAll(p, p2, p3, p4, p5, p6);
      Scene scene = new Scene(pane, 500, 500);
      window.setScene(scene);
      window.setTitle("Path");
      window.show();
   }
   
   public void newWindow2() {
      Stage window = new Stage();
      Pane pane = new Pane();
      
      Text text1 = new Text("FillRule.NON_ZERO");
      text1.relocate(80.0, 20.0);
      
      Text text2 = new Text("FillRule.EVEN_ODD");
      text2.relocate(280.0, 20.0);
      
      
      Path p = new Path(
            new MoveTo(30.0, 100.0),
            new LineTo(170.0, 100.0),
            new LineTo(60.0, 200.0),
            new LineTo(100.0, 40.0),
            new LineTo(140.0, 200.0),
            new ClosePath()
      );
      p.setFill(Color.BLUE);
      p.setStroke(Color.BLACK);
      p.setStrokeWidth(3.0);
      p.setFillRule(FillRule.NON_ZERO);
      p.relocate(50.0, 50.0);
      
      Path p2 = new Path(
            new MoveTo(30.0, 100.0),
            new LineTo(170.0, 100.0),
            new LineTo(60.0, 200.0),
            new LineTo(100.0, 40.0),
            new LineTo(140.0, 200.0),
            new ClosePath()            
      );
      p2.setFill(Color.BLUE);
      p2.setStroke(Color.BLACK);
      p2.setStrokeWidth(3.0);
      p2.setFillRule(FillRule.EVEN_ODD);
      p2.relocate(250.0, 50.0);
      
      Path p3 = new Path(
            new MoveTo(100.0, 0.0),
            new LineTo(150.0, 100.0),
            new LineTo(50.0, 100.0),
            new ClosePath(),
            new MoveTo(140.0, 40.0),
            new LineTo(190.0, 140.0),
            new LineTo(90.0, 140.0),
            new ClosePath()          
      );
      p3.setFill(Color.CHOCOLATE);
      p3.setStroke(Color.BLACK);
      p3.setStrokeWidth(3.0);
      p3.setFillRule(FillRule.NON_ZERO);
      p3.relocate(50.0, 250.0);
      
      Path p4 = new Path(
            new MoveTo(100.0, 0.0),
            new LineTo(150.0, 100.0),
            new LineTo(50.0, 100.0),
            new ClosePath(),
            new MoveTo(140.0, 40.0),
            new LineTo(190.0, 140.0),
            new LineTo(90.0, 140.0),
            new ClosePath() 
      );
      p4.setFill(Color.CHOCOLATE);
      p4.setStroke(Color.BLACK);
      p4.setStrokeWidth(3.0);
      p4.setFillRule(FillRule.EVEN_ODD);
      p4.relocate(250.0, 250.0);
      
      Path p5 = new Path(
            new MoveTo(100.0, 0.0),
            new LineTo(150.0, 100.0),
            new LineTo(50.0, 100.0),
            new ClosePath(),
            new MoveTo(140.0, 40.0),
            new LineTo(90.0, 140.0),
            new LineTo(190.0, 140.0),
            new ClosePath()          
      );
      p5.setFill(Color.CORNFLOWERBLUE);
      p5.setStroke(Color.BLACK);
      p5.setStrokeWidth(3.0);
      p5.setFillRule(FillRule.NON_ZERO);
      p5.relocate(50.0, 450.0);
      
      Path p6 = new Path(
            new MoveTo(100.0, 0.0),
            new LineTo(150.0, 100.0),
            new LineTo(50.0, 100.0),
            new ClosePath(),
            new MoveTo(140.0, 40.0),
            new LineTo(90.0, 140.0),
            new LineTo(190.0, 140.0),
            new ClosePath()   
      );
      p6.setFill(Color.CORNFLOWERBLUE);
      p6.setStroke(Color.BLACK);
      p6.setStrokeWidth(3.0);
      p6.setFillRule(FillRule.EVEN_ODD);
      p6.relocate(250.0, 450.0);

      Path arrow1 = new Path(
            new MoveTo(70.0, 50.0),
            new HLineTo(100.0),
            new LineTo(93.0, 53.0),
            new VLineTo(47.0),
            new LineTo(100.0, 50.0)
      );
      arrow1.relocate(10.0, 10.0);
      arrow1.setStroke(Color.BLACK);
      arrow1.setFill(Color.BLACK);
      arrow1.setRotate(60.0);
      arrow1.relocate(110.0, 270.0);
      
      Path arrow2 = new Path(
            new MoveTo(70.0, 50.0),
            new HLineTo(100.0),
            new LineTo(93.0, 53.0),
            new VLineTo(47.0),
            new LineTo(100.0, 50.0)
      );
      arrow2.relocate(10.0, 10.0);
      arrow2.setStroke(Color.BLACK);
      arrow2.setFill(Color.BLACK);
      arrow2.setRotate(60.0);
      arrow2.relocate(110.0, 470.0);
      
      Path arrow3 = new Path(
            new MoveTo(70.0, 50.0),
            new HLineTo(100.0),
            new LineTo(93.0, 53.0),
            new VLineTo(47.0),
            new LineTo(100.0, 50.0)
      );
      arrow3.relocate(10.0, 10.0);
      arrow3.setStroke(Color.BLACK);
      arrow3.setFill(Color.BLACK);
      arrow3.setRotate(-120.0);
      arrow3.relocate(160.0, 520.0);
      
      Path arrow4 = new Path(
            new MoveTo(70.0, 50.0),
            new HLineTo(100.0),
            new LineTo(93.0, 53.0),
            new VLineTo(47.0),
            new LineTo(100.0, 50.0)
      );
      arrow4.relocate(10.0, 10.0);
      arrow4.setStroke(Color.BLACK);
      arrow4.setFill(Color.BLACK);
      arrow4.setRotate(60.0);
      arrow4.relocate(160.0, 320.0);
      
      pane.getChildren().addAll(text1, p, text2, p2, p3, p4, p5, p6,
                                arrow1, arrow2, arrow3, arrow4);
      Scene scene = new Scene(pane, 500, 650);
      window.setScene(scene);
      window.setTitle("fillRule");
      window.show();
   }
   
   public void newWindow3() {
      Stage window = new Stage();
      Pane pane = new Pane();
      
      VLineTo vline = new VLineTo();
      vline.setY(200.0);
      
      HLineTo hline = new HLineTo();
      hline.setX(200.0);

      LineTo line = new LineTo();
      line.setX(50.0);
      line.setY(20.0);
      
      System.out.println(hline.getX());
      System.out.println(vline.getY());
      System.out.println(line.getX());
      System.out.println(line.getY());
      
      Path p = new Path(new MoveTo(50.0, 20.0), vline, hline, line);
      p.setStroke(Color.BLACK);
      p.setStrokeWidth(3.0);
      
      VLineTo vline2 = new VLineTo(200.0);
      
      HLineTo hline2 = new HLineTo(400.0);
     
      LineTo line2 = new LineTo(250.0, 20.0);
      
      Path p2 = new Path(
            new MoveTo(250.0, 20.0),
            vline2,
            hline2,
            new VLineTo(100.0),
            line2
      );
      p2.setStroke(Color.BLACK);
      p2.setStrokeWidth(3.0);

      Path p3 = new Path(
            new MoveTo(20.0, 250.0),
            new VLineTo(400.0),
            new HLineTo(200.0),
            new LineTo(20.0, 250.0)
      );
      p3.setStroke(Color.BLACK);
      p3.setStrokeWidth(3.0);
      p3.setFill(Color.DARKSALMON);
      
      pane.getChildren().addAll(p, p2, p3);
      Scene scene = new Scene(pane, 500, 500);
      window.setScene(scene);
      window.setTitle("LineTo");
      window.show();
   }
   
   public void newWindow4() {
      Stage window = new Stage();
      Pane pane = new Pane();
      
      ArcTo arc = new ArcTo();
      //ArcTo arc = new ArcTo(100.0, 100.0, 0.0, 400.0, 100.0, false, false);
      arc.setX(400.0);
      arc.setY(100.0);
      arc.setRadiusX(100.0);
      arc.setRadiusY(100.0);
      //arc.setSweepFlag(true);
      //arc.setLargeArcFlag(true);
      //arc.setXAxisRotation(0.0);
      
      System.out.println(arc.getX());
      System.out.println(arc.getY());
      System.out.println(arc.getRadiusX());
      System.out.println(arc.getRadiusY());
      
      System.out.println(arc.getXAxisRotation());
      System.out.println(arc.isSweepFlag());
      System.out.println(arc.isLargeArcFlag());
      
      Path p = new Path(new MoveTo(200.0, 100.0), arc);
      p.setStroke(Color.BLACK);
      p.setStrokeWidth(3.0);
      
      Line line1 = new Line(400.0, 100.0, 400.0, 100.0);
      line1.setStroke(Color.RED);
      line1.setStrokeWidth(7.0);
      line1.setStrokeLineCap(StrokeLineCap.ROUND);
      Line line2 = new Line(200.0, 100.0, 200.0, 100.0);
      line2.setStroke(Color.RED);
      line2.setStrokeWidth(7.0);
      line2.setStrokeLineCap(StrokeLineCap.ROUND);
      
      Slider s1 = new Slider(0.0, 300.0, 100.0);
      Slider s2 = new Slider(0.0, 300.0, 100.0);
      Slider s3 = new Slider(0.0, 360.0, 0.0);
      s1.setShowTickLabels(true);
      s2.setShowTickLabels(true);
      s3.setShowTickLabels(true);
      
      s1.valueProperty().addListener((obj, oldValue, newValue) -> {
         arc.setRadiusX(newValue.doubleValue());
      });
      s2.valueProperty().addListener((obj, oldValue, newValue) -> {
         arc.setRadiusY(newValue.doubleValue());
      });
      s3.valueProperty().addListener((obj, oldValue, newValue) -> {
         arc.setXAxisRotation(newValue.doubleValue());
      });
      s1.relocate(20.0, 350.0);
      s2.relocate(20.0, 400.0);
      s3.relocate(20.0, 450.0);
      s1.setPrefWidth(520.0);
      s2.setPrefWidth(520.0);
      s3.setPrefWidth(520.0);
      
      Button button1 = new Button("largeArcFlag " + arc.isLargeArcFlag());
      Button button2 = new Button("sweepFlag " + arc.isSweepFlag());
      button1.relocate(20.0, 500.0);
      button2.relocate(300.0, 500.0);
      button1.setOnAction(event -> {
         boolean b = !arc.isLargeArcFlag();
         button1.setText("largeArcFlag  " + b);
         arc.setLargeArcFlag(b);
      });
      button2.setOnAction(event -> {
         boolean b = !arc.isSweepFlag();
         button2.setText("sweepFlag " + b);
         arc.setSweepFlag(b);
      });
      
      Text text1 = new Text("RadiusX");
      Text text2 = new Text("RadiusY");
      Text text3 = new Text("XAxisRotation");
      text1.relocate(30.0, 330.0);
      text2.relocate(30.0, 380.0);
      text3.relocate(30.0, 430.0);
      
      pane.getChildren().addAll(p, line1, line2, s1, s2, s3,
            button1, button2, text1, text2, text3);
      Scene scene = new Scene(pane, 600, 600);
      window.setScene(scene);
      window.setTitle("ArcTo");
      window.show();
   }
   
   public void newWindow5() {
      Stage window = new Stage();
      Pane pane = new Pane();
      
      CubicCurveTo cubic = new CubicCurveTo();
      //CubicCurveTo cubic = new CubicCurveTo(300.0, 250.0, 300.0, 50.0, 400.0, 150.0);
      cubic.setControlX1(300.0);
      cubic.setControlY1(250.0);
      cubic.setControlX2(300.0);
      cubic.setControlY2(50.0);
      cubic.setX(400.0);
      cubic.setY(150.0);
      
      System.out.println(cubic.getX());
      System.out.println(cubic.getY());
      System.out.println(cubic.getControlX1());
      System.out.println(cubic.getControlY1());
      System.out.println(cubic.getControlX2());
      System.out.println(cubic.getControlY2());
      
      Path p = new Path(new MoveTo(100.0, 150.0), new HLineTo(200.0), cubic);
      p.setStroke(Color.DARKBLUE);
      p.setStrokeWidth(3.0);
      
      Line line1 = new Line(400.0, 150.0, 400.0, 150.0);
      line1.setStroke(Color.RED);
      line1.setStrokeWidth(7.0);
      line1.setStrokeLineCap(StrokeLineCap.ROUND);
      Line line2 = new Line(200.0, 150.0, 200.0, 150.0);
      line2.setStroke(Color.RED);
      line2.setStrokeWidth(7.0);
      line2.setStrokeLineCap(StrokeLineCap.ROUND);
      Line line3 = new Line(300.0, 250.0, 300.0, 250.0);
      line3.setStroke(Color.ORANGE);
      line3.setStrokeWidth(7.0);
      line3.setStrokeLineCap(StrokeLineCap.ROUND);
      Line line4 = new Line(300.0, 50.0, 300.0, 50.0);
      line4.setStroke(Color.DARKGREEN);
      line4.setStrokeWidth(7.0);
      line4.setStrokeLineCap(StrokeLineCap.ROUND);
      
      Line line5 = new Line(300.0, 250.0, 200.0, 150.0);
      Line line6 = new Line(300.0, 50.0, 400.0, 150.0);
      
      pane.getChildren().addAll(p, line5, line6, line1, line2, line3, line4);
      Scene scene = new Scene(pane, 500, 400);
      window.setScene(scene);
      window.setTitle("CubicCurveTo");
      window.show();
   }
   
   public void newWindow6() {
      Stage window = new Stage();
      Pane pane = new Pane();
      
      QuadCurveTo quad = new QuadCurveTo();
      //QuadCurveTo quad = new QuadCurveTo(300.0, 250.0, 400.0, 150.0);
      quad.setX(400.0);
      quad.setY(150.0);
      quad.setControlX(300.0);
      quad.setControlY(250.0);
      
      System.out.println(quad.getX());
      System.out.println(quad.getY());
      System.out.println(quad.getControlX());
      System.out.println(quad.getControlY());
      
      Path p = new Path(new MoveTo(100.0, 150.0), new HLineTo(200.0), quad);
      p.setStroke(Color.DARKBLUE);
      p.setStrokeWidth(3.0);

      Line line1 = new Line(400.0, 150.0, 400.0, 150.0);
      line1.setStroke(Color.RED);
      line1.setStrokeWidth(7.0);
      line1.setStrokeLineCap(StrokeLineCap.ROUND);
      Line line2 = new Line(200.0, 150.0, 200.0, 150.0);
      line2.setStroke(Color.RED);
      line2.setStrokeWidth(7.0);
      line2.setStrokeLineCap(StrokeLineCap.ROUND);
      Line line3 = new Line(300.0, 250.0, 300.0, 250.0);
      line3.setStroke(Color.ORANGE);
      line3.setStrokeWidth(7.0);
      line3.setStrokeLineCap(StrokeLineCap.ROUND);

      pane.getChildren().addAll(p, line1, line2, line3);
      Scene scene = new Scene(pane, 500, 400);
      window.setScene(scene);
      window.setTitle("QuadCurveTo");
      window.show();
   }
}
