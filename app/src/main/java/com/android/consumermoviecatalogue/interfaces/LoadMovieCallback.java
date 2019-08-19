package com.android.consumermoviecatalogue.interfaces;

import android.database.Cursor;

public interface LoadMovieCallback {
    void postExecute(Cursor cursor);
}
