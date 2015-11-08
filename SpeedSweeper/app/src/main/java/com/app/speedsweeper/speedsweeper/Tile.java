package com.app.speedsweeper.speedsweeper;

import android.content.Context;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import java.lang.*;

/********
 *
 *  There are many methods and variables in this class that, if put to good use, will allow for
 *  functional construction. I believe that I have been ignoring the capabilities of this class
 *  because I have been trying to determine whether or not the link between this class and the
 *  TileAdapter is logically sound. At this point, I am confident that it is; the errors that arise
 *  now result from the discrepancy between this class's capabilities and how it is being used.
 *
 *  Having realized this, I now realize how important it is to have a programmatic structure that
 *  makes use of object hierarchy as much as possible.
 *
 *  Note: I read about Model-View Controllers and how important they are when creating a GUI. With
 *  this model in mind, perhaps GameActivity should play the role of controller. As for the other
 *  methods, it is clear that TileAdapter is responsible for ActionEvent processing, but a
 *
 ********/

public class Tile extends Button {

    /*
    The message of the block comment below applies to these three variables as well. Below is a quick
    jotting of the implementation of one of these variables.
        @Override
        public Parcelable.Creator<MotionEvent> getMotionEventCreator() {
            // flagClicked = onLongClickListener(); // Returns int[x]
            // if (flagClicked != null)
            //      toggleFlag = flagClicked.getPointerId(x);
            return toggleFlag;
        }   // Sends index value to GameActivity, where it is passed on to Board.
            // Board is configured by methods called in Board that have an effect on Tile
     It seems clear that this is not the right way to go about implementing this idea. With this
     approach as a foundation, however, I believe the issues will be resolved.
     */

    // Informs whether this tile has had a flag placed on it
    private Boolean flagStatus = false;
    // Informs whether tile is a bomb
    private Boolean isBomb = false;
    // Informs whether tile has been expanded
    private Boolean isExpanded = false;

    /*
    The four integer variable below can (almost exclusively) be used to accurately configure how
    TileAdapter inflates the hierarchical merging of the Tile objects and the Board object. In order to do
    this, the constructor for each object must be configured correctly, and the two XML files they
    reside in must be configured so that their tag is represented by a NameSpace with a particular
    style that directly corresponds to each object's field and function.
     */

    // Number of bombs that surrounds this tile.
    private int bombCount = 0;
    // Value of the GridLayout row this tile is positioned in.
    private int rowPosition;
    // Value of the GridLayout column this tile is position in.
    private int columnPosition;
    // Position of this view in AL tiles.
    private int gridOrderPosition;

    /*
     * Constructor takes params rowC, colC, identity. Takes the corresponding values and assigns
     * them to rowCoordinate, columnCoordinate, and logicID respectively.
     */

    public Tile (Context context){
        super(context);
    }

    public Tile (Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs);
        this.setText("A");
        this.setWidth(35);
        this.setHeight(35);
        this.setBackgroundResource(R.drawable.temp_pic2);
    }

    public int getIndex() {
        return gridOrderPosition;
    }

    public void setIndex(int position) {
        gridOrderPosition = position;
    }

    public void setFlagStatus(Boolean b) {
        flagStatus = b;
    }

    public Boolean getFlagStatus() {
        return flagStatus;
    }

    public void setBomb(boolean b) {
        isBomb = b;
    }

    public Boolean getIsBomb() {
        return isBomb;
    }

    public void setIsExpanded(Boolean b) {
        isExpanded = b;
    }

    public Boolean getIsExpanded() {
        return isExpanded;
    }

    public int getXCoordinate() {
        return rowPosition;
    }

    public void setXCoordinate(int x) {
        rowPosition = x;
    }

    public int getYCoordinate() {
        return columnPosition;
    }

    public void setYCoordinate(int y) {
        rowPosition = y;
    }

    public void addBombToCount() {
        bombCount++;
    }

    /**
     * Next method is true if bombCount is greater than zero. What this means is that, assuming
     * scanNeighbor has been called on the view at hand and bombCount is the number of surrounding
     * views whose isBomb value is true, the tile should display the number of bombs that surround it.
     * Therefore, if this returns true, then (by use of another method) the background of this tile
     * should be changed to a picture that displays the value of bombCount.
     */
    public Boolean isCautionTile() {
        return (bombCount > 0);
    }

    public void expandTile(View v, int drawable){
        v.setBackgroundResource(drawable);
    }

    /**
     * Not a setter for field variable hasFlag. Adds flags to views on hold length of 0.5 seconds.
     */
    public void setFlag(){
        // onLongClickListener
    }
}
