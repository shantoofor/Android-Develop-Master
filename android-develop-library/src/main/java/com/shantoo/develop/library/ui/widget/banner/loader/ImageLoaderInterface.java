package com.shantoo.develop.library.ui.widget.banner.loader;

import android.content.Context;
import android.view.View;

import java.io.Serializable;

public interface ImageLoaderInterface<T extends View> extends Serializable {

    void displayImage(Context context, Object path, T imageView);

    T createImageView(Context context);
}
