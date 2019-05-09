package com.example.projectsmartershopping.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.projectsmartershopping.R;
import com.example.projectsmartershopping.SessionManager;
import com.example.projectsmartershopping.adapters.cartAdapter;
import com.example.projectsmartershopping.model.Fashion;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BuyActivity extends AppCompatActivity {
    private ImageView view;
    private TextView price, rate, desc, total, place_order, MM, YY;
    private EditText card_numer, msg;
    SessionManager sessionManager ;
    private static List<Fashion> mData;
    private static String HTTP = "http://";
    private static String IP1 = "172.19.107.34";// home is 192.168.1.13
    private static String IP = "192.168.43.104";// 3G is 192.168.43.104
    private static String URL_INSERT_BUY = HTTP + IP1 + "/android_register_login/URL_INSERT_BUY.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);
        view = findViewById(R.id.imgs_cart);
        price = findViewById(R.id.price_cart);
        rate = findViewById(R.id.rate_cart);
        desc = findViewById(R.id.desc_cart);
        total = findViewById(R.id.subTotal);
        place_order = findViewById(R.id.place_order);
        card_numer = findViewById(R.id.card_numer);
        msg  = findViewById(R.id.msg);
        MM = findViewById(R.id.MM);
        YY = findViewById(R.id.YY);

        final Bundle bundle = getIntent().getExtras();
        RequestOptions requestOptions = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);
        Glide.with(this).load(bundle.getString("image")).apply(requestOptions).into(view);
        price.setText(bundle.getString("price"));
        rate.setText(bundle.getString("rate"));
        desc.setText(bundle.getString("description"));
        total.setText(bundle.getString("price"));


        place_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (card_numer.getText() != null && MM.getText() != null && YY.getText() != null) {
                    sessionManager = new SessionManager(getApplicationContext());
                    HashMap<String, String> user = sessionManager.getUserDetail();
                    final String getID = user.get(sessionManager.ID);
                    final String id = bundle.getString("id");
                    StringRequest stringRequest = new StringRequest(Request.Method.POST,
                            URL_INSERT_BUY,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Toast
                                            .makeText(BuyActivity.this,
                                                    " Thanks You... \n you wait the message from sell",
                                                    Toast.LENGTH_SHORT)
                                            .show();
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();

                                }
                            }

                    ) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<>();
                            params.put("getID", getID);
                            params.put("id", id);
                            params.put("msg", msg.getText().toString());
                            params.put("card_numer", card_numer.getText().toString());
                            params.put("MM", MM.getText().toString());
                            params.put("YY", YY.getText().toString());
                            params.put("total", total.getText().toString());
                            return params;
                        }
                    };
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    requestQueue.add(stringRequest);

                } else {
                    Toast.makeText(BuyActivity.this, "please set the Fields ", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }
}
