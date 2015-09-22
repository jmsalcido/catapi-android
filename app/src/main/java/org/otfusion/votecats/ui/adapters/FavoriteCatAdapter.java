package org.otfusion.votecats.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.otfusion.votecats.R;
import org.otfusion.votecats.application.VoteCatsApplication;
import org.otfusion.votecats.common.model.Cat;
import org.otfusion.votecats.util.ViewHolder;

import java.util.Collections;
import java.util.List;

public class FavoriteCatAdapter extends BaseAdapter {

    private List<Cat> _cats;

    public FavoriteCatAdapter() {
        _cats = Collections.emptyList();
    }

    public void updateCats(List<Cat> cats) {
        _cats = cats;
        notifyDataSetChanged();
    }

    public List<Cat> getCats() {
        return _cats;
    }

    @Override
    public int getCount() {
        return _cats.size();
    }

    @Override
    public Cat getItem(int position) {
        return _cats.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private Context getContext() {
        return VoteCatsApplication.getContext();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.list_favorites, parent, false);
        }

        TextView favoriteCat = ViewHolder.get(convertView, R.id.favorite_cat_list_text);

        Cat cat = getItem(position);
        favoriteCat.setText(cat.getName());

        return convertView;
    }
}
