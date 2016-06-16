/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameObjects;

/**
 *
 * @author Dendra
 */
public abstract class GameObjectWithColision extends GameObject implements GameObjectWithCollision{
    
    public GameObjectWithColision(double possitionOnCanvasX, double possitionOnCanvasY) {
        super(possitionOnCanvasX, possitionOnCanvasY);
    }
    
}
