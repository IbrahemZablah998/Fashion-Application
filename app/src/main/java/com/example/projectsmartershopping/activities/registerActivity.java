package com.example.projectsmartershopping.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.projectsmartershopping.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class registerActivity extends AppCompatActivity {
    private EditText name, email, password, c_password, address, street;
    private EditText phone;
    private Button btn_regist;
    private ProgressBar loading;
    private static String HTTP = "http://";
    private static String IP1 = "172.19.107.34";// home is 192.168.1.13
    private static String IP = "192.168.43.104";// 3G is 192.168.43.104
    private static String URL_REGIST = HTTP + IP1 + "/android_register_login/register.php";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        loading = findViewById(R.id.loading);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        c_password = findViewById(R.id.c_password);
        btn_regist = findViewById(R.id.btn_regist);
        phone = findViewById(R.id.phone);
        address = findViewById(R.id.Address);
        street = findViewById(R.id.Street);


        btn_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Regist ();
            }
        });
    }

    private void Regist () {
        final String name = this.name.getText().toString().trim();
        final String email = this.email.getText().toString().trim();
        final String password = this.password.getText().toString().trim();
        final String phone = (this.phone.getText().toString().trim());
        final String address = this.address.getText().toString().trim();
        final String street = this.street.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGIST,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            if (success.equals("1")) {
                                if (!password.isEmpty() && !email.isEmpty() && !name.isEmpty() && !phone.isEmpty()) {
                                    Intent intent = new Intent(registerActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                    Toast.makeText(registerActivity.this, "success", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(registerActivity.this, "please insert all Fields", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(registerActivity.this, "is exsits this user", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            Toast.makeText(registerActivity.this, "error is" + e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(registerActivity.this, "error " + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        )
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name", name);
                params.put("email", email);
                params.put("password", password);
                params.put("phone", phone);
                params.put("address", address);
                params.put("street", street);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}