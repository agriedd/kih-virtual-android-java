package com.komodoindotech.kihvirtual.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.bumptech.glide.Glide;
import com.komodoindotech.kihvirtual.R;
import com.komodoindotech.kihvirtual.json.KelasObject;
import com.komodoindotech.kihvirtual.ui.kelas.ComponentKelas;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AdapterPagerKelasIbuHamil extends FragmentPagerAdapter {

    private Context context;
    private List<KelasObject> kelasObjectList;
    private Fragment fragment;

    public AdapterPagerKelasIbuHamil(@NonNull @NotNull FragmentManager fm, int behavior, Context context, List<KelasObject> kelasObjectList) {
        super(fm, behavior);
        this.context = context;
        this.kelasObjectList = kelasObjectList;
    }

    @NonNull
    @NotNull
    @Override
    public Fragment getItem(int position) {
        return ComponentKelas.newInstance(kelasObjectList.get(position));
    }

    @Override
    public int getCount() {
        return kelasObjectList.size();
    }

    public void update(List<KelasObject> kelasObjects) {
        kelasObjectList = kelasObjects;
        this.notifyDataSetChanged();
    }

    @Override
    public float getPageWidth(int position) {
        if(getCount() <= 1)
            return 1f;
        return .9f;
    }
}
