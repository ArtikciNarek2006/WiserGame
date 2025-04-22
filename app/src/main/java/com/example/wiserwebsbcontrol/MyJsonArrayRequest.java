package com.example.wiserwebsbcontrol;

import android.content.Context;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;

public class MyJsonArrayRequest extends JsonArrayRequest {

    MainActivity mainActivity;
    Context ctx;
    LinearLayout parent_view;
    public MyJsonArrayRequest(String url, Response.Listener<JSONArray> listener, @Nullable Response.ErrorListener errorListener) {
        super(url, listener, errorListener);
    }

    public MyJsonArrayRequest(MainActivity activity, int method, String url, @Nullable JSONArray jsonRequest, Response.Listener<JSONArray> listener, @Nullable Response.ErrorListener errorListener) {
        super(method, url, jsonRequest, listener, errorListener);
        mainActivity = activity;
        ctx = mainActivity;
        parent_view = mainActivity.container;
    }
}
