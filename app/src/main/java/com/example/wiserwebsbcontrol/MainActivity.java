package com.example.wiserwebsbcontrol;
// max category name length 23 symb vertical layout;
import static android.os.Environment.DIRECTORY_DOCUMENTS;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    public static final String read_url = ":8080/WiserWeb/scoreboard";
    public static final String write_url_start = ":8080/WiserWeb/scoreboard?category_id=", write_url_middle = "&new_score=";


    LinearLayout container;
    EditText url_et;
    Button refresh_btn, post_btn;

    RequestQueue queue;
    Context ctx;

    CategoryControl[] ccs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        container = findViewById(R.id.container);
        url_et = findViewById(R.id.url);
        refresh_btn = findViewById(R.id.refresh);
        post_btn = findViewById(R.id.post);
        ctx = this;
        url_et.setText("http://192.168.1.101");

        refresh_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                container.removeAllViews();
                CategoryControl.getFromServer(url_et.getText() + read_url, queue, ctx, container);
                Log.w("my_refresh_categories", "success");
            }
        });

        post_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for (CategoryControl cc: ccs) {
                    cc.updateScoreInServer(url_et.getText() + write_url_start, write_url_middle, queue);
                }
            }
        });

        queue = Volley.newRequestQueue(this);
    }
}