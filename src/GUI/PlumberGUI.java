package GUI;

import Entity.Plumber;
import Tile.Tile;
import main.KeyHandler;
import main.GamePanel;
import main.Directions;

import java.util.Objects;


public class PlumberGUI extends EntityGUI {
    KeyHandler keyH;
    // Constructor to initialize the game panel, key handler, and plumber entity
    public PlumberGUI(GamePanel gp, KeyHandler keyH) {
        super(gp, "/main/res/plumber.png");
        this.keyH = keyH;
        int x = 7 * gp.tileSize;
        int y = 8 * gp.tileSize;
        int width = (int) (size * gp.tileSize);
        int height = (int) (size * gp.tileSize);
        entity = new Plumber(x, y, width, height);
    }
    // Update method to handle the plumber's actions and movements
    @Override
    public void update() {
        Tile tile = getTileFromEntity(0, 0);

        entity.collision = Objects.equals(tile.name, "pipe");

        if (keyH.upPressed) {
            entity.direction = Directions.UP;
        } else if (keyH.downPressed) {
            entity.direction = Directions.DOWN;
        } else if (keyH.leftPressed) {
            entity.direction = Directions.LEFT;
        } else if (keyH.rightPressed) {
            entity.direction = Directions.RIGHT;
        }
        // Fix the tile if 'P' key is pressed
        if (keyH.pPressed) {
            tile.fixTile();
        }
// Build a pipe element if 'M' or 'N' key is pressed and the tile is a cistern
        if ((keyH.mPressed || keyH.nPressed) && Objects.equals(tile.name, "cistern")) {
            Tile toGenerate = null;
            switch (entity.direction) {
                case UP:
                    toGenerate = getTileFromEntity(0, -1);
                    break;
                case DOWN:
                    toGenerate = getTileFromEntity(0, 1);
                    break;
                case LEFT:
                    toGenerate = getTileFromEntity(-1, 0);
                    break;
                case RIGHT:
                    toGenerate = getTileFromEntity(1, 0);
            }
            if (keyH.mPressed) {
                toGenerate.buildPipeElement("pump");
            } else if (keyH.nPressed) {
                toGenerate.buildPipeElement("pipe");
            }
        }
        // Pick up a pipe element if 'Shift' key is pressed
        if (keyH.shiftPressed) {
            if(((Plumber)entity).picked == null) {
                Tile toPick = null;
                switch (entity.direction) {
                    case UP:
                        toPick = getTileFromEntity(0, -1);
                        break;
                    case DOWN:
                        toPick = getTileFromEntity(0, 1);
                        break;
                    case LEFT:
                        toPick = getTileFromEntity(-1, 0);
                        break;
                    case RIGHT:
                        toPick = getTileFromEntity(1, 0);
                }
                ((Plumber) entity).pickElement(toPick.pickPipeElement());
            }
        }
        // Place a pipe element if 'Ctrl' key is pressed
        if (keyH.ctrlPressed) {
            Tile toPlace = null;
            switch (entity.direction) {
                case UP:
                    toPlace = getTileFromEntity(0, -1);
                    break;
                case DOWN:
                    toPlace = getTileFromEntity(0, 1);
                    break;
                case LEFT:
                    toPlace = getTileFromEntity(-1, 0);
                    break;
                case RIGHT:
                    toPlace = getTileFromEntity(1, 0);
            }
            toPlace.placePipeElement(((Plumber)(entity)).picked);
            ((Plumber)entity).dropElement();
        }
// Move the entity and check for collisions if any movement key is pressed
        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
            entity.Move();
            gp.collisionChecker.checkEntityEntity(entity, gp.saboteur.entity);
            gp.collisionChecker.checktile(entity);
        }
    }
}