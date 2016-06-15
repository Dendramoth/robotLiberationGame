/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Enemies;

import Enemies.Enemy;
import Enemies.ObjectWithCollision;
import Enemies.Explosion;
import com.mycompany.robotliberation.LoadAllResources;
import com.mycompany.robotliberation.playerRobot.PlayerRobot;
import java.util.Iterator;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

/**
 *
 * @author Dendra
 */
public class EvilDroneMarkOne extends Enemy {
    private int blinkCounter = 0;
    private int explodingTimer = 0;

    public EvilDroneMarkOne(double movementSpeed, double damagedStateTreshold, int hitPoints, GraphicsContext graphicsContext, double possitionOnCanvasX, double possitionOnCanvasY) {
        super(movementSpeed, damagedStateTreshold, hitPoints, graphicsContext, possitionOnCanvasX, possitionOnCanvasY);
        
        enemyImage = LoadAllResources.getMapOfAllImages().get("evilDroneIdle1");
        hitPoints = 30;
        damagedStateTreshold = 25;
    }

    @Override
    public boolean doOnCollision(GraphicsContext enemyGraphicsContext) {
        return paintDyingEnemyAnimation(enemyGraphicsContext);
    }

    @Override
    public void doOnBeingHit() {
        hitPoints--;
        if (hitPoints < damagedStateTreshold) {
            movementSpeed = 0.5;
        }
        allExplosionsOnEnemy.add(new Explosion());
    }

    @Override
    public void paintEnemy(GraphicsContext enemyGraphicsContext) {
        blinkCounter++;
        if (hitPoints >= damagedStateTreshold) {
            if (blinkCounter <= 15) {
                enemyImage = LoadAllResources.getMapOfAllImages().get("evilDroneIdle1");
            }
            if (blinkCounter > 15) {
                enemyImage = LoadAllResources.getMapOfAllImages().get("evilDroneIdle2");
            }
            if (blinkCounter == 30) {
                blinkCounter = 0;
            }
        }
        if (hitPoints < damagedStateTreshold) {
            if (blinkCounter <= 15) {
                enemyImage = LoadAllResources.getMapOfAllImages().get("evilDroneIdle1Damaged");
            }
            if (blinkCounter > 15) {
                enemyImage = LoadAllResources.getMapOfAllImages().get("evilDroneIdle2Damaged");
            }
            if (blinkCounter == 30) {
                blinkCounter = 0;
            }
        }

        enemyGraphicsContext.drawImage(enemyImage, possitionOnCanvasX, possitionOnCanvasY);
    }
    
    @Override
    public void moveEnemy(double playerPossitionX, double playerPossitionY) {
        playerPossitionX = playerPossitionX - 32;
        playerPossitionY = playerPossitionY - 32;
        double deltaX = playerPossitionX - possitionOnCanvasX;
        double deltaY = playerPossitionY - possitionOnCanvasY;
        double angle = calculateAngleBetweenPlayerAndDrone(deltaX, deltaY);

        possitionOnCanvasX = possitionOnCanvasX - Math.cos(Math.toRadians(angle + 90)) * movementSpeed;
        possitionOnCanvasY = possitionOnCanvasY - Math.sin(Math.toRadians(angle + 90)) * movementSpeed;
    }

    private double calculateAngleBetweenPlayerAndDrone(double x, double y) {
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
            Circle meteorPolygon = new Circle(possitionOnCanvasX + enemyImage.getWidth() / 2, possitionOnCanvasY + enemyImage.getHeight() / 2, (enemyImage.getHeight() / 2));
            Shape intersect = Shape.intersect(shape, meteorPolygon);
            if (intersect.getLayoutBounds().getHeight() <= 0 || intersect.getLayoutBounds().getWidth() <= 0) {
                return false;
            }
        } else {
            return false;
        }
        return true;
    }

    @Override
    public void paintAllExplosionsEnemy(GraphicsContext enemyGraphicsContext) {
        Iterator<Explosion> iterator = allExplosionsOnEnemy.iterator();
        while (iterator.hasNext()) {
            Explosion explosion = iterator.next();
            explosion.paint(possitionOnCanvasX, possitionOnCanvasY, enemyGraphicsContext);
            if (explosion.getNumberOfFramesBeingDisplayed() < 1) {
                iterator.remove();
            }
        }
    }

    @Override
    protected boolean paintDyingEnemyAnimation(GraphicsContext enemyGraphicsContext) {
        if (explodingTimer < 4) {
            enemyImage = LoadAllResources.getMapOfAllImages().get("drone_death1");
        } else if (explodingTimer <= 5) {
            enemyImage = LoadAllResources.getMapOfAllImages().get("drone_death2");
        } else if (explodingTimer > 5 && explodingTimer <= 10) {
            enemyImage = LoadAllResources.getMapOfAllImages().get("drone_death3");
        } else if (explodingTimer > 10 && explodingTimer <= 15) {
            enemyImage = LoadAllResources.getMapOfAllImages().get("drone_death4");
        } else if (explodingTimer > 15 && explodingTimer <= 20) {
            enemyImage = LoadAllResources.getMapOfAllImages().get("drone_death5");
        } else {
            return false;
        }

        enemyGraphicsContext.drawImage(enemyImage, possitionOnCanvasX, possitionOnCanvasY);
        explodingTimer++;
        return true;
    }

    @Override
    public void paintDeadEnemy(GraphicsContext enemyGraphicsContext) {
        
    }

    @Override
    public void paintGameObject() {
    }

    @Override
    public void moveGameObject() {
    }
    
    

}
