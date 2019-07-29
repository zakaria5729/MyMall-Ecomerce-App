package com.zakariahossain.ecomerceapp.ui.home;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

import androidx.databinding.DataBindingUtil;

import com.zakariahossain.ecomerceapp.R;
import com.zakariahossain.ecomerceapp.databinding.ItemHorizontalAndGridProductLayoutBinding;

import java.util.List;

public class GridProductAdapter extends BaseAdapter {
    private List<HorizontalAndGridProduct> gridProductList;

    public GridProductAdapter(List<HorizontalAndGridProduct> gridProductList) {
        this.gridProductList = gridProductList;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        GridProductViewHolder holder;

        if (convertView == null) {
            ItemHorizontalAndGridProductLayoutBinding gridBinding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.item_horizontal_and_grid_product_layout, viewGroup, false);

            holder = new GridProductViewHolder(gridBinding);
            holder.view = gridBinding.getRoot();
            holder.view.setTag(holder);

        } else {
            holder = (GridProductViewHolder) convertView.getTag();
        }

        holder.bind(gridProductList.get(position));
        return holder.view;
    }

    class GridProductViewHolder {
        private View view;
        private ItemHorizontalAndGridProductLayoutBinding gridBinding;

        GridProductViewHolder(ItemHorizontalAndGridProductLayoutBinding gridBinding) {
            this.view = gridBinding.getRoot();
            this.gridBinding = gridBinding;

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            gridBinding.horizontalGridContainer.setLayoutParams(layoutParams);
            view.setElevation(0);
            view.setBackgroundColor(Color.WHITE);
        }

        void bind(HorizontalAndGridProduct gridProduct) {
            gridBinding.setAHorizontalAndGridProduct(gridProduct);
            gridBinding.executePendingBindings();
        }
    }
}
