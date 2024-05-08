package com.lab2d.demo;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.ParallelCamera;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

public class CameraEx extends Application {
   
   public static void main(String[] args) {
      Application.launch(args);
   }

   @Override
   public void start(Stage stage) throws Exception {
      VBox root = new VBox(10);
      root.setAlignment(Pos.CENTER);
      Button button1 = new Button("Класс PerspectiveCamera");
      Button button2 = new Button("Класс ParallelCamera");
      
      root.getChildren().addAll(button1, button2);
      Scene scene = new Scene(root, 400, 300);
      
      button1.setOnAction(event -> {
         newWindow();
      });
      button2.setOnAction(event -> {
         newWindow2();
      });
      
      stage.setTitle("Camera");
      stage.setScene(scene);
      stage.show();
   }
   
   public void newWindow() {
      Stage window = new Stage();
      Box box = new Box(5.0, 5.0, 5.0);
      box.setMaterial(new PhongMaterial(Color.BLUE));
      box.setDrawMode(DrawMode.FILL);
      box.setCullFace(CullFace.BACK);
      box.setTranslateX(5.0);
      box.setTranslateY(5.0);
      box.setTranslateZ(5.0);
      
      PerspectiveCamera camera = new PerspectiveCamera(true);
      double trX = 20.0, trY = 11.0, trZ = 15.0;
      double aX = -30.0, aY = 230.0, aZ = 50.0;
      Translate tr = new Translate(trX, trY, trZ);
      Rotate rx = new Rotate(aX, Rotate.X_AXIS);
      Rotate ry = new Rotate(aY, Rotate.Y_AXIS);
      Rotate rz = new Rotate(aZ, Rotate.Z_AXIS);
      camera.getTransforms().addAll(tr, rx, ry, rz);
      camera.setFieldOfView(30.0);
      camera.setVerticalFieldOfView(true);
      
      System.out.println(camera.getFieldOfView());
      System.out.println(camera.isVerticalFieldOfView());
      System.out.println(camera.isFixedEyeAtCameraZero());

      
      Line lineX = new Line(-1000.0, 0.0, 1000.0, 0.0);
      lineX.setStroke(Color.RED);
      Line lineY = new Line(0.0, -1000.0, 0.0, 1000.0);
      lineY.setStroke(Color.GREEN);
      Line lineZ = new Line(0.0, -1000.0, 0.0, 1000.0);
      lineZ.setRotationAxis(Rotate.X_AXIS);
      lineZ.setRotate(90.0);
      lineZ.setStroke(Color.BLUE);
      
      Button btn = new Button("Показать/скрыть оси");
      btn.relocate(50.0, 510.0);
      btn.setOnAction(event -> {
         lineX.setVisible(!lineX.isVisible());
         lineY.setVisible(!lineY.isVisible());
         lineZ.setVisible(!lineZ.isVisible());
      });
      
      Group group = new Group();
      group.getChildren().addAll(box, lineX, lineY, lineZ);
      
      //SubScene subScene = new SubScene(group, 500, 300);
      SubScene subScene = new SubScene(group, 500, 300, true,
                                       SceneAntialiasing.BALANCED);
      subScene.setFill(Color.ANTIQUEWHITE);
      subScene.setCamera(camera);
      subScene.relocate(5.0, 5.0);
      
      Group root = new Group();
      Slider sliderX = new Slider(-100.0, 100.0, trX);
      sliderX.relocate(50.0, 330.0);
      sliderX.setPrefWidth(400.0);
      sliderX.setShowTickLabels(true);
      
      Slider sliderY = new Slider(-100.0, 100.0, trY);
      sliderY.relocate(50.0, 360.0);
      sliderY.setPrefWidth(400.0);
      sliderY.setShowTickLabels(true);
      
      Slider sliderZ = new Slider(-100.0, 100.0, trZ);
      sliderZ.relocate(50.0, 390.0);
      sliderZ.setPrefWidth(400.0);
      sliderZ.setShowTickLabels(true);
      
      Slider sliderRX = new Slider(-360.0, 360.0, aX);
      sliderRX.relocate(50.0, 420.0);
      sliderRX.setPrefWidth(400.0);
      sliderRX.setShowTickLabels(true);
      
      Slider sliderRY = new Slider(-360.0, 360.0, aY);
      sliderRY.relocate(50.0, 450.0);
      sliderRY.setPrefWidth(400.0);
      sliderRY.setShowTickLabels(true);
      
      Slider sliderRZ = new Slider(-360.0, 360.0, aZ);
      sliderRZ.relocate(50.0, 480.0);
      sliderRZ.setPrefWidth(400.0);
      sliderRZ.setShowTickLabels(true);
      
      Text text = new Text("X");
      text.relocate(30.0, 330.0);
      Text text2 = new Text("Y");
      text2.relocate(30.0, 360.0);
      Text text3 = new Text("Z");
      text3.relocate(30.0, 390.0);
      Text text4 = new Text("RX");
      text4.relocate(30.0, 420.0);
      Text text5 = new Text("RY");
      text5.relocate(30.0, 450.0);
      Text text6 = new Text("RZ");
      text6.relocate(30.0, 480.0);
      
      Text text7 = new Text("(x, y, z) -> (red, green, blue)");
      text7.relocate(300.0, 310.0);
      
      root.getChildren().addAll(subScene, sliderX, sliderY, sliderZ, sliderRX,
            sliderRY, sliderRZ,
            text, text2, text3, text4, text5, text6, btn, text7);

      sliderX.valueProperty().addListener((obj, oldValue, newValue) -> {
         tr.setX(newValue.doubleValue());
      });
      sliderY.valueProperty().addListener((obj, oldValue, newValue) -> {
         tr.setY(newValue.doubleValue());
      });
      
      sliderZ.valueProperty().addListener((obj, oldValue, newValue) -> {
         tr.setZ(newValue.doubleValue());
      });
      
      sliderRX.valueProperty().addListener((obj, oldValue, newValue) -> {
         rx.setAngle(newValue.doubleValue());
      });
      sliderRY.valueProperty().addListener((obj, oldValue, newValue) -> {
         ry.setAngle(newValue.doubleValue());
      });
      sliderRZ.valueProperty().addListener((obj, oldValue, newValue) -> {
         rz.setAngle(newValue.doubleValue());
      });
      
      Scene scene = new Scene(root, 510, 600, Color.BURLYWOOD);
      window.setScene(scene);
      window.setTitle("PerspectiveCamera");
      window.show();
      
      scene.setOnMouseClicked(event -> {
         if (event.getClickCount() > 1) {
            sliderX.setValue(trX);
            sliderY.setValue(trY);
            sliderZ.setValue(trZ);
            sliderRX.setValue(aX);
            sliderRY.setValue(aY);
            sliderRZ.setValue(aZ);
         }
      });
      
   }

   public void newWindow2() {
      Stage window = new Stage();
      Box box = new Box(50.0, 50.0, 50.0);
      box.setMaterial(new PhongMaterial(Color.BLUE));
      box.setDrawMode(DrawMode.FILL);
      box.setCullFace(CullFace.BACK);
      box.setTranslateX(5.0);
      box.setTranslateY(5.0);
      box.setTranslateZ(20.0);
      
      ParallelCamera camera = new ParallelCamera();
      double trX = -205.0, trY = -187.0, trZ = -114.0;
      double aX = -18.0, aY = -35.0, aZ = -10.0;
      Translate tr = new Translate(trX, trY, trZ);
      Rotate rx = new Rotate(aX, Rotate.X_AXIS);
      Rotate ry = new Rotate(aY, Rotate.Y_AXIS);
      Rotate rz = new Rotate(aZ, Rotate.Z_AXIS);
      camera.getTransforms().addAll(tr, rx, ry, rz);
      camera.setNearClip(0.1);
      camera.setFarClip(100.0);
      
      System.out.println(camera.getNearClip());
      System.out.println(camera.getFarClip());
      
      Line lineX = new Line(-1000.0, 0.0, 1000.0, 0.0);
      lineX.setStroke(Color.RED);
      Line lineY = new Line(0.0, -1000.0, 0.0, 1000.0);
      lineY.setStroke(Color.GREEN);
      Line lineZ = new Line(0.0, -1000.0, 0.0, 1000.0);
      lineZ.setRotationAxis(Rotate.X_AXIS);
      lineZ.setRotate(90.0);
      lineZ.setStroke(Color.BLUE);
      
      Button btn = new Button("Показать/скрыть оси");
      btn.relocate(50.0, 510.0);
      btn.setOnAction(event -> {
         lineX.setVisible(!lineX.isVisible());
         lineY.setVisible(!lineY.isVisible());
         lineZ.setVisible(!lineZ.isVisible());
      });
      
      Group group = new Group();
      group.getChildren().addAll(box, lineX, lineY, lineZ);
      
      //SubScene subScene = new SubScene(group, 500, 300);
      SubScene subScene = new SubScene(group, 500, 300, true,
                                       SceneAntialiasing.BALANCED);
      subScene.setFill(Color.ANTIQUEWHITE);
      subScene.setCamera(camera);
      subScene.relocate(5.0, 5.0);
      
      Group root = new Group();
      Slider sliderX = new Slider(-1000.0, 1000.0, trX);
      sliderX.relocate(50.0, 330.0);
      sliderX.setPrefWidth(400.0);
      sliderX.setShowTickLabels(true);
      
      Slider sliderY = new Slider(-1000.0, 1000.0, trY);
      sliderY.relocate(50.0, 360.0);
      sliderY.setPrefWidth(400.0);
      sliderY.setShowTickLabels(true);
      
      Slider sliderZ = new Slider(-1000.0, 1000.0, trZ);
      sliderZ.relocate(50.0, 390.0);
      sliderZ.setPrefWidth(400.0);
      sliderZ.setShowTickLabels(true);
      
      Slider sliderRX = new Slider(-360.0, 360.0, aX);
      sliderRX.relocate(50.0, 420.0);
      sliderRX.setPrefWidth(400.0);
      sliderRX.setShowTickLabels(true);
      
      Slider sliderRY = new Slider(-360.0, 360.0, aY);
      sliderRY.relocate(50.0, 450.0);
      sliderRY.setPrefWidth(400.0);
      sliderRY.setShowTickLabels(true);
      
      Slider sliderRZ = new Slider(-360.0, 360.0, aZ);
      sliderRZ.relocate(50.0, 480.0);
      sliderRZ.setPrefWidth(400.0);
      sliderRZ.setShowTickLabels(true);
      
      Text text = new Text("X");
      text.relocate(30.0, 330.0);
      Text text2 = new Text("Y");
      text2.relocate(30.0, 360.0);
      Text text3 = new Text("Z");
      text3.relocate(30.0, 390.0);
      Text text4 = new Text("RX");
      text4.relocate(30.0, 420.0);
      Text text5 = new Text("RY");
      text5.relocate(30.0, 450.0);
      Text text6 = new Text("RZ");
      text6.relocate(30.0, 480.0);
      
      Text text7 = new Text("(x, y, z) -> (red, green, blue)");
      text7.relocate(300.0, 310.0);
      
      root.getChildren().addAll(subScene, sliderX, sliderY, sliderZ, sliderRX,
            sliderRY, sliderRZ,
            text, text2, text3, text4, text5, text6, btn, text7);

      sliderX.valueProperty().addListener((obj, oldValue, newValue) -> {
         tr.setX(newValue.doubleValue());
      });
      sliderY.valueProperty().addListener((obj, oldValue, newValue) -> {
         tr.setY(newValue.doubleValue());
      });
      
      sliderZ.valueProperty().addListener((obj, oldValue, newValue) -> {
         tr.setZ(newValue.doubleValue());
      });
      
      sliderRX.valueProperty().addListener((obj, oldValue, newValue) -> {
         rx.setAngle(newValue.doubleValue());
      });
      sliderRY.valueProperty().addListener((obj, oldValue, newValue) -> {
         ry.setAngle(newValue.doubleValue());
      });
      sliderRZ.valueProperty().addListener((obj, oldValue, newValue) -> {
         rz.setAngle(newValue.doubleValue());
      });
      
      Scene scene = new Scene(root, 510, 600, Color.BURLYWOOD);
      /*scene.setCamera(new ParallelCamera());
      System.out.println(scene.getCamera());*/
      window.setScene(scene);
      window.setTitle("ParallelCamera");
      window.show();
      
      scene.setOnMouseClicked(event -> {
         if (event.getClickCount() > 1) {
            sliderX.setValue(trX);
            sliderY.setValue(trY);
            sliderZ.setValue(trZ);
            sliderRX.setValue(aX);
            sliderRY.setValue(aY);
            sliderRZ.setValue(aZ);
         }
      });
   }
}
