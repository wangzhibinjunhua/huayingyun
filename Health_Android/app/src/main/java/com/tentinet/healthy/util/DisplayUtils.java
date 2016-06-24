package com.tentinet.healthy.util;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.lang.reflect.Field;

public class DisplayUtils {

    private static final String STATUSBAR_CLASS_NAME = "com.android.internal.R$dimen";
    private static final String STATUSBAR_FIELD_HEIGHT = "status_bar_height";

    /**
     * Convert the given dip to px.
     * See also {@link #px2dip(Context, float)}
     *
     * @param context The context of Android operation system (OS).
     * @param dpValue The dip to be converted
     * @return The px value
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * Convert the given px to dip
     * See also {@link #dip2px(Context, float)}
     *
     * @param context The context of Android operation system (OS).
     * @param pxValue The px to be converted
     * @return The dip value.
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * Get the width and height of the screen in orientation portrait (width < height).
     * See also {@link #getScreenHeight(Context)}, {@link #getScreenWidth(Context)}.
     *
     * @param context The context of Android operation system (OS).
     * @param outMetrics An int array: metrics[0]: the width of the screen
     *            metrics[1]: the height of the screen
     */

    public static void getScreenSizeMetrics(Context context, int outMetrics[]) {
        if (null == outMetrics) {
            outMetrics = new int[2];
        }

        DisplayMetrics displayMetrics = getDisplayMetrics(context);
        int screenHeight = displayMetrics.heightPixels;
        int screenWidth = displayMetrics.widthPixels;

        if (screenHeight < screenWidth) {
            int temp = screenWidth;
            screenWidth = screenHeight;
            screenHeight = temp;
        }

        outMetrics[0] = screenWidth;
        outMetrics[1] = screenHeight;
    }

    /**
     * Get the width of the screen.
     * See also {@link #getDisplayMetrics(Context)}, {@link #getScreenHeight(Context)}.
     *
     * @param context The context of Android operation system (OS).
     * @return
     */
    public static int getScreenWidth(Context context) {
        return getDisplayMetrics(context).widthPixels;
    }

    /**
     * Get the height of the screen.
     * See also {@link #getDisplayMetrics(Context)}, {@link #getScreenWidth(Context)}
     * 
     * @param context The context of Android operation system (OS).
     * @return
     */
    public static int getScreenHeight(Context context) {
        return getDisplayMetrics(context).heightPixels;
    }

    /**
     * Get the height of status bar
     * 
     * @param context The context of Android operation system (OS).
     * @return The height of status bar.
     *         <br>0, if failed.
     */
    public static int getStatusBarHeight(Context context) {
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0;
        int sbar = 0;
        try {
            c = Class.forName(STATUSBAR_CLASS_NAME);
            obj = c.newInstance();
            field = c.getField(STATUSBAR_FIELD_HEIGHT);
            x = Integer.parseInt(field.get(obj).toString());
            sbar = context.getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return sbar;
    }
    
    private static DisplayMetrics getDisplayMetrics(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

}
