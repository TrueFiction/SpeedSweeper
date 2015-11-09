package com.app.speedsweeper.speedsweeper;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import java.lang.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    private int index;
    // Height of tile in pixels.
    private int layout_height;
    // Width of tile in pixels.
    private int layout_width;


    public Tile (Context context){
        super(context);
    }

    public Tile (Context context, AttributeSet attrs) {
        super(context);
/*        this.measure(x, y);
        this.index = index;
        this.setBackgroundResource(R.drawable.temp_pic2);*/
        //this.findViewById(R.id.tile);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        layout_height = heightMeasureSpec;
        layout_width = widthMeasureSpec;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int position) {
        index = position;
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

    /*
    I want this to populate as quick as possible so that I can allocate Tiles to the IDs.
     */

    public void populateIDs() {
        String[] array = getResources().getStringArray(R.array.IDs);
        System.out.println("--array.length--" + array.length);
        List<String> list = new ArrayList<String>();
        list = Arrays.asList(array);
        ArrayList<String> arrayList = new ArrayList<String>(list);
        arrayList.add("TTS");
        array = arrayList.toArray(new String[list.size()]);
    }
}
