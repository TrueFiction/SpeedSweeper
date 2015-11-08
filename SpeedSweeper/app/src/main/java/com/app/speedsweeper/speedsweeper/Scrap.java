/*


import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import org.xmlpull.v1.XmlPullParser;
import java.lang.*;
import java.util.ArrayList;
import java.util.Random;
import android.widget.GridView;
import android.widget.TextView;

//public class GameActivity extends AppCompatActivity {
    */
/*
    To do:
        Main menu
        High score
        Timer
        Proper graphics design

    **Old Variables kept for reference**
    ************************************
    View reset_button;
    ArrayList<View> views = new ArrayList<>(), flaggedTile = new ArrayList<>(), garbage = new ArrayList<>();
    ArrayList<Integer> originalIds = new ArrayList<>();
    Random r = new Random();
    Boolean gameInProgress = true;
    ************************************
    *//*

    // Declaration of class Board to call Board's public methods.
    //TileAdapter tp = new TileAdapter(ThisApplication.getAppContext());

    */
/**
     INFLATER MUST GO HERE
     **//*

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_parent);
        Board board = new Board(this, 8, 7, 7);
        //GridView startupBoard = (GridView) findViewById(R.id.grid);
        //startupBoard.setAdapter(new TileAdapter(this));
        //Board startupBoard = (Board)findViewById(R.id.grid);
        //Board startupBoard = new Board(this, 8, 7, 7);
        board.setRowCount(8);
        board.setColumnCount(7);

        ArrayList<Button> tileCollection = new ArrayList<>();
        LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        for (int x = 0; x < (board.getColumnCount()*board.getRowCount()); x++){
            tileCollection.add(new Button(getApplicationContext()));
        }
        ViewGroup insertPoint = (ViewGroup) findViewById(R.id.grid);
        int count = 0;
        for (int x = 0; x < board.getRowCount(); x++) {
            for (int y = 0; y < board.getColumnCount(); y++) {
                Button b = (Button) inflater.inflate(R.layout.tile_child, null);
                TextView textView = (TextView) b.findViewById(R.id.tile_);
                textView.layout();
                insertPoint.addView(temp, count);
                count++;
            }
        }
    }
    //board = new Board(this, 7, 8, 10);
    //reset_button = findViewById(R.id.reset_button);
    //reset_button.setClickable(false);
    //setContentView(R.layout.board);
        */
/*LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate((XmlPullParser) v, (ViewGroup) findViewById(R.id.parent_layout), true);*//*


    @Override
    public void onStart() {
        super.onStart();
    }

    */
/*
    initializeGrid calls three methods. grabChildren populates ArrayList<View> views with all the
    buttons already created in XML. saveID populates ArrayList<Integer> originalIds with each view's
    original name. plantBombs first selects 7 views to be bombs, then identifies all views surrounding
    the bombs, preparing them to display a number that represents how many bombs are in proximity.
     *//*

*/
/*
    public void initializeGrid() {
        //grabChildren();
        //saveId();
        board.plantBombs();
    }
*//*

    */
/*
    I am not at all proud of the following method, but at least after this, I have exactly what I
    want. Note that I used eclipse to generate the below code in String output.
    *//*

*/
/*
    public void grabChildren() {
        views.add(findViewById(R.id.tile_1));
        views.add(findViewById(R.id.tile_2));
        views.add(findViewById(R.id.tile_3));
        views.add(findViewById(R.id.tile_4));
        views.add(findViewById(R.id.tile_5));
        views.add(findViewById(R.id.tile_6));
        views.add(findViewById(R.id.tile_7));
        views.add(findViewById(R.id.tile_8));
        views.add(findViewById(R.id.tile_9));
        views.add(findViewById(R.id.tile_10));
        views.add(findViewById(R.id.tile_11));
        views.add(findViewById(R.id.tile_12));
        views.add(findViewById(R.id.tile_13));
        views.add(findViewById(R.id.tile_14));
        views.add(findViewById(R.id.tile_15));
        views.add(findViewById(R.id.tile_16));
        views.add(findViewById(R.id.tile_17));
        views.add(findViewById(R.id.tile_18));
        views.add(findViewById(R.id.tile_19));
        views.add(findViewById(R.id.tile_20));
        views.add(findViewById(R.id.tile_21));
        views.add(findViewById(R.id.tile_22));
        views.add(findViewById(R.id.tile_23));
        views.add(findViewById(R.id.tile_24));
        views.add(findViewById(R.id.tile_25));
        views.add(findViewById(R.id.tile_26));
        views.add(findViewById(R.id.tile_27));
        views.add(findViewById(R.id.tile_28));
        views.add(findViewById(R.id.tile_29));
        views.add(findViewById(R.id.tile_30));
        views.add(findViewById(R.id.tile_31));
        views.add(findViewById(R.id.tile_32));
        views.add(findViewById(R.id.tile_33));
        views.add(findViewById(R.id.tile_34));
        views.add(findViewById(R.id.tile_35));
        views.add(findViewById(R.id.tile_36));
        views.add(findViewById(R.id.tile_37));
        views.add(findViewById(R.id.tile_38));
        views.add(findViewById(R.id.tile_39));
        views.add(findViewById(R.id.tile_40));
        views.add(findViewById(R.id.tile_41));
        views.add(findViewById(R.id.tile_42));
        views.add(findViewById(R.id.tile_43));
        views.add(findViewById(R.id.tile_44));
        views.add(findViewById(R.id.tile_45));
        views.add(findViewById(R.id.tile_46));
        views.add(findViewById(R.id.tile_47));
        views.add(findViewById(R.id.tile_48));
        views.add(findViewById(R.id.tile_49));
        views.add(findViewById(R.id.tile_50));
        views.add(findViewById(R.id.tile_51));
        views.add(findViewById(R.id.tile_52));
        views.add(findViewById(R.id.tile_53));
        views.add(findViewById(R.id.tile_54));
        views.add(findViewById(R.id.tile_55));
        views.add(findViewById(R.id.tile_56));
    }
    *//*

*/
/*
    private void saveId() {
        for (int x = 0; x < views.size(); x++) {
            originalIds.add(views.get(x).getId());
        }
    }
*//*

    */
/*
    Plant bombs. Planting = true is on its way to becoming completely unnecessary, but I'll leave it
    for keepsake for now. A loop is created where seven random views are given the id that is equal
    to R.id.bomb in value. Later on, views are checked for the bomb ID and appropriate actions
    ensue.

    After their id has been set, another set of actions have to occur. For each tile selected, its
    immediate surroundings must be gathered and given the id equal to R.id.caution. All such views
    are the views that will display a number to inform the player how many bombs are in the focus
    tile's proximity.
     *//*

*/
/*
    public void plantBombs() {
        for (int x = 0; x < 7; x++) {
            View unluckyTile = views.get(r.nextInt(views.size()));
            unluckyTile.setId(R.id.bomb);
            scan(unluckyTile);
            for (int y = 0; y < flaggedTile.size(); y++) {
                if (flaggedTile.get(y).getId() != R.id.bomb) {
                    flaggedTile.get(y).setId(R.id.caution);
                }
            }
            flaggedTile.removeAll(flaggedTile);
        }
    }
*//*

    */
/*
    Then comes method registerClick. All tile buttons call this method onClick. It checks to see if
    the game is currently in the situation where a bomb has been clicked and the user has not reset
    the game by pressing the reset button. This situation is true if gameInProgress is false, and
    as can be seen below, if gameInProgress is false, registerClick performs no action. The result
    of this is that the user cannot click more views if a bomb tile has been clicked, that is until
    the reset_button is clicked.

    Then, checkTile is called with View v. If checkTile returns true, the game ends as endGame is
    called. checkTile is the beginning of a complicated evaluation of the tile.
     *//*


    public void registerClick(View v) {
       */
/* if (gameInProgress) {
            Boolean entry;
            entry = checkTile(v);
            if (entry) {
                endGame(v);
            }
        }*//*

    }

    */
/*
    In the following method, game data should be saved.

    As can be seen, as soon as this method is called, gameInProgress is set to false, affirming the
    result I explained above. Otherwise, this method is responsible for letting the user know they
    pressed a bomb, as well as revealing and enabling the reset_button.
    *//*

*/
/*
    public void endGame(View v) {
        //gameInProgress = false;
        v.setBackgroundResource(R.drawable.bomb_1);
        reset_button.setVisibility(View.VISIBLE);
        reset_button.setClickable(true);
    }
*//*

    */
/*
    checkTile simply checks the id of the tile. If the id equals the value of R.id.bomb, true is
    returned, resulting in endGame being called in registerClick. If the aforementioned equality is
    not true, then safeResult is called, a method that begins the complicated process of expanding
    views.
     *//*

*/
/*
    public Boolean checkTile(View v) {
        if (v.getId() == R.id.bomb) {
            return true;
        } else {
            safeResult(v);
            return false;
        }
    }
*//*

    */
/*
    safeResult is meant to act as an overseer of sorts. By this I mean that it should call methods
    below in a precise and efficient manner, yielding no errors. This is not what it is now. I am
    using a simple approach to test the methods below.
     *//*

*/
/*
    public void safeResult(View v) {
        */
/*
        NOTE!!!
        If you run the isolated method runOn(v) without the following for loop, the search works
        quite well. It now needs to be able to go beyond what it wants to.
         *//*

    */
/*
        runOn(v);
        if (flaggedTile.size() > 0) {
            runOn(flaggedTile.get(0));
        }
        *//*

    */
/*}


    */
/*
    I am now trying a different approach to implementing the tile expanding
      mechanism.

	USE al.clone();

	Once these views are added to flaggedTile, their ID needs to be checked.
	 If ID == R.id.caution, expand, stop.
	 If ID == bomb, stop
	 Else, expand, gameInProgress surroundings.
	Three different pathways that need to occur sequentially for all views.
	 First, all default id views need to expand.
	 Then, all caution views need to expand. Surroundings should NOT be checked for caution                 views.
	 Finally, all caution views and bomb views need to be added to the garbage, as the computer
	 should not perform redundant calculations.
	 Therefore, when there are already objects in garbage, flaggedTile should not receive
	 any views that are in the garbage.
	   Right now, flaggedTile does receive them, and then the sort method can be called to
	   correct the flaggedTile by checking the garbage.

	Tasks
	Create method addToGarbage
	Set rank priority for tile expansion
	Avoid cyclic processing
	 Create branches of activity direction.

	 CONSIDER USING A SWITCH STATEMENT FOR VARIOUS POSSIBILITIES

	 flaggedTiles must be flushed after every utilization.

	 Overall, does there really need to be a garbage AL?
    *//*


    //Boolean viewInGarbage = false;
    //int init = 0;
*/
/*
    public void runOn(View v) {

        if (flaggedTile.size() > 0 || init == 0) {
        */
/*
        First, the tile clicked has to call scan to gameInProgress the views surrounding the selected
        tile.
        *//*

    //scan(v);
    //sort(); This method has been problematic, mainly in terms of flaggedTile.size()
        */
/*
        Now, each tile in flaggedTile has to be checked for its id. Using a boolean value to
        gameInProgress to see if current view is already in garbage. Tiles are added to garbage if their id
        is caution or bomb. This is done for two reasons: to prevent the program from checking the
        views surrounding the tile in focus, and to save memory - accomplished by preventing the
        tile in focus from being checked for its id and whether it should be added to the garbage
        (oddly, by checking whether or not it is already in the garbage. the difference is that I
        want to have this checking mechanism occur immediately before and after ids are checked.)
         *//*

    */
/*
            int count = 0;
            for (View view : flaggedTile) {
                int id = view.getId();
                if (id == R.id.bomb) {
                */
/*
                If R.id.bomb is the case, then one is incremented to a count variable. This is done
                to determine which number the tile will display (the number representing the amount
                of bombs in the focus tile's proximity. This int variable must lie in the scope of
                runOn.
                 *//*

    */
/*
                    checkGarbage(view);
                    if (!viewInGarbage) {
                        garbage.add(view);
                    }
                    count++;
                }
            }*//*


            */
/*
            And now the count variable is accessed in a switch statement. This makes the tile display
            the number value given by count, which equals the number of bombs that are in proximity of
            the focus tile.
            *//*

    */
/*
            switch (count) {
                case 1:
                    if (v.getId() == R.id.caution) {
                        v.setBackgroundResource(R.drawable.detects_one);
                        v.setClickable(false);
                    }
                    break;
                case 2:
                    if (v.getId() == R.id.caution) {
                        v.setBackgroundResource(R.drawable.detects_two);
                        v.setClickable(false);
                    }
                    break;
                case 3:
                    if (v.getId() == R.id.caution) {
                        v.setBackgroundResource(R.drawable.detects_three);
                        v.setClickable(false);
                    }
                    break;
                default:
                    if (v.getId() != R.id.caution) {
                        v.setBackgroundResource(R.drawable.filler_tile);
                        v.setClickable(false);
                    }
            }
*//*

            */
/*
            I am going to bed now, or at least taking a break. The following method is the right
            idea but logically incorrect as it is changing the value of flaggedTile as it is going
            through the loop.
             *//*

            */
/*
            for (View view : flaggedTile) {
                int id = view.getId();
                if (id == R.id.caution) {
                    scan(view);
                    setDeterminantTile(view, count);
                } else if ((!view.getBackground().equals(getDrawable(R.drawable.detects_one))) &&
                        (!view.getBackground().equals(getDrawable(R.drawable.detects_two))) &&
                        (!view.getBackground().equals(getDrawable(R.drawable.detects_three)))) {
                    view.setBackgroundResource(R.drawable.filler_tile);
                    view.setClickable(false);
                }
                *//*

                    */
/*
                    if (id == R.id.caution) {
                        checkGarbage(view);
                        if (!viewInGarbage) {
                            garbage.add(view);
                        }
                    }

        }
        checkGarbage(v);
        if (!viewInGarbage) {
            garbage.add(v);
        }
        flaggedTile.remove(v);
        init++;
    }
*//*

    */
/*
    The fixed numbers below may look like a nightmare, but they are based off of views, an AL that
    serves as a virtual model for the buttons in XML. I confirm that the method below accurately
    adds all views that surround the focus tile to AL flaggedTile.
     *//*

*/
/*
    public void scan(View v) {
        int focusPosition = -1;
        for (int x = 0; x < views.size(); x++) {
            if (v.equals(views.get(x))) {
                focusPosition = x;
            }
        }
        flaggedTile.add(v);
        if (focusPosition <= 6) {
            if (focusPosition != 0 && focusPosition != 6) {
                flaggedTile.add(views.get(focusPosition + 1));
                flaggedTile.add(views.get(focusPosition - 1));
                flaggedTile.add(views.get(focusPosition + 6));
                flaggedTile.add(views.get(focusPosition + 7));
                flaggedTile.add(views.get(focusPosition + 8));
            } else if (focusPosition == 0) {
                flaggedTile.add(views.get(focusPosition + 1));
                flaggedTile.add(views.get(focusPosition + 7));
                flaggedTile.add(views.get(focusPosition + 8));
            } else if (focusPosition == 6) {
                flaggedTile.add(views.get(focusPosition - 1));
                flaggedTile.add(views.get(focusPosition + 6));
                flaggedTile.add(views.get(focusPosition + 7));
            }
        } else if (focusPosition >= 49) {
            if (focusPosition != 49 && focusPosition != 55) {
                flaggedTile.add(views.get(focusPosition + 1));
                flaggedTile.add(views.get(focusPosition - 1));
                flaggedTile.add(views.get(focusPosition - 6));
                flaggedTile.add(views.get(focusPosition - 7));
                flaggedTile.add(views.get(focusPosition - 8));
            } else if (focusPosition == 49) {
                flaggedTile.add(views.get(focusPosition + 1));
                flaggedTile.add(views.get(focusPosition - 7));
                flaggedTile.add(views.get(focusPosition - 8));
            } else if (focusPosition == 55) {
                flaggedTile.add(views.get(focusPosition - 1));
                flaggedTile.add(views.get(focusPosition - 6));
                flaggedTile.add(views.get(focusPosition - 7));
            }
        } else if (focusPosition < 49 && focusPosition > 6) {
            if (focusPosition % 7 == 0) {
                flaggedTile.add(views.get(focusPosition - 6));
                flaggedTile.add(views.get(focusPosition - 7));
                flaggedTile.add(views.get(focusPosition + 1));
                flaggedTile.add(views.get(focusPosition + 7));
                flaggedTile.add(views.get(focusPosition + 8));
            } else if (focusPosition == 13 || focusPosition == 20 || focusPosition == 27 || focusPosition == 34 || focusPosition == 41 || focusPosition == 48) {
                flaggedTile.add(views.get(focusPosition - 7));
                flaggedTile.add(views.get(focusPosition - 8));
                flaggedTile.add(views.get(focusPosition - 1));
                flaggedTile.add(views.get(focusPosition + 6));
                flaggedTile.add(views.get(focusPosition + 7));
            } else {
                flaggedTile.add(views.get(focusPosition + 1));
                flaggedTile.add(views.get(focusPosition - 1));
                flaggedTile.add(views.get(focusPosition - 6));
                flaggedTile.add(views.get(focusPosition - 7));
                flaggedTile.add(views.get(focusPosition - 8));
                flaggedTile.add(views.get(focusPosition + 6));
                flaggedTile.add(views.get(focusPosition + 7));
                flaggedTile.add(views.get(focusPosition + 8));
            }
        }
    }
*//*

    */
/*
    The method below simply checks to see if there are any views in AL flaggedTile that have already
    been checked. Yes, AL garbage is meant to be populated with views that have already been
    evaluated and expanded.
     *//*

*/
/*
    public void sort() {
        for (int x = 0; x < flaggedTile.size(); x++) {
            Boolean redundant = false;
            for (int y = 0; y < garbage.size(); y++) {
                if (flaggedTile.get(x).equals(garbage.get(y))) {
                    redundant = true;
                }
            }
            if (redundant) {
                flaggedTile.remove(x);
            }
        }
    }
}
*//*

    */
/*
    This method is rather haphazardly structured, but through demonstration you would find that it
    works perfectly. It could probably be optimized, but for now it is a good structure. The current
    focus is on the expansion of views. Nevertheless, resetGame is called when reset_button is
    clicked. It essentially reverses all attribute related transformations and quantity changes.
     *//*

*/
/*
    public void resetGame(View v) {
        for (int x = 0; x < views.size(); x++) {
            View Tile = views.get(x);
            Tile.setId(originalIds.get(x));
            Tile.setBackgroundResource(R.drawable.temp_pic2);
            Tile.setClickable(true);
        }
        garbage.removeAll(garbage);
        flaggedTile.removeAll(flaggedTile);
        plantBombs();
        reset_button.setVisibility(View.INVISIBLE);
        reset_button.setClickable(false);
        gameInProgress = true;
    }
*//*

    */
/*
    checkGarbage checks to see if a particular view is already held in garbage. This has two
    functions: to decide whether or not the view should be added to garbage, or to remove a view
    from flaggedTiles so that the view (which has been checked before, and is therefore in AL
    garbage) is not checked again.
     *//*

*/
/*
    public void checkGarbage(View v) {
        for (int x = 0; x < garbage.size(); x++) {
            if (v.equals(garbage.get(x))) {
                viewInGarbage = true;
                break;
            }
        }
    }
*//*

    */
/*
    This is a copy of the switch statement in runOn - an attempt to perform this action on every
    tile as each tile is accessed from AL flaggedTile
     *//*

*/
/*
    public void setDeterminantTile(View v, int count){
        switch (count)
        {
            case 1:
                if (v.getId() == R.id.caution) {
                    v.setBackgroundResource(R.drawable.detects_one);
                    v.setClickable(false);
                }
                break;
            case 2:
                if (v.getId() == R.id.caution) {
                    v.setBackgroundResource(R.drawable.detects_two);
                    v.setClickable(false);
                }
                break;
            case 3:
                if (v.getId() == R.id.caution) {
                    v.setBackgroundResource(R.drawable.detects_three);
                    v.setClickable(false);
                }
                break;
            default:
                if (v.getId() != R.id.caution) {
                    v.setBackgroundResource(R.drawable.filler_tile);
                    v.setClickable(false);
                }
        }
    }
    *//*

}

    */
/*
    A good, functional framework has been established, although many of the functions revolve
    around the focus tile. It is now time to either change the focus tile or call the functions
    on all views. I prefer to change the focus tile - I do not want anything to be recursive (I think).
    */
