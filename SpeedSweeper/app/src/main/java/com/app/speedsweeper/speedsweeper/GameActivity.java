package com.app.speedsweeper.speedsweeper;

import android.app.ActionBar;
import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import java.lang.*;
import java.util.ArrayList;
import android.widget.GridView;

/**
 * Note that the programming objective, at this point, is to create a functional Minesweeper game
 * whose board is initialized with default values. As it is being created dynamically, programming
 * involving tying GameActivity to prospective activities will not interfere with the structure of
 * the game.
 *
 * This class will serve as a hub between GUI functions (in TileAdapter) and value/logic functions (in Board and Tile).
 *
 * Issue: should GameActivity extend Activity?
 */

public class GameActivity extends Activity {

    /*
    setContentView calls R.layout.grid_parent because R.layout.tile_child is being added to the former
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_parent);

        // default ActionBar button navigates to Activity positioned one above if pressed
        final ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    /*
    Calling initialize onStart seems to produce the same result as if it is called onCreate.
     */
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
        ArrayList<Tile> tiles;
        GridView gridView;

        /* The following is going to create a hypothetical BOARD of TILES based off of input values.
         It will also choose where to place the bombs, and then place them. None of this is currently
         linked to display.*/
        Board startBoard = new Board(this, 8, 7, 7);
        //startBoard.populateTileCollection(this);
        //startBoard.plantBombs();
        startBoard.trial(this);
        tiles = startBoard.getTiles();

        //setAdapter works if tiles is populated with non-null Tile objects
        gridView = (GridView) findViewById(R.id.grid);
        gridView.setAdapter(new TileAdapter(this, tiles, gridView));
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}