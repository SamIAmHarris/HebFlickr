package com.samiamharris.hebflickr.feature.home;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.samiamharris.hebflickr.R;
import com.samiamharris.hebflickr.base.BaseActivity;
import com.samiamharris.hebflickr.feature.image_viewer.ImageViewerActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity
        extends BaseActivity<HomeContract.View, HomeContract.Repository, HomeContract.Presenter>
        implements HomeContract.View {


    @BindView(R.id.button_home)
    Button homeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        homeButton.setOnClickListener(view -> presenter.onUserTapMainButton());
    }

    @Override
    protected HomePresenter initPresenter() {
        return new HomePresenter();
    }

    @Override
    protected HomeRepository initRepository() {
        return new HomeRepository();
    }

    @Override
    public void navigateToImageViewer() {
        Intent imageViewerIntent =
                new Intent(HomeActivity.this, ImageViewerActivity.class);
        startActivity(imageViewerIntent);
    }
}
