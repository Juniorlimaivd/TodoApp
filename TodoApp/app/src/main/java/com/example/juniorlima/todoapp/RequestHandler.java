package com.example.juniorlima.todoapp;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class RequestHandler {

    private static String requestURL = "https://jsonplaceholder.typicode.com/todos/";

    public RequestHandler() {

    }

    /**
     * Uses the make async task to do a GET in the url
     * @return
     */
    public JSONArray makeRequest() {
        try {
            String response =  (new RequestTask()).execute(new String[]{requestURL}).get();

            return new JSONArray(response);
        } catch (Exception e) {
            String veryLongString = e.getMessage();
            int maxLogSize = 1000;
            for(int i = 0; i <= veryLongString.length() / maxLogSize; i++) {
                int start = i * maxLogSize;
                int end = (i+1) * maxLogSize;
                end = end > veryLongString.length() ? veryLongString.length() : end;
                Log.e("HTTPHandler", veryLongString.substring(start, end));
            }
            return null;
        }
    }

    private class RequestTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {

            String response = null;
            try {
                URL url = new URL(strings[0]);

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                InputStream in = new BufferedInputStream(conn.getInputStream());
                response = readStream(in);
            } catch (Exception e) {
                Log.e("RequestTask", "Exception: " + e.getMessage());
            }

            return response;
        }

        /**
         * Extract all data in the stream and returns as a string
         * @param inputStream the input stream
         * @return the resulted string
         */
        private String readStream(InputStream inputStream) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder sb = new StringBuilder();

            String line;
            try {
                while ((line = reader.readLine()) != null) {
                    sb.append(line).append('\n');
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    inputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            return sb.toString();
        }
    }
}


