package mcpartland.donald.Battleship;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by Donald on 10/29/14.
 */
public class BattleshipListAdapter extends ArrayAdapter
{
    public List<BattleshipGame> _games;
    Context _context;
    String gameList;

    ArrayList<NetworkGame> gamesl;
    ArrayList<GameDetails> gameDetailList;

    public BattleshipListAdapter(Context context, int resource, List objects)
    {
        super(context, resource, objects);
        _games = new ArrayList<BattleshipGame>(objects);
        gameDetailList = new ArrayList<GameDetails>();
        _context = context;
    }

    public void refreshGamesList()
    {
        this.clear();
        gameDetailList.clear();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                gameList = new RequestGames().doInBackground("X");
                Gson gson = new Gson();
                Type gameListType = new TypeToken<ArrayList<NetworkGame>>(){}.getType();
               gamesl = gson.fromJson(gameList, gameListType);


            }
        });
        thread.start();
        if(gamesl != null) {
            for (int i = 0; i < gamesl.size(); i++) {
                //this.add(gamesl.get(i));
                final NetworkGame game = gamesl.get(i);

                Thread thread2 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String s = new RequestGameByID().doInBackground(game.getId());
                        Gson gson = new Gson();
                        Type gameDetailsType = new TypeToken<GameDetails>(){}.getType();
                        GameDetails gameDetails = gson.fromJson(s, gameDetailsType);
                        gameDetailList.add(gameDetails);
                    }
                });
                thread2.start();
            }
        }

        int j = 0;
        while(gameDetailList.size() == 0 && j != 3)
        {
            try
            {
                synchronized (this)
                {
                    this.wait(1000);

                }
                j += 1;
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }

        if(gameDetailList != null)
        {
            for(int i = 0; i < gameDetailList.size(); i++)
            {
                this.add(gameDetailList.get(i));
            }

        }
        this.notifyDataSetChanged();
    }

    public void addGameToList(ArrayList<BattleshipGame> games)
    {
//        this.clear();
//        _games.addAll(games);
//
//        for(int i = 0; i < games.size(); i++)
//        {
//            this.add(games.get(i));
//        }
//
//        this.notifyDataSetChanged();
        refreshGamesList();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        GameDetails game = (GameDetails) getItem(i);

        final TextView gameText = new TextView(_context);
        gameText.append("Game " + game.getName() + "\n");
        gameText.append("Player1: " + game.getPlayer1() + "\n");
        gameText.append("Player2: " + game.getPlayer2());

//        final TextView gameText = new TextView(_context);
//        gameText.append("Game " + (i + 1) + "\n");
//        if(game.gameOver)
//        {
//            gameText.append("Game Over\n");
//
//        }
//        else
//        {
//            gameText.append("In Progress: ");
//            gameText.append(game.activePlayer() + "'s Turn\n");
//        }
//        gameText.append("Player 1 Moves: " + game.player1Moves);
//        gameText.append(" Player 2 Moves: " + game.player2Moves);

        return gameText;

    }

}