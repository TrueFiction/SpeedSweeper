package com.app.speedsweeper.speedsweeper;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.GridLayout;
import java.util.Random;

import java.util.ArrayList;

public class Board extends GridLayout {

    int rows, columns, allBombs;
    ArrayList<Tile> tileCollection = new ArrayList<>();
    Random rand = new Random();
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

    public Board(Context context, AttributeSet attrs) {
        super(context, attrs);
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

    public void createTiles() {
        int listIndex = 0;
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < columns; y++) {
                Tile tile = new Tile(ThisApplication.getAppContext(), varAttrSet, x, y, listIndex);
                tileCollection.add(tile);
                listIndex++;
            }
        }
    }

    public void plantBombs() {
        for (int x : selectBombPositions()) {
            Tile toBeBomb = tileCollection.get(x);
            toBeBomb.setIsBomb(true);
            toBeBomb.scanNeighbor();
            setCautionTiles(toBeBomb);
        }
    }

    public void setCautionTiles(Tile focusTile) {
        int focusPosition = focusTile.getIndex();

        //int row = focusTile.getXCoordinate();
        //int column = focusTile.getYCoordinate();

        if (focusPosition <= this.getRowLength() - 1) {
            if (focusPosition != 0 && focusPosition != getRowLength() - 1) {
                tileCollection.get(focusPosition - 1);
                tileCollection.get(focusPosition + getRowLength() - 1);
                tileCollection.get(focusPosition + getRowLength());
                tileCollection.get(focusPosition + getRowLength() + 1);
            } else if (focusPosition == 0) {
                tileCollection.get(focusPosition + 1);
                tileCollection.get(focusPosition + getRowLength());
                tileCollection.get(focusPosition + getRowLength() + 1);
            } else if (focusPosition == getRowLength() - 1) {
                tileCollection.get(focusPosition - 1);
                tileCollection.get(focusPosition + getRowLength() - 1);
                tileCollection.get(focusPosition + getRowLength());
            }
        } else if (focusPosition >= ((getColumnLength() - 1) * (getRowLength()))) {
            if (focusPosition != ((getColumnLength() - 1) * (getRowLength()))
                    && focusPosition != ((getRowLength() * getColumnLength()) - 1)) {
                tileCollection.get(focusPosition + 1);
                tileCollection.get(focusPosition - 1);
                tileCollection.get(focusPosition - getRowLength() - 1);
                tileCollection.get(focusPosition - getRowLength());
                tileCollection.get(focusPosition - getRowLength() + 1);
            } else if (focusPosition == ((getColumnLength() - 1) * (getRowLength()))) {
                tileCollection.get(focusPosition + 1);
                tileCollection.get(focusPosition - getRowLength());
                tileCollection.get(focusPosition - getRowLength() + 1);
            } else if (focusPosition == ((getRowLength() * getColumnLength()) - 1)) {
                tileCollection.get(focusPosition - 1);
                tileCollection.get(focusPosition - getRowLength() - 1);
                tileCollection.get(focusPosition - getRowLength());
            }
        } else {
            if ((focusPosition % getRowLength()) == 0) {
                tileCollection.get(focusPosition - getRowLength() - 1);
                tileCollection.get(focusPosition - getRowLength());
                tileCollection.get(focusPosition + 1);
                tileCollection.get(focusPosition + getRowLength());
                tileCollection.get(focusPosition + getRowLength() + 1);
            } else if ((focusPosition % getRowLength()) == getRowLength() - 1) {
                tileCollection.get(focusPosition - getRowLength());
                tileCollection.get(focusPosition - getRowLength() + 1);
                tileCollection.get(focusPosition - 1);
                tileCollection.get(focusPosition + getRowLength() - 1);
                tileCollection.get(focusPosition + getRowLength());
            } else {
                tileCollection.get(focusPosition + 1);
                tileCollection.get(focusPosition - 1);
                tileCollection.get(focusPosition - getRowLength() - 1);
                tileCollection.get(focusPosition - getRowLength());
                tileCollection.get(focusPosition - getRowLength() + 1);
                tileCollection.get(focusPosition + getRowLength() - 1);
                tileCollection.get(focusPosition + getRowLength());
                tileCollection.get(focusPosition + getRowLength() + 1);
            }
        }
    }
}

