package com.app.speedsweeper.speedsweeper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class TileAdapter extends BaseAdapter {
    // Provides class with app environment.
    private Context context;

    // Allows particular variables (maybe methods) to be called from our main method.
    GameActivity GA;

    // GridView needs to be casted because
    GridView gridView;

    //
    ArrayList<Tile> tiles;

    // If true, flag has been placed on tile.
    Boolean hasFlag;
    // If true, tile is a bomb.
    Boolean isBomb;
    // If true, tile has been expanded (displaying gray or a number).
    Boolean isExpanded;

    // Number of bombs around one tile. Held private in Tile class.
    int bombCount;
    // Y - value of tile on GridView Board. Held private in Tile class.
    int rowPosition;
    // X - value of tile on GridView Board.
    int columnPosition;
    // Position of tile on board (0 to total - 1)
    int orderPosition;

    /*
    The constructor below might be being set with the wrong variables. If I keep them how they are,
        then I will have to do all the processing within the inflater, which is extremely inefficient
        and, on further thought, will not work.
     */

    public TileAdapter(Context c, ArrayList<Tile> tiles, GridView gridView) {
        this.context = c;
        this.tiles = tiles;
        this.gridView = gridView;
    }

    public int getCount() {
        return 0;
    }

    public View getItem(int index) {
        return tiles.get(index);
    }

    public long getItemId(int index) {
        return tiles.get(index).getId();
    }

    /**
     * This method is the one that does the inflating. Essentially, it chooses a layout to inflate,
     * then admits the addition of views to the layout, then inflates the updated layout. It is
     * necessary to do this in an adapter because GridView is an AdapterView; it arranges child
     * views into a grid.
     *
     *
     * @param position
     * @param convertView
     * @param viewGroup
     * @return
     */
    public View getView(int position, View convertView, ViewGroup viewGroup) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ArrayList<Tile> gridTiles = new ArrayList<>();

        if (convertView == null){

            convertView = inflater.inflate(R.layout.tile_child, gridView, false);

            for (int x = 0; x < tiles.size(); x++){
                Tile tile = (Tile) convertView.findViewById(R.id.tile_);
                gridTiles.add(tile);
            }

            gridView = (GridView) convertView.findViewById(R.id.grid);

            return convertView;
        } else {
            return null;
        }
    }
}
