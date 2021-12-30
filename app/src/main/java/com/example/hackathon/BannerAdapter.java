package com.example.hackathon;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class BannerAdapter extends FragmentStateAdapter {

    public int mCount;

    public BannerAdapter(FragmentActivity fa, int count) {
        super(fa);
        mCount = count;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        int index = getPosition(position);

        if(index == 0) return new Fragment_1();
        else return new Fragment_2();
    }

    @Override
    public int getItemCount() {
        return 1000;
    }

    public int getPosition(int position) {
        return position % mCount;
    }
}
