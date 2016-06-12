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

}
