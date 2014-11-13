package mcpartland.donald.Battleship;

/**
 * Created by Donald on 11/12/2014.
 */
public class GameDetails {
    public GameDetails()
    {

    }
    public GameDetails(String id, String name, String player1, String player2, String winner)
    {
        this.id = id;
        this.name = name;
        this.player1 = player1;
        this.player2 = player2;
        this.winner = winner;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPlayer1() {
        return player1;
    }

    public String getPlayer2() {
        return player2;
    }

    public String getWinner() {
        return winner;
    }

    String id;
    String name;
    String player1;
    String player2;
    String winner;
}
