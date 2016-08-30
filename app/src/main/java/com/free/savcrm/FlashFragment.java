package com.free.savcrm;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.free.savcrm.model.FlashItem;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by bynkaa on 7/21/2016.
 */
public class FlashFragment extends Fragment {

    @Bind(R.id.flash_layout)
    RelativeLayout flashLayout;
    @Bind(R.id.title)
    TextView tvTitle;
    @Bind(R.id.message)
    TextView tvMessage;

    FlashItem flashItem;

    public static FlashFragment newInstance(FlashItem flashItem) {
        FlashFragment flashFragment = new FlashFragment();
        flashFragment.flashItem = flashItem;
        return flashFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_flash, container, false);
        ButterKnife.bind(this, view);
        flashLayout.setBackgroundResource(flashItem.getDrawableBackground());
        tvTitle.setText(flashItem.getTitle());
        tvMessage.setText(flashItem.getMessage());
        return view;
    }
}
