package com.example.juniorlima.todoapp;

import java.util.ArrayList;

public class SearchResultPresenter {
    SearchResultActivity view;
    ArrayList<TodoData> todos;

    SearchResultPresenter(SearchResultActivity view, ArrayList<TodoData> todos) {
        this.view = view;
        this.todos = todos;
    }

    /**
     * First method to be called when wakes up
     */
    public void onCreate() {
        this.view.populateListView(this.todos);
    }
}
