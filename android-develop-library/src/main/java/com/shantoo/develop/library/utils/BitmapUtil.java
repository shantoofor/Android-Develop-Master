package com.shantoo.develop.library.utils;

import android.graphics.Bitmap;

import java.nio.ByteBuffer;

/**
 * 作者: shantoo on 2017/4/22 15:54.
 */

public class BitmapUtil {

    public static byte[] bitmapToByteArray(Bitmap bitmap){
        int bytes = bitmap.getByteCount();
        ByteBuffer buf = ByteBuffer.allocate(bytes);
        bitmap.copyPixelsToBuffer(buf);
        byte[] byteArray = buf.array();
        return byteArray;
    }

    public static Bitmap byteArrayToBitmap(int width,int height,byte[] byteArray){
        // use Bitmap.Config.ARGB_8888 instead of type is OK
        Bitmap stitchBmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        stitchBmp.copyPixelsFromBuffer(ByteBuffer.wrap(byteArray));
        return stitchBmp;
    }
}
