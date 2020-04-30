package com.example.foodrocket;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * A simple {@link Fragment} subclass.
 */
public class MenuFragment extends Fragment implements View.OnClickListener{

    private ArrayList<MenuItem> items = new ArrayList<>();
    private RecyclerView recyclerView;
    private ItemAdapter adapter;

    private final String SHARED_PREFS = "sharedPrefs";
    private final String USER_TOKEN = "user_token";
    private boolean api_fetched = false;

    public MenuFragment() {
        // Required empty public constructor
    }


    @SuppressLint("Assert")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_menu, container, false);
        recyclerView = view.findViewById(R.id.recycler_menu);
        adapter = new ItemAdapter(items, getContext());

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
                items = defaultGetItems();
                adapter = new ItemAdapter(items, getContext());
                recyclerView.setAdapter(adapter);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // TODO: Testing only
        CartFragment.addToCart(items.get(0), getContext());
        CartFragment.addToCart(items.get(1), getContext());
        CartFragment.addToCart(items.get(2), getContext());
    }

    private ArrayList<MenuItem> defaultGetItems() throws ExecutionException, InterruptedException {
        ApiMenuItemRequest items_api = new ApiMenuItemRequest();
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        String token = sharedPreferences.getString(USER_TOKEN, "");
        JsonParser parser = new JsonParser();

        String response = items_api.execute(token).get();
        JsonObject json_response = parser.parse(response).getAsJsonObject();

        if (json_response.has("success")) {
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<MenuItem>>() {}.getType();
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
//        switch (v.getId()){
//            case R.id.pizza1 :
//                Intent intent = new Intent(getActivity(), Item.class);
//                startActivity(intent);
//                break;
//            default: break;
//        }
    }

    class ApiMenuItemRequest extends AsyncTask<String,Void,String> {
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }

        @Override
        protected String doInBackground(String... params) {
            GetRequestHandler ruc = new GetRequestHandler();
            return ruc.sendGetRequestParam("https://foodrocket.herokuapp.com/api/v1/menu", params[0]);
        }
    }
}