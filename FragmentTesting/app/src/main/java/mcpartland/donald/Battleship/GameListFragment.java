package mcpartland.donald.Battleship;

import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by Donald on 10/29/14.
 */
public class GameListFragment extends Fragment
{
    private ListView gamesListView;
    public ArrayList<GameDetails> gamesList;
    private LinearLayout layout;
    public BattleshipListAdapter blAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        if(gamesList == null) {
            gamesList = new ArrayList<GameDetails>();
        }
        layout = new LinearLayout(getActivity());

        gamesListView = new ListView(getActivity());
        blAdapter = new BattleshipListAdapter(getActivity(), 0, gamesList);

        gamesListView.setAdapter(blAdapter);

        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setGravity(Gravity.CENTER);

        Button NewGameButton = new Button(getActivity());
        NewGameButton.setText("New Game");
        NewGameButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO make call to create new game

                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String s = new CreateNewGame().doInBackground("X");
                        Gson gson = new Gson();
                        Type gameListType = new TypeToken<ArrayList<NetworkGame>>(){}.getType();
                        //gamesl = gson.fromJson(gameList, gameListType);


                    }
                });
                thread.start();
            }
        });

        Button RefreshGamesButton = new Button(getActivity());
        RefreshGamesButton.setText("Refresh Games List");
        RefreshGamesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                blAdapter.refreshGamesList();
            }
        });

        gamesListView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                for(int i = 0; i < gamesListView.getChildCount(); i++)
                {
                    TextView tempView = (TextView) gamesListView.getChildAt(i);
                    tempView.setTypeface(null, Typeface.NORMAL);
                }
                return false;
            }
        });

        gamesListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {

                if(_onGameSelectedListener != null)
                {
                    TextView newView = (TextView) view;
                    ((TextView) view).setTypeface(null, Typeface.BOLD);
                    _onGameSelectedListener.onGameSelected(GameListFragment.this, position);
                }
            }
        });

        layout.addView(RefreshGamesButton, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Gravity.CENTER, 1));
        layout.addView(NewGameButton, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Gravity.CENTER, 1));

        layout.addView(gamesListView, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER, 8));

        return layout;
    }

    public interface OnGameSelectedListener
    {
        public void onGameSelected(GameListFragment gameListFragment, int identifier);
    }

    public OnGameSelectedListener get_onGameSelectedListener()
    {
        return _onGameSelectedListener;
    }

    public void set_onGameSelectedListener(OnGameSelectedListener _onGameSelectedListener)
    {
        this._onGameSelectedListener = _onGameSelectedListener;
    }

    OnGameSelectedListener _onGameSelectedListener = null;
}
