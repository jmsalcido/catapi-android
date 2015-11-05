package org.otfusion.caturday.ui.fragments;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import com.squareup.picasso.Target;

import org.otfusion.caturday.R;
import org.otfusion.caturday.common.model.Cat;
import org.otfusion.caturday.util.FileUtils;

import java.io.Serializable;

import butterknife.Bind;
import it.sephiroth.android.library.imagezoom.ImageViewTouch;
import it.sephiroth.android.library.imagezoom.ImageViewTouchBase;

public class FavoriteCatImageFragment extends BaseFragment {

    public static final String CAT_KEY = "cat";
    @Bind(R.id.favorite_cat)
    ImageViewTouch mImageViewTouch;

    public static FavoriteCatImageFragment newInstance(Cat cat) {
        FavoriteCatImageFragment fragment = new FavoriteCatImageFragment();
        Bundle args = new Bundle();

        // if the object starts to grow, lets use Parcelable.
        args.putSerializable("cat", cat);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public int getContentLayoutId() {
        return R.layout.fragment_favorite_cat_image;
    }

    @Override
    public void loadUIContent() {
        Cat cat = (Cat) getArguments().getSerializable(CAT_KEY);
        mImageViewTouch.setDisplayType(ImageViewTouchBase.DisplayType.FIT_TO_SCREEN);
        Bitmap bitmap = BitmapFactory.decodeFile(FileUtils.getFileName(cat, true));
        mImageViewTouch.setImageBitmap(bitmap);
    }
}
