package com.example.foodrocket;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder>{

    private ArrayList<MenuItem> models;
    LayoutInflater inflater;

    public CartAdapter(ArrayList<MenuItem> models, Context context){
        this.models = models;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.cart_item_activity,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText("1 x " + models.get(position).getName());
        holder.price.setText("£" + models.get(position).getPrice());
        //holder.itemImage.setImageResource(Integer.parseInt(models.get(position).getImageUrl()));
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView itemImage;
        TextView title;
        TextView price;
        Button remove;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            itemImage = (ImageView) itemView.findViewById(R.id.image);
            title = (TextView) itemView.findViewById(R.id.title);
            price = (TextView) itemView.findViewById(R.id.price);
            remove = (Button) itemView.findViewById(R.id.remove_button);

            remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CartFragment.removeFromCart(models.get(getAdapterPosition()), itemView.getContext());
                    notifyDataSetChanged();
                    CartFragment.updateTotal();
                }
            });
        }
    }
}
