package org.otfusion.caturday.ui.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.otto.Subscribe;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.otfusion.caturday.R;
import org.otfusion.caturday.application.VoteCatsApplication;
import org.otfusion.caturday.common.model.Cat;
import org.otfusion.caturday.events.CatLoadedEvent;
import org.otfusion.caturday.events.FavoriteCatEvent;
import org.otfusion.caturday.ui.gestures.GestureDoubleTap;
import org.otfusion.caturday.util.ApplicationUtils;
import org.otfusion.caturday.util.UIUtils;

import butterknife.BindView;

public class MainFragment extends BaseFragment {


    public static final String FRAGMENT_TAG = "main";

    @BindView(R.id.cat_view)
    ImageView mCatImageView;

    @BindView(R.id.load_cat_button)
    Button mLoadCatButton;

    @BindView(R.id.favorite_cat_button)
    Button mFavoriteCatButton;

    private Cat mCurrentCat;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public int getContentLayoutId() {
        return R.layout.fragment_main;
    }

    private void loadCat() {
        mLoadCatButton.setEnabled(false);
        getCatService().getCatFromApi();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.menu_main_fragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_share:
                Uri imageUri = ApplicationUtils.getLocalBitmapUri(mCatImageView);
                Intent shareImageIntent = ApplicationUtils.getShareImageIntent(imageUri);
                startActivity(Intent.createChooser(shareImageIntent, "Share this cat:"));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void loadUIContent() {
        loadCat();
        mLoadCatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadCat();
            }
        });

        mFavoriteCatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FavoriteCatEvent event = new FavoriteCatEvent(getBus(), mCurrentCat);
                event.executeEvent("button");
            }
        });

        final GestureDoubleTap<FavoriteCatEvent> doubleTapGesture = new GestureDoubleTap<>();
        mCatImageView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                doubleTapGesture.setEvent(new FavoriteCatEvent(getBus(), mCurrentCat));
                GestureDetector gestureDetector = new GestureDetector(getApplicationContext(),
                        doubleTapGesture);
                return gestureDetector.onTouchEvent(motionEvent);
            }
        });
    }


    @Override
    public int getTitleId() {
        return R.string.app_name;
    }

    @Subscribe
    @SuppressWarnings("unused") // used by the bus
    public void handleCatLoadedEvent(CatLoadedEvent catLoadedEvent) {
        mCurrentCat = catLoadedEvent.getCat();
        loadImage();
    }

    private void loadImage() {
        Context context = getApplicationContext();
        Picasso.with(context).load(mCurrentCat.getImageUrl()).into(mCatImageView,
                new Callback() {
                    @Override
                    public void onSuccess() {
                        enableLoadButton();
                    }

                    @Override
                    public void onError() {
                        enableLoadButton();
                    }

                    private void enableLoadButton() {
                        mLoadCatButton.setEnabled(true);
                    }
                });
    }

    private Context getApplicationContext() {
        return VoteCatsApplication.getContext();
    }

    @Subscribe
    @SuppressWarnings("unused") // used by the bus
    public void handleFavoriteCatEvent(FavoriteCatEvent favoriteCatEvent) {
        Cat cat = favoriteCatEvent.getCat();
        if (cat != null) {
            if (getCatService().isCatInFavorites(cat)) {
                UIUtils.showToast("That cat is already in your collection");
            } else {
                getCatService().saveCatToFavorites(cat);
                loadCat();
                UIUtils.showToast("Saving that right Meow!");
            }
        } else {
            UIUtils.showToast("There is no cat there.");
        }
    }
}
