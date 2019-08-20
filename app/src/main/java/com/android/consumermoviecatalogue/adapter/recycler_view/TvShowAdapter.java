package com.android.consumermoviecatalogue.adapter.recycler_view;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.consumermoviecatalogue.R;
import com.android.consumermoviecatalogue.database.tv_show.TvShowContract.TvColumns;
import com.android.consumermoviecatalogue.model.TvShow;
import com.android.consumermoviecatalogue.ui.ItemDetailActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class TvShowAdapter extends RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder> {

    private Activity activity;
    private ArrayList<TvShow> mData = new ArrayList<>();

    public TvShowAdapter(Activity activity) {
        this.activity = activity;
    }

    public void setmData(ArrayList<TvShow> mData) {
        this.mData.clear();
        this.mData.addAll(mData);
        notifyDataSetChanged();
    }

    public ArrayList<TvShow> getmData() {
        return mData;
    }

    @NonNull
    @Override
    public TvShowViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list, viewGroup,
                false);
        return new TvShowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TvShowViewHolder tvShowViewHolder, final int position) {
        final TvShow tvShow = mData.get(position);
        tvShowViewHolder.tvTitle.setText(tvShow.getTitle());
        tvShowViewHolder.tvDescription.setText(tvShow.getDescription());
        tvShowViewHolder.tvUserScore.setText(String.format(
                "%s" + activity.getString(R.string.user_score),
                tvShow.getUserScore()));

        Glide.with(activity)
                .load(tvShow.getImgPhoto())
                .apply(new RequestOptions().override(100, 150))
                .into(tvShowViewHolder.ivPoster);

        tvShowViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
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

    private void openItemDetailActivity(TvShow tvShow, int position) {
        Intent startMoveDetailActivityyIntent = new Intent(activity, ItemDetailActivity.class);

        Uri uri = Uri.parse(TvColumns.CONTENT_URI + "/" + getmData().get(position).getId());
        startMoveDetailActivityyIntent.setData(uri);
        startMoveDetailActivityyIntent.putExtra(ItemDetailActivity.EXTRA_TV_SHOW, tvShow);
        startMoveDetailActivityyIntent.putExtra(ItemDetailActivity.EXTRA_CATEGORY, "Tv Show");
        activity.startActivity(startMoveDetailActivityyIntent);
    }

    public void filterList(ArrayList<TvShow> filterdNames) {
        this.mData = filterdNames;
        notifyDataSetChanged();
    }

    public class TvShowViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle, tvDescription, tvUserScore;
        ImageView ivPoster;
        CardView cardView;

        public TvShowViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tv_title);
            tvDescription = itemView.findViewById(R.id.tv_description);
            ivPoster = itemView.findViewById(R.id.iv_poster);
            tvUserScore = itemView.findViewById(R.id.tv_user_score);
            cardView = itemView.findViewById(R.id.cv_item);
        }
    }
}
