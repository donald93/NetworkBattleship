package mcpartland.donald.Battleship;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Donald on 10/30/14.
 */
public class GameCellView extends View
{
    public int getCellY()
    {
        return cellY;
    }

    public void setCellY(int cellY)
    {
        this.cellY = cellY;
    }

    public int getCellX()
    {
        return cellX;
    }

    public void setCellX(int cellX)
    {
        this.cellX = cellX;
    }

    public int cellX;
    public int cellY;

    public String cellText;
    public String player;
    public BattleshipGame game;

    public int backgroundColor = 0;

    public GameCellView(Context context)
    {
        super(context);
        cellX = -1;
        cellY = -1;
        player = null;
        cellText = null;

    }

    public interface OnMovePerformedListener
    {
        public void onMovePerformed(BattleshipGame battleshipGame, String outcome, int x, int y);
    }


    public OnMovePerformedListener get_OnMovePerformedListener()
    {
        return _onMovePerformedListener;
    }

    public void set_onMovePerformedListener(OnMovePerformedListener _onMovePerformedListner)
    {
        this._onMovePerformedListener = _onMovePerformedListner;
    }

    OnMovePerformedListener _onMovePerformedListener = null;

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        if(game == null|| cellX == -1 || game.gameOver)
        {
            return super.onTouchEvent(event);
        }
        int selectedCell = game.activePlayer.opponentBoard[cellX - 1][cellY - 1];


        if(!player.equals("ActivePlayer") && selectedCell != 2 && selectedCell != 3)
        {
            String outcome = game.performMove(cellX - 1, cellY - 1);
            _onMovePerformedListener.onMovePerformed(game, outcome, cellX, cellY);
        }


        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        if(backgroundColor == 0) {
            setBackgroundColor(Color.BLUE);
        }
        else if(backgroundColor == 1)
        {
            setBackgroundColor(Color.GRAY);
        }
        else if(backgroundColor == 2)
        {
            setBackgroundColor(Color.WHITE);
        }
        else if(backgroundColor == 3)
        {
            setBackgroundColor(Color.RED);
        }

        Paint p = new Paint();
        p.setColor(Color.WHITE);

        if(cellText != null)
        {
            canvas.drawText(cellText, getWidth()/2, getHeight()/2, p);
        }

        super.onDraw(canvas);
    }
}
