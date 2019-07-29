package com.zakariahossain.ecomerceapp.ui.home;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.zakariahossain.ecomerceapp.R;
import com.zakariahossain.ecomerceapp.databinding.ItemHorizontalAndGridProductLayoutBinding;

import java.util.List;

public class HorizontalProductAdapter extends RecyclerView.Adapter<HorizontalProductAdapter.HorizontalProductViewHolder> {

    private List<HorizontalAndGridProduct> horizontalProductList;

    public HorizontalProductAdapter(List<HorizontalAndGridProduct> horizontalProductList) {
        this.horizontalProductList = horizontalProductList;
    }

    @NonNull
    @Override
    public HorizontalProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HorizontalProductViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_horizontal_and_grid_product_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HorizontalProductViewHolder holder, int position) {
        holder.bind(horizontalProductList.get(position));
    }

    @Override
    public int getItemCount() {
        if (horizontalProductList.size() > 8) {
            return 8;
        } else {
            return horizontalProductList.size();
        }
    }

    class HorizontalProductViewHolder extends RecyclerView.ViewHolder {
        private ItemHorizontalAndGridProductLayoutBinding horizontalBinding;

        public HorizontalProductViewHolder(ItemHorizontalAndGridProductLayoutBinding horizontalBinding) {
            super(horizontalBinding.getRoot());
            this.horizontalBinding = horizontalBinding;

            horizontalBinding.getRoot().setOnClickListener(view -> horizontalBinding.getRoot().getContext().startActivity(new Intent(horizontalBinding.getRoot().getContext(), ProductDetailsActivity.class).addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)));
        }

        void bind(HorizontalAndGridProduct horizontalProduct) {
            horizontalBinding.setAHorizontalAndGridProduct(horizontalProduct);
            horizontalBinding.executePendingBindings();
        }
    }
}
