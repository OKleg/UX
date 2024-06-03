package com.indiv;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ToDoListApp extends Application {
//checkInDatePicker.setValue(checkInDatePicker.getValue().plusWeeks(1));
  private ObservableList<Task> tasks = FXCollections.observableArrayList();
  private ListView<Task> tasksListView;

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    primaryStage.setTitle("Список задач");
    loadNotesFromFile(new File("tasks.txt"));

    initUI(primaryStage);
    primaryStage.show();

  }
private void addWind(){
  Stage addWind = new Stage();
  Button saveButton = new Button("Добавить");
  TextField taskFieldW = new TextField();
  TextArea descriptionAreaW = new TextArea();
  TextField workerFieldW = new TextField();
  DatePicker deadlineDatePickerW = new DatePicker();
  taskFieldW.setMaxWidth(250);
  descriptionAreaW.setMaxWidth(250);
  descriptionAreaW.setWrapText(true);
  workerFieldW.setMaxWidth(250);

  HBox buttonsW = new HBox(98, saveButton);
  VBox inputsAddW = new VBox(5, taskFieldW, descriptionAreaW, workerFieldW,deadlineDatePickerW, buttonsW);

  saveButton.setOnAction(eventSave -> {
    String title = taskFieldW.getText();
    String description = descriptionAreaW.getText();
    String worker = workerFieldW.getText();
    String deadline = deadlineDatePickerW.getValue().toString();
    addTask(title, description, worker, deadline,"","","");
    saveTaskToFile(new File("tasks.txt"));
  });
  inputsAddW.setPadding(new Insets(5,5,5,5));
  Scene scene3 = new Scene(inputsAddW,250.0, 350.0);

  addWind.setTitle("Добавление задачи");
  addWind.setScene(scene3);
  addWind.show();
  tasksListView.setItems(tasks);
}
private void detailsWind(Task task, int taskId) {
  Stage detailsWind = new Stage();
  Button saveButton = new Button("Сохранить");
  TextField taskField = new TextField(task.title);
  TextArea descriptionArea = new TextArea(task.description);
  TextField workerField = new TextField(task.worker);
  DatePicker deadlineDatePicker = new DatePicker(LocalDate.parse(task.deadline));
  TextField markField = new TextField(task.mark);
  TextArea commentArea = new TextArea(task.comment);
  TextArea delayArea = new TextArea(task.delay);
  delayArea.setDisable(true);
  VBox delayBox = new VBox(new Label("Причина невыполнения"),delayArea);
  delayBox.setVisible(false);
  boolean isDelay;
  taskField.setMaxWidth(250);
  descriptionArea.setMaxWidth(250);
  descriptionArea.setWrapText(true);
  commentArea.setWrapText(true);
  delayArea.setWrapText(true);
  workerField.setMaxWidth(250);
  deadlineDatePicker.setOnAction(e->
  {
    if (deadlineDatePicker.getValue().isBefore(LocalDate.now())){
      deadlineDatePicker.setStyle("-fx-text-fill: red;");
      delayArea.setStyle("-fx-background-color: red;" +
              "-fx-text-fill: red;");
      delayArea.setDisable(false);
      delayBox.setVisible(true);
      deadlineDatePicker.setValue(LocalDate.now().plusWeeks(1));
    }
    else{
      delayBox.setVisible(false);
    }
  });
  if (deadlineDatePicker.getValue().isBefore(LocalDate.now())){
    deadlineDatePicker.setStyle("-fx-text-fill: red;");
    delayArea.setStyle("-fx-background-color: red;" +
            "-fx-text-fill: red;");
    delayArea.setDisable(false);
    delayBox.setVisible(true);
    isDelay = true;
    deadlineDatePicker.setValue(LocalDate.now().plusWeeks(1));
  } else {
      isDelay = false;
  }
    HBox buttons = new HBox(98, saveButton);
  VBox inputsAddW = new VBox(5, taskField, descriptionArea, workerField,
          deadlineDatePicker,
          new Label("Оценка") ,markField,
          new Label("Комментарий"),commentArea,
          delayBox, buttons);

  saveButton.setOnAction(eventSave -> {
    String title = taskField.getText();
    String description = descriptionArea.getText();
    String worker = workerField.getText();
    String deadline = deadlineDatePicker.getValue().toString();
    String mark = markField.getText();
    String comment = commentArea.getText();
    String delay = delayArea.getText();
    var editedTask = new Task(title, description, worker, deadline,
            mark,comment,delay);
    if (isDelay || (deadlineDatePicker.getValue().isBefore(LocalDate.now()))) {
      editedTask.setIsDelay();
    }
    if (mark!=""){
      editedTask.setIsComplite();

    }
    if (taskId != -1) {
      editTask(taskId,editedTask);
    }
    saveTaskToFile(new File("tasks.txt"));

    saveTaskToFile(new File("tasks.txt"));
    detailsWind.close();
  });
  inputsAddW.setPadding(new Insets(5,5,5,5));
  Scene scene3 = new Scene(inputsAddW,250.0, 350.0);

  detailsWind.setTitle(task.title);
  detailsWind.setScene(scene3);
  detailsWind.show();
}
  private void initUI(Stage primaryStage) {

    Button addButton = new Button("Добавить задачи");
    Button deleteButton = new Button("Удалить");
    Button detailsButton = new Button("Подробнее");

    HBox buttons = new HBox(10, addButton, deleteButton, detailsButton);
    tasksListView = new ListView<>(tasks);
    tasksListView.setPrefWidth(330);
    primaryStage.setMaxWidth(340);

    VBox root = new VBox(5, tasksListView,buttons);
    root.setPadding(new Insets(5,5,5,5));
    // Обработка добавления задачи
    addButton.setOnAction(event -> addWind());

    // Обработка удаления задачи
    deleteButton.setOnAction(event -> {
      int selectedIndex = tasksListView.getSelectionModel().getSelectedIndex();
      if (selectedIndex != -1) {
        tasks.remove(selectedIndex);
      }
      tasksListView.setItems(tasks);
      saveTaskToFile(new File("tasks.txt"));
    });
    detailsButton.setOnAction(event -> {
      int selectedIndex = tasksListView.getSelectionModel().getSelectedIndex();
      if (selectedIndex != -1) {
        detailsWind(tasks.get(selectedIndex), selectedIndex);
      }
      tasksListView.setItems(tasks);
    });

    tasksListView.setItems(tasks);

    Scene scene = new Scene(root, 320, 250);
    primaryStage.setTitle("Список задач");
    primaryStage.setScene(scene);
  }

  private void saveTaskToFile(File file) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
        for (Task task : tasks) {
            writer.write(task.getTitle() + ";"
                    + task.getDescription() + ";"
                    + task.getWorker() + ";"
                    + task.getDeadline() + ";"
                    + task.getMark() + ";"
                    + task.getComment() + ";"
                    + task.getDelay() + ";"
            );
            writer.newLine();
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}

  private void loadNotesFromFile(File file) {
    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(";");
            if (parts.length == 6) {
                addTask(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5],parts[6]);
            }
          if (parts.length == 4) {
            addTask(parts[0], parts[1], parts[2], parts[3], "", "","");
          }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}

  // Добавление записи
  private void addTask(String title, String description, String worker, String deadline,
  String mark, String comment, String delay
  ) {
    if (LocalDate.parse(deadline).isBefore(LocalDate.now()) && mark.isEmpty()){
      tasks.add(0, new Task(title, description, worker, deadline,mark,comment,delay));
    }
    else tasks.add(0,new Task(title, description, worker, deadline,mark,comment,""));

  }
  private void editTask(int taskId, Task editedTask
  ) {
    tasks.set(taskId, editedTask );
    tasksListView.setItems(tasks);
  }

  private static class Task {
    private String title;
    private String description;
    private String worker;
    private String deadline;
    private String mark;
    private String comment;
    private String delay;
    private Boolean isDelay = false;
    private String wasDelay = "";
    private String wasComplite ="";
    public Task(String task, String description, String worker, String deadline,
                String mark, String comment, String delay) {
      this.title = task;
      this.description = description;
      this.worker = worker;
      this.deadline = deadline;

      this.mark = mark;
      this.comment = comment;
      this.delay = delay;
    }

    public String getWorker() {
      return worker;
    }
    public void setWorker(String worker) {this.worker=worker;}

    public String getDescription() {
      return description;
    }
    public void setDescription(String description) {this.description=description;}

    public String getTitle() {
      return title;
    }
    public void setTitle(String title) {this.title=title;}
    public String getDeadline() {
      return deadline;
    }
    public void setDeadline(String deadline) {this.deadline=deadline;}
    public String getMark() {
      return mark;
    }
    public void setMark(String mark) {this.mark=mark;}
    public String getComment() {
      return comment;
    }
    public void setComment(String comment) {this.comment=comment;}
    public String getDelay() {
      return delay;
    }
    public void setDelay(String delay) {this.delay=delay;}
    public void setIsDelay(){
      isDelay=true;
      wasDelay = "\nПричина невыполнения: " + delay;
    }
    public void setIsComplite(){
      wasComplite = "\nОценка: " + mark
              + "\nКомментарий: " + comment;
    }
    @Override
    public String toString() {
      return "Задача: " + title
              + "\nОписание: " + description
              + "\nИсполнитель: " + worker
              + "\nСрок: " + deadline
              + wasComplite
              + wasDelay;
    }

  }
}