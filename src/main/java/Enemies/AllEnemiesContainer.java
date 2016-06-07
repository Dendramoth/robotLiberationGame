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
    private ArrayList<EnemyWithCollision> allEnemiesList = new ArrayList<EnemyWithCollision>();
    private ArrayList<EnemyWithCollision> allDyingEneniesList = new ArrayList<EnemyWithCollision>();
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
        Iterator<EnemyWithCollision> iterator = allEnemiesList.iterator();
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
        Iterator<EnemyWithCollision> iterator = allEnemiesList.iterator();
        while (iterator.hasNext()) {
            EnemyWithCollision enemyWithCollision = iterator.next();
            if (enemyWithCollision.detectCollision(playerRobot.getPlayerRobotPolygon())) {
                playerRobot.removeHitPoints(1);
                enemyWithCollision.setAlive(false);
                allDyingEneniesList.add(enemyWithCollision);
                iterator.remove();
            }
        }
    }

    public void doAllDeathAnimations() {
        Iterator<EnemyWithCollision> iterator = allDyingEneniesList.iterator();
        while (iterator.hasNext()) {
            EnemyWithCollision enemyWithCollision = iterator.next();
            if (!enemyWithCollision.doOnCollision(enemyGraphicsContext)) {
                iterator.remove();
            }
        }
    }

    public void paintAllEnemies() {
        enemyGraphicsContext.clearRect(0, 0, GameMainInfrastructure.WINDOW_WIDTH, GameMainInfrastructure.WINDOW_HEIGH);
        Iterator<EnemyWithCollision> iterator = allEnemiesList.iterator();
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
