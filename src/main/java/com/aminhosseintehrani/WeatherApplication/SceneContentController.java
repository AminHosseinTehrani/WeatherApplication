package com.aminhosseintehrani.WeatherApplication;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javafx.scene.image.ImageView;
import javafx.scene.shape.Shape;


public class SceneContentController {


    public SceneContentController() {

    }

//Find node in scene with specific ID
    public Shape findSceneNode(String Id, Scene scene) {

        return (Shape) scene.lookup(Id);
    }
    //Find image node in scene with specific ID
    public ImageView findSceneImageNode(String Id, Scene scene) {

        return (ImageView) scene.lookup(Id);
    }

    //Find button node in scene with specific ID
    public Button findSceneButtonNode(String Id, Scene scene) {

        return (Button) scene.lookup(Id);
    }

    //Find textfield node in scene with specific ID
    public TextField findSceneTextFieldNode(String Id, Scene scene) {

        return (TextField) scene.lookup(Id);
    }


}
