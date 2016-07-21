package org.otfusion.caturday.ui.fragments;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.squareup.otto.Subscribe;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.otfusion.caturday.R;
import org.otfusion.caturday.common.model.Cat;
import org.otfusion.caturday.events.CatLoadedEvent;
import org.otfusion.caturday.events.FavoriteCatEvent;
import org.otfusion.caturday.events.LoadErrorEvent;
import org.otfusion.caturday.ui.views.ImageDoubleTapView;
import org.otfusion.caturday.util.UIUtils;

import butterknife.BindView;

public class MainFragment extends BaseFragment {

    public static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    public static final String FRAGMENT_TAG = "main";

    @BindView(R.id.cat_view)
    ImageDoubleTapView mCatImageView;

    @BindView(R.id.load_cat_button)
    Button mLoadCatButton;

    @BindView(R.id.favorite_cat_button)
    Button mFavoriteCatButton;

    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;

    private Cat mCurrentCat;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        setRetainInstance(true);
    }

    @Override
    public int getContentLayoutId() {
        return R.layout.fragment_main;
    }

    private void loadCat() {
        mProgressBar.setVisibility(View.VISIBLE);
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
                BitmapDrawable drawable = (BitmapDrawable) mCatImageView.getDrawable();
                Bitmap bitmap = drawable.getBitmap();
                if (bitmap != null) {
                    String filePathFromMediaStore = getFilePathFromMediaStore(bitmap);
                    Intent shareImageIntent = obtainShareImageIntent(Uri.parse(filePathFromMediaStore));
                    startActivity(Intent.createChooser(shareImageIntent, "Share this cat:"));
                } else {
                    UIUtils.showSnackbar(getView(), "Could not share cat");
                }
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
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case REQUEST_EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    saveCat(mCurrentCat);
                } else {
                    String message = "You will not be able to save kitties if you dont give me permissions to read/write files.";
                    Snackbar.make(getView(), message, Snackbar.LENGTH_LONG)
                            .setAction("Settings", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                    intent.setData(Uri.parse("package:" + getContext().getPackageName()));
                                    startActivity(intent);
                                }
                            }).show();
                }
                break;
        }
    }

    @Override
    public int getTitleId() {
        return R.string.app_name;
    }

    private void loadImage() {
        Context context = getContext();
        Picasso.with(context).load(mCurrentCat.getImageUrl()).into(mCatImageView,
                new Callback() {
                    @Override
                    public void onSuccess() {
                        enableLoadButton();
                        mCatImageView.setEvent(new FavoriteCatEvent(getBus(), mCurrentCat));
                        mProgressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError() {
                        enableLoadButton();
                        mProgressBar.setVisibility(View.GONE);
                    }

                    private void enableLoadButton() {
                        mLoadCatButton.setEnabled(true);
                    }
                });
    }

    private void saveCat(Cat cat) {
        boolean permission = checkWriteExternalStoragePermission();
        if (permission) {
            getCatService().saveCatToFavorites(cat);
            loadCat();
            UIUtils.showSnackbar(getView(), "Saving right meow!");
        }
    }

    private boolean checkWriteExternalStoragePermission() {
        int permission = ContextCompat.checkSelfPermission(getCatActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            if (shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                String permissionText = "You will not be able to save kitties if you dont give me permissions to read/write files.";
                UIUtils.showSnackbar(getView(), permissionText, "Okay",
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                requestPermissions(PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
                            }
                        });
                return false;
            }
            requestPermissions(PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
        }

        return permission == PackageManager.PERMISSION_GRANTED;
    }

    @Subscribe
    @SuppressWarnings("unused") // used by the bus
    public void handleCatLoadedEvent(CatLoadedEvent catLoadedEvent) {
        mCurrentCat = catLoadedEvent.getCat();
        loadImage();
    }

    @Subscribe
    @SuppressWarnings("unused") // used by the bus
    public void handleFavoriteCatEvent(FavoriteCatEvent favoriteCatEvent) {
        Cat cat = favoriteCatEvent.getCat();
        if (getCatService().isCatInFavorites(cat)) {
            UIUtils.showSnackbar(getView(), "That cat is already in your collection");
        } else {
            saveCat(cat);
        }
    }

    @Subscribe
    @SuppressWarnings("unused") // used by the bus
    public void handleLoadingErrorEvent(LoadErrorEvent loadErrorEvent) {
        UIUtils.showSnackbar(getView(), "Error while fetching cats, try again please.");
        mLoadCatButton.setEnabled(true);
        mProgressBar.setVisibility(View.GONE);
        // show error kitty?
        // nudge 'load' button for more "visual" info?
    }
}
