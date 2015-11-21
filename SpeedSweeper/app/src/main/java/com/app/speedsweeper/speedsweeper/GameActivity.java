package com.app.speedsweeper.speedsweeper;

import android.app.ActionBar;
import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import java.lang.*;
import java.util.ArrayList;
import android.widget.GridView;

/**
 *
 * This class is the Model View Controller, responsible for game activity and calculations.
 *
 */

public class GameActivity extends Activity {

    GridView gridView;

    Board currentBoard;

    /*
    TODO someone needs to give these guys some values
    This is not the correct way to do it. Individual Tiles do not have any values before they are
    inflated.
     */
    int currentX;
    int currentY;
    int currentTotal = currentX * currentY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_parent);

        // default ActionBar button navigates to Activity positioned one above if pressed
        /*final ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);*/
    }

    @Override
    public void onStart() {
        super.onStart();
        initialize();
    }

    /**
     * This method simply links the GridView type variable gridParent to the GridView in the XML
     * file grid_parent.xml
     *
     * Later on, we will be inflating the RelativeLayout located in tile_child.xml to the GridView in
     * grid_parent.xml.
     *
     * According to information online, the GridView has trouble inflating views
     * that are nested within the GridView. Hence, the use of two XML files.
     *
     * Note that currently all Tile objects are null. Go to Tile constructor.
     */
    private void initialize() {
        ArrayList<Tile> tiles = new ArrayList<>();
        /*
        There is a problem here associating the Tile objects in the tiles arraylist. Furthermore,
        note that because the adapter is taking in an arraylist, there might not be a need to place
        value on them using a for loop within adapter. Perhaps instead the adapter class is already
        built for that.
         */
        for (int x = 0; x < 56; x++) {
            tiles.add((Tile) findViewById(R.id.tile));
        }
        gridView = (GridView) findViewById(R.id.grid);
        gridView.setAdapter(new TileAdapter(this, tiles, gridView));
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}