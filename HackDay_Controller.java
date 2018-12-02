/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sbu.local.hack.day;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.scene.CacheHint;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.ColorInput;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;


/**
 *
 * @author ahtib
 */
public class HackDay_Controller {
    
    ComboBox hair;
    ComboBox face;
    ComboBox shirt;
    ComboBox pants;
    ComboBox shoes;
    String type;
    ArrayList names;
    
    Label name;
    //bodyView, hairView, faceView, shirtView, pantsView, shoesView
    public HackDay_Controller(ComboBox ha, ComboBox fa, ComboBox si, ComboBox pa, ComboBox so, Label na){
        hair = ha;
        face = fa;
        shirt = si;
        pants = pa;
        shoes = so;
        name = na;
        type = "human";
        getNames();
    }
    private void getNames(){
        File f = new File("names.txt");
        names = new ArrayList();
        names.add("Jude");
        try {
            BufferedReader in = new BufferedReader(new FileReader(f));
            String na; 
            try {
                while ((na = in.readLine()) != null){
                    names.add(na); 
                }
            } catch (IOException ex) {
                
            }
        } catch (FileNotFoundException ex) {
            names.add("Names File Not Found");
            names.add("Sorry");
        }

    }
    
    public void bodyBoxAction(String ty){
        type = ty;
        File f = new File("./"+type+"/hair");
        ArrayList<String> hairImages = new ArrayList<String>(Arrays.asList(f.list()));
        hair.getItems().clear();       
        hair.setItems(FXCollections.observableArrayList(hairImages));
        hair.setValue(hair.getItems().get(0));

                
        f = new File("./"+type+"/face");
        ArrayList<String> faceImages = new ArrayList<String>(Arrays.asList(f.list()));
        face.getItems().clear();
        face.setItems(FXCollections.observableArrayList(faceImages));
        face.setValue(face.getItems().get(0));
        
        f = new File("./"+type+"/shirt");
        ArrayList<String> shirtImages = new ArrayList<String>(Arrays.asList(f.list()));
        shirt.getItems().clear();
        shirt.setItems(FXCollections.observableArrayList(shirtImages));
        shirt.setValue(shirt.getItems().get(0));
        
        f = new File("./"+type+"/pants");
        ArrayList<String> pantsImages = new ArrayList<String>(Arrays.asList(f.list()));
        pants.getItems().clear();
        pants.setItems(FXCollections.observableArrayList(pantsImages));
        pants.setValue(pants.getItems().get(0));
        
        f = new File("./"+type+"/shoes");
        ArrayList<String> shoesImages = new ArrayList<String>(Arrays.asList(f.list()));
        shoes.getItems().clear();
        shoes.setItems(FXCollections.observableArrayList(shoesImages));
        shoes.setValue(shoes.getItems().get(0));
        //reset the comboboxes to be for whatever folder the body is set to, we can have the reseting of the image view done in the combobox selection
    }
    public Image comboBoxChange(String path){
        String filePath = new File("").getAbsolutePath();
        filePath = filePath.concat("\\"+type+"\\"+path);
        File h = new File(filePath);
        Image im = new Image(h.toURI().toString());
        return im;
    }
    public void nameChange(String na){
        name.setText(na);        
    }
    public void randomName(){
        int max = names.size();
        int var1 = (int)(Math.random() * max);
        int var2 = (int)(Math.random() * max);
        String nam1 = (String) names.get(var1);
        String nam2 = (String) names.get(var2);
        String fulname = nam1+ " " +nam2;
        nameChange(fulname);
        //first get a random name getting two names from an arraylist of names then call nameChange with that
    }
    public void randomizeFeatures(){
        
        int max = hair.getItems().size();
        int rand = (int)(Math.random() * max);
        String r = (String)hair.getItems().get(rand);
        hair.setValue(r);
        max = face.getItems().size();
        rand = (int)(Math.random() * max);
        r = (String)face.getItems().get(rand);
        face.setValue(r);
        max = shirt.getItems().size();
        rand = (int)(Math.random() * max);
        r = (String)shirt.getItems().get(rand);
        shirt.setValue(r);        
        max = pants.getItems().size();
        rand = (int)(Math.random() * max);
        r = (String)pants.getItems().get(rand);
        pants.setValue(r);
        max = shoes.getItems().size();
        rand = (int)(Math.random() * max);
        r = (String)shoes.getItems().get(rand);
        shoes.setValue(r);
    }
    public Image changeSkinColor(ImageView view, Color skin){
        //should change the color of the body to whatever they select
        Image image= new Image(type+".PNG");
        view.setClip(new ImageView(image));
        
        ColorAdjust monochrome = new ColorAdjust();
        monochrome.setSaturation(-1.0);

        
        
        Blend blush = new Blend(
                BlendMode.MULTIPLY,
                monochrome,
                new ColorInput(
                        0,
                        0,
                        view.getImage().getWidth(),
                        view.getImage().getHeight(),
                        skin
                )
        );
        

        view.effectProperty().setValue(blush);

        view.setCache(true);
        view.setCacheHint(CacheHint.SPEED);
        
        return image;
    }
    
}
