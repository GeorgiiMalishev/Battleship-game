import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Grid {
    private static final char WATER = '.';
    private static final char SHIP = 'S';
    private static final char HIT = 'H';
    private static final char KILLED = 'K';
    private static final char MISS = '*';
    public static final int SIZE = 10;
    private final char[][] grid;
    private final List<Ship> ships;


    public Grid() {
        grid = new char[SIZE][SIZE];
        ships = new ArrayList<>();

        ships.add(new Ship(this, 4));
        ships.add(new Ship(this, 3));
        ships.add(new Ship(this, 3));
        ships.add(new Ship(this, 2));
        ships.add(new Ship(this, 2));
        ships.add(new Ship(this, 2));
        ships.add(new Ship(this, 1));
        ships.add(new Ship(this, 1));
        ships.add(new Ship(this, 1));
        ships.add(new Ship(this, 1));

        this.fillGrid();
    }

    public java.util.List<Ship> getShips() {
        return ships;
    }

    private void fillGrid(){
        for(int i = 0; i < SIZE; i++){
            for(int j = 0; j < SIZE; j++){
                grid[j][i] = WATER;
            }
        }

        for(Ship ship : ships){
            Point[] shipCoords = ship.getCoordinates();
            for(Point coord : shipCoords){
                grid[coord.x][coord.y] = SHIP;
            }
        }
    }


    public void getShot(int x, int y) {
        if (grid[x][y] == WATER) {
            System.out.println("Мимо!\n");
            grid[x][y] = MISS;
        } else if (grid[x][y] == SHIP) {
            System.out.print("Попал\n");
            grid[x][y] = HIT;
            Ship ship = getShipOnCoords(x, y);
            ship.getDamage();
            if (!ship.isAlive()){
                System.out.println("Убил!");
                killShip(ship);
            }
        }
    }

    public Ship getShipOnCoords(int x, int y) {
        for (Ship ship : ships) {
            if (ship.isHit(x, y)) {
                return ship;
            }
        }
        return null;
    }

    public void printPrivateGrid() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(grid[j][i]);
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void printPublicGrid() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(grid[j][i] == SHIP ? WATER : grid[j][i]);
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private void killShip(Ship ship) {
        for (Point coord : ship.getCoordinates()) {
            grid[coord.x][coord.y] = KILLED;
        }
    }
}
