/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.robotliberation;

import Enemies.AllEnemiesContainer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author Dendra
 */
public class GameEnviroment {

    private GraphicsContext gameEnviromentGraphicsContext;
    private GraphicsContext monsterGraphicsContext;
    private AllEnemiesContainer allEnemiesContainer = new AllEnemiesContainer();
    private int counterToGenerateDrone = 0;

    public GameEnviroment(GraphicsContext gameEnviromentGraphicsContext, GraphicsContext monsterGraphicsContext) {
        this.gameEnviromentGraphicsContext = gameEnviromentGraphicsContext;
        this.monsterGraphicsContext = monsterGraphicsContext;
    }

    public void paintEnviroment() {
        gameEnviromentGraphicsContext.setFill(Color.GREY);
        gameEnviromentGraphicsContext.fillRect(0, 0, GameMainInfrastructure.WINDOW_WIDTH, GameMainInfrastructure.WINDOW_HEIGH);
    }

    public void moveAllEnemies() {
        allEnemiesContainer.moveAllEnemies();
        detectCollisionsWithPlayerRobot();
    }
    
    private void detectCollisionsWithPlayerRobot() {
        allEnemiesContainer.detectCollisionsOfAllEnemiesWithPlayerRobot();
    }

    public void paintAllEnemies() {
        allEnemiesContainer.paintAllEnemies(monsterGraphicsContext);
    }

    public void generateEvilDrones() {
        counterToGenerateDrone++;
        if (counterToGenerateDrone > 180) {
            counterToGenerateDrone = 0;
            allEnemiesContainer.generateEvilDroneMark1(0, 0);
        }
    }

}
