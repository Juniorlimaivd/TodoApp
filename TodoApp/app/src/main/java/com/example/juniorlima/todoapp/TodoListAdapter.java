package com.example.juniorlima.todoapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class TodoListAdapter extends BaseAdapter {
    Context context;
    ArrayList<TodoData> content;

    public TodoListAdapter(Context context, ArrayList<TodoData> content) {
        this.context = context;
        this.content = content;
    }

    @Override
    public int getCount() {
        return content.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(this.context);
        View row = inflater.inflate(R.layout.listrow_main, parent, false);

        TextView titleTextView = (TextView) row.findViewById(R.id.title_textView);
        TextView userIdTextView = (TextView) row.findViewById(R.id.userId_textView);
        TextView idTextView = (TextView) row.findViewById(R.id.id_textView);
        ImageView completedImageView = (ImageView) row.findViewById(R.id.completed_imageView);

        TodoData item = this.content.get(position);

        titleTextView.setText(item.getTitle());
        userIdTextView.setText("User ID: ".concat(String.valueOf(item.getUserId())));
        idTextView.setText(String.valueOf(item.getId()));

        if (!item.getCompleted()) {
            completedImageView.setVisibility(View.INVISIBLE);
        }

        return row;
    }
}
