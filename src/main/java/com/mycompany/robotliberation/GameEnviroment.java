/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.robotliberation;

import Enemies.AllEnemiesContainer;
import playerRobot.PlayerRobot;
import java.util.Random;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 *
 * @author Dendra
 */
public class GameEnviroment {

    private double possitionX = 0;
    private double possitionY = 0;

    private GraphicsContext gameEnviromentGraphicsContext;
    private Image[][] masterImage = new Image[10][10];

    public GameEnviroment(GraphicsContext gameEnviromentGraphicsContext, PlayerRobot playerRobot) {
        this.gameEnviromentGraphicsContext = gameEnviromentGraphicsContext;
        generateBackground();
    }

    public void moveEnviromentBasedOnRobotMovement(double robotMovementX, double robotMovementY) {
        possitionX = possitionX + robotMovementX;
        possitionY = possitionY + robotMovementY;
    }

    private void generateBackground() {
        Image image;
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                switch (random.nextInt(15)) {
                    case 0:
                        image = LoadAllResources.getMapOfAllImages().get("terrainCrater");
                        break;
                    case 1:
                        image = LoadAllResources.getMapOfAllImages().get("terrain2");
                        break;
                    case 2:
                        image = LoadAllResources.getMapOfAllImages().get("terrain3");
                        break;
                    case 3:
                        image = LoadAllResources.getMapOfAllImages().get("terrain4");
                        break;
                    case 4:
                        image = LoadAllResources.getMapOfAllImages().get("terrain5");
                        break;
                    case 5:
                        image = LoadAllResources.getMapOfAllImages().get("terrain6");
                        break;
                    case 6:
                        image = LoadAllResources.getMapOfAllImages().get("terrain7");
                        break;
                    case 7:
                        image = LoadAllResources.getMapOfAllImages().get("terrain8");
                        break;
                    case 8:
                        image = LoadAllResources.getMapOfAllImages().get("terrain9");
                        break;
                    case 9:
                        image = LoadAllResources.getMapOfAllImages().get("terrain10");
                        break;
                    case 10:
                        image = LoadAllResources.getMapOfAllImages().get("terrain6");
                        break;
                    case 11:
                        image = LoadAllResources.getMapOfAllImages().get("terrain7");
                        break;
                    case 12:
                        image = LoadAllResources.getMapOfAllImages().get("terrain8");
                        break;
                    case 13:
                        image = LoadAllResources.getMapOfAllImages().get("terrain9");
                        break;
                    case 14:
                        image = LoadAllResources.getMapOfAllImages().get("terrain10");
                        break;
                    default:
                        image = LoadAllResources.getMapOfAllImages().get("terrain10");
                        break;
                }
                masterImage[i][j] = image;
            }
        }
    }

    public void paintEnviroment() {
        Random random = new Random();
        double angle = 0;

        gameEnviromentGraphicsContext.clearRect(0, 0, GameMainInfrastructure.WINDOW_WIDTH, GameMainInfrastructure.WINDOW_HEIGH);

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                switch (random.nextInt(4)) {
                    case 0:
                        angle = 0;
                        break;
                    case 1:
                        angle = 90;
                        break;
                    case 2:
                        angle = 180;
                        break;
                    case 3:
                        angle = 270;
                        break;
                }
                /*           gameEnviromentGraphicsContext.save();
                gameEnviromentGraphicsContext.translate(possitionX + 256 * (i - 2), possitionY + 256 * (j - 2));
                gameEnviromentGraphicsContext.rotate(angle);
                gameEnviromentGraphicsContext.drawImage(masterImage[i][j], -masterImage[i][j].getWidth() / 2, -masterImage[i][j].getHeight() / 2);
                gameEnviromentGraphicsContext.restore();*/
                gameEnviromentGraphicsContext.drawImage(masterImage[i][j], possitionX + 256 * (i - 2), possitionY + 256 * (j - 2));
            }
        }

    }

}
