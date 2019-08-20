package com.android.consumermoviecatalogue.utils;

import android.database.Cursor;

import com.android.consumermoviecatalogue.model.Movie;
import com.android.consumermoviecatalogue.model.TvShow;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.android.consumermoviecatalogue.database.movie.MovieContract.MovieColumns;
import static com.android.consumermoviecatalogue.database.tv_show.TvShowContract.TvColumns;

public class MappingHelper {

    public static ArrayList<Movie> mapCursorMovieToArrayList(Cursor cursor) {
        ArrayList<Movie> movies = new ArrayList<>();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(_ID));
            double userScore = cursor.getDouble(cursor.getColumnIndexOrThrow(MovieColumns.userScore));

            String title = cursor.getString(cursor.getColumnIndexOrThrow(MovieColumns.title));
            String description = cursor.getString(cursor.getColumnIndexOrThrow(MovieColumns.description));
            String dateOfRelase = cursor.getString(cursor.getColumnIndexOrThrow(MovieColumns.dateOfRelease));
            String photoUrl = cursor.getString(cursor.getColumnIndexOrThrow(MovieColumns.imgPhoto));
            String backdropUrl = cursor.getString(cursor.getColumnIndexOrThrow(MovieColumns.backdropPhoto));
            int firstGenre = cursor.getInt(cursor.getColumnIndexOrThrow(MovieColumns.genreId));
            movies.add(new Movie(id, firstGenre, userScore, title, description, dateOfRelase,
                    photoUrl, backdropUrl));
        }
        return movies;
    }

    public static ArrayList<TvShow> mapCursorTvShowToArrayList(Cursor cursor) {
        ArrayList<TvShow> tvShows = new ArrayList<>();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(_ID));
            double userScore = cursor.getDouble(cursor.getColumnIndexOrThrow(TvColumns.userScore));

            String title = cursor.getString(cursor.getColumnIndexOrThrow(TvColumns.title));
            String description = cursor.getString(cursor.getColumnIndexOrThrow(TvColumns.description));
            String dateOfRelase = cursor.getString(cursor.getColumnIndexOrThrow(TvColumns.dateOfRelease));
            String photoUrl = cursor.getString(cursor.getColumnIndexOrThrow(TvColumns.imgPhoto));
            String backdropUrl = cursor.getString(cursor.getColumnIndexOrThrow(TvColumns.backdropPhoto));
            int firstGenre = cursor.getInt(cursor.getColumnIndexOrThrow(TvColumns.genreId));
            tvShows.add(new TvShow(id, firstGenre, userScore, title, description, dateOfRelase,
                    photoUrl, backdropUrl));
        }
        return tvShows;
    }
}
