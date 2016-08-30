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
import com.free.savcrm.model.Product;

import java.util.List;

/**
 * Created by bynkaa on 7/23/2016.
 */
public class CategoryDetailAdapter extends ArrayAdapter<Product> {

    Activity activity;
    public CategoryDetailAdapter(Activity context, List<Product> products) {
        super(context, 0, products);
        this.activity = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null)
        {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_detail_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.ivItem = (ImageView) convertView.findViewById(R.id.item);
            viewHolder.cbHeart = (CheckBox) convertView.findViewById(R.id.cb_heart);
            viewHolder.tvItemName = (TextView) convertView.findViewById(R.id.item_name);
            viewHolder.tvItemPrice = (TextView) convertView.findViewById(R.id.item_price);
            viewHolder.tvStoreName = (TextView) convertView.findViewById(R.id.store_name);
            viewHolder.tvDateUpdated = (TextView) convertView.findViewById(R.id.date_updated);
            viewHolder.rootView = (LinearLayout) convertView.findViewById(R.id.rootView);
            convertView.setTag(viewHolder);
        }

        viewHolder = (ViewHolder) convertView.getTag();

        final Product product = getItem(position);
        viewHolder.ivItem.setImageResource(product.getImageId());
        viewHolder.tvItemName.setText(product.getName());
        viewHolder.tvItemPrice.setText("$" + product.getPrice());
        viewHolder.tvStoreName.setText(product.getStore().getName());
        viewHolder.tvDateUpdated.setText(product.getTime());
        viewHolder.cbHeart.setChecked(product.isFavorite());
        viewHolder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ProductDetailActivity.class);
                ProductDetailActivity.product = product;
                getContext().startActivity(intent);
                activity.overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left);
            }
        });

        final ViewHolder finalViewHolder = viewHolder;
        viewHolder.cbHeart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                product.setFavorite(finalViewHolder.cbHeart.isChecked());
            }
        });

        return convertView;
    }


    private class ViewHolder
    {
        ImageView ivItem;
        CheckBox cbHeart;
        TextView tvItemName;
        TextView tvItemPrice;
        TextView tvStoreName;
        TextView tvDateUpdated;
        LinearLayout rootView;

    }
}
