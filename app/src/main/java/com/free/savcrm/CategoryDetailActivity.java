package com.free.savcrm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;
import android.widget.TextView;

import com.free.savcrm.adapter.CategoryDetailAdapter;
import com.free.savcrm.model.CategoryItem;
import com.free.savcrm.model.Product;
import com.free.savcrm.model.ProductHolder;
import com.free.savcrm.utils.DataUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by bynkaa on 7/23/2016.
 */
public class CategoryDetailActivity extends AppCompatActivity
{

    // TODO: 7/24/2016 Refactor Later
    public static CategoryItem categoryItem;
    @Bind(R.id.gv_category)
    GridView gvCategory;
    @Bind(R.id.category_filter)
    TextView tvCategoryFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_detail);
        ButterKnife.bind(this);
    }


    @Override
    protected void onResume() {
        super.onResume();
        tvCategoryFilter.setText(categoryItem.getCategory());
        initData();
    }

    private void initData()
    {
        List<Product> products = new ArrayList<>();
        if (categoryItem.getCategory().equals("Favorite"))
        {
            products = categoryItem.getProducts();
        }
        else if (ProductHolder.getInstance().getProductMaps().containsKey(categoryItem.getCategory()))
        {
            products = ProductHolder.getInstance().getProductMaps().get(categoryItem.getCategory());
        }
        else
        {
            Type type = new TypeToken<List<Product>>(){}.getType();

            String data = DataUtils.readDataFromAsset(this, categoryItem.getAssetName());
            products = new Gson().fromJson(data, type);
            ProductHolder.getInstance().setProductList(categoryItem.getCategory(), products);
        }

        for(Product product : products)
        {
            product.setImageId(DataUtils.getImageId(this, product.getThumb()));
        }
        gvCategory.setAdapter(new CategoryDetailAdapter(this, products));

    }

    @OnClick(R.id.back)
    void doBack()
    {
        onBackPressed();
    }

    @OnClick(R.id.category_filter)
    void onCategoryFilter()
    {
        Intent intent = new Intent(this, CategoryFilterActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
    }
}
