package demo.app.GStudent;

import java.util.Calendar;

/**
 * Created by Sidne on 21/12/2016.
 */
public class SelectedDay {
    private int selectedDay = 0;
    private ChangeListener listener;

    public SelectedDay() {
        //Get day of the Week
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);

        //Convert to Integer format
        switch (day) {

            case Calendar.MONDAY:
               selectedDay = 0; break;
            case Calendar.TUESDAY:
                selectedDay = 1; break;
            case Calendar.WEDNESDAY:
                selectedDay = 2; break;
            case Calendar.THURSDAY:
                selectedDay = 3; break;
            case Calendar.FRIDAY:
                selectedDay = 4; break;
            case Calendar.SATURDAY:
                selectedDay = 5; break;
            case Calendar.SUNDAY:
                selectedDay = 6; break;
            default:
                selectedDay = 0; break;
        }
    }

    public int get() {
        if(selectedDay < 0)
            selectedDay = 0;
        return selectedDay;
    }

    public void set(int input) {
        this.selectedDay = input;
        if (listener != null) listener.onChange();
    }

    public void dayGoBack() {
        selectedDay--;
        if(selectedDay < 0)
            selectedDay = 6;
        if (listener != null) listener.onChange();
    }

    public void dayGoForward() {
        selectedDay++;
        if(selectedDay > 6)
            selectedDay = 0;
        if (listener != null) listener.onChange();
    }

    public ChangeListener getListener() {
        return listener;
    }

    public void setListener(ChangeListener listener) {
        this.listener = listener;
    }

    public interface ChangeListener {
        void onChange();
    }
}