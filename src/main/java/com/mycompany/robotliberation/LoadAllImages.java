/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.robotliberation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javafx.scene.image.Image;

/**
 *
 * @author Dendra
 */
 public class LoadAllImages {
    static HashMap<String, Image> mapOfAllImages = new HashMap<String, Image>();

    public LoadAllImages() {
        Image image = new Image("/base_passive.png", 64, 64, false, false);
        mapOfAllImages.put("basePassive", image);
        
        image = new Image("/base_moving.png", 64, 64, false, false);
        mapOfAllImages.put("baseMoving", image);
        
        image = new Image("/tower_passive.png", 64, 64, false, false);
        mapOfAllImages.put("towerPassive", image);
        
        image = new Image("/tower_shooting.png", 64, 64, false, false);
        mapOfAllImages.put("towerShooting", image);
        
        image = new Image("/EvilDrone/drone_idle1.png", 64, 64, false, false);
        mapOfAllImages.put("evilDroneIdle1", image);
        
        image = new Image("/EvilDrone/drone_idle2.png", 64, 64, false, false);
        mapOfAllImages.put("evilDroneIdle2", image);
        
        image = new Image("/EvilDrone/drone_idle1_damaged.png", 64, 64, false, false);
        mapOfAllImages.put("evilDroneIdle1Damaged", image);
        
        image = new Image("/EvilDrone/drone_idle2_damaged.png", 64, 64, false, false);
        mapOfAllImages.put("evilDroneIdle2Damaged", image);
        
        image = new Image("/EvilDroneDeath/drone_death1.png", 64, 64, false, false);
        mapOfAllImages.put("drone_death1", image);
        
        image = new Image("/EvilDroneDeath/drone_death2.png", 64, 64, false, false);
        mapOfAllImages.put("drone_death2", image);
        
        image = new Image("/EvilDroneDeath/drone_death3.png", 64, 64, false, false);
        mapOfAllImages.put("drone_death3", image);
        
        image = new Image("/EvilDroneDeath/drone_death4.png", 64, 64, false, false);
        mapOfAllImages.put("drone_death4", image);
        
        image = new Image("/EvilDroneDeath/drone_death5.png", 64, 64, false, false);
        mapOfAllImages.put("drone_death5", image);
        
        image = new Image("/MinigunsHits/drone_hit1.png", 32, 32, false, false);
        mapOfAllImages.put("droneHit1", image);
        
        image = new Image("/MinigunsHits/drone_hit2.png", 32, 32, false, false);
        mapOfAllImages.put("droneHit2", image);
        
        image = new Image("/MinigunsHits/drone_hit3.png", 32, 32, false, false);
        mapOfAllImages.put("droneHit3", image);
    }

    public static HashMap<String, Image> getMapOfAllImages() {
        return mapOfAllImages;
    }

   
    
}
