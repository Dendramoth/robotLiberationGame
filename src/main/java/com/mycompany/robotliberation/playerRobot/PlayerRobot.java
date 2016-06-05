/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.robotliberation.playerRobot;

import com.mycompany.robotliberation.GameMainInfrastructure;
import com.mycompany.robotliberation.LoadAllImages;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

//super dendrova hra :)
//doufam ze na ni bude elfa otrocit
/**
 *
 * @author Dendra
 */
public class PlayerRobot {

    public final static String FORWARD = "FORWARD";
    public final static String BACKWARD = "BACKWARD";
    public final static String LEFT = "LEFT";
    public final static String RIGHT = "RIGHT";

    private int speedForward = 2;
    private int speedBackward = 2;
    
    private double facingAngle = 0.0;

    private double possitionX = 300;
    private double possitionY = 500;

    private GraphicsContext robotGraphicsContext;
    private Image robotImage;
    private Image robotImageMoving;
    private PlayerRobotTurret playerRobotTurret;
    private int moveTracks = 0; 

    public PlayerRobot(GraphicsContext robotGraphicsContext) {
        this.robotGraphicsContext = robotGraphicsContext;
        robotImage = LoadAllImages.getMapOfAllImages().get("basePassive");
        robotImageMoving = LoadAllImages.getMapOfAllImages().get("baseMoving");
        playerRobotTurret = new PlayerRobotTurret(robotGraphicsContext);
    }

    public void paintPlayerRobot() {
        robotGraphicsContext.clearRect(0, 0, GameMainInfrastructure.WINDOW_WIDTH, GameMainInfrastructure.WINDOW_HEIGH);
        
        robotGraphicsContext.save();
        robotGraphicsContext.translate(possitionX, possitionY);
        robotGraphicsContext.rotate(facingAngle);
        robotGraphicsContext.drawImage(robotImage, -robotImage.getWidth() / 2, -robotImage.getHeight() / 2);
        robotGraphicsContext.restore();
        
        robotGraphicsContext.save();
        robotGraphicsContext.translate(possitionX, possitionY);
        robotGraphicsContext.rotate(playerRobotTurret.getTurretAngle());
        playerRobotTurret.paintTurret(possitionX, possitionY);
        playerRobotTurret.moveToMouseCursor();
        robotGraphicsContext.restore();
        
    }

    public void moveRobotForward(){
        possitionX = possitionX - Math.cos(Math.toRadians(facingAngle + 90)) * 2.5;
        possitionY = possitionY - Math.sin(Math.toRadians(facingAngle + 90)) * 2.5;
    }
    
    public void moveRobotBackward(){
        possitionX = possitionX - Math.cos(Math.toRadians(facingAngle -90)) * 2.5;
        possitionY = possitionY - Math.sin(Math.toRadians(facingAngle -90)) * 2.5;
    }
    
    public void moveRobotLeft(){
        facingAngle = facingAngle - 2;
    }
    
    public void moveRobotRight(){
        facingAngle = facingAngle + 2;
    }
    
    public void moveTracks(){
        moveTracks++;
        if (moveTracks >= 10){
            Image changeImage = robotImageMoving;
            robotImageMoving = robotImage;
            robotImage = changeImage;
            
            moveTracks = 0;
        }
    }
}
