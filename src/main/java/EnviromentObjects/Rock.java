/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EnviromentObjects;

import GameObjects.GameObjectWithColision;
import com.mycompany.robotliberation.LoadAllResources;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

/**
 *
 * @author Dendra
 */
public class Rock extends GameObjectWithColision {

    private GraphicsContext graphicsContext;
    private Image rockImage = LoadAllResources.getMapOfAllImages().get("evilDroneIdle1");

    public Rock(double possitionOnCanvasX, double possitionOnCanvasY, GraphicsContext graphicsContext) {
        super(possitionOnCanvasX, possitionOnCanvasY);
        this.graphicsContext = graphicsContext;
    }

    @Override
    public void paintGameObject() {
        graphicsContext.drawImage(rockImage, possitionOnCanvasX, possitionOnCanvasY);
    }

    @Override
    public boolean detectCollision(Shape shape) {
        Circle meteorPolygon = new Circle(possitionOnCanvasX + rockImage.getWidth() / 2, possitionOnCanvasY + rockImage.getHeight() / 2, (rockImage.getHeight() / 2));
        Shape intersect = Shape.intersect(shape, meteorPolygon);
        if (intersect.getLayoutBounds().getHeight() <= 0 || intersect.getLayoutBounds().getWidth() <= 0) {
            return false;
        }

        return true;
    }

    @Override
    public boolean doOnCollision(GraphicsContext enemyGraphicsContext) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void doOnBeingHit(String weaponType) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void changeEnemyPositionBasedOnRobotMovement(double changeX, double changeY){
        possitionOnCanvasX = possitionOnCanvasX + changeX;
        possitionOnCanvasY = possitionOnCanvasY + changeY;
    }

}
