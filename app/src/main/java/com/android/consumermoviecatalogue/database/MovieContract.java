package com.android.consumermoviecatalogue.database;

import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

public class MovieContract {

    private static final String SCHEME = "content";
    public static final String AUTHORITY = "com.android.example.moviewcatalogue";

    public static String TABLE_MOVIE = "movie";

    public MovieContract() {
    }

    public static final class MovieColumns implements BaseColumns {

        public static String title = "title";
        public static String description = "description";
        public static String dateOfRelease = "dateOfRelease";
        public static String imgPhoto = "imgPhoto";
        public static String backdropPhoto = "backdropPhoto";
        public static String userScore = "userScore";
        public static String genreId = "genreId";


        public static final Uri CONTENT_URI = new Uri.Builder().scheme(SCHEME)
                .authority(AUTHORITY)
                .appendPath(TABLE_MOVIE)
                .build();

    }

    public static String getColumnString(Cursor cursor, String columnName) {
        return cursor.getString(cursor.getColumnIndex(columnName));
    }

    public static int getColumnInt(Cursor cursor, String columnName) {
        return cursor.getInt(cursor.getColumnIndex(columnName));
    }

    public static Double getColumnDouble(Cursor cursor, String columnName) {
        return cursor.getDouble(cursor.getColumnIndex(columnName));
    }
}
