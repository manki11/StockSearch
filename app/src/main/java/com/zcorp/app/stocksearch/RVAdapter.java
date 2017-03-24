package com.zcorp.app.stocksearch;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Mankirat on 12-03-2017.
 */

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.Stocks> {


    List<com.zcorp.app.stocksearch.Stocks> stocksList;

    public RVAdapter(List stocksList){
        this.stocksList=stocksList;
    }


    @Override
    public RVAdapter.Stocks onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
        Stocks stocks=new Stocks(v);
        return stocks;
    }

    @Override
    public void onBindViewHolder(RVAdapter.Stocks holder, int position) {
        holder.sticker.setText(stocksList.get(position).getSticker());
        holder.name.setText(stocksList.get(position).getName());
        holder.change.setText(stocksList.get(position).getChange()+"");
        holder.currency.setText(stocksList.get(position).getCurrency());

    }

    @Override
    public int getItemCount() {
        return stocksList.size();
    }

    public static class Stocks extends RecyclerView.ViewHolder{

        CardView cv;
        TextView name,sticker,change,currency;


        public Stocks(View itemView) {
            super(itemView);
            cv=(CardView) itemView.findViewById(R.id.cv);
            name=(TextView) itemView.findViewById(R.id.name);
            sticker=(TextView) itemView.findViewById(R.id.sticker);
            change=(TextView) itemView.findViewById(R.id.change);
            currency=(TextView) itemView.findViewById(R.id.currency);
        }
    }
}
