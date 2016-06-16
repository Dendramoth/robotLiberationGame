/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.robotliberation.playerRobot;

import GameObjects.GameObject;
import GameObjects.GameObjectWithColision;
import GameObjects.GameObjectWithCollision;
import com.mycompany.robotliberation.GameMainInfrastructure;
import com.mycompany.robotliberation.LoadAllResources;
import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

//super dendrova hra :)
//doufam ze na ni bude elfa otrocit
/**
 *
 * @author Dendra
 */
public class PlayerRobot extends GameObjectWithColision {

    private static double worldPossitionX = 500;
    private static double worldpossitionY = 400;

    private double robotPositionChangeX = 0;
    private double robotPositionChangeY = 0;

    private int hitPoints = 1000;
    private double facingAngle = 0.0;
    private GraphicsContext robotGraphicsContext;
    private Image robotImage;
    private Image robotImageMoving;
    private PlayerRobotTurret playerRobotTurret;
    private int moveTracks = 0;

    private AudioClip idleRobotSound = LoadAllResources.getMapOfAllSounds().get("idleRobotSound");
    private AudioClip movingRobotSound = LoadAllResources.getMapOfAllSounds().get("movingRobotSound");

    public PlayerRobot(GraphicsContext robotGraphicsContext, double possitionOnCanvasX, double possitionOnCanvasY) {
        super(possitionOnCanvasX, possitionOnCanvasY);
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

    private void paintRobotTurret() {
        robotGraphicsContext.save();
        robotGraphicsContext.translate(possitionOnCanvasX, possitionOnCanvasY);
        robotGraphicsContext.rotate(playerRobotTurret.getTurretAngle());
        playerRobotTurret.paintTurret(possitionOnCanvasX, possitionOnCanvasY);
        playerRobotTurret.moveToMouseCursor();
        robotGraphicsContext.restore();
    }

    public void moveRobotForward() {
        robotPositionChangeX = Math.cos(Math.toRadians(facingAngle + 90)) * 2.5;
        robotPositionChangeY = Math.sin(Math.toRadians(facingAngle + 90)) * 2.5;

        worldPossitionX = worldPossitionX - robotPositionChangeX;
        worldpossitionY = worldpossitionY - robotPositionChangeY;
    }

    public void moveRobotBackward() {
        robotPositionChangeX = Math.cos(Math.toRadians(facingAngle - 90)) * 2.5;
        robotPositionChangeY = Math.sin(Math.toRadians(facingAngle - 90)) * 2.5;

        worldPossitionX = worldPossitionX - robotPositionChangeX;
        worldpossitionY = worldpossitionY - robotPositionChangeY;
    }

    public void moveRobotLeft() {
        facingAngle = facingAngle - 2;
    }

    public void moveRobotRight() {
        facingAngle = facingAngle + 2;
    }

    public void shootFromRobotTurret(boolean shoot) {
        playerRobotTurret.shootTurret(shoot);
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
        return worldPossitionX;
    }

    public static double getPossitionY() {
        return worldpossitionY;
    }

    public Polygon getPlayerRobotPolygon() {
        return createPolygonForColisionDetection();
    }

    private Polygon createPolygonForColisionDetection() {
        Polygon polygon = new Polygon();
        polygon.getPoints().addAll(new Double[]{
            0.0 + possitionOnCanvasX - robotImage.getWidth() / 2, 0.0 + possitionOnCanvasY - 32,
            0.0 + possitionOnCanvasX - robotImage.getWidth() / 2, 64.0 + possitionOnCanvasY - 32,
            64.0 + possitionOnCanvasX - robotImage.getWidth() / 2, 64.0 + possitionOnCanvasY - 32,
            64.0 + possitionOnCanvasX - robotImage.getWidth() / 2, 0.0 + possitionOnCanvasY - 32,
            0.0 + possitionOnCanvasX - robotImage.getWidth() / 2, 0.0 + possitionOnCanvasY - 32});

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

    @Override
    public void paintGameObject() {
        robotGraphicsContext.clearRect(0, 0, GameMainInfrastructure.WINDOW_WIDTH, GameMainInfrastructure.WINDOW_HEIGH);

        robotGraphicsContext.save();
        robotGraphicsContext.translate(possitionOnCanvasX, possitionOnCanvasY);
        robotGraphicsContext.rotate(facingAngle);
        robotGraphicsContext.drawImage(robotImage, -robotImage.getWidth() / 2, -robotImage.getHeight() / 2);
        robotGraphicsContext.restore();

        paintRobotTurret();
    }

    @Override
    public boolean detectCollision(Shape shape) {
        Polygon meteorPolygon = createPolygonForColisionDetection();
        Shape intersect = Shape.intersect(shape, meteorPolygon);
        if (intersect.getLayoutBounds().getHeight() <= 0 || intersect.getLayoutBounds().getWidth() <= 0) {
            return false;
        }
        return true;
    }

    @Override
    public boolean doOnCollision(GraphicsContext enemyGraphicsContext) {
        return true;
    }

    @Override
    public void doOnBeingHit() {
        hitPoints = hitPoints - 20;
    //    System.out.println("HITTTTT");
    }

}
