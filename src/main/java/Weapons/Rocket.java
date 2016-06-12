/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Weapons;

import com.mycompany.robotliberation.LoadAllResources;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Line;
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

    public Rocket(double startPositionOfShotX, double startPositionOfShotY, double angleOfFiredShot, GraphicsContext graphicsContext) {
        super(startPositionOfShotX, startPositionOfShotY, angleOfFiredShot, graphicsContext);
        projectileImage = LoadAllResources.getMapOfAllImages().get("rocket1");
    }

    @Override
    public Shape getShapeForDetection() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void paintProjectile() {
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
        graphicsContext.translate(possitionX + projectileImage.getWidth() / 2, possitionY + projectileImage.getHeight() / 2);
        graphicsContext.rotate(angleOfFiredShot - 180);
        graphicsContext.drawImage(projectileImage, -projectileImage.getWidth() / 2, -projectileImage.getHeight() / 2);
        graphicsContext.restore();
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
        graphicsContext.drawImage(projectileImage, possitionX, possitionY);

        return true;
    }

    @Override
    public boolean hasProjectileReachedDestination() {
        rocketDistanceCounter++;
        if (rocketDistanceCounter > 75) {
            return true;
        }
        return false;
    }

}
