package com.ud.headlines;

import android.graphics.Bitmap;

import java.util.List;

public interface IAsyncResult {
    void resultTitle(String[] listTitle, String[] listImg);

    void resultHeadlines(List<String> listTitle);

    void resultHeadImg(List<Bitmap> listImg);

    void resultLink(String url, String desc);

    void resultDesc(String str, Bitmap image);
}
