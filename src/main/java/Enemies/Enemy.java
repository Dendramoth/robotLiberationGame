/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Enemies;

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
    
    public Enemy(double x, double y, double speed){
        this.possitionX = x;
        this.possitionY = y;
        this.movementSpeed = speed;
    }
    
    public abstract void moveEnemy(double playerPossitionX, double playerPossitionY);
    
    public abstract void paintEnemy(GraphicsContext enemyGraphicsContext);

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
}
