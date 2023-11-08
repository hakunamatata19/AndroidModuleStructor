package android.widget;

import android.content.Context;
import android.util.AttributeSet;


public class MyListView extends  ListView {
    private ArrayAdapter<String> mAdapter;
    public MyListView(Context context) {
        super(context);
    }

    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyListView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        mAdapter.getViewTypeCount();
    }
}
