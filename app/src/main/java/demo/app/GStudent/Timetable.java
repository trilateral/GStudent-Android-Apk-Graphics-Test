package demo.app.GStudent;

import android.support.v7.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Sidne on 22/12/2016.
 */
public class Timetable {
    public HashMap<Integer, ArrayList<RecyclerView.ViewHolder>> days = new HashMap<>();

    public Timetable(){

    }

    public void put(ArrayList<RecyclerView.ViewHolder> classes, int day){
        days.put(day, classes);
    }

    public ArrayList<RecyclerView.ViewHolder> get(int day){
        return days.get(day);
    }
}

