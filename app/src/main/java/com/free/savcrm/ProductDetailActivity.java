package com.free.savcrm;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.free.savcrm.model.Product;

import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.relex.circleindicator.CircleIndicator;

/**
 * Created by bynkaa on 7/23/2016.
 */
public class ProductDetailActivity extends AppCompatActivity {

    // TODO: 7/25/2016 refactor this later
    public static Product product;

    @Bind(R.id.viewPager)
    ViewPager viewPager;
    @Bind(R.id.indicator)
    CircleIndicator indicator;

    @Bind(R.id.product_name)
    TextView tvProductName;
    @Bind(R.id.date_updated)
    TextView tvTime;
    @Bind(R.id.product_price)
    TextView tvPrice;
    @Bind(R.id.product_desc)
    TextView tvDescription;
    @Bind(R.id.store_name)
    TextView tvStoreName;
    @Bind(R.id.store_address)
    TextView tvStoreAddress;
    @Bind(R.id.add_to_favorite)
    CheckBox cbAddToFavorite;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_detail);
        ButterKnife.bind(this);

        initData();
    }

    private void initData() {
        tvProductName.setText(product.getName());
        tvPrice.setText("$" + product.getPrice());
        tvTime.setText(product.getTime());
        tvDescription.setText(product.getDesc());
        tvStoreName.setText(product.getStore().getName());
        tvStoreAddress.setText(product.getStore().getAddress());
        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager(), Arrays.asList(product.getImageId())));
        indicator.setViewPager(viewPager);
        cbAddToFavorite.setChecked(product.isFavorite());
    }

    @OnClick(R.id.back)
    void doBack()
    {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
    }

    @OnClick(R.id.view_on_map)
    void doViewOnMaps()
    {
        Intent intent = new Intent(this, StoreLocationActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
    }

    @OnClick(R.id.open_in_map)
    void openInMap()
    {
        try {
            Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                    Uri.parse("google.navigation:q=" + product.getStore().getAddress()));
            startActivity(intent);
        }
        catch (Exception e)
        {
            Toast.makeText(this, "No APP Available", Toast.LENGTH_SHORT).show();
        }

    }

    @OnClick(R.id.bottom_bar)
    void doAddToFavorite()
    {
        product.setFavorite(!product.isFavorite());
        cbAddToFavorite.setChecked(product.isFavorite());
    }

    private class MyPagerAdapter extends FragmentPagerAdapter
    {
        private List<Integer> images;

        public MyPagerAdapter(FragmentManager fm, List<Integer> images) {
            super(fm);
            this.images = images;
        }

        @Override
        public Fragment getItem(int position) {
            return ProductDetailImageFragment.newInstance(images.get(position));
        }

        @Override
        public int getCount() {
            return images.size();
        }
    }
}
