package org.otfusion.caturday.ui.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.squareup.picasso.Picasso;

import org.otfusion.caturday.R;
import org.otfusion.caturday.common.domain.Cat;

import butterknife.BindView;
import it.sephiroth.android.library.imagezoom.ImageViewTouch;
import it.sephiroth.android.library.imagezoom.ImageViewTouchBase;
import it.sephiroth.android.library.imagezoom.graphics.FastBitmapDrawable;

public class FavoriteCatImageDaggerFragment extends BaseDaggerFragment {

    public static final String MODEL_KEY = "cat";

    @BindView(R.id.favorite_image_view)
    ImageViewTouch mImageViewTouch;

    public static FavoriteCatImageDaggerFragment newInstance(Cat cat) {
        FavoriteCatImageDaggerFragment fragment = new FavoriteCatImageDaggerFragment();
        Bundle args = new Bundle();
        args.putSerializable("cat", cat);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.menu_favorite_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(getActivity());
                return true;
            case R.id.action_favorite_menu_share:
                FastBitmapDrawable bmpDrawable = (FastBitmapDrawable) mImageViewTouch.getDrawable();
                String filePathMediaStore = getFilePathFromMediaStore(bmpDrawable.getBitmap());
                Intent shareImageIntent = obtainShareImageIntent(Uri.parse(filePathMediaStore));
                startActivity(Intent.createChooser(shareImageIntent, "Share a cat!"));
                return false;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public int getContentLayoutId() {
        return R.layout.fragment_favorite_image;
    }

    @Override
    public void loadUIContent() {
        Cat cat = getCatFromArguments();
        mImageViewTouch.setDisplayType(ImageViewTouchBase.DisplayType.FIT_TO_SCREEN);
        String filePath = getCatService().getCatFileName(cat, true);
        if (filePath.isEmpty()) {
            Picasso.with(getActivity()).load(cat.getImageUrl()).into(mImageViewTouch);
        } else {
            Bitmap bitmap = BitmapFactory.decodeFile(filePath);
            mImageViewTouch.setImageBitmap(bitmap);
        }
    }

    @Override
    public int getTitleId() {
        return R.string.title_activity_favorite;
    }

    private Cat getCatFromArguments() {
        return (Cat) getArguments().getSerializable(MODEL_KEY);
    }
}
