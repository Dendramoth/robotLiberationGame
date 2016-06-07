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
import java.util.Random;
import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author Dendra
 */
public class AllEnemiesContainer {

    private PlayerRobot playerRobot;
    private ArrayList<Enemy> allEnemiesList = new ArrayList<Enemy>();
    private ArrayList<Enemy> allDyingEneniesList = new ArrayList<Enemy>();
    private int counterToGenerateDrone = 0;
    private GraphicsContext enemyGraphicsContext;

    public AllEnemiesContainer(GraphicsContext enemyGraphicsContext, PlayerRobot playerRobot) {
        this.playerRobot = playerRobot;
        this.enemyGraphicsContext = enemyGraphicsContext;
    }

    public void generateEvilDroneMark1(double possX, double possY) {
        EvilDrone.EvilDroneMarkOne evilDroneMarkOne = new EvilDroneMarkOne(possX, possY, 5);
        allEnemiesList.add(evilDroneMarkOne);
    }

    public void moveAllEnemies() {
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

    public void detectCollisionsOfAllEnemiesWithPlayerRobot() {
        Iterator<Enemy> iterator = allEnemiesList.iterator();
        while (iterator.hasNext()) {
            try {
                EvilDroneMarkOne evilDroneMarkOne = (EvilDroneMarkOne) iterator.next();
                if (evilDroneMarkOne.detectCollision(playerRobot.getPlayerRobotPolygon())) {
                    playerRobot.removeHitPoints(1);
                    evilDroneMarkOne.setAlive(false);
                    allDyingEneniesList.add(evilDroneMarkOne);
                    iterator.remove();
                }
            } finally {
            }
        }
    }

    public void doAllDeathAnimations() {
        Iterator<Enemy> iterator = allDyingEneniesList.iterator();
        while (iterator.hasNext()) {
            try {
                EvilDroneMarkOne evilDroneMarkOne = (EvilDroneMarkOne) iterator.next();
                if (!evilDroneMarkOne.doOnCollision(enemyGraphicsContext)) {
                    iterator.remove();
                }
            } finally {
            }
        }
    }

    public void paintAllEnemies() {
        enemyGraphicsContext.clearRect(0, 0, GameMainInfrastructure.WINDOW_WIDTH, GameMainInfrastructure.WINDOW_HEIGH);
        Iterator<Enemy> iterator = allEnemiesList.iterator();
        while (iterator.hasNext()) {
            Enemy enemy = iterator.next();
            enemy.paintEnemy(enemyGraphicsContext);
        }
    }

    public void generateEvilDrones() {
        Random random = new Random();

        counterToGenerateDrone++;
        if (counterToGenerateDrone > 120) {
            counterToGenerateDrone = 0;
            generateEvilDroneMark1(random.nextDouble() * 600, 0);
        }
    }
}
