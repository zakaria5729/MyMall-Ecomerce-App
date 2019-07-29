package com.zakariahossain.ecomerceapp.ui.home;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

public class ProductDetailsImageAdapter extends PagerAdapter {

    private List<Integer> productDetailsImageList;

    public ProductDetailsImageAdapter(List<Integer> productDetailsImageList) {
        this.productDetailsImageList = productDetailsImageList;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView productDetailsImage = new ImageView(container.getContext());
        productDetailsImage.setImageResource(productDetailsImageList.get(position));
        container.addView(productDetailsImage, 0);
        return productDetailsImage;
    }

    @Override
    public int getCount() {
        return productDetailsImageList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ImageView) object);
    }
}
