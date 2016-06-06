/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EvilDrone;

import Enemies.Enemy;
import Enemies.ObjectWithCollision;
import com.mycompany.robotliberation.LoadAllImages;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

/**
 *
 * @author Dendra
 */
public class EvilDroneMarkOne extends Enemy implements ObjectWithCollision{

    public EvilDroneMarkOne(double x, double y, double speed) {
        super(x, y, speed);
        enemyImage = LoadAllImages.getMapOfAllImages().get("evilDroneIdle");
    }

    @Override
    public void doOnCollision() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void doOnBeingHit() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void paintEnemy(GraphicsContext enemyGraphicsContext) {
        enemyGraphicsContext.drawImage(enemyImage, possitionX, possitionY);
    }

    @Override
    public void moveEnemy(double playerPossitionX, double playerPossitionY) {
        playerPossitionX = playerPossitionX -32;
        playerPossitionY = playerPossitionY -32;
        double speed = movementSpeed;
        double deltaX = playerPossitionX - possitionX;
        double deltaY = playerPossitionY - possitionY;
        
        double angle = calculateAngleForDrawingRotatedShip(deltaX, deltaY);
        
        possitionX = possitionX - Math.cos(Math.toRadians(angle + 90));
        possitionY = possitionY - Math.sin(Math.toRadians(angle + 90));
      //  System.out.println(angle);    
    }
    
    private double calculateAngleForDrawingRotatedShip(double x, double y) {
        double angle;
        if (y == 0 && x == 0) {
            angle = 0;
        } else if (y > 0) {
            angle = Math.toDegrees(Math.acos(x / (Math.sqrt(y * y + x * x)))) + 90;
        } else {
            angle = -Math.toDegrees(Math.acos(x / (Math.sqrt(y * y + x * x)))) + 90;
        }
        angle = (angle + 360) % 360;
        return angle;
    }

    @Override
    public boolean detectCollision(Shape shape) {
        Circle meteorPolygon = new Circle(possitionX+ enemyImage.getWidth() / 2, possitionY + enemyImage.getHeight()/ 2, (enemyImage.getHeight()/ 2)); // -5 is here because the meteorits have not totally round shape and we dont want the player to be "hit" when he is not supposed to
        Shape intersect = Shape.intersect(shape, meteorPolygon);
        if (intersect.getLayoutBounds().getHeight() <= 0 || intersect.getLayoutBounds().getWidth() <= 0) {
            return false;
        }
        return true;
    }

        
}
