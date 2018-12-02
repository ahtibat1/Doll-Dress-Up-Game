/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sbu.local.hack.day;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import javafx.scene.image.Image;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author ahtib
 */
public class SBULocalHackDay extends Application {
    Image[] body;
    ObservableList<String> hair;
    ObservableList<String> face;
    ObservableList<String> shirt;
    ObservableList<String> pants;
    ObservableList<String> shoes;
    ImageView bodyView;
    ImageView hairView;
    ImageView faceView;
    ImageView shirtView;
    ImageView pantsView;
    ImageView shoesView;
    String type;
    

    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        BorderPane display = new BorderPane();
        
        root.getChildren().add(display);
        
        type = "human";
        body = new Image[3];
	body[0] = new Image("human.PNG");
        body[1] = new Image("elf.PNG");
        body[2] = new Image("chicken.PNG");
        Label bodylabel = new Label("Body Choices");
        
        VBox bodyboxes = new VBox();
        Button human = new Button("human");
        Button elf = new Button("elf");
        Button chicken = new Button("chicken");
        elf.setStyle("-width: 200px");
        chicken.setStyle("-width: 25%");
        human.setStyle("-width: 200px");
        
       
        Label warning = new Label("If you change body you will reset your doll!");
        
        Label colorLabel = new Label("Change Skin Color:");
        ColorPicker changecolor = new ColorPicker();
        Label colorWarning = new Label("Skin color change will last to different body types!");
        bodyboxes.getChildren().addAll(bodylabel, human,elf,chicken, warning, colorLabel, changecolor, colorWarning);
        // Remember to set the button actions to change the comboboxes and the image
        display.setLeft(bodyboxes);
        bodyboxes.setAlignment(Pos.CENTER_LEFT);
        bodyView = new ImageView(body[0]);
        
        File h = new File("./human/hair");
        ArrayList<String> hairImages = new ArrayList<String>(Arrays.asList(h.list()));
        hair = FXCollections.observableArrayList(hairImages);
        String filePath = new File("").getAbsolutePath();
        String hairPath = filePath.concat("\\human\\hair\\"+hair.get(0));
        h = new File(hairPath);
        hairView = new ImageView(h.toURI().toString());
        ComboBox hairBox = new ComboBox(hair);
        hairBox.setValue(hair.get(0));
        
        File f = new File("./human/face");
        ArrayList<String> faceImages = new ArrayList<String>(Arrays.asList(f.list()));
        face = FXCollections.observableArrayList(faceImages);
        ComboBox faceBox = new ComboBox(face);
        faceBox.setValue(face.get(0));
        String facePath = filePath.concat("\\human\\face\\"+face.get(0));
        f = new File(facePath);
        faceView = new ImageView(f.toURI().toString());

        File s = new File("./human/shirt");
        ArrayList<String> shirtImages = new ArrayList<String>(Arrays.asList(s.list()));
        shirt = FXCollections.observableArrayList(shirtImages);
        ComboBox shirtBox = new ComboBox(shirt);
        shirtBox.setValue(shirt.get(0));
        String shirtPath = filePath.concat("\\human\\shirt\\"+shirt.get(0));
        f = new File(shirtPath);
        shirtView = new ImageView(f.toURI().toString());

        
        File p = new File("./human/pants");
        ArrayList<String> pantsImages = new ArrayList<String>(Arrays.asList(p.list()));
        pants = FXCollections.observableArrayList(pantsImages);
        ComboBox pantsBox = new ComboBox(pants);
        pantsBox.setValue(pants.get(0));
        String pantsPath = filePath.concat("\\human\\pants\\"+pants.get(0));
        f = new File(pantsPath);
        pantsView = new ImageView(f.toURI().toString());
        
        File so = new File("./human/shoes");
        ArrayList<String> shoesImages = new ArrayList<String>(Arrays.asList(so.list()));
        shoes = FXCollections.observableArrayList(shoesImages);
        ComboBox shoesBox = new ComboBox(shoes);
        shoesBox.setValue(shoes.get(0));
        String shoesPath = filePath.concat("\\human\\shoes\\"+shoes.get(0));
        f = new File(shoesPath);
        shoesView = new ImageView(f.toURI().toString());
        
        Button rand = new Button("Randomize");
        Label randLabel = new Label("Randomize will not randomize name \nor body type");
        Label features = new Label("Features");
        
        VBox comboboxes = new VBox();
        comboboxes.getChildren().addAll(features, hairBox, faceBox, shirtBox, pantsBox, shoesBox,rand, randLabel);
        display.setRight(comboboxes);
        comboboxes.setAlignment(Pos.CENTER_LEFT);
        
        HBox nameDisplay = new HBox();
        Label nameLabel = new Label("No Name");
        nameLabel.setStyle("-fx-font-size: 24");
        nameDisplay.getChildren().add(nameLabel);
        display.setTop(nameDisplay);
        nameDisplay.setAlignment(Pos.CENTER);
        
        Label nameFieldLabel = new Label("Enter Name: ");
        TextField nameField = new TextField();
        Button nameGenerator = new Button("Random Name");
        HBox nameArea = new HBox();
        nameArea.getChildren().addAll(nameFieldLabel,nameField, nameGenerator);
        display.setBottom(nameArea);
        nameArea.setAlignment(Pos.CENTER);
        
        StackPane image = new StackPane();
        image.getChildren().addAll(bodyView, hairView, faceView, shoesView,pantsView,shirtView);
        display.setCenter(image);
        //Set up the scene with the stuff
        Scene scene = new Scene(root, 1000, 1000);

	// AND START UP THE WINDOW
	primaryStage.setTitle("Doll Dress Up Game");
	primaryStage.setScene(scene);
	primaryStage.show();
        
        HackDay_Controller cont = new HackDay_Controller(hairBox, faceBox, shirtBox,pantsBox,shoesBox,nameLabel);
        human.setOnAction(e->{ 
                bodyView.setImage(body[0]);
                cont.bodyBoxAction("human");
                resetLists(hairBox, faceBox, shirtBox,pantsBox,shoesBox);
                bodyView.setClip(new ImageView(body[0]));
        });
                
        elf.setOnAction(e-> {
                cont.bodyBoxAction("elf");
                bodyView.setImage(body[1]);
                resetLists(hairBox, faceBox, shirtBox,pantsBox,shoesBox);
                bodyView.setClip(new ImageView(body[1]));
        });
        chicken.setOnAction(e-> {
                cont.bodyBoxAction("chicken");
                bodyView.setImage(body[2]);
                resetLists(hairBox, faceBox, shirtBox,pantsBox,shoesBox);
                bodyView.setClip(new ImageView(body[2]));

        });
        hairBox.setOnAction(e->{
            Image i = cont.comboBoxChange("hair\\"+(String)hairBox.getValue());
            hairView.setImage(i);
        });
        faceBox.setOnAction(e->{
            Image i = cont.comboBoxChange("face\\"+(String)faceBox.getValue());
            faceView.setImage(i);
        });
        shirtBox.setOnAction(e->{
            Image i = cont.comboBoxChange("shirt\\"+(String)shirtBox.getValue());
            shirtView.setImage(i);
        });
        pantsBox.setOnAction(e->{
            Image i = cont.comboBoxChange("pants\\"+(String)pantsBox.getValue());
            pantsView.setImage(i);
        });
        shoesBox.setOnAction(e->{
            Image i = cont.comboBoxChange("shoes\\"+(String)shoesBox.getValue());
            shoesView.setImage(i);
        });
        nameGenerator.setOnAction(e->{
            cont.randomName();
            nameField.setText(nameLabel.getText());
        });
        nameField.setOnAction(e->{
            cont.nameChange(nameField.getText());
        });
        rand.setOnAction(e->{
           cont.randomizeFeatures(); 
        });
        changecolor.setOnAction(e->{ 
            Color skin = changecolor.getValue();
            bodyView.setImage(cont.changeSkinColor(bodyView, skin));
        });
    }
    
    private void resetLists(ComboBox ha, ComboBox fa, ComboBox si, ComboBox pa, ComboBox so){
        hair = ha.getItems();
        face = fa.getItems();
        shirt = si.getItems();
        pants = pa.getItems();
        shoes = so.getItems();
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        launch(args);
    }
    
}
