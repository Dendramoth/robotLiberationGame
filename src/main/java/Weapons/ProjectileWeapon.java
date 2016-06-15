/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Weapons;

import GameObjects.GameObject;
import GameObjects.GameObjectWithCollision;
import com.mycompany.robotliberation.LoadAllResources;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Shape;

/**
 *
 * @author Dendra
 */
public abstract class ProjectileWeapon extends GameObject implements GameObjectWithCollision{
    protected double angleOfFiredShot = 0;
    protected Image projectileImage;
    protected GraphicsContext graphicsContext;

    public ProjectileWeapon(GraphicsContext graphicsContext, double angleOfFiredShot, double possitionOnCanvasX, double possitionOnCanvasY) {
        super(possitionOnCanvasX, possitionOnCanvasY);
        this.graphicsContext = graphicsContext;
        this.angleOfFiredShot = angleOfFiredShot + 180;
    }
    
    public void moveProjectile(){
        possitionOnCanvasX = possitionOnCanvasX - Math.cos(Math.toRadians(angleOfFiredShot + 90)) * 5;
        possitionOnCanvasY = possitionOnCanvasY - Math.sin(Math.toRadians(angleOfFiredShot + 90)) * 5;
    }
    
    public void moveProjectileBasedOnPlayerMovement(double deltaX, double deltaY){
        possitionOnCanvasX = possitionOnCanvasX + deltaX;
        possitionOnCanvasY = possitionOnCanvasY + deltaY;
    }
    
    public abstract boolean hasProjectileReachedDestination();
    
    public abstract Shape getShapeForDetection();
    
    public abstract boolean projectileExplosion();
}
