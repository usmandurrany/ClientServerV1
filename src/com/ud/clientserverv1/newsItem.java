package com.ud.clientserverv1;

import android.graphics.Bitmap;

public class newsItem {
    private Bitmap imageId;
    private String title;

 
    public newsItem(Bitmap imageId, String title) {
        this.imageId = imageId;
        this.title = title;
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