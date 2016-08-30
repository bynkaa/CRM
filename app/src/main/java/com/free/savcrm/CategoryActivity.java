package com.free.savcrm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import com.free.savcrm.adapter.CategoryAdapter;
import com.free.savcrm.model.ProductHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;

/**
 * Created by bynkaa on 7/24/2016.
 */
public class CategoryActivity extends AppCompatActivity {

    @Bind(R.id.gv_category)
    GridView gvCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        ButterKnife.bind(this);
        gvCategory.setAdapter(new CategoryAdapter(this, ProductHolder.getInstance().getCategoryItems()));

    }

    @OnItemClick(R.id.gv_category)
    void onItemClick(int position)
    {
        if (position == 0)
        {
            CategoryDetailActivity.categoryItem = ProductHolder.getInstance().getCategoryItems().get(position);
            CategoryDetailActivity.categoryItem.setProducts(ProductHolder.getInstance().getFavouriteProducts());
            Intent intent = new Intent(this, CategoryDetailActivity.class);
            ProductHolder.getInstance().setCategorySelected(position);
            startActivity(intent);
            overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);

        }
        else
        {
            Intent intent = new Intent(this, CategoryDetailActivity.class);
            ProductHolder.getInstance().setCategorySelected(position);
            CategoryDetailActivity.categoryItem = ProductHolder.getInstance().getCategoryItems().get(position);
            startActivity(intent);
            overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
        }
    }

    @OnClick(R.id.logout)
    void doLogout()
    {
        Intent intent = new Intent(this, FlashActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
    }
}
