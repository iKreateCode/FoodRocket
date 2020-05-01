package com.example.foodrocket;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.StrictMath.round;

/**
 * A simple {@link Fragment} subclass.
 */
public class CartFragment extends Fragment {

    private static ArrayList<MenuItem> items = new ArrayList<>(0);
    private static ArrayList<Offer> offers = new ArrayList<>(0);
    private RecyclerView recyclerView;
    private CartAdapter adapter;
    private Button checkoutButton;
    private static TextView subtotal_amount;
    private static TextView shipping_amount;
    private static TextView total_amount;


    private final static String SHARED_PREFS = "sharedPrefs";
    private final static String CART_MENU_ITEMS = "cart_menu_items";
    private final static String CART_OFFER_ITEMS = "cart_offer_items";

    // Attributes for loading from and saving to SharedPreferences
    private static SharedPreferences sharedPreferences;
    final String USER_TOKEN = "user_token";
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (!cart_loaded) loadSavedCartItems(getContext());

        View view =  inflater.inflate(R.layout.fragment_cart, container, false);
        recyclerView = view.findViewById(R.id.cart_list);
        checkoutButton = view.findViewById(R.id.checkout_btn);
        adapter = new CartAdapter(items, getContext());

        GridLayoutManager gridLayoutManager = new GridLayoutManager((Context) getActivity(),1,GridLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);

        subtotal_amount = (TextView) view.findViewById(R.id.subtotal_amount);
        shipping_amount = (TextView) view.findViewById(R.id.shipping_amount);
        total_amount = (TextView) view.findViewById(R.id.total_amount);

        updateTotal();

        checkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (items.size() == 0) {
                    Toast.makeText(getContext(),"No Items In Cart :(", Toast.LENGTH_SHORT).show();
                } else {
                    // Gather items in JSON
                    Gson gson = new Gson();
                    JsonParser parser = new JsonParser();
                    String items_json = gson.toJson(items);
                    Log.d("Cart", items_json);

                    // Setup request data
                    HashMap<String, String> data = new HashMap<>();
                    data.put("token", sharedPreferences.getString(USER_TOKEN, ""));
                    data.put("order_type_id", "1");
                    data.put("note", "-");
                    data.put("items", items_json);

                    // Make request
                    RequestHandler ruc = new RequestHandler().sendPostRequest("https://foodrocket.herokuapp.com/api/v1/order",
                            data, sharedPreferences.getString(USER_TOKEN, null), getContext());
                    Log.d("Response", ruc.request_response);
                    JsonObject json_response = parser.parse(ruc.request_response).getAsJsonObject();

                    if (json_response.has("success")) {
                        Toast.makeText(getContext(), "Order Placed Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getContext(), AccountFragment.class);
                        getContext().startActivity(intent);
                    } else {
                        // Show Error
                        Toast.makeText(getContext(), "Unexpected Error Occurred.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
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

    public static void updateTotal() {
        double subtotal = 0;
        double delivery = 0;

        for (MenuItem item : items) {
            subtotal += item.getPrice();
        }

        double total = subtotal + delivery;

        subtotal_amount.setText(String.format("£%s", String.format("%.2f", subtotal)));
        shipping_amount.setText(String.format("£%s", String.format("%.2f", delivery)));
        total_amount.setText(String.format("£%s", String.format("%.2f", total)));
    }
}
