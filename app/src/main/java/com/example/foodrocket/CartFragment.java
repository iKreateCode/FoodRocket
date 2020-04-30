package com.example.foodrocket;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class CartFragment extends Fragment {

    private static ArrayList<MenuItem> items = new ArrayList<>(0);
    private static ArrayList<Offer> offers = new ArrayList<>(0);
    private RecyclerView recyclerView;
    private CartAdapter adapter;

    private final static String SHARED_PREFS = "sharedPrefs";
    private final static String CART_MENU_ITEMS = "cart_menu_items";
    private final static String CART_OFFER_ITEMS = "cart_offer_items";

    // Attributes for loading from and saving to SharedPreferences
    private static SharedPreferences sharedPreferences;
    private static Gson gson = new Gson();
    private static Type menu_item_type = new TypeToken<ArrayList<MenuItem>>() {}.getType();
    private static Type offer_type = new TypeToken<ArrayList<Offer>>() {}.getType();
    private static boolean cart_loaded = false;

    public CartFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!cart_loaded) {
            loadSavedCartItems(this.getContext());
            cart_loaded = true;
        }
        Log.d("Cart", "Items loaded in cart: " + items.size());
        Log.d("Cart", "Offers loaded in cart: " + offers.size());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (!cart_loaded) loadSavedCartItems(getContext());

        View view =  inflater.inflate(R.layout.fragment_cart, container, false);
        recyclerView = view.findViewById(R.id.cart_list);
        adapter = new CartAdapter(items, getContext());

        GridLayoutManager gridLayoutManager = new GridLayoutManager((Context) getActivity(),1,GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);

        return view;
    }

    private static void loadSavedCartItems(Context context) {
        if (sharedPreferences == null)
            sharedPreferences = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);

        String json_items = sharedPreferences.getString(CART_MENU_ITEMS, "[]");
        String json_offer_items = sharedPreferences.getString(CART_OFFER_ITEMS, "[]");

        items = gson.fromJson(json_items, menu_item_type);
        offers = gson.fromJson(json_offer_items, offer_type);
    }

    private static void saveCartItems(Context context) {
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(CART_MENU_ITEMS, gson.toJson(items).toString());
        editor.putString(CART_OFFER_ITEMS, gson.toJson(offers).toString());
        editor.apply();
    }

    public static void addToCart(MenuItem item, Context context) {
        if (!cart_loaded) {
            loadSavedCartItems(context);
            cart_loaded = true;
        }

        items.add(item);

        saveCartItems(context);
    }

    public static void addToCart(Offer offer, Context context) {
        if (!cart_loaded) {
            loadSavedCartItems(context);
            cart_loaded = true;
        }

        offers.add(offer);

        saveCartItems(context);
    }

    public static void removeFromCart(MenuItem item, Context context) {
        if (!cart_loaded) {
            loadSavedCartItems(context);
            cart_loaded = true;
        }

        for (MenuItem anItem : items) {
            if (anItem == item) {
                items.remove(item);
                break;
            }
        }
        Toast.makeText(context, "Item Removed.", Toast.LENGTH_SHORT).show();
        saveCartItems(context);
    }

    public static void removeFromCart(Offer offer, Context context) {
        if (!cart_loaded) {
            loadSavedCartItems(context);
            cart_loaded = true;
        }

        for (Offer anOffer : offers) {
            if (anOffer == offer) {
                offers.remove(offer);
            }
        }

        saveCartItems(context);
    }

    public static boolean itemInCart(MenuItem item, Context context) {
        if (!cart_loaded) {
            loadSavedCartItems(context);
            cart_loaded = true;
        }

        for (MenuItem anItem : items) {
            if (anItem == item) {
                return true;
            }
        }

        return false;
    }

    public static boolean offerInCart(Offer offer, Context context) {
        if (!cart_loaded) {
            loadSavedCartItems(context);
            cart_loaded = true;
        }

        for (Offer anOffer : offers) {
            if (anOffer == offer) return true;
        }

        return false;
    }
}
