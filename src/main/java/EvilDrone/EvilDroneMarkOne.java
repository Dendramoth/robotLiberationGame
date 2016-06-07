/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EvilDrone;

import Enemies.Enemy;
import Enemies.ObjectWithCollision;
import Enemies.EnemyWithCollision;
import com.mycompany.robotliberation.LoadAllImages;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

/**
 *
 * @author Dendra
 */
public class EvilDroneMarkOne extends EnemyWithCollision  {

    private int explodingTimer = 0;

    public EvilDroneMarkOne(double x, double y, double speed) {
        super(x, y, speed);
        enemyImage = LoadAllImages.getMapOfAllImages().get("evilDroneIdle");
    }

    @Override
    public boolean doOnCollision(GraphicsContext enemyGraphicsContext) {
        return expolodingAnimation(enemyGraphicsContext);
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
        playerPossitionX = playerPossitionX - 32;
        playerPossitionY = playerPossitionY - 32;
        double deltaX = playerPossitionX - possitionX;
        double deltaY = playerPossitionY - possitionY;

        double angle = calculateAngleForDrawingRotatedShip(deltaX, deltaY);

        possitionX = possitionX - Math.cos(Math.toRadians(angle + 90));
        possitionY = possitionY - Math.sin(Math.toRadians(angle + 90));
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
        if (alive) {
            Circle meteorPolygon = new Circle(possitionX + enemyImage.getWidth() / 2, possitionY + enemyImage.getHeight() / 2, (enemyImage.getHeight() / 2)); // -5 is here because the meteorits have not totally round shape and we dont want the player to be "hit" when he is not supposed to
            Shape intersect = Shape.intersect(shape, meteorPolygon);
            if (intersect.getLayoutBounds().getHeight() <= 0 || intersect.getLayoutBounds().getWidth() <= 0) {
                return false;
            }

        } else {
            return false;
        }
        return true;
    }

    public boolean expolodingAnimation(GraphicsContext enemyGraphicsContext) {
        if (explodingTimer < 4) {
            enemyImage = LoadAllImages.getMapOfAllImages().get("drone_death1");
        } else if (explodingTimer <= 5) {
            enemyImage = LoadAllImages.getMapOfAllImages().get("drone_death2");
        } else if (explodingTimer > 5 && explodingTimer <= 10) {
            enemyImage = LoadAllImages.getMapOfAllImages().get("drone_death3");
        } else if (explodingTimer > 10 && explodingTimer <= 15) {
            enemyImage = LoadAllImages.getMapOfAllImages().get("drone_death4");
        } else if (explodingTimer > 15 && explodingTimer <= 20) {
            enemyImage = LoadAllImages.getMapOfAllImages().get("drone_death5");
        } else {
            return false;
        }
        paintEnemy(enemyGraphicsContext);
        explodingTimer++;
        return true;
    }

}