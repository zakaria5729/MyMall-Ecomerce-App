package com.zakariahossain.ecomerceapp.ui.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zakariahossain.ecomerceapp.R;
import com.zakariahossain.ecomerceapp.databinding.ActivityCategoryBinding;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {
    ActivityCategoryBinding categoryBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        categoryBinding = DataBindingUtil.setContentView(this, R.layout.activity_category);

        setSupportActionBar(categoryBinding.categoryToolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            if (getIntent().getStringExtra("category_name") != null) {
                getSupportActionBar().setTitle(getIntent().getStringExtra("category_name"));
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(RecyclerView.VERTICAL);
        categoryBinding.categoryRecyclerView.setLayoutManager(manager);

        List<HomePageModel> homePageModelList = new ArrayList<>();
        homePageModelList.add(new HomePageModel(0, getSliderList()));
        homePageModelList.add(new HomePageModel(2, "Deals of the day", getHorizontalAndGridProductList()));
        homePageModelList.add(new HomePageModel(1, R.drawable.strip_ad, "#000000"));
        homePageModelList.add(new HomePageModel(3, "Deals of the day", getHorizontalAndGridProductList()));

        HomePageAdapter adapter = new HomePageAdapter(homePageModelList);
        categoryBinding.categoryRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        categoryBinding.executePendingBindings();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_search:
                return true;

            case android.R.id.home:
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private List<HorizontalAndGridProduct> getHorizontalAndGridProductList() {
        List<HorizontalAndGridProduct> horizontalAndGridProductList = new ArrayList<>();

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

    @BindingAdapter("productImage")
    public static void loadImage(ImageView imageView, String imgUrl) {
        Glide.with(imageView.getContext())
                .load(imgUrl)
                .fitCenter()
                .into(imageView);
    }
}
