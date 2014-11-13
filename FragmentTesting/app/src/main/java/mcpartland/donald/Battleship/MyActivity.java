package mcpartland.donald.Battleship;

import android.app.Activity;
import android.app.DownloadManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;


public class MyActivity extends Activity {

    Gson gson;
    ArrayList<BattleshipGame> gameList;
    GameListFragment gameListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        LinearLayout rootLayout = new LinearLayout(this);
        
        rootLayout.setOrientation(LinearLayout.HORIZONTAL);
        setContentView(rootLayout);

        FrameLayout gameListLayout = new FrameLayout(this);
        gameListLayout.setId(10);

        rootLayout.addView(gameListLayout, new LinearLayout.LayoutParams(0,
                ViewGroup.LayoutParams.MATCH_PARENT, 30));

        FrameLayout gameDetailLayout = new FrameLayout(this);
        gameDetailLayout.setId(11);

        rootLayout.addView(gameDetailLayout, new LinearLayout.LayoutParams(0,
                ViewGroup.LayoutParams.MATCH_PARENT, 70));

        FragmentTransaction addTransaction = getFragmentManager().beginTransaction();
        gameListFragment = new GameListFragment();

        final GameDetailFragment gameDetailFragment = new GameDetailFragment();

        gameDetailFragment.set_OnUsedListener(new GameDetailFragment.OnUsedListener()
        {
            @Override
            public void OnUsedListener() {
                gameListFragment.blAdapter.notifyDataSetChanged();
            }
        });

        gameListFragment.set_onGameSelectedListener(new GameListFragment.OnGameSelectedListener()
        {
            @Override
            public void onGameSelected(GameListFragment gameListFragment, int identifier)
            {
                final GameDetails game = gameListFragment.gamesList.get(identifier);
                if(game.getPlayer2() == "")
                {
                    //TODO join a game
                }

            }
        });

        addTransaction.add(10, gameListFragment);
        addTransaction.add(11, gameDetailFragment);
        addTransaction.commit();


    }

    @Override
    protected void onPause()
    {
//        gson = new Gson();
//        gameList = gameListFragment.gamesList;
//        String listOfGames = gson.toJson(gameList);
//
//        ArrayList<String> activePlayers = new ArrayList<String>();
//        for(int i = 0; i < gameList.size(); i++)
//        {
//            activePlayers.add(gameList.get(i).activePlayer());
//        }
//
//        String activePlayerList = gson.toJson(activePlayers);
//
//        try
//        {
//            FileOutputStream os = openFileOutput("gamelist.dat", MODE_PRIVATE);
//            ObjectOutputStream output = new ObjectOutputStream(os);
//            output.writeObject(listOfGames);
//            output.writeObject(activePlayerList);
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
        super.onPause();
    }

    @Override
    protected void onResume() {
//        String listOfGames = null;
//        String listOfPlayers = null;
//
//        try
//        {
//            FileInputStream os = openFileInput("gamelist.dat");
//            ObjectInputStream input = new ObjectInputStream(os);
//            listOfGames = (String)input.readObject();
//            listOfPlayers = (String)input.readObject();
//        }
//        catch(Exception e)
//        {
//            e.printStackTrace();
//        }
//
//        gson = new Gson();
//        Type gameListType = new TypeToken<ArrayList<BattleshipGame>>(){}.getType();
//        Type activePlayersType = new TypeToken<ArrayList<String>>(){}.getType();
//        ArrayList<BattleshipGame> newGames = gson.fromJson(listOfGames, gameListType);
//        ArrayList<String> activePlayers = gson.fromJson(listOfPlayers, activePlayersType);
//
//        if(newGames != null && activePlayers != null)
//        {
//            gameListFragment.gamesList = newGames;
//            for(int i = 0; i < newGames.size(); i++)
//            {
//                newGames.get(i).setActivePlayer(activePlayers.get(i));
//                gameListFragment.blAdapter.add(newGames.get(i));
//            }
//            gameListFragment.blAdapter.notifyDataSetChanged();
//        }
        super.onResume();
    }
}
