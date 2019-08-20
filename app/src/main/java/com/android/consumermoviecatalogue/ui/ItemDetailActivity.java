package com.android.consumermoviecatalogue.ui;

import android.app.Dialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.consumermoviecatalogue.R;
import com.android.consumermoviecatalogue.database.MovieContract.MovieColumns;
import com.android.consumermoviecatalogue.model.Movie;
import com.android.consumermoviecatalogue.utils.GenreChecks;
import com.bumptech.glide.Glide;

public class ItemDetailActivity extends AppCompatActivity {

    public static final String EXTRA_MOVIE = "extra_movie", EXTRA_TV_SHOW = "extra_tv_show",
            EXTRA_CATEGORY = "extra_category";

    private Movie movie;
//    private TvShow tvShow;

    private TextView tvTitle, tvDescription, tvUserScore, tvDateOfRelease, tvFailedLoadData, tvGenre;
    private ImageView ivBackdrop;
    private ProgressBar pbLoadData;
    private Menu menu;
    private Dialog dialogShowPhotoFullscreen;

    private ContentResolver resolver;

    private boolean isAlreadyLoved = false;
    private String cateogry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        initComponent();

        Intent intentThatStartThisActivity = getIntent();
        if (intentThatStartThisActivity != null) {
            movie = intentThatStartThisActivity.getParcelableExtra(EXTRA_MOVIE);
//            tvShow = intentThatStartThisActivity.getParcelableExtra(EXTRA_TV_SHOW);
            cateogry = intentThatStartThisActivity.getStringExtra(EXTRA_CATEGORY);
            Log.d("cateogry: ", cateogry);

            pbLoadData.setVisibility(View.VISIBLE);
            if (movie != null) {
                showMovieData(movie);
            }
//            else if (tvShow != null) {
//                showTvShowData(tvShow);
//            }
        else {
                tvFailedLoadData.setVisibility(View.VISIBLE);
            }
        }
        pbLoadData.setVisibility(View.GONE);

        Uri uri = getIntent().getData();
        if (uri != null) {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
            Log.d("id: ", "Id: " + getIntent().getData());
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    if (cateogry.equalsIgnoreCase("movie")) {
                        movie = new Movie(cursor);
                    } else if (cateogry.equalsIgnoreCase("Tv Show")) {
//                        tvShow = new TvShow(cursor);
                    }
                }
                cursor.close();
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (movie != null) {
            isAlreadyLoved = true;
        }
//        else if (tvShow != null) {
//            isAlreadyLoved = true;
//        }
        Log.d("IsAlreadyLove", String.valueOf(isAlreadyLoved));
    }

//    private void showTvShowData(final TvShow tvShow) {
//        tvTitle.setText(tvShow.getTitle());
//        tvDescription.setText(tvShow.getDescription());
//        tvUserScore.setText(String.format("%s" + getString(R.string.user_score), tvShow.getUserScore()));
//        tvDateOfRelease.setText(tvShow.getDateOfFirstAir());
//        tvGenre.setText(GenreChecks.MovieGenre(tvShow.getGenreId()));
//
//        Glide.with(this)
//                .load(tvShow.getBackdropPhoto())
//                .into(ivBackdrop);
//
//        ActionBar actionBar = getSupportActionBar();
//        if (actionBar != null) {
//            actionBar.setDisplayHomeAsUpEnabled(true);
//            actionBar.setTitle(tvShow.getTitle());
//        }
//
//        ivBackdrop.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                showFullscreenPhoto(tvShow.getBackdropPhoto());
//            }
//        });
//    }

    private void showMovieData(final Movie movie) {
        tvTitle.setText(movie.getTitle());
        tvDescription.setText(movie.getDescription());
        tvUserScore.setText(String.format("%s" + getString(R.string.user_score), movie.getUserScore()));
        tvDateOfRelease.setText(movie.getDateOfRelease());
        tvGenre.setText(GenreChecks.MovieGenre(movie.getGenreId()));

        Glide.with(this)
                .load(movie.getBackdropPhoto())
                .into(ivBackdrop);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(movie.getTitle());
        }

        ivBackdrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFullscreenPhoto(movie.getBackdropPhoto());
            }
        });
    }

    public void showFullscreenPhoto(String backdropPhoto) {
        dialogShowPhotoFullscreen.setContentView(R.layout.dialog_poster_fullscreen);

        ImageView ivPhotoFullScreen = dialogShowPhotoFullscreen.findViewById(R.id.iv_photo_fullscreen);

        Glide.with(getBaseContext())
                .load(backdropPhoto)
                .override(600, 250)
                .into(ivPhotoFullScreen);

        dialogShowPhotoFullscreen.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogShowPhotoFullscreen.show();

        dialogShowPhotoFullscreen.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                dialog.dismiss();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item_detail, menu);
        this.menu = menu;
        setFavorite();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_favorite) {
            if (cateogry.equalsIgnoreCase("Movie")) {
                if (isAlreadyLoved) {
                    isAlreadyLoved = false;
                    unFavoriteMovie();
                    setFavorite();
                }
            } else if (cateogry.equalsIgnoreCase("Tv Show")) {
                if (isAlreadyLoved) {
                    isAlreadyLoved = false;
//                    unFavoriteTvShow();
                    setFavorite();
                }
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setFavorite() {
        MenuItem favorite = menu.findItem(R.id.action_favorite);
        if (isAlreadyLoved) {
            favorite.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_favorite_white));
        } else {
            favorite.setIcon(ContextCompat.getDrawable(this, R.drawable.ic_favorite_border_white));
        }
    }

    private void unFavoriteMovie(){
        Uri uri = getIntent().getData();
        resolver.delete(uri, null, null);
        resolver.notifyChange(MovieColumns.CONTENT_URI, new MovieFragment.DataObserver(new Handler(), ItemDetailActivity.this));
    }

    private void initComponent() {
        tvTitle = findViewById(R.id.tv_title);
        tvDateOfRelease = findViewById(R.id.tv_date_of_release);
        tvDescription = findViewById(R.id.tv_description);
        tvUserScore = findViewById(R.id.tv_user_score);
        tvGenre = findViewById(R.id.tv_genre);

        tvFailedLoadData = findViewById(R.id.tv_failed_load_data);

        ivBackdrop = findViewById(R.id.iv_poster_backdrop);

        pbLoadData = findViewById(R.id.pb_loading_detail_data);

        dialogShowPhotoFullscreen = new Dialog(this, android.R.style.Theme_Light);

        resolver = getContentResolver();
    }
}
