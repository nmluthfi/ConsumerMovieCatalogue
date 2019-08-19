package com.android.consumermoviecatalogue.adapter.recycler_view;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.consumermoviecatalogue.R;
import com.android.consumermoviecatalogue.model.Movie;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private Activity activity;
    private ArrayList<Movie> mData = new ArrayList<>();

    public MovieAdapter(Activity activity) {
        this.activity = activity;
    }

    public void setmData(ArrayList<Movie> mData) {
        this.mData.clear();
        this.mData.addAll(mData);
        notifyDataSetChanged();
    }

    public ArrayList<Movie> getmData() {
        return mData;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list, viewGroup,
                false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder movieViewHolder, final int position) {
        final Movie movie = mData.get(position);
        movieViewHolder.tvTitle.setText(movie.getTitle());
        movieViewHolder.tvDescription.setText(movie.getDescription());
        movieViewHolder.tvUserScore.setText(String.format(
                "%s" + activity.getString(R.string.user_score),
                movie.getUserScore()));

        Glide.with(activity)
                .load(movie.getImgPhoto())
                .apply(new RequestOptions().override(100, 150))
                .into(movieViewHolder.ivPoster);

        movieViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openItemDetailActivity(mData.get(position), position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    private void openItemDetailActivity(Movie movie, int position) {
//        Intent startMoveDetailActivityyIntent = new Intent(mContext, ItemDetailActivity.class);
//
//        Uri uri = Uri.parse(MovieColumns.CONTENT_URI + "/" + getmData().get(position).getId());
//        startMoveDetailActivityyIntent.setData(uri);
//        startMoveDetailActivityyIntent.putExtra(ItemDetailActivity.EXTRA_MOVIE, movie);
//        startMoveDetailActivityyIntent.putExtra(ItemDetailActivity.EXTRA_CATEGORY, "Movie");
//        mContext.startActivity(startMoveDetailActivityyIntent);
    }

    public void filterList(ArrayList<Movie> filterdNames) {
        this.mData = filterdNames;
        notifyDataSetChanged();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle, tvDescription, tvUserScore;
        ImageView ivPoster;
        CardView cardView;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDescription = itemView.findViewById(R.id.tv_description);
            ivPoster = itemView.findViewById(R.id.iv_poster);
            tvUserScore = itemView.findViewById(R.id.tv_user_score);
            cardView = itemView.findViewById(R.id.cv_item);
        }
    }

}