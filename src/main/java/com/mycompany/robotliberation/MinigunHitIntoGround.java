/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.robotliberation;

import java.util.Random;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 *
 * @author Dendra
 */
public class MinigunHitIntoGround {

    private double possitionX = 0;
    private double possitionY = 0;
    private Image explosionImage;
    private int numberOfFramesBeingDisplayed = 120;

    public MinigunHitIntoGround(double possitionX, double possitionY) {
        Random random = new Random();
        this.possitionX = possitionX - 32;
        this.possitionY = possitionY - 32;

        switch (random.nextInt(7)) {
            case 0:
                this.explosionImage = LoadAllResources.getMapOfAllImages().get("minigunMiss1");
                break;
            case 1:
                this.explosionImage = LoadAllResources.getMapOfAllImages().get("minigunMiss2");
                break;
            case 2:
                this.explosionImage = LoadAllResources.getMapOfAllImages().get("minigunMiss3");
                break;
            case 3:
                this.explosionImage = LoadAllResources.getMapOfAllImages().get("minigunMiss4");
                break;
            case 4:
                this.explosionImage = LoadAllResources.getMapOfAllImages().get("minigunMiss5");
                break;
            case 5:
                this.explosionImage = LoadAllResources.getMapOfAllImages().get("minigunMiss6");
                break;
            case 6:
                this.explosionImage = LoadAllResources.getMapOfAllImages().get("minigunMiss7");
                break;
        }
        this.explosionImage = explosionImage;
    }

    public void paint(GraphicsContext graphicsContext) {
        graphicsContext.drawImage(explosionImage, possitionX, possitionY);
        numberOfFramesBeingDisplayed--;
    }

    public int getNumberOfFramesBeingDisplayed() {
        return numberOfFramesBeingDisplayed;
    }
    
    public void changeEnemyPositionBasedOnRobotMovement(double changeX, double changeY){
        possitionX = possitionX + changeX;
        possitionY = possitionY + changeY;
    }
}
