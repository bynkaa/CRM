package com.free.savcrm;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import com.free.savcrm.adapter.CategoryDetailAdapter;
import com.free.savcrm.model.ProductHolder;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by bynkaa on 7/24/2016.
 */
public class FavoriteProductActivity extends AppCompatActivity
{
    @Bind(R.id.gv_category)
    GridView gvCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorite_product);
        ButterKnife.bind(this);
        gvCategory.setAdapter(new CategoryDetailAdapter(this, ProductHolder.getInstance().getFavouriteProducts()));
    }

    @OnClick(R.id.back)
    void doLogout()
    {
       onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
    }
}