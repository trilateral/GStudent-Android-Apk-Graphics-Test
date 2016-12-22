package demo.app.GStudent;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

public class LessonDetail extends AppCompatActivity {

    int selectedClass = 0;
    int maxClasses = 0;

    int zoomedHeight;

    TextView title;
    Toolbar toolbar;
    ImageView image;
    Context thisContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        thisContext = this.getApplicationContext();
        //getActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_lessondetail);

        LinearLayout view = (LinearLayout) this.findViewById(R.id.lessonDetail);
        toolbar = (Toolbar) this.findViewById(R.id.toolbar);
        final ScrollView scrollView = (ScrollView) this.findViewById(R.id.lessonDetailContent);
        title = (TextView) this.findViewById(R.id.lessonDetailTitle);
        image = (ImageView) this.findViewById(R.id.lessonDetailBackground);

        if(image.getWidth()>(view.getHeight()/2)){
            zoomedHeight=view.getHeight()/2;
        }else{
            zoomedHeight=image.getWidth();
        }
        image.setMinimumHeight(zoomedHeight);

        updateInfo();

        scrollView.setOnTouchListener(new SimpleSwipeListener(LessonDetail.this) {
            public void onSwipeTop() {
                if (scrollView.getScrollX() < 5)
                    image.setMinimumHeight(0);

            }

            public void onSwipeBottom() {
                image.setMinimumHeight(zoomedHeight);
            }

            public void onSwipeRight() {
                if(selectedClass > 0){
                    selectedClass--;
                    ((GStudent)getApplicationContext()).selectedClass.set(selectedClass);
                    updateInfo();
                }

            }
            public void onSwipeLeft() {
                if(selectedClass < maxClasses){
                    selectedClass++;
                    ((GStudent)getApplicationContext()).selectedClass.set(selectedClass);
                    updateInfo();
                }
            }

        });
        image.setOnTouchListener(new SimpleSwipeListener(LessonDetail.this) {
            public void onSwipeTop() {
                image.setMinimumHeight(0);
            }
            public void onSwipeBottom() {
                image.setMinimumHeight(image.getWidth());
            }

        });
    }

    public void updateInfo(){
        ArrayList<ClassItem> data = ((GStudent) getApplicationContext()).getData();
        maxClasses = data.size();
        if(maxClasses == 0){
            super.onBackPressed();
        }
        selectedClass = ((GStudent)getApplicationContext()).selectedClass.get();
        title.setText(data.get(selectedClass).className);
        ((AppCompatActivity)this).getSupportActionBar().setTitle(data.get(selectedClass).className +" Class");
        image.setImageResource(data.get(selectedClass).background);
    }
}
