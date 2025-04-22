package com.example.wiserwebsbcontrol;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.content.res.AppCompatResources;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class CategoryControl {
    public int category_id, score;
    public String category_name;
    LinearLayout row_layout;
    TextView name_tv, score_tv;
    Button plus_btn, minus_btn;

    public CategoryControl(int category_id, String category_name, int score, Context ctx, LinearLayout parent_view) {
        this.category_name = category_name;
        this.category_id = category_id;
        this.score = score;
        createViews(parent_view, ctx);
        createBtnOnClickListeners();
        updateViews();
    }


    public void updateViews() {
        name_tv.setText(this.category_name);
        this.score_tv.setText(String.valueOf(this.score));
    }

    public void increment() {
        this.score++;
        this.updateViews();
    }

    public void decrement() {
        this.score--;
        this.updateViews();
    }

    public void createViews(LinearLayout parent_view, Context ctx) {
        float row_fontSize = ctx.getResources().getDimension(R.dimen.row_textSize);
        int row_text_color = ctx.getColor(R.color.white);

        LinearLayout.LayoutParams row_lp_mp_wc = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        int row_margin = (int) ctx.getResources().getDimension(R.dimen.row_margin);
        row_lp_mp_wc.setMargins(row_margin, row_margin, row_margin, row_margin);

        LinearLayout.LayoutParams name_tv_lp_wc_wc = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        int name_tv_margin_RL = (int) ctx.getResources().getDimension(R.dimen.cat_name_margin_RL);
        name_tv_lp_wc_wc.setMargins(name_tv_margin_RL, name_tv_margin_RL, name_tv_margin_RL, name_tv_margin_RL);

        LinearLayout.LayoutParams btn_lp_wc_wc = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        int btn_margin_RL = (int) ctx.getResources().getDimension(R.dimen.btn_margin_RL);
        btn_lp_wc_wc.setMargins(btn_margin_RL, btn_margin_RL, btn_margin_RL, btn_margin_RL);


        LinearLayout.LayoutParams lp_wc_wc = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        row_layout = new LinearLayout(ctx);
        row_layout.setLayoutParams(row_lp_mp_wc);
        row_layout.setGravity(Gravity.CENTER);
        row_layout.setBackground(AppCompatResources.getDrawable(ctx, R.drawable.layout_bg));
        row_layout.setOrientation(LinearLayout.HORIZONTAL);
        int row_padding = (int) ctx.getResources().getDimension(R.dimen.row_padding);
        row_layout.setPadding(row_padding, row_padding, row_padding, row_padding);

        name_tv = new TextView(ctx);
        name_tv.setLayoutParams(lp_wc_wc);
        name_tv.setTextSize(row_fontSize);
        name_tv.setTextColor(row_text_color);
        name_tv.setText("No Category Name");

        minus_btn = new Button(ctx);
        minus_btn.setLayoutParams(btn_lp_wc_wc);
        minus_btn.setText(ctx.getText(R.string.minus_symb));
        minus_btn.setTextSize(row_fontSize);
        minus_btn.setTextColor(row_text_color);
        minus_btn.setBackgroundTintList(ColorStateList.valueOf(ctx.getColor(R.color.minus_btn)));

        score_tv = new TextView(ctx);
        name_tv.setLayoutParams(lp_wc_wc);
        name_tv.setTextSize(row_fontSize);
        name_tv.setTextColor(row_text_color);
        name_tv.setText("-1");

        plus_btn = new Button(ctx);
        plus_btn.setLayoutParams(btn_lp_wc_wc);
        plus_btn.setText(ctx.getText(R.string.plus_symb));
        plus_btn.setTextSize(row_fontSize);
        plus_btn.setTextColor(row_text_color);
        plus_btn.setBackgroundTintList(ColorStateList.valueOf(ctx.getColor(R.color.plus_btn)));


        parent_view.addView(row_layout);
        row_layout.addView(name_tv);
        row_layout.addView(minus_btn);
        row_layout.addView(score_tv);
        row_layout.addView(plus_btn);
    }


    public void createBtnOnClickListeners() {
        minus_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decrement();
            }
        });

        plus_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                increment();
            }
        });
    }


    public void updateScoreInServer(String url_start, String url_middle, RequestQueue queue){
        String url = url_start + category_id + url_middle + score;
        Log.e("MY_LOG", url);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray jsonArray) {
                        try {
                            String status = jsonArray.getString(0);
                            if(!Objects.equals(status, "success")){
                                Log.e("my_scoreboard_req_update_score", jsonArray.getString(1));
                            }else{
                                Log.e("my_scoreboard_req_update_score", "update success");
                            }
                        } catch (JSONException e) {
                            Log.e("my_scoreboard_req_array_parse", e.toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("my_scoreboard_req", error.toString());
                    }
                });

        queue.add(jsonArrayRequest);
    }

    public static void getFromServer(String url, RequestQueue queue, Context ctx, LinearLayout parent_view){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray jsonArray) {
                        try {
                            CategoryControl[] categoryControls = new CategoryControl[jsonArray.length()];
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject cat_json = jsonArray.getJSONObject(i);
                                int id = cat_json.getInt("id");
                                String name = cat_json.getString("name");
                                int score = cat_json.getInt("score");
                                categoryControls[i] = new CategoryControl(id, name, score, ctx, parent_view);
                            }
                            ((MainActivity) ctx).ccs = categoryControls;
                        } catch (JSONException e) {
                            Log.e("my_scoreboard_req_array_parse", e.toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("my_scoreboard_req", error.toString());
                    }
                });

        queue.add(jsonArrayRequest);
    }
}
