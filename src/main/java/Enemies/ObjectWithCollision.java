/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Enemies;

/**
 *
 * @author Dendra
 */
public interface ObjectWithCollision {
    public abstract void detectCollision();
    
    public abstract void doOnCollision();
    
    public abstract void doOnBeingHit();
}
