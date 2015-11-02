package com.app.speedsweeper.speedsweeper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import java.lang.*;
import java.util.ArrayList;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    /*
    Getting some tough to solve errors by calling the methods below.
    I am trying to populate an arraylist with view objects, each one
    being of the form of the XML made buttons I labelled with separate
    ids. I am doing this so that I can access all the buttons individually,
    allowing me to place the bombs.
     */

    View reset;
    ArrayList<View> tiles = new ArrayList<>();
    ArrayList<Integer> originalIds = new ArrayList<>();
    Random r = new Random();

    public void initializeGrid(){
        grabChildren();
        saveId();
        plantBombs();
    }
    public void grabChildren(){
        View view;
        view = findViewById(R.id.mine_field);
        ((ViewGroup) view).addChildrenForAccessibility(tiles);
    }
    private void saveId(){
        for (int x = 0; x < tiles.size(); x++){
            originalIds.add(tiles.get(x).getId());
        }
    }
    public void plantBombs(){
        for (int x = 1; x <= 10; x++) {
            View unluckyTile = tiles.get(1);
            unluckyTile.setId(R.id.bomb);
        }
    }
    public Boolean checkTile(View v, Boolean b){
        if (v.getId() == R.id.bomb){
            b = explosionResult();
            return b;
        } else {
            b = safeResult();
            return b;
        }
    }
    public Boolean explosionResult(){
        return false;
    }
    public Boolean safeResult(){
        return true;
    }
    public void resetGame(View v){
        for (int x = 0; x < tiles.size(); x++) {
            View Tile = tiles.get(x);
            Tile.setId(originalIds.get(x));
            Tile.setBackgroundResource(R.drawable.temp_pic2);
        }
    }
    /*
    Normal creation.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        reset = findViewById(R.id.reset_button);
        reset.setClickable(false);
    }
    /*
    Start involves setting up an AL that's easy to work with.
     */
    @Override
    public void onStart(){
        super.onStart();
        initializeGrid();
    }
    /*
    This next method is where the program is working. I have a
        conditional test in there but just to test its reliability.
     */
    public void registerClick(View v){
        Boolean entry = false;
        entry = checkTile(v, entry);
        if (entry) {
            endGame(v);
        }
    }
    /*
    Ideally I would use many methods like this to allow the
        computer to manage all possible outcomes.
     */
    public void endGame(View v) {
        v.setBackgroundResource(R.drawable.bomb_1);
        reset.setVisibility(View.VISIBLE);
        reset.setClickable(true);
    }
}