package com.app.speedsweeper.speedsweeper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class TileAdapter extends BaseAdapter {
    // Provides class with app environment.
    private Context context;

    // This is assigned to findValueByID(R.id.grid)
    GridView gridView;

    // Local variable that takes form of called ArrayList tiles
    ArrayList<Tile> views;

    /*
    Default constructor. Necessary for function. Second parameter can be any List.
     */
    public TileAdapter(Context c, ArrayList<Tile> views, GridView grid) {
        context = c;
        this.views = views;
        this.gridView = grid;
    }

    /*
    The next three methods can be called in order to check the values of the List variable param.
    Getter methods that do not produce results unless overriding to accommodate specific conditions.
     */
    public int getCount() {
        return views.size();
    }

    public View getItem(int index) {
        return null;
    }

    public long getItemId(int index) {
        return 0;
    }

    /**
     * This method is the one that does the inflating. Essentially, it chooses a layout to inflate,
     * then admits the addition of views to the layout, then inflates the updated layout. It is
     * necessary to do this in an adapter because GridView is an AdapterView; it arranges child
     * views into a grid.
     *
     *
     * @param position Not used.
     * @param convertView Begins as null, then assumes the value of the previous view.
     * @param viewGroup Method checks for local viewGroup to facilitate inflation.
     * @return View v ... The abstract hierarchical concatenation that is inflated.
     */
    public View getView(int position, View convertView, ViewGroup viewGroup) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view;

        if (convertView == null){

            view = inflater.inflate(R.layout.tile_child, gridView, false);

            for (int x = 0; x < views.size(); x++){
                Tile tile = (Tile) view.findViewById(R.id.tile);
                tile.setIndex(x);
            }

            return view;
        } else {
            return convertView;
        }
    }
}
