/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.robotliberation;

import GameObjects.GameObject;
import Enemies.AllEnemiesContainer;
import Enemies.Enemy;
import Weapons.Rocket;
import com.mycompany.robotliberation.playerRobot.PlayerRobot;
import com.mycompany.robotliberation.playerRobot.ShotsFromMinigun;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 *
 * @author Dendra
 */
public class CalculateCollisions {

    private AllProjectilesContainer allProjectilesContainer;
    private PlayerRobot playerRobot;
    private ArrayList<GameObject> allGameObjects = new ArrayList<GameObject>();

    private ArrayList<Enemy> allLivingEnemiesList = new ArrayList<Enemy>();
    private ArrayList<Enemy> allDyingEneniesList = new ArrayList<Enemy>();
    
    private ArrayList<Rocket> allRocketList = new ArrayList<Rocket>();
    private ArrayList<Rocket> allExplodingRocketList = new ArrayList<Rocket>();

    public CalculateCollisions(AllProjectilesContainer allProjectilesContainer, AllEnemiesContainer allEnemiesContainer, PlayerRobot playerRobot) {
        this.allLivingEnemiesList = allEnemiesContainer.getAllLivingEnemiesList();
        this.allDyingEneniesList = allEnemiesContainer.getAllDyingEneniesList();
        
        this.allRocketList = allProjectilesContainer.getAllRocketList();
        this.allExplodingRocketList = allProjectilesContainer.getAllExplodingRocketList();
        this.playerRobot = playerRobot;
    }

    public void detectCollisionsOfAllEnemiesWithPlayerRobot() {
        Iterator<Enemy> iterator = allLivingEnemiesList.iterator();
        while (iterator.hasNext()) {
            Enemy enemyWithCollision = iterator.next();
            if (enemyWithCollision.detectCollision(playerRobot.getPlayerRobotPolygon())) {
                playerRobot.removeHitPoints(1);
                enemyWithCollision.setAlive(false);
                allDyingEneniesList.add(enemyWithCollision);
                iterator.remove();
            }
        }
    }
    
    public void detectCollisionsOfAllEnemiesWithShots() {
        if (playerRobot.getAllShotsFromMinigun() != null && playerRobot.getAllShotsFromMinigun().size() > 0) {
            ShotsFromMinigun shotFromMinigun = playerRobot.getAllShotsFromMinigun().get(0);
            shotFromMinigun.getLineForDetection();

            for (int i = 0; i < allLivingEnemiesList.size(); i++){
                allLivingEnemiesList.get(i).setObjectForComparisonPosX(playerRobot.getPossitionOnCanvasX());
                allLivingEnemiesList.get(i).setObjectForComparisonPosY(playerRobot.getPossitionOnCanvasY());
            }
            
            Collections.sort(allLivingEnemiesList);
            
            Iterator<Enemy> iterator = allLivingEnemiesList.iterator();
            while (iterator.hasNext()) {
                Enemy enemy = iterator.next();
                if (enemy.detectCollision(shotFromMinigun.getLineForDetection())) {
                    enemy.doOnBeingHit();
                    if (enemy.getHitPoints() < 1) {
                        enemy.setAlive(false);
                        allDyingEneniesList.add(enemy);
                        iterator.remove();
                    }
                    break;
                }
            }

            playerRobot.getAllShotsFromMinigun().clear();
        }
    }
    
    public void detectCollisionsWithRockets() {
       Iterator<Rocket> iterator = allRocketList.iterator();
        while (iterator.hasNext()) {
            Rocket rocket = iterator.next();
           
            if (rocket.hasProjectileReachedDestination()) {
                allExplodingRocketList.add(rocket);
                iterator.remove();
            }
        }
    }

}
