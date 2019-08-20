package com.android.consumermoviecatalogue.interfaces;

import android.database.Cursor;

public interface LoadTvShowCallback {
    void postExecute(Cursor cursor);
}
