package org.otfusion.votecats.ui.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import org.otfusion.votecats.common.model.Cat;
import org.otfusion.votecats.providers.CatLoadedEvent;
import org.otfusion.votecats.service.CatServiceImpl;

import javax.inject.Inject;

import otfusion.org.votecats.R;

public class MainActivity extends CatActivity {

    @Inject
    CatServiceImpl _catService;

    @Inject
    Bus _bus;

    private Button _loadCatButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadUIElements();
        _bus.register(this);
    }

    // TODO inject ui elements
    private void loadUIElements() {
        _loadCatButton = (Button) findViewById(R.id.load_cat_button);
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
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Subscribe
    public void handleCatLoadedEvent(CatLoadedEvent catLoadedEvent) {
        Cat cat = catLoadedEvent.getCat();
        Toast.makeText(this, cat.getImageUrl(), Toast.LENGTH_LONG).show();
        _loadCatButton.setEnabled(true);
    }
}
