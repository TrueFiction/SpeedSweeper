package com.app.speedsweeper.speedsweeper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;

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
        // LayoutInflater is necessary; GridView becomes new Layout.
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // Variable below must be called before conditional.
        View view;

        /*
        Condition below is true the first time the adapter is constructed. After that, convertView can be
        assigned to null again, for instance on the occurrence of a new game action event.
         */
        if (convertView == null){
            /*
            Intelligently, the view becomes a variable that both accumulates value and performs
            calculations on the views being inflated.
             */
            view = inflater.inflate(R.layout.tile_child, gridView, false);
            view = view.findViewById(R.id.linear_layout);

           /*
           This is where the error occurs. view.findViewByID(x) is null because the Tile class has
           not been properly constructed as a Button class.
            */
            for (int x = 0; x < views.size(); x++){
                Tile tile = (Tile) view.findViewById(x);
            }

            /*
            More code is needed here. After running it once, an error occurred that had to do with
            either the inflater or the adapter not being flushed or closed. The return values are
            set as they are in order to run through this program only once, so as to confirm its
            functionality
             */

            return view;
        } else {
            return convertView;
        }
    }
}
