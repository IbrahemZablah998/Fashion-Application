package com.example.projectsmartershopping.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.projectsmartershopping.R;
import com.example.projectsmartershopping.SessionManager;
import com.example.projectsmartershopping.adapters.cartAdapter;
import com.example.projectsmartershopping.model.Fashion;
import com.example.projectsmartershopping.visaCard;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartFragment extends Fragment {
    String getID;
    private static String HTTP = "http://";
    private static String IP1 = "172.19.107.34";// home is 192.168.1.13
    private static String IP = "192.168.43.104";// 3G is 192.168.43.104
    private static String URL_READ_cart = HTTP + IP1 + "/android_register_login/read_cart.php";
    private static String URL_DELETE_cart = HTTP + IP1 + "/android_register_login/delete_cart.php";
    private List<Fashion> fashions;
    public static List<Integer> itemsToDelete;
    SessionManager sessionManager ;
    RecyclerView viewAdapter;
    private ImageButton delete;
    private Button butit;
    public static TextView mTotalPrice;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.fragment_cart, container, false);
        fashions = new ArrayList<>();
        sessionManager = new SessionManager(getActivity().getApplicationContext());
        HashMap<String, String> user = sessionManager.getUserDetail();
        itemsToDelete= new ArrayList<Integer>();
        getID = user.get(sessionManager.ID);
        delete = myView.findViewById(R.id.delete);
        mTotalPrice=myView.findViewById(R.id.tv_totalPrice);
        butit = myView.findViewById(R.id.butit);
        delete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                delete();
            }
        });
        viewAdapter = myView.findViewById(R.id.recycleview_cart);
        if (sessionManager.isLogin()) {
            recover();
        }
        else {
            Toast.makeText(getActivity().getApplicationContext(), "You are not LOGIN... \n Please LOGIN...", Toast.LENGTH_SHORT).show();
        }
        butit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTotalPrice.length() == 1) {
                    Toast.makeText(getActivity().getApplicationContext(), "Please Check the product to Buy!", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(getActivity(), visaCard.class);
                    startActivity(intent);
                }
            }
        });
        return myView;
    }
    public  void delete() {
        for (final Integer i : itemsToDelete) {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DELETE_cart,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Toast.makeText(getActivity().getApplicationContext(), "success", Toast.LENGTH_SHORT).show();
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getActivity().getApplicationContext(), "error", Toast.LENGTH_SHORT).show();

                        }
                    }

            ) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("id", String.valueOf(i.intValue()));
                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
            requestQueue.add(stringRequest);
        }

    }
    private void recover() {
        HashMap<String, String> user = sessionManager.getUserDetail();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                URL_READ_cart,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject object = jsonArray.getJSONObject(i);
                                    String image = object.getString("image_url");
                                    String description = object.getString("description").trim();
                                    double price = object.getDouble("price");
                                    int id = object.getInt("id");
                                    double rating = object.getDouble("rating");
                                    Fashion fashion = new Fashion(image, description, price, rating, id);
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
                        error.printStackTrace();
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
    private void setUpReCycleReView(List<Fashion> fashions) {
        cartAdapter recyclerViewAdapter = new cartAdapter(getActivity().getApplicationContext(), fashions);
        viewAdapter.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        viewAdapter.setAdapter(recyclerViewAdapter);
    }
}
