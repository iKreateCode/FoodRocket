package com.example.foodrocket;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MenuFragment extends Fragment implements View.OnClickListener{

    CardView itemCard;

    RecyclerView recyclerView;
    ViewPager viewPager;
    PopularItemAdapter adapter;
    List<ItemModel> models;
    Integer[] colors = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();

    RecyclerView dataList;
    List<String> titles;
    List<Integer> images;
    List<String> desc;
    ItemAdapter adapters;

    public MenuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view =  inflater.inflate(R.layout.fragment_menu, container, false);

        //itemCard = (CardView) view.findViewById(R.id.pizza1);

        //itemCard.setOnClickListener(this);

//        models = new ArrayList<>();
//        models.add(new ItemModel(R.drawable.pizza, "Pizza", "Fresh Backed Pizza"));
//        models.add(new ItemModel(R.drawable.pizza, "Pizza", "Fresh Backed Pizza"));
//        models.add(new ItemModel(R.drawable.pizza, "Pizza", "Fresh Backed Pizza"));
//        models.add(new ItemModel(R.drawable.pizza, "Pizza", "Fresh Backed Pizza"));
//
//        adapter = new PopularItemAdapter(models, (Context) getActivity());
//
//        viewPager = view.findViewById(R.id.viewPager);
//        viewPager.setAdapter(adapter);
//        viewPager.setPadding(200, 0, 200, 0);

        dataList = (RecyclerView) view.findViewById(R.id.recycler_menu);

        titles = new ArrayList<>();
        images = new ArrayList<>();
        desc = new ArrayList<>();


        titles.add("First Item");
        titles.add("Second Item");
        titles.add("Third Item");
        titles.add("Fourth Item");
        titles.add("First Item");
        titles.add("Second Item");
        titles.add("Third Item");
        titles.add("Fourth Item");

        desc.add("asdsadasdasdasdasdasdasdasdsadasd");
        desc.add("Second Item");
        desc.add("Third Item");
        desc.add("Fourth Item");
        desc.add("First Item");
        desc.add("Second Item");
        desc.add("Third Item");
        desc.add("Fourth Item");

        images.add(R.drawable.pizza);
        images.add(R.drawable.pizza);
        images.add(R.drawable.pizza);
        images.add(R.drawable.pizza);
        images.add(R.drawable.pizza);
        images.add(R.drawable.pizza);
        images.add(R.drawable.pizza);
        images.add(R.drawable.pizza);

        adapters = new ItemAdapter((Context) getActivity(),titles,images,desc);

        GridLayoutManager gridLayoutManager = new GridLayoutManager((Context) getActivity(),2,GridLayoutManager.VERTICAL,false);
        dataList.setLayoutManager(gridLayoutManager);
        dataList.setAdapter(adapters);

        return view;

    }

    @Override
    public void onStart() {
        super.onStart();
        //defaultGetItems();
    }

    // Recycler view fetch items
//    private void defaultGetItems() {
//
//        recyclerView.setAdapter(adapter);
//        adapter.startListening();
//    }

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
}
