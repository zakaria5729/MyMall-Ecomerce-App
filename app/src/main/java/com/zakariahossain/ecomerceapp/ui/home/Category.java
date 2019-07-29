package com.zakariahossain.ecomerceapp.ui.home;

public class Category {
    private String categoryIconUrl, categoryName;

    public Category(String categoryIconUrl, String categoryName) {
        this.categoryIconUrl = categoryIconUrl;
        this.categoryName = categoryName;
    }

    public String getCategoryIconUrl() {
        return categoryIconUrl;
    }

    public void setCategoryIconUrl(String categoryIconUrl) {
        this.categoryIconUrl = categoryIconUrl;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
