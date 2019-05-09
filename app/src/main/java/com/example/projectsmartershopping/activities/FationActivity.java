package com.example.projectsmartershopping.activities;

import android.content.Intent;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ProgressBar;
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

import java.util.HashMap;
import java.util.Map;

public class FationActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{
    private ProgressBar loading;
    private String name, description, price, rate, image_url, id_image;
    private SessionManager sessionManager;
    private static String HTTP = "http://";
    private static String IP1 = "172.19.107.34";// home is 192.168.1.13
    private static String IP = "192.168.43.104";// 3G is 192.168.43.104
    private static String Data_UPLOAD = HTTP + IP1 + "/android_register_login/upload_cart.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fation);
        sessionManager = new SessionManager(this);
        BottomNavigationView navigation = findViewById(R.id.navigation1);
        navigation.setOnNavigationItemSelectedListener(this);

        loading = findViewById(R.id.loading);
        // hide defult actionBar
        getSupportActionBar().hide();

        // Recieve data
        name = getIntent().getExtras().getString("fation_name");
        description = getIntent().getExtras().getString("fation_description");

        price = getIntent().getExtras().getString("fation_price") ;
        rate = getIntent().getExtras().getString("fation_rate");
        image_url = getIntent().getExtras().getString("fation_Image_url") ;
        id_image = getIntent().getExtras().getString("fation_ID");

        TextView tv_name = findViewById(R.id.f_title);
        TextView tv_description = findViewById(R.id.f_description);
        TextView tv_price = findViewById(R.id.f_price) ;
        TextView tv_rate = findViewById(R.id.f_rate);
        ImageView img = findViewById(R.id.f_imgs);

        // setting values to each view
        tv_name.setText(name);
        tv_description.setText(description);

        tv_price.setText(String.valueOf(price));
        tv_rate.setText(String.valueOf(rate));


        RequestOptions requestOptions = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);

        // set image using Glide
        Glide.with(this).load(image_url).apply(requestOptions).into(img);
    }
    public boolean onNavigationItemSelected(MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.navigation_addtocart:
                    Upload_Cart();
                break;
            case R.id.navigation_buy:
                HashMap<String, String> map = sessionManager.getUserDetail();
                final String id_user = map.get("ID");
                if (id_user != null) {
                    Intent intent = new Intent(FationActivity.this, BuyActivity.class);
                    intent.putExtra("description", description);
                    intent.putExtra("price", price);
                    intent.putExtra("rate", rate);
                    intent.putExtra("image", image_url);
                    intent.putExtra("id", id_image);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "Please Login user...", Toast.LENGTH_SHORT).show();
                }
                break;
        }
        return false;
    }

    public void Upload_Cart() {
        HashMap<String, String> map = sessionManager.getUserDetail();
        final String id_user = map.get("ID");
        if (id_user != null)
        {
            StringRequest stringRequest = new StringRequest(Request.Method.POST,
                    Data_UPLOAD,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Toast.makeText(FationActivity.this, "Success", Toast.LENGTH_SHORT).show();
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(FationActivity.this, "try agian " + error.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();

                    params.put("id_product", id_image);
                    params.put("id_user", id_user);
                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        } else {
            startActivity(new Intent(FationActivity.this, LoginActivity.class));
        }

    }
}


