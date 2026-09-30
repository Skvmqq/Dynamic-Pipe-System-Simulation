package Entity;

import main.Directions;

import java.awt.*;

public class Entity {
    public final float size = 0.8f;
    public Rectangle solidarea;
    public int speed;
    public Directions direction = Directions.UP;
    public boolean collision = false;
    public boolean collisionOn = false;

    Entity(int x, int y, int width, int height) {
        solidarea = new Rectangle(x, y, width, height);
        speed = 4;
    }
    // Method to move the entity based on the current direction

    public void Move() {
        if(collisionOn)
        {
            collisionOn = false;
            return;
        }
        // Update the entity's position based on its direction
        switch (direction) {
            case UP:
                solidarea.y -= speed;
                break;
            case DOWN:
                solidarea.y += speed;
                break;
            case LEFT:
                solidarea.x -= speed;
                break;
            case RIGHT:
                solidarea.x += speed;
                break;
            default:
                break;
        }
    }
}
