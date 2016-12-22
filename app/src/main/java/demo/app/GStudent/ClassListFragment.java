package demo.app.GStudent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Sidney on 21/12/2016.
 */
public class ClassListFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";

        private static ClassItemAdapter adapter;
        public static RecyclerView recyclerView;
        private static Context parentContext;

        public static TextView title;
        public static TextView desc;

        public static ImageView details;

        public ClassListFragment() {

        }

        /**
         * Returns a new instance of this fragment for the given section number
         */
        public static ClassListFragment newInstance(int sectionNumber, Context in_parentContext) {
            ClassListFragment fragment = new ClassListFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            parentContext = in_parentContext;
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_central, container, false);


            //Add Class Tiles
            recyclerView = (RecyclerView) rootView.findViewById(R.id.classList);
            ((GStudent) getContext().getApplicationContext()).setData(getData());
            ArrayList<ClassItem> data = ((GStudent) getContext().getApplicationContext()).getData();
            adapter=new ClassItemAdapter(parentContext, this, data);
            recyclerView.setAdapter(adapter);

            recyclerView.setLayoutManager(new LinearLayoutManager(parentContext,  LinearLayoutManager.HORIZONTAL, false));

            //Add Description Event
            title = (TextView) rootView.findViewById(R.id.name);
            desc = (TextView) rootView.findViewById(R.id.desc);

            title.setText(data.get(0).className);
            desc.setText (data.get(0).description);
            ((GStudent)getContext().getApplicationContext()).selectedClass = new CurrentClass();
            ((GStudent)getContext().getApplicationContext()).selectedClass.setListener(new CurrentClass.ChangeListener() {
                @Override
                public void onChange() {
                    ArrayList<ClassItem> data = ((GStudent) getContext().getApplicationContext()).getData();
                    int i = ((GStudent)getContext().getApplicationContext()).selectedClass.get();
                    title.setText(data.get(i).className);
                    desc.setText (data.get(i).description);
                }
            });

            details = (ImageView) rootView.findViewById(R.id.detailkey);
            details.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getContext().startActivity(new Intent(getContext(), LessonDetail.class));
                }
            });

            return rootView;
        }

        public static ArrayList<ClassItem> getData(){
            ArrayList<ClassItem> data = new ArrayList<>();
            //Example Set
            ClassItem temp;
            temp = new ClassItem(R.drawable.test, 0, "English", "This is space to ass some sort of description or actions for this English class.");
            data.add(temp);
            temp = new ClassItem(R.drawable.test, 1, "Math",
                    "MATHEMATICS\n" +
                    "/maθ(ə)ˈmatɪks/\n" +
                    "noun\n" +
                    "the abstract science of number, quantity, and space, either as abstract concepts ( pure mathematics ), or as applied to other disciplines such as physics and engineering ( applied mathematics ).\n" +
                    "\"a taste for mathematics\"");
            data.add(temp);
            temp = new ClassItem(R.drawable.test, 2, "Science");
            data.add(temp);
            return data;
        }

}
