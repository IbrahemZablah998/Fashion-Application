package com.example.projectsmartershopping.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.projectsmartershopping.R;

import java.util.HashMap;
import java.util.Map;


public class toBeReview extends AppCompatActivity {
    private EditText editText;
    private Button submit, clear;

    private static String HTTP = "http://";
    private static String IP1 = "172.19.107.34";// home is 192.168.1.13
    private static String IP = "192.168.43.104";// 3G is 192.168.43.104
    private static String URL_FeedBack = HTTP + IP1 + "/android_register_login/feed.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_be_review);

        editText = findViewById(R.id.feedback);
        submit = findViewById(R.id.submit);
        clear = findViewById(R.id.clear);



            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (editText.getText().length() < 1) {
                        Toast.makeText(toBeReview.this, "Please Full the fiels feedback", Toast.LENGTH_SHORT).show();
                    } else {
                        final String feedback = editText.getText().toString();
                        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_FeedBack,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        editText.getText().clear();
                                        editText.setHint("Your FeedBack Application Here... ");
                                        Toast.makeText(toBeReview.this, "Thanks for feedback ..", Toast.LENGTH_SHORT).show();
                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {

                                    }
                                }
                        )
                        {
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String, String> params = new HashMap<>();
                                params.put("feed", feedback);
                                return params;
                            }
                        };
                        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                        requestQueue.add(stringRequest);
                    }
                }
            });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.getText().clear();
                editText.setHint("Your FeedBack Application Here... ");
            }
        });

    }
}
