/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.robotliberation;

import com.mycompany.robotliberation.playerRobot.PlayerRobot;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TimelineBuilder;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.event.Event;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;


public class GameMainInfrastructure {

    public static int WINDOW_WIDTH = 640;
    public static int WINDOW_HEIGH = 860;
    public static int FRAMERATE = 60;
    public static double windowPositionX = 0.0;
    public static double windowPositionY = 0.0;

    private static Timeline gameLoop;
    private GameEnviroment gameEnviroment;
    private PlayerRobot playerRobot;

    private boolean keyAPressed = false;
    private boolean keySPressed = false;
    private boolean keyWPressed = false;
    private boolean keyDPressed = false;

    public GameMainInfrastructure(Stage stage, VBox gamePanel) throws Exception {
        StackPane gameCanvasPanel = new StackPane();

        Canvas baseCanvas = new Canvas(WINDOW_WIDTH, WINDOW_HEIGH);
        GraphicsContext enviromentGraphicsContext = baseCanvas.getGraphicsContext2D();

        Canvas robotCanvas = new Canvas(WINDOW_WIDTH, WINDOW_HEIGH);
        GraphicsContext robotGraphicsContext = robotCanvas.getGraphicsContext2D();

        gameCanvasPanel.getChildren().add(baseCanvas);
        gameCanvasPanel.getChildren().add(robotCanvas);
        gamePanel.getChildren().add(gameCanvasPanel);

        /**
         * mouse detection
         */
        stage.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
            }
        });

        stage.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                setUpKeyAsPressed(true, event);
            }
        });

        stage.addEventFilter(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                setUpKeyAsPressed(false, event);
            }
        });

        gameEnviroment = new GameEnviroment(enviromentGraphicsContext);
        playerRobot = new PlayerRobot(robotGraphicsContext);

        buildAndSetGameLoop(enviromentGraphicsContext, robotGraphicsContext, stage);
    }

    private void setUpKeyAsPressed(final boolean pressed, final KeyEvent event) {
        switch (event.getText().toUpperCase()) {
            case "A":
                keyAPressed = pressed;
                break;
            case "S":
                keySPressed = pressed;
                break;
            case "W":
                keyWPressed = pressed;
                break;
            case "D":
                keyDPressed = pressed;
        }
    }

    private void buildAndSetGameLoop(final GraphicsContext baseGraphicsContext, final GraphicsContext robotGraphicsContext, final Stage stage) {
        final Duration oneFrameDuration = Duration.millis(1000 / FRAMERATE);
        final KeyFrame oneFrame = new KeyFrame(oneFrameDuration,
                new EventHandler() {

            /**
             * Everything inside this handle is what will be repeated in every
             * game loop. Move objects here, detect colisions etc.
             */
            @Override
            public void handle(Event event) {
                windowPositionX = stage.getX();
                windowPositionY = stage.getY();
                        
                gameEnviroment.paintEnviroment();
                movePlayerRobot();
                playerRobot.paintPlayerRobot();
            }

        });

        setGameLoop(TimelineBuilder.create()
                .cycleCount(Animation.INDEFINITE)
                .keyFrames(oneFrame)
                .build());
    }

    private void movePlayerRobot() {
        if (keyAPressed == true) {
            playerRobot.movePlayerRobot(PlayerRobot.LEFT);
        }
        if (keySPressed == true) {
            playerRobot.movePlayerRobot(PlayerRobot.BACKWARD);
        }
        if (keyWPressed == true) {
            playerRobot.movePlayerRobot(PlayerRobot.FORWARD);
        }
        if (keyDPressed == true) {
            playerRobot.movePlayerRobot(PlayerRobot.RIGHT);
        }
    }

    protected static void setGameLoop(Timeline gameLoop) {
        GameMainInfrastructure.gameLoop = gameLoop;
    }

    public void beginGameLoop() {
        gameLoop.play();
    }

}
