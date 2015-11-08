package com.app.speedsweeper.speedsweeper;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

import java.util.Random;
import java.util.ArrayList;

public class Board extends GridView {
    // Local variable tiles
    ArrayList<Tile> tiles;

    /*
    The following three variables should not be static, as they pertain to a particular instance of
    board that may change within the activity (new game Action event).

    Issue: Should these values be private?
     */
    // Number of rows specified for this instance of board.
    int rows;
    // Number of columns specified for this instance of board.
    int columns;
    // Total number of bombs that this board contains.
    int allBombs;

    // Random object for improved gameplay (see plantBombs).
    Random rand = new Random();

    /*
    The first four constructors are default and are seemingly necessary to implement. The plan here
    is to override either the second or third constructor. An AttributeSet value can be called if the
    GridView in grid_parent.xml is replaced by a NameSpace and a style is defined.

    It is this same problem that subclass Tile has with superclass Button.
     */
    public Board(Context context) {
        super(context);
    }

    public Board(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Board(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public Board(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    // The constructor I have been using. Has not caused errors, but only because Tile does first.
    public Board(Context context, int row, int col, int numBombs) {
        super(context);
        rows = row;
        columns = col;
        allBombs = numBombs;

        // If this is initialized where it is declared above, nullPointerExceptions occur.
        tiles = new ArrayList<>();
    }

    /*
    If the app is run now, the error would occur here. From time to time I decided to have this method
    operate on Views or Buttons in order to check the functionality of the code beyond this.
     */
    public void populateTileCollection(Context c){
        if (tiles.size() < (getRowCount() * getColumnCount() + 1)) {
            for (int x = 0; x < getRowCount(); x++) {
                for (int y = 0; y < getColumnCount(); y++) {
                    Tile tile = new Tile(c);
                    tiles.add(tile);
                }
            }
        }
    }

    /*
    Upon looking at the following method, it seems as if it might be a better idea to pass Tile data
    from class to class by passing primitive data rather than by passing objects.
     */
    private int[] selectBombPositions() {
        int[] bombPositions = new int[allBombs];
        for (int x = 0; x < allBombs; x++) {
            bombPositions[x] = rand.nextInt(tiles.size());
        }
        return bombPositions;
    }

    /*
    Returns tiles
     */
    public ArrayList<Tile> getTiles(){
        return tiles;
    }

    /*
    Returns the total number of rows. Note that the value returned will be (1 + highestYCoordinate)
     */
    public int getRowCount() {
        return columns;
    }

    /*
    This will be used if the user initiates a new game action event, whether within GameActivity or
    from prospective HomeActivity.
     */
    public void setRowCount(int r) {
        columns = r;
    }

    /*
    Returns the total number of columns. (1 + highestXCoordinate)
     */
    public int getColumnCount() {
        return rows;
    }

    // Mimics setRowCount, performs calculations on rows.
    public void setColumnCount(int c) {
        rows = c;
    }

    /*
    This method sets a variable specific to Tile, isBomb, to true or false and then marks the
    surrounding tiles with setCautionTiles method. No errors here.
     */
    public void plantBombs(){
        for (int x : selectBombPositions()) {
            Tile toBeBomb = tiles.get(x);
            toBeBomb.setBomb(true);
            setCautionTiles(toBeBomb);
        }
    }

    /*
    It is this method that makes use of the Tile subclass the most.
     */
    public void setCautionTiles(Tile focusTile) {
        int focusPosition = focusTile.getIndex();
        ArrayList<Tile> scannerList = new ArrayList<>();
        for(Tile tile : tiles) {
            scannerList.add(tile);
        }

        if (focusPosition <= this.getRowCount() - 1) {
            if (focusPosition != 0 && focusPosition != getRowCount() - 1) {
                scannerList.get(focusPosition - 1).addBombToCount();
                scannerList.get(focusPosition + getRowCount() - 1).addBombToCount();
                scannerList.get(focusPosition + getRowCount()).addBombToCount();
                scannerList.get(focusPosition + getRowCount() + 1).addBombToCount();
            } else if (focusPosition == 0) {
                scannerList.get(focusPosition + 1).addBombToCount();
                scannerList.get(focusPosition + getRowCount()).addBombToCount();
                scannerList.get(focusPosition + getRowCount() + 1).addBombToCount();
            } else if (focusPosition == getRowCount() - 1) {
                scannerList.get(focusPosition - 1).addBombToCount();
                scannerList.get(focusPosition + getRowCount() - 1).addBombToCount();
                scannerList.get(focusPosition + getRowCount()).addBombToCount();
            }
        } else if (focusPosition >= ((getColumnCount() - 1) * (getRowCount()))) {
            if (focusPosition != ((getColumnCount() - 1) * (getRowCount()))
                    && focusPosition != ((getRowCount() * getColumnCount()) - 1)) {
                scannerList.get(focusPosition + 1).addBombToCount();
                scannerList.get(focusPosition - 1).addBombToCount();
                scannerList.get(focusPosition - getRowCount() - 1).addBombToCount();
                scannerList.get(focusPosition - getRowCount()).addBombToCount();
                scannerList.get(focusPosition - getRowCount() + 1).addBombToCount();
            } else if (focusPosition == ((getColumnCount() - 1) * (getRowCount()))) {
                scannerList.get(focusPosition + 1).addBombToCount();
                scannerList.get(focusPosition - getRowCount()).addBombToCount();
                scannerList.get(focusPosition - getRowCount() + 1).addBombToCount();
            } else if (focusPosition == ((getRowCount() * getColumnCount()) - 1)) {
                scannerList.get(focusPosition - 1).addBombToCount();
                scannerList.get(focusPosition - getRowCount() - 1).addBombToCount();
                scannerList.get(focusPosition - getRowCount()).addBombToCount();
            }
        } else {
            if ((focusPosition % getRowCount()) == 0 && focusPosition >= getRowCount()) {
                scannerList.get(focusPosition - getRowCount() - 1).addBombToCount();
                scannerList.get(focusPosition - getRowCount()).addBombToCount();
                scannerList.get(focusPosition + 1).addBombToCount();
                scannerList.get(focusPosition + getRowCount()).addBombToCount();
                scannerList.get(focusPosition + getRowCount() + 1).addBombToCount();
            } else if ((focusPosition % getRowCount()) == getRowCount() - 1 && focusPosition >= getRowCount()) {
                scannerList.get(focusPosition - getRowCount()).addBombToCount();
                scannerList.get(focusPosition - getRowCount() + 1).addBombToCount();
                scannerList.get(focusPosition - 1).addBombToCount();
                scannerList.get(focusPosition + getRowCount() - 1).addBombToCount();
                scannerList.get(focusPosition + getRowCount()).addBombToCount();
            } else {
                scannerList.get(focusPosition + 1).addBombToCount();
                scannerList.get(focusPosition - 1).addBombToCount();
                scannerList.get(focusPosition - getRowCount() - 1).addBombToCount();
                scannerList.get(focusPosition - getRowCount()).addBombToCount();
                scannerList.get(focusPosition - getRowCount() + 1).addBombToCount();
                scannerList.get(focusPosition + getRowCount() - 1).addBombToCount();
                scannerList.get(focusPosition + getRowCount()).addBombToCount();
                scannerList.get(focusPosition + getRowCount() + 1).addBombToCount();
            }
        }
        for (Tile tile : scannerList){

        }
    }
}