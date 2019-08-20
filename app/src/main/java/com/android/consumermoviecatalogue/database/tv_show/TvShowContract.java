package com.android.consumermoviecatalogue.database.tv_show;

import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

public class TvShowContract {
    private static final String SCHEME = "content";
    public static final String AUTHORITY = "com.android.example.moviewcatalogue.database";

    public static String TABLE_TV_SHOW = "tv_show";

    public static final class TvColumns implements BaseColumns {

        public static String title = "title";
        public static String description = "description";
        public static String dateOfRelease = "dateOfRelease";
        public static String imgPhoto = "imgPhoto";
        public static String backdropPhoto = "backdropPhoto";
        public static String userScore = "userScore";
        public static String genreId = "genreId";

        public static final Uri CONTENT_URI = new Uri.Builder().scheme(SCHEME)
                .authority(AUTHORITY)
                .appendPath(TABLE_TV_SHOW)
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
