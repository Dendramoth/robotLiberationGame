/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.robotliberation;

import Enemies.Enemy;
import Weapons.Rocket;
import java.util.ArrayList;
import java.util.Iterator;
import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author Dendra
 */
public class AllProjectilesContainer {

    private ArrayList<Rocket> allRocketList = new ArrayList<Rocket>();
    private ArrayList<Rocket> allExplodingRocketList = new ArrayList<Rocket>();

    public void moveAllRockets() {
        Iterator<Rocket> iterator = allRocketList.iterator();
        while (iterator.hasNext()) {
            Rocket rocket = iterator.next();
            rocket.moveProjectile();
            if (rocket.hasProjectileReachedDestination()) {
                allExplodingRocketList.add(rocket);
                iterator.remove();
            }
        }
    }

    public void explodeAllExplodingRockets() {
        Iterator<Rocket> iterator = allExplodingRocketList.iterator();
        while (iterator.hasNext()) {
            Rocket rocket = iterator.next();
            if (!rocket.projectileExplosion()) {
                iterator.remove();
            }
        }
    }

    public void moveAllRocketsBasedOnPlayerMovement(double changeX, double changeY) {
        Iterator<Rocket> iterator = allRocketList.iterator();
        while (iterator.hasNext()) {
            Rocket rocket = iterator.next();
            rocket.moveProjectileBasedOnPlayerMovement(changeX, changeY);
        }
    }

    public void moveAllExplodingRocketsBasedOnPlayerMovement(double changeX, double changeY) {
        Iterator<Rocket> iterator = allExplodingRocketList.iterator();
        while (iterator.hasNext()) {
            Rocket rocket = iterator.next();
            rocket.moveProjectileBasedOnPlayerMovement(changeX, changeY);
        }
    }

    public void paintAllRockets() {
        Iterator<Rocket> iterator = allRocketList.iterator();
        while (iterator.hasNext()) {
            Rocket rocket = iterator.next();
            rocket.paintGameObject();
        }
    }

    public void addNewRocket(double possitionX, double possitionY, double turretAngle, GraphicsContext graphicsContext, Enemy enemy) {
        Rocket rocket = new Rocket(graphicsContext, turretAngle, possitionX, possitionY, enemy);
        allRocketList.add(rocket);
    }

    public ArrayList<Rocket> getAllRocketList() {
        return allRocketList;
    }

    public ArrayList<Rocket> getAllExplodingRocketList() {
        return allExplodingRocketList;
    }

}
