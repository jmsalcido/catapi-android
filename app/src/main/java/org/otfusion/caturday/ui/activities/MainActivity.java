package org.otfusion.caturday.ui.activities;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.otto.Subscribe;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.otfusion.caturday.R;
import org.otfusion.caturday.common.model.Cat;
import org.otfusion.caturday.events.CatLoadedEvent;
import org.otfusion.caturday.events.FavoriteCatEvent;
import org.otfusion.caturday.ui.gestures.GestureDoubleTap;
import org.otfusion.caturday.util.UIUtils;

import butterknife.Bind;

public class MainActivity extends CatActivity {

    @Bind(R.id.cat_view)
    ImageView mCatImageView;

    @Bind(R.id.load_cat_button)
    Button mLoadCatButton;

    @Bind(R.id.favorite_cat_button)
    Button mFavoriteCatButton;

    @Bind(R.id.main_toolbar)
    Toolbar mToolbar;

    private Cat mCurrentCat;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void loadUIContent() {
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

        setSupportActionBar(mToolbar);
    }

    private void loadCat() {
        mLoadCatButton.setEnabled(false);
        getCatService().getCatFromApi();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_main_goto_favorite) {
            Intent intent = new Intent(this, FavoriteActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Subscribe
    @SuppressWarnings("unused") // used by the bus
    public void handleCatLoadedEvent(CatLoadedEvent catLoadedEvent) {
        mCurrentCat = catLoadedEvent.getCat();
        loadImage();
    }

    private void loadImage() {
        Picasso.with(getApplicationContext()).load(mCurrentCat.getImageUrl()).into(mCatImageView,
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
