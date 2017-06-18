package com.shantoo.develop.library.utils;

import android.os.CountDownTimer;
import android.widget.TextView;

/**
 * 作者: shantoo on 2017/3/13 16:37.
 */

public class VerifyTimer extends CountDownTimer {
    /**
     * @param millisInFuture    The number of millis in the future from the call
     * to {@link #start()} until the countdown is done and {@link #onFinish()}
     * is called.
     * @param countDownInterval The interval along the way to receive
     * {@link #onTick(long)} callbacks.
     */
    private TextView verify;
    private int checkAbleColor = 0;
    private int unCheckAbleColor = 0;
    private int checkAbleBackGround = 0;
    private int unCheckAbleBackGround = 0;

    public VerifyTimer(long millisInFuture, long countDownInterval, TextView verify) {
        super(millisInFuture, countDownInterval);
        this.verify = verify;
    }

    public VerifyTimer(long millisInFuture, long countDownInterval,
                       TextView verify,
                       int unCheckAbleColor, int unCheckAbleBackGround,
                       int checkAbleColor, int checkAbleBackGround) {
        super(millisInFuture, countDownInterval);
        this.verify = verify;
        this.checkAbleColor = checkAbleColor;
        this.unCheckAbleColor = unCheckAbleColor;
        this.checkAbleBackGround = checkAbleBackGround;
        this.unCheckAbleBackGround = unCheckAbleBackGround;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        if (verify != null) {
            verify.setTextSize(13);
            String text = millisUntilFinished / 1000 + "秒";
            verify.setText(text);
            verify.setClickable(false);
            if (unCheckAbleBackGround != 0) {
                verify.setBackgroundResource(unCheckAbleBackGround);
            }
            if (unCheckAbleColor != 0) {
                verify.setTextColor(unCheckAbleColor);
            }
        }
    }

    @Override
    public void onFinish() {
        if (verify != null) {
            verify.setTextSize(13);
            verify.setText("获取验证码");
            verify.setClickable(true);
            if (checkAbleColor != 0) {
                verify.setTextColor(checkAbleColor);
            }
            if (checkAbleBackGround != 0) {
                verify.setBackgroundResource(checkAbleBackGround);
            }
        }
    }
}
