package com.zakariahossain.ecomerceapp.ui.home;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager.widget.PagerAdapter;

import com.zakariahossain.ecomerceapp.R;
import com.zakariahossain.ecomerceapp.databinding.ItemSliderLayoutBinding;

import java.util.List;

public class SliderAdapter extends PagerAdapter {
    private List<Slider> sliderList;

    SliderAdapter(List<Slider> sliderList) {
        this.sliderList = sliderList;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ItemSliderLayoutBinding sliderBinding = DataBindingUtil.inflate(LayoutInflater.from(container.getContext()), R.layout.item_slider_layout, container, false);

        sliderBinding.bannerSlider.setImageResource(sliderList.get(position).getBanner());
        sliderBinding.bannerContainer.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(sliderList.get(position).getBackgroundColor())));
        sliderBinding.executePendingBindings();

        container.addView(sliderBinding.getRoot(), 0);
        return sliderBinding.getRoot();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public int getCount() {
        return sliderList.size();
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
