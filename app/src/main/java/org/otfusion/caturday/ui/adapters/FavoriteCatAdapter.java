package org.otfusion.caturday.ui.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.otfusion.caturday.R;
import org.otfusion.caturday.util.FileUtils;
import org.otfusion.caturday.util.ImageUtils;
import org.otfusion.caturday.util.ViewHolder;
import org.otfusion.caturday.application.VoteCatsApplication;
import org.otfusion.caturday.common.model.Cat;

import java.util.Collections;
import java.util.List;

public class FavoriteCatAdapter extends BaseAdapter {

    private List<Cat> cats;

    public FavoriteCatAdapter() {
        cats = Collections.emptyList();
    }

    public void updateCats(List<Cat> cats) {
        this.cats = cats;
        notifyDataSetChanged();
    }

    public List<Cat> getCats() {
        return cats;
    }

    @Override
    public int getCount() {
        return cats.size();
    }

    @Override
    public Cat getItem(int position) {
        return cats.get(position);
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
                    .inflate(R.layout.list_favorite_item, parent, false);
        }

        TextView favoriteCat = ViewHolder.get(convertView, R.id.favorite_cat_list_text);

        Cat cat = getItem(position);
        favoriteCat.setText(cat.getName());
        String filePath = FileUtils.getFileName(cat, true);
        if (!filePath.isEmpty()) {
            Bitmap bitmap = ImageUtils.cropBitmap(BitmapFactory.decodeFile(filePath));
            Resources resources = VoteCatsApplication.getContext().getResources();
            RoundedBitmapDrawable img =
                    RoundedBitmapDrawableFactory.create(resources, ImageUtils.resizeBitmap(bitmap, 128, 128));
            img.setCornerRadius(Math.max(128, 128) / 2.0f);
            favoriteCat.setCompoundDrawablesWithIntrinsicBounds(img, null, null, null);
        }

        return convertView;
    }
}
