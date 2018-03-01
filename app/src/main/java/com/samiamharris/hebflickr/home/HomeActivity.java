package com.samiamharris.hebflickr.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.samiamharris.hebflickr.R;
import com.samiamharris.hebflickr.image_viewer.ImageViewerActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity implements HomeContract.View {


    @BindView(R.id.button_home)
    Button homeButton;

    HomeContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        presenter = new HomePresenter();
        presenter.setView(this);

        homeButton.setOnClickListener(view -> presenter.onUserTapMainButton());
    }

    @Override
    public void navigateToImageViewer() {
        Intent imageViewerIntent = new Intent(HomeActivity.this, ImageViewerActivity.class);
        startActivity(imageViewerIntent);
    }
}
