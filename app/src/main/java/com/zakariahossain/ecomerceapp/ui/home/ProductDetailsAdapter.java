package com.zakariahossain.ecomerceapp.ui.home;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ProductDetailsAdapter extends FragmentPagerAdapter {
    private int totalTabs;

    public ProductDetailsAdapter(@NonNull FragmentManager fm, int behavior, int totalTabs) {
        super(fm, behavior);
        this.totalTabs = totalTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new ProductDescriptionAndOtherFragment();  //for description tab

            case 1:
                return new ProductSpecificationFragment();

            case 2:
                return new ProductDescriptionAndOtherFragment(); //for other details tab

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}
