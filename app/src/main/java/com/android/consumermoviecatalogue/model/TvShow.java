package com.android.consumermoviecatalogue.model;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.android.consumermoviecatalogue.database.tv_show.TvShowContract.TvColumns;

import static android.provider.BaseColumns._ID;
import static com.android.consumermoviecatalogue.database.tv_show.TvShowContract.getColumnDouble;
import static com.android.consumermoviecatalogue.database.tv_show.TvShowContract.getColumnInt;
import static com.android.consumermoviecatalogue.database.tv_show.TvShowContract.getColumnString;

public class TvShow implements Parcelable {
    private int id, genreId;
    private Double userScore;
    private String title, description, dateOfFirstAir, imgPhoto, backdropPhoto;

    public TvShow(int id, int genreId, Double userScore, String title, String description, String dateOfFirstAir, String imgPhoto, String backdropPhoto) {
        this.id = id;
        this.genreId = genreId;
        this.userScore = userScore;
        this.title = title;
        this.description = description;
        this.dateOfFirstAir = dateOfFirstAir;
        this.imgPhoto = imgPhoto;
        this.backdropPhoto = backdropPhoto;
    }

    public TvShow(Cursor cursor) {
        this.id = getColumnInt(cursor, _ID);
        this.userScore = getColumnDouble(cursor, TvColumns.userScore);
        this.title = getColumnString(cursor, TvColumns.title);
        this.description = getColumnString(cursor, TvColumns.description);
        this.dateOfFirstAir = getColumnString(cursor, TvColumns.dateOfRelease);
        this.imgPhoto = getColumnString(cursor, TvColumns.imgPhoto);
        this.backdropPhoto = getColumnString(cursor, TvColumns.backdropPhoto);
        this.genreId = getColumnInt(cursor, TvColumns.genreId);
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

    public String getDateOfFirstAir() {
        return dateOfFirstAir;
    }

    public void setDateOfFirstAir(String dateOfFirstAir) {
        this.dateOfFirstAir = dateOfFirstAir;
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
        dest.writeString(this.dateOfFirstAir);
        dest.writeString(this.imgPhoto);
        dest.writeString(this.backdropPhoto);
    }

    public TvShow() {
    }

    protected TvShow(Parcel in) {
        this.id = in.readInt();
        this.genreId = in.readInt();
        this.userScore = (Double) in.readValue(Double.class.getClassLoader());
        this.title = in.readString();
        this.description = in.readString();
        this.dateOfFirstAir = in.readString();
        this.imgPhoto = in.readString();
        this.backdropPhoto = in.readString();
    }

    public static final Parcelable.Creator<TvShow> CREATOR = new Parcelable.Creator<TvShow>() {
        @Override
        public TvShow createFromParcel(Parcel source) {
            return new TvShow(source);
        }

        @Override
        public TvShow[] newArray(int size) {
            return new TvShow[size];
        }
    };
}
