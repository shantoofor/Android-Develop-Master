package com.shantoo.develop.library.ui.widget.dialogfragment;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.shantoo.develop.library.R;

/**
 * 作者: shantoo on 2017/5/19 15:48.
 *
 * 二维码
 */

public class QRCodeDialog extends DialogFragment implements View.OnClickListener{

    private static String qr_code_url;
    private final static String key = "qr_code_url";
    private final static String TAG = QRCodeDialog.class.getSimpleName();

    public static QRCodeDialog newInstance(String qrCodeUrl) {
        qr_code_url = qrCodeUrl;
        QRCodeDialog newFragment = new QRCodeDialog();
        Bundle bundle = new Bundle();
        bundle.putString(key,qrCodeUrl);
        newFragment.setArguments(bundle);
        return newFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return init();
    }

    public void show(FragmentManager manager) {
        super.show(manager, TAG);
    }

    private View init(){
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View root = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_qr_code,null);
        ImageView qrCode = (ImageView) root.findViewById(R.id.qr_code);
        ImageView qrCodeClose = (ImageView) root.findViewById(R.id.qr_code_close);
        Glide.with(getActivity()).load(qr_code_url).into(qrCode);
        qrCodeClose.setOnClickListener(this);
        return root;
    }

    @Override
    public void onClick(View v) {
        if(R.id.qr_code_close == v.getId()){
            dismiss();
        }
    }
}
