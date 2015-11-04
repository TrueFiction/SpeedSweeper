package com.app.speedsweeper.speedsweeper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import java.lang.*;
import java.util.ArrayList;
import java.util.Random;

public class GameActivity extends AppCompatActivity {
    /*
    Alrighty then, I have a good framework. I now need to figure out how to expand tiles
        and create the minesweeper gameplay.

    Other tasks:
        Main menu
        High score
        Timer
        Proper graphics design
     */

    View reset_button;
    ArrayList<View> tiles = new ArrayList<>(), flaggedTile = new ArrayList<>(), garbage = new ArrayList<>();
    ArrayList<Integer> originalIds = new ArrayList<>();
    Random r = new Random();
    Boolean check = true, planting, init;

    public void initializeGrid() {
        grabChildren();
        saveId();
        plantBombs();
    }

    /*
    I am not at all proud of the following method, but at least after this,
        I have exactly what I want. Note that I used eclipse to generate
        the below code in String output.
     */
    public void grabChildren() {
        tiles.add(findViewById(R.id.tile_1));
        tiles.add(findViewById(R.id.tile_2));
        tiles.add(findViewById(R.id.tile_3));
        tiles.add(findViewById(R.id.tile_4));
        tiles.add(findViewById(R.id.tile_5));
        tiles.add(findViewById(R.id.tile_6));
        tiles.add(findViewById(R.id.tile_7));
        tiles.add(findViewById(R.id.tile_8));
        tiles.add(findViewById(R.id.tile_9));
        tiles.add(findViewById(R.id.tile_10));
        tiles.add(findViewById(R.id.tile_11));
        tiles.add(findViewById(R.id.tile_12));
        tiles.add(findViewById(R.id.tile_13));
        tiles.add(findViewById(R.id.tile_14));
        tiles.add(findViewById(R.id.tile_15));
        tiles.add(findViewById(R.id.tile_16));
        tiles.add(findViewById(R.id.tile_17));
        tiles.add(findViewById(R.id.tile_18));
        tiles.add(findViewById(R.id.tile_19));
        tiles.add(findViewById(R.id.tile_20));
        tiles.add(findViewById(R.id.tile_21));
        tiles.add(findViewById(R.id.tile_22));
        tiles.add(findViewById(R.id.tile_23));
        tiles.add(findViewById(R.id.tile_24));
        tiles.add(findViewById(R.id.tile_25));
        tiles.add(findViewById(R.id.tile_26));
        tiles.add(findViewById(R.id.tile_27));
        tiles.add(findViewById(R.id.tile_28));
        tiles.add(findViewById(R.id.tile_29));
        tiles.add(findViewById(R.id.tile_30));
        tiles.add(findViewById(R.id.tile_31));
        tiles.add(findViewById(R.id.tile_32));
        tiles.add(findViewById(R.id.tile_33));
        tiles.add(findViewById(R.id.tile_34));
        tiles.add(findViewById(R.id.tile_35));
        tiles.add(findViewById(R.id.tile_36));
        tiles.add(findViewById(R.id.tile_37));
        tiles.add(findViewById(R.id.tile_38));
        tiles.add(findViewById(R.id.tile_39));
        tiles.add(findViewById(R.id.tile_40));
        tiles.add(findViewById(R.id.tile_41));
        tiles.add(findViewById(R.id.tile_42));
        tiles.add(findViewById(R.id.tile_43));
        tiles.add(findViewById(R.id.tile_44));
        tiles.add(findViewById(R.id.tile_45));
        tiles.add(findViewById(R.id.tile_46));
        tiles.add(findViewById(R.id.tile_47));
        tiles.add(findViewById(R.id.tile_48));
        tiles.add(findViewById(R.id.tile_49));
        tiles.add(findViewById(R.id.tile_50));
        tiles.add(findViewById(R.id.tile_51));
        tiles.add(findViewById(R.id.tile_52));
        tiles.add(findViewById(R.id.tile_53));
        tiles.add(findViewById(R.id.tile_54));
        tiles.add(findViewById(R.id.tile_55));
        tiles.add(findViewById(R.id.tile_56));
    }

    private void saveId() {
        for (int x = 0; x < tiles.size(); x++) {
            originalIds.add(tiles.get(x).getId());
        }
    }
    /*
    In the following method I would reset game data.
     */
    public void resetGame(View v) {
        for (int x = 0; x < tiles.size(); x++) {
            View Tile = tiles.get(x);
            Tile.setId(originalIds.get(x));
            Tile.setBackgroundResource(R.drawable.temp_pic2);
        }
        if (garbage.size() > 0) {
            for (int x = 0; x < garbage.size(); x++) {
                garbage.remove(x);
            }
        }
        plantBombs();
        reset_button.setVisibility(View.INVISIBLE);
        reset_button.setClickable(false);
        check = true;
    }

    /*
    Normal creation.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        reset_button = findViewById(R.id.reset_button);
        reset_button.setClickable(false);
    }

    /*
    Start involves setting up an AL that's easy to work with.
     */
    @Override
    public void onStart() {
        super.onStart();
        initializeGrid();
    }

    public void registerClick(View v) {
        if (check) {
            Boolean entry;
            entry = checkTile(v);
            if (entry) {
                endGame(v);
            }
        }
    }
    /*
    In the following method I would save game data.
     */
    public void endGame(View v) {
        check = false;
        v.setBackgroundResource(R.drawable.bomb_1);
        reset_button.setVisibility(View.VISIBLE);
        reset_button.setClickable(true);
    }
    public void plantBombs() {
        planting = true;
        for (int x = 0; x < 7; x++) {
            View unluckyTile = tiles.get(r.nextInt(tiles.size()));
            unluckyTile.setId(R.id.bomb);
            checkSurroundings(unluckyTile, false);
        }
        planting = false;
    }
    public Boolean checkTile(View v) {
        if (v.getId() == R.id.bomb) {
            Boolean b = explosionResult();
            return b;
        } else {
            Boolean b = safeResult(v);
            return b;
        }
    }
    public Boolean explosionResult() {
        return true;
    }
    public Boolean safeResult(View v) {
        checkSurroundings(v, true);
        int x = 100;
        while (x > 0){
            checkSurroundings(flaggedTile.get(0), false);
            x--;
        }
        for (int y = 0; y < flaggedTile.size(); y++){
            flaggedTile.remove(y);
        }
        return false;
    }
    /*
    I took a stab at expanding tiles and I got it working, but not accurately.
    There is unnecessary code, I have been comparing approaches.
     */
    public void checkSurroundings(View v, Boolean b) {
        scan(v, b);
        decideSurroundings(v);
        sort();
    }

    public void scan(View v, Boolean b) {
        init = b;
        int focusPosition = -1;
        for (int x = 0; x < tiles.size(); x++) {
            if (v.equals(tiles.get(x))) {
                focusPosition = x;
            }
        }
        if (focusPosition <= 6) {
            if (focusPosition != 0 && focusPosition != 6) {
                flaggedTile.add(tiles.get(focusPosition + 1));
                flaggedTile.add(tiles.get(focusPosition - 1));
                flaggedTile.add(tiles.get(focusPosition + 6));
                flaggedTile.add(tiles.get(focusPosition + 7));
                flaggedTile.add(tiles.get(focusPosition + 8));
            } else if (focusPosition == 0) {
                flaggedTile.add(tiles.get(focusPosition + 1));
                flaggedTile.add(tiles.get(focusPosition + 7));
                flaggedTile.add(tiles.get(focusPosition + 8));
            } else if (focusPosition == 6) {
                flaggedTile.add(tiles.get(focusPosition - 1));
                flaggedTile.add(tiles.get(focusPosition + 6));
                flaggedTile.add(tiles.get(focusPosition + 7));
            }
        } else if (focusPosition >= 49) {
            if (focusPosition != 49 && focusPosition != 55) {
                flaggedTile.add(tiles.get(focusPosition + 1));
                flaggedTile.add(tiles.get(focusPosition - 1));
                flaggedTile.add(tiles.get(focusPosition - 6));
                flaggedTile.add(tiles.get(focusPosition - 7));
                flaggedTile.add(tiles.get(focusPosition - 8));
            } else if (focusPosition == 49) {
                flaggedTile.add(tiles.get(focusPosition + 1));
                flaggedTile.add(tiles.get(focusPosition - 7));
                flaggedTile.add(tiles.get(focusPosition - 8));
            } else if (focusPosition == 55) {
                flaggedTile.add(tiles.get(focusPosition - 1));
                flaggedTile.add(tiles.get(focusPosition - 6));
                flaggedTile.add(tiles.get(focusPosition - 7));
            }
        } else if (focusPosition < 49 && focusPosition > 6) {
            if (focusPosition % 7 == 0) {
                flaggedTile.add(tiles.get(focusPosition - 6));
                flaggedTile.add(tiles.get(focusPosition - 7));
                flaggedTile.add(tiles.get(focusPosition + 1));
                flaggedTile.add(tiles.get(focusPosition + 7));
                flaggedTile.add(tiles.get(focusPosition + 8));
            } else if ((focusPosition + 6) % 6 == 0) {
                flaggedTile.add(tiles.get(focusPosition - 7));
                flaggedTile.add(tiles.get(focusPosition - 8));
                flaggedTile.add(tiles.get(focusPosition - 1));
                flaggedTile.add(tiles.get(focusPosition + 6));
                flaggedTile.add(tiles.get(focusPosition + 7));
            } else {
                flaggedTile.add(tiles.get(focusPosition + 1));
                flaggedTile.add(tiles.get(focusPosition - 1));
                flaggedTile.add(tiles.get(focusPosition - 6));
                flaggedTile.add(tiles.get(focusPosition - 7));
                flaggedTile.add(tiles.get(focusPosition - 8));
                flaggedTile.add(tiles.get(focusPosition + 6));
                flaggedTile.add(tiles.get(focusPosition + 7));
                flaggedTile.add(tiles.get(focusPosition + 8));
            }
        }
    }



    public void sort(){
        for (int y = 0; y < flaggedTile.size(); y++) {
            Boolean jesus = true;
            for (int x = 0; x < garbage.size(); x++) {
                if (!garbage.get(x).equals(flaggedTile.get(y))){
                    if (flaggedTile.get(y).getId() != R.id.caution || flaggedTile.get(y).getId() != R.id.bomb)
                    jesus = false;
                }
            }
            if (jesus) {
                garbage.add(flaggedTile.get(y));
            }
        }
        for (int x = 0; x < flaggedTile.size(); x++){
            Boolean jesus = false;
            for (int y = 0; y < garbage.size(); y++){
                if (flaggedTile.get(x).equals(garbage.get(y))){
                    jesus = true;
                }
            }
            if (jesus){
                flaggedTile.remove(x);
            }
        }
        if ((!planting && !init)&&(flaggedTile.size() > 0)){
            //decideSurroundings(v);
        } else {
            for (int x = 0; x < flaggedTile.size(); x++) {
                if (flaggedTile.get(x).getId() != R.id.bomb) {
                    flaggedTile.get(x).setId(R.id.caution);
                }
                flaggedTile.remove(x);
            }
        }
    }

    public void decideSurroundings(View v) {
            if (v.getId() != R.id.bomb) {
                safeExpansion(v);
            }
    }

    public void expand(View v) {
        /*
        Here is where I would have all focus positions that are not bombs expand
        and reveal proximity of bombs.
         */
        v.setBackgroundResource(R.drawable.pic_3);
        int id = v.getId();
        if (id == R.id.caution) {
            flaggedTile.remove(v);
        }
    }

    public void safeExpansion(View v){
        if (flaggedTile.size() > 0) {
            expand(v);
        }
    }
}