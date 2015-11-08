package com.app.speedsweeper.speedsweeper;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.lang.*;
import java.util.ArrayList;
import android.widget.GridView;
import android.widget.LinearLayout;

/**
 * Note that the programming objective, at this point, is to create a functional Minesweeper game
 * whose board is initialized with default values. As it is being created dynamically, programming
 * involving tying GameActivity to prospective activities will not interfere with the structure of
 * the game.
 *
 * This class will serve as a hub between display functions (in TileAdapter) and value/logic functions (in Board and Tile).
 */

public class GameActivity extends AppCompatActivity {
    // Necessary for inflation.
    GridView gridView;

    // Helpful for maintaining consistent context.


    // Of View type because when dealing inflation operates on XML compatible types like View
    ArrayList<Tile> tiles;

    // This is not where this should be declared. Ideally the last three positions would take
    // values from decision user makes.


    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_parent);
        /* Below is for navigating application.
        final ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        */
        initialize();
    }

    @Override
    public void onStart(){
        super.onStart();
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
     */
    private void initialize() {
        // The following is going to create a hypothetical BOARD of TILES based off of input values.
        // It will also choose where to place the bombs, and then place them.
        Board startBoard = new Board(this, 8, 7, 7);
        startBoard.populateTileCollection();
        startBoard.plantBombs();
        tiles = startBoard.getTileCollection();

        gridView = (GridView) findViewById(R.id.grid);
        gridView.setAdapter(new TileAdapter(this, tiles, gridView));
    }

    /**
     * This method is going to handle all actionEvents after the tiles have been inflated.
     */
}