package com.example.juniorlima.todoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class SearchResultActivity extends AppCompatActivity {

    ArrayList<TodoData> todos;
    SearchResultPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);

        this.setup();

    }

    /**
     * Populate listView with the todos content
     * @param todos list of todos
     */
    public void populateListView(ArrayList<TodoData> todos) {
        ListView listView = findViewById(R.id.search_listview);
        TodoListAdapter adapter = new TodoListAdapter(this, todos);
        listView.setAdapter(adapter);
    }

    /**
     * Setup the UI and wakes up the presenter
     */
    private void setup(){
        getSupportActionBar().setTitle("Search Results");

        ArrayList<TodoData> todos = this.getTodos();
        presenter = new SearchResultPresenter(this, todos);
        presenter.onCreate();
    }

    /**
     * get Todos from the intent
     * @return the Todo list found
     */
    private ArrayList<TodoData> getTodos() {
        Intent intent = (Intent) getIntent();
        return  (ArrayList<TodoData>) intent.getSerializableExtra("todos");
    }
}
