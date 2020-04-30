package com.example.foodrocket;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OffersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OffersFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ArrayList<Offer> offers = new ArrayList<>();

    public OffersFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OffersFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OffersFragment newInstance(String param1, String param2) {
        OffersFragment fragment = new OffersFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_offers, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        defaultGetOffers();
    }

    private void defaultGetOffers() {
        JsonParser parser = new JsonParser();
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Offer>>() {}.getType();

        try {
            ApiOfferRequest items_api = new ApiOfferRequest();
            String response = items_api.execute().get();
            JsonObject json_response = parser.parse(response).getAsJsonObject();

            if (json_response.has("success")) {
                offers = gson.fromJson(json_response.getAsJsonArray("success"), type);
            } else if (json_response.has("error")) { // Invalid credentials
                String error = json_response.get("error").toString();
                Toast.makeText(this.getContext(), error,Toast.LENGTH_LONG).show();
            } else { // Some other error :(
                Toast.makeText(this.getContext(), response, Toast.LENGTH_LONG).show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
//        recyclerView.setAdapter(adapter);
//        adapter.startListening();
    }

    class ApiOfferRequest extends AsyncTask<String,Void,String> {
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }

        @Override
        protected String doInBackground(String... params) {
            GetRequestHandler ruc = new GetRequestHandler();
            return ruc.sendGetRequestParam("https://foodrocket.herokuapp.com/api/v1/offers", null);
        }
    }
}
