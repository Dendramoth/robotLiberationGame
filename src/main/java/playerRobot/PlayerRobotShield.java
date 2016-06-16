/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package playerRobot;

/**
 *
 * @author Dendra
 */
public class PlayerRobotShield {

    private double shieldHitPoints = 0;
    private PlayerRobot playerRobot;
    private boolean active = false;

    public PlayerRobotShield(int shieldHitPoints, PlayerRobot playerRobot) {
        this.shieldHitPoints = shieldHitPoints;
        this.playerRobot = playerRobot;
    }

    public void removeHitPointsFromShield(double removeValue) {
        shieldHitPoints = shieldHitPoints - removeValue;
        if (shieldHitPoints <= 0) {
            active = false;
            shieldHitPoints = 0;
        }
    }

    public void addHitPointsToShield(double addValue) {
        shieldHitPoints = shieldHitPoints + addValue;
        if (shieldHitPoints >= 100) {
            shieldHitPoints = 100;
        }
    }

    public double getShieldHitPoints() {
        return shieldHitPoints;
    }

    public void setShieldHitPoints(int shieldHitPoints) {
        this.shieldHitPoints = shieldHitPoints;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}
