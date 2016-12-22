package demo.app.GStudent;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Sidney on 21/12/2016.
 */
public class TimetableItemAdaptor extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final LayoutInflater layoutInflator;
    Timetable timetable;
    boolean tabletMode;
    private final DayplanFragment parentFragment;
    private final Context parentContext;

    public TimetableItemAdaptor(Context context, DayplanFragment parent){
        layoutInflator = LayoutInflater.from(context);
        parentContext = context;
        this.parentFragment = parent;

        ArrayList<RecyclerView.ViewHolder> Monday = new ArrayList<>();
        Monday.add(new Timeslot(new View(context), 0, "Homegroup", "", 0, 8, 45, 45));
        Monday.add(new Timeslot(new View(context), 0, "English", "The Boy in the Striped Pyjamas Workbook A pdf\n" +
                "Introductory Letter\n" +
                "Poetry Assignment\n" +
                "Free Choice Novel Assignment\n" +
                "OCTOBER SKY FINAL assignmentdocx\n" +
                "OCTOBER SKY summary", 0, 9, 30, 45));
        Monday.add(new Timeslot(new View(context), 1, "Math", "Check mathsonline", 0, 10, 15, 45));
        Monday.add(new Timeslot(new View(context), 2, "Science", "", 0, 11, 00, 45));
        Monday.add(new Timeslot(new View(context), 0, "German", "", 0, 11, 45, 45));

        ((GStudent)context.getApplicationContext()).timetable.put(Monday, 0);

        ArrayList<RecyclerView.ViewHolder> Tuesday = new ArrayList<>();
        Tuesday.add(new Timeslot(new View(context), 0, "English", "", 0, 9, 30, 45));
        Tuesday.add(new Timeslot(new View(context), 1, "Math", "", 0, 10, 15, 45));
        Tuesday.add(new Timeslot(new View(context), 2, "Science", "", 0, 11, 00, 45));
        Tuesday.add(new Timeslot(new View(context), 0, "Some Tuesday Stuff", "", 0, 11, 45, 45));

        ((GStudent)context.getApplicationContext()).timetable.put(Tuesday, 1);

        ArrayList<RecyclerView.ViewHolder> Wednesday = new ArrayList<>();
        //Wednesday.add(new Timeslot(new View(context), 0, "Homegroup", "", 0, 9, 30, 45));
        //Wednesday.add(new Timeslot(new View(context), 0, "Math", "", 0, 10, 15, 45));
        //Wednesday.add(new Timeslot(new View(context), 0, "Science", "", 0, 11, 00, 45));
        //Wednesday.add(new Timeslot(new View(context), 0, "Some Wednesday Stuff", "", 0, 11, 45, 45));

        ((GStudent)context.getApplicationContext()).timetable.put(Wednesday, 2);

        ArrayList<RecyclerView.ViewHolder> Thursday = new ArrayList<>();
        Thursday.add(new Timeslot(new View(context), 0, "English", "", 0, 9, 30, 45));
        Thursday.add(new Timeslot(new View(context), 0, "Math", "", 0, 10, 15, 45));
        Thursday.add(new Timeslot(new View(context), 0, "Science", "", 0, 11, 00, 45));
        Thursday.add(new Timeslot(new View(context), 0, "Some Thursday Stuff", "", 0, 11, 45, 45));

        ((GStudent)context.getApplicationContext()).timetable.put(Thursday, 3);

        ArrayList<RecyclerView.ViewHolder> Friday = new ArrayList<>();
        Friday.add(new Timeslot(new View(context), 0, "Homegroup", "", 0, 8, 45, 45));
        Friday.add(new Timeslot(new View(context), 0, "English", "", 0, 9, 30, 45));
        Friday.add(new Timeslot(new View(context), 0, "Math", "", 0, 10, 15, 45));
        Friday.add(new Timeslot(new View(context), 0, "Science", "", 0, 11, 00, 45));
        Friday.add(new Timeslot(new View(context), 0, "Some Friday Stuff", "", 0, 11, 45, 45));

        ((GStudent)context.getApplicationContext()).timetable.put(Friday, 4);

        ArrayList<RecyclerView.ViewHolder> Saturday = new ArrayList<>();

        if(Saturday.size()<1){
            Saturday.add(new Freeday(new View(context)));
        }

        ((GStudent)context.getApplicationContext()).timetable.put(Saturday, 5);

        ArrayList<RecyclerView.ViewHolder> Sunday = new ArrayList<>();

        if(Sunday.size()<1){
            Sunday.add(new Freeday(new View(context)));
        }

        ((GStudent)context.getApplicationContext()).timetable.put(Sunday, 6);

        timetable = ((GStudent)context.getApplicationContext()).timetable;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder;

        if(viewType == 0)
            holder = new Timeslot(layoutInflator.inflate(R.layout.timetableitem, parent, false));
        else if(viewType == 1)
            holder = new Freeday(layoutInflator.inflate(R.layout.freeday, parent, false));
        else
            holder = new TimetableError(layoutInflator.inflate(R.layout.timetableerror, parent, false));

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if(holder instanceof Timeslot){
            final Timeslot temp;
            temp = (Timeslot) timetable.get(((GStudent)parentContext.getApplicationContext()).selectedDay.get()).get(position);

            //Alternating Background Color
            if ((position % 2)>0)
                ((RelativeLayout) holder.itemView.findViewById(R.id.timetableItemWrapper)).setBackgroundColor(Color.parseColor("#E9E9E9"));

            //Class Name
            ((TextView) holder.itemView.findViewById(R.id.timetableItemClass)).setText(temp.classname);
            //Homework Feild
            ((TextView) holder.itemView.findViewById(R.id.timetableItemHomework)).setText(temp.homework);
            //Time
            formatTime(temp, holder);

            //ClickListeners
            if(tabletMode){
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ((GStudent)parentContext.getApplicationContext()).selectedClass.set(temp.classid);
                    }
                });
            }else{
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ((GStudent)parentContext.getApplicationContext()).selectedClass.set(temp.classid);
                        parentContext.getApplicationContext().startActivity(new Intent(parentContext, LessonDetail.class));
                    }
                });
            }
        }
        if(holder instanceof Freeday){


            TextView Message = (TextView) holder.itemView.findViewById(R.id.dayplanFreedayMessage);
            switch((int)(Math.random()*5)){
                case 0: Message.setText("Enjoy some time off! :)"); break;
                case 1: Message.setText("Nothing to do!"); break;
                case 2: Message.setText("Break time!"); break;
                case 3: Message.setText("Take a break"); break;
                case 4: Message.setText("Extra time for sleep"); break;
            }

            ImageView Backward = (ImageView) holder.itemView.findViewById(R.id.dayplanFreedayBack);
            ImageView Forward = (ImageView) holder.itemView.findViewById(R.id.dayplanFreedayForward);
            Backward.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((GStudent) parentContext.getApplicationContext()).selectedDay.dayGoBack();
                    notifyDataSetChanged();
                }
            });
            Forward.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((GStudent) parentContext.getApplicationContext()).selectedDay.dayGoForward();
                    notifyDataSetChanged();
                }
            });
        }
        if(holder instanceof TimetableError){
            ((Button)holder.itemView.findViewById(R.id.dayplanErrorRetry)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    notifyDataSetChanged();
                }
            });
        }
    }

    private void formatTime(Timeslot temp, RecyclerView.ViewHolder holder){
        String min1, min2;
        if (temp.startMinute < 10)
            min1 = "0" + temp.startMinute;
        else
            min1 = String.valueOf(temp.startMinute);

        if (temp.endMinute < 10)
            min2 = "0" + temp.endMinute;
        else
            min2 = String.valueOf(temp.endMinute);

        String hour1, ampm1, hour2, ampm2 = "";
        ampm1 = "AM";
        ampm2 = "AM";
        if (temp.startHour > 12) {
            hour1 = String.valueOf(temp.startHour - 12);
            ampm1 = "PM";
        } else {
            hour1 = String.valueOf(temp.startHour);
        }
        if (temp.endHour > 12){
            hour2 = String.valueOf(temp.endHour - 12);
            ampm2 = "PM";
        }else{
            hour2 = String.valueOf(temp.endHour);
        }

        ((TextView) holder.itemView.findViewById(R.id.timetableItemTime)).setText((hour1+":"+min1+" "+ampm1+" TO "+hour2+":"+min2+" "+ampm2));

    }

    @Override
    public int getItemCount() {
        int i = timetable.get(((GStudent)parentContext.getApplicationContext()).selectedDay.get()).size();
        if(i<1) //To show error and not crash
            i = 1;
        return i;
    }
    @Override
    public int getItemViewType(int position) {
        if(timetable.get(((GStudent)parentContext.getApplicationContext()).selectedDay.get()).size()<1)
            return 2; //Error
        if (timetable.get(((GStudent)parentContext.getApplicationContext()).selectedDay.get()).get(position) instanceof Freeday)
            return 1;
        return 0;
    }

}