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
public class StaticTurret extends Enemy {

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

    public StaticTurret(AllProjectilesContainer allProjectilesContainer, double movementSpeed, double damagedStateTreshold, int hitPoints, GraphicsContext graphicsContext, double possitionOnCanvasX, double possitionOnCanvasY) {
        super(movementSpeed, damagedStateTreshold, hitPoints, graphicsContext, possitionOnCanvasX, possitionOnCanvasY);
        enemyImage = LoadAllResources.getMapOfAllImages().get("idleTurret");
        this.allProjectilesContainer = allProjectilesContainer;
        hitPoints = 50;
    }

    @Override
    public void moveEnemy(double playerPossitionX, double playerPossitionY) {
        playerPossX = playerPossitionX;
        playerPossY = playerPossitionY;
        double deltaX = (playerPossitionX - possitionOnCanvasX - enemyImage.getWidth() / 2);
        double deltaY = -(playerPossitionY - possitionOnCanvasY - enemyImage.getWidth() / 2);

        /**
         * rotate Turret to player by turretAngleSpeed
         */
        if (active && !playInitialIntro) {
            double angleToPlayer = (Math.toDegrees(Math.atan2(deltaX, deltaY)) + 360) % 360;
            turretAngle = (turretAngle + 360) % 360;
            if (((turretAngle - angleToPlayer > 0) && (turretAngle - angleToPlayer < 180)) || (turretAngle - angleToPlayer < -180)) {
                turretAngle = turretAngle + turretAngleSpeed;
            } else {
                turretAngle = turretAngle - turretAngleSpeed;
            }
        }

        if (!active) {
            double distanceFromPlayer = Math.sqrt((deltaX * deltaX) + (deltaY * deltaY));
            if (distanceFromPlayer < 500 && active == false) {
                active = true;
                playInitialIntro = true;
            }
        }
    }

    @Override
    public void paintEnemy(GraphicsContext enemyGraphicsContext) {
        if (playInitialIntro) {
            paintInitialIntro(enemyGraphicsContext);
            enemyGraphicsContext.drawImage(enemyImage, possitionOnCanvasX, possitionOnCanvasY);
        } else if (active) {
            rocketCounter++;
            if (rocketCounter > 100) {
                rocketCounter = 0;
                fireRocket(enemyGraphicsContext);
            }

            enemyImage = LoadAllResources.getMapOfAllImages().get("turretBase");
            enemyGraphicsContext.drawImage(enemyImage, possitionOnCanvasX, possitionOnCanvasY);
            paintRotatedGunOnTurret(enemyGraphicsContext);
        } else {
            enemyImage = LoadAllResources.getMapOfAllImages().get("idleTurret");
            enemyGraphicsContext.drawImage(enemyImage, possitionOnCanvasX, possitionOnCanvasY);
        }
    }

    private void paintRotatedGunOnTurret(GraphicsContext enemyGraphicsContext) {
        if (hitPoints > 35) {
            enemyImage = LoadAllResources.getMapOfAllImages().get("turretTower");
        } else {
            enemyImage = LoadAllResources.getMapOfAllImages().get("turretDamaged");
        }
        enemyGraphicsContext.save();
        enemyGraphicsContext.translate(possitionOnCanvasX + enemyImage.getWidth() / 2, possitionOnCanvasY + enemyImage.getHeight() / 2);
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
        allProjectilesContainer.addNewRocket(possitionOnCanvasX, possitionOnCanvasY, turretAngle, graphicsContext);
    }

    @Override
    public void changeEnemyPositionBasedOnRobotMovement(double changeX, double changeY) {
        possitionOnCanvasX = possitionOnCanvasX + changeX;
        possitionOnCanvasY = possitionOnCanvasY + changeY;
        //moveAllRocketsBasedOnPlayerMovement(changeX, changeY);
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
        enemyGraphicsContext.drawImage(LoadAllResources.getMapOfAllImages().get("turretBaseDead"), possitionOnCanvasX, possitionOnCanvasY);

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
        enemyGraphicsContext.translate(possitionOnCanvasX + enemyImage.getWidth() / 2 - 32, possitionOnCanvasY + enemyImage.getHeight() / 2 - 32);
        enemyGraphicsContext.rotate(turretAngle);
        enemyGraphicsContext.drawImage(enemyImage, -enemyImage.getWidth() / 2, -enemyImage.getHeight() / 2);
        enemyGraphicsContext.restore();

        //      enemyGraphicsContext.drawImage(enemyImage, possitionX - 32, possitionY - 32);
        explodingTimer++;
        return true;
    }

    @Override
    public void paintDeadEnemy(GraphicsContext enemyGraphicsContext) {
        enemyGraphicsContext.drawImage(LoadAllResources.getMapOfAllImages().get("turretBaseDead"), possitionOnCanvasX, possitionOnCanvasY);
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

    @Override
    public void paintGameObject() {
    }

    @Override
    public void moveGameObject() {
    }

}
