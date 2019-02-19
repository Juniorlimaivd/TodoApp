package com.example.juniorlima.todoapp;

import org.json.JSONArray;

import java.util.ArrayList;

public class MainPresenter {
    MainActivity view;
    ArrayList<TodoData> todos;
    RequestHandler httpHandler;

    MainPresenter(MainActivity view) {
        this.view = view;
        this.httpHandler = new RequestHandler();
    }

    /**
     * First method to be called when awake
     */
    public void onCreate() {
        this.todos = this.fetchTodos();
        view.populateListView(this.todos);
    }


    /**
     * When the user click to search, it should go to another screen with the filtered todos
     * @param filter The user search key
     */
    public void onSearchClicked(String filter) {
        view.navigateToSearchResult(this.filterTodos(this.todos, filter));
    }

    /**
     * Uses the httpRequestHandler to get the todos from the API and parses them
     * @return The list of fetched todos
     */
    private ArrayList<TodoData> fetchTodos() {
        JSONArray result = this.httpHandler.makeRequest();
        return TodoData.fromJsonArray(result);
    }

    /**
     * Select the todoDatas that contains the filter in the title
     * @param todos initial list of todos
     * @param filter string that should be searched
     * @return filtered todos
     */
    private  ArrayList<TodoData> filterTodos(ArrayList<TodoData> todos, String filter) {
        ArrayList<TodoData> filteredTodos = new ArrayList<>();

        for (int i=0; i < todos.size(); i++) {
            TodoData item = todos.get(i);

            if (item.getTitle().contains(filter)) filteredTodos.add(item);
        }

        return filteredTodos;
    }
}
