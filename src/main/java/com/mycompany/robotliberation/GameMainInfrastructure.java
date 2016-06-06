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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.paint.Color;

public class GameMainInfrastructure {

    public static double WINDOW_WIDTH = 640;
    public static double WINDOW_HEIGH = 860;

    public static int FRAMERATE = 60;
    public static double windowPositionX = 0.0;
    public static double windowPositionY = 0.0;

    private static Timeline gameLoop;
    private GameEnviroment gameEnviroment;
    private PlayerRobot playerRobot;

    private boolean mousePressed = false;
    private boolean keyAPressed = false;
    private boolean keySPressed = false;
    private boolean keyWPressed = false;
    private boolean keyDPressed = false;

    public GameMainInfrastructure(Stage stage, VBox gamePanel) throws Exception {
        StackPane gameCanvasPanel = new StackPane();
        //   WINDOW_WIDTH = stage.getWidth();
        //   WINDOW_HEIGH = stage.getHeight();

        final Canvas baseCanvas = new Canvas(WINDOW_WIDTH, WINDOW_HEIGH);
        GraphicsContext enviromentGraphicsContext = baseCanvas.getGraphicsContext2D();
        final Canvas enemiesCanvas = new Canvas(WINDOW_WIDTH, WINDOW_HEIGH);
        GraphicsContext enemyGraphicsContext = enemiesCanvas.getGraphicsContext2D();
        final Canvas robotCanvas = new Canvas(WINDOW_WIDTH, WINDOW_HEIGH);
        GraphicsContext robotGraphicsContext = robotCanvas.getGraphicsContext2D();

        gameCanvasPanel.getChildren().add(baseCanvas);
        gameCanvasPanel.getChildren().add(robotCanvas);
        gameCanvasPanel.getChildren().add(enemiesCanvas);
        gamePanel.getChildren().add(gameCanvasPanel);

        setUpMouseListeners(stage);
        setUpKeyboardListeners(stage);
        setUpResizeListeners(stage, baseCanvas, robotCanvas, enemiesCanvas);

        gameEnviroment = new GameEnviroment(enviromentGraphicsContext, enemyGraphicsContext);
        playerRobot = new PlayerRobot(robotGraphicsContext);

        buildAndSetGameLoop(enviromentGraphicsContext, robotGraphicsContext, stage);
    }

    private void setUpMouseListeners(Stage stage) {
        stage.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setUpMouseAsPressed(true);
            }
        });
        stage.addEventFilter(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setUpMouseAsPressed(false);
            }
        });
    }

    private void setUpKeyboardListeners(Stage stage) {
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
    }

    private void setUpResizeListeners(Stage stage, final Canvas baseCanvas, final Canvas robotCanvas, final Canvas enemyCanvas) {
        stage.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                WINDOW_WIDTH = (double) newValue;
                /*       baseCanvas.setWidth(WINDOW_WIDTH);
                robotCanvas.setWidth(WINDOW_WIDTH);
                enemyCanvas.setWidth(WINDOW_WIDTH);*/
            }
        });

        stage.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneHeight, Number newSceneHeight) {
                WINDOW_HEIGH = (double) newSceneHeight;
                /*   baseCanvas.setHeight(WINDOW_HEIGH);
                robotCanvas.setHeight(WINDOW_HEIGH);
                enemyCanvas.setWidth(WINDOW_HEIGH);*/
            }
        });
    }

    private void setUpMouseAsPressed(final boolean pressed) {
        mousePressed = pressed;
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
                shootPlayerRobot();
                playerRobot.paintPlayerRobot();

            }

        });

        setGameLoop(TimelineBuilder.create()
                .cycleCount(Animation.INDEFINITE)
                .keyFrames(oneFrame)
                .build());
    }

    private void shootPlayerRobot() {
        playerRobot.shootFromRobotTurret(mousePressed);
    }

    private void movePlayerRobot() {
        if (keyAPressed == true || keySPressed == true || keyWPressed == true || keyDPressed == true) {
            playerRobot.moveTracks();
        }

        if (keyAPressed == true) {
            playerRobot.moveRobotLeft();
        }
        if (keySPressed == true) {
            playerRobot.moveRobotBackward();
        }
        if (keyWPressed == true) {
            playerRobot.moveRobotForward();
        }
        if (keyDPressed == true) {
            playerRobot.moveRobotRight();
        }
    }

    protected static void setGameLoop(Timeline gameLoop) {
        GameMainInfrastructure.gameLoop = gameLoop;
    }

    public void beginGameLoop() {
        gameLoop.play();
    }

}
