package com.shantoo.develop.library.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.view.ContextThemeWrapper;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;

import com.shantoo.develop.library.R;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * 作者: shantoo on 2017/4/5 13:21.
 */

public class CamraUtil {

    private static File mFolder;
    private static String mImgName;
    private static int img_state = 0;
    private static int mWhereCode = 0;
    /**
     * 获取背景图片
     */
    public final static int IMG_BACKGROUND = 0;
    /**
     * 获取头像
     */
    public final static int IMG_PORTRAIT = 1;
    private static final int CAMERA = 0;
    private static final int ALBUM = 1;

    public static int getWhereCode() {
        return mWhereCode;
    }

    /**
     * 打开对话框
     **/
    public static void openPictureSelectDialog(final Activity activity, int model, int whereCode) {
        mWhereCode = whereCode;
        openPictureSelectDialog(activity, model);
    }

    public static void openPictureSelectDialog(final Activity activity, int model) {
        // 自定义Context,添加主题
        Context dialogContext = new ContextThemeWrapper(activity, android.R.style.Theme_Light);
        String[] choiceItems = new String[2];
        choiceItems[0] = "相机拍摄"; // 拍照
        choiceItems[1] = "本地相册"; // 从相册中选择
        ListAdapter adapter = new ArrayAdapter<>(dialogContext, R.layout.camra, choiceItems);
        // 对话框建立在刚才定义好的上下文上
        AlertDialog.Builder builder = new AlertDialog.Builder(dialogContext);

        if (model == IMG_BACKGROUND) {
            img_state = IMG_BACKGROUND;
            builder.setTitle("获取背景图片");
        } else if (model == IMG_PORTRAIT) {
            img_state = IMG_PORTRAIT;
            builder.setTitle("获取头像");
        }
        builder.setSingleChoiceItems(adapter, -1,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0: // 相机
                                getImgFromCamra(activity);
                                break;
                            case 1: // 从图库相册中选取
                                getImgFromAlbum(activity);
                                break;
                        }
                        dialog.dismiss();
                    }
                });
        builder.create().show();
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public static Bitmap setImage(Activity activity, int requestCode, int resultCode, Intent data, View backgroundImg, ImageView headpicImg) throws FileNotFoundException {
        Bitmap bm = null;
        if (resultCode == Activity.RESULT_OK && requestCode == CAMERA) {
            // 调用系统方法获取到的是被压缩过的图片，通过自定义路径轻松获取原始图片。
            bm = BitmapFactory.decodeFile(mFolder.getAbsolutePath() + File.separator + mImgName);
            if (img_state == IMG_BACKGROUND && backgroundImg != null) {
                backgroundImg.setBackground(new BitmapDrawable(bm));
            }
            if (img_state == IMG_PORTRAIT && headpicImg != null) {
                bm = ImageUtil.toRoundBitmap(bm);
                headpicImg.setImageBitmap(bm);
            }
        }
        if (resultCode == Activity.RESULT_OK && requestCode == ALBUM) {
            if (data != null) {
                // 获取本地相册图片
                Uri uri = data.getData();
                ContentResolver cr = activity.getContentResolver();
                bm = BitmapFactory.decodeStream(cr.openInputStream(uri));
                if (img_state == IMG_BACKGROUND && backgroundImg != null) {
                    backgroundImg.setBackground(new BitmapDrawable(bm));
                }
                if (img_state == IMG_PORTRAIT && headpicImg != null) {
                    bm = ImageUtil.toRoundBitmap(bm);
                    headpicImg.setImageBitmap(bm);
                    LogUtils.e("setImageBitmap");
                }
            }
        }
        return bm;
    }

    /*@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public static void setBackgroundImg(int requestCode, int resultCode, Intent data, View backgroundImg){
        Bitmap bm;
        if (resultCode == RESULT_OK && requestCode == CAMERA) {
            // 调用系统方法获取到的是被压缩过的图片，通过自定义路径轻松获取原始图片。
            bm = BitmapFactory.decodeFile(mFolder.getAbsolutePath() + File.separator + mImgName);
            if (img_state == IMG_BACKGROUND) {
                backgroundImg.setBackground(new BitmapDrawable(bm));
            }
        }
        if (resultCode == RESULT_OK && requestCode == ALBUM) {
            try {
                if (data != null) {
                    // 获取本地相册图片
                    Uri uri = data.getData();
                    ContentResolver cr = getContentResolver();
                    bm = BitmapFactory.decodeStream(cr.openInputStream(uri));
                    if (img_state == IMG_BACKGROUND) {
                        backgroundImg.setBackground(new BitmapDrawable(bm));
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }*/
    /*public static void setHeadPicImg(int requestCode, int resultCode, Intent data, View headPicImg){
        Bitmap bm;
        if (resultCode == RESULT_OK && requestCode == CAMERA) {
            // 调用系统方法获取到的是被压缩过的图片，通过自定义路径轻松获取原始图片。
            bm = BitmapFactory.decodeFile(mFolder.getAbsolutePath() + File.separator + mImgName);
            if (img_state == IMG_PORTRAIT) {
                headPicImg.setImageBitmap(ImageUtil.toRoundBitmap(bm));
            }
        }
        if (resultCode == RESULT_OK && requestCode == ALBUM) {
            try {
                if (data != null) {
                    // 获取本地相册图片
                    Uri uri = data.getData();
                    ContentResolver cr = getContentResolver();
                    bm = BitmapFactory.decodeStream(cr.openInputStream(uri));
                    if (img_state == IMG_PORTRAIT) {
                        headPicImg.setImageBitmap(ImageUtil.toRoundBitmap(bm));
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }*/

    /**
     * 设置从本地相册获取图片
     */
    private static void getImgFromAlbum(Activity activity) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        activity.startActivityForResult(intent, ALBUM);
    }

    /**
     * 设置从相机获取图片
     */
    private static void getImgFromCamra(Activity activity) {
        // 先检测是不是有内存卡。
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            mFolder = new File(Environment.getExternalStorageDirectory(), "bCache");
            // 判断手机中有没有这个文件夹，没有就新建。
            if (!mFolder.exists()) {
                mFolder.mkdirs();
            }
            // 自定义图片名字，这里是以毫秒数作为图片名。
            mImgName = System.currentTimeMillis() + ".jpg";
            Uri uri = Uri.fromFile(new File(mFolder, mImgName));
            // 调用系统拍照功能
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            activity.startActivityForResult(intent, CAMERA);
        } else {
            ToastUtil.showShort(activity, "未检测到SD卡");
        }
    }
}
