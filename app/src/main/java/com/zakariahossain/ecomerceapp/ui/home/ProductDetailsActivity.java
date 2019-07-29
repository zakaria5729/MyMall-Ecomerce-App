package com.zakariahossain.ecomerceapp.ui.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.zakariahossain.ecomerceapp.R;
import com.zakariahossain.ecomerceapp.databinding.ActivityProductDetailsBinding;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailsActivity extends AppCompatActivity {
    private ActivityProductDetailsBinding detailsBinding;
    private boolean alreadyAddedToWishList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        detailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_product_details);

        setSupportActionBar(detailsBinding.productDetailsToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        detailsBinding.productDetailsImageLayoutIncluder.tabViewPagerIndicator.setupWithViewPager(detailsBinding.productDetailsImageLayoutIncluder.productDetailsImgViewPager, true); //set tabIndicator with viewPager
    }

    @Override
    protected void onStart() {
        super.onStart();

        ProductDetailsImageAdapter adapter = new ProductDetailsImageAdapter(getProductDetailsImageList());
        detailsBinding.productDetailsImageLayoutIncluder.productDetailsImgViewPager.setAdapter(adapter);

        detailsBinding.productDetailsImageLayoutIncluder.fabWishList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (alreadyAddedToWishList) {
                    alreadyAddedToWishList = false;
                    detailsBinding.productDetailsImageLayoutIncluder.fabWishList.setColorFilter(Color.GRAY);

                } else {
                    alreadyAddedToWishList = true;
                    detailsBinding.productDetailsImageLayoutIncluder.fabWishList.setColorFilter(Color.RED);
                }
            }
        });
    }

    private List<Integer> getProductDetailsImageList() {
        List<Integer> productDetailsImageList = new ArrayList<>();
        productDetailsImageList.add(R.drawable.slider_banner);
        productDetailsImageList.add(R.drawable.ic_menu_share);
        productDetailsImageList.add(R.drawable.slider_banner);

        return productDetailsImageList;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search_and_cart, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_search:
                return true;

            case R.id.menu_cart:
                return true;

            case android.R.id.home:
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
