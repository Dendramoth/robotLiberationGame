/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.robotliberation;

import GameObjects.GameObject;
import Enemies.AllEnemiesContainer;
import Enemies.Enemy;
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

    public CalculateCollisions(AllProjectilesContainer allProjectilesContainer, AllEnemiesContainer allEnemiesContainer, PlayerRobot playerRobot) {
        this.allLivingEnemiesList = allEnemiesContainer.getAllLivingEnemiesList();
        this.allDyingEneniesList = allEnemiesContainer.getAllDyingEneniesList();
        
        this.allProjectilesContainer = allProjectilesContainer;
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

            for (GameObject gameObject : allLivingEnemiesList){
                gameObject.setObjectForComparisonPosX(playerRobot.getPossitionOnCanvasX());
                gameObject.setObjectForComparisonPosY(playerRobot.getPossitionOnCanvasY());
            }
            
            Collections.sort(allGameObjects);
            
            Iterator<Enemy> iterator = allLivingEnemiesList.iterator();
            while (iterator.hasNext()) {
                Enemy enemyWithCollision = iterator.next();
                if (enemyWithCollision.detectCollision(shotFromMinigun.getLineForDetection())) {
                    enemyWithCollision.doOnBeingHit();
                    if (enemyWithCollision.getHitPoints() < 1) {
                        enemyWithCollision.setAlive(false);
                        allDyingEneniesList.add(enemyWithCollision);
                        iterator.remove();
                    }
                    break;
                }
            }

            playerRobot.getAllShotsFromMinigun().clear();
        }
    }
    
    public void detectCollisionsWithRockets() {
       
    }

}
