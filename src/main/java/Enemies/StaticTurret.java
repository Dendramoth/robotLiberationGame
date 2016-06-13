/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Enemies;

import Weapons.Rocket;
import com.mycompany.robotliberation.AllProjectilesContainer;
import com.mycompany.robotliberation.GameMainInfrastructure;
import com.mycompany.robotliberation.LoadAllResources;
import com.mycompany.robotliberation.playerRobot.PlayerRobot;
import java.util.ArrayList;
import java.util.Iterator;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Circle;
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
    
    private int rocketCounter = 0;
    private int explodingTimer = 0;
    private double turretAngleSpeed = 1;
    private AllProjectilesContainer allProjectilesContainer;

    public StaticTurret(double x, double y, double speed, AllProjectilesContainer allProjectilesContainer) {
        super(x, y, 0); //zero speed turret is static
        enemyImage = LoadAllResources.getMapOfAllImages().get("idleTurret");
        this.allProjectilesContainer = allProjectilesContainer;
        hitPoints = 50;
    }

    @Override
    public void moveEnemy(double playerPossitionX, double playerPossitionY) {
        playerPossX = playerPossitionX;
        playerPossY = playerPossitionY;
        double dX = (playerPossitionX - possitionX - 32);
        double dY = -(playerPossitionY - possitionY - 32);
        double distanceFromPlayer = Math.sqrt((dX * dX) + (dY * dY));
        
        //drobna oprava, uz se ti turret spravne toci, ikdyz ho objizdis ze spoda
        //a pouziti Math.atan2: neni treba zvlastni vypocty pro ruzny kvadranty a nulovy hodnoty
        // - Wh1skeyjack
        if (active && !playInitialIntro) {
            double playerAngle = (Math.toDegrees(Math.atan2(dX, dY))+360)%360;
            turretAngle = (turretAngle+360)%360;
            double dAngle = turretAngle - playerAngle;
            if (((dAngle>0)&&(dAngle<180))||(dAngle<-180)) {
                turretAngle = turretAngle + turretAngleSpeed;
            } else {
                turretAngle = turretAngle - turretAngleSpeed;
            }
        }
        
        // tohle a getAngleForRotationOfTurretToPlayer muzes asi smazat - Wh1skeyjack
            /*if (active == true) {
                double currentAngleToPlayer = getAngleForRotationOfTurretToPlayer(playerPossitionX, playerPossitionY);
                turretAngle = (turretAngle % 360);
                if (Math.abs(currentAngleToPlayer) - Math.abs(turretAngle) > 0) {
                    turretAngle = turretAngle - turretAngleSpeed;
                } else {
                    turretAngle = turretAngle + turretAngleSpeed;
                }
            }
        }*/
        if (distanceFromPlayer < 500 && active == false) {
            active = true;
            playInitialIntro = true;
        }
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
            rocketCounter++;
            if (rocketCounter > 100) {
                rocketCounter = 0;
                fireRocket(enemyGraphicsContext);
            }

            enemyImage = LoadAllResources.getMapOfAllImages().get("turretBase");
            enemyGraphicsContext.drawImage(enemyImage, possitionX, possitionY);
            paintRotatedGunOnTurret(enemyGraphicsContext);
        } else {
            enemyImage = LoadAllResources.getMapOfAllImages().get("idleTurret");
            enemyGraphicsContext.drawImage(enemyImage, possitionX, possitionY);
        }
    }

    private void paintRotatedGunOnTurret(GraphicsContext enemyGraphicsContext) {
        if (hitPoints > 35) {
            enemyImage = LoadAllResources.getMapOfAllImages().get("turretTower");
        } else {
            enemyImage = LoadAllResources.getMapOfAllImages().get("turretDamaged");
        }
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
        } else if (initialIntroFrame > 40 && initialIntroFrame <= 48) {
            enemyImage = LoadAllResources.getMapOfAllImages().get("turretIntro5");
        } else if (initialIntroFrame > 48) {
            playInitialIntro = false;
        }
    }

    private void fireRocket(GraphicsContext graphicsContext) {
        allProjectilesContainer.addNewRocket(possitionX, possitionY, turretAngle, graphicsContext);
    }

    @Override
    public void changeEnemyPositionBasedOnRobotMovement(double changeX, double changeY) {
        possitionX = possitionX + changeX;
        possitionY = possitionY + changeY;
        //moveAllRocketsBasedOnPlayerMovement(changeX, changeY);
    }



    @Override
    public void paintAllExplosionsEnemy(GraphicsContext enemyGraphicsContext) {
        Iterator<Explosion> iterator = allExplosionsOnEnemy.iterator();
        while (iterator.hasNext()) {
            Explosion explosion = iterator.next();
            explosion.paint(possitionX, possitionY, enemyGraphicsContext);
            if (explosion.getNumberOfFramesBeingDisplayed() < 1) {
                iterator.remove();
            }
        }
    }

    @Override
    protected boolean paintDyingEnemyAnimation(GraphicsContext enemyGraphicsContext) {
        enemyGraphicsContext.drawImage(LoadAllResources.getMapOfAllImages().get("turretBaseDead"), possitionX, possitionY);

        if (explodingTimer < 4) {
            enemyImage = LoadAllResources.getMapOfAllImages().get("turretDeath1");
        } else if (explodingTimer <= 10) {
            enemyImage = LoadAllResources.getMapOfAllImages().get("turretDeath2");
        } else if (explodingTimer > 10 && explodingTimer <= 20) {
            enemyImage = LoadAllResources.getMapOfAllImages().get("turretDeath3");
        } else if (explodingTimer > 20 && explodingTimer <= 30) {
            enemyImage = LoadAllResources.getMapOfAllImages().get("turretDeath4");
        } else if (explodingTimer > 30 && explodingTimer <= 40) {
            enemyImage = LoadAllResources.getMapOfAllImages().get("turretDeath5");
        } else if (explodingTimer > 40 && explodingTimer <= 50) {
            enemyImage = LoadAllResources.getMapOfAllImages().get("turretDeath6");
        } else {
            return false;
        }

        enemyGraphicsContext.save();
        enemyGraphicsContext.translate(possitionX + enemyImage.getWidth() / 2 - 32, possitionY + enemyImage.getHeight() / 2 - 32);
        enemyGraphicsContext.rotate(turretAngle);
        enemyGraphicsContext.drawImage(enemyImage, -enemyImage.getWidth() / 2, -enemyImage.getHeight() / 2);
        enemyGraphicsContext.restore();

        //      enemyGraphicsContext.drawImage(enemyImage, possitionX - 32, possitionY - 32);
        explodingTimer++;
        return true;
    }

    @Override
    public void paintDeadEnemy(GraphicsContext enemyGraphicsContext) {
        enemyGraphicsContext.drawImage(LoadAllResources.getMapOfAllImages().get("turretBaseDead"), possitionX, possitionY);
    }

    @Override
    public boolean detectCollision(Shape shape) {
        if (alive) {
            Circle meteorPolygon = new Circle(possitionX + enemyImage.getWidth() / 2, possitionY + enemyImage.getHeight() / 2, (enemyImage.getHeight() / 2));
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
    public boolean doOnCollision(GraphicsContext enemyGraphicsContext) {
        return paintDyingEnemyAnimation(enemyGraphicsContext);
    }

    @Override
    public void doOnBeingHit() {
        hitPoints--;
        allExplosionsOnEnemy.add(new Explosion());
        if (hitPoints < 35) {
            turretAngleSpeed = 0.3;
        }
    }

}
