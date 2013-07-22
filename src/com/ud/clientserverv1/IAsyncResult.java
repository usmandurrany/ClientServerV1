package com.ud.clientserverv1;

import java.util.List;

import android.graphics.Bitmap;

public interface IAsyncResult {
void resultTitle(String[] listTitle, String[] listImg);
void resultHeadlines(List<String> listTitle);
void resultHeadImg(List<Bitmap> listImg);
void resultLink(String url, String desc);
void resultDesc(String str, Bitmap image);
}
