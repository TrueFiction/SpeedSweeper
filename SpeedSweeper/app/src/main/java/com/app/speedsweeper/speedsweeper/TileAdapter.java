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

    // GridView needs to be casted because
    GridView gridView;

    //
    ArrayList<Tile> views;

    public TileAdapter(Context c, ArrayList<Tile> views, GridView gridView) {
        this.context = c;
        this.views = views;
        this.gridView = gridView;
    }

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
     * @param position
     * @param convertView
     * @param viewGroup
     * @return
     */
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view;

        if (convertView == null){
            view = new View(context);
            view = inflater.inflate(R.layout.tile_child, gridView, false);

            LinearLayout layout = (LinearLayout) view.findViewById(R.id.linear_layout);

            for (int x = 0; x < views.size(); x++){
                Tile tile = (Tile) view.findViewById(x);
            }

            view = view.findViewById(R.id.grid);
            return view;
        } else {
            return convertView;
        }
    }
}
