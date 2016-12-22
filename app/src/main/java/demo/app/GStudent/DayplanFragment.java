package demo.app.GStudent;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class DayplanFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    private Context parentContext;
    public RecyclerView recyclerView;
    private TimetableItemAdaptor adapter;

    private boolean tabletMode = false;
    private RelativeLayout tabletPaddingObject;

    private ImageView Backward, Forward;

    public DayplanFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static DayplanFragment newInstance(int sectionNumber, Context parentContext) {
        ((GStudent)parentContext.getApplicationContext()).selectedDay = new SelectedDay();
        DayplanFragment fragment = new DayplanFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        fragment.parentContext = parentContext;
        return fragment;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if(tabletMode && newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){
            tabletPaddingObject.setPadding(0,96,0,0);
            Log.i("RotateEvent", "Landscape");
        }else if(tabletMode && newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            tabletPaddingObject.setPadding(0,0,0,0);
            Log.i("RotateEvent", "Profile");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView;

        Configuration config = getResources().getConfiguration();
        if (config.smallestScreenWidthDp >= 600)
        {
            rootView = inflater.inflate(R.layout.fragment_dayplan_tablet, container, false);
            tabletPaddingObject = (RelativeLayout) rootView.findViewById(R.id.dayplanTabletPadding);
            tabletMode = true;
        }
        else
        {
            rootView = inflater.inflate(R.layout.fragment_dayplan, container, false);
        }

        if(config.orientation == Configuration.ORIENTATION_PORTRAIT && tabletMode){
            tabletPaddingObject.setPadding(0,0,0,0);
        }

        recyclerView = (RecyclerView) rootView.findViewById(R.id.dayplanTimetable);
        adapter=new TimetableItemAdaptor(getContext(), this);
        adapter.tabletMode = tabletMode;
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        Backward = (ImageView) rootView.findViewById(R.id.dayplanBack);
        Forward = (ImageView) rootView.findViewById(R.id.dayplanForward);
        Backward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((GStudent) getContext().getApplicationContext()).selectedDay.dayGoBack();
                adapter.notifyDataSetChanged();
            }
        });
        Forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((GStudent) getContext().getApplicationContext()).selectedDay.dayGoForward();
                adapter.notifyDataSetChanged();
            }
        });

        if(tabletMode) {
            ((GStudent) getContext().getApplicationContext()).selectedClass.setAltListener(new CurrentClass.ChangeListener() {
                @Override
                public void onChange() {
                    ArrayList<ClassItem> data = ((GStudent) getContext().getApplicationContext()).getData();
                    int i = ((GStudent) getContext().getApplicationContext()).selectedClass.get();
                    ((TextView) rootView.findViewById(R.id.dayplanTabletTitle)).setText(data.get(i).className);
                }
            });
            ((GStudent) getContext().getApplicationContext()).selectedDay.setListener(new SelectedDay.ChangeListener() {
                @Override
                public void onChange() {
                    String days[] = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
                    String day = days[((GStudent) parentContext.getApplicationContext()).selectedDay.get()];
                    ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(day + "'s Dayplan");
                }
            });
        }

        return rootView;
    }

}
