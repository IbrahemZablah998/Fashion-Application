package com.example.projectsmartershopping.Fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.projectsmartershopping.SessionManager;
import com.example.projectsmartershopping.activities.LoginActivity;
import com.example.projectsmartershopping.R;
import com.example.projectsmartershopping.activities.logoutActivity;
import com.example.projectsmartershopping.activities.toBeReview;
import com.example.projectsmartershopping.model.Fashion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountFragment extends Fragment{
    private Button sign, setting, feedback;
    private String getID;
    private ImageButton imageButton;
    private List<Fashion> fashions;
    private static String HTTP = "http://";
    private static String IP1 = "172.19.107.34";// home is 192.168.1.13
    private static String IP = "192.168.43.104";// 3G is 192.168.43.104
    private static String URL_READ_photo = HTTP + IP1 + "/android_register_login/read_photo.php";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.fragment_account, container, false);
        sign = (Button) myView.findViewById(R.id.sign);
        final SessionManager sessionManager = new SessionManager(getActivity().getApplicationContext());

        HashMap<String, String> user = sessionManager.getUserDetail();

        getID = user.get(sessionManager.ID);
        feedback = (Button) myView.findViewById(R.id.to_be_reviewed);
        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), toBeReview.class);
                startActivity(intent);
            }
        });
        if (sessionManager.isLogin()) {
            sign.setText("LOG OUT");

            //read_photo();
        } else {

            sign.setVisibility(View.VISIBLE);


        }
        setting = (Button) myView.findViewById(R.id.settings);
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), logoutActivity.class);
                startActivity(intent);
            }
        });
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sessionManager.isLogin()) {
                    sessionManager.logout();

                } else if(!sessionManager.isLogin()){
                    goes();
                }

            }
        });

        return myView;
    }
    public void goes() {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);

    }

    public void read_photo() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                URL_READ_photo,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for (int i = 0; i < jsonArray.length(); i++) {

                                JSONObject object = jsonArray.getJSONObject(i);
                                String image = object.getString("photo");
                                URL url_value = new URL(image);
                                Bitmap mIcon1 = BitmapFactory.decodeStream(url_value.openConnection().getInputStream());
                                imageButton.setImageBitmap(mIcon1);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id", getID);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        requestQueue.add(stringRequest);
    }

}
