package com.app.speedsweeper.speedsweeper;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import java.lang.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.jar.Attributes;

public class Tile extends Button {

    // Informs whether this tile has had a flag placed on it
    private Boolean hasFlag = false;
    // Informs whether tile is a bomb
    private Boolean isBomb = false;
    // Informs whether tile has been expanded
    private Boolean isExpanded = false;

    // Number of bombs that surrounds this tile.
    private int bombCount = 0;
    // Value of the GridLayout row this tile is positioned in.
    private int rowCoordinate;
    // Value of the GridLayout column this tile is position in.
    private int columnCoordinate;
    // Position of this view in AL tileCollection.
    private int gridOrderPosition;

    // The previous two variables provide unique coordinates of position. They are passed into Tile
    // class constructor in order to create a connection between this Tile to an existing, XML
    // created button (by reference) which is positioned on the grid layout with coordinate values
    // that match those of the above variables.

    // Alternatively, these two variables would determine the coordinate position of this Tile on a
    // GridLayout - in other words, starting a new approach to the creation of this application.
    // If this were to be done, all of the pre-existing XML code is effectively useless.

    // The tile, before this matching process, is hypothetical, as it has neither been given any
    // XML attributes, nor has it been assigned to a view that already exists),

    /*
     * Constructor takes params rowC, colC, identity. Takes the corresponding values and assigns
     * them to rowCoordinate, columnCoordinate, and logicID respectively.
     */
    public Tile (Context context, AttributeSet attrs, int rowC, int colC, int index) {
        super(context);
        rowCoordinate = rowC;
        columnCoordinate = colC;
        gridOrderPosition = index;
        /* set attributes for the button  */
    }

    public int getIndex() {
        return gridOrderPosition;
    }

    public void setHasFlag(Boolean b) {
        hasFlag = b;
    }

    public Boolean getHasFlag() {
        return hasFlag;
    }

    public void setIsBomb(Boolean b) {
        isBomb = b;
    }

    public Boolean getIsBomb() {
        return isBomb;
    }

    public void setIsExpanded(Boolean b) {
        isBomb = b;
    }

    public Boolean getIsExpanded() {
        return isBomb;
    }

    public int getXCoordinate() {
        return rowCoordinate;
    }

    public int getYCoordinate() {
        return columnCoordinate;
    }

    public int addBombToCount() {
        return bombCount + 1;
    }

    public void scanNeighbor() {
        //Check surrounding tiles.
        // if
    }

    /**
     * Next method is true if bombCount is greater than zero. What this means is that, assuming
     * scanNeighbor has been called on the view at hand and bombCount is the number of surrounding
     * tiles whose isBomb value is true, the tile should display the number of bombs that surround it.
     * Therefore, if this returns true, then (by use of another method) the background of this tile
     * should be changed to a picture that displays the value of bombCount.
     */
    public Boolean isCautionTile() {
        return (bombCount > 0);
    }

    public void expandTile(View v, int drawable){
        v.setBackgroundResource(drawable);
        // int drawable example: R.id.detects_one
    }

    /**
     * Not a setter for field variable hasFlag. Adds flags to tiles on hold length of 0.5 seconds.
     */
    public void setFlag(){
        // onLongClickListener
    }
}
