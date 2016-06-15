/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.robotliberation;

import GameObjects.GameObject;
import Enemies.AllEnemiesContainer;
import com.mycompany.robotliberation.playerRobot.PlayerRobot;
import java.util.ArrayList;

/**
 *
 * @author Dendra
 */
public class CalculateCollisions {
    
    private AllProjectilesContainer allProjectilesContainer;
    private AllEnemiesContainer allEnemiesContainer;
    private PlayerRobot playerRobot;
    private ArrayList<GameObject> allGameObjects = new ArrayList<GameObject>();

    public CalculateCollisions(AllProjectilesContainer allProjectilesContainer, AllEnemiesContainer allEnemiesContainer, PlayerRobot playerRobot) {
        this.allEnemiesContainer = allEnemiesContainer;
        this.allProjectilesContainer = allProjectilesContainer;
        this.playerRobot = playerRobot;
    }
    
    
    
}
