package com.example.juniorlima.todoapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class TodoData implements Serializable {

    private int userId;
    private int id;
    private String title;
    private Boolean completed;

    public TodoData(int userId, int id, String title, Boolean completed) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.completed = completed;
    }

    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public static ArrayList<TodoData> fromJsonArray(JSONArray jsonArray) {
        ArrayList<TodoData> result = new ArrayList<TodoData>();
        try {
            for (int i=0; i < jsonArray.length(); i++) {
                JSONObject item = jsonArray.getJSONObject(i);

                String title = item.getString("title");
                int id = item.getInt("id");
                int userId = item.getInt("userId");
                Boolean completed = item.getBoolean("completed");

                result.add(new TodoData(userId, id, title, completed));
            }
            return result;
        } catch (Exception e) {
            Log.e("JsonParser", "Error parsing:" + e.getMessage());
        }
        return null;
    }

}
