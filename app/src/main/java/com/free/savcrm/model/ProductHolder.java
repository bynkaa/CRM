package com.free.savcrm.model;

import com.free.savcrm.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by bynkaa on 7/24/2016.
 */
public class ProductHolder
{
    Set<Product> productList = new HashSet<>();
    Map<String, List<Product>> productMaps = new HashMap<>();
    List<CategoryItem> categoryItems = new ArrayList<>();

    private static ProductHolder instance;

    private ProductHolder()
    {
        categoryItems.add(new CategoryItem(R.drawable.kat_pic_favourites, "Favorite", ""));
        categoryItems.add(new CategoryItem(R.drawable.kat_pic_her, "For Her", "forHer.json"));
        categoryItems.add(new CategoryItem(R.drawable.cat_pic_him, "For Him", "forHim.json"));
        categoryItems.add(new CategoryItem(R.drawable.cat_pic_kids, "For Kids", "forKid.json"));
        categoryItems.add(new CategoryItem(R.drawable.cat_pic_adventurer," For the Adventurer", "forAdv.json"));
        categoryItems.add(new CategoryItem(R.drawable.cat_pic_wild_colors, "Wild Colours", "forWild.json"));
    }

    public static ProductHolder getInstance()
    {
        if (instance == null)
        {
            instance = new ProductHolder();
        }
        return instance;
    }

    public void setProductList(String categoryName, List<Product> productList) {
        this.productList.addAll(productList);
        this.productMaps.put(categoryName, productList);
    }

    public List<Product> getFavouriteProducts()
    {
        List<Product> favoriteProducts = new ArrayList<>();
        for (Product product : productList)
        {
            if (product.isFavorite)
            {
                favoriteProducts.add(product);
            }
        }
        return favoriteProducts;
    }

    public List<CategoryItem> getCategoryItems() {
        return categoryItems;
    }

    public void setCategorySelected(int position)
    {
        for (CategoryItem categoryItem : categoryItems)
        {
            categoryItem.setSelected(false);
        }
        categoryItems.get(position).setSelected(true);
    }

    public Map<String, List<Product>> getProductMaps() {
        return productMaps;
    }
}
