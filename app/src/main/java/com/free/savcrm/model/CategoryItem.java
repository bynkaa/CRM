package com.free.savcrm.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bynkaa on 7/24/2016.
 */
public class CategoryItem
{
    private int imageId;
    private String category;
    private boolean isSelected;
    private String assetName;
    private List<Product> products = new ArrayList<>();

    public CategoryItem(int imageId, String category, String assetName) {
        this.imageId = imageId;
        this.category = category;
        this.assetName = assetName;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
