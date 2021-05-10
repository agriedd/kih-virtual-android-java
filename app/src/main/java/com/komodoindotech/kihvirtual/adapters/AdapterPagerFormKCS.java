package com.komodoindotech.kihvirtual.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.komodoindotech.kihvirtual.R;

import java.util.List;

public class AdapterPagerFormKCS extends FragmentStateAdapter {

    List<Fragment> fragmentList;
    Context mContext;

    public AdapterPagerFormKCS(@NonNull FragmentActivity fragmentActivity, List<Fragment> fragmentList, Context mContext) {
        super(fragmentActivity);
        this.fragmentList = fragmentList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getItemCount() {
        return fragmentList.size();
    }

}
