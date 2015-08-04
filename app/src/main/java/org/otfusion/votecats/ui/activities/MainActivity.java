package org.otfusion.votecats.ui.activities;

import android.content.Intent;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.otto.Subscribe;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.otfusion.votecats.R;
import org.otfusion.votecats.common.model.Cat;
import org.otfusion.votecats.providers.CatLoadedEvent;
import org.otfusion.votecats.ui.events.FavoriteCatEvent;
import org.otfusion.votecats.ui.gestures.GestureDoubleTap;

import butterknife.Bind;

public class MainActivity extends CatActivity {

    @Bind(R.id.cat_view)
    ImageView _catImageView;

    @Bind(R.id.load_cat_button)
    Button _loadCatButton;

    @Bind(R.id.favorite_cat_button)
    Button _favoriteCatButton;

    private Cat _currentCat;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void loadUIElements() {
        _loadCatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _loadCatButton.setEnabled(false);
                getCatService().getCatFromApi();
            }
        });

        _favoriteCatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FavoriteCatEvent event = new FavoriteCatEvent(getBus(), _currentCat);
                event.executeEvent("button");
            }
        });

        final GestureDoubleTap<FavoriteCatEvent> doubleTapGesture = new GestureDoubleTap<>();
        _catImageView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                doubleTapGesture.setEvent(new FavoriteCatEvent(getBus(), _currentCat));
                GestureDetector gestureDetector = new GestureDetector(getApplicationContext(),
                        doubleTapGesture);
                return gestureDetector.onTouchEvent(motionEvent);
            }
        });
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
        _currentCat = catLoadedEvent.getCat();
        Picasso.with(getApplicationContext()).load(_currentCat.getImageUrl()).into(_catImageView,
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
                        _loadCatButton.setEnabled(true);
                    }
                });
    }

    @Subscribe
    @SuppressWarnings("unused") // used by the bus
    public void handleFavoriteCatEvent(FavoriteCatEvent favoriteCatEvent) {
        Cat cat = favoriteCatEvent.getCat();
        if (cat != null) {
            if(getCatService().isCatInFavorites(this, cat)) {
                Toast.makeText(this, "That cat is already in your collection", Toast.LENGTH_SHORT).show();
//                cat.setFavorite(false);
//                getCatService().removeCatFromFavorites();
            } else {
                cat.setFavorite(true);
                getCatService().saveCatToFavorites(this, cat);
                Toast.makeText(this, "Meow! Saved that.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "There is no cat there.", Toast.LENGTH_SHORT).show();
        }
    }
}
