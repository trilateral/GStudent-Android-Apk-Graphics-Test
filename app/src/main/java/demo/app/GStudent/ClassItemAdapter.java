package demo.app.GStudent;

import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;

import java.util.ArrayList;

/**
 * Created by Sidney on 21/12/2016.
 */
public class ClassItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final LayoutInflater layoutInflator;
    ArrayList<ClassItem> data = new ArrayList<>();
    private final ClassListFragment parentFragment;
    private final Context parentContext;

    private PopupWindow addClassPopup;
    private View layout;

    public ClassItemAdapter(Context context, ClassListFragment parent, final ArrayList<ClassItem> data){
        layoutInflator = LayoutInflater.from(context);
        parentContext = context;
        this.parentFragment = parent;
        this.data = data;

        //We need to get the instance of the LayoutInflater, use the context of this activity
        LayoutInflater inflater = (LayoutInflater) parent.getActivity()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //Inflate the view from a predefined XML layout
        layout = inflater.inflate(R.layout.addclasspopup,
                (ViewGroup) parent.getActivity().findViewById(R.id.addClassWrapper));

        //Compute window Size
        Display display = parent.getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width, height;
        width = (int)(size.x*0.8f);
        height= (int)(size.y*0.8f);
        if(width>1080)
            width = 1080;
        if(height>1920)
            width = 1920;
        Log.i("width", String.valueOf(width));

        // create a 300px width and 470px height PopupWindow
        addClassPopup = new PopupWindow(layout, width, height, true);
        //Add shadow
        addClassPopup.setElevation(10);
        addClassPopup.setBackgroundDrawable(new ColorDrawable(parent.getResources().getColor(R.color.colorPrimaryDark)));
        //Add Listeners to popup
        View cancelButton = (View) layout.findViewById(R.id.addClassCancel);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addClassPopup.dismiss();
            }
        });

        FloatingActionButton ok = (FloatingActionButton) layout.findViewById(R.id.addClassOK);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newID = data.get(data.size()-1).classId + 1;
                String name = ((EditText) layout.findViewById(R.id.addClassName)).getText().toString();
                String desc = ((EditText) layout.findViewById(R.id.addClassDesc)).getText().toString();

                ClassItem newClassItem;
                newClassItem = new ClassItem(R.drawable.test, newID, name, desc);

                data.add(newClassItem);
                dataSaveAction();
                notifyDataSetChanged();
                addClassPopup.dismiss();
            }
        });
    }

    public void dataSaveAction(){
        ((GStudent)parentContext.getApplicationContext()).data = data;
        //((GStudent)parentContext.getApplicationContext()).saveData();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        RecyclerView.ViewHolder holder;
        switch(viewType){
            case 0: view = layoutInflator.inflate(R.layout.classitem, parent, false);
                    holder = new ClassItemHolder(view, parentFragment);
                    break;
            case 1: view = layoutInflator.inflate(R.layout.additem, parent, false);
                    holder = new AddItemHolder(view);
                    break;
            default: view = layoutInflator.inflate(R.layout.classitem, parent, false);
                    holder = new ClassItemHolder(view, parentFragment);
                    break;
        }

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        switch (holder.getItemViewType()) {
            case 1://Add Button
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override public void onClick(View v) {
                        try {

                            // display the popup in the center
                            addClassPopup.showAtLocation(layout, Gravity.CENTER, 0, 0);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                break;
            case 0://Class Tile
                 holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override public void onClick(View v) {
                        ((GStudent)parentContext.getApplicationContext()).selectedClass.set(data.get(position).classId);
                    }
                });
                ClassItemHolder tempHolder = (ClassItemHolder) holder;
                tempHolder.title.setText(data.get(position).className);
                tempHolder.image.setImageResource(data.get(position).background);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return data.size()+1;
    }
    @Override
    public int getItemViewType(int position) {
        if(position >= data.size()){
            return 1;
        }else{
            return 0;
        }
    }

}