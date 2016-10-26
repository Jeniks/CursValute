package com.example.ngers.cursvalute.view;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ngers.cursvalute.R;
import com.example.ngers.cursvalute.databinding.ItemValuteBinding;
import com.example.ngers.cursvalute.model.Valute;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ngers on 25.10.16.
 */

public class CurrencyAdapter extends RecyclerView.Adapter<CurrencyAdapter.ViewHolder> {

    private List<Valute> valuteList = new ArrayList<>();
    private OnClickItem onClickItem;

    public CurrencyAdapter(List<Valute> valuteList, OnClickItem onClickItem) {
        this.valuteList = valuteList;
        this.onClickItem = onClickItem;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemValuteBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_valute,
                parent,
                false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Valute valute = valuteList.get(position);
        holder.binding.name.setText(valute.getName());
        holder.binding.value.setText(valute.getValue());
        holder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickItem.OnClickItem(valute);
            }
        });
    }

    @Override
    public int getItemCount() {
        return valuteList != null ? valuteList.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ItemValuteBinding binding;


        public ViewHolder(ItemValuteBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public interface OnClickItem {
        void OnClickItem(Valute valute);
    }
}
