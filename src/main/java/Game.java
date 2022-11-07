import Player.Player;
import Utils.Display;

public class Game {
    /**
     * Takes all the actions required to make single player's move.
     *
     * @param enemyPlayer - opposite player
     * @param playerShot - array with shot's coordinates
     * @return false if enemy lost the game, otherwise true
     */

    Display display = new Display();
    private final Player player1;
    private final Player player2;
    private Player currentPlayer;
    private Player opponentPlayer;

    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        currentPlayer = player1;
        opponentPlayer = player2;
    }

    public Player playGame() {
        while (true) {
            if (currentPlayer.round.playRound(currentPlayer, opponentPlayer)) {
                continueGame();
                continue;
            }
            gameOver();
            break;
        }
        return currentPlayer;
    }

    private void continueGame() {
        display.boardWithoutShips(opponentPlayer.getBoard().getOcean());
        waitForFewSeconds();
        swapPlayers();
    }

    private void swapPlayers() {
        if (currentPlayer == player1) {
            currentPlayer = player2;
            opponentPlayer = player1;
        } else {
            currentPlayer = player1;
            opponentPlayer = player2;
        }
    }

    private void gameOver() {
        display.boardWithoutShips(opponentPlayer.getBoard().getOcean());
        display.gameOver(currentPlayer.getName());
    }


    private void waitForFewSeconds() {
        try {
            Thread.sleep(1500);
        } catch (InterruptedException ie) {
            display.printMessage(ie.getMessage());
        }
    }
}