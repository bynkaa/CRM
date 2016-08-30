package com.free.savcrm.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.free.savcrm.ProductDetailActivity;
import com.free.savcrm.R;
import com.free.savcrm.model.CategoryItem;

import java.util.List;

/**
 * Created by bynkaa on 7/24/2016.
 */
public class CategoryAdapter extends ArrayAdapter<CategoryItem> {

    public CategoryAdapter(Activity context, List<CategoryItem> categoryItems) {
        super(context, 0, categoryItems);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null)
        {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.ivCategory = (ImageView) convertView.findViewById(R.id.ivCategory);
            viewHolder.tvCategoryName = (TextView) convertView.findViewById(R.id.tvCategoryName);
            convertView.setTag(viewHolder);
        }

        viewHolder = (ViewHolder) convertView.getTag();

        final CategoryItem categoryItem = getItem(position);

        viewHolder.ivCategory.setImageResource(categoryItem.getImageId());
        viewHolder.tvCategoryName.setText(categoryItem.getCategory());
        return convertView;
    }

    private class ViewHolder
    {
        ImageView ivCategory;
        TextView tvCategoryName;

    }
}