package mcpartland.donald.Battleship;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

/**
 * Created by Donald on 10/29/14.
 */
public class GameDetailFragment extends Fragment
{
    public int[][] _playerBoard;
    public int[][] _opponentBoard;
    public BattleshipGame _game;

    private GameGridViewGroup ggViewGroup;

    public void setBoards()
    {
        _playerBoard = _game.activePlayer.playerBoard;
        _opponentBoard = _game.activePlayer.opponentBoard;

        for(int i = 0; i < ggViewGroup.getChildCount(); i++)
        {
            GameCellView gameCell = (GameCellView) ggViewGroup.getChildAt(i);


            gameCell.game = _game;
            if(gameCell.player.equals("ActivePlayer") && gameCell.cellX != -1)
            {
                gameCell.backgroundColor = _playerBoard[gameCell.cellX - 1][gameCell.cellY - 1];
                gameCell.invalidate();
            }
            else if(gameCell.player.equals("OpponentPlayer") && gameCell.cellX != -1)
            {
                gameCell.set_onMovePerformedListener(new GameCellView.OnMovePerformedListener() {
                    @Override
                    public void onMovePerformed(final BattleshipGame battleshipGame, String result, int x, int y)
                    {
                        AlertDialog ad = new AlertDialog.Builder(getActivity()).create();
                        ad.setMessage("Result of move: " + result + " at (" + x + ", " + y + ")");

                        ad.setButton("Next Turn", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                _game = battleshipGame;
                                setBoards();
                                _OnUsedListener.OnUsedListener();
                                dialog.dismiss();

                            }
                        });

                        ad.show();

                    }
                });

                gameCell.backgroundColor = _opponentBoard[gameCell.cellX - 1][gameCell.cellY - 1];
                gameCell.invalidate();
            }

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        ggViewGroup = new GameGridViewGroup(getActivity());
        ggViewGroup.setBackgroundColor(Color.BLACK);

        int x = 1;
        int y = 1;
        for(int i = 0; i < 242; i++)
        {
            GameCellView gameCell = new GameCellView(getActivity());

            if(i < 121)
            {
                gameCell.player = "OpponentPlayer";
            }
            else
            {
                gameCell.player = "ActivePlayer";
            }

            if(i == 0 || i == 121)
            {
                gameCell.cellText = "X";
            }
            else if(i == 1 || i == 122)
            {
                gameCell.cellText = "A";
            }
            else if(i == 2 || i == 123)
            {
                gameCell.cellText = "B";
            }
            else if(i == 3 || i == 124)
            {
                gameCell.cellText = "C";
            }
            else if(i == 4 || i == 125)
            {
                gameCell.cellText = "D";
            }
            else if(i == 5 || i == 126)
            {
                gameCell.cellText = "E";
            }
            else if(i == 6 || i == 127)
            {
                gameCell.cellText = "F";
            }
            else if(i == 7 || i == 128)
            {
                gameCell.cellText = "G";
            }
            else if(i == 8 || i == 129)
            {
                gameCell.cellText = "H";
            }
            else if(i == 9 || i == 130)
            {
                gameCell.cellText = "I";
            }
            else if(i == 10 || i == 131)
            {
                gameCell.cellText = "J";
            }

            else if(i == 11 || i == 132)
            {
                gameCell.cellText = "1";
            }
            else if(i == 22 || i == 143)
            {
                gameCell.cellText = "2";
            }
            else if(i == 33 || i == 154)
            {
                gameCell.cellText = "3";
            }
            else if(i == 44 || i == 165)
            {
                gameCell.cellText = "4";
            }
            else if(i == 55 || i == 176)
            {
                gameCell.cellText = "5";
            }
            else if(i == 66 || i == 187)
            {
                gameCell.cellText = "6";
            }
            else if(i == 77 || i == 198)
            {
                gameCell.cellText = "7";
            }
            else if(i == 88 || i == 209)
            {
                gameCell.cellText = "8";
            }
            else if(i == 99 || i == 220)
            {
                gameCell.cellText = "9";
            }
            else if(i == 110 || i == 231)
            {
                gameCell.cellText = "10";
            }
            else
            {
                gameCell.cellX = x;
                gameCell.cellY = y;

                if(_playerBoard != null && _opponentBoard != null)
                {
                    if(gameCell.player.equals("ActivePlayer"))
                    {
                        gameCell.backgroundColor = _playerBoard[gameCell.cellX][gameCell.cellY];
                    }
                    else
                    {
                        gameCell.backgroundColor = _opponentBoard[gameCell.cellX][gameCell.cellY];
                    }
                }

                if(x < 10)
                {
                    x++;
                }
                else
                {
                    x = 1;
                    if(y < 10)
                    {
                        y++;
                    }
                    else
                    {
                        y = 1;
                    }
                }

            }
            ggViewGroup.addView(gameCell);

        }

        return ggViewGroup;

    }

    public interface OnUsedListener
    {
        public void OnUsedListener();
    }

    public OnUsedListener getOnUsedListener()
    {
        return _OnUsedListener;
    }

    public void set_OnUsedListener(OnUsedListener _OnUsedListener)
    {
        this._OnUsedListener = _OnUsedListener;
    }

    OnUsedListener _OnUsedListener = null;

}
