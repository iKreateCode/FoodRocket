package com.example.foodrocket;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class OffersFragment extends Fragment implements View.OnClickListener{
    private ArrayList<Offer> offers = new ArrayList<>(0);
    private RecyclerView recyclerView;
    private OfferAdapter adapter;

    private final String SHARED_PREFS = "sharedPrefs";
    private final String USER_TOKEN = "user_token";
    private boolean api_fetched = false;

    public OffersFragment() {
        // Required empty public constructor
    }

    @SuppressLint("Assert")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_offers, container, false);
        recyclerView = view.findViewById(R.id.recycler_menu);
        adapter = new OfferAdapter(offers, getContext());

        GridLayoutManager gridLayoutManager = new GridLayoutManager((Context) getActivity(),2,GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!api_fetched) {
            try {
                offers = defaultGetOffers();
                adapter = new OfferAdapter(offers, getContext());
                recyclerView.setAdapter(adapter);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private ArrayList<Offer> defaultGetOffers() throws ExecutionException, InterruptedException {
        JsonParser parser = new JsonParser();
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Offer>>() {}.getType();

        ApiOfferRequest items_api = new ApiOfferRequest();
        String response = items_api.execute().get();
        JsonObject json_response = parser.parse(response).getAsJsonObject();

        if (json_response.has("success")) {
                return gson.fromJson(json_response.getAsJsonArray("success"), type);
            } else if (json_response.has("error")) { // Invalid credentials
                String error = json_response.get("error").toString();
                Toast.makeText(this.getContext(), error,Toast.LENGTH_LONG).show();
            } else { // Some other error :(
                Toast.makeText(this.getContext(), response, Toast.LENGTH_LONG).show();
            }

        return null;
    }

    @Override
    public void onClick(View v) {
        // TODO: Show single offer view
    }

    class ApiOfferRequest extends AsyncTask<String,Void,String> {
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }

        @Override
        protected String doInBackground(String... params) {
            RequestHandler ruc = new RequestHandler();
            return ruc.sendGetRequestParam("https://foodrocket.herokuapp.com/api/v1/offers", null);
        }
    }
}
