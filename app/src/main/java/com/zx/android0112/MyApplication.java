package com.zx.android0112;

import android.app.Application;

import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;

/**
 * Created by 周旭 on 2017/1/12.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //初始化即创建语音配置对象，只有初始化后才可以使用MSC的各项服务。
        // 将“12345678”替换成您申请的APPID，申请地址：http://open.voicecloud.cn
        SpeechUtility.createUtility(this, SpeechConstant.APPID +"=5876c6cc");
    }
}
