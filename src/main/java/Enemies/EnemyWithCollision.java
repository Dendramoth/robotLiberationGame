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
public abstract class EnemyWithCollision extends Enemy implements ObjectWithCollision{
    
    public EnemyWithCollision(double x, double y, double speed) {
        super(x, y, speed);
    }
    
}
