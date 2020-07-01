package com.tutorial.novelproject.utils;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class ApiCaller {
    private final String baseUrl = "http://18.162.110.201";

    public ApiCaller() {}

    private void execute(JsonObjectRequest request, Context context) {
        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(request);
    }

    public void callRequest(int method, String url, JSONObject jsonRequest, Response.Listener successListener, Response.ErrorListener errorListener, Context context) {
        String fullUrl = baseUrl + url;
        JsonObjectRequest request = new JsonObjectRequest(method, fullUrl, jsonRequest, successListener, errorListener);
        execute(request, context);
    }

    public void getAllNovelCard(Response.Listener successListener, Response.ErrorListener errorListener, Context context) {
        callRequest(Request.Method.GET, "/api/hakore/all", null, successListener, errorListener, context);
    }

    public void getAllNovelCardWithPage(int page, Response.Listener successListener, Response.ErrorListener errorListener, Context context) {
        callRequest(Request.Method.GET, "/api/hakore/all?page=" + page, null, successListener, errorListener, context);
    }

    public void getNovelDetail(String url, Response.Listener successListener, Response.ErrorListener errorListener, Context context) {
        callRequest(Request.Method.GET, url, null, successListener, errorListener, context);
    }
}
