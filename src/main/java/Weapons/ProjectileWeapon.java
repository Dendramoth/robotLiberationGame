/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Weapons;

import com.mycompany.robotliberation.LoadAllResources;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Shape;

/**
 *
 * @author Dendra
 */
public abstract class ProjectileWeapon {
    protected double possitionX = 0;
    protected double possitionY = 0;
    protected double angleOfFiredShot = 0;
    protected Image projectileImage;
    protected GraphicsContext graphicsContext;

    public ProjectileWeapon(double startPositionOfShotX, double startPositionOfShotY, double angleOfFiredShot, GraphicsContext graphicsContext) {
        possitionX = startPositionOfShotX;
        possitionY = startPositionOfShotY;
        this.angleOfFiredShot = angleOfFiredShot + 180;
        this.graphicsContext = graphicsContext;
    }
    
    public void paintProjectile() {
        graphicsContext.save();
        graphicsContext.translate(possitionX + projectileImage.getWidth() / 2, possitionY + projectileImage.getHeight() / 2);
        graphicsContext.rotate(angleOfFiredShot - 180);
        graphicsContext.drawImage(projectileImage, -projectileImage.getWidth() / 2, -projectileImage.getHeight() / 2);
        graphicsContext.restore();
    }
    
    public void moveProjectile(){
        possitionX = possitionX - Math.cos(Math.toRadians(angleOfFiredShot + 90)) * 5;
        possitionY = possitionY - Math.sin(Math.toRadians(angleOfFiredShot + 90)) * 5;
    }
    
    public void moveProjectileBasedOnPlayerMovement(double deltaX, double deltaY){
        possitionX = possitionX + deltaX;
        possitionY = possitionY + deltaY;
    }
    
    public abstract boolean hasProjectileReachedDestination();
    
    public abstract Shape getShapeForDetection();
    
    public abstract boolean projectileExplosion();
}
