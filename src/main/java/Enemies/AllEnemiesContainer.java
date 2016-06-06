/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Enemies;

import EvilDrone.EvilDroneMarkOne;
import com.mycompany.robotliberation.GameMainInfrastructure;
import com.mycompany.robotliberation.playerRobot.PlayerRobot;
import java.util.ArrayList;
import java.util.Iterator;
import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author Dendra
 */
public class AllEnemiesContainer {
    private ArrayList<Enemy> allEnemiesList = new ArrayList<Enemy>();
    
    public AllEnemiesContainer(){
        
    }
    
    public void generateEvilDroneMark1(double possX, double possY){
        EvilDrone.EvilDroneMarkOne evilDroneMarkOne = new EvilDroneMarkOne(possX, possY, 5);
        allEnemiesList.add(evilDroneMarkOne);
    }
    
    public void moveAllEnemies(){
        Iterator<Enemy> iterator = allEnemiesList.iterator();
        while (iterator.hasNext()) {
            Enemy enemy = iterator.next();
            enemy.moveEnemy(PlayerRobot.getPossitionX(), PlayerRobot.getPossitionY());
            if (enemy.getPossitionX() > 2000) {
                iterator.remove();
                break;
            }
            if (enemy.getPossitionX() < 0) {
                iterator.remove();
                break;
            }
            if (enemy.getPossitionY() > 2000) {
                iterator.remove();
                break;
            }
            if (enemy.getPossitionY() < 0) {
                iterator.remove();
            }
        }
    }
    
    public void detectCollisionsOfAllEnemiesWithPlayerRobot(){
        Iterator<Enemy> iterator = allEnemiesList.iterator();
        while (iterator.hasNext()) {
            try{
            EvilDroneMarkOne evilDroneMarkOne = (EvilDroneMarkOne)iterator.next();
            if(evilDroneMarkOne.detectCollision(PlayerRobot.getPlayerRobotPolygon())){
                PlayerRobot.removeHitPoints(1);
                iterator.remove();
            }
            }finally{
            }
        }
    }
    
    public void paintAllEnemies(GraphicsContext enemyGraphicsContext){
        enemyGraphicsContext.clearRect(0, 0, GameMainInfrastructure.WINDOW_WIDTH, GameMainInfrastructure.WINDOW_HEIGH);
        Iterator<Enemy> iterator = allEnemiesList.iterator();
        while (iterator.hasNext()) {
            Enemy enemy = iterator.next();
            enemy.paintEnemy(enemyGraphicsContext);
        }
    }
}
