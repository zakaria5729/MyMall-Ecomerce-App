package com.zakariahossain.ecomerceapp.ui.home;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.zakariahossain.ecomerceapp.R;
import com.zakariahossain.ecomerceapp.databinding.GridProductLayoutBinding;
import com.zakariahossain.ecomerceapp.databinding.HorizontalProductLayoutBinding;
import com.zakariahossain.ecomerceapp.databinding.SliderViewPagerLayoutBinding;
import com.zakariahossain.ecomerceapp.databinding.StripAdLayoutBinding;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomePageAdapter extends RecyclerView.Adapter {
    private List<HomePageModel> homePageModelList;

    public HomePageAdapter(List<HomePageModel> homePageModelList) {
        this.homePageModelList = homePageModelList;
    }

    @Override
    public int getItemViewType(int position) {
        switch (homePageModelList.get(position).getType()) {
            case 0:
                return HomePageModel.SLIDER;

            case 1:
                return HomePageModel.STRIP_AD;

            case 2:
                return HomePageModel.HORIZONTAL_PRODUCT_VIEW;

            case 3:
                return HomePageModel.GRID_PRODUCT_VIEW;

            default:
                return -1;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case HomePageModel.SLIDER:
                return new SliderViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.slider_view_pager_layout, parent, false));

            case HomePageModel.STRIP_AD:
                return new StripAdViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.strip_ad_layout, parent, false));

            case HomePageModel.HORIZONTAL_PRODUCT_VIEW:
                return new HorizontalProductViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.horizontal_product_layout, parent, false));

            case HomePageModel.GRID_PRODUCT_VIEW:
                return new GridProductViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.grid_product_layout, parent, false));

            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (homePageModelList.get(position).getType()) {
            case HomePageModel.SLIDER:
                ((SliderViewHolder) holder).bindSliderViewPager(homePageModelList.get(position).getSlideList());
                break;

            case HomePageModel.STRIP_AD:
                ((StripAdViewHolder) holder).bindStripAd(homePageModelList.get(position).getStripAdImg(), homePageModelList.get(position).getStripBackgroundColor());
                break;

            case HomePageModel.HORIZONTAL_PRODUCT_VIEW:
                ((HorizontalProductViewHolder) holder).bindHorizontalProduct(homePageModelList.get(position).getHorizontalAndGridProductTitle(), homePageModelList.get(position).getHorizontalAndGridProductList());
                break;

            case HomePageModel.GRID_PRODUCT_VIEW:
                ((GridProductViewHolder) holder).bindGridProduct(homePageModelList.get(position).getHorizontalAndGridProductTitle(), homePageModelList.get(position).getHorizontalAndGridProductList());
                break;

            default:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return homePageModelList.size();
    }

    class SliderViewHolder extends RecyclerView.ViewHolder  {
        private SliderViewPagerLayoutBinding sliderBinding;
        private int currentPage;
        private Timer timer;

        SliderViewHolder(@NonNull SliderViewPagerLayoutBinding sliderBinding) {
            super(sliderBinding.getRoot());
            this.sliderBinding = sliderBinding;
        }

        @SuppressLint("ClickableViewAccessibility")
        private void bindSliderViewPager(List<Slider> sliderList) {
            currentPage = 2;
            if (timer != null) {
                timer.cancel();
            }

            SliderAdapter sliderAdapter = new SliderAdapter(sliderList);
            sliderBinding.bannerSliderViewPager.setAdapter(sliderAdapter);
            sliderBinding.bannerSliderViewPager.setClipToPadding(false);
            sliderBinding.bannerSliderViewPager.setPageMargin(20);
            sliderBinding.bannerSliderViewPager.setCurrentItem(currentPage);

            ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                }

                @Override
                public void onPageSelected(int position) {
                    currentPage = position;
                }

                @Override
                public void onPageScrollStateChanged(int state) {
                    if (state == ViewPager.SCROLL_STATE_IDLE) {
                        sliderPageLooper(sliderList);
                    }
                }
            };
            sliderBinding.bannerSliderViewPager.addOnPageChangeListener(onPageChangeListener);

            startBannerSlideShow(sliderList);
            sliderBinding.bannerSliderViewPager.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    sliderPageLooper(sliderList);
                    stopBannerSliderShow();

                    if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                        startBannerSlideShow(sliderList);
                    }
                    return false;
                }
            });
            sliderBinding.executePendingBindings();
        }

        private void startBannerSlideShow(List<Slider> sliderList) {
            Handler handler = new Handler();
            Runnable update = () -> {
                if (currentPage >= sliderList.size()) {
                    currentPage = 1;
                }
                sliderBinding.bannerSliderViewPager.setCurrentItem(currentPage++, true);
            };

            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post(update);
                }
            }, 2000, 2000);
        }

        private void stopBannerSliderShow() {
            timer.cancel();
        }

        private void sliderPageLooper(List<Slider> sliderList) {
            if (currentPage == sliderList.size() - 2) {
                currentPage = 2;
                sliderBinding.bannerSliderViewPager.setCurrentItem(currentPage, false);
            }

            if (currentPage == 1) {
                currentPage = sliderList.size() - 3;
                sliderBinding.bannerSliderViewPager.setCurrentItem(currentPage, false);
            }
        }
    }

    class StripAdViewHolder extends RecyclerView.ViewHolder {
        private StripAdLayoutBinding stripBinding;

        StripAdViewHolder(@NonNull StripAdLayoutBinding stripBinding) {
            super(stripBinding.getRoot());
            this.stripBinding = stripBinding;
        }

        void bindStripAd(int stripAdImg, String backgroundColor) {
            stripBinding.stripAdImg.setImageResource(stripAdImg);
            stripBinding.stripAdContainer.setBackgroundColor(Color.parseColor(backgroundColor));
            stripBinding.executePendingBindings();
        }
    }

    class HorizontalProductViewHolder extends RecyclerView.ViewHolder {
        private HorizontalProductLayoutBinding horizontalBinding;

        public HorizontalProductViewHolder(@NonNull HorizontalProductLayoutBinding horizontalBinding) {
            super(horizontalBinding.getRoot());
            this.horizontalBinding = horizontalBinding;
        }

        void bindHorizontalProduct(String horizontalProductTitle, List<HorizontalAndGridProduct> horizontalProductList) {

            if (horizontalProductList.size() > 8) {
                horizontalBinding.btnHorizontalViewAll.setVisibility(View.VISIBLE);
            } else {
                horizontalBinding.btnHorizontalViewAll.setVisibility(View.INVISIBLE);
            }

            horizontalBinding.tvHorizontalProductTitle.setText(horizontalProductTitle);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(horizontalBinding.getRoot().getContext());
            linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
            horizontalBinding.horizontalProductRecyclerView.setLayoutManager(linearLayoutManager);

            HorizontalProductAdapter horizontalProductAdapter = new HorizontalProductAdapter(horizontalProductList);
            horizontalBinding.horizontalProductRecyclerView.setAdapter(horizontalProductAdapter);

            horizontalProductAdapter.notifyDataSetChanged();
            horizontalBinding.executePendingBindings();
        }
    }

    class GridProductViewHolder extends RecyclerView.ViewHolder {
        private GridProductLayoutBinding gridBinding;

        public GridProductViewHolder(@NonNull GridProductLayoutBinding gridBinding) {
            super(gridBinding.getRoot());
            this.gridBinding = gridBinding;
        }

        void bindGridProduct(String gridProductTitle, List<HorizontalAndGridProduct> gridProductList) {
            gridBinding.tvGridProductTitle.setText(gridProductTitle);
            gridBinding.gridProductGridView.setAdapter(new GridProductAdapter(gridProductList));
            gridBinding.executePendingBindings();
        }
    }
}
