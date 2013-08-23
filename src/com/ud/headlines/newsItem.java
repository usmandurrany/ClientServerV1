package com.ud.headlines;

import android.graphics.Bitmap;

public class newsItem {
    public Bitmap imageId;
    public String title;


    public newsItem(Bitmap imageId, String title) {
        this.imageId = imageId;
        this.title = title;
    }

    public newsItem(String title) {
        this.title = title;
    }

    public newsItem(Bitmap imageId) {
        this.imageId = imageId;
    }

    public Bitmap getImageId() {
        return imageId;
    }

    public void setImageId(Bitmap imageId) {
        this.imageId = imageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}