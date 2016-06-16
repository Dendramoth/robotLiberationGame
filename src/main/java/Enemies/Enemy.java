/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Enemies;

import GameObjects.GameMovingObject;
import GameObjects.GameObject;
import GameObjects.GameObjectWithColision;
import GameObjects.GameObjectWithCollision;
import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 *
 * @author Dendra
 */
public abstract class Enemy extends GameObjectWithColision implements GameMovingObject{
    protected double movementSpeed;
    protected double damagedStateTreshold;
    protected Image enemyImage;
    protected boolean alive = true;
    protected int hitPoints;
    protected ArrayList<Explosion> allExplosionsOnEnemy = new ArrayList<Explosion>();
    protected GraphicsContext graphicsContext;
    
    public Enemy(double movementSpeed, double damagedStateTreshold, int hitPoints, GraphicsContext graphicsContext, double possitionOnCanvasX, double possitionOnCanvasY) {
        super(possitionOnCanvasX, possitionOnCanvasY);
        this.movementSpeed = movementSpeed;
        this.damagedStateTreshold = damagedStateTreshold;
        this.hitPoints = hitPoints;
        this.graphicsContext = graphicsContext;
    }
    
    public abstract void moveEnemy(double playerPossitionX, double playerPossitionY);
    
    public abstract void paintEnemy(GraphicsContext enemyGraphicsContext);
    
    public abstract void paintAllExplosionsEnemy(GraphicsContext enemyGraphicsContext);
    
    protected abstract boolean paintDyingEnemyAnimation(GraphicsContext enemyGraphicsContext);
    
    public abstract void paintDeadEnemy(GraphicsContext enemyGraphicsContext);

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public ArrayList<Explosion> getAllExplosionsOnEnemy() {
        return allExplosionsOnEnemy;
    }

    public void setAllExplosionsOnEnemy(ArrayList<Explosion> allExplosionsOnEnemy) {
        this.allExplosionsOnEnemy = allExplosionsOnEnemy;
    }
    
    public void changeEnemyPositionBasedOnRobotMovement(double changeX, double changeY){
        possitionOnCanvasX = possitionOnCanvasX + changeX;
        possitionOnCanvasY = possitionOnCanvasY + changeY;
    }
    
    
    
}
