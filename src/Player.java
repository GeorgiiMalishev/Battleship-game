import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Player {
    private final Grid grid;
    protected Player opponent;
    protected final List<Point> previousShots;


    public Player() {
        this.grid = new Grid();
        this.previousShots = new ArrayList<>();
    }

    public Grid getGrid() {
        return grid;
    }

    public void makeMove(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите координаты: ");
        while (true){
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            if (isValidShot(x, y)){
                previousShots.add(new Point(x, y));
                opponent.grid.getShot(x, y);
                break;
            }
            System.out.println("Неверный ввод, попробуйте еще раз: ");
        }
    }

    protected boolean isValidShot(int x, int y) {
        return  x >= 0
                && x < 10
                && y >= 0
                && y < 10
                && !previousShots.contains(new Point(x, y));
    }

    public boolean isLoosed(){
        for(Ship ship : grid.getShips()){
            if(ship.isAlive()) return false;
        }
        return true;
    }


    public void setOpponent(Player opponent) {
        this.opponent = opponent;
    }
    

}
