/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.robotliberation;

import Enemies.AllEnemiesContainer;
import com.mycompany.robotliberation.playerRobot.PlayerRobot;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author Dendra
 */
public class GameEnviroment {

    private GraphicsContext gameEnviromentGraphicsContext;
    

    public GameEnviroment(GraphicsContext gameEnviromentGraphicsContext, PlayerRobot playerRobot) {
        this.gameEnviromentGraphicsContext = gameEnviromentGraphicsContext;
    }

    public void paintEnviroment() {
        gameEnviromentGraphicsContext.setFill(Color.WHITE);
        gameEnviromentGraphicsContext.fillRect(0, 0, GameMainInfrastructure.WINDOW_WIDTH, GameMainInfrastructure.WINDOW_HEIGH);
    }

}
