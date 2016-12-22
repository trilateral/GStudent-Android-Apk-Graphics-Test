package demo.app.GStudent;

import android.app.Application;

import java.util.ArrayList;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class GStudent extends Application {

    ArrayList<ClassItem> data = new ArrayList<>();
    public Timetable timetable = new Timetable();
    public SelectedDay selectedDay;
    public static CurrentClass selectedClass = new CurrentClass();

    public ArrayList<ClassItem> getData(){
        return data;
    }

    public void setData(ArrayList<ClassItem> data){
        this.data = data;
    }

}