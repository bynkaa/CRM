package com.free.savcrm;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by bynkaa on 7/23/2016.
 */
public class ProductDetailImageFragment extends Fragment
{
    @Bind(R.id.product_image)
    ImageView productImage;
    int imageId;

    public static ProductDetailImageFragment newInstance(int imageId)
    {
        ProductDetailImageFragment productDetailImageFragment = new ProductDetailImageFragment();
        productDetailImageFragment.imageId = imageId;
        return productDetailImageFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.product_detail_image, container, false);
        ButterKnife.bind(this, view);
        productImage.setImageResource(imageId);
        return view;
    }
}
