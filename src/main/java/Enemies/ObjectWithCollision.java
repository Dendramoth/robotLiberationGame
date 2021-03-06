/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Enemies;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Shape;

/**
 *
 * @author Dendra
 */
public interface ObjectWithCollision {
    public abstract boolean detectCollision(Shape shape);
    
    public abstract boolean doOnCollision(GraphicsContext enemyGraphicsContext);
    
    public abstract void doOnBeingHit();
}
