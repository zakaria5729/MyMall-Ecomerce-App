package com.zakariahossain.ecomerceapp.ui.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.zakariahossain.ecomerceapp.R;
import com.zakariahossain.ecomerceapp.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding homeBinding;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        return homeBinding.getRoot();
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        homeBinding.categoryRecyclerView.setLayoutManager(layoutManager);

        CategoryAdapter categoryAdapter = new CategoryAdapter(getCategoryList());
        homeBinding.categoryRecyclerView.setAdapter(categoryAdapter);
        categoryAdapter.notifyDataSetChanged();

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(RecyclerView.VERTICAL);
        homeBinding.homeRecyclerView.setLayoutManager(manager);

        List<HomePageModel> homePageModelList = new ArrayList<>();
        homePageModelList.add(new HomePageModel(0, getSliderList()));
        homePageModelList.add(new HomePageModel(2, "Deals of the day", getHorizontalAndGridProductList()));
        //homePageModelList.add(new HomePageModel(1, R.drawable.strip_ad, "#000000"));
        homePageModelList.add(new HomePageModel(3, "Deals of the day", getHorizontalAndGridProductList()));

        HomePageAdapter adapter = new HomePageAdapter(homePageModelList);
        homeBinding.homeRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        homeBinding.executePendingBindings();
    }

    private List<HorizontalAndGridProduct> getHorizontalAndGridProductList() {
        List<HorizontalAndGridProduct> horizontalAndGridProductList = new ArrayList<>();

        horizontalAndGridProductList.add(new HorizontalAndGridProduct("https://static.toiimg.com/photo/63393835/Vivo-Apex.jpg", "Redmi 5A", "SD 425 Processsor", "৳ 5999/-"));
        horizontalAndGridProductList.add(new HorizontalAndGridProduct("https://static.toiimg.com/photo/63393835/Vivo-Apex.jpg", "LookMi 5A", "SD 425 Processsor", "৳ 5999/-"));
        horizontalAndGridProductList.add(new HorizontalAndGridProduct("https://static.toiimg.com/photo/63393835/Vivo-Apex.jpg", "Redmi 5A", "SD 425 Processsor", "৳ 5999/-"));
        horizontalAndGridProductList.add(new HorizontalAndGridProduct("https://static.toiimg.com/photo/63393835/Vivo-Apex.jpg", "Redmi 5A", "SD 425 Processsor", "৳ 5999/-"));
        horizontalAndGridProductList.add(new HorizontalAndGridProduct("https://static.toiimg.com/photo/63393835/Vivo-Apex.jpg", "Redmi 5A", "SD 425 Processsor", "৳ 5999/-"));
        horizontalAndGridProductList.add(new HorizontalAndGridProduct("https://static.toiimg.com/photo/63393835/Vivo-Apex.jpg", "Redmi 5A", "SD 425 Processsor", "৳ 5999/-"));
        horizontalAndGridProductList.add(new HorizontalAndGridProduct("https://static.toiimg.com/photo/63393835/Vivo-Apex.jpg", "Redmi 5A", "SD 425 Processsor", "৳ 5999/-"));
        horizontalAndGridProductList.add(new HorizontalAndGridProduct("https://static.toiimg.com/photo/63393835/Vivo-Apex.jpg", "Redmi 5A", "SD 425 Processsor", "৳ 5999/-"));
        horizontalAndGridProductList.add(new HorizontalAndGridProduct("https://static.toiimg.com/photo/63393835/Vivo-Apex.jpg", "Redmi 5A", "SD 425 Processsor", "৳ 5999/-"));

        return horizontalAndGridProductList;
    }

    private List<Slider> getSliderList() {
        List<Slider> sliderList = new ArrayList<>();

        sliderList.add(new Slider(R.drawable.ic_home_24dp, "#077AE4"));
        sliderList.add(new Slider(R.drawable.ic_my_mall_black_24dp, "#077AE4"));
        sliderList.add(new Slider(R.drawable.ic_menu_send, "#077AE4"));

        sliderList.add(new Slider(R.drawable.slider_banner, "#077AE4"));
        sliderList.add(new Slider(R.drawable.ic_favorite_black_24dp, "#077AE4"));
        sliderList.add(new Slider(R.drawable.ic_home_24dp, "#077AE4"));

        sliderList.add(new Slider(R.drawable.ic_my_mall_black_24dp, "#077AE4"));
        sliderList.add(new Slider(R.drawable.ic_menu_send, "#077AE4"));
        sliderList.add(new Slider(R.drawable.slider_banner, "#077AE4"));

        return sliderList;
    }

    private List<Category> getCategoryList() {
        List<Category> categoryList = new ArrayList<>();
        categoryList.add(new Category("https://static.toiimg.com/photo/63393835/Vivo-Apex.jpg", "Home"));
        categoryList.add(new Category("https://www.pinclipart.com/picdir/middle/345-3452565_web-app-video-stream-vector-old-tv-png.png", "Appliances"));
        categoryList.add(new Category("https://static.toiimg.com/photo/63393835/Vivo-Apex.jpg", "Books"));
        categoryList.add(new Category("https://www.pinclipart.com/picdir/middle/345-3452565_web-app-video-stream-vector-old-tv-png.png", "Furniture"));
        categoryList.add(new Category("https://static.toiimg.com/photo/63393835/Vivo-Apex.jpg", "Shoes"));
        categoryList.add(new Category("https://www.pinclipart.com/picdir/middle/345-3452565_web-app-video-stream-vector-old-tv-png.png", "Electronics"));
        categoryList.add(new Category("https://static.toiimg.com/photo/63393835/Vivo-Apex.jpg", "Toys"));
        categoryList.add(new Category("https://www.pinclipart.com/picdir/middle/345-3452565_web-app-video-stream-vector-old-tv-png.png", "Sports"));

        return categoryList;
    }

    @BindingAdapter("productImage")
    public static void loadImage(ImageView imageView, String imgUrl) {
        Glide.with(imageView.getContext())
                .load(imgUrl)
                .fitCenter()
                .into(imageView);
    }

    @BindingAdapter("categoryImageUrl")
    public static void loadCategoryIcon(ImageView imageView, String categoryIconUrl) {
        Glide.with(imageView.getContext())
                .load(categoryIconUrl)
                .fitCenter()
                .into(imageView);
    }
}
