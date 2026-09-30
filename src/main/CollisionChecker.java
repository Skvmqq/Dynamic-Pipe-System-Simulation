package main;

import Entity.Entity;
import Tile.Tile;

import java.awt.*;

public class CollisionChecker {
    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }
    // Method to check for collisions between an entity and the tiles
    public void checktile(Entity entity) {
        // Get the boundaries of the entity
        int entityLeftX = entity.solidarea.x;
        int entityRightX = entity.solidarea.x + entity.solidarea.width;
        int entityTopY = entity.solidarea.y;
        int entityBottomY = entity.solidarea.y + entity.solidarea.height;
// Calculate the tile positions the entity occupies
        int entityleftcol = entityLeftX / gp.tileSize;
        int entityrightcol = entityRightX / gp.tileSize;
        int entitytoprow = entityTopY / gp.tileSize;
        int entitybottomrow = entityBottomY / gp.tileSize;

        Tile tile1;
        Tile tile2;

        switch (entity.direction) {
            case UP:
                entitytoprow = (entityTopY - entity.speed) / gp.tileSize;
                tile1 = gp.tileManager.tile[entityleftcol][entitytoprow];
                tile2 = gp.tileManager.tile[entityrightcol][entitytoprow];
                if (tile1.collision || tile2.collision) {
                    entity.collisionOn = true;
                }
                break;
            case DOWN:
                entitybottomrow = (entityBottomY + entity.speed) / gp.tileSize;
                tile1 = gp.tileManager.tile[entityleftcol][entitybottomrow];
                tile2 = gp.tileManager.tile[entityrightcol][entitybottomrow];
                if (tile1.collision || tile2.collision) {
                    entity.collisionOn = true;
                }
                break;
            case LEFT:
                entityleftcol = (entityLeftX - entity.speed) / gp.tileSize;
                tile2 = gp.tileManager.tile[entityleftcol][entitytoprow];
                tile1 = gp.tileManager.tile[entityleftcol][entitybottomrow];
                if (tile1.collision || tile2.collision) {
                    entity.collisionOn = true;
                }
                break;
            case RIGHT:
                entityrightcol = (entityRightX + entity.speed) / gp.tileSize;
                tile1 = gp.tileManager.tile[entityrightcol][entitytoprow];
                tile2 = gp.tileManager.tile[entityrightcol][entitybottomrow];
                if (tile1.collision || tile2.collision) {
                    entity.collisionOn = true;
                }
                break;

        }
    }
    // Method to check for collisions between two entities
    public boolean checkEntityEntity(Entity entity1, Entity entity2) {
        boolean flag = false;
        Rectangle solidareaE1;
        Rectangle solidareaE2;
        if (entity1 != null) {
            solidareaE1 = new Rectangle(entity1.solidarea);
            solidareaE2 = new Rectangle(entity2.solidarea);

            switch (entity1.direction) {
                case UP:
                    solidareaE1.y -= entity1.speed;
                    break;
                case DOWN:
                    solidareaE1.y += entity1.speed;
                    break;
                case LEFT:
                    solidareaE1.x -= entity1.speed;
                    break;
                case RIGHT:
                    solidareaE1.x += entity1.speed;
                    break;
            }

            switch (entity2.direction) {
                case UP:
                    solidareaE2.y -= entity2.speed;
                    break;
                case DOWN:
                    solidareaE2.y += entity2.speed;
                    break;
                case LEFT:
                    solidareaE2.x -= entity2.speed;
                    break;
                case RIGHT:
                    solidareaE2.x += entity2.speed;
                    break;
            }

            if (entity1.solidarea.intersects(entity2.solidarea)) {
                if (entity1.collision && entity2.collision) {
                    switch (entity1.direction) {
                        case UP:
                            entity1.solidarea.y = entity2.solidarea.y + entity1.solidarea.height;
                            break;
                        case DOWN:
                            entity1.solidarea.y = entity2.solidarea.y - entity1.solidarea.height;
                            break;
                        case LEFT:
                            entity1.solidarea.x = entity2.solidarea.x + entity1.solidarea.width;
                            break;
                        case RIGHT:
                            entity1.solidarea.x = entity2.solidarea.x - entity1.solidarea.width;
                            break;
                    }
                    entity1.collisionOn = true;
                    flag = true;
                }
            }
        }
        return flag;
    }

}