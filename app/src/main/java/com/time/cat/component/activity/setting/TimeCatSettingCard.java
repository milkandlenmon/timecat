package com.time.cat.component.activity.setting;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;

import com.time.cat.R;
import com.time.cat.component.activity.SettingTimeCatActivity;
import com.time.cat.component.activity.searchengine.SearchEngineActivity;
import com.time.cat.component.base.baseCard.AbsCard;
import com.time.cat.util.ConstantUtil;
import com.time.cat.util.UrlCountUtil;
import com.time.cat.mvp.view.HintTextView;
import com.shang.commonjar.contentProvider.SPHelper;

/**
 * Created by wangyan-pd on 2016/11/9.
 */

public class TimeCatSettingCard extends AbsCard {
    private SwitchCompat browserSwitch;
    private SwitchCompat floatTriggerSwitch;
    private HintTextView floatTriggerHintTextView;

    public TimeCatSettingCard(Context context) {
        super(context);
        initView(context);
        refresh();
    }

    private void refresh() {
        browserSwitch.setChecked(SPHelper.getBoolean(ConstantUtil.USE_LOCAL_WEBVIEW, true));
        floatTriggerSwitch.setChecked(SPHelper.getBoolean(ConstantUtil.USE_FLOAT_VIEW_TRIGGER, true));
        floatTriggerHintTextView.setShowHint(!floatTriggerSwitch.isChecked());
        floatTriggerHintTextView.setShowAnimation(true);
    }

    private void initView(Context context) {
        mContext = context;
        LayoutInflater.from(context).inflate(R.layout.card_timecat_setting, this);
        findViewById(R.id.setting_timecat).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                UrlCountUtil.onEvent(UrlCountUtil.CLICK_SETTINGS_SET_STYLE_TIMECAT);
                Intent intent = new Intent(mContext, SettingTimeCatActivity.class);
                mContext.startActivity(intent);
            }
        });
        findViewById(R.id.setting_search_engine).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                UrlCountUtil.onEvent(UrlCountUtil.CLICK_SETTINGS_SEARCH_ENGINE);
                Intent intent = new Intent(mContext, SearchEngineActivity.class);
                mContext.startActivity(intent);
            }
        });
        browserSwitch = (SwitchCompat) findViewById(R.id.browser_switch);
        browserSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton aSwitch, boolean isChecked) {
                SPHelper.save(ConstantUtil.USE_LOCAL_WEBVIEW, isChecked);
                UrlCountUtil.onEvent(UrlCountUtil.STATUS_USE_BUILTIN_BROWSER, isChecked);
            }
        });

        findViewById(R.id.browser_setting).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                browserSwitch.setChecked(!browserSwitch.isChecked());
            }
        });
        floatTriggerHintTextView = (HintTextView) findViewById(R.id.float_trigger_tv);
        floatTriggerSwitch = (SwitchCompat) findViewById(R.id.float_trigger_switch);
        floatTriggerSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SPHelper.save(ConstantUtil.USE_FLOAT_VIEW_TRIGGER, isChecked);
                UrlCountUtil.onEvent(UrlCountUtil.STATUS_FLOAT_VIEW_TRIGGER, isChecked);
                floatTriggerHintTextView.setShowHint(!isChecked);
            }
        });
        findViewById(R.id.float_trigger_rl).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                floatTriggerSwitch.setChecked(!floatTriggerSwitch.isChecked());
            }
        });
    }

}
