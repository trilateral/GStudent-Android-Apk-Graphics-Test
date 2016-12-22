package demo.app.GStudent;

import android.graphics.drawable.Drawable;

/**
 * Created by Sidne on 21/12/2016.
 */
public class ClassItem {
    int background = R.mipmap.error;
    int classId = 0;
    String className = "Error";
    String description = "Error";

    public ClassItem(int drawableBackground, int classId, String className){
        this.classId = classId;
        this.background = drawableBackground;
        this.className = className;
        description = "N/A";
    }

    public ClassItem(int drawableBackground, int classId, String className, String description){
        this.classId = classId;
        this.background = drawableBackground;
        this.className = className;
        this.description = description;
    }
}
