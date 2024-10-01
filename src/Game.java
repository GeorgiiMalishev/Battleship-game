import java.util.Scanner;

public class Game {
    private GameMode mode;
    private Player player1;
    private Player player2;
    private final Scanner scanner = new Scanner(System.in);

    private void start() {
        System.out.println("Добро пожаловать в игру!");
        setMode();
        initializePlayers();
        play();
        endGame();
    }

    private void setMode() {
        System.out.println("Введите режим игры(PVP, PVC, CVC): ");
        while (true) {
            try {
                mode = GameMode.valueOf(scanner.nextLine().toUpperCase());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Неверный ввод, попробуйте еще раз: ");
            }
        }
    }

    private void initializePlayers() {
        switch (mode) {
            case PVP -> {player1 = new Player(); player2 = new Player();}
            case PVC -> {player1 = new Player(); player2 = new AIPlayer();}
            default  -> {player1 = new AIPlayer(); player2 = new AIPlayer();}
        }
        player1.setOpponent(player2);
        player2.setOpponent(player1);
    }

    private void play() {
        while (true) {
            playTurn(player1, player2);
            if (isGameOver()) break;
            playTurn(player2, player1);
            if (isGameOver()) break;
        }
    }

    private void playTurn(Player currentPlayer, Player opponent) {
        currentPlayer.getGrid().printPrivateGrid();
        opponent.getGrid().printPublicGrid();
        System.out.println("Ход игрока " + (currentPlayer == player1 ? "1" : "2"));
        currentPlayer.makeMove();
    }

    private boolean isGameOver() {
        return player1.isLoosed() || player2.isLoosed();
    }

    private void endGame() {
        System.out.println("Игра окончена!");
        if (player1.isLoosed()) {
            System.out.println("Игрок 2 победил!");
        } else {
            System.out.println("Игрок 1 победил!");
        }
    }

    public static void main(String[] args) {
        new Game().start();
    }
}