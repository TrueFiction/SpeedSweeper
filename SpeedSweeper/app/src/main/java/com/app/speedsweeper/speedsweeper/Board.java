package com.app.speedsweeper.speedsweeper;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import java.util.Random;
import java.util.ArrayList;

public class Board extends TableLayout {
    //Holds GameActivity's context for resources
    Context context;

    // Number of rows specified for this instance of board.
    int rows;
    // Number of columns specified for this instance of board.
    int columns;
    // Total number of bombs that this board contains.
    int allBombs;

    // Collection of all Tiles.
    ArrayList<Tile> tileCollection = new ArrayList<>();

    // Random object for improved gameplay (see plantBombs).
    Random rand = new Random();

    //
    AttributeSet varAttrSet = new AttributeSet() {
        @Override
        public int getAttributeCount() {
            return 0;
        }

        @Override
        public String getAttributeName(int index) {
            return null;
        }

        @Override
        public String getAttributeValue(int index) {
            return null;
        }

        @Override
        public String getAttributeValue(String namespace, String name) {
            return null;
        }

        @Override
        public String getPositionDescription() {
            return null;
        }

        @Override
        public int getAttributeNameResource(int index) {
            return 0;
        }

        @Override
        public int getAttributeListValue(String namespace, String attribute, String[] options, int defaultValue) {
            return 0;
        }

        @Override
        public boolean getAttributeBooleanValue(String namespace, String attribute, boolean defaultValue) {
            return false;
        }

        @Override
        public int getAttributeResourceValue(String namespace, String attribute, int defaultValue) {
            return 0;
        }

        @Override
        public int getAttributeIntValue(String namespace, String attribute, int defaultValue) {
            return 0;
        }

        @Override
        public int getAttributeUnsignedIntValue(String namespace, String attribute, int defaultValue) {
            return 0;
        }

        @Override
        public float getAttributeFloatValue(String namespace, String attribute, float defaultValue) {
            return 0;
        }

        @Override
        public int getAttributeListValue(int index, String[] options, int defaultValue) {
            return 0;
        }

        @Override
        public boolean getAttributeBooleanValue(int index, boolean defaultValue) {
            return false;
        }

        @Override
        public int getAttributeResourceValue(int index, int defaultValue) {
            return 0;
        }

        @Override
        public int getAttributeIntValue(int index, int defaultValue) {
            return 0;
        }

        @Override
        public int getAttributeUnsignedIntValue(int index, int defaultValue) {
            return 0;
        }

        @Override
        public float getAttributeFloatValue(int index, float defaultValue) {
            return 0;
        }

        @Override
        public String getIdAttribute() {
            return null;
        }

        @Override
        public String getClassAttribute() {
            return null;
        }

        @Override
        public int getIdAttributeResourceValue(int defaultValue) {
            return 0;
        }

        @Override
        public int getStyleAttribute() {
            return 0;
        }
    };

    public Board(Context activityContext, int row, int col, int numBombs) {
        super(activityContext);

        ////////////////IF WE SEE RED THEN THIS LAYOUT IS GETTING TO ACTIVITY/////////////////

        this.setBackgroundResource(R.color.red);
        context = activityContext;
        rows = row;
        columns = col;
        allBombs = numBombs;
    }

    private int[] selectBombPositions() {
        int[] bombPositions = new int[allBombs];
        for (int x = 0; x < allBombs; x++) {
            bombPositions[x] = rand.nextInt(allBombs);
        }
        return bombPositions;
    }

    public int getRowLength() {
        return columns;
    }

    public int getColumnLength() {
        return rows;
    }

    public TableLayout createTiles() {

        TableLayout layout = new TableLayout(context);
        layout.setLayoutParams(new TableLayout.LayoutParams(4, 5));

        int listIndex = 0;
        for (int x = 0; x < rows; x++) {
            TableRow tr = new TableRow(context);
            for (int y = 0; y < columns; y++) {
                Tile tile = new Tile (context, varAttrSet, x, y, listIndex);
                tile.setText("A");
                tileCollection.add(tile);
                listIndex++;
                tr.addView(tile, 35, 35);
            }
            layout.addView(tr);

        }
        return layout;
    }
           /* int [] attributes = new int [] {android.R.attr.background, android.R.attr.layout_width,
            android.R.attr.layout_height, android.R.attr.id, android.R.attr.onClick,
            android.R.attr.layout_row, android.R.attr.layout_column, android.R.attr.visibility};*/

    public void plantBombs() {
        for (int x : selectBombPositions()) {
            Tile toBeBomb = tileCollection.get(x);
            toBeBomb.setIsBomb(true);
            setCautionTiles(toBeBomb);
        }
    }

    public void setCautionTiles(Tile focusTile) {
        int focusPosition = focusTile.getIndex();

        if (focusPosition <= this.getRowLength() - 1) {
            if (focusPosition != 0 && focusPosition != getRowLength() - 1) {
                tileCollection.get(focusPosition - 1).addBombToCount();
                tileCollection.get(focusPosition + getRowLength() - 1).addBombToCount();
                tileCollection.get(focusPosition + getRowLength()).addBombToCount();
                tileCollection.get(focusPosition + getRowLength() + 1).addBombToCount();
            } else if (focusPosition == 0) {
                tileCollection.get(focusPosition + 1).addBombToCount();
                tileCollection.get(focusPosition + getRowLength()).addBombToCount();
                tileCollection.get(focusPosition + getRowLength() + 1).addBombToCount();
            } else if (focusPosition == getRowLength() - 1) {
                tileCollection.get(focusPosition - 1).addBombToCount();
                tileCollection.get(focusPosition + getRowLength() - 1).addBombToCount();
                tileCollection.get(focusPosition + getRowLength()).addBombToCount();
            }
        } else if (focusPosition >= ((getColumnLength() - 1) * (getRowLength()))) {
            if (focusPosition != ((getColumnLength() - 1) * (getRowLength()))
                    && focusPosition != ((getRowLength() * getColumnLength()) - 1)) {
                tileCollection.get(focusPosition + 1).addBombToCount();
                tileCollection.get(focusPosition - 1).addBombToCount();
                tileCollection.get(focusPosition - getRowLength() - 1).addBombToCount();
                tileCollection.get(focusPosition - getRowLength()).addBombToCount();
                tileCollection.get(focusPosition - getRowLength() + 1).addBombToCount();
            } else if (focusPosition == ((getColumnLength() - 1) * (getRowLength()))) {
                tileCollection.get(focusPosition + 1).addBombToCount();
                tileCollection.get(focusPosition - getRowLength()).addBombToCount();
                tileCollection.get(focusPosition - getRowLength() + 1).addBombToCount();
            } else if (focusPosition == ((getRowLength() * getColumnLength()) - 1)) {
                tileCollection.get(focusPosition - 1).addBombToCount();
                tileCollection.get(focusPosition - getRowLength() - 1).addBombToCount();
                tileCollection.get(focusPosition - getRowLength()).addBombToCount();
            }
        } else {
            if ((focusPosition % getRowLength()) == 0 && focusPosition >= getRowLength()) {
                tileCollection.get(focusPosition - getRowLength() - 1).addBombToCount();
                tileCollection.get(focusPosition - getRowLength()).addBombToCount();
                tileCollection.get(focusPosition + 1).addBombToCount();
                tileCollection.get(focusPosition + getRowLength()).addBombToCount();
                tileCollection.get(focusPosition + getRowLength() + 1).addBombToCount();
            } else if ((focusPosition % getRowLength()) == getRowLength() - 1 && focusPosition >= getRowLength()) {
                tileCollection.get(focusPosition - getRowLength()).addBombToCount();
                tileCollection.get(focusPosition - getRowLength() + 1).addBombToCount();
                tileCollection.get(focusPosition - 1).addBombToCount();
                tileCollection.get(focusPosition + getRowLength() - 1).addBombToCount();
                tileCollection.get(focusPosition + getRowLength()).addBombToCount();
            } else {
                tileCollection.get(focusPosition + 1).addBombToCount();
                tileCollection.get(focusPosition - 1).addBombToCount();
                tileCollection.get(focusPosition - getRowLength() - 1).addBombToCount();
                tileCollection.get(focusPosition - getRowLength()).addBombToCount();
                tileCollection.get(focusPosition - getRowLength() + 1).addBombToCount();
                tileCollection.get(focusPosition + getRowLength() - 1).addBombToCount();
                tileCollection.get(focusPosition + getRowLength()).addBombToCount();
                tileCollection.get(focusPosition + getRowLength() + 1).addBombToCount();
            }
        }
    }
}

