/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameObjects;

/**
 *
 * @author Dendra
 */
public abstract class GameObject implements Comparable<GameObject> {
    protected double possitionOnCanvasX = 0;
    protected double possitionOnCanvasY = 0;
    protected double objectForComparisonPosX = 0;
    protected double objectForComparisonPosY = 0;

    public GameObject(double possitionOnCanvasX, double possitionOnCanvasY) {
        this.possitionOnCanvasX = possitionOnCanvasX;
        this.possitionOnCanvasY = possitionOnCanvasY;
    }
    
    public abstract void paintGameObject();

    @Override
    public int compareTo(GameObject o) {
        double myDistance = Math.sqrt((this.possitionOnCanvasX - this.objectForComparisonPosX) * (this.possitionOnCanvasX - this.objectForComparisonPosX) + (this.possitionOnCanvasY - this.objectForComparisonPosY) * (this.possitionOnCanvasY - this.objectForComparisonPosY));
        double otherDistance = Math.sqrt((o.getPossitionOnCanvasX() - o.getObjectForComparisonPosX()) * (o.getPossitionOnCanvasX() - o.getObjectForComparisonPosX()) + (o.getPossitionOnCanvasY() - o.getObjectForComparisonPosY()) * (o.getPossitionOnCanvasY() - o.getObjectForComparisonPosY()));
        if (myDistance < otherDistance) {
            return -1;
        } else {
            return 1;
        }
    }

    public double getPossitionOnCanvasX() {
        return possitionOnCanvasX;
    }

    public double getPossitionOnCanvasY() {
        return possitionOnCanvasY;
    }

    public double getObjectForComparisonPosX() {
        return objectForComparisonPosX;
    }

    public double getObjectForComparisonPosY() {
        return objectForComparisonPosY;
    }

    public void setObjectForComparisonPosX(double objectForComparisonPosX) {
        this.objectForComparisonPosX = objectForComparisonPosX;
    }

    public void setObjectForComparisonPosY(double objectForComparisonPosY) {
        this.objectForComparisonPosY = objectForComparisonPosY;
    }

}
