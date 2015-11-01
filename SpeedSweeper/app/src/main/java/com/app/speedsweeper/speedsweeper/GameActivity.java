package com.app.speedsweeper.speedsweeper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.lang.*;

public class GameActivity extends AppCompatActivity {

    //Variables

    ArrayList<View> maid = new ArrayList<>(),  tileAL, result, viewArrayList;
    View bb, child;
    ViewGroup viewGroup;
    Runnable r;
    int i = 0;
    Boolean determine;

    /*
    Normal creation.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
    }
    /*
    Start involves setting up an AL that's easy to work with.
     */
    @Override
    public void onStart(){
        super.onStart();
    }
    /*
    populateList is a method that I want to use because it creates an
        effective data structure that would optimize the treatment of
        individual tiles.
    I am still in the process of making this work. Right now it has no use.
     */
    public ArrayList<View> populateList(View v){
        bb = v;
        viewGroup = (ViewGroup) findViewById(R.id.mine_field);
        determine = ((viewGroup.getChildCount() / 8) < i);
        child =  viewGroup.getChildAt(i);
        i++;

        //Not the best at threading and handling.

        r = new Runnable() {
            @Override
            public void run()  {
                viewGroup = (ViewGroup) findViewById(R.id.mine_field);
                determine = ((viewGroup.getChildCount() / 8) < i);
                child = viewGroup.getChildAt(i);
                i++;
                while (!determine) {
                    try {
                        executables();
                    } catch (Exception e) {
                    }
                    if (determine) {
                        break;
                    }
                }
                try {
                    if (Thread.interrupted()) {
                        throw new InterruptedException();
                    }
                    Thread.sleep(5000);
                } catch (InterruptedException ex) {}
                while (!determine) {
                    try {
                        executables();
                    } catch (Exception e){}
                }
                result.addAll(viewArrayList);
            }
        };
        return result;
    }
    /*
    This next method is where the program is working. I have a
        conditional test in there but just to test its reliability.
     */
    public void registerClick(View b){
        Boolean testOne = false;
        if (maid.size() == 0)
            maid.add(b);
        for (View view : maid) {
            if (view == b || maid.size() == 0) {
                break;
            } else {
                maid.add(b);
                testOne = true;
                break;
            }
        }
        if (testOne) {
            processClick(b);
        }
    }
    /*
    Ideally I would use many methods like this to allow the
        computer to manage all possible outcomes.
     */
    public void processClick(View v) {
        v.setBackgroundResource(R.drawable.pic_3);
    }
    /*
    Scrap code that is being used for the threaded method.
     */
    public void executables(){
        viewArrayList.add(bb);
        viewArrayList.addAll(populateList(child));
    }
}