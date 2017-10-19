/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bergerproject1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;

/**
 *
 * @author khari
 */
public class BergerProject1 extends Application {

    /**
     * @param args the command line arguments
     */
    List<Media> media;
    static ObservableList<Media> items;

    public static void main(String[] args) {
        launch(args);}

          public static boolean check(String name) {
        for (Media item : items) {
            if (item.getTitle().equals(name)) {
                return true;
            }
        }
        return false;
    }

  
    public static void refresh(RadioButton rb, ListView listV){
       if(rb.isSelected() == true ){
           Collections.sort(items);
           listV.refresh();
       } else {
           Collections.sort(items, (Media t, Media t1) -> {
                if (t.getLoan().getDate() != null && t1.getLoan().getDate() != null) {
                    listV.refresh();
                    return t.getLoan().getDate().compareTo(t1.getLoan().getDate());
                } else if (t.getLoan().getDate() == null && t1.getLoan().getDate() == null) {
                    listV.refresh();
                    return t.getTitle().compareTo(t1.getTitle());
                } else if (t.getLoan().containsNull()) {
                    listV.refresh();
                    return 1;
                } else if(t1.getLoan().containsNull()) {
                    listV.refresh();
                    return -1;
                }
                listV.refresh();
                return 0;
            });
       }
    }

    public static List<Media> readFile(String fileName) throws FileNotFoundException, IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(new File(fileName)));
        ArrayList<Media> media = (ArrayList<Media>) ois.readObject();
        return media;
    }

    public static void writeFile(String fileName, ObservableList<Media> listV) throws FileNotFoundException, IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(new File(fileName)))) {
            oos.writeObject(new ArrayList(listV));
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        media = new ArrayList();
        try {
            boolean existf = new File("proj1.txt").exists();
            media = readFile("proj1.txt");
        } catch (IOException | ClassNotFoundException e) {
            File file = new File("proj1.txt");
            try {
                file.createNewFile();
                System.out.println("Storage File Created - proj1.txt");
            } catch (IOException ex) {
                System.out.println("File does NOT exist and FAILED to create");
            }
        }

        primaryStage.setTitle("Not Neflix");
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_LEFT);

        grid.setHgap(3);
        grid.setVgap(0);
        grid.setPadding(new Insets(0, 0, 0, 0));
        Scene scene = new Scene(grid, 500, 390);

        //Top Box
        VBox vBox1 = new VBox(3);

        vBox1.setStyle("-fx-border-style: solid inside;");
        grid.add(vBox1, 5, 0);
        grid.setValignment(vBox1, VPos.TOP);

        vBox1.setPadding(new Insets(10, 10, 10, 10));

        vBox1.setPrefSize(250, 100);
        vBox1.setMaxSize(220, 50);
        vBox1.setTranslateY(10);
        Button add = new Button("Add");

        HBox hBox1 = new HBox();
        hBox1.setAlignment(Pos.BOTTOM_LEFT);
        TextField titleInput = new TextField();
        titleInput.setPrefSize(130, 10);
        hBox1.getChildren().addAll(new Label("Title:      "), titleInput);

        //this is where you added the second title bar fix it
        TextField formatInput = new TextField();
        formatInput.setPrefSize(130.2, 10);
        HBox hBox2 = new HBox();
        vBox1.getChildren().addAll(hBox1, hBox2);
        hBox2.getChildren().addAll(new Label("Format: "), formatInput);

        vBox1.getChildren().add(add);
        add.setAlignment(Pos.BOTTOM_LEFT);

        Button removeb = new Button("remove");
        Button returnb = new Button("return");

        //in between buttons
        grid.add(removeb, 5, 0);
        grid.add(returnb, 5, 0);

        removeb.setTranslateY(-65);
        returnb.setTranslateY(-20);

        //Second Box
        VBox vBox3 = new VBox(3);
        vBox3.setPadding(new Insets(10, 10, 10, 10));
        vBox3.setStyle("-fx-border-style: solid inside;");
        grid.add(vBox3, 5, 0);
        vBox3.setPrefSize(220, 100);
        vBox3.setMaxSize(220, 100);
        vBox3.setTranslateY(53);

        HBox hBox3 = new HBox();
        HBox hBox4 = new HBox();
        Button loan = new Button("Loan");
        vBox3.getChildren().addAll(hBox3, hBox4, loan);

        TextField toInput = new TextField();
        toInput.setPrefSize(130, 10);
        TextField onInput = new TextField();
        onInput.setPrefSize(130.2, 10);

        hBox3.getChildren().addAll(new Label("Loaned To:  "), toInput);
        hBox4.getChildren().addAll(new Label("Loaned On: "), onInput);

        //Sort
        Text text = new Text("Sort");
        text.setTranslateY(125);
        grid.add(text, 5, 0);

        //Radio Buttons
        RadioButton radi1 = new RadioButton("By Title");
        RadioButton radi2 = new RadioButton("By Date Loaned");
        grid.add(radi1, 5, 0);
        radi1.setTranslateY(150);
        grid.add(radi2, 5, 0);
        radi2.setTranslateY(170);
        ToggleGroup tGroup = new ToggleGroup();
        radi1.setSelected(true);
        radi1.setToggleGroup(tGroup);
        radi2.setToggleGroup(tGroup);

        //List
        items = FXCollections.observableArrayList();
        for (Media item : media) {
            items.add(item);
        }
        ListView listV = new ListView();
        listV.setItems(items);
        grid.add(listV, 0, 0);

        //Locks window
        primaryStage.setResizable(false);

        //Button Actions
        add.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (titleInput.getText().trim().isEmpty() || formatInput.getText().trim().isEmpty()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please Enter a Valid Title and Format");
                    alert.showAndWait();
                } else if (check(titleInput.getText().trim()) == true) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Title already found");
                    alert.showAndWait();
                } else {
                    items.add(new Media(titleInput.getText(), formatInput.getText(), new Loan()));
                    refresh(radi1, listV);
                    titleInput.clear();
                    formatInput.clear();
                }
            }
        }
        );

        removeb.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int index = listV.getSelectionModel().getSelectedIndex();
                if (index == -1) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please enter a valid name and date");
                    alert.showAndWait();
                } else {
                    items.remove(index);
                    refresh(radi1, listV);
                }
            }
        }
        );

        returnb.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int index = listV.getSelectionModel().getSelectedIndex();
                if (index == -1) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please enter a valid name and date");
                    alert.showAndWait();
                } else {
                    Media item = items.get(listV.getSelectionModel().getSelectedIndex());
                    Loan loan = new Loan();
                    item.setLoan(loan);
                    listV.refresh();
                    refresh(radi1, listV);
                }
            }
        }
        );

        loan.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int index = listV.getSelectionModel().getSelectedIndex();
                if (index != -1) {
                    Media item = items.get(listV.getSelectionModel().getSelectedIndex());
                    if (toInput.getText().trim().isEmpty() || onInput.getText().trim().isEmpty()) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Please enter a valid name and date(MM/dd/yyyy)");
                        alert.showAndWait();
                    } else if (item.getLoan() == null || item.getLoan().containsNull()) {
                        try {
                            DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                            String Date = onInput.getText();
                            Date loanDate = df.parse(Date);
                            Loan loan = new Loan(toInput.getText().trim(), loanDate);
                            item.setLoan(loan);
                            refresh(radi1, listV);
                            toInput.clear();
                            onInput.clear();

                        } catch (Exception e) {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setContentText("Make sure item is selected and date format(MM/dd/yyyy) is correct");
                            alert.showAndWait();
                        }
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("This item is already loaned");
                        alert.showAndWait();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please select an item");
                    alert.showAndWait();
                }
            }

        });
        
        radi1.setOnAction((event) -> {
            Collections.sort(items);
            refresh(radi1, listV);
        });
        radi2.setOnAction(event -> {
            Collections.sort(items, (Media t, Media t1) -> {
                if (t.getLoan().getDate() != null && t1.getLoan().getDate() != null) {
                    listV.refresh();
                    return t.getLoan().getDate().compareTo(t1.getLoan().getDate());
                } else if (t.getLoan().getDate() == null && t1.getLoan().getDate() == null) {
                    listV.refresh();
                    return t.getTitle().compareTo(t1.getTitle());
                } else if (t.getLoan().containsNull()) {
                    listV.refresh();
                    return 1;
                } else if(t1.getLoan().containsNull()) {
                    listV.refresh();
                    return -1;
                }
                listV.refresh();
                return 0;
            });
        });
        

        //Sets and Shows Stage
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setOnCloseRequest(event -> {
            try {
                writeFile("proj1.txt", items);
            } catch (IOException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("File did not save");
                alert.showAndWait();
            }
        });

    }
}
