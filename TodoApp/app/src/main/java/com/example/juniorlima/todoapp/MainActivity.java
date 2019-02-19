package com.example.juniorlima.todoapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.setup();

    }

    /**
     * Configure UI and wake up presenter
     */
    private void setup(){
        getSupportActionBar().hide();

        this.presenter = new MainPresenter(this);
        presenter.onCreate();
    }

    /**
     * Populate listView with the todos content
     * @param todos list of todoData
     */
    public void populateListView(ArrayList<TodoData> todos) {
        ListView listView = findViewById(R.id.todo_listview);

        TodoListAdapter adapter = new TodoListAdapter(this, todos);
        listView.setAdapter(adapter);
    }

    /**
     * Go to the SearchResultActivity
     * @param filteredTodos todo list that should be presented in SearchResultActivity
     */
    public void navigateToSearchResult(ArrayList<TodoData> filteredTodos){
        Intent intent = new Intent(getApplicationContext(), SearchResultActivity.class);
        intent.putExtra("todos", filteredTodos);
        startActivity(intent);
    }

    /**
     * Triggers everytime the searchButton is clicked
     * @param view Clicked view
     */
    public void searchButtonClicked(View view) {
        EditText searchTextView = findViewById(R.id.searchEditText);
        final String filter = searchTextView.getText().toString();
        presenter.onSearchClicked(filter);
    }
}
