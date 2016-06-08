/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Enemies;

import com.mycompany.robotliberation.LoadAllImages;
import java.util.Random;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 *
 * @author Dendra
 */
public class Explosion {

    private double possitionX = 0;
    private double possitionY = 0;
    private Image explosionImage;
    private int numberOfFramesBeingDisplayed = 10;

    public Explosion() {
        Random random = new Random();
        this.possitionX = random.nextDouble() * 32;
        this.possitionY = random.nextDouble() * 32;

        switch (random.nextInt(3)) {
            case 0:
                this.explosionImage = LoadAllImages.getMapOfAllImages().get("droneHit1");
                break;
            case 1:
                this.explosionImage = LoadAllImages.getMapOfAllImages().get("droneHit2");
                break;
            case 2:
                this.explosionImage = LoadAllImages.getMapOfAllImages().get("droneHit3");
                break;
        }
        this.explosionImage = explosionImage;
    }

    public void paint(double possX, double possY, GraphicsContext graphicsContext) {
        graphicsContext.drawImage(explosionImage, possX + possitionX, possY + possitionY);
        numberOfFramesBeingDisplayed--;
    }

    public int getNumberOfFramesBeingDisplayed() {
        return numberOfFramesBeingDisplayed;
    }

}
