package com.android.consumermoviecatalogue;


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
import android.widget.Toast;

import com.android.consumermoviecatalogue.adapter.recycler_view.MovieAdapter;
import com.android.consumermoviecatalogue.database.MovieContract.MovieColumns;
import com.android.consumermoviecatalogue.interfaces.LoadMovieCallback;
import com.android.consumermoviecatalogue.model.Movie;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import static com.android.consumermoviecatalogue.utils.MappingHelper.mapCursorMovieToArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment implements LoadMovieCallback {

    private static final String EXTRA_STATE = "EXTRA_STATE";

    private MovieAdapter movieAdapter;
    private DataObserver myObserver;
    private static HandlerThread handlerThread;

    private RecyclerView rvFavoriteMovie;
    private ProgressBar progressBar;
    private TextView tvEmptyState;

    public MovieFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initComponent(view, savedInstanceState);
    }

    private void initComponent(View view, Bundle savedInstanceState) {
        progressBar = view.findViewById(R.id.pb_load_favorite_movie);

        tvEmptyState = view.findViewById(R.id.tv_empty_favorite_movie);

        rvFavoriteMovie = view.findViewById(R.id.rv_favorite_movie);
        rvFavoriteMovie.setLayoutManager(new LinearLayoutManager(getContext()));
        rvFavoriteMovie.setHasFixedSize(true);

        movieAdapter = new MovieAdapter(getActivity());
        rvFavoriteMovie.setAdapter(movieAdapter);

        handlerThread = new HandlerThread("DataObserver");
        handlerThread.start();

        Handler handler = new Handler(handlerThread.getLooper());
        myObserver = new DataObserver(handler, getContext());

        getActivity().getContentResolver()
                .registerContentObserver(MovieColumns.CONTENT_URI, true, myObserver);

        if (savedInstanceState == null) {
            new getData(getContext(), this).execute();
        } else {
            ArrayList<Movie> list = savedInstanceState.getParcelableArrayList(EXTRA_STATE);
            if (list != null) {
                movieAdapter.setmData(list);
            }
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelableArrayList(EXTRA_STATE, movieAdapter.getmData());
        super.onSaveInstanceState(outState);
    }

    @Override
    public void postExecute(Cursor cursor) {
        progressBar.setVisibility(View.GONE);
        ArrayList<Movie> movies = mapCursorMovieToArrayList(cursor);
        if (movies != null) {
            if (movies.size() > 0) {
                movieAdapter.setmData(movies);
                tvEmptyState.setVisibility(View.GONE);
                progressBar.setVisibility(View.GONE);
                Log.d("MOVIE TIDAK NULL", " MOVIE TIDAK NULL");
            } else {
                tvEmptyState.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
                movieAdapter.setmData(new ArrayList<Movie>());
                Toast.makeText(getContext(), "Tidak Ada data saat ini", Toast.LENGTH_SHORT).show();
                Log.d("MOVIE NULL", " MOVIE NULL");
            }
        } else {
            tvEmptyState.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
            movieAdapter.setmData(new ArrayList<Movie>());
            Toast.makeText(getContext(), "Tidak Ada data saat ini", Toast.LENGTH_SHORT).show();
            Log.d("MOVIE NULL", " MOVIE NULL");
        }
        Log.d("postExecute", "postExecute");
    }

    private static class getData extends AsyncTask <Void, Void, Cursor> {

        private final WeakReference<Context> weakContext;
        private final WeakReference<LoadMovieCallback> weakCallback;

        private getData(Context context, LoadMovieCallback callback) {
            weakContext = new WeakReference<>(context);
            weakCallback = new WeakReference<>(callback);
        }

        @Override
        protected Cursor doInBackground(Void... voids) {
            return weakContext.get()
                    .getContentResolver()
                    .query(MovieColumns.CONTENT_URI, null, null, null, null);
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
