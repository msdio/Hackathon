package com.example.hackathon;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class CardAdapter extends FragmentStateAdapter {

    public int mCount;

    public CardAdapter(FragmentActivity fa, int count) {
        super(fa);
        mCount = count;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        int index = getPosition(position);

        if(index == 0) return new CardFragment_1();
        else if(index == 1) return new CardFragment_2();
        else return new CardFragment_3();
    }

    @Override
    public int getItemCount() { return 3; }

    public int getPosition(int position) { return position % mCount; }


}
