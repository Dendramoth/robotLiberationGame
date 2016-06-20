/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Enemies;

import EnviromentObjects.Rock;
import GameObjects.GameObjectWithColision;
import java.util.ArrayList;
import java.util.Iterator;
import javafx.scene.shape.Shape;

/**
 *
 * @author Dendra
 */
public class PathFinding {

    private ArrayList<Enemy> allLivingEnemiesList = new ArrayList<Enemy>();
    private ArrayList<Rock> allRocks = new ArrayList<Rock>();

    public PathFinding(ArrayList<Enemy> allLivingEnemiesList, ArrayList<Rock> allRocks) {
        this.allLivingEnemiesList = allLivingEnemiesList;
        this.allRocks = allRocks;
    }

    public boolean detectFreeSpaceForMovement(Enemy enemy, Shape shape) {
        ArrayList<GameObjectWithColision> gameObjects = new ArrayList<GameObjectWithColision>();
        for (GameObjectWithColision gameObjectWithColision : allLivingEnemiesList){
            gameObjects.add(gameObjectWithColision);
        }
        for (GameObjectWithColision gameObjectWithColision : allRocks){
            gameObjects.add(gameObjectWithColision);
        }

        Iterator<GameObjectWithColision> iterator = gameObjects.iterator();
        while (iterator.hasNext()) {
            GameObjectWithColision gameObjectWithColision = iterator.next();
            if (gameObjectWithColision != enemy) {
                if (gameObjectWithColision.detectCollision(shape)) {
                    return false;
                }
            }
        }

        return true;
    }
}
