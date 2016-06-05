/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.robotliberation.playerRobot;

/**
 *
 * @author Dendra
 */



import com.mycompany.robotliberation.GameMainInfrastructure;
import com.mycompany.robotliberation.LoadAllImages;
import java.awt.MouseInfo;
import java.awt.Point;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class PlayerRobotTurret {
    private Image turretImage;
    private double turretAngle = 0;
    private GraphicsContext robotGraphicsContext;
    private double possitionX = 0;
    private double possitionY = 0;
    
    public PlayerRobotTurret(GraphicsContext robotGraphicsContext){
        this.robotGraphicsContext = robotGraphicsContext;
        turretImage = LoadAllImages.getMapOfAllImages().get("towerPassive");
    }
    
    public void paintTurret(double possitionX, double possitionY){
        this.possitionX = possitionX;
        this.possitionY = possitionY;
        
        Point mouseLocation = MouseInfo.getPointerInfo().getLocation();
        mouseLocation.getX();
        mouseLocation.getY();
        
        robotGraphicsContext.drawImage(turretImage, -turretImage.getWidth() / 2, -turretImage.getHeight() / 2);
    }
    
    public void moveTurretToLeft(){
        turretAngle = turretAngle - 5; 
    }
    
    public void moveTurretToRight(){
        turretAngle = turretAngle + 5;
    }

    public double getTurretAngle() {
        return turretAngle;
    }
    
    public void moveToMouseCursor() {
        Point mouseLocation = MouseInfo.getPointerInfo().getLocation();
        mouseLocation.getX();
        mouseLocation.getY();

        double xMovement = mouseLocation.getX() - possitionX - GameMainInfrastructure.windowPositionX - turretImage.getWidth() / 2;
        double yMovement = mouseLocation.getY() - possitionY - GameMainInfrastructure.windowPositionY - turretImage.getHeight()/ 2;
        
        double angleToMouse = calculateAngleForDrawingRotatedTurret(xMovement, yMovement);
        angleToMouse = (angleToMouse + 360) % 360;
        
        turretAngle = angleToMouse;
    }
    
    private double calculateAngleForDrawingRotatedTurret(double x, double y) {
        double angle = 0;
        if (y == 0 && x == 0) {
            angle = 0;
        } else if (y > 0) {
            angle = Math.toDegrees(Math.acos(x / (Math.sqrt(y * y + x * x)))) + 90;
        } else {
            angle = -Math.toDegrees(Math.acos(x / (Math.sqrt(y * y + x * x)))) + 90;
        }
        return angle;
    }
    
    
    
}
