package com.tentinet.healthy.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.TextUtils.TruncateAt;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tentinet.healthy.R;


/**
 * 标题.
 *
 * @author paladin
 * @Description
 * @date 2014年7月18日
 * @Copyright: Copyright (c) 2014 Shenzhen Tentinet Technology Co., Ltd. Inc.
 * All rights reserved.
 */
public class TitleView extends LinearLayout {

    /**
     * 上下文环境
     */
    private Context context;
    /**
     * 标题
     */
    private TextView txt_title;
    /**
     * 左文字按钮
     */
    private Button btn_left;
    /**
     * 右文字按钮
     */
    private Button btn_right;
    /**
     * 右侧按钮提示红点
     */
    private ImageView img_tip;
    /**
     * 右侧图标提示红点
     */
    private ImageView img_rightOne_tip;
    /**
     * 左图标按钮
     */
    private ImageButton imgbtn_left;
    /**
     * 右一图标按钮
     */
    private ImageButton imgbtn_rightOne;
    /**
     * 右二图标按钮
     */
    private ImageButton imgbtn_rightTwo;

    /**
     * 左右边距
     */
    private int marginHorizontal;
    /**
     * 上下边距
     */
    private int marginVertical;
    /**
     * 标题文字大小
     */
    private float titleFontSize;
    /**
     * 标题文字颜色
     */
    private int titleFontColor;
    /**
     * 返回
     */
    private String back;
    private int backFontColor;
    private int resBack;
    private int resBackDrawable;
    /**
     * 背景色
     */
    private int backgroundColor = -1;
    private int background = -1;
    private int padding = 10;
    /**
     * 标题电量间隔
     */
    private RelativeLayout statusbar;
    private RelativeLayout titleView;

    public TitleView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public TitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public TitleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        init();
    }

    /**
     * 设置背景
     *
     * @param background
     * @version 1.0
     * @createTime 2015年6月24日, 上午9:22:40
     * @updateTime 2015年6月24日, 上午9:22:40
     * @createAuthor 郑梓笙
     * @updateAuthor 郑梓笙
     * @updateInfo (此处输入修改内容, 若无修改可不写.)
     */
    public void setBackground(int background) {
        this.setBackgroundResource(background);
    }

    /**
     * 初始化.
     *
     * @version 1.0
     * @createTime 2014年7月20日, 上午11:22:37
     * @updateTime 2014年7月20日, 上午11:22:37
     * @createAuthor paladin
     * @updateAuthor paladin
     * @updateInfo
     */
    @SuppressWarnings("ResourceType")
    private void init() {
        initParams(); // 初始化参数.
        this.setOrientation(VERTICAL);
        // 设置背景色.
        if (-1 != backgroundColor) {
            super.setBackgroundColor(getResources().getColor(backgroundColor));
        } else if (-1 != background) {
            this.setBackgroundResource(background);
        }
        statusbar = new RelativeLayout(context);
        RelativeLayout.LayoutParams statusbarParams = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, getResources().getDimensionPixelSize(R.dimen.title_view_padding_top));

        if (-1 != backgroundColor) {
            statusbar.setBackgroundColor(getResources().getColor(backgroundColor));
        }
        this.addView(statusbar, statusbarParams);

        titleView = new RelativeLayout(context);
        RelativeLayout.LayoutParams titleviewParams = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

        this.addView(titleView, titleviewParams);


        // 添加标题文字控件.
        txt_title = new TextView(context);
//		txt_title.setId(1000);
        RelativeLayout.LayoutParams titleParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        titleParams.addRule(RelativeLayout.CENTER_IN_PARENT,
                RelativeLayout.TRUE);
        titleParams.topMargin = marginVertical;
        titleParams.bottomMargin = marginVertical;
        txt_title.setPadding(padding, padding, padding, padding);
        titleView.addView(txt_title, titleParams);
        txt_title.setTextSize(TypedValue.COMPLEX_UNIT_PX, titleFontSize);
//		txt_title.setMaxWidth(getResources().getDimensionPixelSize(
//				R.dimen.title_max));
        txt_title.setEllipsize(TruncateAt.END);
        txt_title.setSingleLine(true);
        txt_title.setTextColor(context.getResources().getColor(titleFontColor));

        // 添加左一文字按钮和左一图标按钮控件.
        RelativeLayout.LayoutParams leftParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        leftParams.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
        leftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT,
                RelativeLayout.TRUE);
        leftParams.topMargin = marginVertical;
        leftParams.bottomMargin = marginVertical;
        leftParams.leftMargin = marginHorizontal;
        btn_left = new Button(context);
        btn_left.setPadding(padding, padding, padding, padding);
        titleView.addView(btn_left, leftParams);
        imgbtn_left = new ImageButton(context);
        imgbtn_left.setPadding(padding, padding, padding, padding);
        imgbtn_left.setBackgroundColor(Color.TRANSPARENT);
        titleView.addView(imgbtn_left, leftParams);
        btn_left.setVisibility(View.GONE);
        btn_left.setTextColor(context.getResources().getColor(backFontColor));
        imgbtn_left.setVisibility(View.GONE);

        // 添加右一文字按钮和右一图标按钮控件.
        RelativeLayout.LayoutParams rightOneParams = new RelativeLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        rightOneParams.addRule(RelativeLayout.CENTER_VERTICAL,
                RelativeLayout.TRUE);
        rightOneParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,
                RelativeLayout.TRUE);
        rightOneParams.topMargin = marginVertical;
        rightOneParams.bottomMargin = marginVertical;
        rightOneParams.rightMargin = marginHorizontal;
        btn_right = new Button(context);
        btn_right.setBackgroundColor(context.getResources().getColor(
                R.color.transparent));
        btn_right.setPadding(padding, padding, padding, padding);
        btn_right.setId(1001);
        btn_right.setTextSize(14);
        titleView.addView(btn_right, rightOneParams);
        img_tip = new ImageView(context);
        RelativeLayout.LayoutParams tipsParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        tipsParams.addRule(RelativeLayout.ALIGN_RIGHT, 1001);
        tipsParams.addRule(RelativeLayout.ALIGN_TOP, 1001);
        img_tip.setPadding(0, padding, 5, 0);
        img_tip.setVisibility(View.GONE);
//		img_tip.setImageResource(R.drawable.icon_tip_small);
        titleView.addView(img_tip, tipsParams);
        imgbtn_rightOne = new ImageButton(context);
//		imgbtn_rightOne.setId(100001);
        imgbtn_rightOne.setPadding(padding, padding, padding, padding);
        imgbtn_rightOne.setBackgroundColor(Color.TRANSPARENT);
        titleView.addView(imgbtn_rightOne, rightOneParams);
        btn_right.setVisibility(View.GONE);
        imgbtn_rightOne.setVisibility(View.GONE);
        btn_right.setTextColor(context.getResources().getColor(backFontColor));

        //添加右图标红点
        img_rightOne_tip = new ImageView(context);
        RelativeLayout.LayoutParams rightTipsParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        rightTipsParams.addRule(RelativeLayout.ALIGN_RIGHT, 100001);
        rightTipsParams.addRule(RelativeLayout.ALIGN_TOP, 100001);
        img_rightOne_tip.setPadding(0, 5, 5, 0);
        img_rightOne_tip.setVisibility(View.GONE);
//		img_rightOne_tip.setImageResource(R.drawable.icon_tip_small);
        titleView.addView(img_rightOne_tip, rightTipsParams);

        // 添加右二文字按钮和右二图标按钮控件.
        RelativeLayout.LayoutParams rightTwoParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        rightTwoParams.addRule(RelativeLayout.CENTER_VERTICAL,
                RelativeLayout.TRUE);
        rightTwoParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT,RelativeLayout.TRUE);
//        rightTwoParams.addRule(RelativeLayout.LEFT_OF, imgbtn_rightOne.getId());
        rightTwoParams.rightMargin = marginVertical*8;
        rightTwoParams.topMargin = marginVertical;
        rightTwoParams.bottomMargin = marginVertical;
        imgbtn_rightTwo = new ImageButton(context);
        imgbtn_rightTwo.setPadding(padding, padding, padding, padding);
        imgbtn_rightTwo.setBackgroundColor(Color.TRANSPARENT);
        titleView.addView(imgbtn_rightTwo, rightTwoParams);
        imgbtn_rightTwo.setVisibility(View.GONE);
    }

    /**
     * 初始化参数.
     *
     * @version 1.0
     * @createTime 2014年7月20日, 上午11:34:29
     * @updateTime 2014年7月20日, 上午11:34:29
     * @createAuthor paladin
     * @updateAuthor paladin
     * @updateInfo
     */
    private void initParams() {
        marginHorizontal = getResources().getDimensionPixelSize(
                R.dimen.spacing_small);
        marginVertical = getResources().getDimensionPixelSize(
                R.dimen.spacing_small);
        padding = getResources().getDimensionPixelSize(R.dimen.spacing_default);
        titleFontSize = getResources().getDimension(R.dimen.font_size_big);
        titleFontColor = R.color.white;
        back = context.getString(R.string.back);
        resBack = R.mipmap.icon_back_white;
        resBackDrawable = R.mipmap.icon_back_white;
        backFontColor = R.color.white;
        backgroundColor = R.color.title_background;
    }

    /**
     * 设置标题.
     *
     * @param title 标题文字.
     * @version 1.0
     * @createTime 2014年7月20日, 上午11:32:11
     * @updateTime 2014年7月20日, 上午11:32:11
     * @createAuthor paladin
     * @updateAuthor paladin
     * @updateInfo
     */
    public void setTitle(String title) {
        txt_title.setText(title);
    }

    /**
     * 设置标题背景颜色.
     *
     * @param backgroundColor 颜色引用.
     * @version 1.0
     * @createTime 2014年7月20日, 上午09:21:11
     * @updateTime 2014年7月20日, 上午09:21:11
     * @createAuthor paladin
     * @updateAuthor paladin
     * @updateInfo
     */
    @SuppressWarnings("ResourceType")
    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
        super.setBackgroundColor(getResources().getColor(backgroundColor));
    }


        /**
         * 设置标题文字颜色.
         *
         * @param titleFontColor 标题文字颜色.
         * @version 1.0
         * @createTime 2014年10月31日, 下午12:00:27
         * @updateTime 2014年10月31日, 下午12:00:27
         * @createAuthor paladin
         * @updateAuthor paladin
         * @updateInfo
         */
    public void setTitleTextColor(int titleFontColor) {
        this.titleFontColor = titleFontColor;
        txt_title.setTextColor(context.getResources().getColor(titleFontColor));
  
    }

    /**
     * 设置标题.
     *
     * @param resID 标题文字资源引用.
     * @version 1.0
     * @createTime 2014年7月20日, 上午11:32:24
     * @updateTime 2014年7月20日, 上午11:32:24
     * @createAuthor paladin
     * @updateAuthor paladin
     * @updateInfo
     */
    public void setTitle(int resID) {
        txt_title.setText(resID);
    }
    /**
     * 设置标题栏背景颜色.
     *
     * @param backgroundColor 颜色引用.
     *                        <h3>Version</h3> 1.0
     *                        <h3>CreateTime</h3> 2016/3/24,14:25
     *                        <h3>UpdateTime</h3> 2016/3/24,14:25
     *                        <h3>CreateAuthor</h3> （郑梓笙）
     *                        <h3>UpdateAuthor</h3>
     *                        <h3>UpdateInfo</h3> (此处输入修改内容,若无修改可不写.)
     */
    public void setStatusbarColor(int backgroundColor) {
        if (-1 != backgroundColor) {
            statusbar.setBackgroundColor(getResources().getColor(backgroundColor));
        }
    }

    /**
     * 设置返回文字按钮.
     *
     * @version 1.0
     * @createTime 2014年7月20日, 上午11:43:24
     * @updateTime 2014年7月20日, 上午11:43:24
     * @createAuthor paladin
     * @updateAuthor paladin
     * @updateInfo
     */
    public void setBackButton() {
        btn_left.setText(back);
        btn_left.setTextColor(context.getResources().getColor(backFontColor));
        btn_left.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                finishActivity();
            }

        });
        btn_left.setVisibility(View.VISIBLE);
    }

	/**
	 * 设置返回图标加文字按钮.
	 *
	 * @version 1.0
	 * @createTime 2014年8月28日, 下午1:56:47
	 * @updateTime 2014年8月28日, 下午1:56:47
	 * @createAuthor paladin
	 * @updateAuthor paladin
	 * @updateInfo
	 */
	public void setBackDrawableButton() {
		btn_left.setTextColor(context.getResources().getColor(backFontColor));
		btn_left.setCompoundDrawablesWithIntrinsicBounds(getResources()
				.getDrawable(resBackDrawable), null, null, null);
		btn_left.setCompoundDrawablePadding(context.getResources().getDimensionPixelOffset(R.dimen.spacing_small));
		btn_left.setBackgroundColor(getResources().getColor(R.color.transparent));
		btn_left.setText(back);
		btn_left.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finishActivity();
			}

		});
		btn_left.setVisibility(View.VISIBLE);
	}

	/**
	 * 设置消息提示红点
	 *
	 * @param isShow 是否显示（true显示 false不显示）
	 * @version 1.0
	 * @createTime 2015年3月31日, 下午5:07:15
	 * @updateTime 2015年3月31日, 下午5:07:15
	 * @createAuthor 王治粮
	 * @updateAuthor 王治粮
	 * @updateInfo
	 */
	public void setImageTips(boolean isShow) {
		if (isShow) {
			img_tip.setVisibility(View.VISIBLE);
		} else {
			img_tip.setVisibility(View.GONE);
		}
	}

	/**
	 * 设置右侧图标提示红点
	 *
	 * @param isShow
	 * @version 1.0
	 * @createTime 2015年7月8日, 下午5:26:09
	 * @updateTime 2015年7月8日, 下午5:26:09
	 * @createAuthor 郑梓笙
	 * @updateAuthor 郑梓笙
	 * @updateInfo (此处输入修改内容, 若无修改可不写.)
	 */
	public void setRightImageTips(boolean isShow) {
		if (isShow) {
			img_rightOne_tip.setVisibility(View.VISIBLE);
		} else {
			img_rightOne_tip.setVisibility(View.GONE);
		}
	}

	/**
	 * 设置返回图标按钮.
	 *
	 * @version 1.0
	 * @createTime 2014年7月20日, 上午11:44:40
	 * @updateTime 2014年7月20日, 上午11:44:40
	 * @createAuthor paladin
	 * @updateAuthor paladin
	 * @updateInfo
	 */
	public void setBackImageButton() {
		imgbtn_left.setImageResource(resBack);
		imgbtn_left.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finishActivity();
			}

		});
		imgbtn_left.setVisibility(View.VISIBLE);
	}

	/**
	 * 设置返回图标按钮.
	 *
	 * @param resID 返回图标引用.
	 * @version 1.0
	 * @createTime 2014年7月20日, 上午11:44:40
	 * @updateTime 2014年7月20日, 上午11:44:40
	 * @createAuthor paladin
	 * @updateAuthor paladin
	 * @updateInfo
	 */
	public void setBackImageButton(int resID) {
		imgbtn_left.setImageResource(resID);
		imgbtn_left.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finishActivity();
			}

		});
		imgbtn_left.setVisibility(View.VISIBLE);
	}

	/**
	 * 设置返回图标按钮
	 *
	 * @param resID         图片引用
	 * @param clickListener 点击监听对象
	 * @version 1.0
	 * @createTime 2015年3月9日, 下午7:11:27
	 * @updateTime 2015年3月9日, 下午7:11:27
	 * @createAuthor 王治粮
	 * @updateAuthor 王治粮
	 * @updateInfo
	 */
	public void setBackImageButton(int resID, OnClickListener clickListener) {
		imgbtn_left.setImageResource(resID);
		imgbtn_left.setOnClickListener(clickListener);
		imgbtn_left.setVisibility(View.VISIBLE);
	}

	/**
	 * 关闭显示标题控件的界面.
	 *
	 * @version 1.0
	 * @createTime 2014年7月20日, 上午11:46:55
	 * @updateTime 2014年7月20日, 上午11:46:55
	 * @createAuthor paladin
	 * @updateAuthor paladin
	 * @updateInfo
	 */
	private void finishActivity() {
		closeSoftKeyBoard();
		((Activity) context).finish();
//		((Activity) context).overridePendingTransition(R.anim.exit_enter,
//				R.anim.exit_exit);
	}

	/**
	 * 设置左文字按钮.
	 *
	 * @param btnTxt 按钮文字.
	 * @param clk    点击监听对象.
	 * @version 1.0
	 * @createTime 2014年7月20日, 上午11:48:15
	 * @updateTime 2014年7月20日, 上午11:48:15
	 * @createAuthor paladin
	 * @updateAuthor paladin
	 * @updateInfo
	 */
	public void setLeftButton(String btnTxt, OnClickListener clk) {
		btn_left.setText(btnTxt);
		btn_left.setBackgroundDrawable(null);
		btn_left.setOnClickListener(clk);
		btn_left.setVisibility(View.VISIBLE);
	}

	/**
	 * 获取左侧按钮
	 * <h3>Version</h3> 1.0
	 * <h3>CreateTime</h3> 2016/3/8,14:37
	 * <h3>UpdateTime</h3> 2016/3/8,14:37
	 * <h3>CreateAuthor</h3> 覃励
	 * <h3>UpdateAuthor</h3>
	 * <h3>UpdateInfo</h3> (此处输入修改内容,若无修改可不写.)
	 */
	public Button getLeftButton(){
		return btn_left;
	}

	/**
	 * 更新左侧按钮文字
	 *
	 * @param text
	 * @version 1.0
	 * @createTime 2014年11月25日, 下午5:29:50
	 * @updateTime 2014年11月25日, 下午5:29:50
	 * @createAuthor 王治粮
	 * @updateAuthor 王治粮
	 * @updateInfo
	 */
	public void setleftBtnText(String text) {
		btn_left.setText(text);
	}

	/**
	 * 更新右侧按钮文字
	 *
	 * @param text
	 * @version 1.0
	 * @createTime 2015年4月15日, 上午11:21:04
	 * @updateTime 2015年4月15日, 上午11:21:04
	 * @createAuthor 郑梓笙
	 * @updateAuthor 郑梓笙
	 * @updateInfo (此处输入修改内容, 若无修改可不写.)
	 */
	public void setRightBtnText(String text) {
		btn_right.setText(text);
	}

	/**
	 * 设置左按钮四周图标.
	 *
	 * @param left   左图标.
	 * @param top    上图标.
	 * @param right  右图标.
	 * @param bottom 下图标.
	 * @version 1.0
	 * @createTime 2014年11月25日, 上午11:26:59
	 * @updateTime 2014年11月25日, 上午11:26:59
	 * @createAuthor paladin
	 * @updateAuthor paladin
	 * @updateInfo
	 */
	public void setLeftButtonDrawable(Drawable left, Drawable top,
									  Drawable right, Drawable bottom) {
		if (null != left) {
			left.setBounds(0, 0, left.getMinimumWidth(),
					left.getMinimumHeight());
		}
		if (null != top) {
			top.setBounds(0, 0, top.getMinimumWidth(), top.getMinimumHeight());
		}
		if (null != right) {
			right.setBounds(0, 0, right.getMinimumWidth(),
					right.getMinimumHeight());
		}
		if (null != bottom) {
			bottom.setBounds(0, 0, bottom.getMinimumWidth(),
					bottom.getMinimumHeight());
		}
		btn_left.setCompoundDrawables(left, top, right, bottom);
		btn_left.setCompoundDrawablePadding(3);
	}

	/**
	 * 设置左按钮四周图标.
	 *
	 * @param left   左图标.
	 * @param top    上图标.
	 * @param right  右图标.
	 * @param bottom 下图标.
	 * @version 1.0
	 * @createTime 2015年4月13日, 上午9:56:13
	 * @updateTime 2015年4月13日, 上午9:56:13
	 * @createAuthor 郑梓笙
	 * @updateAuthor 郑梓笙
	 * @updateInfo (此处输入修改内容, 若无修改可不写.)
	 */
	public void setRightButtonDrawable(Drawable left, Drawable top,
									   Drawable right, Drawable bottom) {
		if (null != left) {
			left.setBounds(0, 0, left.getMinimumWidth(),
					left.getMinimumHeight());
		}
		if (null != top) {
			top.setBounds(0, 0, top.getMinimumWidth(), top.getMinimumHeight());
		}
		if (null != right) {
			right.setBounds(0, 0, right.getMinimumWidth(),
					right.getMinimumHeight());
		}
		if (null != bottom) {
			bottom.setBounds(0, 0, bottom.getMinimumWidth(),
					bottom.getMinimumHeight());
		}
		btn_right.setCompoundDrawables(left, top, right, bottom);
		btn_right.setCompoundDrawablePadding(context.getResources()
				.getDimensionPixelOffset(R.dimen.spacing_mini));
	}

	/**
	 * 设置左文字按钮.
	 *
	 * @param resID 按钮文字引用id.
	 * @param clk   点击监听对象.
	 * @version 1.0
	 * @createTime 2014年7月20日, 上午11:48:15
	 * @updateTime 2014年7月20日, 上午11:48:15
	 * @createAuthor paladin
	 * @updateAuthor paladin
	 * @updateInfo
	 */
	public void setLeftButton(int resID, OnClickListener clk) {
		btn_left.setText(resID);
		btn_left.setOnClickListener(clk);
		btn_left.setBackgroundDrawable(null);
		btn_left.setVisibility(View.VISIBLE);
	}

	/**
	 * 设置左图片按钮.
	 *
	 * @param resID 按钮文字引用id.
	 * @param clk   点击监听对象.
	 * @version 1.0
	 * @createTime 2014年7月20日, 上午11:48:15
	 * @updateTime 2014年7月20日, 上午11:48:15
	 * @createAuthor paladin
	 * @updateAuthor paladin
	 * @updateInfo
	 */
	public void setLeftImageButton(int resID, OnClickListener clk) {
		imgbtn_left.setImageResource(resID);
		imgbtn_left.setOnClickListener(clk);
		imgbtn_left.setVisibility(View.VISIBLE);
	}

	/**
	 * 设置右文字按钮.
	 *
	 * @param btnTxt 按钮文字.
	 * @param clk    点击监听对象.
	 * @version 1.0
	 * @createTime 2014年7月20日, 上午11:51:04
	 * @updateTime 2014年7月20日, 上午11:51:04
	 * @createAuthor paladin
	 * @updateAuthor paladin
	 * @updateInfo
	 */
	public void setRightButton(String btnTxt, OnClickListener clk) {
		btn_right.setText(btnTxt);
		btn_right.setOnClickListener(clk);
		btn_right.setVisibility(View.VISIBLE);
	}

	/**
	 * 设置右文字按钮.
	 *
	 * @param resID 按钮文字引用id.
	 * @param clk   点击监听对象.
	 * @version 1.0
	 * @createTime 2014年7月20日, 上午11:51:04
	 * @updateTime 2014年7月20日, 上午11:51:04
	 * @createAuthor paladin
	 * @updateAuthor paladin
	 * @updateInfo
	 */
	public void setRightButton(int resID, OnClickListener clk) {
		btn_right.setText(resID);
		btn_right.setOnClickListener(clk);
		btn_right.setVisibility(View.VISIBLE);
    }

    /**
	 * 设置右一图标按钮.
	 *
	 * @param resID 图标引用id.
	 * @param clk   点击监听对象.
	 * @version 1.0
	 * @createTime 2014年7月20日, 上午11:52:52
	 * @updateTime 2014年7月20日, 上午11:52:52
	 * @createAuthor paladin
	 * @updateAuthor paladin
	 * @updateInfo
	 */
	public void setRightOneImageButton(int resID, OnClickListener clk) {
		imgbtn_rightOne.setImageResource(resID);
		imgbtn_rightOne.setOnClickListener(clk);
		imgbtn_rightOne.setVisibility(View.VISIBLE);
	}

	public ImageButton getImgbtn_rightOne() {
		return imgbtn_rightOne;
	}
    public ImageButton getImgbtn_rightTwo() {
		return imgbtn_rightTwo;
	}
    public ImageButton getImgbtn_left() {
		return imgbtn_left;
	}

	/**
	 * 设置右一图标
	 *
	 * @param resID
	 * @version 1.0
	 * @createTime 2015年5月4日, 下午4:52:10
	 * @updateTime 2015年5月4日, 下午4:52:10
	 * @createAuthor 郑梓笙
	 * @updateAuthor 郑梓笙
	 * @updateInfo (此处输入修改内容, 若无修改可不写.)
	 */
	public void setRightOneImage(int resID) {
		imgbtn_rightOne.setImageResource(resID);
	}

	/**
	 * 设置右一图标按钮
	 *
	 * @param resID     图标引用id
	 * @param longclick 长按监听对象
	 * @version 1.0
	 * @createTime 2015年3月6日, 下午4:26:02
	 * @updateTime 2015年3月6日, 下午4:26:02
	 * @createAuthor 王治粮
	 * @updateAuthor 王治粮
	 * @updateInfo
	 */
	public void setRightOneImageButton(int resID, OnLongClickListener longclick) {
		imgbtn_rightOne.setImageResource(resID);
		imgbtn_rightOne.setOnLongClickListener(longclick);
		imgbtn_rightOne.setVisibility(View.VISIBLE);
	}

	/**
	 * 设置右二图标按钮(若右一图标按钮未显示,则界面会出现一场).
	 *
	 * @param resID 图标引用id.
	 * @param clk   点击监听对象.
	 * @version 1.0
	 * @createTime 2014年7月20日, 上午11:53:56
	 * @updateTime 2014年7月20日, 上午11:53:56
	 * @createAuthor paladin
	 * @updateAuthor paladin
	 * @updateInfo
	 */
	public void setRightTwoImageButton(int resID, OnClickListener clk) {
		imgbtn_rightTwo.setImageResource(resID);
		imgbtn_rightTwo.setOnClickListener(clk);
		imgbtn_rightTwo.setVisibility(View.VISIBLE);
	}

	/**
	 * 关闭键盘.
	 *
	 * @version 1.0
	 * @createTime 2014年10月30日, 上午10:32:39
	 * @updateTime 2014年10月30日, 上午10:32:39
	 * @createAuthor paladin
	 * @updateAuthor paladin
	 * @updateInfo
	 */
	private void closeSoftKeyBoard() {
		InputMethodManager imm = (InputMethodManager) context
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		// 得到InputMethodManager的实例
		if (null != ((Activity) context).getCurrentFocus() && imm.isActive()) { // 如果开启
			imm.hideSoftInputFromWindow(((Activity) context).getCurrentFocus()
					.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
		}
	}


	/**
	 * 是否关闭键盘
	 *
	 * @param show true 显示， false 关闭键盘
	 * @version 1.0
	 * @createTime 2014年4月9日, 下午4:55:52
	 * @updateTime 2014年4月9日, 下午4:55:52
	 * @createAuthor CodeApe
	 * @updateAuthor CodeApe
	 * @updateInfo (此处输入修改内容, 若无修改可不写.)
	 */
	public void showInput(boolean show) {
		try {
			if (show) {
				InputMethodManager imm = (InputMethodManager) context
						.getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.showSoftInputFromInputMethod(((Activity) context)
						.getCurrentFocus().getApplicationWindowToken(), 0);
			} else {
				InputMethodManager imm = (InputMethodManager) context
						.getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(((Activity) context)
						.getCurrentFocus().getApplicationWindowToken(), 0);
			}
		} catch (NullPointerException e1) {

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

    /**
     * <h3>Description</h3> 获取右边button
     * <h3>Version</h3> 1.0
     * <h3>CreateTime</h3> 2016/3/21 14:56
     * <h3>UpdateTime</h3> 2016/3/21  14:56
     * <h3>CreateAuthor</h3> 陈思齐
     * <h3>UpdateAuthor</h3>
     * <h3>UpdateInfo</h3>(此处输入修改内容,若无修改可不写.)
     */
    public Button getRightButton(){
        return btn_right;
    }
}
