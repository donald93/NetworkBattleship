package mcpartland.donald.Battleship;

import android.util.Pair;

import java.util.ArrayList;

/**
 * Created by Donald on 10/26/14.
 */
public class BattleshipPlayer
{
    int [][] playerBoard;
    int [][] opponentBoard;

    public BattleshipPlayer()
    {
        playerBoard = new int[10][10];
        opponentBoard = new int[10][10];
        generateShipLocations();
    }

    private void generateShipLocations()
    {
        int [] ships = {2, 3, 3, 4, 5};

        ArrayList<Pair<Integer, Integer>> boatPoints = new ArrayList<Pair<Integer, Integer>>();
        int i = 0;
        while(i < ships.length)
        {
            int startingX = (int) (Math.random() * 10);
            int startingY = (int) (Math.random() * 10);

            int direction = (int) (Math.random() * 2);

            if(direction == 0)
            {
                for(int j = 0; j < ships[i]; j++)
                {
                    if(validLocation(startingX + j, startingY) && playerBoard[startingX + j][startingY] == 0)
                    {
                        boatPoints.add(new Pair<Integer, Integer>(startingX + j, startingY));
                    }
                    else
                    {
                        boatPoints.clear();
                    }
                }
            }

            if(direction == 1)
            {
                for(int j = 0; j < ships[i]; j++)
                {
                    if(validLocation(startingX, startingY + j) && playerBoard[startingX][startingY + j] != 1)
                    {
                        boatPoints.add(new Pair<Integer, Integer>(startingX, startingY + j));
                    }
                    else
                    {
                        boatPoints.clear();
                    }
                }
            }

            if(boatPoints.size() == ships[i])
            {
                placePoints(boatPoints);
                boatPoints.clear();
                i++;
            }
        }
    }

    private void placePoints(ArrayList<Pair<Integer, Integer>> boatPoints)
    {
        for(int i = 0; i < boatPoints.size(); i++)
        {
            playerBoard[boatPoints.get(i).first][boatPoints.get(i).second] = 1;
        }
    }

    private boolean validLocation(int x, int y)
    {
        boolean xValid = false;
        boolean yValid = false;
        if(x >= 0 && x < 10)
        {
            xValid = true;
        }

        if(y >= 0 && y < 10)
        {
            yValid = true;
        }

        return xValid && yValid;
    }

    public String evaluateOpponentMove(int x, int y)
    {
        if(playerBoard[x][y] == 1)
        {
            playerBoard[x][y] = 3;
            return "hit";
        }
        else if (playerBoard[x][y] == 0)
        {
            playerBoard[x][y] = 2;
            return "miss";
        }
        else
        {
            return "invalid";
        }
    }

    public void evaluateMove(String missOrHit, int x, int y)
    {
        if(missOrHit == "hit")
        {
            opponentBoard[x][y] = 3;
        }
        else
        {
            opponentBoard[x][y] = 2;
        }

    }
}
