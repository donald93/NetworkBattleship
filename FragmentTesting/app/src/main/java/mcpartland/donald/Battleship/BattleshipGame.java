package mcpartland.donald.Battleship;

/**
 * Created by Donald on 10/26/14.
 */
public class BattleshipGame
{
    BattleshipPlayer player1;
    BattleshipPlayer player2;

    public BattleshipPlayer activePlayer;
    public int player1Moves;
    public int player2Moves;

    public boolean gameOver = false;

    public BattleshipGame()
    {
        player1 = new BattleshipPlayer();
        player2 = new BattleshipPlayer();
        activePlayer = player1;
        player1Moves = 0;
        player2Moves = 0;
    }

    public void setActivePlayer(String s)
    {
        if(s.equals("Player 1"))
        {
            activePlayer = player1;
        }
        else
        {
            activePlayer = player2;
        }
    }

    public String activePlayer()
    {
        if(gameOver)
        {
            return "Game Over";
        }

        if(activePlayer.equals(player1))
        {
            return "Player 1";
        }
        else
        {
            return "Player 2";
        }
    }

    public String performMove(int x, int y)
    {
        String result;

        if(activePlayer.equals(player1))
        {
            result = player2.evaluateOpponentMove(x, y);
            player1.evaluateMove(result, x, y);
            activePlayer = player2;
            player1Moves += 1;
            boolean endGame = true;
            for(int i = 0; i < 10; i++)
            {
                for(int j = 0; j < 10; j++)
                {
                    if(player2.playerBoard[i][j] == 1)
                    {
                        endGame = false;
                    }
                }
            }

            if(endGame)
            {
                gameOver = true;
                result = "Game Over";
            }
            return result;
        }
        else
        {
            result = player1.evaluateOpponentMove(x, y);
            player2.evaluateMove(result, x, y);
            activePlayer = player1;
            player2Moves += 1;

            boolean endGame = true;
            for(int i = 0; i < 10; i++)
            {
                for(int j = 0; j < 10; j++)
                {
                    if(player1.playerBoard[i][j] == 1)
                    {
                        endGame = false;
                    }
                }
            }

            if(endGame)
            {
                gameOver = true;
                result = "Game Over";
            }
            return result;
        }

    }
}
