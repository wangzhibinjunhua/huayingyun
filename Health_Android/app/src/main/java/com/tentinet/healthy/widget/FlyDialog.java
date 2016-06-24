package com.tentinet.healthy.widget;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationSet;
import android.view.animation.Interpolator;

/**
 * Description
 * TODO
 * Author YKK
 * Date 2016/6/14 17:39
 * Copyright Copyright (c)2016 Shenzhen Tentinet Technology Co., Ltd. Inc. All rights reserved.
 */
public class FlyDialog extends Dialog {
    private Context context;
    private int duration = 1000;
    private Interpolator interpolator;
    private AnimatorSet animatorSet = new AnimatorSet();

    public FlyDialog(Context context) {
        super(context);
    }

    public FlyDialog(Context context, int styleId) {
        super(context, styleId);
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        animatorSet.setDuration(duration);
        if (interpolator != null) {
            animatorSet.setInterpolator(interpolator);
        }
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animatorSet.start();
    }

    public void showBounceTopEenter(View view) {
        DisplayMetrics dm = view.getContext().getResources().getDisplayMetrics();
        animatorSet.playTogether(ObjectAnimator.ofFloat(view, "alpha", 0, 1, 1, 1),//
                ObjectAnimator.ofFloat(view, "translationY", -250 * dm.density, 30, -10, 0));
    }

    public void showZoomInEenter(View view) {
        animatorSet.playTogether(//
                ObjectAnimator.ofFloat(view, "scaleX", 0.5f, 1),//
                ObjectAnimator.ofFloat(view, "scaleY", 0.5f, 1),//
                ObjectAnimator.ofFloat(view, "alpha", 0, 1));//

    }

    public void showFallRotateEenter(View view){
        animatorSet.playTogether(ObjectAnimator.ofFloat(view, "scaleX", 2, 1.5f, 1),//
                ObjectAnimator.ofFloat(view, "scaleY", 2, 1.5f, 1),//
                ObjectAnimator.ofFloat(view, "rotation", 45, 0),//
                ObjectAnimator.ofFloat(view, "alpha", 0, 1));
    }

    public void showFallEenter(View view){
        animatorSet.playTogether(ObjectAnimator.ofFloat(view, "scaleX", 2f, 1.5f, 1f).setDuration(duration),//
                ObjectAnimator.ofFloat(view, "scaleY", 2f, 1.5f, 1f).setDuration(duration),//
                ObjectAnimator.ofFloat(view, "alpha", 0, 1f).setDuration(duration));
    }

    public void showBounceEenter(View view){
        animatorSet.playTogether(ObjectAnimator.ofFloat(view, "alpha", 0, 1, 1, 1), //
                ObjectAnimator.ofFloat(view, "scaleX", 0.5f, 1.05f, 0.95f, 1),//
                ObjectAnimator.ofFloat(view, "scaleY", 0.5f, 1.05f, 0.95f, 1));
    }

    public void showBounceBottomExit(View view){
        DisplayMetrics dm = view.getContext().getResources().getDisplayMetrics();
        animatorSet.playTogether(//
                ObjectAnimator.ofFloat(view, "translationY", 0, 250 * dm.density), //
                ObjectAnimator.ofFloat(view, "alpha", 1, 0));
    }

    public void showJelly(View view){
        animatorSet.playTogether(//
                ObjectAnimator.ofFloat(view, "scaleX", 0.3f, 0.5f, 0.9f, 0.8f, 0.9f, 1),//
                ObjectAnimator.ofFloat(view, "scaleY", 0.3f, 0.5f, 0.9f, 0.8f, 0.9f, 1),//
                ObjectAnimator.ofFloat(view, "alpha", 0.2f, 1));
    }
}
