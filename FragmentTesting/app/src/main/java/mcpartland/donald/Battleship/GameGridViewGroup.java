package mcpartland.donald.Battleship;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Donald on 10/30/14.
 */
public class GameGridViewGroup extends ViewGroup
{
    public GameGridViewGroup(Context context)
    {
        super(context);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b)
    {
        for(int childIndex = 0; childIndex < getChildCount(); childIndex++)
        {

            int row = childIndex/11;
            int column = childIndex % 11;
            View child = getChildAt(childIndex);
            child.layout((getRight()/11 * column) + 2, (getBottom()/22 * row) + 2, (getRight()/11* (column + 1)) - 2, (getBottom()/22 * (row+1)) - 2);
        }
    }

}
