package com.android.consumermoviecatalogue.ui;


import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.consumermoviecatalogue.R;
import com.android.consumermoviecatalogue.adapter.recycler_view.TvShowAdapter;
import com.android.consumermoviecatalogue.database.movie.MovieContract;
import com.android.consumermoviecatalogue.database.tv_show.TvShowContract.TvColumns;
import com.android.consumermoviecatalogue.interfaces.LoadTvShowCallback;
import com.android.consumermoviecatalogue.model.TvShow;
import com.android.consumermoviecatalogue.utils.MappingHelper;

import java.lang.ref.WeakReference;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class TvShowFragment extends Fragment implements LoadTvShowCallback{

    private static final String EXTRA_STATE = "EXTRA_STATE";

    private TvShowAdapter tvShowAdapter;
    private DataObserver myObserver;
    private static HandlerThread handlerThread;
    Handler handler;

    private RecyclerView rvFavoriteTvShow;
    private ProgressBar progressBar;
    private TextView tvEmptyState;

    public TvShowFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_show, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initComponent(view, savedInstanceState);
    }

    private void initComponent(View view, Bundle savedInstanceState) {
        progressBar = view.findViewById(R.id.pb_load_favorite_movie);

        tvEmptyState = view.findViewById(R.id.tv_empty_favorite_movie);

        rvFavoriteTvShow = view.findViewById(R.id.rv_favorite_movie);
        rvFavoriteTvShow.setLayoutManager(new LinearLayoutManager(getContext()));
        rvFavoriteTvShow.setHasFixedSize(true);

        tvShowAdapter = new TvShowAdapter(getActivity());
        rvFavoriteTvShow.setAdapter(tvShowAdapter);

        handlerThread = new HandlerThread("DataObserver");
        handlerThread.start();

        handler = new Handler(handlerThread.getLooper());
        myObserver = new TvShowFragment.DataObserver(handler, getContext());

        getActivity().getContentResolver()
                .registerContentObserver(MovieContract.MovieColumns.CONTENT_URI, true, myObserver);

        if (savedInstanceState == null) {
            new TvShowFragment.getData(getContext(), this).execute();
        } else {
            ArrayList<TvShow> list = savedInstanceState.getParcelableArrayList(EXTRA_STATE);
            if (list != null) {
                tvShowAdapter.setmData(list);
            }
        }
    }

    @Override
    public void postExecute(Cursor cursor) {
        progressBar.setVisibility(View.GONE);
        ArrayList<TvShow> tvShows = MappingHelper.mapCursorTvShowToArrayList(cursor);
        if (tvShows != null) {
            if (tvShows.size() > 0) {
                tvShowAdapter.setmData(tvShows);
                tvEmptyState.setVisibility(View.GONE);
                progressBar.setVisibility(View.GONE);
                Log.d("MOVIE TIDAK NULL", " MOVIE TIDAK NULL");
            } else {
                tvEmptyState.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
                tvShowAdapter.setmData(new ArrayList<TvShow>());
                Log.d("MOVIE NULL", " MOVIE NULL");
            }
        } else {
            tvEmptyState.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
            tvShowAdapter.setmData(new ArrayList<TvShow>());
            Log.d("MOVIE NULL", " MOVIE NULL");
        }
    }

    private static class getData extends AsyncTask<Void, Void, Cursor> {

        private final WeakReference<Context> weakContext;
        private final WeakReference<LoadTvShowCallback> weakCallback;

        private getData(Context context, LoadTvShowCallback callback) {
            weakContext = new WeakReference<>(context);
            weakCallback = new WeakReference<>(callback);
        }

        @Override
        protected Cursor doInBackground(Void... voids) {
            return weakContext.get()
                    .getContentResolver()
                    .query(TvColumns.CONTENT_URI, null, null, null, null);
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            super.onPostExecute(cursor);
            weakCallback.get().postExecute(cursor);
        }
    }

    static class DataObserver extends ContentObserver {
        final Context context;

        public DataObserver(Handler handler, Context context) {
            super(handler);
            this.context = context;
        }

        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
        }
    }
}
