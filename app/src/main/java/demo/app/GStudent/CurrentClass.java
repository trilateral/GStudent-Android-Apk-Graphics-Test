package demo.app.GStudent;

/**
 * Created by Sidney on 21/12/2016.
 */
public class CurrentClass {
    private int selectedClass = 1;
    private ChangeListener listener;
    private ChangeListener altlistener;

    public CurrentClass() {

    }

    public int get() {
        return selectedClass;
    }

    public void set(int input) {
        this.selectedClass = input;
        if (listener != null) listener.onChange();
    }

    public ChangeListener getListener() {
        return listener;
    }

    public void setListener(ChangeListener listener) {
        this.listener = listener;
    }

    public void setAltListener(ChangeListener listener) {
        this.altlistener = listener;
    }

    public interface ChangeListener {
        void onChange();
    }
}