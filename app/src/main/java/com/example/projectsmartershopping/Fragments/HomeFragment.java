package com.example.projectsmartershopping.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.projectsmartershopping.R;
import com.example.projectsmartershopping.adapters.RecyclerViewAdapter;
import com.example.projectsmartershopping.model.Fashion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private static String HTTP = "http://";
    private static String IP = "192.168.43.104";// 3G is 192.168.43.104
    private static String IP1 = "172.19.107.34";// home is 192.168.1.13
    private static String URL_LOGIN = HTTP + IP1 + "/android_register_login/api.php";
    private List<Fashion> fashions;

    RecyclerView viewAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.fragment_home, container, false);
        fashions = new ArrayList<>();
        viewAdapter = myView.findViewById(R.id.recycleview);
        loadProduct();
        return myView;
    }

    private void loadProduct() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject object = jsonArray.getJSONObject(i);
                                int id = object.getInt("id");
                                String title = object.getString("title").trim();
                                String description = object.getString("description").trim();
                                double rating = object.getDouble("rating");
                                double price = object.getDouble("price");
                                String image = object.getString("imageURL");
                                Fashion fashion = new Fashion(image, title, description, price, rating, id);

                                fashions.add(fashion);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getContext(), "error my so sorry", Toast.LENGTH_SHORT).show();
                        }
                        setUpReCycleReView(fashions);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), "error my sorry", Toast.LENGTH_SHORT).show();
                    }
                });
        Volley.newRequestQueue(getActivity().getApplicationContext()).add(stringRequest);
    }
    private void setUpReCycleReView(List<Fashion> fashions) {
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(getActivity().getApplicationContext(), fashions);
        viewAdapter.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        viewAdapter.setAdapter(recyclerViewAdapter);
    }
}
