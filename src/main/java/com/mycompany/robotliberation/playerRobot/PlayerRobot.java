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
import javafx.scene.shape.Polygon;

//super dendrova hra :)
//doufam ze na ni bude elfa otrocit
/**
 *
 * @author Dendra
 */
public class PlayerRobot {
    
    private static double possitionX = 300;
    private static double possitionY = 500;
    
    private int hitPoints = 10;
    private double facingAngle = 0.0;
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

        paintRobotTurret();
    }
    
    private void paintRobotTurret(){
        robotGraphicsContext.save();
        robotGraphicsContext.translate(possitionX, possitionY);
        robotGraphicsContext.rotate(playerRobotTurret.getTurretAngle());
        playerRobotTurret.paintTurret(possitionX, possitionY);
        playerRobotTurret.moveToMouseCursor();
        robotGraphicsContext.restore();
    }

    public void moveRobotForward() {
        possitionX = possitionX - Math.cos(Math.toRadians(facingAngle + 90)) * 2.5;
        possitionY = possitionY - Math.sin(Math.toRadians(facingAngle + 90)) * 2.5;
        bounderiesDetection();

    }

    public void moveRobotBackward() {
        possitionX = possitionX - Math.cos(Math.toRadians(facingAngle - 90)) * 2.5;
        possitionY = possitionY - Math.sin(Math.toRadians(facingAngle - 90)) * 2.5;
        bounderiesDetection();
    }

    public void moveRobotLeft() {
        facingAngle = facingAngle - 2;
        bounderiesDetection();
    }

    public void moveRobotRight() {
        facingAngle = facingAngle + 2;
        bounderiesDetection();
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
            3.0 + possitionX - robotImage.getWidth() / 2, 28.0 + possitionY - 32,
            5.0 + possitionX - robotImage.getWidth() / 2, 55.0 + possitionY - 32,
            7.0 + possitionX - robotImage.getWidth() / 2, 50.0 + possitionY - 32,
            20.0 + possitionX - robotImage.getWidth() / 2, 64.0 + possitionY - 32,
            42.0 + possitionX - robotImage.getWidth() / 2, 64.0 + possitionY - 32,
            55.0 + possitionX - robotImage.getWidth() / 2, 50.0 + possitionY - 32,
            59.0 + possitionX - robotImage.getWidth() / 2, 55.0 + possitionY - 32,
            61.0 + possitionX - robotImage.getWidth() / 2, 28.0 + possitionY - 32,
            57.0 + possitionX - robotImage.getWidth() / 2, 28.0 + possitionY - 32,
            57.0 + possitionX - robotImage.getWidth() / 2, 40.0 + possitionY - 32,
            40.0 + possitionX - robotImage.getWidth() / 2, 44.0 + possitionY - 32,
            40.0 + possitionX - robotImage.getWidth() / 2, 10.0 + possitionY - 32,
            36.0 + possitionX - robotImage.getWidth() / 2, 0.0 + possitionY - 32,
            28.0 + possitionX - robotImage.getWidth() / 2, 0.0 + possitionY - 32,
            24.0 + possitionX - robotImage.getWidth() / 2, 10.0 + possitionY - 32,
            24.0 + possitionX - robotImage.getWidth() / 2, 44.0 + possitionY - 32,
            7.0 + possitionX - robotImage.getWidth() / 2, 40.0 + possitionY - 32,
            5.0 + possitionX - robotImage.getWidth() / 2, 28.0 + possitionY - 32,
            3.0 + possitionX - robotImage.getWidth() / 2, 28.0 + possitionY - 32});

        polygon.setRotate(facingAngle);
        return polygon;
    }

    public int getHitPoints() {
        return hitPoints;
    }
    
    public void removeHitPoints(int hpToRemove){
        hitPoints = hitPoints - hpToRemove;
    }
    
    public void addHitPoints(int hpToAdd){
        hitPoints = hitPoints + hpToAdd;
    }
    
    

}
