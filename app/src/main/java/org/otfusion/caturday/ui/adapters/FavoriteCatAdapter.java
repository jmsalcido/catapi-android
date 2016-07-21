package org.otfusion.caturday.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.otfusion.caturday.R;
import org.otfusion.caturday.common.model.Cat;

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

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public ViewHolder(View view) {
            super(view);
            textView = (TextView) view.findViewById(R.id.favorite_cat_list_text);
        }

        public void bind(Cat cat) {
            textView.setText(cat.getName());
            textView.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
            // todo figure out thumbnail.
//            String filePath = FileUtils.getFileName(cat, true);
//            if (!filePath.isEmpty()) {
//                Bitmap bitmap = ImageUtils.cropBitmap(BitmapFactory.decodeFile(filePath));
//                Resources resources = VoteCatsApplication.getContext().getResources();
//                RoundedBitmapDrawable img =
//                        RoundedBitmapDrawableFactory.create(resources, ImageUtils.resizeBitmap(bitmap, 128, 128));
//                img.setCornerRadius(Math.max(128, 128) / 2.0f);
        }
    }
}