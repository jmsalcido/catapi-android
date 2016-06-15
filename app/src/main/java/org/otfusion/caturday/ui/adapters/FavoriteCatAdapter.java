package org.otfusion.caturday.ui.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.otfusion.caturday.R;
import org.otfusion.caturday.application.VoteCatsApplication;
import org.otfusion.caturday.common.model.Cat;
import org.otfusion.caturday.util.FileUtils;
import org.otfusion.caturday.util.ImageUtils;

import java.util.Collections;
import java.util.List;

public class FavoriteCatAdapter extends RecyclerView.Adapter<FavoriteCatAdapter.ViewHolder> {

    private List<Cat> dataSet;

    public FavoriteCatAdapter() {
        dataSet = Collections.emptyList();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_favorite_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Cat cat = dataSet.get(position);
        holder.bind(cat);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public void updateCats(List<Cat> cats) {
        dataSet = cats;
        notifyDataSetChanged();
    }

    public List<Cat> getDataSet() {
        return dataSet;
    }

    private Context getContext() {
        return VoteCatsApplication.getContext();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public ViewHolder(View view) {
            super(view);
            textView = (TextView) view.findViewById(R.id.favorite_cat_list_text);
        }

        public void bind(Cat cat) {
            textView.setText(cat.getName());
            String filePath = FileUtils.getFileName(cat, true);
            if (!filePath.isEmpty()) {
                Bitmap bitmap = ImageUtils.cropBitmap(BitmapFactory.decodeFile(filePath));
                Resources resources = VoteCatsApplication.getContext().getResources();
                RoundedBitmapDrawable img =
                        RoundedBitmapDrawableFactory.create(resources, ImageUtils.resizeBitmap(bitmap, 128, 128));
                img.setCornerRadius(Math.max(128, 128) / 2.0f);
                textView.setCompoundDrawablesWithIntrinsicBounds(img, null, null, null);
            }
        }
    }
}
