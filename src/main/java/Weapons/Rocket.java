/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Weapons;

import Enemies.Enemy;
import com.mycompany.robotliberation.LoadAllResources;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

/**
 *
 * @author Dendra
 */
public class Rocket extends ProjectileWeapon {

    private int switchingRocketImageCounter = 0;
    private boolean rocket1Active = true;
    private int rocketExplosionCounter = 0;
    private int rocketDistanceCounter = 0;

    public Rocket(GraphicsContext graphicsContext, double angleOfFiredShot, double possitionOnCanvasX, double possitionOnCanvasY, Enemy enemy) {
        super(graphicsContext, angleOfFiredShot, possitionOnCanvasX, possitionOnCanvasY, enemy);
        projectileImage = LoadAllResources.getMapOfAllImages().get("rocket1");
    }

    @Override
    public boolean projectileExplosion() {
        rocketExplosionCounter++;
        if (rocketExplosionCounter <= 5) {
            projectileImage = LoadAllResources.getMapOfAllImages().get("rocketExplosion1");
        } else if (rocketExplosionCounter > 5 && rocketExplosionCounter <= 10) {
            projectileImage = LoadAllResources.getMapOfAllImages().get("rocketExplosion2");
        } else if (rocketExplosionCounter > 15 && rocketExplosionCounter <= 20) {
            projectileImage = LoadAllResources.getMapOfAllImages().get("rocketExplosion3");
        } else if (rocketExplosionCounter > 25 && rocketExplosionCounter <= 30) {
            projectileImage = LoadAllResources.getMapOfAllImages().get("rocketExplosion4");
        } else if (rocketExplosionCounter > 35 && rocketExplosionCounter <= 40) {
            projectileImage = LoadAllResources.getMapOfAllImages().get("rocketExplosion5");
        } else if (rocketExplosionCounter > 40) {
            return false;
        }
        graphicsContext.drawImage(projectileImage, possitionOnCanvasX, possitionOnCanvasY);

        return true;
    }

    @Override
    public boolean hasProjectileReachedDestination() {
        rocketDistanceCounter++;
        if (rocketDistanceCounter > 100) {
            return true;
        }
        return false;
    }

    @Override
    public boolean detectCollision(Shape shape) {
        return false;
    }

    @Override
    public Shape getShapeForDetection() {
        Polygon polygon = new Polygon();
           polygon.getPoints().addAll(new Double[]{
            30.0 + possitionOnCanvasX, 63.0 + possitionOnCanvasY,
            35.0 + possitionOnCanvasX, 63.0 + possitionOnCanvasY,
            35.0 + possitionOnCanvasX, 50.0 + possitionOnCanvasY,
            30.0 + possitionOnCanvasX, 50.0 + possitionOnCanvasY,
            30.0 + possitionOnCanvasX, 63.0 + possitionOnCanvasY});

        polygon.setRotate(angleOfFiredShot);
        return polygon;
    }

    @Override
    public boolean doOnCollision(GraphicsContext enemyGraphicsContext) {
        return false;
    }

    @Override
    public void doOnBeingHit() {
        //do nothing for now, rocket cannot be hit by anything
    }

    @Override
    public void paintGameObject() {
        switchingRocketImageCounter++;
        if (switchingRocketImageCounter > 8) {
            switchingRocketImageCounter = 0;
            if (rocket1Active) {
                projectileImage = LoadAllResources.getMapOfAllImages().get("rocket2");
                rocket1Active = false;
            } else {
                projectileImage = LoadAllResources.getMapOfAllImages().get("rocket1");
                rocket1Active = true;
            }
        }

        graphicsContext.save();
        graphicsContext.translate(possitionOnCanvasX + projectileImage.getWidth() / 2, possitionOnCanvasY + projectileImage.getHeight() / 2);
        graphicsContext.rotate(angleOfFiredShot - 180);
        graphicsContext.drawImage(projectileImage, -projectileImage.getWidth() / 2, -projectileImage.getHeight() / 2);
        graphicsContext.restore();
    }

    public int getRocketDistanceCounter() {
        return rocketDistanceCounter;
    }

    public void setRocketDistanceCounter(int rocketDistanceCounter) {
        this.rocketDistanceCounter = rocketDistanceCounter;
    }

}
