import java.awt.*;
import java.util.List;
import java.util.Random;

public class Ship {
    private final Grid grid;
    private int x;
    private int y;
    private final int size;
    private int health;
    private boolean isVertical;
    private final Point[] coordinates;

    public Ship(Grid grid, int size) {
        this.grid = grid;
        this.size = size;
        health = size;
        placeShip();
        coordinates = fillCoordinates();
    }

    public Point[] getCoordinates() {
        return coordinates;
    }

    private void placeShip() {
        Random random = new Random();
        do{
            isVertical = random.nextBoolean();
            x = isVertical ? random.nextInt(0 ,Grid.SIZE) : random.nextInt(0, Grid.SIZE -size+1);
            y = !isVertical ? random.nextInt(0 ,Grid.SIZE) : random.nextInt(0, Grid.SIZE-size+1);

        }while(!isCorrectPlacement());
    }

    private Point[] fillCoordinates() {
        Point[] coordinates = new Point[size];
        for(int i = 0; i < size; i++) {
            coordinates[i] = isVertical
                    ? new Point(x, y+i)
                    : new Point(x+i, y);
        }
        return coordinates;
    }


    private boolean isCorrectPlacement() {
        List<Ship> existingShips = grid.getShips();
        Point[] newShipCoordinates = fillCoordinates();

        for (Ship existingShip : existingShips) {
            if (shipsOverlapOrAdjacent(existingShip.getCoordinates(), newShipCoordinates)) {
                return false;
            }
        }
        return true;
    }

    private boolean shipsOverlapOrAdjacent(Point[] ship1, Point[] ship2) {
        for (Point p1 : ship1) {
            for (Point p2 : ship2) {
                if (pointsOverlapOrAdjacent(p1, p2)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean pointsOverlapOrAdjacent(Point p1, Point p2) {
        return Math.abs(p1.x - p2.x) <= 1 && Math.abs(p1.y - p2.y) <= 1;
    }

    public boolean isHit(int gridX, int gridY){
        for(Point coordinate : coordinates) {
            if (coordinate.x == gridX && coordinate.y == gridY) {
                return true;
            }
        }
        return false;
    }

    public boolean isAlive(){
        return health > 0;
    }

    public void getDamage(){
        health -= 1;
    }

}
