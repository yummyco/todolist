package com.example.yummyco;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Adapter to bind a ToDoItem List to a view
 */
public class ToDoItemAdapter extends ArrayAdapter<ToDoItem> {

    /**
     * Adapter context
     */
    Context mContext;

    /**
     * Adapter View layout
     */
    int mLayoutResourceId;

    public ToDoItemAdapter(Context context, int layoutResourceId) {
        super(context, layoutResourceId);

        mContext = context;
        mLayoutResourceId = layoutResourceId;
    }

    /**
     * Returns the view for a specific item on the list
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;

        final ToDoItem currentItem = getItem(position);

        if (row == null) {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            row = inflater.inflate(mLayoutResourceId, parent, false);
        }

        row.setTag(currentItem);

        LinearLayout layout = (LinearLayout) row.findViewById(R.id.toDoItem);
        View newView = null;

        switch (currentItem.getType()) {
            case 0:
                newView = getCheckBox(currentItem);
                break;
            case 1:
                newView = getTextView(currentItem);
                break;
            default:
                newView = getCheckBox(currentItem);
                break;
        }
//        final CheckBox checkBox = new CheckBox(mContext);
//        checkBox.setText(currentItem.getText());
//        checkBox.setChecked(false);
//        checkBox.setEnabled(true);
//
//        checkBox.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View arg0) {
//                if (checkBox.isChecked()) {
//                    checkBox.setEnabled(false);
//                    if (mContext instanceof ToDoActivity) {
//                        ToDoActivity activity = (ToDoActivity) mContext;
//                        activity.checkItem(currentItem);
//                    }
//                }
//            }
//        });

        layout.removeAllViews();
        layout.addView(newView);

        return row;
    }

    private View getCheckBox(final ToDoItem currentItem) {
        final CheckBox checkBox = new CheckBox(mContext);
        checkBox.setText(currentItem.getText());
        checkBox.setChecked(false);
        checkBox.setEnabled(true);

        checkBox.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if (checkBox.isChecked()) {
                    checkBox.setEnabled(false);
                    if (mContext instanceof ToDoActivity) {
                        ToDoActivity activity = (ToDoActivity) mContext;
                        activity.checkItem(currentItem);
                    }
                }
            }
        });

        return checkBox;
    }

    private View getTextView(final ToDoItem currentItem) {
        final CheckBox checkBox = new CheckBox(mContext);
        checkBox.setText(currentItem.getText());
        checkBox.setChecked(false);
        checkBox.setEnabled(true);
        checkBox.setClickable(false);

        return  checkBox;
    }

}