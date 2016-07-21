package org.otfusion.caturday.ui.fragments;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import org.otfusion.caturday.R;
import org.otfusion.caturday.application.VoteCatsApplication;
import org.otfusion.caturday.common.model.Cat;
import org.otfusion.caturday.ui.activities.FavoriteImageActivity;
import org.otfusion.caturday.ui.adapters.DividerItemDecoration;
import org.otfusion.caturday.ui.adapters.FavoriteCatAdapter;
import org.otfusion.caturday.ui.adapters.ItemClickSupport;
import org.otfusion.caturday.util.UIUtils;

import butterknife.BindView;


public class FavoriteCatListFragment extends BaseFragment {

    public static final String FRAGMENT_TAG = "favorite";

    @BindView(R.id.list_view)
    RecyclerView mFavoritedView;

    private FavoriteCatAdapter mAdapter;

    private BottomSheetDialog mBottomSheetDialog;
    private Cat selectedCat;

    public static FavoriteCatListFragment newInstance() {
        return new FavoriteCatListFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        VoteCatsApplication voteCatsApplication = (VoteCatsApplication) getActivity().getApplication();
        voteCatsApplication.getApplicationComponent().inject(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public int getContentLayoutId() {
        return R.layout.fragment_favorite_list;
    }

    @Override
    public void loadUIContent() {
        setupBottomSheetDialog();
        setupRecycleView();
    }

    private void setupRecycleView() {
        RecyclerView.LayoutManager mFavoritedLayoutManager = new LinearLayoutManager(getContext());
        mFavoritedView.setLayoutManager(mFavoritedLayoutManager);
        registerForContextMenu(mFavoritedView);
        mAdapter = new FavoriteCatAdapter();
        mAdapter.updateCats(getCatService().getFavoriteCats());
        mFavoritedView.setAdapter(mAdapter);
        mFavoritedView.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL_LIST));

        ItemClickSupport.addTo(mFavoritedView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Intent intent = new Intent(getContext(), FavoriteImageActivity.class);
                intent.putExtra(FavoriteImageActivity.MODEL_KEY, mAdapter.getDataSet().get(position));
                startActivity(intent);
            }
        });

        ItemClickSupport.addTo(mFavoritedView).setOnItemLongClickListener(new ItemClickSupport.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClicked(RecyclerView recyclerView, int position, View v) {
                mBottomSheetDialog.show();
                selectedCat = mAdapter.getDataSet().get(position);
                return true;
            }
        });
    }

    private void setupBottomSheetDialog() {
        mBottomSheetDialog = new BottomSheetDialog(getActivity());
        View sheetView = getActivity().getLayoutInflater()
                .inflate(R.layout.bottom_sheet_dialog_favorite_list, null);
        mBottomSheetDialog.setContentView(sheetView);
        LinearLayout share = (LinearLayout) sheetView.findViewById(R.id.action_share);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleActionShareOption(selectedCat);
                selectedCat = null;
                mBottomSheetDialog.dismiss();
            }
        });

        LinearLayout delete = (LinearLayout) sheetView.findViewById(R.id.action_delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleActionDeleteOption(selectedCat);
                refreshFavoriteCats();
                selectedCat = null;
                mBottomSheetDialog.dismiss();
            }
        });
    }

    @Override
    public int getTitleId() {
        return R.string.title_activity_favorite;
    }

    private void refreshFavoriteCats() {
        mAdapter.updateCats(getCatService().getFavoriteCats());
    }

    private boolean handleActionDeleteOption(Cat cat) {
        catService.deleteFromFavorites(cat);
        refreshFavoriteCats();
        return true;
    }

    private boolean handleActionShareOption(Cat cat) {
        String filePath = getCatService().getCatFileName(cat, true);
        if (filePath.isEmpty()) {
            UIUtils.showToast(getContext(), "Could not retrieve the image, try saving it again.");
            return false;
        } else {
            Bitmap bmp = BitmapFactory.decodeFile(filePath);
            String mediaStoreFilePath = getFilePathFromMediaStore(bmp);
            Intent shareImageIntent = obtainShareImageIntent(Uri.parse(mediaStoreFilePath));
            startActivity(Intent.createChooser(shareImageIntent, "Share a cat!"));
            return true;
        }
    }

}
