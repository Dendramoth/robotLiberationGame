/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Enemies;

import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 *
 * @author Dendra
 */
public abstract class Enemy {
    protected double possitionX;
    protected double possitionY;
    protected double movementSpeed;
    protected Image enemyImage;
    protected boolean alive = true;
    protected int hitPoints;
    protected ArrayList<Explosion> allExplosionsOnEnemy = new ArrayList<Explosion>();
    
    public Enemy(double x, double y, double speed){
        this.possitionX = x;
        this.possitionY = y;
        this.movementSpeed = speed;
    }
    
    public abstract void moveEnemy(double playerPossitionX, double playerPossitionY);
    
    public abstract void paintEnemy(GraphicsContext enemyGraphicsContext);
    
    public abstract void paintAllExplosionsEnemy(GraphicsContext enemyGraphicsContext);
    
    protected abstract boolean paintDyingEnemyAnimation(GraphicsContext enemyGraphicsContext);

    public double getPossitionX() {
        return possitionX;
    }

    public double getPossitionY() {
        return possitionY;
    }

    public double getMovementSpeed() {
        return movementSpeed;
    }

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
        possitionX = possitionX + changeX;
        possitionY = possitionY + changeY;
    }
    
    
    
}
