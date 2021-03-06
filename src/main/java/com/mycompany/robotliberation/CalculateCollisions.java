/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.robotliberation;

import EnviromentObjects.GameEnviroment;
import Enemies.AllEnemiesContainer;
import Enemies.Enemy;
import Enemies.EvilDroneMarkOne;
import EnviromentObjects.Rock;
import GameObjects.GameObjectWithColision;
import playerRobot.PlayerRobot;
import playerRobot.ShotsFromMinigun;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import Weapons.ProjectileWeapon;
import javafx.scene.shape.Shape;

/**
 *
 * @author Dendra
 */
public class CalculateCollisions {

    private AllProjectilesContainer allProjectilesContainer;
    private PlayerRobot playerRobot;
    private GameEnviroment gameEnviroment;

    private ArrayList<GameObjectWithColision> allGameObjectsWithColisions = new ArrayList<GameObjectWithColision>();
    private ArrayList<Enemy> allLivingEnemiesList = new ArrayList<Enemy>();
    private ArrayList<Enemy> allDyingEneniesList = new ArrayList<Enemy>();

    private ArrayList<ProjectileWeapon> allProjectilesList = new ArrayList<ProjectileWeapon>();
    private ArrayList<ProjectileWeapon> allExplodingProjectilesList = new ArrayList<ProjectileWeapon>();
    
    private ArrayList<Rock> allRocks = new ArrayList<Rock>();

    public CalculateCollisions(AllProjectilesContainer allProjectilesContainer, AllEnemiesContainer allEnemiesContainer, PlayerRobot playerRobot, GameEnviroment gameEnviroment) {
        this.allLivingEnemiesList = allEnemiesContainer.getAllLivingEnemiesList();
        this.allDyingEneniesList = allEnemiesContainer.getAllDyingEneniesList();

        this.allProjectilesList = allProjectilesContainer.getAllProjectilesList();
        this.allExplodingProjectilesList = allProjectilesContainer.getAllExplodingProjectilesList();
        this.allRocks = gameEnviroment.getAllRocks();
        this.playerRobot = playerRobot;
        this.gameEnviroment = gameEnviroment;
    }

    public void detectCollisionsOfAllEnemiesWithPlayerRobot() {
        Iterator<Enemy> iterator = allLivingEnemiesList.iterator();
        while (iterator.hasNext()) {
            Enemy enemyWithCollision = iterator.next();
            if (enemyWithCollision.detectCollision(playerRobot.getPlayerRobotPolygon())) {
                playerRobot.doOnCollision(null);
                playerRobot.removeHitPoints(40);
                enemyWithCollision.setAlive(false);
                allDyingEneniesList.add(enemyWithCollision);
                iterator.remove();
            }
        }
    }

    public void detectCollisionsOfAllEnemiesWithPlayerMinigunShots() {
        if (playerRobot.getAllShotsFromMinigun() != null && playerRobot.getAllShotsFromMinigun().size() > 0) {
            ShotsFromMinigun shotFromMinigun = playerRobot.getAllShotsFromMinigun().get(0);
            Boolean shotHitSomething = false;

            for (int i = 0; i < allLivingEnemiesList.size(); i++) {
                allLivingEnemiesList.get(i).setObjectForComparisonPosX(playerRobot.getPossitionOnCanvasX());
                allLivingEnemiesList.get(i).setObjectForComparisonPosY(playerRobot.getPossitionOnCanvasY());
            }

            Collections.sort(allLivingEnemiesList);

            Iterator<Enemy> iterator = allLivingEnemiesList.iterator();
            while (iterator.hasNext()) {
                Enemy enemy = iterator.next();
                if (enemy.detectCollision(shotFromMinigun.getLineForDetection())) {
                    enemy.doOnBeingHit("minigun");
                    if (enemy.getHitPoints() < 1) {
                        enemy.setAlive(false);
                        allDyingEneniesList.add(enemy);
                        iterator.remove();
                    }
                    shotHitSomething = true;
                    break;
                }
            }

            if (!shotHitSomething) {
                gameEnviroment.generateNewMinigunHitOnGround(shotFromMinigun.getEndPositionOfShotX(), shotFromMinigun.getEndPositionOfShotY());
            }
            playerRobot.getAllShotsFromMinigun().clear();
        }
    }

    public void detectCollisionsWithRockets() {

        Iterator<ProjectileWeapon> iterator = allProjectilesList.iterator();
        while (iterator.hasNext()) {
            ProjectileWeapon projectileWeapon = iterator.next();

            allGameObjectsWithColisions.clear();
            for (int i = 0; i < allLivingEnemiesList.size(); i++) {
                allGameObjectsWithColisions.add(allLivingEnemiesList.get(i));
                allGameObjectsWithColisions.get(i).setObjectForComparisonPosX(projectileWeapon.getPossitionOnCanvasX());
                allGameObjectsWithColisions.get(i).setObjectForComparisonPosY(projectileWeapon.getPossitionOnCanvasY());
            }
            allGameObjectsWithColisions.add(playerRobot);
            playerRobot.setObjectForComparisonPosX(projectileWeapon.getPossitionOnCanvasX());
            playerRobot.setObjectForComparisonPosY(projectileWeapon.getPossitionOnCanvasY());

            allGameObjectsWithColisions.remove(projectileWeapon.getEnemyWhoShootedThisProjectile()); // remove shooting turret for detection who was hit

            Collections.sort(allGameObjectsWithColisions);

            Iterator<GameObjectWithColision> iteratorEnemy = allGameObjectsWithColisions.iterator();
            while (iteratorEnemy.hasNext()) {
                GameObjectWithColision gameObject = iteratorEnemy.next();
                if (gameObject.detectCollision(projectileWeapon.getShapeForDetection())) {
                    gameObject.doOnBeingHit("rocket");
                    projectileWeapon.doOnBeingHit("anything");
                    if (gameObject instanceof EvilDroneMarkOne) {
                        EvilDroneMarkOne evilDroneMarkOne = (EvilDroneMarkOne) gameObject;
                        if (evilDroneMarkOne.getHitPoints() < 1) {
                            evilDroneMarkOne.setAlive(false);
                            allDyingEneniesList.add(evilDroneMarkOne);
                            allLivingEnemiesList.remove(evilDroneMarkOne);
                            iterator.remove();
                        }
                    }

                    break;
                }
            }
        }
    }

}
