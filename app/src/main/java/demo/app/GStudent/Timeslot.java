package demo.app.GStudent;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Sidne on 22/12/2016.
 */
public class Timeslot extends RecyclerView.ViewHolder{
    int classid = 0;
    String classname = "Error";
    String homework = "Error";

    int day = 0; //Out of 7

    int startHour = 8;
    int startMinute = 45;

    int endHour = 8;
    int endMinute = 45;

    public Timeslot(View itemView){
        super(itemView);
    }

    public Timeslot(View itemView, int Classid, String Classname, String Homework, int day, int startHour, int startMinute, int minutes){
        super(itemView);
        this.classid = Classid;
        this.classname = Classname;
        this.homework = Homework;
        this.day = day;
        this.startHour = startHour;
        this.startMinute = startMinute;
        this.endHour = startHour + ((startMinute+minutes)/60);
        this.endMinute = (startMinute+minutes)%60;
    }
}
