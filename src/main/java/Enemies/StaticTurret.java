/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Enemies;

import Weapons.Rocket;
import com.mycompany.robotliberation.GameMainInfrastructure;
import com.mycompany.robotliberation.LoadAllResources;
import com.mycompany.robotliberation.playerRobot.PlayerRobot;
import java.util.ArrayList;
import java.util.Iterator;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Shape;

/**
 *
 * @author Dendra
 */
public class StaticTurret extends EnemyWithCollision {

    private boolean active = false;
    private boolean playInitialIntro = false;
    private int initialIntroFrame = 0;
    private double turretAngle = 0;
    private double playerPossX = 0;
    private double playerPossY = 0;
    private ArrayList<Rocket> allRocketList = new ArrayList<Rocket>();
    private int rocketCounter = 0;

    public StaticTurret(double x, double y, double speed) {
        super(x, y, 0); //zero speed turret is static
        enemyImage = LoadAllResources.getMapOfAllImages().get("idleTurret");
        hitPoints = 50;
    }

    @Override
    public void moveEnemy(double playerPossitionX, double playerPossitionY) {
        playerPossX = playerPossitionX;
        playerPossY = playerPossitionY;

        double deltaX = playerPossitionX - possitionX;
        double deltaY = playerPossitionY - possitionY;
        double distanceFromPlayer = Math.sqrt((deltaX * deltaX) + (deltaY * deltaY));
        if (distanceFromPlayer < 500 && active == false) {
            active = true;
            playInitialIntro = true;
        }
        if (active == true) {
            double currentAngleToPlayer = getAngleForRotationOfTurretToPlayer(playerPossitionX, playerPossitionY);

            turretAngle = (turretAngle % 360);
            //     System.out.println(angle);
            //      System.out.println(currentAngleToPlayer);

            if (Math.abs(currentAngleToPlayer) - Math.abs(turretAngle) > 0) {
                turretAngle = turretAngle - 0.5;
    //            System.out.println("-0.5");
            } else {
                turretAngle = turretAngle + 0.5;
      //          System.out.println("+0.5");
            }

            //         System.out.println("------------------------");
        }
        
        moveAllRockets();
    }

    private double getAngleForRotationOfTurretToPlayer(double playerPossitionX, double playerPossitionY) {
        double angle;
        double x = (playerPossitionX - possitionX - 32); // 32 = half of the player robot image
        double y = -(playerPossitionY - possitionY - 32); // 32 = half of the player robot image

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
    public void paintEnemy(GraphicsContext enemyGraphicsContext) {
        if (playInitialIntro) {
            paintInitialIntro(enemyGraphicsContext);
            enemyGraphicsContext.drawImage(enemyImage, possitionX, possitionY);
        } else if (active) {
            enemyImage = LoadAllResources.getMapOfAllImages().get("turretBase");
            enemyGraphicsContext.drawImage(enemyImage, possitionX, possitionY);
            paintRotatedGunOnTurret(enemyGraphicsContext);
        } else {
            enemyImage = LoadAllResources.getMapOfAllImages().get("idleTurret");
            enemyGraphicsContext.drawImage(enemyImage, possitionX, possitionY);
        }

        rocketCounter++;
        if (rocketCounter > 100) {
            rocketCounter = 0;
            fireRocket(enemyGraphicsContext);
        }

        paintAllRockets();
    }

    private void paintRotatedGunOnTurret(GraphicsContext enemyGraphicsContext) {
        enemyImage = LoadAllResources.getMapOfAllImages().get("turretTower");
        enemyGraphicsContext.save();
        enemyGraphicsContext.translate(possitionX + enemyImage.getWidth() / 2, possitionY + enemyImage.getHeight() / 2);
        enemyGraphicsContext.rotate(turretAngle);
        enemyGraphicsContext.drawImage(enemyImage, -enemyImage.getWidth() / 2, -enemyImage.getHeight() / 2);
        enemyGraphicsContext.restore();
    }

    private void paintInitialIntro(GraphicsContext graphicsContext) {
        initialIntroFrame++;
        if (initialIntroFrame <= 8) {
            enemyImage = LoadAllResources.getMapOfAllImages().get("turretIntro1");
        } else if (initialIntroFrame > 13 && initialIntroFrame <= 24) {
            enemyImage = LoadAllResources.getMapOfAllImages().get("turretIntro2");
        } else if (initialIntroFrame > 24 && initialIntroFrame <= 32) {
            enemyImage = LoadAllResources.getMapOfAllImages().get("turretIntro3");
        } else if (initialIntroFrame > 32 && initialIntroFrame <= 40) {
            enemyImage = LoadAllResources.getMapOfAllImages().get("turretIntro4");
        } else if (initialIntroFrame > 40) {
            playInitialIntro = false;
        }
    }

    private void fireRocket(GraphicsContext graphicsContext) {
        Rocket rocket = new Rocket(possitionX, possitionY, turretAngle, playerPossX, playerPossY, graphicsContext);
        allRocketList.add(rocket);
    }

    public void moveAllRockets() {
        Iterator<Rocket> iterator = allRocketList.iterator();
        while (iterator.hasNext()) {
            Rocket rocket = iterator.next();
            rocket.moveRocket();
            if (rocket.hasRocketReachedDestination()){
                iterator.remove();
            }
        }
    }
    
    private void moveAllRocketsBasedOnPlayerMovement(double changeX, double changeY){
        Iterator<Rocket> iterator = allRocketList.iterator();
        while (iterator.hasNext()) {
            Rocket rocket = iterator.next();
            rocket.moveRocketBasedOnPlayerMovement(changeX, changeY);
        }
    }
    
    @Override
    public void changeEnemyPositionBasedOnRobotMovement(double changeX, double changeY){
        possitionX = possitionX + changeX;
        possitionY = possitionY + changeY;
        moveAllRocketsBasedOnPlayerMovement(changeX, changeY);
    }
    
    public void paintAllRockets() {
        Iterator<Rocket> iterator = allRocketList.iterator();
        while (iterator.hasNext()) {
            Rocket rocket = iterator.next();
            rocket.paintRocket();
        }
    }
    
    @Override
    public void paintAllExplosionsEnemy(GraphicsContext enemyGraphicsContext) {

    }

    @Override
    protected void paintDyingEnemyAnimation(GraphicsContext enemyGraphicsContext) {

    }

    @Override
    public boolean detectCollision(Shape shape) {
        return false;
    }

    @Override
    public boolean doOnCollision(GraphicsContext enemyGraphicsContext) {
        return false;
    }

    @Override
    public void doOnBeingHit() {
    }

    public ArrayList<Rocket> getAllRocketList() {
        return allRocketList;
    }

}
