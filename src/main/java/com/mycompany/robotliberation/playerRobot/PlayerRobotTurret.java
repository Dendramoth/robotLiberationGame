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
import Enemies.EnemyWithCollision;
import com.mycompany.robotliberation.GameMainInfrastructure;
import com.mycompany.robotliberation.LoadAllImages;
import java.awt.MouseInfo;
import java.awt.Point;
import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class PlayerRobotTurret {

    private Image turretIdleImage;
    private Image turretShootingImage;
    private Image turretCurrentImage;
    private double turretAngle = 0;
    private GraphicsContext robotGraphicsContext;
    private double possitionX = 0;
    private double possitionY = 0;
    private int shootingCounter = 0;
    private ArrayList<ShotsFromMinigun> allShotsFromMinigun = new ArrayList<ShotsFromMinigun>(); 

    public PlayerRobotTurret(GraphicsContext robotGraphicsContext) {
        this.robotGraphicsContext = robotGraphicsContext;
        turretIdleImage = LoadAllImages.getMapOfAllImages().get("towerPassive");
        turretShootingImage = LoadAllImages.getMapOfAllImages().get("towerShooting");
        turretCurrentImage = turretIdleImage;
    }

    public void paintTurret(double possitionX, double possitionY) {
        this.possitionX = possitionX;
        this.possitionY = possitionY;

        Point mouseLocation = MouseInfo.getPointerInfo().getLocation();
        mouseLocation.getX();
        mouseLocation.getY();

        robotGraphicsContext.drawImage(turretCurrentImage, -turretCurrentImage.getWidth() / 2, -turretCurrentImage.getHeight() / 2);
    }

    public void moveTurretToLeft() {
        turretAngle = turretAngle - 5;
    }

    public void moveTurretToRight() {
        turretAngle = turretAngle + 5;
    }

    public double getTurretAngle() {
        return turretAngle;
    }

    public void shootTurret(boolean shoot) {
        if (shoot) {
            shootingCounter++;
            if (shootingCounter > 5) {
                if (turretCurrentImage == turretIdleImage) {
                    turretCurrentImage = turretShootingImage;
                    shootingCounter = 0;
                    shootMinigunProjectile();
                } else {
                    turretCurrentImage = turretIdleImage;
                    shootingCounter = 3;
                }
            }
        } else {
            turretCurrentImage = turretIdleImage;
        }
    }

    private void shootMinigunProjectile() {
        allShotsFromMinigun.add(new ShotsFromMinigun(possitionX, possitionY, turretAngle));
    }

    public void moveToMouseCursor() {
        Point mouseLocation = MouseInfo.getPointerInfo().getLocation();
        mouseLocation.getX();
        mouseLocation.getY();

        double xMovement = mouseLocation.getX() - possitionX - GameMainInfrastructure.windowPositionX - turretCurrentImage.getWidth() / 2;
        double yMovement = mouseLocation.getY() - possitionY - GameMainInfrastructure.windowPositionY - turretCurrentImage.getHeight() / 2;

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

    public ArrayList<ShotsFromMinigun> getAllShotsFromMinigun() {
        return allShotsFromMinigun;
    }
}
