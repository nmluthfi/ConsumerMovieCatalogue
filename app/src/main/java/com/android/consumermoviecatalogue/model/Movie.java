package com.android.consumermoviecatalogue.model;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.android.consumermoviecatalogue.database.MovieContract.MovieColumns;

import static android.provider.BaseColumns._ID;
import static com.android.consumermoviecatalogue.database.MovieContract.getColumnDouble;
import static com.android.consumermoviecatalogue.database.MovieContract.getColumnInt;
import static com.android.consumermoviecatalogue.database.MovieContract.getColumnString;

public class Movie implements Parcelable {

    private int id, genreId;
    private Double userScore;
    private String title, description, dateOfRelease, imgPhoto, backdropPhoto;

    public Movie(Cursor cursor) {
        this.id = getColumnInt(cursor, _ID);
        this.userScore = getColumnDouble(cursor, MovieColumns.userScore);
        this.title = getColumnString(cursor, MovieColumns.title);
        this.description = getColumnString(cursor, MovieColumns.description);
        this.dateOfRelease = getColumnString(cursor, MovieColumns.dateOfRelease);
        this.imgPhoto = getColumnString(cursor, MovieColumns.imgPhoto);
        this.backdropPhoto = getColumnString(cursor, MovieColumns.backdropPhoto);
        this.genreId = getColumnInt(cursor, MovieColumns.genreId);
    }

    public Movie(int id, int genreId, Double userScore, String title, String description, String dateOfRelease, String imgPhoto, String backdropPhoto) {
        this.id = id;
        this.genreId = genreId;
        this.userScore = userScore;
        this.title = title;
        this.description = description;
        this.dateOfRelease = dateOfRelease;
        this.imgPhoto = imgPhoto;
        this.backdropPhoto = backdropPhoto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    public Double getUserScore() {
        return userScore;
    }

    public void setUserScore(Double userScore) {
        this.userScore = userScore;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateOfRelease() {
        return dateOfRelease;
    }

    public void setDateOfRelease(String dateOfRelease) {
        this.dateOfRelease = dateOfRelease;
    }

    public String getImgPhoto() {
        return imgPhoto;
    }

    public void setImgPhoto(String imgPhoto) {
        this.imgPhoto = imgPhoto;
    }

    public String getBackdropPhoto() {
        return backdropPhoto;
    }

    public void setBackdropPhoto(String backdropPhoto) {
        this.backdropPhoto = backdropPhoto;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeInt(this.genreId);
        dest.writeValue(this.userScore);
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeString(this.dateOfRelease);
        dest.writeString(this.imgPhoto);
        dest.writeString(this.backdropPhoto);
    }

    public Movie() {
    }

    protected Movie(Parcel in) {
        this.id = in.readInt();
        this.genreId = in.readInt();
        this.userScore = (Double) in.readValue(Double.class.getClassLoader());
        this.title = in.readString();
        this.description = in.readString();
        this.dateOfRelease = in.readString();
        this.imgPhoto = in.readString();
        this.backdropPhoto = in.readString();
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
