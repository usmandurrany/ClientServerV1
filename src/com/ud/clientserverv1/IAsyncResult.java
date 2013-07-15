package com.ud.clientserverv1;

import java.util.List;

import android.graphics.Bitmap;

public interface IAsyncResult {
void resultTitle(List<String> listContents);
void resultLink(String url, String desc);
void resultDesc(String str, Bitmap image);
}
