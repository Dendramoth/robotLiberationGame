/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Weapons;

import com.mycompany.robotliberation.LoadAllResources;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Line;

/**
 *
 * @author Dendra
 */
public class Rocket {
    
    private double possitionX = 0;
    private double possitionY = 0;
    private double angleOfFiredShot = 0;
    private double finalX = 0;
    private double finalY = 0;
    private Image rocketImage = LoadAllResources.getMapOfAllImages().get("rocket");
    private GraphicsContext graphicsContext;
    private int counterOfRocketLive = 0;

    public Rocket(double startPositionOfShotX, double startPositionOfShotY, double angleOfFiredShot, double playerX, double playerY, GraphicsContext graphicsContext) {
        possitionX = startPositionOfShotX;
        possitionY = startPositionOfShotY;
        calculateEndingPossitionOfRocket(playerX, playerY, possitionX, possitionY);
        this.angleOfFiredShot = angleOfFiredShot + 180;
        System.out.println(angleOfFiredShot);
        this.graphicsContext = graphicsContext;
    }
    
    private void calculateEndingPossitionOfRocket(double playerX, double playerY, double rocketX, double rocketY){
        playerX = playerX - 32;
        playerY = playerY - 32;
        double deltaX = playerX - rocketX;
        double deltaY = playerY - rocketY;

        finalX = playerX - Math.cos(Math.toRadians(angleOfFiredShot + 90)) * 100;
        finalY = playerY - Math.sin(Math.toRadians(angleOfFiredShot + 90)) * 100;
    }

    public void paintRocket() {
        graphicsContext.save();
        graphicsContext.translate(possitionX + rocketImage.getWidth() / 2, possitionY + rocketImage.getHeight() / 2);
        graphicsContext.rotate(angleOfFiredShot - 180);
        graphicsContext.drawImage(rocketImage, -rocketImage.getWidth() / 2, -rocketImage.getHeight() / 2);
        graphicsContext.restore();
    }
    
    public void moveRocket(){
        double deltaX = finalX - possitionX;
        double deltaY = finalY - possitionY;

        possitionX = possitionX - Math.cos(Math.toRadians(angleOfFiredShot + 90)) * 5;
        possitionY = possitionY - Math.sin(Math.toRadians(angleOfFiredShot + 90)) * 5;
    }
    
    public void moveRocketBasedOnPlayerMovement(double deltaX, double deltaY){
        possitionX = possitionX + deltaX;
        possitionY = possitionY + deltaY;
    }
    
    public boolean hasRocketReachedDestination(){
        counterOfRocketLive++;
        if (counterOfRocketLive < 100 ){
            return false;
        }
        return true;
    }

    public Line getLineForDetection() {
        Line line = new Line();
        line.setStartX(possitionX );
        line.setStartY(possitionY );
        line.setEndX(possitionX - Math.cos(Math.toRadians(angleOfFiredShot + 90)) * 1000);
        line.setEndY(possitionY - Math.sin(Math.toRadians(angleOfFiredShot + 90)) * 1000);

        return line;
    }
    
}
