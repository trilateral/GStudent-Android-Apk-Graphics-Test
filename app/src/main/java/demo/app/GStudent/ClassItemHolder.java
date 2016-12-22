package demo.app.GStudent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Sidney on 21/12/2016.
 */
public class ClassItemHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView image;
        public ClassListFragment parent;

        public ClassItemHolder(View itemView, ClassListFragment parent) {
            super(itemView);
            this.parent = parent;
            title = (TextView) itemView.findViewById(R.id.title);
            image = (ImageView) itemView.findViewById(R.id.image);
        }


}

