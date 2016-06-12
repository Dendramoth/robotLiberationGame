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
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;

/**
 *
 * @author Dendra
 */
 public class LoadAllResources {
    static HashMap<String, Image> mapOfAllImages = new HashMap<String, Image>();
    static HashMap<String, AudioClip> mapOfAllSounds = new HashMap<String, AudioClip>();

    public LoadAllResources() {
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
        
        image = new Image("/Cursor/cursor_target.png", 32, 32, false, false);
        mapOfAllImages.put("cursorTarget", image);
        
        
        loadTerrainImages();
        loadStaticTurretPictures();
        loadRocketImage();
        loadAllSounds();
    }
    
    private void loadTerrainImages(){
        Image image = new Image("/terrain/teren1.png", 256, 256, false, false);
        mapOfAllImages.put("terrainCrater", image);
        
        image = new Image("/terrain/teren2.png", 256, 256, false, false);
        mapOfAllImages.put("terrain2", image);
        
        image = new Image("/terrain/teren3.png", 256, 256, false, false);
        mapOfAllImages.put("terrain3", image);
        
        image = new Image("/terrain/teren4.png", 256, 256, false, false);
        mapOfAllImages.put("terrain4", image);
        
        image = new Image("/terrain/teren5.png", 256, 256, false, false);
        mapOfAllImages.put("terrain5", image);
        
        image = new Image("/terrain/teren_pisek_1.png", 256, 256, false, false);
        mapOfAllImages.put("terrain6", image);
        
        image = new Image("/terrain/teren_pisek_2.png", 256, 256, false, false);
        mapOfAllImages.put("terrain7", image);
        
        image = new Image("/terrain/teren_pisek_3.png", 256, 256, false, false);
        mapOfAllImages.put("terrain8", image);
        
        image = new Image("/terrain/teren_pisek_4.png", 256, 256, false, false);
        mapOfAllImages.put("terrain9", image);
        
        image = new Image("/terrain/teren_pisek_5.png", 256, 256, false, false);
        mapOfAllImages.put("terrain10", image);
    }
    
    private void loadRocketImage(){
        Image image = new Image("/Rocket/turret_rocket_1.png", 64, 64, false, false);
        mapOfAllImages.put("rocket1", image);
        
        image = new Image("/Rocket/turret_rocket_2.png", 64, 64, false, false);
        mapOfAllImages.put("rocket2", image);
        
        image = new Image("/Rocket/rocket_explosion1.png", 64, 64, false, false);
        mapOfAllImages.put("rocketExplosion1", image);
        
        image = new Image("/Rocket/rocket_explosion2.png", 64, 64, false, false);
        mapOfAllImages.put("rocketExplosion2", image);
        
        image = new Image("/Rocket/rocket_explosion3.png", 64, 64, false, false);
        mapOfAllImages.put("rocketExplosion3", image);
        
        image = new Image("/Rocket/rocket_explosion4.png", 64, 64, false, false);
        mapOfAllImages.put("rocketExplosion4", image);
        
        image = new Image("/Rocket/rocket_explosion5.png", 64, 64, false, false);
        mapOfAllImages.put("rocketExplosion5", image);
    }
    
    private void loadStaticTurretPictures(){
        Image image = new Image("/StaticTurret/turret_base.png", 64, 64, false, false);
        mapOfAllImages.put("turretBase", image);
        
        image = new Image("/StaticTurret/turret_idle.png", 64, 64, false, false);
        mapOfAllImages.put("idleTurret", image);
        
        image = new Image("/StaticTurret/turret_tower.png", 64, 64, false, false);
        mapOfAllImages.put("turretTower", image);
        
        image = new Image("/StaticTurret/turret_intro_1.png", 64, 64, false, false);
        mapOfAllImages.put("turretIntro1", image);
        
        image = new Image("/StaticTurret/turret_intro_2.png", 64, 64, false, false);
        mapOfAllImages.put("turretIntro2", image);
        
        image = new Image("/StaticTurret/turret_intro_3.png", 64, 64, false, false);
        mapOfAllImages.put("turretIntro3", image);
        
        image = new Image("/StaticTurret/turret_intro_3.5.png", 64, 64, false, false);
        mapOfAllImages.put("turretIntro4", image);
        
        image = new Image("/StaticTurret/turret_intro_4.png", 64, 64, false, false);
        mapOfAllImages.put("turretIntro5", image);
        
        image = new Image("/StaticTurret/turret_tower_damaged.png", 64, 64, false, false);
        mapOfAllImages.put("turretDamaged", image);
        
        image = new Image("/StaticTurret/turret_death1.png", 128, 128, false, false);
        mapOfAllImages.put("turretDeath1", image);
        
        image = new Image("/StaticTurret/turret_death2.png", 128, 128, false, false);
        mapOfAllImages.put("turretDeath2", image);
        
        image = new Image("/StaticTurret/turret_death3.png", 128, 128, false, false);
        mapOfAllImages.put("turretDeath3", image);
        
        image = new Image("/StaticTurret/turret_death4.png", 128, 128, false, false);
        mapOfAllImages.put("turretDeath4", image);
        
        image = new Image("/StaticTurret/turret_death5.png", 128, 128, false, false);
        mapOfAllImages.put("turretDeath5", image);
        
        image = new Image("/StaticTurret/turret_death6.png", 128, 128, false, false);
        mapOfAllImages.put("turretDeath6", image);
        
        image = new Image("/StaticTurret/turret_base_dead.png", 64, 64, false, false);
        mapOfAllImages.put("turretBaseDead", image);
        
    }

    private void loadAllSounds(){
        AudioClip audioClip = new AudioClip(this.getClass().getResource("/Sounds/idleRobot.mp3").toExternalForm());
        mapOfAllSounds.put("idleRobotSound", audioClip);
        
        audioClip = new AudioClip(this.getClass().getResource("/Sounds/movingRobot.mp3").toExternalForm());
        mapOfAllSounds.put("movingRobotSound", audioClip);
    }
    
    public static HashMap<String, Image> getMapOfAllImages() {
        return mapOfAllImages;
    }

    public static HashMap<String, AudioClip> getMapOfAllSounds() {
        return mapOfAllSounds;
    }
    
    

   
    
}
