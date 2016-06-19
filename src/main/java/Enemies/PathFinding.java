/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Enemies;

import java.util.ArrayList;
import java.util.Iterator;
import javafx.scene.shape.Shape;

/**
 *
 * @author Dendra
 */
public class PathFinding {

    private ArrayList<Enemy> allLivingEnemiesList = new ArrayList<Enemy>();

    public PathFinding(ArrayList<Enemy> allLivingEnemiesList) {
        this.allLivingEnemiesList = allLivingEnemiesList;
    }

    public boolean detectFreeSpaceForMovement(Enemy enemy, Shape shape) {

        Iterator<Enemy> iterator = allLivingEnemiesList.iterator();
        while (iterator.hasNext()) {
            Enemy enemyWithCollision = iterator.next();
            if (enemyWithCollision != enemy) {
                if (enemyWithCollision.detectCollision(shape)) {
                    return false;
                }
            }
        }

        return true;
    }
}
