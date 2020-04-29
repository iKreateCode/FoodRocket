package com.example.foodrocket;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MenuFragment extends Fragment implements View.OnClickListener{

    CardView itemCard;

    RecyclerView recyclerView;
    ViewPager viewPager;
    Adapter adapter;
    List<PopularItemModel> models;
    Integer[] colors = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();

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

        models = new ArrayList<>();
        models.add(new PopularItemModel(R.drawable.pizza, "Pizza", "Fresh Backed Pizza"));
        models.add(new PopularItemModel(R.drawable.pizza, "Pizza", "Fresh Backed Pizza"));
        models.add(new PopularItemModel(R.drawable.pizza, "Pizza", "Fresh Backed Pizza"));
        models.add(new PopularItemModel(R.drawable.pizza, "Pizza", "Fresh Backed Pizza"));

        adapter = new Adapter(models, (Context) getActivity());

        viewPager = view.findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);
        viewPager.setPadding(200, 0, 200, 0);

        Integer[] colors_temp = {
                getResources().getColor(R.color.UI_LightBlue),
                getResources().getColor(R.color.UI_LightBlue),
                getResources().getColor(R.color.UI_DarkBlue),
                getResources().getColor(R.color.UI_Red)
        };



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
