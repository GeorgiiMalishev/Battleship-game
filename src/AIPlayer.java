import java.awt.*;
import java.util.Random;

public class AIPlayer extends Player {
    @Override
    public void makeMove(){
        Random rand = new Random();
        int x;
        int y;
        do{
            x = rand.nextInt(0, 10);
            y = rand.nextInt(0, 10);
        }while(!isValidShot(x, y));
        previousShots.add(new Point(x, y));
        opponent.getGrid().getShot(x, y);
    }
}
