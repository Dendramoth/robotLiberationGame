/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.robotliberation;

import Enemies.Enemy;
import Weapons.ProjectileWeapon;
import Weapons.Rocket;
import java.util.ArrayList;
import java.util.Iterator;
import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author Dendra
 */
public class AllProjectilesContainer {

    private ArrayList<ProjectileWeapon> allProjectilesList = new ArrayList<ProjectileWeapon>();
    private ArrayList<ProjectileWeapon> allExplodingProjectilesList = new ArrayList<ProjectileWeapon>();

    public void moveAllRockets() {
        Iterator<ProjectileWeapon> iterator = allProjectilesList.iterator();
        while (iterator.hasNext()) {
            ProjectileWeapon projectileWeapon = iterator.next();
            projectileWeapon.moveProjectile();
            if (projectileWeapon.hasProjectileReachedDestination()) {
                allExplodingProjectilesList.add(projectileWeapon);
                iterator.remove();
            }
        }
    }

    public void explodeAllExplodingRockets() {
        Iterator<ProjectileWeapon> iterator = allExplodingProjectilesList.iterator();
        while (iterator.hasNext()) {
            ProjectileWeapon projectileWeapon = iterator.next();
            if (!projectileWeapon.projectileExplosion()) {
                iterator.remove();
            }
        }
    }

    public void moveAllRocketsBasedOnPlayerMovement(double changeX, double changeY) {
        Iterator<ProjectileWeapon> iterator = allProjectilesList.iterator();
        while (iterator.hasNext()) {
            ProjectileWeapon projectileWeapon = iterator.next();
            projectileWeapon.moveProjectileBasedOnPlayerMovement(changeX, changeY);
        }
    }

    public void moveAllExplodingRocketsBasedOnPlayerMovement(double changeX, double changeY) {
        Iterator<ProjectileWeapon> iterator = allExplodingProjectilesList.iterator();
        while (iterator.hasNext()) {
            ProjectileWeapon projectileWeapon = iterator.next();
            projectileWeapon.moveProjectileBasedOnPlayerMovement(changeX, changeY);
        }
    }

    public void paintAllRockets() {
        Iterator<ProjectileWeapon> iterator = allProjectilesList.iterator();
        while (iterator.hasNext()) {
            ProjectileWeapon projectileWeapon = iterator.next();
            projectileWeapon.paintGameObject();
        }
    }

    public void addNewRocket(double possitionX, double possitionY, double turretAngle, GraphicsContext graphicsContext, Enemy enemy) {
        Rocket rocket = new Rocket(graphicsContext, turretAngle, possitionX, possitionY, enemy);
        allProjectilesList.add(rocket);
    }

    public ArrayList<ProjectileWeapon> getAllProjectilesList() {
        return allProjectilesList;
    }

    public ArrayList<ProjectileWeapon> getAllExplodingProjectilesList() {
        return allExplodingProjectilesList;
    }



}
