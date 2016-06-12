/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.robotliberation.playerRobot;

import com.mycompany.robotliberation.GameMainInfrastructure;
import com.mycompany.robotliberation.LoadAllResources;
import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Polygon;

//super dendrova hra :)
//doufam ze na ni bude elfa otrocit
/**
 *
 * @author Dendra
 */
public class PlayerRobot {

    private static final double robotStaticPossionX = 500;
    private static final double robotStaticPossionY = 400;

    private static double possitionX = 500;
    private static double possitionY = 400;

    private double robotPositionChangeX = 0;
    private double robotPositionChangeY = 0;

    private int hitPoints = 10;
    private double facingAngle = 0.0;
    private GraphicsContext robotGraphicsContext;
    private Image robotImage;
    private Image robotImageMoving;
    private PlayerRobotTurret playerRobotTurret;
    private int moveTracks = 0;

    private AudioClip idleRobotSound = LoadAllResources.getMapOfAllSounds().get("idleRobotSound");
    private AudioClip movingRobotSound = LoadAllResources.getMapOfAllSounds().get("movingRobotSound");

    public PlayerRobot(GraphicsContext robotGraphicsContext) {
        this.robotGraphicsContext = robotGraphicsContext;
        robotImage = LoadAllResources.getMapOfAllImages().get("basePassive");
        robotImageMoving = LoadAllResources.getMapOfAllImages().get("baseMoving");
        playerRobotTurret = new PlayerRobotTurret(robotGraphicsContext);
    }

    public void playRobotIdleSound() {
        if (!idleRobotSound.isPlaying()) {
            idleRobotSound.play();
            if (movingRobotSound.isPlaying()) {
                movingRobotSound.stop();
            }
        }
    }

    public void playRobotMovingSound() {
        if (!movingRobotSound.isPlaying()) {
            movingRobotSound.setVolume(0.4);
            movingRobotSound.play();
            if (idleRobotSound.isPlaying()) {
                idleRobotSound.stop();
            }
        }
    }

    public void paintPlayerRobot() {
        robotGraphicsContext.clearRect(0, 0, GameMainInfrastructure.WINDOW_WIDTH, GameMainInfrastructure.WINDOW_HEIGH);

        robotGraphicsContext.save();
        robotGraphicsContext.translate(robotStaticPossionX, robotStaticPossionY);
        robotGraphicsContext.rotate(facingAngle);
        robotGraphicsContext.drawImage(robotImage, -robotImage.getWidth() / 2, -robotImage.getHeight() / 2);
        robotGraphicsContext.restore();

        paintRobotTurret();
    }

    private void paintRobotTurret() {
        robotGraphicsContext.save();
        robotGraphicsContext.translate(robotStaticPossionX, robotStaticPossionY);
        robotGraphicsContext.rotate(playerRobotTurret.getTurretAngle());
        playerRobotTurret.paintTurret(robotStaticPossionX, robotStaticPossionY);
        playerRobotTurret.moveToMouseCursor();
        robotGraphicsContext.restore();
    }

    public void moveRobotForward() {
        robotPositionChangeX = Math.cos(Math.toRadians(facingAngle + 90)) * 2.5;
        robotPositionChangeY = Math.sin(Math.toRadians(facingAngle + 90)) * 2.5;

        possitionX = possitionX - robotPositionChangeX;
        possitionY = possitionY - robotPositionChangeY;
        //     bounderiesDetection();
    }

    public void moveRobotBackward() {
        robotPositionChangeX = Math.cos(Math.toRadians(facingAngle - 90)) * 2.5;
        robotPositionChangeY = Math.sin(Math.toRadians(facingAngle - 90)) * 2.5;

        possitionX = possitionX - robotPositionChangeX;
        possitionY = possitionY - robotPositionChangeY;
        //      bounderiesDetection();
    }

    public void moveRobotLeft() {
        facingAngle = facingAngle - 2;
        //    bounderiesDetection();
    }

    public void moveRobotRight() {
        facingAngle = facingAngle + 2;
        //     bounderiesDetection();
    }

    public void shootFromRobotTurret(boolean shoot) {
        playerRobotTurret.shootTurret(shoot);
    }

    private void bounderiesDetection() {
        if (possitionX < 0 + robotImage.getWidth() / 2) {
            possitionX = 0 + robotImage.getWidth() / 2;
        }
        if (possitionX > GameMainInfrastructure.WINDOW_WIDTH - robotImage.getWidth()) {
            possitionX = GameMainInfrastructure.WINDOW_WIDTH - robotImage.getWidth();
        }
        if (possitionY < 0 + robotImage.getHeight() / 2) {
            possitionY = 0 + robotImage.getHeight() / 2;
        }
        if (possitionY > GameMainInfrastructure.WINDOW_HEIGH - robotImage.getHeight()) {
            possitionY = GameMainInfrastructure.WINDOW_HEIGH - robotImage.getHeight();
        }
    }

    public void moveTracks() {
        moveTracks++;
        if (moveTracks >= 10) {
            Image changeImage = robotImageMoving;
            robotImageMoving = robotImage;
            robotImage = changeImage;

            moveTracks = 0;
        }
    }

    public static double getPossitionX() {
        return possitionX;
    }

    public static double getPossitionY() {
        return possitionY;
    }

    public Polygon getPlayerRobotPolygon() {
        return createPolygonForColisionDetection();
    }

    private Polygon createPolygonForColisionDetection() {
        Polygon polygon = new Polygon();
        polygon.getPoints().addAll(new Double[]{
            3.0 + robotStaticPossionX - robotImage.getWidth() / 2, 28.0 + robotStaticPossionY - 32,
            5.0 + robotStaticPossionX - robotImage.getWidth() / 2, 55.0 + robotStaticPossionY - 32,
            7.0 + robotStaticPossionX - robotImage.getWidth() / 2, 50.0 + robotStaticPossionY - 32,
            20.0 + robotStaticPossionX - robotImage.getWidth() / 2, 64.0 + robotStaticPossionY - 32,
            42.0 + robotStaticPossionX - robotImage.getWidth() / 2, 64.0 + robotStaticPossionY - 32,
            55.0 + robotStaticPossionX - robotImage.getWidth() / 2, 50.0 + robotStaticPossionY - 32,
            59.0 + robotStaticPossionX - robotImage.getWidth() / 2, 55.0 + robotStaticPossionY - 32,
            61.0 + robotStaticPossionX - robotImage.getWidth() / 2, 28.0 + robotStaticPossionY - 32,
            57.0 + robotStaticPossionX - robotImage.getWidth() / 2, 28.0 + robotStaticPossionY - 32,
            57.0 + robotStaticPossionX - robotImage.getWidth() / 2, 40.0 + robotStaticPossionY - 32,
            40.0 + robotStaticPossionX - robotImage.getWidth() / 2, 44.0 + robotStaticPossionY - 32,
            40.0 + robotStaticPossionX - robotImage.getWidth() / 2, 10.0 + robotStaticPossionY - 32,
            36.0 + robotStaticPossionX - robotImage.getWidth() / 2, 0.0 + robotStaticPossionY - 32,
            28.0 + robotStaticPossionX - robotImage.getWidth() / 2, 0.0 + robotStaticPossionY - 32,
            24.0 + robotStaticPossionX - robotImage.getWidth() / 2, 10.0 + robotStaticPossionY - 32,
            24.0 + robotStaticPossionX - robotImage.getWidth() / 2, 44.0 + robotStaticPossionY - 32,
            7.0 + robotStaticPossionX - robotImage.getWidth() / 2, 40.0 + robotStaticPossionY - 32,
            5.0 + robotStaticPossionX - robotImage.getWidth() / 2, 28.0 + robotStaticPossionY - 32,
            3.0 + robotStaticPossionX - robotImage.getWidth() / 2, 28.0 + robotStaticPossionY - 32});

        polygon.setRotate(facingAngle);
        return polygon;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void removeHitPoints(int hpToRemove) {
        hitPoints = hitPoints - hpToRemove;
    }

    public void addHitPoints(int hpToAdd) {
        hitPoints = hitPoints + hpToAdd;
    }

    public ArrayList<ShotsFromMinigun> getAllShotsFromMinigun() {
        return playerRobotTurret.getAllShotsFromMinigun();
    }

    public static double getRobotStaticPossionX() {
        return robotStaticPossionX;
    }

    public static double getRobotStaticPossionY() {
        return robotStaticPossionY;
    }

    public double getRobotPositionChangeX() {
        return robotPositionChangeX;
    }

    public double getRobotPositionChangeY() {
        return robotPositionChangeY;
    }

    public void setRobotPositionChangeX(double robotPositionChangeX) {
        this.robotPositionChangeX = robotPositionChangeX;
    }

    public void setRobotPositionChangeY(double robotPositionChangeY) {
        this.robotPositionChangeY = robotPositionChangeY;
    }

}
