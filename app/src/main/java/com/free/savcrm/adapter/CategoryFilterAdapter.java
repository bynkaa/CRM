package com.free.savcrm.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.free.savcrm.CategoryDetailActivity;
import com.free.savcrm.R;
import com.free.savcrm.model.CategoryItem;
import com.free.savcrm.model.ProductHolder;

import java.util.List;

/**
 * Created by bynkaa on 7/24/2016.
 */
public class CategoryFilterAdapter extends ArrayAdapter<CategoryItem> {

    public CategoryFilterAdapter(Context context, List<CategoryItem> categoryItems) {
        super(context, 0, categoryItems);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_filter_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tvCategory = (TextView) convertView.findViewById(R.id.category_name);
            viewHolder.ivCheck = (ImageView) convertView.findViewById(R.id.checked);
            viewHolder.rlRootView = (RelativeLayout) convertView.findViewById(R.id.rootView);
            convertView.setTag(viewHolder);
        }
        viewHolder = (ViewHolder) convertView.getTag();
        final CategoryItem categoryItem = getItem(position);
        viewHolder.tvCategory.setText(categoryItem.getCategory());
        viewHolder.ivCheck.setVisibility(categoryItem.isSelected() ? View.VISIBLE : View.GONE);
        viewHolder.rlRootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CategoryDetailActivity.categoryItem = categoryItem;
                ProductHolder.getInstance().setCategorySelected(position);
                ((Activity)getContext()).onBackPressed();
            }
        });
        return convertView;
    }

    private static class ViewHolder
    {
        TextView tvCategory;
        ImageView ivCheck;
        RelativeLayout rlRootView;
    }

}
