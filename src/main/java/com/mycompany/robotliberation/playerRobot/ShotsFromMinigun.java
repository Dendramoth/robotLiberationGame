/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.robotliberation.playerRobot;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

/**
 *
 * @author Dendra
 */
public class ShotsFromMinigun {

    private double startPositionOfShotX = 0;
    private double startPositionOfShotY = 0;
    private double angleOfFiredShot = 0;

    public ShotsFromMinigun(double startPositionOfShotX, double startPositionOfShotY, double angleOfFiredShot) {
        this.startPositionOfShotX = startPositionOfShotX;
        this.startPositionOfShotY = startPositionOfShotY;
        this.angleOfFiredShot = angleOfFiredShot;
    }

    public void paintShot() {

    }

    public Line getLineForDetection() {
        Line line = new Line();
   //     System.out.println(angleOfFiredShot);
        line.setStartX(startPositionOfShotX );
        line.setStartY(startPositionOfShotY );
        line.setEndX(startPositionOfShotX - Math.cos(Math.toRadians(angleOfFiredShot + 90)) * 1000);
        line.setEndY(startPositionOfShotY - Math.sin(Math.toRadians(angleOfFiredShot + 90)) * 1000);

  /*      System.out.println("start X: " + line.getStartX());
        System.out.println("start Y: " + line.getStartY());
        System.out.println("end X: " + line.getEndX());
        System.out.println("end Y: " + line.getEndY());*/
        
  /*      graphicsContext.setFill(Color.AQUA);
        graphicsContext.strokeLine(line.getStartX(), line.getStartY(), line.getEndX(), line.getEndY());*/
        return line;
    }

}