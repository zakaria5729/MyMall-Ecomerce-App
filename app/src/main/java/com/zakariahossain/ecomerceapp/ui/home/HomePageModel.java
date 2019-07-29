package com.zakariahossain.ecomerceapp.ui.home;

import java.util.List;

public class HomePageModel {
    static final int SLIDER = 0;
    static final int STRIP_AD = 1;
    static final int HORIZONTAL_PRODUCT_VIEW = 2;
    static final int GRID_PRODUCT_VIEW = 3;

    private int type;

    /*Banner slider start*/
    private List<Slider> slideList;

    public HomePageModel(int type, List<Slider> slideList) {
        this.type = type;
        this.slideList = slideList;
    }

    public List<Slider> getSlideList() {
        return slideList;
    }
    /*Banner slider end*/

    public int getType() {
        return type;
    }

    /*Strip ad start*/
    private int stripAdImg;
    private String stripBackgroundColor;

    public HomePageModel(int type, int stripAdImg, String stripBackgroundColor) {
        this.type = type;
        this.stripAdImg = stripAdImg;
        this.stripBackgroundColor = stripBackgroundColor;
    }

    public int getStripAdImg() {
        return stripAdImg;
    }

    public String getStripBackgroundColor() {
        return stripBackgroundColor;
    }
    /*Strip ad end*/

    /*Horizontal and Grid product view start*/
    private String horizontalAndGridProductTitle;
    private List<HorizontalAndGridProduct> horizontalAndGridProductList;

    public HomePageModel(int type, String horizontalAndGridProductTitle, List<HorizontalAndGridProduct> horizontalAndGridProductList) {
        this.type = type;
        this.horizontalAndGridProductTitle = horizontalAndGridProductTitle;
        this.horizontalAndGridProductList = horizontalAndGridProductList;
    }

    public String getHorizontalAndGridProductTitle() {
        return horizontalAndGridProductTitle;
    }

    public List<HorizontalAndGridProduct> getHorizontalAndGridProductList() {
        return horizontalAndGridProductList;
    }
    /*Horizontal and Grid product view end*/
}
