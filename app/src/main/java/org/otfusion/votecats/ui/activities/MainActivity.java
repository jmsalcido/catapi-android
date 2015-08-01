package org.otfusion.votecats.ui.activities;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import com.squareup.picasso.Picasso;

import org.otfusion.votecats.R;
import org.otfusion.votecats.common.model.Cat;
import org.otfusion.votecats.providers.CatLoadedEvent;
import org.otfusion.votecats.service.CatServiceImpl;
import org.otfusion.votecats.ui.gestures.GestureDoubleTap;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends CatActivity {

    @Inject
    Bus _bus;

    @Inject
    CatServiceImpl _catService;

    @Bind(R.id.cat_view)
    ImageView _catImageView;

    @Bind(R.id.load_cat_button)
    Button _loadCatButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        loadUIElements();
        getBus().register(this);
    }

    // TODO inject ui elements
    private void loadUIElements() {
        _loadCatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _catService.getCatFromApi();
                _loadCatButton.setEnabled(false);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Subscribe
    public void handleCatLoadedEvent(CatLoadedEvent catLoadedEvent) {
        Cat cat = catLoadedEvent.getCat();
        Picasso.with(getApplicationContext()).load(cat.getImageUrl()).into(_catImageView);

        GestureDoubleTap<Cat> doubleTapGesture = new GestureDoubleTap<>(getBus(), cat);
        final GestureDetector gestureDetector = new GestureDetector(getApplicationContext(), doubleTapGesture);
        _catImageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return gestureDetector.onTouchEvent(motionEvent);
            }
        });
        _loadCatButton.setEnabled(true);
    }

    @Subscribe
    public void handleDoubleTap(GestureDoubleTap<Cat> gestureDoubleTap) {
        // do the double tap stuff (like)
        Toast.makeText(this, "Double Tap: " + gestureDoubleTap.getObject().getImageUrl(), Toast.LENGTH_SHORT).show();
    }

    private Bus getBus() {
        return _bus;
    }
}
