package com.app.speedsweeper.speedsweeper;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.GridView;
import java.util.Random;
import java.util.ArrayList;

public class Board extends GridView {
    
    GameActivity GA;
    
    ArrayList<Tile> tileCollection;
    
    Tile tile;

    Context c;

    // Number of rows specified for this instance of board.
    static int rows;
    // Number of columns specified for this instance of board.
    static int columns;
    // Total number of bombs that this board contains.
    static int allBombs;

    // Random object for improved gameplay (see plantBombs).
    Random rand = new Random();

    public Board(Context context) {
        super(context);
        rows = 8;
        columns = 7;
        allBombs = 7;
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

    public Board(Context context, int row, int col, int numBombs) {
        super(context);
        c = context;
        rows = row;
        columns = col;
        allBombs = numBombs;
        tileCollection = new ArrayList<>(rows*columns);
    }

    private int[] selectBombPositions() {
        int[] bombPositions = new int[allBombs];
        for (int x = 0; x < allBombs; x++) {
            bombPositions[x] = rand.nextInt(allBombs);
        }
        return bombPositions;
    }

    public ArrayList<Tile> getTileCollection(){
        return tileCollection;
    }

    public int getRowCount() {
        return columns;
    }

    public void setRowCount(int r) {
        columns = r;
    }

    public int getColumnCount() {
        return rows;
    }

    public void setColumnCount(int c) {
        rows = c;
    }

    public void populateTileCollection(){
        int index = 0;
        if (tileCollection.size() < getRowCount() * getColumnCount()) {
            for (int x = 0; x < getRowCount(); x++) {
                for (int y = 0; y < getColumnCount(); y++) {
                    Tile tile = new Tile(c, y, x, index);
                    tileCollection.add(tile);
                    index++;
                }
                }
            }
        }

    public void plantBombs(){
        for (int x : selectBombPositions()) {
            Tile toBeBomb = tileCollection.get(x);
            toBeBomb.setBomb(true);
            setCautionTiles(toBeBomb);
        }
    }

    public void setCautionTiles(Tile focusTile) {
        int focusPosition = focusTile.getIndex();
        ArrayList<Tile> scannerList = new ArrayList<>();
        for(Tile tile : tileCollection) {
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
    }
}