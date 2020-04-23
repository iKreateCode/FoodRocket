package com.example.foodrocket;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class MenuFragment extends Fragment {

    CardView itemCard;

    public MenuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        //itemCard = (CardView) getView().findViewById(R.id.pizza1);

        //itemCard.setOnClickListener(this);

        return inflater.inflate(R.layout.fragment_menu, container, false);

    }

//    @Override
//    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.pizza1 :
//                Intent intent = new Intent(getContextApplication(), Login.class);
//                startActivity(intent);
//                break;
//            default: break;
//        }
//    }
}
