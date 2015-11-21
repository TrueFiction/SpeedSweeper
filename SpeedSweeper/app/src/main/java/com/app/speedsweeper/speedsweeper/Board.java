package com.app.speedsweeper.speedsweeper;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.GridView;

import org.w3c.dom.Attr;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;

/**
 * Board is going to call actions that only occur on create.
 */

public class Board extends GridView {

    // Number of rows specified for this instance of board.
    int rows;
    // Number of columns specified for this instance of board.
    int columns;
    // Total number of bombs that this board contains.
    int allBombs;

    // Random object for improved gameplay (see plantBombs).
    Random rand = new Random();

    public Board(Context context, AttributeSet attrs) {
        super(context, attrs);
        setBoardAttributes(attrs);
        this.rows = 8;
        this.columns = 7;
        this.allBombs = 7;
    }

    private void setBoardAttributes(AttributeSet attrs){
        TypedArray a = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.Board, 0, 0);

        try {
            rows = a.getInt(R.styleable.Board_rows, 8);
            columns = a.getInt(R.styleable.Board_columns, 7);
            allBombs = a.getInt(R.styleable.Board_allBombs, 7);
        } finally {
            a.recycle();
        }
    }

    public void layDownBoard(){
        populateTileCollection();
        selectBombPositions();
        plantBombs();
    }
    private ArrayList<Tile> tiles = new ArrayList<>();
    /*
    If the app is run now, the error would occur here. From time to time I decided to have this method
    operate on Views or Buttons in order to check the functionality of the code beyond this.
     */
    public void populateTileCollection() {
        int index = 0;
        for (int y = 0; y < getRowCount(); y++) {
            for (int x = 0; x < getColumnCount(); x++) {
                Tile tile = (Tile)findViewById(R.id.tile);
                tile.setIndex(index);
                tile.setYCoordinate(y);
                tile.setXCoordinate(x);
                tiles.add(tile);
                index++;
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
This method sets a variable specific to Tile, isBomb, to true or false and then marks the
surrounding tiles with setCautionTiles method. No errors here.
*/
    public void plantBombs() {
        for (int x : selectBombPositions()) {
            tiles.get(x).setBomb(true);
            setCautionTiles(tiles.get(x));
        }
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
    It is this method that makes use of the Tile subclass the most.
     */
    public void setCautionTiles(Tile focusTile) {
        int focusPosition = focusTile.getIndex();
        ArrayList<Tile> scannerList = new ArrayList<>();
        for (Tile tile : tiles) {
            scannerList.add(tile);
        }
        scan(scannerList, focusPosition);
    }

    public void scan(ArrayList<Tile> scannerList, int focusPosition) {
        /*
        The first block here checks the very top row. The values should be set dynamically.
         */
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
        /*
        The middle block checks the very bottom row. Values set dynamically.
        */
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
        /*
        Last block checks for all other tiles, dynamically.
        */
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
    }
}
