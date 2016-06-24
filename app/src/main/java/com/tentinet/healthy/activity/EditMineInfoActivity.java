package com.tentinet.healthy.activity;

import android.content.Intent;
import android.text.InputType;
import android.text.method.NumberKeyListener;
import android.view.View;

import com.tentinet.healthy.R;
import com.tentinet.healthy.util.RegexUtil;
import com.tentinet.healthy.util.ToastUtil;
import com.tentinet.healthy.view.TitleView;
import com.tentinet.healthy.widget.EditInputBox;

/**
 * 填写个人信息
 * TODO
 * Author YKK
 * Date 2016/5/11 13:51
 * Copyright Copyright (c)2016 Shenzhen Tentinet Technology Co., Ltd. Inc. All rights reserved.
 */
public class EditMineInfoActivity extends BaseActivity {

    private TitleView view_title;
    private String title_key = "", value = "", number = "", id;
    private EditInputBox edit_content;


    @Override
    protected void getData() {
        title_key = getIntent().getExtras().getString(UpdateMineInfoActivity.EDIT_KEY);
        value = getIntent().getExtras().getString(UpdateMineInfoActivity.EDIT_VALUE);
        if (getIntent().hasExtra(UpdateMineInfoActivity.EDIT_TYPE)) {
            number = getIntent().getExtras().getString(UpdateMineInfoActivity.EDIT_TYPE);
        }
        if (getIntent().hasExtra(UpdateMineInfoActivity.EDIT_ID)) {
            id = getIntent().getExtras().getString(UpdateMineInfoActivity.EDIT_ID);
        }
    }

    @Override
    protected int setContent() {
        return R.layout.activity_edit_mine_info;
    }

    @Override
    protected void init() {
        view_title = (TitleView) findViewById(R.id.view_title);
        view_title.setBackImageButton();
        view_title.setTitle(getString(R.string.mine_edit_change) + title_key);
        view_title.setRightButton(getString(R.string.ok), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (number.equals(UpdateMineInfoActivity.EDIT_PHONE)) {
                    if (!RegexUtil.isMobileNO(edit_content.getInputContent())) {
                        ToastUtil.showLongToast(EditMineInfoActivity.this, getString(R.string.phone_error_msg));
                        return;
                    }
                } else if (number.equals(UpdateMineInfoActivity.EDIT_CERT) && id.equals("1")) {
                    if (!RegexUtil.isIdCard(edit_content.getInputContent())) {
                        ToastUtil.showLongToast(EditMineInfoActivity.this, getString(R.string.idcard_error_msg));
                        return;
                    }
                }
                Intent intent = new Intent();
                intent.putExtra(UpdateMineInfoActivity.EDIT_VALUE, edit_content.getInputContent());
                intent.setClass(EditMineInfoActivity.this, UpdateMineInfoActivity.class);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        edit_content = (EditInputBox) findViewById(R.id.edit_content);
        edit_content.setContent(value);
        edit_content.showSoftKeyBoard();
        if (number.equals(UpdateMineInfoActivity.EDIT_NUMBER)) {
            edit_content.getInputEditText().setInputType(InputType.TYPE_CLASS_NUMBER);
        } else if (number.equals(UpdateMineInfoActivity.EDIT_PHONE)) {
            edit_content.getInputEditText().setInputType(InputType.TYPE_CLASS_NUMBER);
        } else if (number.equals(UpdateMineInfoActivity.EDIT_CERT)) {
            edit_content.getInputEditText().setKeyListener(new NumberKeyListener() {
                @Override
                protected char[] getAcceptedChars() {
                    char[] myChar = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0', 'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p', 'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'z', 'x', 'c', 'v', 'b', 'n', 'm', 'Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'O', 'P', 'A', 'S', 'D', 'F', 'G', 'H', 'J', 'K', 'L', 'Z', 'X', 'C', 'V', 'B', 'N', 'M'};
                    return myChar;
                }

                @Override
                public int getInputType() {
                    return InputType.TYPE_CLASS_TEXT;
                }
            });
        }
    }

    @Override
    protected void registerEvent() {

    }
}
