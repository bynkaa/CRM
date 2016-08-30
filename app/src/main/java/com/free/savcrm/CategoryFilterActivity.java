package com.free.savcrm;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.free.savcrm.adapter.CategoryAdapter;
import com.free.savcrm.adapter.CategoryFilterAdapter;
import com.free.savcrm.model.CategoryItem;
import com.free.savcrm.model.ProductHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by bynkaa on 7/24/2016.
 */
public class CategoryFilterActivity extends AppCompatActivity {

    @Bind(R.id.list_category)
    ListView lvCategory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_filter);
        ButterKnife.bind(this);
        List<CategoryItem> categoryItems = new ArrayList<>();
        lvCategory.setAdapter(new CategoryFilterAdapter(this, ProductHolder.getInstance().getCategoryItems()));

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
    }

    @OnClick(R.id.following)
    void doCancel()
    {
        onBackPressed();
    }
}
